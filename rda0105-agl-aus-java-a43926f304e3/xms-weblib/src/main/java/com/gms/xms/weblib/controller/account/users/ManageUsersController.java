package com.gms.xms.weblib.controller.account.users;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.CryptUtils;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.common.utils.PasswordUtils;
import com.gms.xms.filter.account.users.manage.UserFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.UserLevelModel;
import com.gms.xms.model.UserModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.UserLevelVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Posted from ManageUsersController
 * <p>
 * Author DatTV Oct 20, 2015
 */
public class ManageUsersController extends AdminJsonBaseController {

    private static final long serialVersionUID = 5181358328356390581L;

    private String userCode;
    private String userLevelId;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private Paging<UserModel> users;
    private List<UserLevelModel> userLevels;
    private String userId;
    private UserModel user;

    public String show() {
        try {
            // Default order by is customer code (user code)
            orderField = "user_code";
            orderType = "0";
            preparePageSizes();
            prepareUserLevels();
            loadUsers();
            determineAdminLevel();
        } catch (CustomException e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText("System internal error. Please contact the administrator for supporting."));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String search() {
        try {
            loadUsers();
        } catch (CustomException e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText("System internal error. Please contact the administrator for supporting."));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String save() {
        try {
            prepareUserLevels();
            if (!validUser(user)) {
                setErrorCode(ErrorCode.FIELD_ERROR);
            } else {
                IUserService userService = new UserServiceImp();
                UserVo userVo = ModelUtils.createVoFromModel(user, UserVo.class);
                userVo.setPassword(CryptUtils.Encrypt(userVo.getPassword(), AppConstants.APP_SETTINGS.getEncryptionKey()));
                Date current = Calendar.getInstance().getTime();
                userVo.setLastChange(current);
                // Insert user if it's have no user id
                if (userVo.getUserId() == null) {
                    userService.insertUser(this.getAddInfoMap(), userVo);
                    log.info("Account -> Users -> Manage Users: Insert user [id=" + userId + "]" + " at " + current);
                } else {
                    // Update user
                    userService.updateUser(this.getAddInfoMap(), userVo);
                    log.info("Account -> Users -> Manage Users: Update user [id=" + userId + "]" + " at " + current);
                }
            }
        } catch (CustomException e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText("System internal error. Please contact the administrator for supporting."));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String loadUserDetail() {
        try {
            // Prepare user level list
            prepareUserLevels();
            UserVo userVo = null;
            // Create new user if user id is null
            if (StringUtils.isBlank(userId)) {
                userVo = new UserVo();
                userVo.setUserLevelId(0);
                userVo.setIsCollector(false);
                userVo.setIsRequireChangePassword(true);
                userVo.setLanguage(0);
                userVo.setTargetPhoneCall(0);
                userVo.setOverdueDay(0);
                userVo.setTargetSuccess(0);
            } else {
                IUserService userService = new UserServiceImp();
                // Get user by user id
                userVo = userService.getUserById(userId);
                userVo.setPassword(CryptUtils.Decrypt(userVo.getPassword(), AppConstants.APP_SETTINGS.getEncryptionKey()));
            }
            // Convert user vo to model
            UserModel userModel = ModelUtils.createModelFromVo(userVo, UserModel.class);
            this.setUser(userModel);
        } catch (CustomException e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText("System internal error. Please contact the administrator for supporting."));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public void delete() {
        try {
            determineAdminLevel();
            if (this.getAdminLevel() != 1) {
                throw new CustomException("You don't have permission to delete this user.");
            }
            if (StringUtils.isBlank(this.getUserId())) {
                throw new CustomException("Please choose an user for deleting.");
            }
            Long uid = Long.valueOf(this.getUserId());
            IUserService userService = new UserServiceImp();
            userService.deleteById(this.getAddInfoMap(), uid);
        } catch (CustomException e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            setErrorCode(ErrorCode.ACTION_ERROR);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(this.getLocalizationText("Cannot delete this user. <br/>Please contact the administrator for supporting."));
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
    }

    private boolean validUser(UserModel userModel) {
        if (userModel.getUserCode().length() != 3 && userModel.getUserCode().length() != 8) {
            addFieldError("user.userCode", this.getLocalizationText("Customer # should be 3 or 8 character"));
        } else {
            Long code = null;
            try {
                code = Long.valueOf(this.getUser().getUserCode());
            } catch (Exception e) {
                code = null;
            }
            if (code == null) {
                addFieldError("user.userCode", this.getLocalizationText("Customer # must be a number."));
            }
        }
        if (StringUtils.isBlank(userModel.getUserName())) {
            addFieldError("user.userName", this.getLocalizationText("Please Enter UserName"));
        }
        if (!PasswordUtils.isValid(userModel.getPassword())) {
            addFieldError("user.password", this.getLocalizationText("Password should contain minimum 8 characters, with at lease one letter and one number."));
        }
        if (StringUtils.isBlank(userModel.getUserLevelId())) {
            addFieldError("user.userLevelId", this.getLocalizationText("Please Select Admin Level"));
        }
        if (StringUtils.isBlank(userModel.getDisplayName())) {
            addFieldError("user.displayName", this.getLocalizationText("Please Enter Display Name"));
        }
        if (StringUtils.isBlank(userModel.getEmail())) {
            addFieldError("user.email", this.getLocalizationText("Please Enter Email"));
        } else if (!EmailUtils.isValidEmail(userModel.getEmail())) {
            addFieldError("user.email", this.getLocalizationText("Invalid Email Address"));
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void loadUsers() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        int pSize = 0;
        try {
            pSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pSize = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize());
        }

        int iPage = 0;
        try {
            iPage = Integer.parseInt(this.page);
        } catch (Exception ex) {
            iPage = 1;
        }

        UserFilter filter = this.buildFilter();
        IUserService userService = new UserServiceImp();
        long recordCount = userService.countManagedUsersByUserId(filter);

        // Set paging info
        Paging<UserModel> paging = new Paging<UserModel>(iPage, nLinks, pSize, recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());

        // Get data of page
        List<UserVo> userVos = userService.getManagedUsersByUserId(filter);
        String key = AppConstants.APP_SETTINGS.getEncryptionKey();
        for (UserVo userVo : userVos) {
            userVo.setPassword(CryptUtils.Decrypt(userVo.getPassword(), key));
        }
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        paging.setRecords(userModels);
        this.setUsers(paging);
    }

    private void prepareUserLevels() throws Exception {
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        IUserService userService = new UserServiceImp();
        List<UserLevelVo> userLevelVos = userService.getUserLevelsByUserId(Long.valueOf(userId));
        List<UserLevelModel> userLevelModels = ModelUtils.createListModelFromVo(userLevelVos, UserLevelModel.class);
        this.setUserLevels(userLevelModels);
    }

    private UserFilter buildFilter() throws Exception {
        UserFilter filter = new UserFilter();
        filter.setUserCode(userCode);
        filter.setUserLevelId(StringUtils.isBlank(userLevelId) ? null : Integer.valueOf(userLevelId));
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        filter.setUserId(Long.valueOf(userId));
        filter.setFranchiseList(this.buildFranchiseCodeList());
        orderField = StringUtils.isBlank(orderField) ? "user_code" : orderField;
        filter.setOrderField(orderField);
        orderType = StringUtils.isBlank(orderType) ? "0" : orderType;
        filter.setOrderType(Integer.valueOf(orderType));
        return filter;
    }

    private String buildFranchiseCodeList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        String codeList = "";
        for (FranchiseInfoVo franchiseInfoVo : franchiseInfoVos) {
            codeList += franchiseInfoVo.getCode() + ",";
        }
        return codeList.substring(0, codeList.length() - 1);
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(String userLevelId) {
        this.userLevelId = userLevelId;
    }

    public Paging<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Paging<UserModel> users) {
        this.users = users;
    }

    public List<UserLevelModel> getUserLevels() {
        return userLevels;
    }

    public void setUserLevels(List<UserLevelModel> userLevels) {
        this.userLevels = userLevels;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}

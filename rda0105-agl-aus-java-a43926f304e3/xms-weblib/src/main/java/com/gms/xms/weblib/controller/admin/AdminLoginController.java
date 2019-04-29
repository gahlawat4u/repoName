package com.gms.xms.weblib.controller.admin;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.persistence.dao.UserLevelDao;
import com.gms.xms.persistence.dao.admin.LoginLogDao;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.UserLevelVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.admin.LoginLogVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.Calendar;

/**
 * Posted from Aug 25, 2016 8:55:59 AM
 * <p>
 * Author dattrinh
 */
public class AdminLoginController extends JsonBaseController {

    private static final long serialVersionUID = 1L;

    private String franchiseCode;
    private String userName;
    private String password;

    public String login() {
        return "success";
    }

    public String logout() {
        this.setSession("SESS_XMS_ADMIN_ID", null);
        this.setSession("SESS_XMS_ADMIN_EMAIL", null);
        this.setSession("SESS_XMS_ADMIN_CODE", null);
        this.setSession("SESS_XMS_ADMIN_NAME", null);
        this.setSession("SESS_XMS_ADMIN_LEVEL_ID", null);
        this.setSession("SESS_XMS_PASSWORD", null);
        this.setSession("SESS_XMS_COLLECTOR", null);
        this.setSession("SESS_XMS_ISREQUIRECHANGE", null);
        return "success";
    }

    public String doLogin() {
        try {
            if (validInput()) {
                IUserService userService = new UserServiceImp();
                UserVo userVo = userService.checkLogin(this.getFranchiseCode(), this.getUserName(), this.getPassword());
                String loginMessage = (userVo == null) ? this.getLocalizationText("Customer code, user name or password is incorrect") : this.getLocalizationText("Success");
                // Insert login log.
                LoginLogDao loginLogDao = new LoginLogDao();
                LoginLogVo loginLogVo = new LoginLogVo();
                loginLogVo.setUserName(this.getUserName());
                loginLogVo.setLoginDate(Calendar.getInstance().getTime());
                loginLogVo.setFranchiseCode(Long.valueOf(this.getFranchiseCode()));
                loginLogVo.setIpAddress(this.getIpAddress());
                loginLogVo.setDescription(loginMessage);
                loginLogDao.insertLog(this.getAddInfoMap(), loginLogVo);
                if (userVo == null) {
                    addActionError(loginMessage);
                    return "error";
                } else {
                    // Set session.
                    this.setSession("SESS_XMS_ADMIN_ID", String.valueOf(userVo.getUserId()));
                    this.setSession("SESS_XMS_ADMIN_EMAIL", userVo.getEmail());
                    this.setSession("SESS_XMS_ADMIN_CODE", String.valueOf(userVo.getUserCode()));
                    this.setSession("SESS_XMS_ADMIN_NAME", userVo.getDisplayName());
                    this.setSession("SESS_XMS_ADMIN_LEVEL_ID", String.valueOf(userVo.getUserLevelId()));
                    this.setSession("SESS_XMS_PASSWORD", userVo.getPassword());
                    this.setSession("SESS_XMS_COLLECTOR", userVo.getIsCollector() ? "1" : "0");
                    this.setSession("SESS_XMS_ISREQUIRECHANGE", userVo.getIsRequireChangePassword() ? "1" : "0");

                    Double adminLevel = null;
                    Integer showCollector = 0;
                    try {
                        UserLevelDao dao = new UserLevelDao();
                        UserLevelVo userLevelVo = dao.selectById(userVo.getUserLevelId());
                        adminLevel = userLevelVo.getUserLevelCode();
                        if (userVo.getIsCollector()) {
                            showCollector = 1;
                        } else {
                            showCollector = 0;
                        }
                    } catch (Exception e) {
                        log.error(e);
                    }
                    this.setSession("SESS_XMS_ADMIN_COLLECTOR", showCollector);
                    this.setSession("SESS_XMS_ADMIN_LEVEL", adminLevel.toString());
                    if (adminLevel == 10) {
                        return "level10";
                    }
                    return "success";
                }
            } else {
                return "error";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
    }

    protected boolean validInput() {
        if (StringUtils.isBlank(this.getFranchiseCode())) {
            addFieldError("franchiseCode", this.getLocalizationText("Please enter customer code."));
        }
        if (StringUtils.isBlank(this.getUserName())) {
            addFieldError("userName", this.getLocalizationText("Please enter user name."));
        }
        if (StringUtils.isBlank(this.getPassword())) {
            addFieldError("password", this.getLocalizationText("Please enter password."));
        }
        return !hasFieldErrors();
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.gms.xms.weblib.controller.receivables;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.receivables.BankFilter;
import com.gms.xms.model.BankModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.UserLevelModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.bank.BankServiceImp;
import com.gms.xms.persistence.service.bank.IBankService;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.BankVo;
import com.gms.xms.txndb.vo.UserLevelVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Posted from BankListController
 * <p>
 * Author HoangPH Nov 4, 2015
 */
public class BankListController extends JsonBaseController {
    private static final long serialVersionUID = -7945740360436049333L;

    // Filter props.
    private String pageSize;
    private String page;
    private String orderType;
    private String orderField;

    private Paging<BankModel> bankList;
    private BankModel bank;
    private String bankId;
    private List<String> listPageSize = this.buildPageSizeList();
    private List<UserLevelModel> userLevels;

    public String show() {
        try {
            // Set default sorting props.
            setPage("1");
            setOrderType("0");
            setOrderField("bankid");
            // Get bank list.
            searchBanks();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            searchBanks();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void searchBanks() throws Exception {
        IBankService service = new BankServiceImp();
        BankFilter filter = this.buildFilter();
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        long recordCount = service.countByFilter(filter);
        Paging<BankModel> paging = new Paging<BankModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        List<BankVo> bankVos = service.selectByFilter(filter);
        List<BankModel> bankModels = ModelUtils.createListModelFromVo(bankVos, BankModel.class);
        paging.setRecords(bankModels);
        this.setBankList(paging);
    }

    public String edit() throws Exception {
        try {
            prepareUserLevels();
            IBankService service = new BankServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (this.getBank() != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }
                    BankVo bankVo = ModelUtils.createVoFromModel(this.getBank(), BankVo.class);
                    service.update(this.getAddInfoMap(), bankVo);
                    searchBanks();
                    return "saved";
                } else {
                    if (!StringUtils.isBlank(this.getBankId())) {
                        BankVo bankVo = service.selectById(Long.parseLong(this.getBankId()));
                        BankModel bankModel = ModelUtils.createModelFromVo(bankVo, BankModel.class);
                        this.setBank(bankModel);
                    } else {
                        throw new CustomException("Please select an bank to edit.");
                    }
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String add() throws Exception {
        try {
            prepareUserLevels();
            IBankService service = new BankServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (this.getBank() != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }
                    BankVo bankVo = ModelUtils.createVoFromModel(this.getBank(), BankVo.class);
                    Date modifiedDate = new Date();
                    bankVo.setModifiedDate(modifiedDate);
                    service.insert(this.getAddInfoMap(), bankVo);
                    searchBanks();
                    return "saved";
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String delete() {
        try {
            IBankService service = new BankServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (!StringUtils.isBlank(this.getBankId())) {
                    service.delete(this.getAddInfoMap(), Long.parseLong(this.getBankId()));
                    searchBanks();
                    return "deleted";
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected BankFilter buildFilter() throws Exception {
        BankFilter filter = new BankFilter();
        Integer userLevelId = Integer.parseInt(this.getSession("SESS_XMS_ADMIN_LEVEL_ID").toString());
        filter.setUserLevelId(userLevelId);
        // Set page.
        Integer page = null;
        try {
            page = StringUtils.isBlank(this.getPage()) ? 1 : Integer.valueOf(this.getPage());
            if (page <= 0) {
                throw new CustomException("The page number cannot be less than 0.");
            }
            filter.setPage(page);
        } catch (Exception e) {
            throw new CustomException("Invalid page number.");
        }
        // Set page size.
        Integer pageSize = null;
        try {
            pageSize = StringUtils.isBlank(this.getPageSize()) ? Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()) : Integer.valueOf(this.getPageSize());
            if (pageSize <= 0) {
                throw new CustomException("The page size cannot be less than 0.");
            }
            filter.setPageSize(pageSize);
        } catch (Exception e) {
            throw new CustomException("Invalid page size number.");
        }
        // Set order type.
        Integer order = null;
        try {
            order = StringUtils.isBlank(this.getOrderType()) ? 0 : Integer.valueOf(this.getOrderType());
            if (order != 0 && order != 1) {
                throw new CustomException("The order type cannot be other value exception (0: ascending, 1: descending)");
            }
            filter.setOrderType(order);
        } catch (Exception e) {
            throw new CustomException("Invalid order type value.");
        }
        // Set order field.
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "bankid" : this.getOrderField());
        return filter;
    }

    protected Boolean validateFields() throws Exception {
        if (this.bank == null) {
            return false;
        } else {
            if (StringUtils.isBlank(this.bank.getBankName())) {
                this.addFieldError("bank.bankName", "Please Enter Bank Name");
            } else {
                IBankService service = new BankServiceImp();
                BankVo bankVo = ModelUtils.createVoFromModel(this.bank, BankVo.class);
                if (service.countByName(bankVo) > 0) {
                    this.addFieldError("bank.bankName", "Input Bank Name already exists.");
                    return false;
                }
            }
        }
        if (this.hasFieldErrors()) {
            return false;
        }
        return true;
    }

    private void prepareUserLevels() throws Exception {
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        IUserService userService = new UserServiceImp();
        List<UserLevelVo> userLevelVos = userService.getUserLevelsByUserId(Long.valueOf(userId));
        List<UserLevelModel> userLevelModels = ModelUtils.createListModelFromVo(userLevelVos, UserLevelModel.class);
        this.setUserLevels(userLevelModels);
    }

    public Paging<BankModel> getBankList() {
        return bankList;
    }

    public void setBankList(Paging<BankModel> bankList) {
        this.bankList = bankList;
    }

    public BankModel getBank() {
        return bank;
    }

    public void setBank(BankModel bank) {
        this.bank = bank;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<UserLevelModel> getUserLevels() {
        return userLevels;
    }

    public void setUserLevels(List<UserLevelModel> userLevels) {
        this.userLevels = userLevels;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }
}

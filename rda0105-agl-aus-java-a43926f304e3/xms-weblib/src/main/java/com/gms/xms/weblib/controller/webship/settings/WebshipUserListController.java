package com.gms.xms.weblib.controller.webship.settings;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.CryptUtils;
import com.gms.xms.common.utils.PasswordUtils;
import com.gms.xms.filter.webship.WebshipFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.WebshipModel;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.persistence.service.webship.IWebshipService;
import com.gms.xms.persistence.service.webship.WebshipServiceImp;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.List;

/**
 * Posted from Jun 27, 2016 11:16:25 AM
 * <p>
 * Author dattrinh
 */
public class WebshipUserListController extends JsonBaseController {

    private static final long serialVersionUID = -1572131199822921952L;

    // Models.
    private Paging<WebshipModel> webships;
    private List<String> pageSizes;
    private WebshipModel webship;

    // Filter props.
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;

    private String webshipId;

    public String show() {
        try {
            // Set default sorting.
            setPage("1");
            setOrderField("customer_code");
            setOrderType("0");
            // Load user list.
            loadWebships();
            preparePageSizes();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            loadWebships();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String load() {
        try {
            // Check login
            WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
            if (webshipLoginVo == null) {
                throw new CustomException("You don't have permission to do this action.");
            }
            // Get customer code.
            String customerCode = String.valueOf(webshipLoginVo.getCustomerCode());
            // New webship user
            if (StringUtils.isBlank(this.getWebshipId())) {
                WebshipModel webshipModel = new WebshipModel();
                webshipModel.setCustomerCode(customerCode);
                webshipModel.setAllowExportAddressBook("false");
                webshipModel.setIsRequireChangePassword("false");
                webshipModel.setLanguage("0");
                this.setWebship(webshipModel);
            } else {
                // Load note info
                IWebshipService webshipService = new WebshipServiceImp();
                WebshipVo webshipVo = webshipService.selectWebshipById(Long.valueOf(this.getWebshipId()));
                webshipVo.setPassword(CryptUtils.Decrypt(webshipVo.getPassword(), AppConstants.APP_SETTINGS.getEncryptionKey()));
                WebshipModel webshipModel = ModelUtils.createModelFromVo(webshipVo, WebshipModel.class);
                this.setWebship(webshipModel);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String edit() {
        try {
            if (!validWebship()) {
                setErrorCode(ErrorCode.FIELD_ERROR);
            } else {
                IWebshipService webshipService = new WebshipServiceImp();
                WebshipVo webshipVo = ModelUtils.createVoFromModel(webship, WebshipVo.class);
                webshipVo.setPassword(CryptUtils.Encrypt(webshipVo.getPassword(), AppConstants.APP_SETTINGS.getEncryptionKey()));
                if (webshipVo.getWebshipId() == null) {
                    if (checkDuplicate(webshipVo)) {
                        throw new CustomException("This alternate user already existed.");
                    }
                    webshipVo.setCreateDate(Calendar.getInstance().getTime());
                    webshipService.insertWebship(this.getAddInfoMap(), webshipVo);
                    log.info("Insert webship user: " + webshipVo);
                } else {
                    webshipService.updateWebship(this.getAddInfoMap(), webshipVo);
                    log.info("Update webship user: " + webshipVo);
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean checkDuplicate(WebshipVo webshipVo) throws Exception {
        WebshipDao webshipDao = new WebshipDao();
        int count = webshipDao.checkUser(webshipVo);
        return count > 0;
    }

    private boolean validWebship() {
        if (webship == null) {
            return false;
        }
        if (StringUtils.isBlank(webship.getCustomerCode())) {
            addFieldError("webship.customerCode", "No customer code");
        }
        if (StringUtils.isBlank(webship.getName())) {
            addFieldError("webship.name", "Alternate User cannot be blank");
        }
        if (StringUtils.isBlank(webship.getPassword())) {
            addFieldError("webship.password", "Password cannot be blank");
        } else if (!PasswordUtils.isValid(webship.getPassword())) {
            addFieldError("webship.password", "Password should contain minimum 8 characters, with at lease one letter and one number.");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void preparePageSizes() {
        this.setPageSizes(this.buildPageSizeList());
    }

    private void loadWebships() throws Exception {
        IWebshipService webshipService = new WebshipServiceImp();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        WebshipFilter filter = this.buildFilter();
        long recordCount = webshipService.countByFilter(filter);
        Paging<WebshipModel> paging = new Paging<WebshipModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        List<WebshipVo> webshipVos = webshipService.selectByFilter(filter);
        String encryptionKey = AppConstants.APP_SETTINGS.getEncryptionKey();
        for (WebshipVo webshipVo : webshipVos) {
            webshipVo.setPassword(CryptUtils.Decrypt(webshipVo.getPassword(), encryptionKey));
        }
        List<WebshipModel> webshipModels = ModelUtils.createListModelFromVo(webshipVos, WebshipModel.class);
        paging.setRecords(webshipModels);
        this.setWebships(paging);
    }

    private WebshipFilter buildFilter() throws Exception {
        WebshipFilter filter = new WebshipFilter();
        // Set customer code.
        if (StringUtils.isBlank(this.getCustomerCode())) {
            throw new CustomException("No customer code");
        }
        filter.setCustomerCode(this.getCustomerCode());
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "customer_code" : this.getOrderField());
        return filter;
    }

    public String getCustomerCode() throws Exception {
        // Check login
        WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
        if (webshipLoginVo == null) {
            return null;
        }
        // Get customer code.
        String customerCode = String.valueOf(webshipLoginVo.getCustomerCode());
        return customerCode;
    }

    public Paging<WebshipModel> getWebships() {
        return webships;
    }

    public void setWebships(Paging<WebshipModel> webships) {
        this.webships = webships;
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

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public String getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(String webshipId) {
        this.webshipId = webshipId;
    }

    public WebshipModel getWebship() {
        return webship;
    }

    public void setWebship(WebshipModel webship) {
        this.webship = webship;
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

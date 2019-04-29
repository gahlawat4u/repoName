package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.account.customers.manage.MarkupFilter;
import com.gms.xms.model.CustomerAccessorialModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.account.customers.manage.MarkupModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.markup.IMarkupService;
import com.gms.xms.persistence.service.markup.MarkupServiceImp;
import com.gms.xms.txndb.vo.CustomerAccessorialVo;
import com.gms.xms.txndb.vo.account.customers.manage.MarkupVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.List;

/**
 * Posted from ManageCustomerMarkupController
 * <p>
 * Author DatTV Oct 6, 2015
 */
public class ManageCustomerMarkupController extends JsonBaseController {

    private static final long serialVersionUID = -6288924618016825183L;

    // Filter props.
    private String customerCode;
    private String description;
    private String code;
    private String typeName;
    private String amount;
    private String serviceName;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;

    // Models.
    private Paging<MarkupModel> markups;
    private List<String> pageSizes;
    private CustomerAccessorialModel markup;

    public String show() {
        try {
            // Set default sorting props.
            this.setPage("1");
            this.setOrderType("0");
            this.setOrderField("description");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            // Set page size list and markup data.
            this.setPageSizes(this.buildPageSizeList());
            loadMarkups();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            loadMarkups();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void edit() {
        try {
            if (validMarkup()) {
                IMarkupService markupService = new MarkupServiceImp();
                CustomerAccessorialVo accessorialVo = ModelUtils.createVoFromModel(markup, CustomerAccessorialVo.class);
                markupService.editMarkup(this.getAddInfoMap(), accessorialVo);
                log.info("Edit customer accessorial [customer_code=" + accessorialVo.getCustomerCode() + ", accessorialid=" + accessorialVo.getAccessorialid() + "] at" + Calendar.getInstance().getTime());
            } else {
                setErrorCode(ErrorCode.FIELD_ERROR);
                setErrorMessage("Invalid customer accessorial");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    private boolean validMarkup() {
        if (markup == null) {
            addActionError("No markup to edit");
            return false;
        }
        if (StringUtils.isBlank(markup.getCustomerCode())) {
            addFieldError("markup.customerCode", "Customer code cannot be blank");
        }
        if (StringUtils.isBlank(markup.getAccessorialid())) {
            addFieldError("markup.accessorialid", "Accessorial id cannot be blank");
        }
        if (StringUtils.isBlank(markup.getAmount())) {
            addFieldError("markup.amount", "Amount cannot be blank");
        } else {
            if (!NumberUtils.isNumber(markup.getAmount())) {
                addFieldError("markup.amount", "Amount must be a number");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void loadMarkups() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        IMarkupService markupService = new MarkupServiceImp();
        MarkupFilter filter = this.buildFilter();
        long recordCount = markupService.countByFilter(filter);
        // Set paging info
        Paging<MarkupModel> paging = new Paging<MarkupModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get data of page
        List<MarkupVo> markupVos = markupService.selectByFilter(filter);
        List<MarkupModel> markupModels = ModelUtils.createListModelFromVo(markupVos, MarkupModel.class);
        paging.setRecords(markupModels);
        this.setMarkups(paging);
    }

    private MarkupFilter buildFilter() throws Exception {
        MarkupFilter filter = new MarkupFilter();
        if (StringUtils.isBlank(this.getCustomerCode())) {
            throw new CustomException("No customer code.");
        }
        filter.setCustomerCode(this.getCustomerCode());
        filter.setDescription(this.getDescription());
        filter.setCode(this.getCode());
        filter.setTypeName(this.getTypeName());
        filter.setAmount(this.getAmount());
        filter.setServiceName(this.getServiceName());
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "description" : this.getOrderField());
        return filter;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Paging<MarkupModel> getMarkups() {
        return markups;
    }

    public void setMarkups(Paging<MarkupModel> markups) {
        this.markups = markups;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public CustomerAccessorialModel getMarkup() {
        return markup;
    }

    public void setMarkup(CustomerAccessorialModel markup) {
        this.markup = markup;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

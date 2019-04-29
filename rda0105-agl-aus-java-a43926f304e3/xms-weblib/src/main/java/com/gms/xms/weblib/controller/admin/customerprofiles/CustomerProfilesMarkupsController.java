package com.gms.xms.weblib.controller.admin.customerprofiles;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.account.customers.manage.MarkupFilter;
import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.account.customers.manage.MarkupModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.markup.IMarkupService;
import com.gms.xms.persistence.service.markup.MarkupServiceImp;
import com.gms.xms.txndb.vo.account.customers.manage.MarkupVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.List;

/**
 * Posted from CustomerProfilesController
 * <p>
 * Author TANDT 21-10-2015
 */
public class CustomerProfilesMarkupsController extends CustomerProfilesAccountSetupController {
    private static final long serialVersionUID = -7110570240797000983L;

    // Filter props.
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private String description;
    private String code;
    private String typeName;
    private String amount;
    private String serviceName;

    // Models.
    protected List<AccessorialModel> accessorials;
    private List<String> pageSizes;
    private Paging<MarkupModel> markups;
    private MarkupFilter filterMarkup;
    private MarkupModel markupModel;
    private String isEditMarkup;

    public String indexMarkup() {
        try {
            // Set default sorting props.
            this.setPage("1");
            this.setOrderType("0");
            this.setOrderField("description");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            // Load page size list and markup data.
            pageSizes = this.buildPageSizeList();
            loadMarkups();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String indexMarkupSearch() {
        try {
            loadMarkups();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String indexMarkupEdit() {
        try {
            if (isEditMarkup != null) {
                if (validMarkupModel()) {
                    IMarkupService service = new MarkupServiceImp();
                    if (StringUtils.isNotEmpty(markupModel.getProfileId())) {
                        service.updateCustomerProfile(this.getAddInfoMap(), ModelUtils.createVoFromModel(markupModel, MarkupVo.class));
                    } else {
                        markupModel.setProfileId(filterMarkup.getProfileId().toString());
                        service.insertCustomerProfileAccessorial(this.getAddInfoMap(), ModelUtils.createVoFromModel(markupModel, MarkupVo.class));
                    }
                    this.setProfileId(filterMarkup.getProfileId().toString());
                    loadMarkups();
                    return "home";
                } else {
                    return "success";
                }
            } else {
                prepareMarkupDetail();
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private boolean validMarkupModel() throws Exception {
        if (markupModel == null) {
            setErrorMessage("Not found markup model for edit.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        if (StringUtils.isEmpty(markupModel.getAmount().trim())) {
            setErrorMessage("Amount is not empty.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        if (!NumberUtils.isNumber(markupModel.getAmount().trim())) {
            setErrorMessage("Amount is not number.");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        return true;
    }

    private void prepareMarkupDetail() throws Exception {
        IMarkupService service = new MarkupServiceImp();
        markupModel = ModelUtils.createModelFromVo(service.selectDetailByIds(filterMarkup), MarkupModel.class);
    }

    private void loadMarkups() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        IMarkupService markupService = new MarkupServiceImp();
        MarkupFilter mfilter = this.buildFilter();
        long recordCount = markupService.selectMarkupForCustomerCount(mfilter);
        // Set paging info
        Paging<MarkupModel> paging = new Paging<MarkupModel>(mfilter.getPage(), nLinks, mfilter.getPageSize(), recordCount);
        // Get data of page
        List<MarkupVo> markupVos = markupService.selectMarkupForCustomer(mfilter);
        List<MarkupModel> markupModels = ModelUtils.createListModelFromVo(markupVos, MarkupModel.class);
        paging.setRecords(markupModels);
        this.setMarkups(paging);
    }

    private MarkupFilter buildFilter() throws Exception {
        MarkupFilter filter = new MarkupFilter();
        if (StringUtils.isBlank(this.getProfileId())) {
            throw new CustomException("No profile id.");
        }
        filter.setProfileId(Long.valueOf(this.getProfileId()));
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

    public List<AccessorialModel> getAccessorials() {
        return accessorials;
    }

    public void setAccessorials(List<AccessorialModel> accessorials) {
        this.accessorials = accessorials;
    }

    public MarkupFilter getFilterMarkup() {
        return filterMarkup;
    }

    public void setFilterMarkup(MarkupFilter filterMarkup) {
        this.filterMarkup = filterMarkup;
    }

    public String getIsEditMarkup() {
        return isEditMarkup;
    }

    public void setIsEditMarkup(String isEditMarkup) {
        this.isEditMarkup = isEditMarkup;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public Paging<MarkupModel> getMarkups() {
        return markups;
    }

    public void setMarkups(Paging<MarkupModel> markups) {
        this.markups = markups;
    }

    public MarkupModel getMarkupModel() {
        return markupModel;
    }

    public void setMarkupModel(MarkupModel markupModel) {
        this.markupModel = markupModel;
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
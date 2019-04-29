package com.gms.xms.weblib.controller.account.franchises.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.account.customers.manage.MarkupFilter;
import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.account.customers.manage.MarkupModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.markup.IMarkupService;
import com.gms.xms.persistence.service.markup.MarkupServiceImp;
import com.gms.xms.txndb.vo.CustomerAccessorialVo;
import com.gms.xms.txndb.vo.account.customers.manage.MarkupVo;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name: ManageFranchiseMarkupsController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 18-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseMarkupsController
 */
public class ManageFranchiseMarkupsController extends ManageFranchiseAccountSetupController {

    private static final long serialVersionUID = -504999319047410379L;

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
    private MarkupModel markupModel;
    private String isEditMarkup;

    private MarkupFilter filterMarkup;

    public String indexMarkup() {
        try {
            // Set default sorting props.
            this.setPage("1");
            this.setOrderType("0");
            this.setOrderField("description");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            // Load franchise info, page size list and markup data.
            prepareFranchiseDetail();
            pageSizes = this.buildPageSizeList();
            loadMarkups();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String indexMarkupSearch() {
        try {
            pageSizes = this.buildPageSizeList();
            loadMarkups();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    /**
     * Functions: indexMarkupEdit <br/>
     * Date Time Create: 18-11-2015 - 14:06:00 <br/>
     * Descriptions: Function indexMarkupEdit ....... <br/>
     *
     * @return
     */
    public String indexMarkupEdit() {
        try {
            if (isEditMarkup != null) {
                validMarkupModel();
                MarkupVo markupVo = ModelUtils.createVoFromModel(this.getMarkupModel(), MarkupVo.class);
                CustomerAccessorialVo accessorialVo = new CustomerAccessorialVo();
                accessorialVo.setAccessorialid(markupVo.getAccessorialId());
                accessorialVo.setAmount(new BigDecimal(markupVo.getAmount()));
                accessorialVo.setCustomerCode(Long.valueOf(this.getFranchiseCode()));
                IMarkupService service = new MarkupServiceImp();
                service.editMarkup(this.getAddInfoMap(), accessorialVo);
                log.info("Edit customer accessorial: " + accessorialVo);
                loadMarkups();
                return "home";
            } else {
                prepareMarkupDetail();
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    /**
     * Functions: validMarkupModel <br/>
     * Date Time Create: 18-11-2015 - 14:06:07 <br/>
     * Descriptions: Function validMarkupModel ....... <br/>
     *
     * @return boolean
     * @throws Exception
     */
    protected void validMarkupModel() throws Exception {
        if (markupModel == null) {
            throw new CustomException("Not found markup model for edit.");
        } else {
            if (StringUtils.isEmpty(markupModel.getAmount().trim())) {
                throw new CustomException("Amount is empty.");
            } else {
                Double amount = null;
                try {
                    amount = Double.valueOf(this.getMarkupModel().getAmount());
                } catch (Exception e) {
                    amount = null;
                }
                if (amount == null) {
                    throw new CustomException("Amount is not a number.");
                }
            }
        }
    }

    /**
     * Functions: prepareMarkupDetail <br/>
     * Date Time Create: 18-11-2015 - 14:06:27 <br/>
     * Descriptions: Function prepareMarkupDetail ....... <br/>
     *
     * @throws Exception
     */
    private void prepareMarkupDetail() throws Exception {
        IMarkupService service = new MarkupServiceImp();
        markupModel = ModelUtils.createModelFromVo(service.selectDetailByIds(filterMarkup), MarkupModel.class);
    }

    /**
     * Functions: loadMarkups <br/>
     * Date Time Create: 18-11-2015 - 14:06:32 <br/>
     * Descriptions: Function loadMarkups ....... <br/>
     *
     * @throws Exception
     */
    private void loadMarkups() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        IMarkupService markupService = new MarkupServiceImp();
        MarkupFilter filter = this.buildFilter();
        long recordCount = markupService.selectMarkupForCustomerCount(filter);
        // Set paging info
        Paging<MarkupModel> paging = new Paging<MarkupModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        // Get data of page
        List<MarkupVo> markupVos = new ArrayList<MarkupVo>();
        markupVos = markupService.selectByFilter(filter);
        List<MarkupModel> markupModels = ModelUtils.createListModelFromVo(markupVos, MarkupModel.class);
        paging.setRecords(markupModels);
        this.setMarkups(paging);
    }

    /**
     * Functions: buildFilter <br/>
     * Date Time Create: 18-11-2015 - 14:08:01 <br/>
     * Descriptions: Function buildFilter ....... <br/>
     *
     * @return MarkupFilter
     * @throws Exception
     */

    private MarkupFilter buildFilter() throws Exception {
        MarkupFilter filter = new MarkupFilter();
        if (StringUtils.isBlank(this.getFranchiseCode())) {
            throw new CustomException("No franchise code.");
        }
        filter.setCustomerCode(this.getFranchiseCode());
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
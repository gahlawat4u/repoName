package com.gms.xms.weblib.controller.admin.customergroup;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.CustomerGroupFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.customergroup.CustomerGroupModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customergroup.CustomerGroupServiceImp;
import com.gms.xms.persistence.service.customergroup.ICustomerGroupService;
import com.gms.xms.txndb.vo.customergroup.CustomerGroupVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from CustomerGroupListController
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class CustomerGroupListController extends JsonBaseController {
    private static final long serialVersionUID = -7945740360436049333L;

    private CustomerGroupFilter customerGroupFilter;

    private Paging<CustomerGroupModel> customerGroupList;

    private CustomerGroupModel customerGroupModel;
    private String isEdit;
    private String customerGroupId;
    private String pageSize;
    private String page;
    private String orderType;
    private String orderField;
    private List<String> listPageSize = this.buildPageSizeList();

    public String show() {
        try {
            // Set default sort
            setPage("1");
            setOrderType("0");
            setOrderField("customer_group_id");

            loadCustomerGroup();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            loadCustomerGroup();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void loadCustomerGroup() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        ICustomerGroupService customerGroupService = new CustomerGroupServiceImp();
        CustomerGroupFilter filter = this.buildFilter();
        long recordCount = customerGroupService.countByFilter(filter);
        // Set paging info
        Paging<CustomerGroupModel> paging = new Paging<CustomerGroupModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get data of page
        List<CustomerGroupVo> customerGroupVos = customerGroupService.selectByFilter(filter);
        List<CustomerGroupModel> customerGroupModels = ModelUtils.createListModelFromVo(customerGroupVos, CustomerGroupModel.class);
        paging.setRecords(customerGroupModels);
        this.setCustomerGroupList(paging);
    }

    public String add() {
        try {
            if (customerGroupModel != null) {
                if (validCustomerGroup()) {
                    ICustomerGroupService CustomerGroupService = new CustomerGroupServiceImp();
                    CustomerGroupVo CustomerGroupVo = ModelUtils.createVoFromModel(customerGroupModel, CustomerGroupVo.class);
                    System.out.println("Number of banks is " + CustomerGroupVo);
                    CustomerGroupService.insert(this.getAddInfoMap(), CustomerGroupVo);
                    loadCustomerGroup();
                    return "home";

                } else {
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return "success";
                }
            } else {
                return "success";
            }

        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String edit() {
        try {
            if (isEdit != null) {
                if (validCustomerGroup()) {
                    ICustomerGroupService customerGroupService = new CustomerGroupServiceImp();
                    CustomerGroupVo CustomerGroupVo = ModelUtils.createVoFromModel(customerGroupModel, CustomerGroupVo.class);
                    customerGroupService.update(this.getAddInfoMap(), CustomerGroupVo);
                    loadCustomerGroup();
                    return "home";
                } else {
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return "success";
                }
            } else {
                return "success";
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String delete() {
        try {
            if (customerGroupModel.getCustomerGroupId() != null) {
                ICustomerGroupService service = new CustomerGroupServiceImp();
                service.delete(this.getAddInfoMap(), Integer.parseInt(customerGroupModel.getCustomerGroupId()));
                loadCustomerGroup();
                return "home";
            } else {
                return "success";
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    private CustomerGroupFilter buildFilter() throws CustomException {
        CustomerGroupFilter filter = new CustomerGroupFilter();
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "customer_group_id" : this.getOrderField());
        return filter;
    }

    private boolean validCustomerGroup() throws Exception {
        if (customerGroupModel == null) {
            return false;
        }
        if (StringUtils.isBlank(customerGroupModel.getCustomerGroupName())) {
            addFieldError("customerGroupModel.customerGroupName", "Please Enter Customer Group Name");
        } else {
            ICustomerGroupService service = new CustomerGroupServiceImp();
            CustomerGroupVo customerGroupVo = ModelUtils.createVoFromModel(this.customerGroupModel, CustomerGroupVo.class);
            if (service.countByName(customerGroupVo) > 0) {
                this.addFieldError("customerGroupModel.customerGroupName", "Input Customer Group Name already exists.");
                return false;
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public CustomerGroupModel getCustomerGroupModel() {
        return customerGroupModel;
    }

    public void setCustomerGroupModel(CustomerGroupModel customerGroupModel) {
        this.customerGroupModel = customerGroupModel;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(String customerGroupId) {
        this.customerGroupId = customerGroupId;
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

    public Paging<CustomerGroupModel> getCustomerGroupList() {
        return customerGroupList;
    }

    public void setCustomerGroupList(Paging<CustomerGroupModel> customerGroupList) {
        this.customerGroupList = customerGroupList;
    }

    public CustomerGroupFilter getCustomerGroupFilter() {
        return customerGroupFilter;
    }

    public void setCustomerGroupFilter(CustomerGroupFilter customerGroupFilter) {
        this.customerGroupFilter = customerGroupFilter;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
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

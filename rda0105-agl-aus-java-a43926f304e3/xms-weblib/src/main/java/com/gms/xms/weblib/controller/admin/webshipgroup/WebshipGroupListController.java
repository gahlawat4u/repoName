package com.gms.xms.weblib.controller.admin.webshipgroup;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.WebshipGroupFilter;
import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.WebshipGroupModel;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.webshipgroup.IWebshipGroupService;
import com.gms.xms.persistence.service.webshipgroup.WebshipGroupServiceImp;
import com.gms.xms.txndb.vo.CustomerFilter;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from WebshipGroupListController
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class WebshipGroupListController extends JsonBaseController {
    private static final long serialVersionUID = -7945740360436049333L;

    private Paging<WebshipGroupModel> webshipGroupList;
    private WebshipGroupModel webshipGroup;
    private String webshipGroupId;

    private List<String> listPageSize = this.buildPageSizeList();
    private String pageSize;
    private String page;
    private List<CustomerModel> customerOwerList;

    public String show() {
        try {
            IWebshipGroupService service = new WebshipGroupServiceImp();
            List<WebshipGroupVo> webshipGroupVos = service.selectByFilter(this.buildFilter());
            List<WebshipGroupModel> webshipGroupModels = ModelUtils.createListModelFromVo(webshipGroupVos, WebshipGroupModel.class);
            this.webshipGroupList.setRecords(webshipGroupModels);
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String edit() {
        try {
            this.buildCustomerOwerList();
            IWebshipGroupService service = new WebshipGroupServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (this.webshipGroup != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        WebshipGroupModel webshipGroupModel = this.webshipGroup;
                        this.webshipGroup = webshipGroupModel;
                        return "success";
                    }
                    WebshipGroupVo webshipGroupVo = ModelUtils.createVoFromModel(this.webshipGroup, WebshipGroupVo.class);

                    service.update(this.getAddInfoMap(), webshipGroupVo);
                    List<WebshipGroupModel> surchargeListModels = this.getWebshipGroupListModel();
                    this.webshipGroupList.setRecords(surchargeListModels);
                    return "saved";
                } else {
                    if (!StringUtils.isBlank(this.webshipGroupId)) {
                        WebshipGroupVo webshipGroupVo = service.selectById(Integer.parseInt(this.webshipGroupId));
                        WebshipGroupModel webshipGroupModel = ModelUtils.createModelFromVo(webshipGroupVo, WebshipGroupModel.class);
                        this.webshipGroup = webshipGroupModel;
                    } else {
                        this.setErrorCode(ErrorCode.ERROR);
                        this.setErrorMessage("Please select an webship group to edit.");
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
        }
        return "success";
    }

    protected void buildCustomerOwerList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        List<String> franchiseCodeList = new ArrayList<String>();
        for (FranchiseInfoModel franchise : franchiseInfoModels) {
            franchiseCodeList.add(franchise.getCode());
        }
        CustomerFilter filter = new CustomerFilter();
        filter.setFranchiseCodeList(franchiseCodeList);
        ICustomerService customerService = new CustomerServiceImp();
        List<CustomerVo> customerVos = customerService.selectOwnerByFranchises(filter);
        List<CustomerModel> customerModels = ModelUtils.createListModelFromVo(customerVos, CustomerModel.class);
        for (CustomerModel model : customerModels) {
            String owner = model.getCustomerCode();
            if (!StringUtils.isBlank(model.getCustomerAddress().getOwner())) {
                owner += " - " + model.getCustomerAddress().getOwner();
            }
            model.setOwner(owner);
        }
        this.setCustomerOwerList(customerModels);
    }

    public String add() {
        try {
            IWebshipGroupService service = new WebshipGroupServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (webshipGroup != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        this.buildCustomerOwerList();
                        return "success";
                    }
                    WebshipGroupVo webshipGroupVo = ModelUtils.createVoFromModel(this.webshipGroup, WebshipGroupVo.class);
                    service.insert(this.getAddInfoMap(), webshipGroupVo);
                    List<WebshipGroupModel> webshipGroupListModels = this.getWebshipGroupListModel();
                    this.webshipGroupList.setRecords(webshipGroupListModels);
                    return "saved";
                } else {
                    this.buildCustomerOwerList();
                }
            }
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
        }
        return "success";
    }

    public String delete() {
        try {
            IWebshipGroupService service = new WebshipGroupServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (!StringUtils.isBlank(this.webshipGroupId)) {
                    service.delete(this.getAddInfoMap(), Integer.parseInt(this.webshipGroupId));
                    List<WebshipGroupModel> webshipGroupListModels = this.getWebshipGroupListModel();
                    this.webshipGroupList.setRecords(webshipGroupListModels);
                    return "deleted";
                }
            }
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
        }
        return "success";
    }

    protected List<WebshipGroupModel> getWebshipGroupListModel() throws Exception {
        IWebshipGroupService service = new WebshipGroupServiceImp();
        List<WebshipGroupVo> webshipGroupListVos = service.selectByFilter(this.buildFilter());
        List<WebshipGroupModel> surchargeListModels = ModelUtils.createListModelFromVo(webshipGroupListVos, WebshipGroupModel.class);
        return surchargeListModels;
    }

    protected WebshipGroupFilter buildFilter() throws Exception {
        IWebshipGroupService service = new WebshipGroupServiceImp();
        WebshipGroupFilter filter = new WebshipGroupFilter();

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
        long recordCount = 0;
        try {
            recordCount = service.countByFilter(filter);
        } catch (Exception ex) {
            recordCount = 0;
        }

        Paging<WebshipGroupModel> paging = new Paging<WebshipGroupModel>(page, 10, pageSize, recordCount);

        this.setWebshipGroupList(paging);
        filter.setPageSize(paging.getPageSize());
        filter.setPage(paging.getCurrentPage());
        return filter;
    }

    protected Boolean validateFields() throws Exception {
        if (this.webshipGroup == null) {
            return false;
        } else {
            if (StringUtils.isBlank(this.webshipGroup.getWebshipGroupName())) {
                this.addFieldError("webshipGroup.webshipGroupName", "Please Enter Webship Group Name");
            } else {
                IWebshipGroupService service = new WebshipGroupServiceImp();
                WebshipGroupVo webshipGroupVo = ModelUtils.createVoFromModel(this.webshipGroup, WebshipGroupVo.class);
                if (service.countByName(webshipGroupVo) > 0) {
                    this.addFieldError("webshipGroup.webshipGroupName", "Input Webship Group Name already exists.");
                    return false;
                }

            }
        }
        if (this.hasFieldErrors()) {
            return false;
        }
        return true;
    }

    public Paging<WebshipGroupModel> getWebshipGroupList() {
        return webshipGroupList;
    }

    public void setWebshipGroupList(Paging<WebshipGroupModel> webshipGroupList) {
        this.webshipGroupList = webshipGroupList;
    }

    public WebshipGroupModel getWebshipGroup() {
        return webshipGroup;
    }

    public void setWebshipGroup(WebshipGroupModel webshipGroup) {
        this.webshipGroup = webshipGroup;
    }

    public String getWebshipGroupId() {
        return webshipGroupId;
    }

    public void setWebshipGroupId(String webshipGroupId) {
        this.webshipGroupId = webshipGroupId;
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

    public List<CustomerModel> getCustomerOwerList() {
        return customerOwerList;
    }

    public void setCustomerOwerList(List<CustomerModel> customerOwerList) {
        this.customerOwerList = customerOwerList;
    }

    @Override
    public String toString() {
        return "WebshipGroupListController [webshipGroupList=" + webshipGroupList + ", webshipGroup=" + webshipGroup + ", webshipGroupId=" + webshipGroupId + ", listPageSize=" + listPageSize + ", pageSize=" + pageSize + ", page=" + page + ", customerOwerList=" + customerOwerList + "]";
    }
}

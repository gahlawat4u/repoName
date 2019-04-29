package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.UserModel;
import com.gms.xms.model.account.customers.manage.CustomerAccountSetupModel;
import com.gms.xms.model.area.AreaModel;
import com.gms.xms.model.customergroup.CustomerGroupModel;
import com.gms.xms.model.industry.IndustryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.WebshipGroupModel;
import com.gms.xms.persistence.service.area.AreaServiceImp;
import com.gms.xms.persistence.service.area.IAreaService;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.customergroup.CustomerGroupServiceImp;
import com.gms.xms.persistence.service.customergroup.ICustomerGroupService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.industry.IIndustryService;
import com.gms.xms.persistence.service.industry.IndustryServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.persistence.service.webshipgroup.IWebshipGroupService;
import com.gms.xms.persistence.service.webshipgroup.WebshipGroupServiceImp;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.account.customers.manage.CustomerAccountSetupVo;
import com.gms.xms.txndb.vo.area.AreaVo;
import com.gms.xms.txndb.vo.customergroup.CustomerGroupVo;
import com.gms.xms.txndb.vo.industry.IndustryVo;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from CustomerAccountSetupController
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class CustomerAccountSetupController extends JsonBaseController {

    private static final long serialVersionUID = 3432861315297588425L;

    private String customerCode;
    private CustomerAccountSetupModel customer;
    private List<ServiceModel> services;
    private List<CustomerGroupModel> customerGroups;
    private List<WebshipGroupModel> webshipGroups;
    private List<IndustryModel> industries;
    private List<AreaModel> areas;
    private List<UserModel> salesReps;
    private List<UserModel> collectors;

    public String show() {
        try {
            prepareData();
            loadAccountSetup();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String save() {
        // Update customer account setup
        try {
            if (isValidAccountSetup()) {
                CustomerAccountSetupVo accountSetupVo = ModelUtils.createVoFromModel(customer, CustomerAccountSetupVo.class);
                CustomerVo customerVo = accountSetupVo;
                ICustomerService customerService = new CustomerServiceImp();
                customerService.updateCustomer(this.getAddInfoMap(), customerVo);
                addActionMessage("Saved successfully");
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        // Load new data
        try {
            prepareData();
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    private boolean isValidAccountSetup() {
        if (customer == null) {
            addActionError("Invalid customer account setup information");
            return false;
        }
        if (StringUtils.isBlank(customer.getGstId())) {
            addFieldError("customer.gstId", "GST # is required (default=0)");
        } else {
            Integer gstNumber = 0;
            try {
                gstNumber = Integer.valueOf(customer.getGstId());
                if (gstNumber < 0) {
                    addFieldError("customer.gstId", "GST # must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("customer.gstId", "GST # must be a integer number");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void loadAccountSetup() throws Exception {
        if (StringUtils.isBlank(customerCode)) {
            throw new Exception("No customer code");
        } else {
            ICustomerService customerService = new CustomerServiceImp();
            CustomerAccountSetupVo customerVo = customerService.getCustomerAccountSetupInfo(customerCode);
            CustomerAccountSetupModel customerModel = ModelUtils.createModelFromVo(customerVo, CustomerAccountSetupModel.class);
            this.setCustomer(customerModel);
        }
    }

    private void prepareData() throws Exception {
        prepareServiceList();
        prepareCustomerGroupList();
        prepareWebshipGroupList();
        prepareIndustryList();
        prepareAreaList();
        prepareSalesRepList();
        prepareCollectorList();
    }

    private void prepareServiceList() throws Exception {
        IServiceService iServiceService = new ServiceServiceImp();
        List<ServiceVo> serviceVos = iServiceService.selectAll();
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.setServices(serviceModels);
    }

    private void prepareCustomerGroupList() throws Exception {
        ICustomerGroupService iCustomerGroupService = new CustomerGroupServiceImp();
        List<CustomerGroupVo> customerGroupVos = iCustomerGroupService.selectAll();
        List<CustomerGroupModel> customerGroupModels = ModelUtils.createListModelFromVo(customerGroupVos, CustomerGroupModel.class);
        this.setCustomerGroups(customerGroupModels);
    }

    private void prepareWebshipGroupList() throws Exception {
        IWebshipGroupService iWebshipGroupService = new WebshipGroupServiceImp();
        List<WebshipGroupVo> webshipGroupVos = iWebshipGroupService.selectAll();
        List<WebshipGroupModel> webshipGroupModels = ModelUtils.createListModelFromVo(webshipGroupVos, WebshipGroupModel.class);
        this.setWebshipGroups(webshipGroupModels);
    }

    private void prepareIndustryList() throws Exception {
        IIndustryService iIndustryService = new IndustryServiceImp();
        List<IndustryVo> industryVos = iIndustryService.selectAll();
        List<IndustryModel> industryModels = ModelUtils.createListModelFromVo(industryVos, IndustryModel.class);
        this.setIndustries(industryModels);
    }

    private void prepareAreaList() throws Exception {
        IAreaService iAreaService = new AreaServiceImp();
        List<AreaVo> areaVos = iAreaService.selectAll();
        List<AreaModel> areaModels = ModelUtils.createListModelFromVo(areaVos, AreaModel.class);
        this.setAreas(areaModels);
    }

    private void prepareSalesRepList() throws Exception {
        IUserService iUserService = new UserServiceImp();
        List<UserVo> userVos = iUserService.getSaleReps(this.buildFranchiseCodeList());
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setSalesReps(userModels);
    }

    private void prepareCollectorList() throws Exception {
        IUserService iUserService = new UserServiceImp();
        List<UserVo> userVos = iUserService.getCollectors();
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setCollectors(userModels);
    }

    private List<String> buildFranchiseCodeList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<String> franchiseCodeList = new ArrayList<String>();
        for (FranchiseInfoVo franchise : franchiseInfoVos) {
            franchiseCodeList.add(franchise.getCode());
        }

        return franchiseCodeList;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public CustomerAccountSetupModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerAccountSetupModel customer) {
        this.customer = customer;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

    public List<CustomerGroupModel> getCustomerGroups() {
        return customerGroups;
    }

    public void setCustomerGroups(List<CustomerGroupModel> customerGroups) {
        this.customerGroups = customerGroups;
    }

    public List<WebshipGroupModel> getWebshipGroups() {
        return webshipGroups;
    }

    public void setWebshipGroups(List<WebshipGroupModel> webshipGroups) {
        this.webshipGroups = webshipGroups;
    }

    public List<IndustryModel> getIndustries() {
        return industries;
    }

    public void setIndustries(List<IndustryModel> industries) {
        this.industries = industries;
    }

    public List<AreaModel> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaModel> areas) {
        this.areas = areas;
    }

    public List<UserModel> getSalesReps() {
        return salesReps;
    }

    public void setSalesReps(List<UserModel> salesReps) {
        this.salesReps = salesReps;
    }

    public List<UserModel> getCollectors() {
        return collectors;
    }

    public void setCollectors(List<UserModel> collectors) {
        this.collectors = collectors;
    }
}

package com.gms.xms.weblib.controller.admin.customerprofiles;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.UserModel;
import com.gms.xms.model.area.AreaModel;
import com.gms.xms.model.customer.CustomerProfileModel;
import com.gms.xms.model.customergroup.CustomerGroupModel;
import com.gms.xms.model.industry.IndustryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.area.AreaServiceImp;
import com.gms.xms.persistence.service.area.IAreaService;
import com.gms.xms.persistence.service.customergroup.CustomerGroupServiceImp;
import com.gms.xms.persistence.service.customergroup.ICustomerGroupService;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.persistence.service.industry.IIndustryService;
import com.gms.xms.persistence.service.industry.IndustryServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from CustomerProfilesAccountSetupController
 * <p>
 * Author TANDT 22-10-2015
 */
public class CustomerProfilesAccountSetupController extends CustomerProfilesController {
    private static final long serialVersionUID = -7110570240797000983L;
    protected CustomerProfileModel cusProfile;
    protected List<ServiceModel> services;
    protected List<CustomerGroupModel> customerGroup;
    protected List<IndustryModel> industries;
    protected List<AreaModel> areas;
    protected List<UserModel> saleReps;
    protected List<UserModel> collectors;
    private String profileNameOld;
    protected String profileId;

    public String accountSetupIndex() {
        try {
            if (preapareCustomerDetail()) {
                prepareListService();
                prepareCustomerGroup();
                prepareIndustries();
                prepareAreas();
                prepareSaleRep();
                prepareCollectorList();
                return "success";
            } else {
                return "error";
            }

        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    protected boolean preapareCustomerDetail() throws Exception {
        if (StringUtils.isNotEmpty(profileId)) {
            if (profileId.equals("0")) {
                CustomerProfileModel cusProfileN = new CustomerProfileModel();
                cusProfileN.setRegistrationId("0");
                cusProfileN.setDhlAccount("3p");
                cusProfileN.setTntAccount("3p");
                cusProfileN.setTollPriorityAccount("3p");
                cusProfileN.setWebshipGroupId("0");
                cusProfileN.setInvoiceTerms("3");
                cusProfileN.setGstId("0");
                cusProfileN.setEmailInvoice("1");
                cusProfileN.setInvoiceLateFee("0");
                cusProfileN.setInvoiceSorting("0.00");
                this.setCusProfile(cusProfileN);
            } else {
                ICustomerProfileService service = new CustomerProfileServiceImp();
                this.setCusProfile(ModelUtils.createModelFromVo(service.selectByProfileId(Long.parseLong(profileId)), CustomerProfileModel.class));
            }

        } else {
            setErrorMessage("Please enter customer profiles name");
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("Please enter customer profiles name");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public String save() {
        // Update customer invoice options
        try {
            if (validCustomerProfileAccountSetup()) {
                CustomerProfileVo customerProfileVoN = ModelUtils.createVoFromModel(cusProfile, CustomerProfileVo.class);
                ICustomerProfileService service = new CustomerProfileServiceImp();
                if (customerProfileVoN.getProfileId() == null) {
                    service.insertCustomerProfiles(this.getAddInfoMap(), customerProfileVoN);
                    this.prepareListCustomerProfilesCreate();
                    profileId = customerProfileVoN.getProfileId().toString();
                    return "createSuccess";
                } else {
                    service.updateCustomerProfiles(this.getAddInfoMap(), customerProfileVoN);
                }

                addActionMessage("Saved successfully");
            } else {
                setErrorCode(ErrorCode.FIELD_ERROR);
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    private void prepareListCustomerProfilesCreate() throws Exception {
        ICustomerProfileService service = new CustomerProfileServiceImp();
        if (StringUtils.isNotEmpty(cusProfile.getFranchiseCode())) {
            List<CustomerProfileModel> customersN = ModelUtils.createListModelFromVo(service.selectByFranchiseCode(cusProfile.getFranchiseCode()), CustomerProfileModel.class);
            buildListCustomers(customersN);
        } else {
            setErrorMessage("Please choice a Franchise Code");
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("Please choice a Franchise Code");
        }
    }

    protected void prepareCustomerGroup() throws Exception {
        ICustomerGroupService service = new CustomerGroupServiceImp();
        this.setCustomerGroup(ModelUtils.createListModelFromVo(service.selectAll(), CustomerGroupModel.class));
    }

    private boolean validCustomerProfileAccountSetup() throws Exception {

        if (StringUtils.isEmpty(cusProfile.getProfileName())) {
            addFieldError("cusProfile.profileName", "Name profile is empty.");
        } else {
            CustomerProfileFilter filter = new CustomerProfileFilter();
            ICustomerProfileService service = new CustomerProfileServiceImp();
            filter.setFranchiseCode(cusProfile.getFranchiseCode());
            filter.setProfileName(cusProfile.getProfileName());
            if (cusProfile.getProfileId() == "0" || cusProfile.getProfileId() == null) {
                if (service.checkProfileName(filter) > 0) {
                    addFieldError("cusProfile.profileName", "Customer Profile Name Exist In system!");
                }
            } else {
                if (service.checkProfileName(filter) > 0 && !cusProfile.getProfileName().equals(profileNameOld)) {
                    addFieldError("cusProfile.profileName", "Customer Profile Name Exist In system!");
                }
            }
        }
        if (StringUtils.isEmpty(cusProfile.getGstId())) {
            addFieldError("cusProfile.gstId", "GST is empty.");
        } else {
            if (!StringUtils.isNumeric(cusProfile.getGstId())) {
                addFieldError("cusProfile.gstId", "GST is not numberic.");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void prepareListService() throws Exception {
        IServiceService service = new ServiceServiceImp();
        this.setServices(ModelUtils.createListModelFromVo(service.selectAll(), ServiceModel.class));
    }

    protected void prepareIndustries() throws Exception {
        IIndustryService service = new IndustryServiceImp();
        this.setIndustries(ModelUtils.createListModelFromVo(service.selectAll(), IndustryModel.class));
    }

    protected void prepareAreas() throws Exception {
        IAreaService service = new AreaServiceImp();
        this.setAreas(ModelUtils.createListModelFromVo(service.selectAll(), AreaModel.class));
    }

    protected void prepareSaleRep() throws Exception {
        IUserService userService = new UserServiceImp();
        List<UserVo> userVos = userService.getSaleReps(this.buildFranchiseCodeList(franchiseCode));
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setSaleReps(userModels);
    }

    protected void prepareCollectorList() throws Exception {
        IUserService iUserService = new UserServiceImp();
        List<UserVo> userVos = iUserService.getCollectors();
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setCollectors(userModels);
    }

    protected List<String> buildFranchiseCodeList(String franCode) throws Exception {
        List<String> franchiseCodeList = new ArrayList<String>();
        if (StringUtils.isBlank(franCode) || "All".equalsIgnoreCase(franCode)) {
            if (this.getFranchises() == null) {
                this.prepareListFranchise();
            }
            for (FranchiseInfoModel franchise : franchises) {
                franchiseCodeList.add(franchise.getCode());
            }
        } else {
            franchiseCodeList.add(franCode);
        }

        return franchiseCodeList;
    }

    @Override
    public CustomerProfileModel getCusProfile() {
        return cusProfile;
    }

    @Override
    public void setCusProfile(CustomerProfileModel cusProfile) {
        this.cusProfile = cusProfile;
    }

    @Override
    public String getProfileId() {
        return profileId;
    }

    @Override
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

    public List<CustomerGroupModel> getCustomerGroup() {
        return customerGroup;
    }

    public List<IndustryModel> getIndustries() {
        return industries;
    }

    public void setIndustries(List<IndustryModel> industries) {
        this.industries = industries;
    }

    public void setCustomerGroup(List<CustomerGroupModel> customerGroup) {
        this.customerGroup = customerGroup;
    }

    public List<AreaModel> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaModel> areas) {
        this.areas = areas;
    }

    public List<UserModel> getSaleReps() {
        return saleReps;
    }

    public void setSaleReps(List<UserModel> saleReps) {
        this.saleReps = saleReps;
    }

    public List<UserModel> getCollectors() {
        return collectors;
    }

    public void setCollectors(List<UserModel> collectors) {
        this.collectors = collectors;
    }

    @Override
    public String getProfileNameOld() {
        return profileNameOld;
    }

    @Override
    public void setProfileNameOld(String profileNameOld) {
        this.profileNameOld = profileNameOld;
    }

}
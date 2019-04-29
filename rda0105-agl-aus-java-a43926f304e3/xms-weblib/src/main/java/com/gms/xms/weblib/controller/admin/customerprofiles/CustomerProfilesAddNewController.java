package com.gms.xms.weblib.controller.admin.customerprofiles;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.model.*;
import com.gms.xms.model.admin.customerprofile.AddNewCutomerProfileModel;
import com.gms.xms.model.area.AreaModel;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.customer.CustomerProfileModel;
import com.gms.xms.model.customergroup.CustomerGroupModel;
import com.gms.xms.model.industry.IndustryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.customers.CustomerCollectionDao;
import com.gms.xms.persistence.service.area.AreaServiceImp;
import com.gms.xms.persistence.service.area.IAreaService;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.customergroup.CustomerGroupServiceImp;
import com.gms.xms.persistence.service.customergroup.ICustomerGroupService;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.persistence.service.industry.IIndustryService;
import com.gms.xms.persistence.service.industry.IndustryServiceImp;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.customerprofile.manage.AddNewCutomerProfileVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import com.gms.xms.weblib.controller.admin.customerprofiles.baserate.ManageCustProfileBaseRateController;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from CustomerProfilesAddNewController
 * <p>
 * Author @author HungNT Apr 15, 2016
 */
public class CustomerProfilesAddNewController extends ManageCustProfileBaseRateController {
    private static final long serialVersionUID = -4506831338681191282L;
    private String profileId;
    private String franchiseCode;
    private List<CustomerProfileModel> profileList;

    private CustomerProfileModel cusProfile;

    // Invoice options
    private List<InvoiceSortingOption> invoiceSortingOptions;
    private List<InvoiceTermModel> invoiceTerms;
    private List<CustomerModel> invoiceToCustomers;
    private List<PickupFee> pickupFees;

    // Dropdown list
    private List<ServiceModel> services;
    private List<CustomerGroupModel> customerGroup;
    private List<IndustryModel> industries;
    private List<AreaModel> areas;
    private List<UserModel> saleReps;
    private List<UserModel> collectors;

    // Collection
    private CustomerCollectionModel collectionModel;
    private AddNewCutomerProfileModel addNewCutomerProfile;

    public String loadAddNew() {
        try {
            if (!StringUtils.isBlank(this.getProfileId()) && !StringUtils.isBlank(this.getFranchiseCode())) {
                this.prepareDatas();
            } else {
                this.prepareProfiles();
                return "dialog";
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doAdd() {
        try {
            // Valid saving data.
            AddNewCutomerProfileModel addNewCutomerProfileModel = this.getAddNewCutomerProfile();
            if (addNewCutomerProfileModel == null) {
                throw new CustomException("No data for saving.");
            }
            boolean isValid = true;
            if (addNewCutomerProfileModel.getCustomerProfile() != null) {
                isValid = isValid && isValidAccountSetup(addNewCutomerProfileModel.getCustomerProfile());
            }
            if (addNewCutomerProfileModel.getCollection() != null) {
                isValid = isValid && isValidCustomerCollection(addNewCutomerProfileModel.getCollection());
            }
            if (isValid) {
                AddNewCutomerProfileVo addNewCutomerProfileVo = ModelUtils.createVoFromModel(addNewCutomerProfileModel, AddNewCutomerProfileVo.class);
                ICustomerProfileService service = new CustomerProfileServiceImp();
                service.addSaveCustomerProfiles(this.getAddInfoMap(), addNewCutomerProfileVo);
                // Update new customer profile id.
                this.setProfileId(String.valueOf(addNewCutomerProfileVo.getCustomerProfile().getProfileId()));
            } else {
                setErrorCode(ErrorCode.FIELD_ERROR);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean isValidAccountSetup(CustomerProfileModel customerProfileModel) throws Exception {

        if (customerProfileModel != null) {
            if (StringUtils.isEmpty(customerProfileModel.getProfileName())) {
                addFieldError("cusProfile.profileName", "Name profile is empty.");
            } else {
                CustomerProfileFilter filter = new CustomerProfileFilter();
                ICustomerProfileService service = new CustomerProfileServiceImp();
                filter.setFranchiseCode(customerProfileModel.getFranchiseCode());
                filter.setProfileName(customerProfileModel.getProfileName());
                if (service.checkProfileName(filter) > 0) {
                    addFieldError("cusProfile.profileName", "Customer Profile Name Exist In system!");
                }
            }
            if (StringUtils.isEmpty(customerProfileModel.getGstId())) {
                addFieldError("cusProfile.gstId", "GST is empty.");
            } else {
                if (!StringUtils.isNumeric(customerProfileModel.getGstId())) {
                    addFieldError("cusProfile.gstId", "GST is not numberic.");
                }
            }
            if (StringUtils.isBlank(customerProfileModel.getInvoiceLateFee())) {
                addFieldError("customer.invoiceLateFee", "Invoice later fee cannot be blank (default=0)");
            } else {
                Double lateFee = 0.00;
                try {
                    lateFee = Double.valueOf(customerProfileModel.getInvoiceLateFee());
                    if (lateFee < 0) {
                        addFieldError("cusProfile.invoiceLateFee", "Invoice later fee must be greater than 0");
                    }
                } catch (Exception e) {
                    addFieldError("cusProfile.invoiceLateFee", "Invoice later fee must be a number");
                }
            }
        } else {
            this.addActionError("Missing customer profile informations.");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected boolean isValidCustomerCollection(CustomerCollectionModel collection) {
        if (StringUtils.isBlank(collection.getFreightCreditLimit())) {
            addFieldError("collectionModel.freightCreditLimit", "Freight Credit Limit cannot leave blank");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void prepareDatas() throws Exception {
        this.prepareAccountSetup();
        this.prepareBaseRates();
        this.prepareInvoiceOptions();
        this.prepareCollection();
    }

    protected void prepareAccountSetup() throws Exception {
        ICustomerProfileService service = new CustomerProfileServiceImp();
        CustomerProfileVo customerProfileVo = service.selectByProfileId(Long.parseLong(this.getProfileId()));
        CustomerProfileModel customerProfileModel = ModelUtils.createModelFromVo(customerProfileVo, CustomerProfileModel.class);
        customerProfileModel.setProfileId("0");
        customerProfileModel.setProfileName("");
        this.setCusProfile(customerProfileModel);
        this.prepareListService();
        this.prepareCustomerGroup();
        this.prepareIndustries();
        this.prepareAreas();
        this.prepareSaleRep();
        this.prepareCollectorList();
    }

    protected void prepareBaseRates() throws Exception {
        this.loadCustomer();
        this.loadDHL();
        this.loadDHLDom();
        this.loadTNTDom();
        this.loadTNTIntl();
        this.loadTollPriority();
        this.loadTollIpec();
        this.loadStarTrack();
        this.loadOthers();
    }

    protected void prepareInvoiceOptions() throws Exception {
        this.prepareInvoiceSortingOptions();
        this.prepareInvoiceTerms();
        this.prepareInvoiceToCustomers();
        this.preparePickupFees();
    }

    protected void prepareProfiles() throws Exception {
        ICustomerProfileService profileService = new CustomerProfileServiceImp();
        List<CustomerProfileVo> profileVos = profileService.selectByFranchiseCode(this.getFranchiseCode());
        List<CustomerProfileModel> profileModels = ModelUtils.createListModelFromVo(profileVos, CustomerProfileModel.class);
        this.setProfileList(profileModels);
    }

    protected void prepareCustomerGroup() throws Exception {
        ICustomerGroupService service = new CustomerGroupServiceImp();
        this.setCustomerGroup(ModelUtils.createListModelFromVo(service.selectAll(), CustomerGroupModel.class));
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
        List<UserVo> userVos = userService.getSaleReps(this.buildFranchiseCodesList(this.getFranchiseCode()));
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setSaleReps(userModels);
    }

    protected void prepareCollectorList() throws Exception {
        IUserService iUserService = new UserServiceImp();
        List<UserVo> userVos = iUserService.getCollectors();
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setCollectors(userModels);
    }

    protected List<String> buildFranchiseCodesList(String franCode) throws Exception {
        List<String> franchiseCodeList = new ArrayList<String>();
        if (StringUtils.isBlank(franCode) || "All".equalsIgnoreCase(franCode)) {
            for (FranchiseInfoModel franchise : this.getFranchises()) {
                franchiseCodeList.add(franchise.getCode());
            }
        } else {
            franchiseCodeList.add(franCode);
        }
        return franchiseCodeList;
    }

    protected void prepareInvoiceSortingOptions() {
        List<InvoiceSortingOption> options = new ArrayList<InvoiceSortingOption>();
        options.add(new InvoiceSortingOption(0, "Sort by ship date"));
        options.add(new InvoiceSortingOption(1, "Sort by reference code"));
        options.add(new InvoiceSortingOption(2, "Sort by Freight"));
        options.add(new InvoiceSortingOption(3, "Sort by customer"));
        this.setInvoiceSortingOptions(options);
    }

    protected void preparePickupFees() {
        List<PickupFee> options = new ArrayList<PickupFee>();
        options.add(new PickupFee(0, ""));
        options.add(new PickupFee(1, "On Demand"));
        options.add(new PickupFee(2, "Regular Stop"));
        options.add(new PickupFee(3, "Drop Box"));
        this.setPickupFees(options);
    }

    protected void prepareInvoiceTerms() throws Exception {
        IInvoiceService invoiceService = new InvoiceServiceImp();
        List<InvoiceTermVo> termVos = invoiceService.selectInvoiceTerms();
        List<InvoiceTermModel> termModels = ModelUtils.createListModelFromVo(termVos, InvoiceTermModel.class);
        for (InvoiceTermModel model : termModels) {
            model.setDays(model.getDays().concat(" days"));
        }
        this.setInvoiceTerms(termModels);
    }

    protected void prepareInvoiceToCustomers() throws Exception {
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        ICustomerService customerService = new CustomerServiceImp();
        List<CustomerVo> customerVos = customerService.selectByLogin(userId);
        List<CustomerModel> customerModels = new ArrayList<CustomerModel>();
        CustomerModel customerModel;
        for (CustomerVo customerVo : customerVos) {
            customerModel = new CustomerModel();
            customerModel.setCustomerCode(String.valueOf(customerVo.getCustomerCode()));
            customerModels.add(customerModel);
        }
        this.setInvoiceToCustomers(customerModels);
    }

    protected void prepareCollection() throws Exception {
        CustomerCollectionDao collectionDao = new CustomerCollectionDao();
        CustomerCollectionVo collectionVo = collectionDao.selectByProfileId(this.getProfileId());
        CustomerCollectionModel collectionModel = ModelUtils.createModelFromVo(collectionVo, CustomerCollectionModel.class);
        this.setCollectionModel(collectionModel);
    }

    @Override
    public String getProfileId() {
        return profileId;
    }

    @Override
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public List<CustomerProfileModel> getProfileList() {
        return profileList;
    }

    public void setProfileList(List<CustomerProfileModel> profileList) {
        this.profileList = profileList;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public CustomerProfileModel getCusProfile() {
        return cusProfile;
    }

    public void setCusProfile(CustomerProfileModel cusProfile) {
        this.cusProfile = cusProfile;
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

    public void setCustomerGroup(List<CustomerGroupModel> customerGroup) {
        this.customerGroup = customerGroup;
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

    public List<InvoiceSortingOption> getInvoiceSortingOptions() {
        return invoiceSortingOptions;
    }

    public void setInvoiceSortingOptions(List<InvoiceSortingOption> invoiceSortingOptions) {
        this.invoiceSortingOptions = invoiceSortingOptions;
    }

    public List<InvoiceTermModel> getInvoiceTerms() {
        return invoiceTerms;
    }

    public void setInvoiceTerms(List<InvoiceTermModel> invoiceTerms) {
        this.invoiceTerms = invoiceTerms;
    }

    public List<CustomerModel> getInvoiceToCustomers() {
        return invoiceToCustomers;
    }

    public void setInvoiceToCustomers(List<CustomerModel> invoiceToCustomers) {
        this.invoiceToCustomers = invoiceToCustomers;
    }

    public List<PickupFee> getPickupFees() {
        return pickupFees;
    }

    public void setPickupFees(List<PickupFee> pickupFees) {
        this.pickupFees = pickupFees;
    }

    public CustomerCollectionModel getCollectionModel() {
        return collectionModel;
    }

    public void setCollectionModel(CustomerCollectionModel collectionModel) {
        this.collectionModel = collectionModel;
    }

    public AddNewCutomerProfileModel getAddNewCutomerProfile() {
        return addNewCutomerProfile;
    }

    public void setAddNewCutomerProfile(AddNewCutomerProfileModel addNewCutomerProfile) {
        this.addNewCutomerProfile = addNewCutomerProfile;
    }
}

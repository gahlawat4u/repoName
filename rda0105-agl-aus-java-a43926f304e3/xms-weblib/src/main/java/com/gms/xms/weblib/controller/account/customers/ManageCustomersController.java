package com.gms.xms.weblib.controller.account.customers;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.account.customers.BasicCustomerModel;
import com.gms.xms.model.account.customers.manage.CustomerAccountSetupModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerAddressModel;
import com.gms.xms.model.account.customers.manage.SaveManageCustomerModel;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.customer.CustomerProfileModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.customer.IManageCustomerService;
import com.gms.xms.persistence.service.customer.ManageCustomerServiceImp;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerFilter;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerVo;
import com.gms.xms.txndb.vo.account.customers.manage.SaveManageCustomerVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from ManageCustomersController
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class ManageCustomersController extends JsonBaseController {

    private static final long serialVersionUID = 6975124429186391772L;

    private String franchiseCode;
    private String customerCode;
    private String searchText;
    private String tabId;
    private List<FranchiseInfoModel> franchises;
    private List<BasicCustomerModel> customers;
    private List<CustomerProfileModel> profiles;

    // For saving.
    private SaveManageCustomerModel saveManageCustomer;

    public String show() {
        try {
            prepareData();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }

        return "success";
    }

    public String loadTab() {
        try {
            if (StringUtils.isBlank(this.getTabId())) {
                throw new CustomException("No tab id for loading...");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String view() {
        try {
            if (StringUtils.isBlank(this.getCustomerCode()) || StringUtils.isBlank(this.getTabId())) {
                throw new CustomException("Please input customer code and tab id for loading customer information.");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            // Valid saving data.
            SaveManageCustomerModel manageCustomerModel = this.getSaveManageCustomer();
            if (manageCustomerModel == null) {
                throw new CustomException("No data for saving.");
            }
            boolean isValid = true;
            if (manageCustomerModel.getAccountSetup() != null) {
                isValid = isValid && isValidAccountSetup(manageCustomerModel.getAccountSetup());
            }
            if (manageCustomerModel.getCustomerAddress() != null) {
                isValid = isValid && isValidCustomerAddressInfo(manageCustomerModel.getCustomerAddress());
            }
            if (manageCustomerModel.getInvoiceOption() != null) {
                isValid = isValid && isValidInvoiceOptions(manageCustomerModel.getInvoiceOption());
            }
            if (manageCustomerModel.getCollection() != null) {
                isValid = isValid && isValidCustomerCollection(manageCustomerModel.getCollection());
            }
            if (isValid) {
                SaveManageCustomerVo manageCustomerVo = ModelUtils.createVoFromModel(this.getSaveManageCustomer(), SaveManageCustomerVo.class);
                IManageCustomerService manageCustomerService = new ManageCustomerServiceImp();
                manageCustomerService.updateCustomer(this.getAddInfoMap(), manageCustomerVo);
            } else {
                setErrorCode(ErrorCode.FIELD_ERROR);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean isValidAccountSetup(CustomerAccountSetupModel customer) {
        if (StringUtils.isBlank(customer.getGstId())) {
            addFieldError("saveManageCustomer.accountSetup.gstId", "GST # is required (default=0)");
        } else {
            Integer gstNumber = 0;
            try {
                gstNumber = Integer.valueOf(customer.getGstId());
                if (gstNumber < 0) {
                    addFieldError("saveManageCustomer.accountSetup.gstId", "GST # must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("saveManageCustomer.accountSetup.gstId", "GST # must be a integer number");
            }
        }
        return !hasFieldErrors();
    }

    protected boolean isValidCustomerAddressInfo(ManageCustomerAddressModel customer) {
        if (customer.getAddress().getUserType() == null) {
            customer.getAddress().setUserType("1");
        }

        if (StringUtils.isBlank(customer.getAddress().getCustomerName())) {
            addFieldError("saveManageCustomer.customerAddress.address.customerName", "Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getContactName())) {
            addFieldError("saveManageCustomer.customerAddress.address.contactName", "Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getAddress1())) {
            addFieldError("saveManageCustomer.customerAddress.address.address1", "Address cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getCity())) {
            addFieldError("saveManageCustomer.customerAddress.address.city", "City cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getCountry())) {
            addFieldError("saveManageCustomer.customerAddress.address.country", "Please select a country.");
        } else {
            if ("0".equalsIgnoreCase(customer.getAddress().getCountry())) {
                addFieldError("saveManageCustomer.customerAddress.address.country", "Please select a country.");
            }
        }
        if (StringUtils.isBlank(customer.getAddress().getPhone())) {
            addFieldError("saveManageCustomer.customerAddress.address.phone", "Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customer.getAddress().getEmail())) {
            String[] emails = customer.getAddress().getEmail().split(";");
            for (String email : emails) {
                if (!EmailUtils.isValidEmail(email.trim())) {
                    addFieldError("saveManageCustomer.customerAddress.address.email", "Invalid email address.");
                    break;
                }
            }
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCustomerName())) {
            addFieldError("saveManageCustomer.customerAddress.billingAddress.billingCustomerName", "Billing Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingContactName())) {
            addFieldError("saveManageCustomer.customerAddress.billingAddress.billingContactName", "Billing Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingAddress1())) {
            addFieldError("saveManageCustomer.customerAddress.billingAddress.billingAddress1", "Billing Address cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCity())) {
            addFieldError("saveManageCustomer.customerAddress.billingAddress.billingCity", "Billing City cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCountry())) {
            addFieldError("saveManageCustomer.customerAddress.billingAddress.billingCountry", "Please select a billing country.");
        } else {
            if ("0".equalsIgnoreCase(customer.getBillingAddress().getBillingCountry())) {
                addFieldError("saveManageCustomer.customerAddress.billingAddress.billingCountry", "Please select a billing country.");
            }
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingPhone())) {
            addFieldError("saveManageCustomer.customerAddress.billingAddress.billingPhone", "Billing Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customer.getBillingAddress().getBillingEmail())) {
            String[] emails = customer.getBillingAddress().getBillingEmail().split(";");
            for (String email : emails) {
                if (!EmailUtils.isValidEmail(email.trim())) {
                    addFieldError("saveManageCustomer.customerAddress.billingAddress.billingEmail", "Invalid billing email address.");
                    break;
                }
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOwnerEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOwnerEmail())) {
                addFieldError("saveManageCustomer.customerAddress.address.ownerEmail", "Invalid owner email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getApEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getApEmail())) {
                addFieldError("saveManageCustomer.customerAddress.address.apEmail", "Invalid AP email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOtherEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOtherEmail())) {
                addFieldError("saveManageCustomer.customerAddress.address.otherEmail", "Invalid other email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOther2Email())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOther2Email())) {
                addFieldError("saveManageCustomer.customerAddress.address.other2Email", "Invalid other 2 email address.");
            }
        }
        return !hasFieldErrors();
    }

    protected boolean isValidInvoiceOptions(CustomerModel customer) throws Exception {
        // If email invoice checked so need valid email address of billing email
        // under Address tab.
        if ("true".equalsIgnoreCase(customer.getEmailInvoice())) {
            String billingEmail = "";
            CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
            CustomerBillingAddressVo billingAddressVo = null;
            if (this.getSaveManageCustomer().getCustomerAddress() == null) {
                billingAddressVo = billingAddressDao.getByCustomerCode(customer.getCustomerCode());
                billingEmail = billingAddressVo == null ? "" : billingAddressVo.getBillingEmail();
            } else if (this.getSaveManageCustomer().getCustomerAddress().getBillingAddress() == null) {
                billingAddressVo = billingAddressDao.getByCustomerCode(customer.getCustomerCode());
                billingEmail = billingAddressVo == null ? "" : billingAddressVo.getBillingEmail();
            } else {
                billingEmail = this.getSaveManageCustomer().getCustomerAddress().getBillingAddress().getBillingEmail();
            }
            if (StringUtils.isBlank(billingEmail)) {
                addFieldError("saveManageCustomer.customerAddress.billingAddress.billingEmail", "The email for billing email is required.");
            } else {
                String[] emails = billingEmail.split(";");
                for (String email : emails) {
                    if (!EmailUtils.isValidEmail(email.trim())) {
                        addFieldError("saveManageCustomer.customerAddress.billingAddress.billingEmail", "Please enter valid email for billing email.");
                        break;
                    }
                }
            }
        }
        if (StringUtils.isBlank(customer.getInvoiceLateFee())) {
            addFieldError("saveManageCustomer.invoiceOption.invoiceLateFee", "Invoice later fee cannot be blank (default=0)");
        } else {
            Double lateFee = 0.00;
            try {
                lateFee = Double.valueOf(customer.getInvoiceLateFee());
                if (lateFee < 0) {
                    addFieldError("saveManageCustomer.invoiceOption.invoiceLateFee", "Invoice later fee must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("saveManageCustomer.invoiceOption.invoiceLateFee", "Invoice later fee must be a number");
            }
        }
        return !hasFieldErrors();
    }

    protected boolean isValidCustomerCollection(CustomerCollectionModel customerCollection) {
        if (StringUtils.isBlank(customerCollection.getFreightCreditLimit())) {
            addFieldError("saveManageCustomer.collection.freightCreditLimit", "Freight Credit Limit cannot leave blank");
        } else {
            try {
                Double freight = Double.valueOf(customerCollection.getFreightCreditLimit());
                if (freight < 0) {
                    addFieldError("saveManageCustomer.collection.freightCreditLimit", "Freight Credit Limit must be greater than zero");
                }
            } catch (Exception e) {
                addFieldError("saveManageCustomer.collection.freightCreditLimit", "Freight Credit Limit must be a number");
            }
        }
        if (!StringUtils.isBlank(customerCollection.getReminderEmailAddress()) && !EmailUtils.isValidEmail(customerCollection.getReminderEmailAddress())) {
            addFieldError("saveManageCustomer.collection.reminderEmailAddress", "Invalid reminder email address");
        }
        return !hasFieldErrors();
    }

    public String searchCustomers() {
        try {
            doSearch(this.buildFilter());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String loadAddCus() {
        try {
            prepareProfiles();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void doSearch(BasicCustomerFilter filter) throws Exception {
        ICustomerService customerService = new CustomerServiceImp();
        List<BasicCustomerVo> customerVos = customerService.selectByBasicCustomerFilter(filter);
        List<BasicCustomerModel> customerModels = ModelUtils.createListModelFromVo(customerVos, BasicCustomerModel.class);
        // Final customer list.
        List<BasicCustomerModel> result = new ArrayList<BasicCustomerModel>();
        // The first option.
        BasicCustomerModel model = new BasicCustomerModel();
        model.setCustomerCode("-1");
        model.setCustomerName(this.getLocalizationText("Select a Customer"));
        result.add(model);
        // The second option.
        model = new BasicCustomerModel();
        model.setCustomerCode("0");
        model.setCustomerName(this.getLocalizationText("Add a Customer"));
        result.add(model);
        result.addAll(customerModels);
        this.setCustomers(result);
    }

    private String getFirstFranchiseCode() {
        if (franchises == null || franchises.size() == 0) {
            return null;
        }
        return franchises.get(0).getCode();
    }

    private BasicCustomerFilter buildFilter() throws Exception {
        BasicCustomerFilter filter = new BasicCustomerFilter();
        filter.setSearchText(searchText);
        filter.setFranchiseCode(franchiseCode);
        // Set user id and user level for filter.
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        String userLevel = (String) this.getSession("SESS_XMS_ADMIN_LEVEL");
        filter.setUserId(Long.valueOf(userId));
        filter.setUserLevel(Double.valueOf(userLevel).intValue());
        return filter;
    }

    private void prepareData() throws Exception {
        // Get franchise list.
        prepareFranchiseList();
        // Set customer code and franchise code follow the given customer code.
        BasicCustomerFilter filter = new BasicCustomerFilter();
        if (!StringUtils.isBlank(this.getCustomerCode())) {
            String franchiseCode = this.getCustomerCode().length() > 3 ? this.getCustomerCode().substring(0, 3) : this.getCustomerCode();
            this.setFranchiseCode(franchiseCode);
            filter.setFranchiseCode(franchiseCode);
        } else {
            filter.setFranchiseCode(this.getFirstFranchiseCode());
        }
        // Set user id and user level for filter.
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        String userLevel = (String) this.getSession("SESS_XMS_ADMIN_LEVEL");
        filter.setUserId(Long.valueOf(userId));
        filter.setUserLevel(Double.valueOf(userLevel).intValue());
        // Get customer list.
        doSearch(filter);
    }

    private void prepareFranchiseList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        this.setFranchises(franchiseInfoModels);
    }

    private void prepareProfiles() throws Exception {
        ICustomerProfileService profileService = new CustomerProfileServiceImp();
        List<CustomerProfileVo> profileVos = profileService.selectByFranchiseCode(franchiseCode);
        List<CustomerProfileModel> profileModels = ModelUtils.createListModelFromVo(profileVos, CustomerProfileModel.class);
        this.setProfiles(profileModels);
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<FranchiseInfoModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseInfoModel> franchises) {
        this.franchises = franchises;
    }

    public List<BasicCustomerModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<BasicCustomerModel> customers) {
        this.customers = customers;
    }

    public List<CustomerProfileModel> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<CustomerProfileModel> profiles) {
        this.profiles = profiles;
    }

    public SaveManageCustomerModel getSaveManageCustomer() {
        return saveManageCustomer;
    }

    public void setSaveManageCustomer(SaveManageCustomerModel saveManageCustomer) {
        this.saveManageCustomer = saveManageCustomer;
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }
}

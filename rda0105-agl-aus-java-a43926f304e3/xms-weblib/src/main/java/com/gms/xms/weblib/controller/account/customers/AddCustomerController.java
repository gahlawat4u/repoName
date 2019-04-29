package com.gms.xms.weblib.controller.account.customers;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.*;
import com.gms.xms.model.*;
import com.gms.xms.model.account.customers.manage.AddCustomerModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerAddressModel;
import com.gms.xms.model.account.customers.manage.SaveCustomerBaseRateModel;
import com.gms.xms.model.area.AreaModel;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.customergroup.CustomerGroupModel;
import com.gms.xms.model.industry.IndustryModel;
import com.gms.xms.model.invoicing.CustomerBillingAddressModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.WebshipGroupModel;
import com.gms.xms.persistence.dao.AccountServiceDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.admin.CarrierZoneDao;
import com.gms.xms.persistence.dao.admin.customer.baserate.ManageCustProfileBaseRateDao;
import com.gms.xms.persistence.service.area.AreaServiceImp;
import com.gms.xms.persistence.service.area.IAreaService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.customer.IManageCustomerService;
import com.gms.xms.persistence.service.customer.ManageCustomerServiceImp;
import com.gms.xms.persistence.service.customergroup.CustomerGroupServiceImp;
import com.gms.xms.persistence.service.customergroup.ICustomerGroupService;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.industry.IIndustryService;
import com.gms.xms.persistence.service.industry.IndustryServiceImp;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.persistence.service.webshipgroup.IWebshipGroupService;
import com.gms.xms.persistence.service.webshipgroup.WebshipGroupServiceImp;
import com.gms.xms.txndb.model.admin.customer.baserate.CustBaseRateModel;
import com.gms.xms.txndb.model.admin.customer.baserate.OtherCustBaseRateModel;
import com.gms.xms.txndb.model.admin.customer.baserate.ServiceTypeModel;
import com.gms.xms.txndb.model.admin.customerprofile.baserate.CustProfileServiceTypeModel;
import com.gms.xms.txndb.model.admin.customerprofile.baserate.OtherCustProfileBaseRateModel;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.account.customers.manage.AddCustomerVo;
import com.gms.xms.txndb.vo.admin.customerprofile.baserate.*;
import com.gms.xms.txndb.vo.area.AreaVo;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import com.gms.xms.txndb.vo.customergroup.CustomerGroupVo;
import com.gms.xms.txndb.vo.industry.IndustryVo;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Posted from AddCustomerController
 * <p>
 * Author DatTV Oct 13, 2015
 */
public class AddCustomerController extends JsonBaseController {

    private static final long serialVersionUID = 6573103142377000501L;

    // For loading profile info.
    private String profileId;
    // For all drop down list.
    private List<ServiceModel> services;
    private List<CustomerGroupModel> customerGroups;
    private List<WebshipGroupModel> webshipGroups;
    private List<IndustryModel> industries;
    private List<AreaModel> areas;
    private List<UserModel> salesReps;
    private List<UserModel> collectors;
    private List<CountryModel> countries;
    private List<InvoiceSortingOption> invoiceSortingOptions;
    private List<InvoiceTermModel> invoiceTerms;
    private List<CustomerModel> invoiceToCustomers;
    private List<PickupFee> pickupFees;

    // Show model.
    private CustomerModel customer; // For account setup + invoice options +
    // minimum base rate of base rates tab.
    private ManageCustomerAddressModel address; // For address tab.
    // For base rates tab.
    private List<ServiceTypeModel> dhl;
    private List<ServiceTypeModel> dhlDom;
    private List<ServiceTypeModel> tntDom;
    private List<ServiceTypeModel> tntIntl;
    private List<ServiceTypeModel> tollPriority;
    private List<ServiceTypeModel> tollIpec;
    private List<ServiceTypeModel> starTrack;
    private List<OtherCustBaseRateModel> others;
    private List<CarrierZoneModel> tntDomColumns;
    private List<CarrierZoneModel> starTrackColumns;
    // For collections tab.
    private CustomerCollectionModel collection;
    // For note tab.
    private NoteModel note;
    // For saving base rates.
    private SaveCustomerBaseRateModel saveCustBaseRate;

    public String loadView() {
        try {
            if (StringUtils.isBlank(this.getProfileId())) {
                throw new CustomException("No profile id for loading.");
            }
            // Prepare data for all drop down lists.
            prepareData4DropDownLists();
            // Load profile info.
            loadProfile(Long.valueOf(this.getProfileId()));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            // Validate customer info.
            if (this.getCustomer() == null || this.getAddress() == null || this.getSaveCustBaseRate() == null || this.getCollection() == null) {
                throw new CustomException("Please input all customer information for saving.");
            }
            boolean isValid = true;
            isValid = isValid && isValidCustomer(this.getCustomer());
            isValid = isValid && isValidCustomerAddress(this.getAddress());
            isValid = isValid && isValidInvoiceOptions(this.getCustomer());
            isValid = isValid && isValidCustomerCollection(this.getCollection());
            if (!isValid) {
                setErrorCode(ErrorCode.FIELD_ERROR);
            } else {
                // Check duplicate customer.
                if (isDuplicateCustomer(this.getCustomer().getCustomerCode())) {
                    throw new CustomException("Duplicate customer code.");
                }
                // Prepare add customer model for saving.
                AddCustomerVo addCustomerVo = prepareAddCustomerModel();
                // Do save customer.
                IManageCustomerService customerService = new ManageCustomerServiceImp();
                customerService.addCustomer(this.getAddInfoMap(), addCustomerVo, Long.valueOf(this.getProfileId()));
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean isDuplicateCustomer(String customerCode) throws Exception {
        CustomerDao customerDao = new CustomerDao();
        CustomerVo customerVo = customerDao.selectByCode(customerCode);
        return customerVo != null;
    }

    protected AddCustomerVo prepareAddCustomerModel() throws Exception {
        AddCustomerModel addCustomer = new AddCustomerModel();
        CustomerModel customer = this.getCustomer();
        // Update create date and create user id.
        customer.setCreateDate(DateUtils.convertDateToString(new Date(), AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null));
        customer.setCreatedUserId((String) this.getSession("SESS_XMS_ADMIN_ID"));
        customer.setAdminFunction("false");
        customer.setWebshipAdminId("0");
        if (StringUtils.isBlank(customer.getEnableSi())) {
            customer.setEnableSi("false");
        }
        addCustomer.setCustomer(customer);
        // Update customer address.
        CustomerAddressModel customerAddress = this.getAddress().getAddress();
        // Upper case some customer address info.
        customerAddress.setCustomerName(StringUtils.isBlank(customerAddress.getCustomerName()) ? "" : customerAddress.getCustomerName().toUpperCase());
        customerAddress.setContactName(StringUtils.isBlank(customerAddress.getContactName()) ? "" : customerAddress.getContactName().toUpperCase());
        customerAddress.setContactTitle(StringUtils.isBlank(customerAddress.getContactTitle()) ? "" : customerAddress.getContactTitle().toUpperCase());
        customerAddress.setAddress1(StringUtils.isBlank(customerAddress.getAddress1()) ? "" : customerAddress.getAddress1().toUpperCase());
        customerAddress.setAddress2(StringUtils.isBlank(customerAddress.getAddress2()) ? "" : customerAddress.getAddress2().toUpperCase());
        customerAddress.setCity(StringUtils.isBlank(customerAddress.getCity()) ? "" : customerAddress.getCity().toUpperCase());
        customerAddress.setPostalCode(StringUtils.isBlank(customerAddress.getPostalCode()) ? "" : customerAddress.getPostalCode().toUpperCase());
        customerAddress.setStateCode(StringUtils.isBlank(customerAddress.getStateCode()) ? "" : customerAddress.getStateCode().toUpperCase());
        customerAddress.setOwner(StringUtils.isBlank(customerAddress.getOwner()) ? "" : customerAddress.getOwner().toUpperCase());
        customerAddress.setApContact(StringUtils.isBlank(customerAddress.getApContact()) ? "" : customerAddress.getApContact().toUpperCase());
        customerAddress.setOtherContact(StringUtils.isBlank(customerAddress.getOtherContact()) ? "" : customerAddress.getOtherContact().toUpperCase());
        customerAddress.setOther2Contact(StringUtils.isBlank(customerAddress.getOther2Contact()) ? "" : customerAddress.getOther2Contact().toUpperCase());
        customerAddress.setCustomerCode(customer.getCustomerCode());
        customerAddress.setUserType("1"); // Create customer so user type is 1.
        addCustomer.setAddress(customerAddress);
        // Update customer billing address.
        CustomerBillingAddressModel billingAddress = this.getAddress().getBillingAddress();
        // Upper case some customer billing address info.
        billingAddress.setBillingCustomerName(StringUtils.isBlank(billingAddress.getBillingCustomerName()) ? "" : billingAddress.getBillingCustomerName().toUpperCase());
        billingAddress.setBillingContactName(StringUtils.isBlank(billingAddress.getBillingContactName()) ? "" : billingAddress.getBillingContactName().toUpperCase());
        billingAddress.setBillingContactTitle(StringUtils.isBlank(billingAddress.getBillingContactTitle()) ? "" : billingAddress.getBillingContactTitle().toUpperCase());
        billingAddress.setBillingAddress1(StringUtils.isBlank(billingAddress.getBillingAddress1()) ? "" : billingAddress.getBillingAddress1().toUpperCase());
        billingAddress.setBillingAddress2(StringUtils.isBlank(billingAddress.getBillingAddress2()) ? "" : billingAddress.getBillingAddress2().toUpperCase());
        billingAddress.setBillingCity(StringUtils.isBlank(billingAddress.getBillingCity()) ? "" : billingAddress.getBillingCity().toUpperCase());
        billingAddress.setBillingPostalCode(StringUtils.isBlank(billingAddress.getBillingPostalCode()) ? "" : billingAddress.getBillingPostalCode().toUpperCase());
        billingAddress.setBillingStateCode(StringUtils.isBlank(billingAddress.getBillingStateCode()) ? "" : billingAddress.getBillingStateCode().toUpperCase());
        billingAddress.setCustomerCode(customer.getCustomerCode());
        billingAddress.setUsertype("1");
        addCustomer.setBillingAddress(billingAddress);
        // Get customer base rates.
        List<CustomerBaseRateModel> customerBaseRates = this.getSaveCustBaseRate().getCustomerBaseRates();
        if (customerBaseRates != null && customerBaseRates.size() > 0) {
            for (CustomerBaseRateModel baseRate : customerBaseRates) {
                baseRate.setCustomerCode(customer.getCustomerCode());
                // Set default zone check is false if it's null.
                if (baseRate.getZoneCheck() == null) {
                    baseRate.setZoneCheck("false");
                }
                // If content is -1 then save it as 0.
                if ("-1".equalsIgnoreCase(baseRate.getContent())) {
                    baseRate.setContent("0");
                }
            }
        }
        addCustomer.setCustomerBaseRates(customerBaseRates);
        // Get customer collection.
        CustomerCollectionModel collection = this.getCollection();
        collection.setUserType("1");
        addCustomer.setCollection(collection);
        // Update note.
        NoteModel note = this.getNote();
        if (StringUtils.isBlank(note.getNote())) {
            note = null;
        } else {
            note.setAccountNo(customer.getCustomerCode());
            note.setNoteType("1"); // Note type for customer is 1.
            note.setCheck("false");
            note.setCurrent("0");
            note.setInvoiceCode("");
            note.setModifyDate(DateUtils.convertDateToString(new Date(), AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null));
            note.setPaymentId("0");
            note.setUserId((String) this.getSession("SESS_XMS_ADMIN_ID"));
        }
        addCustomer.setNote(note);
        // Convert model to vo.
        AddCustomerVo addCustomerVo = ModelUtils.createVoFromModel(addCustomer, AddCustomerVo.class);
        // If inactive=0 then active date is current date.
        if (!addCustomerVo.getCustomer().getInActive()) {
            addCustomerVo.getCustomer().setActivateDate(new Date());
        }
        // Add Webship User.
        WebshipVo webshipVo = new WebshipVo();
        webshipVo.setCustomerCode(addCustomerVo.getCustomer().getCustomerCode());
        webshipVo.setName(String.valueOf(addCustomerVo.getCustomer().getCustomerCode()));
        String encryptionKey = AppConstants.APP_SETTINGS.getEncryptionKey();
        String webshipPassword = AppConstants.APP_SETTINGS.getDefaultWebshipPassword();
        webshipVo.setPassword(CryptUtils.Encrypt(webshipPassword, encryptionKey));
        webshipVo.setCreateDate(new Date());
        webshipVo.setLanguage(0);
        webshipVo.setAllowExportAddressBook(true);
        webshipVo.setIsRequireChangePassword(true);
        webshipVo.setResetPasswordStatus(0);
        addCustomerVo.setWebship(webshipVo);
        // Add account service setting for this customer.
        String franchiseCode = String.valueOf(addCustomerVo.getCustomer().getCustomerCode());
        franchiseCode = franchiseCode.length() > 3 ? franchiseCode.substring(0, 3) : franchiseCode;
        franchiseCode += "00001";
        AccountServiceDao accountServiceDao = new AccountServiceDao();
        List<AccountServiceVo> accountServiceVos = accountServiceDao.selectActiveServiceByFranchiseCode(franchiseCode);
        if (accountServiceVos != null && accountServiceVos.size() > 0) {
            for (AccountServiceVo accountServiceVo : accountServiceVos) {
                accountServiceVo.setCustomerCode(addCustomerVo.getCustomer().getCustomerCode());
                accountServiceVo.setUserType(0);
            }
        }
        addCustomerVo.setAccountServices(accountServiceVos);
        return addCustomerVo;
    }

    protected boolean isValidCustomer(CustomerModel customer) {
        if (StringUtils.isBlank(customer.getCustomerCode())) {
            addFieldError("customer.customerCode", "Customer Code is required.");
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
        if (StringUtils.isBlank(customer.getMinimunBaseCharge())) {
            addFieldError("customer.minimumBaseCharge", "Minimum base charge is required");
        } else {
            Double minimunBaseCharge;
            try {
                minimunBaseCharge = Double.valueOf(customer.getMinimunBaseCharge());
                if (minimunBaseCharge < 0.0) {
                    addFieldError("customer.minimumBaseCharge", "Minimum base charge must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("customer.minimumBaseCharge", "Minimum base charge must be a double number");
            }
        }

        return !hasFieldErrors();
    }

    protected boolean isValidCustomerAddress(ManageCustomerAddressModel customerAddress) {
        if (StringUtils.isBlank(customerAddress.getAddress().getCustomerName())) {
            addFieldError("address.address.customerName", "Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customerAddress.getAddress().getContactName())) {
            addFieldError("address.address.contactName", "Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customerAddress.getAddress().getAddress1())) {
            addFieldError("address.address.address1", "Address cannot be empty.");
        }
        if (StringUtils.isBlank(customerAddress.getAddress().getCity())) {
            addFieldError("address.address.city", "City cannot be empty.");
        }
        if (StringUtils.isBlank(customerAddress.getAddress().getCountry())) {
            addFieldError("address.address.country", "Please select a country.");
        } else {
            if ("0".equalsIgnoreCase(customerAddress.getAddress().getCountry())) {
                addFieldError("address.address.country", "Please select a country.");
            }
        }
        if (StringUtils.isBlank(customerAddress.getAddress().getPhone())) {
            addFieldError("address.address.phone", "Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customerAddress.getAddress().getEmail())) {
            if (!EmailUtils.isValidEmail(customerAddress.getAddress().getEmail())) {
                addFieldError("address.address.email", "Invalid email address.");
            }
        }
        if (StringUtils.isBlank(customerAddress.getBillingAddress().getBillingCustomerName())) {
            addFieldError("address.billingAddress.billingCustomerName", "Billing Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customerAddress.getBillingAddress().getBillingContactName())) {
            addFieldError("address.billingAddress.billingContactName", "Billing Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customerAddress.getBillingAddress().getBillingAddress1())) {
            addFieldError("address.billingAddress.billingAddress1", "Billing Address cannot be empty.");
        }
        if (StringUtils.isBlank(customerAddress.getBillingAddress().getBillingCity())) {
            addFieldError("address.billingAddress.billingCity", "Billing City cannot be empty.");
        }
        if (StringUtils.isBlank(customerAddress.getBillingAddress().getBillingCountry())) {
            addFieldError("address.billingAddress.billingCountry", "Please select a billing country.");
        } else {
            if ("0".equalsIgnoreCase(customerAddress.getBillingAddress().getBillingCountry())) {
                addFieldError("address.billingAddress.billingCountry", "Please select a billing country.");
            }
        }
        if (StringUtils.isBlank(customerAddress.getBillingAddress().getBillingPhone())) {
            addFieldError("address.billingAddress.billingPhone", "Billing Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customerAddress.getBillingAddress().getBillingEmail())) {
            if (!EmailUtils.isValidEmail(customerAddress.getBillingAddress().getBillingEmail())) {
                addFieldError("address.billingAddress.billingEmail", "Invalid billing email address.");
            }
        }
        if (!StringUtils.isBlank(customerAddress.getAddress().getOwnerEmail())) {
            if (!EmailUtils.isValidEmail(customerAddress.getAddress().getOwnerEmail())) {
                addFieldError("address.address.ownerEmail", "Invalid owner email address.");
            }
        }
        if (!StringUtils.isBlank(customerAddress.getAddress().getApEmail())) {
            if (!EmailUtils.isValidEmail(customerAddress.getAddress().getApEmail())) {
                addFieldError("address.address.apEmail", "Invalid AP email address.");
            }
        }
        if (!StringUtils.isBlank(customerAddress.getAddress().getOtherEmail())) {
            if (!EmailUtils.isValidEmail(customerAddress.getAddress().getOtherEmail())) {
                addFieldError("address.address.otherEmail", "Invalid other email address.");
            }
        }
        if (!StringUtils.isBlank(customerAddress.getAddress().getOther2Email())) {
            if (!EmailUtils.isValidEmail(customerAddress.getAddress().getOther2Email())) {
                addFieldError("address.address.other2Email", "Invalid other 2 email address.");
            }
        }
        return !hasFieldErrors();
    }

    protected boolean isValidInvoiceOptions(CustomerModel customer) {
        if (StringUtils.isBlank(customer.getInvoiceLateFee())) {
            addFieldError("customer.invoiceLateFee", "Invoice later fee cannot be blank (default=0)");
        } else {
            Double lateFee = 0.00;
            try {
                lateFee = Double.valueOf(customer.getInvoiceLateFee());
                if (lateFee < 0) {
                    addFieldError("customer.invoiceLateFee", "Invoice later fee must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("customer.invoiceLateFee", "Invoice later fee must be a number");
            }
        }
        return !hasFieldErrors();
    }

    protected boolean isValidCustomerCollection(CustomerCollectionModel customerCollection) {
        if (StringUtils.isBlank(customerCollection.getFreightCreditLimit())) {
            addFieldError("collection.freightCreditLimit", "Freight Credit Limit cannot leave blank");
        } else {
            try {
                Double freight = Double.valueOf(customerCollection.getFreightCreditLimit());
                if (freight < 0) {
                    addFieldError("collection.freightCreditLimit", "Freight Credit Limit must be greater than zero");
                }
            } catch (Exception e) {
                addFieldError("collection.freightCreditLimit", "Freight Credit Limit must be a number");
            }
        }
        if (!StringUtils.isBlank(customerCollection.getReminderEmailAddress()) && !EmailUtils.isValidEmail(customerCollection.getReminderEmailAddress())) {
            addFieldError("collection.reminderEmailAddress", "Invalid reminder email address");
        }
        return !hasFieldErrors();
    }

    protected void loadProfile(Long profileId) throws Exception {
        // Load customer profile.
        ICustomerProfileService profileService = new CustomerProfileServiceImp();
        CustomerProfileVo profileVo = profileService.selectByProfileId(profileId);
        // Load customer account setup.
        CustomerVo customerVo = convertProfileToCustomer(profileVo);
        CustomerModel customerModel = ModelUtils.createModelFromVo(customerVo, CustomerModel.class);

        this.setCustomer(customerModel);
        // Load customer address.
        ManageCustomerAddressModel manageCustomerAddressModel = new ManageCustomerAddressModel();
        CustomerAddressModel addressModel = new CustomerAddressModel();
        manageCustomerAddressModel.setAddress(addressModel);
        CustomerBillingAddressModel billingAddressModel = new CustomerBillingAddressModel();
        billingAddressModel.setBillingSameWithCustomer("true");
        manageCustomerAddressModel.setBillingAddress(billingAddressModel);
        this.setAddress(manageCustomerAddressModel);
        // Load customer base rates from customer profile base rates.
        loadDHL(customerModel.getCustomerCode());
        loadDHLDom(customerModel.getCustomerCode());
        loadTNTDom(customerModel.getCustomerCode());
        loadTNTIntl(customerModel.getCustomerCode());
        loadTollPriority(customerModel.getCustomerCode());
        loadTollIpec(customerModel.getCustomerCode());
        loadStarTrack(customerModel.getCustomerCode());
        loadOthers(customerModel.getCustomerCode());
        // Load customer collection.
        CustomerCollectionModel collectionModel = new CustomerCollectionModel();
        collectionModel.setFreightCreditLimit("0.0");
        collectionModel.setReminder("false");
        collectionModel.setReminderEmail("false");
        collectionModel.setReminderPrint("true");
        collectionModel.setReminderUseEmailInvoice("true");
        this.setCollection(collectionModel);
        // New note.
        NoteModel noteModel = new NoteModel();
        noteModel.setNote("");
        this.setNote(noteModel);
    }

    protected CustomerVo convertProfileToCustomer(CustomerProfileVo profile) throws Exception {
        CustomerVo customerVo = new CustomerVo();
        // Generate new customer code
        ICustomerService customerService = new CustomerServiceImp();
        Long customerCode = customerService.getLastestCustCodeByFranCode(profile.getFranchiseCode());
        Integer srno = null;
        if (customerCode == null) {
            customerCode = profile.getFranchiseCode() * 100000 + 2;
            srno = 0;
        } else {
            srno = (int) (customerCode % 100000);
            customerCode++;
        }
        customerVo.setCustomerCode(customerCode);
        customerVo.setFranchiseCode(profile.getFranchiseCode());
        customerVo.setSrno(srno);
        customerVo.setInActive(profile.getInActive());
        customerVo.setGroupId(profile.getGroupId());
        customerVo.setWebshipGroupId(profile.getWebshipGroupId());
        customerVo.setIndustryId(profile.getIndustryId().intValue());
        customerVo.setSalesRepId(profile.getSalesRepId());
        customerVo.setCollectorId(profile.getCollectorId().longValue());
        customerVo.setRegistrationId(String.valueOf(profile.getRegistrationId()));
        customerVo.setGstId(profile.getGstId());
        customerVo.setDhlAccount(profile.getDhlAccount());
        customerVo.setDhlDomesticAccount(profile.getDhlDomesticAccount());
        customerVo.setDhlInternationalAccount(profile.getDhlInternationalAccount());
        customerVo.setDhlInboundAccount(profile.getDhlInboundAccount());
        customerVo.setOtherAccount(profile.getOtherAccount());
        customerVo.setRejectionNote(profile.getRejectionNote());
        customerVo.setMinimunBaseCharge(profile.getMinimunBaseCharge());
        customerVo.setInvoiceSorting(profile.getInvoiceSorting());
        customerVo.setInvoiceTerms(profile.getInvoiceTerms());
        customerVo.setInvoiceToCustomerId(profile.getInvoiceToCustomerId());
        customerVo.setPickupFee(profile.getPickupFee());
        customerVo.setInvoiceLateFee(profile.getInvoiceLateFee());
        customerVo.setEmailInvoice(profile.getEmailInvoice());
        customerVo.setDownloadCsvInvoice(profile.getDownloadCsvInvoice());
        customerVo.setPreviousCarrier(profile.getPreviousCarrier());
        customerVo.setAreaId(profile.getAreaId());
        customerVo.setTntAccount(profile.getTntAccount());
        customerVo.setAaeAccount(profile.getAaeAccount());
        customerVo.setHubAccount(profile.getHubAccount());
        customerVo.setFedexAccount(profile.getFedexAccount());
        customerVo.setTollPriorityAccount(profile.getTollPriorityAccount());
        customerVo.setUkMailAccount(profile.getUkMailAccount());
        customerVo.setUpsAccount(profile.getUpsAccount());
        customerVo.setBookingEmailNotification(profile.getBookingEmailNotification());
        customerVo.setTollIpecAccount(profile.getTollIpecAccount());
        customerVo.setEnableSi(profile.getEnableSi());
        return customerVo;
    }

    protected void prepareData4DropDownLists() throws Exception {
        prepareServiceList();
        prepareCustomerGroupList();
        prepareWebshipGroupList();
        prepareIndustryList();
        prepareAreaList();
        prepareSalesRepList();
        prepareCollectorList();
        prepareCountryList();
        prepareInvoiceSortingOptions();
        prepareInvoiceTerms();
        prepareInvoiceToCustomers();
        preparePickupFees();
    }

    protected void prepareServiceList() throws Exception {
        IServiceService iServiceService = new ServiceServiceImp();
        List<ServiceVo> serviceVos = iServiceService.selectAll();
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.setServices(serviceModels);
    }

    protected void prepareCustomerGroupList() throws Exception {
        ICustomerGroupService iCustomerGroupService = new CustomerGroupServiceImp();
        List<CustomerGroupVo> customerGroupVos = iCustomerGroupService.selectAll();
        List<CustomerGroupModel> customerGroupModels = ModelUtils.createListModelFromVo(customerGroupVos, CustomerGroupModel.class);
        this.setCustomerGroups(customerGroupModels);
    }

    protected void prepareWebshipGroupList() throws Exception {
        IWebshipGroupService iWebshipGroupService = new WebshipGroupServiceImp();
        List<WebshipGroupVo> webshipGroupVos = iWebshipGroupService.selectAll();
        List<WebshipGroupModel> webshipGroupModels = ModelUtils.createListModelFromVo(webshipGroupVos, WebshipGroupModel.class);
        this.setWebshipGroups(webshipGroupModels);
    }

    protected void prepareIndustryList() throws Exception {
        IIndustryService iIndustryService = new IndustryServiceImp();
        List<IndustryVo> industryVos = iIndustryService.selectAll();
        List<IndustryModel> industryModels = ModelUtils.createListModelFromVo(industryVos, IndustryModel.class);
        this.setIndustries(industryModels);
    }

    protected void prepareAreaList() throws Exception {
        IAreaService iAreaService = new AreaServiceImp();
        List<AreaVo> areaVos = iAreaService.selectAll();
        List<AreaModel> areaModels = ModelUtils.createListModelFromVo(areaVos, AreaModel.class);
        this.setAreas(areaModels);
    }

    protected void prepareSalesRepList() throws Exception {
        IUserService iUserService = new UserServiceImp();
        List<UserVo> userVos = iUserService.getSaleReps(this.buildFranchiseCodeList());
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setSalesReps(userModels);
    }

    protected void prepareCollectorList() throws Exception {
        IUserService iUserService = new UserServiceImp();
        List<UserVo> userVos = iUserService.getCollectors();
        List<UserModel> userModels = ModelUtils.createListModelFromVo(userVos, UserModel.class);
        this.setCollectors(userModels);
    }

    protected void prepareCountryList() throws Exception {
        ICountryService countryService = new CountryServiceImp();
        List<CountryVo> countryVos = countryService.getCountryList();
        List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        this.setCountries(countryModels);
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

    protected List<String> buildFranchiseCodeList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        List<String> franchiseCodeList = new ArrayList<String>();
        for (FranchiseInfoVo franchise : franchiseInfoVos) {
            franchiseCodeList.add(franchise.getCode());
        }

        return franchiseCodeList;
    }

    protected List<RateType> prepareRateTypes() throws Exception {
        List<RateType> rateTypes = new ArrayList<RateType>();
        rateTypes.add(new RateType(0, "DHL"));
        rateTypes.add(new RateType(1, "% Markup"));
        rateTypes.add(new RateType(2, "% Margin"));
        rateTypes.add(new RateType(3, "% Topup"));
        return rateTypes;
    }

    protected List<ServiceTypeModel> convertCustProfileServiceType2ServiceType(List<CustProfileServiceTypeModel> custProfileServiceTypeModels, String customerCode) {
        Type custProfileListType = new TypeToken<List<CustProfileServiceTypeModel>>() {
        }.getType();
        String jsonObject = GsonUtils.toGson(custProfileServiceTypeModels, custProfileListType);
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("custProfileBaseRates", "custBaseRates");
        replaceMap.put("custProfileBaseRateDetails", "custBaseRateDetails");
        replaceMap.put("customerProfileBaseRateId", "customerBaseRateId");
        replaceMap.put("profileId", "customerCode");
        jsonObject = AppUtils.replaceStringByMap(replaceMap, jsonObject);
        Type type = new TypeToken<List<ServiceTypeModel>>() {
        }.getType();
        List<ServiceTypeModel> result = GsonUtils.fromGson(jsonObject, type);
        if (result != null && result.size() > 0) {
            for (ServiceTypeModel serviceType : result) {
                // Update customer code for all customer base rate and customer
                // base rate detail.
                if (serviceType.getCustBaseRates() != null && serviceType.getCustBaseRates().size() > 0) {
                    for (CustBaseRateModel baseRate : serviceType.getCustBaseRates()) {
                        baseRate.setCustomerCode(customerCode);
                    }
                }
            }
        }
        return result;
    }

    protected void loadDHL(String customerCode) throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        // Get all service type of DHL carrier.
        List<CustProfileServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(1L);
        // Create the list of service type that will be show on the page.
        List<CustProfileServiceTypeVo> showServiceTypes = new ArrayList<CustProfileServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustProfileBaseRates(this.getProfileId(), serviceTypeVo);
            }
        }
        // Set to model.
        List<CustProfileServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, CustProfileServiceTypeModel.class);
        this.setDhl(convertCustProfileServiceType2ServiceType(serviceTypeModels, customerCode));
    }

    protected void loadDHLDom(String customerCode) throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        // Get all service type of DHL Domestic carrier.
        List<CustProfileServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(15L);
        // Create the list of service type that will be show on the page.
        List<CustProfileServiceTypeVo> showServiceTypes = new ArrayList<CustProfileServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustProfileBaseRates(this.getProfileId(), serviceTypeVo);
            }
        }
        // Set to model.
        List<CustProfileServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, CustProfileServiceTypeModel.class);
        this.setDhlDom(convertCustProfileServiceType2ServiceType(serviceTypeModels, customerCode));
    }

    protected void loadTNTDom(String customerCode) throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        // Get all service type of TNT Domestic carrier.
        List<CustProfileServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(3L);
        // Create the list of service type that will be show on the page.
        List<CustProfileServiceTypeVo> showServiceTypes = new ArrayList<CustProfileServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustProfileBaseRates(this.getProfileId(), serviceTypeVo);
            }
        }
        // Set to model.
        List<CustProfileServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, CustProfileServiceTypeModel.class);
        this.setTntDom(convertCustProfileServiceType2ServiceType(serviceTypeModels, customerCode));
        // Load orgin.
        CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
        List<CarrierZoneVo> carrierZoneVos = carrierZoneDao.getZoneByCarrier(3L);
        List<CarrierZoneModel> carrierZoneModels = ModelUtils.createListModelFromVo(carrierZoneVos, CarrierZoneModel.class);
        this.setTntDomColumns(carrierZoneModels);
    }

    protected void loadStarTrack(String customerCode) throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        // Get all service type of Star Track carrier.
        List<CustProfileServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(72L);
        // Create the list of service type that will be show on the page.
        List<CustProfileServiceTypeVo> showServiceTypes = new ArrayList<CustProfileServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustProfileBaseRates(this.getProfileId(), serviceTypeVo);
            }
        }
        // Set to model.
        List<CustProfileServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, CustProfileServiceTypeModel.class);
        this.setStarTrack(convertCustProfileServiceType2ServiceType(serviceTypeModels, customerCode));
        // Load orgin.
        CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
        List<CarrierZoneVo> carrierZoneVos = carrierZoneDao.getZoneByCarrier(72L);
        List<CarrierZoneModel> carrierZoneModels = ModelUtils.createListModelFromVo(carrierZoneVos, CarrierZoneModel.class);
        this.setStarTrackColumns(carrierZoneModels);
    }

    protected void loadTNTIntl(String customerCode) throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        // Get all service type of TNT International carrier.
        List<CustProfileServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(54L);
        // Create the list of service type that will be show on the page.
        List<CustProfileServiceTypeVo> showServiceTypes = new ArrayList<CustProfileServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustProfileBaseRates(this.getProfileId(), serviceTypeVo);
            }
        }
        // Set to model.
        List<CustProfileServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, CustProfileServiceTypeModel.class);
        this.setTntIntl(convertCustProfileServiceType2ServiceType(serviceTypeModels, customerCode));
    }

    protected void loadTollPriority(String customerCode) throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        // Get all service type of Toll Priority carrier.
        List<CustProfileServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(52L);
        // Create the list of service type that will be show on the page.
        List<CustProfileServiceTypeVo> showServiceTypes = new ArrayList<CustProfileServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : serviceTypes) {
                if (!serviceTypeVo.getAllowCarrier()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustProfileBaseRates(this.getProfileId(), serviceTypeVo);
            }
        }
        // Set to model.
        List<CustProfileServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, CustProfileServiceTypeModel.class);
        this.setTollPriority(convertCustProfileServiceType2ServiceType(serviceTypeModels, customerCode));
    }

    protected void loadTollIpec(String customerCode) throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        // Get all service type of Toll Ipec carrier.
        List<CustProfileServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(59L);
        // Create the list of service type that will be show on the page.
        List<CustProfileServiceTypeVo> showServiceTypes = new ArrayList<CustProfileServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : serviceTypes) {
                if (!serviceTypeVo.getAllowCarrier()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustProfileBaseRates(this.getProfileId(), serviceTypeVo);
            }
        }
        // Set to model.
        List<CustProfileServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, CustProfileServiceTypeModel.class);
        this.setTollIpec(convertCustProfileServiceType2ServiceType(serviceTypeModels, customerCode));
    }

    protected void loadOthers(String customerCode) throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        // Get others customer base rate by customer code.
        List<OtherCustProfileBaseRateVo> baseRateVos = dao.getOtherBaseRateByProfileId(Long.valueOf(this.getProfileId()));
        if (baseRateVos != null && baseRateVos.size() > 0) {
            for (OtherCustProfileBaseRateVo otherCustProfileBaseRateVo : baseRateVos) {
                List<RateType> rateTypes = this.prepareRateTypes();
                rateTypes.remove(3);
                rateTypes.remove(0);
                otherCustProfileBaseRateVo.setRateTypes(rateTypes);
            }
        }
        List<OtherCustProfileBaseRateModel> baseRateModels = ModelUtils.createListModelFromVo(baseRateVos, OtherCustProfileBaseRateModel.class);
        this.setOthers(convertOtherCustProfileBaseRate2OtherCustBaseRate(baseRateModels, customerCode));
    }

    protected List<OtherCustBaseRateModel> convertOtherCustProfileBaseRate2OtherCustBaseRate(List<OtherCustProfileBaseRateModel> otherCustProfileBaseRateModels, String customerCode) {
        Type otherCustProfileListType = new TypeToken<List<OtherCustProfileBaseRateModel>>() {
        }.getType();
        String jsonObject = GsonUtils.toGson(otherCustProfileBaseRateModels, otherCustProfileListType);
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("customerProfileBaseRateId", "customerBaseRateId");
        replaceMap.put("profileId", "customerCode");
        replaceMap.put("custProfileBaseRateDetails", "custBaseRateDetails");
        jsonObject = AppUtils.replaceStringByMap(replaceMap, jsonObject);
        Type type = new TypeToken<List<OtherCustBaseRateModel>>() {
        }.getType();
        List<OtherCustBaseRateModel> result = GsonUtils.fromGson(jsonObject, type);
        for (OtherCustBaseRateModel otherCustBaseRateModel : result) {
            otherCustBaseRateModel.setCustomerCode(customerCode);
        }
        return result;
    }

    protected void determineNameAndRateSheet(CustProfileServiceTypeVo serviceTypeVo) throws Exception {
        Long rateSheetId = 0L;
        Long perWeightRateSheetId = 0L;
        Long ncRateSheetId = 0L;
        Long ncPerWeightRateSheetId = 0L;
        switch (serviceTypeVo.getDocumentType()) {
            case 0: // Document
                serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Documents");
                if (serviceTypeVo.getAllowCarrier()) {
                    rateSheetId = serviceTypeVo.getCarrierDocRate();
                }
                if (serviceTypeVo.getAllowNonCarrier()) {
                    ncRateSheetId = serviceTypeVo.getNonCarrierDocRate();
                }
                break;
            case 1: // Document In-bound
                serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Documents (Inbound)");
                if (serviceTypeVo.getAllowCarrier()) {
                    rateSheetId = serviceTypeVo.getCarrierDocInRate();
                }
                if (serviceTypeVo.getAllowNonCarrier()) {
                    ncRateSheetId = serviceTypeVo.getNonCarrierDocInRate();
                }
                break;
            case 2: // Package
                serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Package");
                if (serviceTypeVo.getAllowCarrier()) {
                    rateSheetId = serviceTypeVo.getCarrierPackRate();
                    perWeightRateSheetId = serviceTypeVo.getCarrierPackPerWeightRate();
                }
                if (serviceTypeVo.getAllowNonCarrier()) {
                    ncRateSheetId = serviceTypeVo.getNonCarrierPackRate();
                    ncPerWeightRateSheetId = serviceTypeVo.getNonCarrierPackPerWeightRate();
                }
                break;
            case 3: // Package In-bound
                serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Package (Inbound)");
                if (serviceTypeVo.getAllowCarrier()) {
                    rateSheetId = serviceTypeVo.getCarrierPackInRate();
                    perWeightRateSheetId = serviceTypeVo.getCarrierPackInPerWeightRate();
                }
                if (serviceTypeVo.getAllowNonCarrier()) {
                    ncRateSheetId = serviceTypeVo.getNonCarrierPackInRate();
                    ncPerWeightRateSheetId = serviceTypeVo.getNonCarrierPackInPerWeightRate();
                }
                break;
            case 4: // Pak
                serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Pak");
                if (serviceTypeVo.getAllowCarrier()) {
                    rateSheetId = serviceTypeVo.getCarrierPakRate();
                    perWeightRateSheetId = serviceTypeVo.getCarrierPakPerWeightRate();
                }
                if (serviceTypeVo.getAllowNonCarrier()) {
                    ncRateSheetId = serviceTypeVo.getNonCarrierPakRate();
                    ncPerWeightRateSheetId = serviceTypeVo.getNonCarrierPakPerWeightRate();
                }
                break;
            case 5: // Pak In-bound
                serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName() + " - Pak (Inbound)");
                if (serviceTypeVo.getAllowCarrier()) {
                    rateSheetId = serviceTypeVo.getCarrierPakInRate();
                    perWeightRateSheetId = serviceTypeVo.getCarrierPakInPerWeightRate();
                }
                if (serviceTypeVo.getAllowNonCarrier()) {
                    ncRateSheetId = serviceTypeVo.getNonCarrierPakInRate();
                    ncPerWeightRateSheetId = serviceTypeVo.getNonCarrierPakInPerWeightRate();
                }
                break;
            case 6: // Non Type
                serviceTypeVo.setDisplayName(serviceTypeVo.getShipmentTypeName());
                if (serviceTypeVo.getAllowCarrier()) {
                    rateSheetId = serviceTypeVo.getCarrierDocRate();
                }
                if (serviceTypeVo.getAllowNonCarrier()) {
                    ncRateSheetId = serviceTypeVo.getNonCarrierDocRate();
                }
                break;
            default:
                throw new Exception("Unknown document type.");
        }
        serviceTypeVo.setRateSheetId(rateSheetId);
        serviceTypeVo.setPerWeightRateSheetId(perWeightRateSheetId);
        serviceTypeVo.setNcRateSheetId(ncRateSheetId);
        serviceTypeVo.setNcPerWeightRateSheetId(ncPerWeightRateSheetId);
    }

    protected void loadZones(CustProfileServiceTypeVo serviceTypeVo) throws Exception {
        if (serviceTypeVo.getRateSheetId() != 0) {
            ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
            List<String> zones = dao.getZonesBySheetId(serviceTypeVo.getRateSheetId());
            serviceTypeVo.setZones(zones);
        }
    }

    protected void loadCustProfileBaseRates(String customerCode, CustProfileServiceTypeVo serviceTypeVo) throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        CustProfileBaseRateVo baseRateFilter = new CustProfileBaseRateVo();
        baseRateFilter.setProfileId(Long.valueOf(this.getProfileId()));
        ;
        baseRateFilter.setShipmentTypeId(serviceTypeVo.getShipmentTypeId());
        baseRateFilter.setContent(serviceTypeVo.getContent() == -1 ? 0 : serviceTypeVo.getContent());
        baseRateFilter.setBound(serviceTypeVo.getBound());
        List<CustProfileBaseRateVo> baseRateVos = dao.getCustProfileBaseRateByFilter(baseRateFilter);
        if (baseRateVos != null && baseRateVos.size() > 0) {
            for (CustProfileBaseRateVo custProfileBaseRateVo : baseRateVos) {
                if (custProfileBaseRateVo.getZoneCheck()) {
                    // Load customer base rate detail.
                    CustProfileBaseRateDetailByFilter filter = new CustProfileBaseRateDetailByFilter();
                    filter.setCustomerProfileBaseRateId(custProfileBaseRateVo.getCustomerProfileBaseRateId());
                    filter.setZones(serviceTypeVo.getZones());
                    List<CustProfileBaseRateDetailVo> baseRateDetailVos = dao.getCustProfileBaseRateDetailByFilter(filter);
                    custProfileBaseRateVo.setCustProfileBaseRateDetails(baseRateDetailVos);
                    // Add new customer base rate detail with zone has no base
                    // rate detail.
                    for (int i = 0; i < serviceTypeVo.getZones().size(); i++) {
                        boolean isInList = false;
                        for (CustProfileBaseRateDetailVo custProfileBaseRateDetailVo : custProfileBaseRateVo.getCustProfileBaseRateDetails()) {
                            if (serviceTypeVo.getZones().get(i).equals(custProfileBaseRateDetailVo.getZone())) {
                                isInList = true;
                                break;
                            }
                        }
                        if (!isInList) {
                            CustProfileBaseRateDetailVo baseRateDetailVo = new CustProfileBaseRateDetailVo();
                            baseRateDetailVo.setCustomerProfileBaseRateId(custProfileBaseRateVo.getCustomerProfileBaseRateId());
                            baseRateDetailVo.setZone(serviceTypeVo.getZones().get(i));
                            baseRateDetailVo.setRate(0.0);
                            custProfileBaseRateVo.getCustProfileBaseRateDetails().add(i, baseRateDetailVo);
                        }
                    }
                } else {
                    // Create new customer base rate detail.
                    if (serviceTypeVo.getZones() != null && serviceTypeVo.getZones().size() > 0) {
                        List<CustProfileBaseRateDetailVo> baseRateDetailVos = new ArrayList<CustProfileBaseRateDetailVo>();
                        for (String zone : serviceTypeVo.getZones()) {
                            CustProfileBaseRateDetailVo baseRateDetailVo = new CustProfileBaseRateDetailVo();
                            baseRateDetailVo.setCustomerProfileBaseRateId(custProfileBaseRateVo.getCustomerProfileBaseRateId());
                            baseRateDetailVo.setZone(zone);
                            baseRateDetailVo.setRate(custProfileBaseRateVo.getRate());
                            baseRateDetailVos.add(baseRateDetailVo);
                        }
                        custProfileBaseRateVo.setCustProfileBaseRateDetails(baseRateDetailVos);
                    }
                }
                // Load rate types of customer base rate.
                List<RateType> rateTypes = this.prepareRateTypes();
                if (custProfileBaseRateVo.getWeight() == 0) {
                    rateTypes.remove(3); // Remove Topup rate type.
                }
                if (!serviceTypeVo.getAllowNonCarrier()) {
                    rateTypes.remove(0); // Remove DHL rate type.
                }
                custProfileBaseRateVo.setRateTypes(rateTypes);
            }
        } else {
            // Create new customer base rate.
            CustProfileBaseRateVo baseRateVo = new CustProfileBaseRateVo();
            baseRateVo.setProfileId(Long.valueOf(this.getProfileId()));
            baseRateVo.setShipmentTypeId(serviceTypeVo.getShipmentTypeId());
            baseRateVo.setRateType(0);
            baseRateVo.setWeight(0.0);
            baseRateVo.setRate(0.0);
            baseRateVo.setZoneCheck(false);
            baseRateVo.setContent(serviceTypeVo.getContent());
            baseRateVo.setBound(serviceTypeVo.getBound());
            baseRateVo.setBaserateDescription(serviceTypeVo.getDisplayName());
            baseRateVo.setCarrierId(serviceTypeVo.getServiceId());
            // Load rate types of customer base rate.
            List<RateType> rateTypes = this.prepareRateTypes();
            rateTypes.remove(3); // Remove Topup rate type.
            if (!serviceTypeVo.getAllowNonCarrier()) {
                rateTypes.remove(0); // Remove DHL rate type.
            }
            baseRateVo.setRateTypes(rateTypes);
            // Create new customer base rate detail.
            if (serviceTypeVo.getZones() != null && serviceTypeVo.getZones().size() > 0) {
                List<CustProfileBaseRateDetailVo> baseRateDetailVos = new ArrayList<CustProfileBaseRateDetailVo>();
                for (String zone : serviceTypeVo.getZones()) {
                    CustProfileBaseRateDetailVo baseRateDetailVo = new CustProfileBaseRateDetailVo();
                    baseRateDetailVo.setCustomerProfileBaseRateId(baseRateVo.getCustomerProfileBaseRateId());
                    baseRateDetailVo.setZone(zone);
                    baseRateDetailVo.setRate(baseRateVo.getRate());
                    baseRateDetailVos.add(baseRateDetailVo);
                }
                baseRateVo.setCustProfileBaseRateDetails(baseRateDetailVos);
            }
            baseRateVos.add(baseRateVo);
        }
        serviceTypeVo.setCustProfileBaseRates(baseRateVos);
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
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

    public List<CountryModel> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryModel> countries) {
        this.countries = countries;
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

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public ManageCustomerAddressModel getAddress() {
        return address;
    }

    public void setAddress(ManageCustomerAddressModel address) {
        this.address = address;
    }

    public List<ServiceTypeModel> getDhl() {
        return dhl;
    }

    public void setDhl(List<ServiceTypeModel> dhl) {
        this.dhl = dhl;
    }

    public List<ServiceTypeModel> getDhlDom() {
        return dhlDom;
    }

    public void setDhlDom(List<ServiceTypeModel> dhlDom) {
        this.dhlDom = dhlDom;
    }

    public List<ServiceTypeModel> getTntDom() {
        return tntDom;
    }

    public void setTntDom(List<ServiceTypeModel> tntDom) {
        this.tntDom = tntDom;
    }

    public List<ServiceTypeModel> getTntIntl() {
        return tntIntl;
    }

    public void setTntIntl(List<ServiceTypeModel> tntIntl) {
        this.tntIntl = tntIntl;
    }

    public List<ServiceTypeModel> getTollPriority() {
        return tollPriority;
    }

    public void setTollPriority(List<ServiceTypeModel> tollPriority) {
        this.tollPriority = tollPriority;
    }

    public List<ServiceTypeModel> getTollIpec() {
        return tollIpec;
    }

    public void setTollIpec(List<ServiceTypeModel> tollIpec) {
        this.tollIpec = tollIpec;
    }

    public List<OtherCustBaseRateModel> getOthers() {
        return others;
    }

    public void setOthers(List<OtherCustBaseRateModel> others) {
        this.others = others;
    }

    public List<CarrierZoneModel> getTntDomColumns() {
        return tntDomColumns;
    }

    public void setTntDomColumns(List<CarrierZoneModel> tntDomColumns) {
        this.tntDomColumns = tntDomColumns;
    }

    public CustomerCollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CustomerCollectionModel collection) {
        this.collection = collection;
    }

    public NoteModel getNote() {
        return note;
    }

    public void setNote(NoteModel note) {
        this.note = note;
    }

    public SaveCustomerBaseRateModel getSaveCustBaseRate() {
        return saveCustBaseRate;
    }

    public void setSaveCustBaseRate(SaveCustomerBaseRateModel saveCustBaseRate) {
        this.saveCustBaseRate = saveCustBaseRate;
    }

    public List<ServiceTypeModel> getStarTrack() {
        return starTrack;
    }

    public void setStarTrack(List<ServiceTypeModel> starTrack) {
        this.starTrack = starTrack;
    }

    public List<CarrierZoneModel> getStarTrackColumns() {
        return starTrackColumns;
    }

    public void setStarTrackColumns(List<CarrierZoneModel> starTrackColumns) {
        this.starTrackColumns = starTrackColumns;
    }
}

package com.gms.xms.weblib.controller.account.franchises;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.CryptUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.model.*;
import com.gms.xms.model.account.franchises.AddFranchiseModel;
import com.gms.xms.model.area.AreaModel;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.customergroup.CustomerGroupModel;
import com.gms.xms.model.industry.IndustryModel;
import com.gms.xms.model.invoicing.CustomerBillingAddressModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.WebshipGroupModel;
import com.gms.xms.persistence.dao.AccountServiceDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.service.area.AreaServiceImp;
import com.gms.xms.persistence.service.area.IAreaService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.customergroup.CustomerGroupServiceImp;
import com.gms.xms.persistence.service.customergroup.ICustomerGroupService;
import com.gms.xms.persistence.service.franchise.IManageFranchiseService;
import com.gms.xms.persistence.service.franchise.ManageFranchiseServiceImp;
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
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.account.franchises.AddFranchiseVo;
import com.gms.xms.txndb.vo.area.AreaVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.txndb.vo.customergroup.CustomerGroupVo;
import com.gms.xms.txndb.vo.industry.IndustryVo;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.weblib.controller.account.customers.manage.ManageFranBaseRateController;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from Apr 15, 2016 2:23:33 PM
 * <p>
 * Author huynd
 */
public class AddFranchiseController extends ManageFranBaseRateController {

    private static final long serialVersionUID = -3891363233909521226L;

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
    private CustomerCollectionModel collection;
    private NoteModel note;
    private CustomerAddressModel address;
    private CustomerBillingAddressModel billingAddress;
    private List<CustomerBaseRateModel> customerBaseRates;
    private List<FranchiseServiceMarkupModel> listServiceMarkup;

    public String load() {
        try {
            prepareFranchises();
            prepareWebshipGroupList();
            prepareAreaList();
            prepareSalesRepList();
            prepareCountryList();
            prepareInvoiceSortingOptions();
            prepareInvoiceTerms();
            prepareInvoiceToCustomers();
            preparePickupFees();
            loadFranchise();
            this.setAddress(new CustomerAddressModel());
            CustomerBillingAddressModel billingAddressModel = new CustomerBillingAddressModel();
            billingAddressModel.setBillingSameWithCustomer("true");
            this.setBillingAddress(billingAddressModel);
            loadBaseRates();
            loadCollection();
            this.setNote(new NoteModel());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            // Validate franchise info.
            if (this.getFranchise() == null || this.getAddress() == null || this.getCustomerBaseRates() == null || this.getCollection() == null) {
                throw new CustomException("Please input all franchise information for saving.");
            }
            boolean isValid = true;
            isValid = isValid && isValidFranchise(this.getFranchise());
            isValid = isValid && isValidAddress(this.getAddress());
            isValid = isValid && isValidBillingAddress(this.getBillingAddress());
            isValid = isValid && isValidCollection(this.getCollection());
            if (!isValid) {
                setErrorCode(ErrorCode.FIELD_ERROR);
            } else {
                // Check duplicate franchise code.
                if (checkDuplicate(this.getFranchise().getFranchiseCode())) {
                    throw new CustomException("This franchise already existed. Please input an other franchise code.");
                }
                // Prepare add franchise model for saving.
                AddFranchiseVo addFranchiseVo = prepareAddFranchise4Saving();
                // Do save franchise.
                IManageFranchiseService franchiseService = new ManageFranchiseServiceImp();
                franchiseService.addFranchise(this.getAddInfoMap(), addFranchiseVo);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean checkDuplicate(String franchiseCode) throws Exception {
        FranchiseDao franchiseDao = new FranchiseDao();
        FranchiseVo franchiseVo = franchiseDao.selectFranchiseByFranchiseCode(franchiseCode);
        return franchiseVo != null;
    }

    protected AddFranchiseVo prepareAddFranchise4Saving() throws Exception {
        AddFranchiseModel addFranchise = new AddFranchiseModel();
        FranchiseModel franchise = this.getFranchise();
        franchise.setFranchiseCode(franchise.getFranchiseCode() + "00001");
        // Update create date and create user id.
        franchise.setCreateDate(DateUtils.convertDateToString(new Date(), AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null));
        franchise.setCreatedUserid((String) this.getSession("SESS_XMS_ADMIN_ID"));
        franchise.setAdminFunction("false");
        franchise.setWebshipAdminid("0");
        addFranchise.setFranchise(franchise);
        // Update franchise address.
        CustomerAddressModel address = this.getAddress();
        // Upper case some customer address info.
        address.setCustomerName(StringUtils.isBlank(address.getCustomerName()) ? "" : address.getCustomerName().toUpperCase());
        address.setContactName(StringUtils.isBlank(address.getContactName()) ? "" : address.getContactName().toUpperCase());
        address.setContactTitle(StringUtils.isBlank(address.getContactTitle()) ? "" : address.getContactTitle().toUpperCase());
        address.setAddress1(StringUtils.isBlank(address.getAddress1()) ? "" : address.getAddress1().toUpperCase());
        address.setAddress2(StringUtils.isBlank(address.getAddress2()) ? "" : address.getAddress2().toUpperCase());
        address.setCity(StringUtils.isBlank(address.getCity()) ? "" : address.getCity().toUpperCase());
        address.setPostalCode(StringUtils.isBlank(address.getPostalCode()) ? "" : address.getPostalCode().toUpperCase());
        address.setStateCode(StringUtils.isBlank(address.getStateCode()) ? "" : address.getStateCode().toUpperCase());
        address.setOwner(StringUtils.isBlank(address.getOwner()) ? "" : address.getOwner().toUpperCase());
        address.setApContact(StringUtils.isBlank(address.getApContact()) ? "" : address.getApContact().toUpperCase());
        address.setOtherContact(StringUtils.isBlank(address.getOtherContact()) ? "" : address.getOtherContact().toUpperCase());
        address.setOther2Contact(StringUtils.isBlank(address.getOther2Contact()) ? "" : address.getOther2Contact().toUpperCase());
        address.setCustomerCode(franchise.getFranchiseCode());
        address.setUserType("2"); // Create franchise so user type is 2.
        addFranchise.setAddress(address);
        // Update franchise billing address.
        CustomerBillingAddressModel billingAddress = this.getBillingAddress();
        // Upper case some customer billing address info.
        billingAddress.setBillingCustomerName(StringUtils.isBlank(billingAddress.getBillingCustomerName()) ? "" : billingAddress.getBillingCustomerName().toUpperCase());
        billingAddress.setBillingContactName(StringUtils.isBlank(billingAddress.getBillingContactName()) ? "" : billingAddress.getBillingContactName().toUpperCase());
        billingAddress.setBillingContactTitle(StringUtils.isBlank(billingAddress.getBillingContactTitle()) ? "" : billingAddress.getBillingContactTitle().toUpperCase());
        billingAddress.setBillingAddress1(StringUtils.isBlank(billingAddress.getBillingAddress1()) ? "" : billingAddress.getBillingAddress1().toUpperCase());
        billingAddress.setBillingAddress2(StringUtils.isBlank(billingAddress.getBillingAddress2()) ? "" : billingAddress.getBillingAddress2().toUpperCase());
        billingAddress.setBillingCity(StringUtils.isBlank(billingAddress.getBillingCity()) ? "" : billingAddress.getBillingCity().toUpperCase());
        billingAddress.setBillingPostalCode(StringUtils.isBlank(billingAddress.getBillingPostalCode()) ? "" : billingAddress.getBillingPostalCode().toUpperCase());
        billingAddress.setBillingStateCode(StringUtils.isBlank(billingAddress.getBillingStateCode()) ? "" : billingAddress.getBillingStateCode().toUpperCase());
        billingAddress.setCustomerCode(franchise.getFranchiseCode());
        billingAddress.setUsertype("2");
        addFranchise.setBillingAddress(billingAddress);
        // Get customer base rates.
        List<CustomerBaseRateModel> customerBaseRates = this.getCustomerBaseRates();
        if (customerBaseRates != null && customerBaseRates.size() > 0) {
            for (CustomerBaseRateModel baseRate : customerBaseRates) {
                baseRate.setCustomerCode(franchise.getFranchiseCode());
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
        addFranchise.setCustomerBaseRates(customerBaseRates);
        // Get customer collection.
        CustomerCollectionModel collection = this.getCollection();
        collection.setUserType("2");
        addFranchise.setCollection(collection);
        // Update note.
        NoteModel note = this.getNote();
        if (StringUtils.isBlank(note.getNote())) {
            note = null;
        } else {
            note.setAccountNo(franchise.getFranchiseCode());
            note.setNoteType("2"); // Note type for franchise is 2.
            note.setCheck("false");
            note.setCurrent("0");
            note.setInvoiceCode("");
            note.setModifyDate(DateUtils.convertDateToString(new Date(), AppConstants.APP_SETTINGS.getDefaultDateTimeFormat(), null));
            note.setPaymentId("0");
            note.setUserId((String) this.getSession("SESS_XMS_ADMIN_ID"));
        }
        addFranchise.setNote(note);
        addFranchise.setListServiceMarkup(this.getListServiceMarkup());
        // Convert model to vo.
        AddFranchiseVo addFranchiseVo = ModelUtils.createVoFromModel(addFranchise, AddFranchiseVo.class);
        // If inactive=0 then active date is current date.
        if (!addFranchiseVo.getFranchise().getInactive()) {
            addFranchiseVo.getFranchise().setActivateDate(new Date());
        }
        String franchiseCode = String.valueOf(addFranchiseVo.getFranchise().getFranchiseCode());
        // Add Webship User.
        WebshipVo webshipVo = new WebshipVo();
        webshipVo.setCustomerCode(addFranchiseVo.getFranchise().getFranchiseCode());
        webshipVo.setName(franchiseCode);
        String encryptionKey = AppConstants.APP_SETTINGS.getEncryptionKey();
        String webshipPassword = AppConstants.APP_SETTINGS.getDefaultWebshipPassword();
        webshipVo.setPassword(CryptUtils.Encrypt(webshipPassword, encryptionKey));
        webshipVo.setCreateDate(new Date());
        webshipVo.setLanguage(0);
        webshipVo.setAllowExportAddressBook(true);
        webshipVo.setIsRequireChangePassword(true);
        webshipVo.setResetPasswordStatus(0);
        addFranchiseVo.setWebship(webshipVo);
        // Add account service setting for this customer.
        AccountServiceDao accountServiceDao = new AccountServiceDao();
        List<AccountServiceVo> accountServiceVos = accountServiceDao.selectActiveServiceByFranchiseCode(franchiseCode);
        if (accountServiceVos != null && accountServiceVos.size() > 0) {
            for (AccountServiceVo accountServiceVo : accountServiceVos) {
                accountServiceVo.setCustomerCode(addFranchiseVo.getFranchise().getFranchiseCode());
                accountServiceVo.setUserType(1);
            }
        }
        addFranchiseVo.setAccountServices(accountServiceVos);
        return addFranchiseVo;
    }

    protected boolean isValidFranchise(FranchiseModel franchise) {
        if (!StringUtils.isNumeric(franchise.getFranchiseCode())) {
            addFieldError("franchise.franchiseCode", "Franchise Code Is Not Number.");
        }
        if (StringUtils.isBlank(franchise.getFranchiseTerritory())) {
            addFieldError("franchise.franchiseTerritory", "Franchise Territory Is Not Blank.");
        }
        if (StringUtils.isBlank(franchise.getGstid())) {
            addFieldError("franchise.gstid", "GST # is required (default=0)");
        } else {
            Integer gstNumber = 0;
            try {
                gstNumber = Integer.valueOf(franchise.getGstid());
                if (gstNumber < 0) {
                    addFieldError("franchise.gstid", "GST # must be greater than 0");
                }
            } catch (Exception e) {
                addFieldError("franchise.gstid", "GST # must be a integer number");
            }
        }
        return !hasFieldErrors();
    }

    protected boolean isValidAddress(CustomerAddressModel address) {
        if (StringUtils.isBlank(address.getCustomerName())) {
            addFieldError("address.customerName", "Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(address.getContactName())) {
            addFieldError("address.contactName", "Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(address.getAddress1())) {
            addFieldError("address.address1", "Address cannot be empty.");
        }
        if (StringUtils.isBlank(address.getCity())) {
            addFieldError("address.city", "City cannot be empty.");
        }
        if (StringUtils.isBlank(address.getCountry())) {
            addFieldError("address.country", "Please select a country.");
        } else {
            if ("0".equalsIgnoreCase(address.getCountry())) {
                addFieldError("address.country", "Please select a country.");
            }
        }
        if (StringUtils.isBlank(address.getPhone())) {
            addFieldError("address.phone", "Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(address.getEmail())) {
            if (!EmailUtils.isValidEmail(address.getEmail())) {
                addFieldError("address.email", "Invalid email address.");
            }
        }
        if (!StringUtils.isBlank(address.getOwnerEmail())) {
            if (!EmailUtils.isValidEmail(address.getOwnerEmail())) {
                addFieldError("address.ownerEmail", "Invalid owner email address.");
            }
        }
        if (!StringUtils.isBlank(address.getApEmail())) {
            if (!EmailUtils.isValidEmail(address.getApEmail())) {
                addFieldError("address.apEmail", "Invalid AP email address.");
            }
        }
        if (!StringUtils.isBlank(address.getOtherEmail())) {
            if (!EmailUtils.isValidEmail(address.getOtherEmail())) {
                addFieldError("address.otherEmail", "Invalid other email address.");
            }
        }
        if (!StringUtils.isBlank(address.getOther2Email())) {
            if (!EmailUtils.isValidEmail(address.getOther2Email())) {
                addFieldError("address.other2Email", "Invalid other 2 email address.");
            }
        }
        return !hasFieldErrors();
    }

    protected boolean isValidBillingAddress(CustomerBillingAddressModel billingAddress) {
        if (StringUtils.isBlank(billingAddress.getBillingCustomerName())) {
            addFieldError("billingAddress.billingCustomerName", "Billing Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(billingAddress.getBillingContactName())) {
            addFieldError("billingAddress.billingContactName", "Billing Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(billingAddress.getBillingAddress1())) {
            addFieldError("billingAddress.billingAddress1", "Billing Address cannot be empty.");
        }
        if (StringUtils.isBlank(billingAddress.getBillingCity())) {
            addFieldError("billingAddress.billingCity", "Billing City cannot be empty.");
        }
        if (StringUtils.isBlank(billingAddress.getBillingCountry())) {
            addFieldError("billingAddress.billingCountry", "Please select a billing country.");
        } else {
            if ("0".equalsIgnoreCase(billingAddress.getBillingCountry())) {
                addFieldError("billingAddress.billingCountry", "Please select a billing country.");
            }
        }
        if (StringUtils.isBlank(billingAddress.getBillingPhone())) {
            addFieldError("billingAddress.billingPhone", "Billing Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(billingAddress.getBillingEmail())) {
            if (!EmailUtils.isValidEmail(billingAddress.getBillingEmail())) {
                addFieldError("billingAddress.billingEmail", "Invalid billing email address.");
            }
        }
        return !hasFieldErrors();
    }

    protected boolean isValidCollection(CustomerCollectionModel collection) {
        if (StringUtils.isBlank(collection.getFreightCreditLimit())) {
            addFieldError("collection.freightCreditLimit", "Freight Credit Limit cannot leave blank");
        } else {
            try {
                Double freight = Double.valueOf(collection.getFreightCreditLimit());
                if (freight < 0) {
                    addFieldError("collection.freightCreditLimit", "Freight Credit Limit must be greater than zero");
                }
            } catch (Exception e) {
                addFieldError("collection.freightCreditLimit", "Freight Credit Limit must be a number");
            }
        }
        if (!StringUtils.isBlank(collection.getReminderEmailAddress()) && !EmailUtils.isValidEmail(collection.getReminderEmailAddress())) {
            addFieldError("collection.reminderEmailAddress", "Invalid reminder email address");
        }
        return !hasFieldErrors();
    }

    private void loadFranchise() throws Exception {
        FranchiseVo franchiseVo = new FranchiseVo();
        franchiseVo.setDhlInternationalAccount("3p");
        franchiseVo.setDhlInboundAccount("3p");
        franchiseVo.setDhlDomesticAccount("3p");
        franchiseVo.setInactive(false);
        franchiseVo.setExcludeFromAll(false);
        franchiseVo.setGroupId(0);
        franchiseVo.setWebshipGroupid(0);
        franchiseVo.setAreaId(0);
        franchiseVo.setSalesRepId(0L);
        franchiseVo.setRegistrationid("0");
        franchiseVo.setGstid(0);
        franchiseVo.setBookingEmailNotification(false);
        franchiseVo.setProfitShare(0.0);
        franchiseVo.setInvoicingCharge(false);
        franchiseVo.setMarkupRate(0.0);
        franchiseVo.setDhlDomMarkupRate(0.0);
        franchiseVo.setTntMarkupRate(0.0);
        franchiseVo.setTntInternationalMarkupRate(0.0);
        franchiseVo.setTollMarkupRate(0.0);
        franchiseVo.setTollIpecMarkupRate(0.0);
        franchiseVo.setMinimunBaseCharge(0.0);
        franchiseVo.setInvoiceSorting(0);
        franchiseVo.setInvoiceTerms(0);
        franchiseVo.setInvoiceToCustomerid(0L);
        franchiseVo.setPickupFee(0);
        franchiseVo.setInvoiceLateFee(0.0);
        franchiseVo.setDownloadCsvInvoice(false);
        franchiseVo.setEmailInvoice(false);
        franchiseVo.setOvernight(0.0);
        franchiseVo.setNextAfternoon(0.0);
        franchiseVo.setSecondDay(0.0);
        franchiseVo.setGround(0.0);
        franchiseVo.setIntlOutbound(0.0);
        franchiseVo.setIntlInbound(0.0);
        franchiseVo.setOther(0.0);
        franchiseVo.setExpressPerAirbill(0.0);
        franchiseVo.setFreightPerAirbill(0.0);
        franchiseVo.setInvoicingFee(0.0);
        franchiseVo.setCharge1Amount(0.0);
        franchiseVo.setCharge2Amount(0.0);
        franchiseVo.setCharge3Amount(0.0);
        franchiseVo.setSwMaintenance(0.0);
        franchiseVo.setSwDevelopment(0.0);
        franchiseVo.setMarketing(0.0);
        franchiseVo.setWebship(0.0);
        FranchiseModel franchiseModel = ModelUtils.createModelFromVo(franchiseVo, FranchiseModel.class);
        this.setFranchise(franchiseModel);
    }

    protected void loadBaseRates() throws Exception {
        this.setFranchiseCode("0");
        loadDHL();
        loadDHLDom();
        loadTNTDom();
        loadTNTIntl();
        loadTollPriority();
        loadTollIpec();
        loadStarTrack();
        loadOthers();
    }

    protected void loadCollection() throws Exception {
        CustomerCollectionVo collectionVo = new CustomerCollectionVo();
        collectionVo.setFreightCreditLimit(0.0);
        collectionVo.setReminder(false);
        collectionVo.setReminderEmail(false);
        collectionVo.setReminderPrint(true);
        collectionVo.setReminderUseEmailInvoice(true);
        CustomerCollectionModel collectionModel = ModelUtils.createModelFromVo(collectionVo, CustomerCollectionModel.class);
        this.setCollection(collectionModel);
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
        List<UserVo> userVos = iUserService.getSaleReps(this.buildFranchiseList("All"));
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

    public CustomerAddressModel getAddress() {
        return address;
    }

    public void setAddress(CustomerAddressModel address) {
        this.address = address;
    }

    public CustomerBillingAddressModel getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(CustomerBillingAddressModel billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<CustomerBaseRateModel> getCustomerBaseRates() {
        return customerBaseRates;
    }

    public void setCustomerBaseRates(List<CustomerBaseRateModel> customerBaseRates) {
        this.customerBaseRates = customerBaseRates;
    }

    public List<FranchiseServiceMarkupModel> getListServiceMarkup() {
        return listServiceMarkup;
    }

    public void setListServiceMarkup(List<FranchiseServiceMarkupModel> listServiceMarkup) {
        this.listServiceMarkup = listServiceMarkup;
    }
}

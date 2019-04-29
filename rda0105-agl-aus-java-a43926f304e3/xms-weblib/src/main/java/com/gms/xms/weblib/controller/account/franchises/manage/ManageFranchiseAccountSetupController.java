package com.gms.xms.weblib.controller.account.franchises.manage;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.EmailUtils;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.model.*;
import com.gms.xms.model.account.customers.manage.ManageCustomerAddressModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerBaseRateModel;
import com.gms.xms.model.area.AreaModel;
import com.gms.xms.model.customer.CustomerCollectionModel;
import com.gms.xms.model.customergroup.CustomerGroupModel;
import com.gms.xms.model.industry.IndustryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.WebshipGroupModel;
import com.gms.xms.persistence.dao.franchise.FranchiseServiceMarkupDao;
import com.gms.xms.persistence.service.area.AreaServiceImp;
import com.gms.xms.persistence.service.area.IAreaService;
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
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerAddressVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerBaseRateVo;
import com.gms.xms.txndb.vo.area.AreaVo;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.txndb.vo.customergroup.CustomerGroupVo;
import com.gms.xms.txndb.vo.industry.IndustryVo;
import com.gms.xms.txndb.vo.webship.WebshipGroupVo;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name: ManageFranchiseAccountSetupController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 18-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseAccountSetupController
 */
public class ManageFranchiseAccountSetupController extends ManageFranchiseController {
    private static final long serialVersionUID = -7110570240797000983L;
    protected FranchiseModel franchise;
    private List<ServiceModel> services;
    private List<CustomerGroupModel> customerGroups;
    private List<WebshipGroupModel> webshipGroups;
    private List<IndustryModel> industries;
    private List<AreaModel> areas;
    private List<UserModel> salesReps;
    private List<UserModel> collectors;
    protected String franchiseCode;
    private NoteModel note;
    private List<FranchiseServiceMarkupModel> listServiceMarkup;

    // Collection Tab Data
    private CustomerCollectionModel collectionModel;
    // Address Tab Data
    private ManageCustomerAddressModel customer;
    // BaseRate Tab Data
    private ManageCustomerBaseRateModel baseRate;

    public String accountSetupIndex() {
        try {
            if (prepareFranchiseDetail()) {
                prepareServiceList();
                prepareCustomerGroupList();
                prepareWebshipGroupList();
                prepareIndustryList();
                prepareAreaList();
                prepareSalesRepList();
                prepareCollectorList();
                prepareListFranchise();
                return "success";
            } else {
                return "error";
            }

        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    protected boolean prepareFranchiseDetail() throws Exception {
        if (StringUtils.isNotEmpty(franchiseCode)) {
            if (franchiseCode.equals("0")) {
                FranchiseModel franchiseN = new FranchiseModel();
                franchiseN.setRegistrationid("0");
                franchiseN.setDhlAccount("3p");
                franchiseN.setTntAccount("3p");
                franchiseN.setTollPriorityAccount("3p");
                franchiseN.setWebshipGroupid("0");
                franchiseN.setInvoiceTerms("3");
                franchiseN.setGstid("0");
                franchiseN.setEmailInvoice("1");
                franchiseN.setInvoiceLateFee("0");
                franchiseN.setInvoiceSorting("0.00");
                this.setFranchise(franchiseN);
                this.setFranchiseCode(franchiseCode);
            } else {
                IFranchiseService service = new FranchiseServiceImp();
                ManageFranchiseFilter filter = new ManageFranchiseFilter();
                filter.setFranchiseCode(Long.parseLong(franchiseCode));
                this.setFranchise(ModelUtils.createModelFromVo(service.selectFranchiseByFilter(filter), FranchiseModel.class));

                FranchiseServiceMarkupDao franchiseServiceMarkupDao = new FranchiseServiceMarkupDao();
                List<FranchiseServiceMarkupVo> franchiseServiceMarkupVos = franchiseServiceMarkupDao.selectFranchiseServiceMarkupByFranchiseCode(Long.parseLong(this.getFranchiseCode()));
                List<FranchiseServiceMarkupModel> franchiseServiceMarkupModels = ModelUtils.createListModelFromVo(franchiseServiceMarkupVos, FranchiseServiceMarkupModel.class);
                this.setListServiceMarkup(franchiseServiceMarkupModels);
            }
        } else {
            setErrorMessage("Please enter franchise name");
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("Please enter franchise name");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    @Override
    public String save() {
        try {
            if (validFranchiseInfo()) {
                IFranchiseService franchiseService = new FranchiseServiceImp();
                if (!franchiseCode.equals("0")) {
                    FranchiseVo franchiseVo = new FranchiseVo();
                    ManageCustomerAddressVo customerVo = new ManageCustomerAddressVo();
                    CustomerCollectionVo collectionVo = new CustomerCollectionVo();
                    ManageCustomerBaseRateVo customerBaseRateVo = new ManageCustomerBaseRateVo();
                    franchiseVo = ModelUtils.createVoFromModel(franchise, FranchiseVo.class);
                    if (customer != null) {
                        customerVo = ModelUtils.createVoFromModel(customer, ManageCustomerAddressVo.class);
                    }
                    if (collectionModel != null) {
                        collectionVo = ModelUtils.createVoFromModel(collectionModel, CustomerCollectionVo.class);
                    }
                    if (baseRate != null) {
                        customerBaseRateVo = ModelUtils.createVoFromModel(baseRate, ManageCustomerBaseRateVo.class);
                    }
                    franchiseService.updateManageFranchise(this.getAddInfoMap(), franchiseVo, customerVo, collectionVo, customerBaseRateVo);
                } else {
                    FranchiseVo franchiseVo = new FranchiseVo();
                    franchiseVo = ModelUtils.createVoFromModel(franchise, FranchiseVo.class);
                    franchiseVo = prepareFranchiseVo(franchiseVo);
                    if (!hasFieldErrors()) {
                        String franchiseCodeRp = String.valueOf(franchiseVo.getFranchiseCode());
                        franchiseCodeRp = franchiseCodeRp.concat("00001");
                        franchiseVo.setFranchiseCode(Long.parseLong(franchiseCodeRp));
                        ManageCustomerAddressVo customerVo = new ManageCustomerAddressVo();
                        CustomerCollectionVo collectionVo = new CustomerCollectionVo();
                        ManageCustomerBaseRateVo customerBaseRateVo = new ManageCustomerBaseRateVo();
                        NoteVo noteVo = new NoteVo();
                        if (customer != null) {
                            customerVo = ModelUtils.createVoFromModel(customer, ManageCustomerAddressVo.class);
                        }
                        if (collectionModel != null) {
                            collectionVo = ModelUtils.createVoFromModel(collectionModel, CustomerCollectionVo.class);
                        }
                        if (baseRate != null) {
                            customerBaseRateVo = ModelUtils.createVoFromModel(baseRate, ManageCustomerBaseRateVo.class);
                        }
                        if (note != null) {
                            noteVo = ModelUtils.createVoFromModel(note, NoteVo.class);
                        }

                        franchiseService.insertManageFranchise(this.getAddInfoMap(), franchiseVo, customerVo, collectionVo, customerBaseRateVo, noteVo);
                        prepareListFranchise();
                        this.setFranchiseCode(String.valueOf(franchiseVo.getFranchiseCode()));
                        return "createSuccess";
                    } else {
                        setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }
                }
                addActionMessage("Saved successfully");
            } else {
                setErrorCode(ErrorCode.FIELD_ERROR);
                return "success";
            }
        } catch (Exception e) {
            addActionError(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    protected FranchiseVo prepareFranchiseVo(FranchiseVo franchiseVo) throws Exception {
        if (franchiseVo.getFranchiseCode() == null || StringUtils.isEmpty(String.valueOf(franchiseVo.getFranchiseCode()))) {
            addFieldError("franchiseVo.franchiseCode", "Franchise Code is Not Empty");
        } else {
            String franchiseCodeS = String.valueOf(franchiseVo.getFranchiseCode());
            if (franchiseCodeS.length() > 3 && franchiseCode.equals("0")) {
                addFieldError("franchiseVo.franchiseCode", "Franchise Code Is Not Valid ");
            } else {
                IFranchiseService service = new FranchiseServiceImp();
                FranchiseVo franchiseVoC = new FranchiseVo();
                franchiseVoC = service.selectFranchiseByFranchiseCodeExt(franchiseCodeS);
                if (franchiseVoC != null) {
                    addFieldError("franchiseVo.franchiseCode", "Franchise Code is exis in system");
                }

            }
        }
        if (franchiseVo.getFranchiseTerritory() == null || StringUtils.isEmpty(franchiseVo.getFranchiseTerritory())) {
            addFieldError("franchiseVo.franchiseTerritory", "franchiseTerritory is Not Empty");
        }
        if (franchiseVo.getDhlInternationalAccount() == null || StringUtils.isEmpty(franchiseVo.getDhlInternationalAccount())) {
            franchiseVo.setDhlInternationalAccount("");
        }
        if (franchiseVo.getDhlInboundAccount() == null || StringUtils.isEmpty(franchiseVo.getDhlInboundAccount())) {
            franchiseVo.setDhlInboundAccount("");
        }
        if (franchiseVo.getDhlDomesticAccount() == null || StringUtils.isEmpty(franchiseVo.getDhlDomesticAccount())) {
            franchiseVo.setDhlDomesticAccount("");
        }
        if (franchiseVo.getMarkupRate() == null) {
            franchiseVo.setMarkupRate(0D);
        }
        if (franchiseVo.getMinimunBaseCharge() == null) {
            franchiseVo.setMinimunBaseCharge(0D);
        }
        if (franchiseVo.getInvoiceSorting() == null) {
            franchiseVo.setInvoiceSorting(0);
        }
        if (franchiseVo.getInvoiceTerms() == null) {
            franchiseVo.setInvoiceTerms(0);
        }
        if (franchiseVo.getInvoiceToCustomerid() == null) {
            franchiseVo.setInvoiceToCustomerid(0L);
        }

        if (franchiseVo.getPickupFee() == null) {
            franchiseVo.setPickupFee(0);
        }
        if (franchiseVo.getGroupId() == null) {
            franchiseVo.setGroupId(0);
        }
        if (franchiseVo.getInvoicingFee() == null) {
            franchiseVo.setInvoicingFee(0D);
        }
        if (franchiseVo.getSwMaintenance() == null) {
            franchiseVo.setSwMaintenance(0.0);
        }
        if (franchiseVo.getInvoiceLateFee() == null) {
            franchiseVo.setInvoiceLateFee(0D);
        }

        if (franchiseVo.getEmailInvoice() == null) {
            franchiseVo.setEmailInvoice(false);
        }

        if (franchiseVo.getDownloadCsvInvoice() == null) {
            franchiseVo.setDownloadCsvInvoice(false);
        }
        if (franchiseVo.getOvernight() == null) {
            franchiseVo.setOvernight(0D);
        }
        if (franchiseVo.getNextAfternoon() == null) {
            franchiseVo.setNextAfternoon(0D);
        }
        if (franchiseVo.getSecondDay() == null) {
            franchiseVo.setSecondDay(0D);
        }
        if (franchiseVo.getGround() == null) {
            franchiseVo.setGround(0D);
        }
        if (franchiseVo.getIntlOutbound() == null) {
            franchiseVo.setIntlOutbound(0D);
        }
        if (franchiseVo.getIntlInbound() == null) {
            franchiseVo.setIntlInbound(0D);
        }
        if (franchiseVo.getOther() == null) {
            franchiseVo.setOther(0D);
        }
        if (franchiseVo.getExpressPerAirbill() == null) {
            franchiseVo.setExpressPerAirbill(0D);
        }
        if (franchiseVo.getFreightPerAirbill() == null) {
            franchiseVo.setFreightPerAirbill(0D);
        }
        if (franchiseVo.getCharge1() == null) {
            franchiseVo.setCharge1("");
        }
        if (franchiseVo.getCharge2() == null) {
            franchiseVo.setCharge2("");
        }
        if (franchiseVo.getCharge3() == null) {
            franchiseVo.setCharge3("");
        }
        if (franchiseVo.getCharge1Amount() == null) {
            franchiseVo.setCharge1Amount(0.0);
        }
        if (franchiseVo.getCharge2Amount() == null) {
            franchiseVo.setCharge2Amount(0.0);
        }
        if (franchiseVo.getCharge3Amount() == null) {
            franchiseVo.setCharge3Amount(0.0);
        }
        if (franchiseVo.getSwCollectionServiceFee() == null) {
            franchiseVo.setSwCollectionServiceFee(0.0);
        }
        if (franchiseVo.getSwDevelopment() == null) {
            franchiseVo.setSwDevelopment(0.0);
        }
        if (franchiseVo.getMarketing() == null) {
            franchiseVo.setMarketing(0.0);
        }
        if (franchiseVo.getWebship() == null) {
            franchiseVo.setWebship(0.0);
        }
        if (franchiseVo.getAdminFunction() == null) {
            franchiseVo.setAdminFunction(false);
        }
        if (franchiseVo.getWebshipAdminid() == null) {
            franchiseVo.setWebshipAdminid(0L);
        }
        if (franchiseVo.getDhlDomesticAccount() == null) {
            franchiseVo.setDhlDomesticAccount("");
        }
        if (franchiseVo.getAreaId() == null) {
            franchiseVo.setAreaId(0);
        }
        if (franchiseVo.getCreatedUserid() == null) {
            franchiseVo.setCreatedUserid(0L);
        }
        if (franchiseVo.getProfitShare() == null) {
            franchiseVo.setProfitShare(0.0);
        }
        if (franchiseVo.getProfitShare() == null) {
            franchiseVo.setProfitShare(0.0);
        }
        if (franchiseVo.getBookingEmailNotification() == null) {
            franchiseVo.setBookingEmailNotification(false);
        }
        if (franchiseVo.getTntInternationalMarkupRate() == null) {
            franchiseVo.setTntInternationalMarkupRate(0D);
        }
        if (franchiseVo.getInvoicingCharge() == null) {
            franchiseVo.setInvoicingCharge(false);
        }
        if (franchiseVo.getExcludeFromAll() == null) {
            franchiseVo.setExcludeFromAll(false);
        }
        if (franchiseVo.getManagementMargin() == null) {
            franchiseVo.setManagementMargin(0.0);
        }
        if (franchiseVo.getInternationalShipmentFee() == null) {
            franchiseVo.setInternationalShipmentFee(0.0);
        }
        if (franchiseVo.getDomesticShipmentFee() == null) {
            franchiseVo.setDomesticShipmentFee(0.0);
        }
        if (franchiseVo.getPrintingFee() == null) {
            franchiseVo.setPrintingFee(0.0);
        }
        if (franchiseVo.getPostageFee() == null) {
            franchiseVo.setPostageFee(0.0);
        }
        if (franchiseVo.getTollIpecMarkupRate() == null) {
            franchiseVo.setTollIpecMarkupRate(0D);
        }
        if (franchiseVo.getStartrackMarkupRate() == null) {
            franchiseVo.setStartrackMarkupRate(0D);
        }
        if (franchiseVo.getUpsMarkupRate() == null) {
            franchiseVo.setUpsMarkupRate(0D);
        }
        return franchiseVo;
    }

    protected boolean validFranchiseInfo() throws Exception {
        this.validAccountSetup();
        if (customer != null) {
            this.isValidCustomerAddressInfo();
        }
        if (collectionModel != null) {
            this.validCustomerCollection();
        }
        if (baseRate != null) {
            this.validBaseRate();
        }
        return !hasFieldErrors();
    }

    protected void validBaseRate() throws Exception {
        // Valid BaseRate
    }

    protected boolean validCustomerCollection() {
        if (StringUtils.isBlank(collectionModel.getFreightCreditLimit())) {
            addFieldError("customerCollection.freightCreditLimit", "Freight Credit Limit cannot leave blank");
        } else {
            try {
                Double freight = Double.valueOf(collectionModel.getFreightCreditLimit());
                if (freight < 0) {
                    addFieldError("customerCollection.freightCreditLimit", "Freight Credit Limit must be greater than zero");
                }
            } catch (Exception e) {
                addFieldError("customerCollection.freightCreditLimit", "Freight Credit Limit must be a number");
            }
        }
        if (!StringUtils.isBlank(collectionModel.getReminderEmailAddress()) && !EmailUtils.isValidEmail(collectionModel.getReminderEmailAddress())) {
            addFieldError("customerCollection.reminderEmailAddress", "Invalid reminder email address");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void validAccountSetup() {
        if (franchise == null) {
            addFieldError("franchise", "Invalid Franchise account setup information");
        }
        if (!StringUtils.isNumeric(franchise.getFranchiseCode())) {
            addFieldError("franchise.franchiseCode", "Franchise Code Is Not Number.");
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
    }

    private void isValidCustomerAddressInfo() {
        if (customer.getAddress().getUserType() == null) {
            customer.getAddress().setUserType("0");
        }
        if (StringUtils.isBlank(customer.getAddress().getCustomerName())) {
            addFieldError("customer.address.customerName", "Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getContactName())) {
            addFieldError("customer.address.contactName", "Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getAddress1())) {
            addFieldError("customer.address.address1", "Address cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getCity())) {
            addFieldError("customer.address.city", "City cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getAddress().getCountry())) {
            addFieldError("customer.address.country", "Please select a country.");
        } else {
            if ("0".equalsIgnoreCase(customer.getAddress().getCountry())) {
                addFieldError("customer.address.country", "Please select a country.");
            }
        }
        if (StringUtils.isBlank(customer.getAddress().getPhone())) {
            addFieldError("customer.address.phone", "Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customer.getAddress().getEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getEmail())) {
                addFieldError("customer.address.email", "Invalid email address.");
            }
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCustomerName())) {
            addFieldError("customer.billingAddress.billingCustomerName", "Billing Customer Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingContactName())) {
            addFieldError("customer.billingAddress.billingContactName", "Billing Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingAddress1())) {
            addFieldError("customer.billingAddress.billingAddress1", "Billing Address cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCity())) {
            addFieldError("customer.billingAddress.billingCity", "Billing City cannot be empty.");
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingCountry())) {
            addFieldError("customer.billingAddress.billingCountry", "Please select a billing country.");
        } else {
            if ("0".equalsIgnoreCase(customer.getBillingAddress().getBillingCountry())) {
                addFieldError("customer.billingAddress.billingCountry", "Please select a billing country.");
            }
        }
        if (StringUtils.isBlank(customer.getBillingAddress().getBillingPhone())) {
            addFieldError("customer.billingAddress.billingPhone", "Billing Phone cannot be empty.");
        }
        if (!StringUtils.isBlank(customer.getBillingAddress().getBillingEmail())) {
            if (!EmailUtils.isValidEmail(customer.getBillingAddress().getBillingEmail())) {
                addFieldError("customer.billingAddress.billingEmail", "Invalid billing email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOwnerEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOwnerEmail())) {
                addFieldError("customer.address.ownerEmail", "Invalid owner email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getApEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getApEmail())) {
                addFieldError("customer.address.apEmail", "Invalid AP email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOtherEmail())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOtherEmail())) {
                addFieldError("customer.address.otherEmail", "Invalid other email address.");
            }
        }
        if (!StringUtils.isBlank(customer.getAddress().getOther2Email())) {
            if (!EmailUtils.isValidEmail(customer.getAddress().getOther2Email())) {
                addFieldError("customer.address.other2Email", "Invalid other 2 email address.");
            }
        }
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

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
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

    public List<UserModel> getCollectors() {
        return collectors;
    }

    public void setCollectors(List<UserModel> collectors) {
        this.collectors = collectors;
    }

    public FranchiseModel getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseModel franchise) {
        this.franchise = franchise;
    }

    @Override
    public String getFranchiseCode() {
        return franchiseCode;
    }

    @Override
    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
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

    public List<UserModel> getSalesReps() {
        return salesReps;
    }

    public void setSalesReps(List<UserModel> salesReps) {
        this.salesReps = salesReps;
    }

    public CustomerCollectionModel getCollectionModel() {
        return collectionModel;
    }

    public void setCollectionModel(CustomerCollectionModel collectionModel) {
        this.collectionModel = collectionModel;
    }

    public ManageCustomerAddressModel getCustomer() {
        return customer;
    }

    public void setCustomer(ManageCustomerAddressModel customer) {
        this.customer = customer;
    }

    public ManageCustomerBaseRateModel getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(ManageCustomerBaseRateModel baseRate) {
        this.baseRate = baseRate;
    }

    public NoteModel getNote() {
        return note;
    }

    public void setNote(NoteModel note) {
        this.note = note;
    }

    public List<FranchiseServiceMarkupModel> getListServiceMarkup() {
        return listServiceMarkup;
    }

    public void setListServiceMarkup(List<FranchiseServiceMarkupModel> listServiceMarkup) {
        this.listServiceMarkup = listServiceMarkup;
    }

}
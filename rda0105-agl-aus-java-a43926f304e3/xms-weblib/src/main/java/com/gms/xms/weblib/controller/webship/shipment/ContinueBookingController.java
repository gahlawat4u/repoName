package com.gms.xms.weblib.controller.webship.shipment;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.dto.ErrorInfoVo;
import com.gms.xms.dto.delivery.DhlCapabilityVo;
import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.ContentDetailModel;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.*;
import com.gms.xms.model.webship.ship.QuoteModel;
import com.gms.xms.persistence.dao.ComponentDao;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.dao.TradeTypeDao;
import com.gms.xms.persistence.dao.customers.CustomerCollectionDao;
import com.gms.xms.persistence.service.collectiontype.CollectionTypeServiceImp;
import com.gms.xms.persistence.service.collectiontype.ICollectionTypeService;
import com.gms.xms.persistence.service.contentdetail.ContentDetailServiceImp;
import com.gms.xms.persistence.service.contentdetail.IContentDetailService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.customer.CustomerDefaultSettingServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerDefaultSettingService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.persistence.service.webship.locationcode.ILocationCodeService;
import com.gms.xms.persistence.service.webship.locationcode.LocationCodeServiceImp;
import com.gms.xms.persistence.service.webship.settings.IUserSettingsService;
import com.gms.xms.persistence.service.webship.settings.UserSettingsServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.customer.CustomerCollectionVo;
import com.gms.xms.txndb.vo.shipment.ShipmentRequestVo;
import com.gms.xms.txndb.vo.webship.*;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.workflow.client.GetQuoteClient;
import com.gms.xms.workflow.client.integration.request.GetQuoteRequest;
import com.gms.xms.workflow.client.integration.response.GetQuoteResponse;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.utils.ShipmentUtils;

import java.util.*;

/**
 * Posted from ContinueBookingController
 * <p>
 * Author HungNT Date Jul 22, 2015
 */
public class ContinueBookingController extends ShipmentController {
    private static final long serialVersionUID = -818841839518074429L;
    private List<ContentDetailModel> contentDetailList;
    private List<CollectionTypeModel> collectionTypeList;
    private List<LocationCodeModel> locationCodeList;
    private String serviceId;
    private ShipmentTypeModel shipmentType;
    private PackageModel packageType;
    private ShipmentRequestModel shipmentRequestModel;
    private Map<String, String> pickupTimeList;
    private QuoteModel quoteModel;
    private String quoteModelJson;
    private String dutiesTax = "DTU";

    private HashMap<Integer, String> selCommercialInvoice;
    private List<CountryModel> countryList;
    private HashMap<Integer, String> selPackagingList;
    private HashMap<String, String> selTermOfTradeList;
    private HashMap<Integer, String> listTransportationCharges;
    private Map<Integer, String> listDutiTax = new HashMap<>();
    private HashMap<Integer, String> pickupLocation;

    private DhlCapabilityVo dhlCapabilityVo;
    private String shipmentRequestModelGson;
    private String scheduleCollectionsType;
    private String packingListSelect;
    private String commercialInvoiceSelect;
    private String accountNumber = "3p";
    private String countryOfOrigin;
    private String hasDtp;

    public String continueBooking() {
        try {
            if (!this.validateShipmentRequest() || !this.validateCustomerInfo()) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
                return "field_error";
            }
            // Get Quote from shipmentPage and webship Info
            GetQuoteResponse getQuoteResponse = prepareDataQuote();

            // Check request api
            if (!checkCapabilityApiRequest(getQuoteResponse)) {
                return "field_error";
            }

            String customerCode = String.valueOf(this.getWebshipLoginInfo().getCustomerCode());
            Double freightCreditLimit = 0D;
            CustomerCollectionDao collectionDao = new CustomerCollectionDao();
            CustomerCollectionVo collectionVo = new CustomerCollectionVo();
            if (customerCode.substring(3, customerCode.length()).equalsIgnoreCase("00001")) {
                collectionVo = collectionDao.selectByFranCode(customerCode);
                if (collectionVo != null) {
                    freightCreditLimit = collectionVo.getFreightCreditLimit() != null ? collectionVo.getFreightCreditLimit() : 0D;
                }
            } else {
                collectionVo = collectionDao.selectByCustCode(customerCode);
                if (collectionVo != null) {
                    freightCreditLimit = collectionVo.getFreightCreditLimit() != null ? collectionVo.getFreightCreditLimit() : 0D;
                }
            }

            // Prepare Data for ContinueBookingPage
            this.prepareContinueBook(getQuoteResponse);

            // Prepare Data for ShipmentRequest from QuoteResponse
            this.prepareShipmentRequest(getQuoteResponse);

            // Data ShipmentRequest for input hidden
            this.setShipmentRequestModelGson(GsonUtils.toGson(this.shipmentRequestModel));

           /* Double totalCharge = Double.valueOf(quoteModel.getTotalCharge());
            InvoiceDao invoiceDao = new InvoiceDao();
            Double totalDue = invoiceDao.getTotalDueValue(customerCode);
            totalCharge += totalDue != null ? totalDue : 0;
            if (freightCreditLimit < totalCharge) {
                String errMess = "Please contact customer service to continue.<br/>Total of unpaid invoices and cost of this shipment ({unpaidAmount}) is more then your credit limit amount ({limitAmount}).";
                String currencySymbol = SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL);
                String limitAmount = currencySymbol + String.valueOf(freightCreditLimit);
                String unpaidAmount = currencySymbol + String.valueOf(totalCharge);
                Map<String, String> replaceMap = new HashMap<>();
                replaceMap.put("{limitAmount}", limitAmount);
                replaceMap.put("{unpaidAmount}", unpaidAmount);
                errMess = AppUtils.replaceStringByMap(replaceMap, errMess);
                throw new CustomException(errMess);
            }*/
            // Get default orgin country.
            buildDefaultCountryOfOrigin();
            // return View with serviceId and contentType
            this.setServiceId(shipmentRequestModel.getShipmentInfo().getServiceId());
            return ShipmentUtils.resultViewContinueBooking(shipmentRequestModel.getShipmentInfo().getServiceId(), shipmentRequestModel.getShipmentInfo().getContentType());

        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            log.error(e);
        }
        return "success";
    }

    public String getScheduleCollectionForm() {
        try {
            if (scheduleCollectionsType.equals("1")) {
                setPickupTimeList(HelperUtils.getPickupTimeMap());
                loadLocationCodeList();
                this.setShipmentRequestModel(GsonUtils.fromGson(shipmentRequestModelGson, ShipmentRequestModel.class));
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String getCommercialInvoiceForm() {
        try {
            if (commercialInvoiceSelect.equals("3")) {
                this.setShipmentRequestModel(GsonUtils.fromGson(shipmentRequestModelGson, ShipmentRequestModel.class));
                buildTermOfTrade();
                buildCountryList();
                buildListDutiTax();
                buildDefaultCountryOfOrigin();
                IFranchiseService service = new FranchiseServiceImp();
                FranchiseVo franchiseVo = service.selectFranchiseByFranchiseCodeExt(String.valueOf(this.getWebshipLoginInfo().getCustomerCode()));
                switch (shipmentRequestModel.getShipmentInfo().getServiceId()) {
                    case "54":
                        this.setAccountNumber(franchiseVo.getTntAccount());
                    default:

                }
            }
            if (packingListSelect.equals("2")) {
                buildTermOfTrade();
                buildCountryList();
            }

        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String getPackingListForm() {
        try {
            if (packingListSelect.equals("2")) {
                buildTermOfTrade();
                buildCountryList();
            }
            if (commercialInvoiceSelect != null && commercialInvoiceSelect.equals("3")) {
                buildTermOfTrade();
                buildCountryList();
                buildListDutiTax();
                buildDefaultCountryOfOrigin();
                IFranchiseService service = new FranchiseServiceImp();
                FranchiseVo franchiseVo = service.selectFranchiseByFranchiseCodeExt(String.valueOf(this.getWebshipLoginInfo().getCustomerCode()));
                this.setShipmentRequestModel(GsonUtils.fromGson(shipmentRequestModelGson, ShipmentRequestModel.class));
                switch (shipmentRequestModel.getShipmentInfo().getServiceId()) {
                    case "54":
                        this.setAccountNumber(franchiseVo.getTntAccount());
                    default:

                }
            }
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    /***
     * checkCapabilityApiRequest API
     *
     * @param getQuoteResponse
     * @return bool
     * @throws Exception
     */
    private boolean checkCapabilityApiRequest(GetQuoteResponse getQuoteResponse) throws Exception {
        Integer serviceId = getQuoteResponse.getShipmentInfoVo().getServiceId();
        if (serviceId == 1 || serviceId == 15) {
            this.dhlCapabilityVo = getQuoteResponse.getDhlCapabilityVo();
            if(dhlCapabilityVo != null)
            {
                if (AppConstants.ERROR.equalsIgnoreCase(this.dhlCapabilityVo.getActionStatus())) {
                    for (ErrorInfoVo errorInfo : this.dhlCapabilityVo.getErrorList()) {
                        this.addFieldError(errorInfo.getErrCode(), errorInfo.getErrMsg());
                    }
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return false;
                } else if (!this.dhlCapabilityVo.isFound()) {
                    String strMsg = getLocalizationText("Selected service is not available. please select those available services:");
                    this.addFieldError("", strMsg);
                    for (String availSvr : this.dhlCapabilityVo.getAvailSvrs()) {
                        this.addFieldError("", availSvr);
                    }
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * Build List Select in ContinueBooking Page
     *
     * @throws Exception
     */
    private void buildListDataForContinuePage() throws Exception {
        this.setPickupTimeList(HelperUtils.getPickupTimeMap());
        buildListInvoiceCommercial();
        buildPackingList();
        buildTermOfTrade();
        buildCountryList();
        buildPickupLocation();
        if (this.getShipmentPage().getServiceId().equals("3")) {
            for (ServiceAddConModel addCon : this.getShipmentPage().getAddCons()) {
                if (addCon.getAddConCode().equalsIgnoreCase("dangerousgoods") && addCon.getValue() != null) {
                    loadContentTypeListTNT();
                } else {
                    if (this.getShipmentPage().getContentType().equalsIgnoreCase("DOX")) {
                        loadContentTypeList();
                    } else {
                        this.setContentDetailList(new ArrayList<ContentDetailModel>());
                    }
                }
            }
        } else {
            loadContentTypeList();
        }
        loadCollectionTypeList();
        loadLocationCodeList();
        prepareListTransportationCharges();
        buildListDutiTax();
        this.checkDutiesTax(); // check dutyTax
    }

    /***
     * Prepare Data for shipmentRequestModel
     *
     * @param getQuoteResponse
     * @throws Exception
     */
    private void prepareShipmentRequest(GetQuoteResponse getQuoteResponse) throws Exception {
        ShipmentRequestModel prevShipmentRequestModel = this.getShipmentRequestModel();
        ShipmentRequestVo shipmentRequestVo = new ShipmentRequestVo();
        shipmentRequestVo.setShipmentInfo(ModelUtils.createVoFromModel(this.getShipmentPage(), ShipmentInfoVo.class));
        shipmentRequestVo.setQuote(getQuoteResponse.getQuoteVo());
        shipmentRequestVo.setShipmentReference(String.valueOf(this.getWebshipLoginInfo().getCustomerCode()));
        ShipmentRequestModel shipmentRequestModel = ModelUtils.createModelFromVo(shipmentRequestVo, ShipmentRequestModel.class);
        IUserSettingsService uService = new UserSettingsServiceImp();
        String customerCodeN = "";
        customerCodeN = uService.selectReferenceUserSetting(this.getWebshipLoginInfo().getCustomerCode());
        shipmentRequestModel.setShipmentReference(customerCodeN);
        shipmentRequestModel.getQuote().setBaseChargeUnit(HelperUtils.formatCurrency(shipmentRequestModel.getQuote().getBaseCharge()));
        shipmentRequestModel.getQuote().setTotalChargeUnit(HelperUtils.formatCurrency(shipmentRequestModel.getQuote().getTotalCharge()));
        for (AccessorialModel accessorial : shipmentRequestModel.getQuote().getAccessorial()) {
            accessorial.setValueCurrency(HelperUtils.formatCurrency(accessorial.getValue()));
        }
        // Build account for shipment info
        ComponentDao componentDao = new ComponentDao();
        String dhlAccount = componentDao.getDHLAccount(String.valueOf(this.getWebshipLoginInfo().getCustomerCode()));
        ICustomerDefaultSettingService cdfService = new CustomerDefaultSettingServiceImp();
        CustomerDefaultSettingVo customerDefaultSettingVo = cdfService.selectByCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
        if (customerDefaultSettingVo == null) {
            customerDefaultSettingVo = new CustomerDefaultSettingVo();
            customerDefaultSettingVo.setCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
            customerDefaultSettingVo.setDutiesBillTo(0);
            customerDefaultSettingVo.setBillingParty(0);
            customerDefaultSettingVo.setDefaultFromAddressId(0L);
            customerDefaultSettingVo.setDefaultToAddressId(0L);
        }
        shipmentRequestModel.getShipmentInfo().setDutiesAccount(customerDefaultSettingVo.getDutiesAccount());
        shipmentRequestModel.getShipmentInfo().setDutiesBillTo(customerDefaultSettingVo.getDutiesBillTo().toString());
        shipmentRequestModel.getShipmentInfo().setShipperAccount(dhlAccount);
        shipmentRequestModel.getShipmentInfo().setBillingParty(customerDefaultSettingVo.getBillingParty().toString());
        shipmentRequestModel.getQuote().setWeight(shipmentRequestModel.getQuote().getWeight() + " kgs");

        if (prevShipmentRequestModel != null) {
            shipmentRequestModel.setContentDetail(prevShipmentRequestModel.getContentDetail());
            shipmentRequestModel.setShipmentReference(prevShipmentRequestModel.getShipmentReference());
            shipmentRequestModel.setScheduleCollectionSelect(prevShipmentRequestModel.getScheduleCollectionSelect());
            shipmentRequestModel.setScheduleCollection(prevShipmentRequestModel.getScheduleCollection());
            shipmentRequestModel.setSelCollection(prevShipmentRequestModel.getSelCollection());
            shipmentRequestModel.setSelCommercial(prevShipmentRequestModel.getSelCommercial());
            shipmentRequestModel.setSelPackingList(prevShipmentRequestModel.getSelPackingList());
            shipmentRequestModel.setShipmentProductDetails(prevShipmentRequestModel.getShipmentProductDetails());
            if (prevShipmentRequestModel.getShipmentInfo() != null) {
                shipmentRequestModel.getShipmentInfo().setBillingType(prevShipmentRequestModel.getShipmentInfo().getBillingType());
                shipmentRequestModel.getShipmentInfo().setSpecialDelivery(prevShipmentRequestModel.getShipmentInfo().getSpecialDelivery());
                shipmentRequestModel.getShipmentInfo().setReceiverTaxId(prevShipmentRequestModel.getShipmentInfo().getReceiverTaxId());
                shipmentRequestModel.getShipmentInfo().setTermOfTrade(prevShipmentRequestModel.getShipmentInfo().getTermOfTrade());
                shipmentRequestModel.getShipmentInfo().setShipperAccount(prevShipmentRequestModel.getShipmentInfo().getShipperAccount());
                shipmentRequestModel.getShipmentInfo().setReasonForExport(prevShipmentRequestModel.getShipmentInfo().getReasonForExport());
            }
        }

        this.setShipmentRequestModel(shipmentRequestModel);
    }

    protected void buildDefaultCountryOfOrigin() throws Exception {
        SystemSettingVo systemSettingVo = SystemSettingCache.getInstance().getObjectByKey("Default Origin Country");
        this.setCountryOfOrigin(systemSettingVo.getSettingValue());
    }

    protected void buildListInvoiceCommercial() throws Exception {
        HashMap<Integer, String> selCommercialInvoiceN = new HashMap<>();
        selCommercialInvoiceN.put(1, "I don't need a Commercial Invoice.");
        selCommercialInvoiceN.put(0, "I already have a Commercial Invoice.");
        selCommercialInvoiceN.put(3, "Help me generate a Commercial Invoice.");
        this.setSelCommercialInvoice(selCommercialInvoiceN);
    }

    private void prepareListTransportationCharges() throws Exception {
        HashMap<Integer, String> listTransportationChargesN = new HashMap<>();
        listTransportationChargesN.put(1, "Sender");
        listTransportationChargesN.put(2, "Receiver");
        listTransportationChargesN.put(3, "Third party");
        this.setListTransportationCharges(listTransportationChargesN);
    }

    private void buildPickupLocation() throws Exception {
        HashMap<Integer, String> selPickupLocation = new HashMap<>();
        selPickupLocation.put(1, "Front");
        selPickupLocation.put(2, "Rear");
        selPickupLocation.put(3, "Side");
        this.setPickupLocation(selPickupLocation);
    }

    @Override
    protected void buildCountryList() throws Exception {
        ICountryService service = new CountryServiceImp();
        List<CountryVo> countryVos = service.getCountryList();
        List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        CountryModel headerValue = new CountryModel();
        headerValue.setCountryId("0");
        headerValue.setCountryName("Please select a country.");
        countryModels.remove(0);
        countryModels.add(0, headerValue);
        this.setCountryList(countryModels);
    }

    protected void buildPackingList() throws Exception {
        HashMap<Integer, String> selPackingList = new HashMap<>();
        selPackingList.put(0, "I don't need Packing List.");
        selPackingList.put(1, "I already have Packing List.");
        selPackingList.put(2, "Help me generate Packing List");
        this.setSelPackagingList(selPackingList);
    }

    protected void buildListDutiTax() throws Exception {
        HashMap<Integer, String> selDutiTax = new HashMap<>();
        selDutiTax.put(1, "Duty To Be Paid By Sender.");
        selDutiTax.put(2, "Duty To Be Paid By Receiver.");
        selDutiTax.put(3, "Duty To Be Paid By Third Party");
        this.setListDutiTax(selDutiTax);
    }

    protected void buildTermOfTrade() throws Exception {
        TradeTypeDao tradeTypeDao = new TradeTypeDao();
        List<TradeTypeVo> tradeTypeVos = tradeTypeDao.getTradeTypeList();
        List<TradeTypeModel> tradeTypeModels = ModelUtils.createListModelFromVo(tradeTypeVos, TradeTypeModel.class);
        HashMap<String, String> selTermOfTrade = new HashMap<>();
        for (TradeTypeModel tradeTypeModel : tradeTypeModels) {
            selTermOfTrade.put(tradeTypeModel.getTradeCode(), tradeTypeModel.getTradeName());
        }
        this.setSelTermOfTradeList(selTermOfTrade);
        this.checkDutiesTax();
    }

    /***
     * Prepare Data for ContinueBooking Page
     *
     * @param getQuoteResponse getQuoteResponse
     * @throws Exception
     */
    private void prepareContinueBook(GetQuoteResponse getQuoteResponse) throws Exception {
        Locale.setDefault(Locale.ENGLISH);

        if (getQuoteResponse.getErrorCode().equalsIgnoreCase(ErrorCode.SUCCESS)) {
            this.quoteModel = (ModelUtils.createModelFromVo(getQuoteResponse.getQuoteVo(), QuoteModel.class));

            // add currency symbol and weight unit
            this.quoteModel.setBaseChargeUnit(HelperUtils.formatCurrency(quoteModel.getBaseCharge()));
            for (AccessorialModel accessorial : quoteModel.getAccessorial()) {
                accessorial.setValueCurrency(HelperUtils.formatCurrency(accessorial.getValue()));
                if (accessorial.getDescription().equalsIgnoreCase("Additional Protection") && accessorial.getValue().equals("0.00")) {
                    accessorial.setValueCurrency("Not available");
                }
            }
            this.quoteModel.setTotalChargeUnit(HelperUtils.formatCurrency(quoteModel.getTotalCharge()));
            this.quoteModel.setWeightFomated(quoteModel.getWeight() + "kg(s)");

            ShipmentInfoModel shipmentInfoModel = new ShipmentInfoModel();
            shipmentInfoModel = ModelUtils.createModelFromVo(getQuoteResponse.getShipmentInfoVo(), ShipmentInfoModel.class);
            this.setShipmentPage(shipmentInfoModel);
            this.setQuoteModelJson(GsonUtils.toGson(this.quoteModel));

            Integer shipmentTypeId = Integer.parseInt(this.getShipmentPage().getShipmentTypeId());
            Integer packageId = Integer.parseInt(this.getShipmentPage().getPackageId());
            // Get shipment type detail
            IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.selectByServiceTypeExt(shipmentTypeId);
            this.setShipmentType(ModelUtils.createModelFromVo(shipmentTypeVo, ShipmentTypeModel.class));
            // Get package detail
            IPackageService packageService = new PackageServiceImp();
            PackageVo packageVo = packageService.getPackagebyId(packageId);
            PackageModel packageModel = ModelUtils.createModelFromVo(packageVo, PackageModel.class);
            this.packageType = packageModel;
            buildListDataForContinuePage();
        } else {
            this.setErrorCode(getQuoteResponse.getErrorCode());
            this.setErrorMessage(getQuoteResponse.getErrorMessage());
        }
    }

    private void checkDutiesTax() throws Exception {
        if (this.getShipmentPage() != null) {
            if (this.getShipmentPage().getAddCons() != null) {
                this.setDutiesTax("DTU");
                for (ServiceAddConModel svAddConModel : this.getShipmentPage().getAddCons()) {
                    if (svAddConModel.getAddConName() != null && svAddConModel.getValue() != null) {
                        if (svAddConModel.getAddConName().equals("Duties/Taxes Paid by Sender") && svAddConModel.getValue().equals("1")) {
                            this.setDutiesTax("DTP");
                        }
                    }
                }
            }
        } else if (shipmentRequestModel != null && shipmentRequestModel.getShipmentInfo() != null && shipmentRequestModel.getShipmentInfo().getAddCons() != null) {
            this.setDutiesTax("DTU");
            for (ServiceAddConModel svAddConModel : shipmentRequestModel.getShipmentInfo().getAddCons()) {
                if (svAddConModel.getAddConName() != null && svAddConModel.getValue() != null) {
                    if (svAddConModel.getAddConName().equals("Duties/Taxes Paid by Sender") && svAddConModel.getValue().equals("1")) {
                        this.setDutiesTax("DTP");
                    }
                }
            }
            this.getShipmentRequestModel().getShipmentInfo().setTermOfTrade(this.getDutiesTax());
        }
    }

    protected void loadContentTypeList() throws Exception {
        IContentDetailService service = new ContentDetailServiceImp();
        List<ContentDetailVo> contentDetailVos = service.getContentDetailList();
        List<ContentDetailModel> contentDetailList = ModelUtils.createListModelFromVo(contentDetailVos, ContentDetailModel.class);
        this.contentDetailList = contentDetailList;
    }

    protected void loadContentTypeListTNT() {
        List<ContentDetailModel> contentDetailList = new ArrayList<>();
        ContentDetailModel contentDetailModel = new ContentDetailModel();
        contentDetailModel.setDescription("Biological Substance, Category B");
        contentDetailList.add(contentDetailModel);
        contentDetailModel = new ContentDetailModel();
        contentDetailModel.setDescription("Dry Ice");
        contentDetailList.add(contentDetailModel);
        contentDetailModel = new ContentDetailModel();
        contentDetailModel.setDescription("Limited Quantities");
        contentDetailList.add(contentDetailModel);
        contentDetailModel = new ContentDetailModel();
        contentDetailModel.setDescription("Partially Regulated (excepted) Lithium Batteries");
        contentDetailList.add(contentDetailModel);
        this.contentDetailList = contentDetailList;
    }

    private GetQuoteResponse prepareDataQuote() throws Exception {
        WebshipLoginVo webshipLoginInfo = this.getWebshipLoginInfo();
        GetQuoteRequest getQuoteRequest = new GetQuoteRequest();
        GetQuoteClient getQuoteClient = new GetQuoteClient(this.getAddInfoMap());

        getQuoteRequest.setShipmentInfoVo(ModelUtils.createVoFromModel(this.getShipmentPage(), ShipmentInfoVo.class));
        getQuoteRequest.setWebshipLoginVo(webshipLoginInfo);
        GetQuoteResponse getQuoteResponse = getQuoteClient.getQuote2(getQuoteRequest);
        if (!getQuoteResponse.getErrorCode().equals(ErrorCode.SUCCESS)) {
            if (getQuoteResponse.getIsOtherCarrier() != null && getQuoteResponse.getIsOtherCarrier() || this.getShipmentPage().getServiceId().equalsIgnoreCase("3")) {
                return this.getOtherCarrierQuoteByName("Wfl-GetOtherCarrierQuoteInfo");
            }
            if(getQuoteResponse.getUpsLargeCharger())
            {
                return this.getOtherCarrierQuoteByName("Wfl-GetOtherCarrierQuoteInfo");
            }
            throw new CustomException(getQuoteResponse.getErrorMessage());
        }
        return getQuoteResponse;
    }

    protected GetQuoteResponse getOtherCarrierQuoteByName(String carrierQuote) throws Exception {
        GetQuoteResponse getQuoteResponse = new GetQuoteResponse();
        WebshipLoginVo webshipLoginInfo = this.getWebshipLoginInfo();
        ContextBase2 context = new ContextBase2(this.getAddInfoMap());
        ShipmentInfoVo shipmentInfoVo = ModelUtils.createVoFromModel(this.getShipmentPage(), ShipmentInfoVo.class);
        context.put(Attributes.SHIPMENT_INFO_VO, shipmentInfoVo);
        context.put(Attributes.USER_LOGGIN_INFO, webshipLoginInfo);
        Integer bound = ShipmentUtils.getBound(shipmentInfoVo.getSenderAddress().getCountry(), shipmentInfoVo.getReceiverAddress().getCountry());
        shipmentInfoVo.setBound(bound);
        context.put(Attributes.WFP_NAME, carrierQuote);
        context = WorkFlowManager2.getInstance().process(context);
        QuoteVo quoteVo = new QuoteVo();
        quoteVo = context.get(Attributes.QUOTE_VO);
        shipmentInfoVo = context.get(Attributes.SHIPMENT_INFO_VO);
        getQuoteResponse.setErrorCode(context.getString(Attributes.ERROR_CODE));
        getQuoteResponse.setErrorMessage(context.getString(Attributes.ERROR_MESSAGE));
        getQuoteResponse.setQuoteVo(quoteVo);
        getQuoteResponse.setShipmentInfoVo(shipmentInfoVo);
        return getQuoteResponse;
    }

    protected void loadCollectionTypeList() throws Exception {
        ICollectionTypeService service = new CollectionTypeServiceImp();
        List<CollectionTypeVo> collectionTypeVos = service.getCollectionTypeList();
        List<CollectionTypeModel> collectionTypeList = ModelUtils.createListModelFromVo(collectionTypeVos, CollectionTypeModel.class);
        this.collectionTypeList = collectionTypeList;
    }

    protected void loadLocationCodeList() throws Exception {
        ILocationCodeService service = new LocationCodeServiceImp();
        List<LocationCodeVo> locationCodeVos = service.getLocationCodeList();
        List<LocationCodeModel> locationCodeList = ModelUtils.createListModelFromVo(locationCodeVos, LocationCodeModel.class);
        this.locationCodeList = locationCodeList;
    }

    public List<ContentDetailModel> getContentDetailList() {
        return contentDetailList;
    }

    public void setContentDetailList(List<ContentDetailModel> contentDetailList) {
        this.contentDetailList = contentDetailList;
    }

    public List<CollectionTypeModel> getCollectionTypeList() {
        return collectionTypeList;
    }

    public void setCollectionTypeList(List<CollectionTypeModel> collectionTypeList) {
        this.collectionTypeList = collectionTypeList;
    }

    public List<LocationCodeModel> getLocationCodeList() {
        return locationCodeList;
    }

    public void setLocationCodeList(List<LocationCodeModel> locationCodeList) {
        this.locationCodeList = locationCodeList;
    }

    public Map<String, String> getPickupTimeList() {
        return pickupTimeList;
    }

    public void setPickupTimeList(Map<String, String> pickupTimeList) {
        this.pickupTimeList = pickupTimeList;
    }

    public ShipmentTypeModel getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentTypeModel shipmentType) {
        this.shipmentType = shipmentType;
    }

    public PackageModel getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageModel packageType) {
        this.packageType = packageType;
    }

    public ShipmentRequestModel getShipmentRequestModel() {
        return shipmentRequestModel;
    }

    public void setShipmentRequestModel(ShipmentRequestModel shipmentRequestModel) {
        this.shipmentRequestModel = shipmentRequestModel;
    }

    public QuoteModel getQuoteModel() {
        return quoteModel;
    }

    public void setQuoteModel(QuoteModel quoteModel) {
        this.quoteModel = quoteModel;
    }

    public String getQuoteModelJson() {
        return quoteModelJson;
    }

    public void setQuoteModelJson(String quoteModelJson) {
        this.quoteModelJson = quoteModelJson;
    }

    public HashMap<Integer, String> getSelCommercialInvoice() {
        return selCommercialInvoice;
    }

    public void setSelCommercialInvoice(HashMap<Integer, String> selCommercialInvoice) {
        this.selCommercialInvoice = selCommercialInvoice;
    }

    public HashMap<Integer, String> getSelPackagingList() {
        return selPackagingList;
    }

    public void setSelPackagingList(HashMap<Integer, String> selPackagingList) {
        this.selPackagingList = selPackagingList;
    }

    public HashMap<String, String> getSelTermOfTradeList() {
        return selTermOfTradeList;
    }

    public void setSelTermOfTradeList(HashMap<String, String> selTermOfTradeList) {
        this.selTermOfTradeList = selTermOfTradeList;
    }

    @Override
    public List<CountryModel> getCountryList() {
        return countryList;
    }

    @Override
    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public String getDutiesTax() {
        return dutiesTax;
    }

    public void setDutiesTax(String dutiesTax) {
        this.dutiesTax = dutiesTax;
    }

    public HashMap<Integer, String> getListTransportationCharges() {
        return listTransportationCharges;
    }

    public void setListTransportationCharges(HashMap<Integer, String> listTransportationCharges) {
        this.listTransportationCharges = listTransportationCharges;
    }

    public Map<Integer, String> getListDutiTax() {
        return listDutiTax;
    }

    public void setListDutiTax(Map<Integer, String> listDutiTax) {
        this.listDutiTax = listDutiTax;
    }

    public HashMap<Integer, String> getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(HashMap<Integer, String> pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getShipmentRequestModelGson() {
        return shipmentRequestModelGson;
    }

    public void setShipmentRequestModelGson(String shipmentRequestModelGson) {
        this.shipmentRequestModelGson = shipmentRequestModelGson;
    }

    public String getScheduleCollectionsType() {
        return scheduleCollectionsType;
    }

    public void setScheduleCollectionsType(String scheduleCollectionsType) {
        this.scheduleCollectionsType = scheduleCollectionsType;
    }

    public DhlCapabilityVo getDhlCapabilityVo() {
        return dhlCapabilityVo;
    }

    public String getPackingListSelect() {
        return packingListSelect;
    }

    public void setPackingListSelect(String packingListSelect) {
        this.packingListSelect = packingListSelect;
    }

    public String getCommercialInvoiceSelect() {
        return commercialInvoiceSelect;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCommercialInvoiceSelect(String commercialInvoiceSelect) {
        this.commercialInvoiceSelect = commercialInvoiceSelect;
    }

    public void setDhlCapabilityVo(DhlCapabilityVo dhlCapabilityVo) {
        this.dhlCapabilityVo = dhlCapabilityVo;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getHasDtp() {
        return hasDtp;
    }

    public void setHasDtp(String hasDtp) {
        this.hasDtp = hasDtp;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
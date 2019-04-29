package com.gms.xms.weblib.controller.webship.shipment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.CurrencyModel;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.DimensionModel;
import com.gms.xms.model.QuotePieceModel;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.StateModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.AddressModel;
import com.gms.xms.model.webship.CustomerAddressBookModel;
import com.gms.xms.model.webship.PackageModel;
import com.gms.xms.model.webship.PieceModel;
import com.gms.xms.model.webship.QuoteJobModel;
import com.gms.xms.model.webship.ServiceAddConModel;
import com.gms.xms.model.webship.ShipmentInfoModel;
import com.gms.xms.model.webship.ShipmentModel;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.model.webship.ShipmentTypePackageModel;
import com.gms.xms.model.webship.WebshipQuoteLogDetailModel;
import com.gms.xms.model.webship.history.HistoryDetailAccessorialModel;
import com.gms.xms.model.webship.history.HistoryDetailFilterModel;
import com.gms.xms.model.webship.history.HistoryDetailInfoModel;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.persistence.service.accessorial.AccessorialServiceImp;
import com.gms.xms.persistence.service.accessorial.IAccessorialSerivce;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.currency.CurrencyServiceImp;
import com.gms.xms.persistence.service.currency.ICurrencyService;
import com.gms.xms.persistence.service.customeraddress.CustomerAddressServiceImp;
import com.gms.xms.persistence.service.customeraddress.ICustomerAddressService;
import com.gms.xms.persistence.service.dimension.DimensionServiceImp;
import com.gms.xms.persistence.service.dimension.IDimensionService;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.serviceadditionaloptions.IServiceAdditionalOptionsService;
import com.gms.xms.persistence.service.serviceadditionaloptions.ServiceAdditionalOptionsServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.persistence.service.state.IStateService;
import com.gms.xms.persistence.service.state.StateServiceImp;
import com.gms.xms.persistence.service.webship.addressbook.AddressBookServiceImp;
import com.gms.xms.persistence.service.webship.addressbook.IAddressBookService;
import com.gms.xms.persistence.service.webship.settings.IUserSettingsService;
import com.gms.xms.persistence.service.webship.settings.UserSettingsServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.persistence.service.webship.shipmenttypepackage.IShipmentTypePackageService;
import com.gms.xms.persistence.service.webship.shipmenttypepackage.ShipmentTypePackageServiceImp;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.CurrencyVo;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.QuoteJobFilter;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.StateVo;
import com.gms.xms.txndb.vo.WebshipQuoteLogDetailVo;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;
import com.gms.xms.txndb.vo.webship.DimensionFilter;
import com.gms.xms.txndb.vo.webship.DimensionVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConFilter;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.ServiceFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypePackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailAccessorialVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.quotejob.QuoteJobVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.service.webship.history.HistoryAddressServiceImp;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryAddressService;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.service.webship.quotejob.IQuoteJobService;
import com.gms.xms.workflow.service.webship.quotejob.QuoteJobServiceImp;
import com.gms.xms.workflow.utils.ShipmentUtils;

/**
 * Posted from ShipmentController
 * <p>
 * Author HungNT Date Jul 7, 2015
 */
public class ShipmentController extends JsonBaseController {
    protected Log log = LogFactory.getLog(ShipmentController.class);
    private static final long serialVersionUID = 3076569046752794458L;
    private List<CountryModel> countryList;
    private ShipmentInfoModel shipmentPage;
    private List<ServiceModel> serviceList;
    private List<ShipmentTypeModel> shipmentTypeList;
    private List<PackageModel> packageList;
    private List<String> dimensionUnitList;
    private List<CurrencyModel> currencyList;
    private List<String> weightUnitList;
    private ShipmentTypePackageModel shipmentTypePackage;
    // Dimension Type
    private DimensionModel dimension;
    private List<DimensionModel> dimensionList;
    private Integer dimensionId;
    private String dimensionResult;
    private List<PieceModel> pieceModels;
    private HistoryDetailInfoModel detailInfoModel;
    private HistoryDetailFilterModel detailFilterModel;
    private List<HistoryDetailAccessorialModel> detailAccessorialModels;
    private ShipmentModel shipmentModel;
    private List<ServiceAddConModel> listAdditionalConfig;
    private List<StateModel> listStates;
    private QuoteJobModel quoteJob;
    private String quoteId;
    private String carrierType;
    private String serviceId;
    private String shipmentTypeId;
    private String packageId;
    private String prevPackageName;
    private String contentType;
    private String shipmentId;
    private String serviceType;
    private String defaultCurrencyCode = SystemSettingCache.getInstance().getValueByKey("Default Currency for Webship");
    private String addressId;
    private String defCloseTime;
    private String defaultAddressJson;
    private String changeCountryId;
    private String senderState;
    private String receiverState;
    private Boolean isSender;
    private Boolean isAglWarranty;

    private static final int DOMESTIC_TYPE = 1;
    private static final int INTERNATIONAL_TYPE = 0;
    private static final String DEFAULT_COUNTRY = "16";// Australia

    public String show() {
        this.setPageTitle("Webship");
        this.setBreadCrumb("Ship");
        try {
            // Get customer default settings
            CustomerDefaultSettingVo customerDefaultSetting = this.getCustomerDefaultSetting();

            // Re-ship same package from History page
            if (!StringUtils.isBlank(this.getShipmentId())) {
                this.prepareReshipHistory();
                this.buildCustomerDefaultAddress(customerDefaultSetting, true);
                this.setDefCloseTime(SystemSettingCache.getInstance().getValueByKey("Close Time"));
            } else if (!StringUtils.isBlank(this.getQuoteId())) {
                // Re-ship from Quote Log
                this.prepareReshipQuoteLog();
                this.buildCustomerDefaultAddress(customerDefaultSetting, true);
                this.setDefCloseTime(SystemSettingCache.getInstance().getValueByKey("Close Time"));
            } else {
                // Create new shipment info.
                shipmentPage = new ShipmentInfoModel();
                this.buildCustomerDefaultAddress(customerDefaultSetting, false);
                this.setDefCloseTime(SystemSettingCache.getInstance().getValueByKey("Close Time"));
                buildCustomerShipmentDefault(customerDefaultSetting);
            }
            this.setCarrierType(String.valueOf(this.determineCarrierType()));
            this.prepareData();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String changeCountry() {
        Integer carrierType = 0;
        try {
            carrierType = this.determineCarrierType();
            this.loadServiceList(carrierType);
            this.loadShipmentTypeList(this.getFirstServiceId());
            this.shipmentTypeId = this.shipmentTypeList.get(0).getShipmentTypeId();
            this.serviceId = this.serviceList.get(0).getServiceId();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String changeService() {
        Integer serviceId = 0;
        try {
            serviceId = Integer.parseInt(this.serviceId);
            this.loadShipmentTypeList(serviceId);
            this.loadPackageList(this.getFirstShipmentTypeId());
            this.packageId = String.valueOf(this.getFirstShipmentTypeId());
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String changeShipmentType() {
        Integer shipmentTypeId = 0;
        try {
            shipmentTypeId = Integer.parseInt(this.shipmentTypeId);
            this.loadPackageList(shipmentTypeId);
            this.loadShipmentTypePackage(this.getFirstPackageId(), this.getFirstShipmentTypeId());
            this.shipmentTypeId = String.valueOf(shipmentTypeId);
            this.packageId = this.determinePackageIdByPrevPackageName();
            this.shipmentPage.setPackageId(this.packageId);
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private String determinePackageIdByPrevPackageName() {
        if (this.prevPackageName != null) {
            for (PackageModel packageModel : this.packageList) {
                if (this.prevPackageName.equals(packageModel.getPackageName())) {
                    return packageModel.getPackageId();
                }
            }
        }
        return this.getFirstPackageId().toString();
    }

    public String changePackage() {
        Integer packageId = 0;
        Integer shipmentTypeId = 0;
        this.setCarrierType(String.valueOf(this.determineCarrierType()));
        try {
            packageId = Integer.parseInt(this.packageId);
            shipmentTypeId = Integer.parseInt(this.shipmentTypeId);
            shipmentPage = this.getShipmentPage();
        } catch (Exception e) {
        }
        try {
            this.loadServiceId(shipmentTypeId);
            this.loadShipmentTypePackage(packageId, shipmentTypeId);
            this.loadWeightUnit();
            this.loadDimensionUnitList();
            this.loadCurrencyList();
            this.loadDimensionList();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void loadServiceId(Integer shipmentTypeId) throws DaoException {
        ShipmentTypeDao shipmentTypeDao = new ShipmentTypeDao();
        ShipmentTypeVo shipmentTypeVo = shipmentTypeDao.selectById(shipmentTypeId);
        this.getShipmentPage().setServiceId(shipmentTypeVo.getServiceId().toString());
    }

    public String changeDimension() {
        try {
            IDimensionService dimensionService = new DimensionServiceImp();
            DimensionVo dimensionVo = dimensionService.getById(this.getDimensionId());
            if (dimensionVo != null) {
                String dimensionResult = "";
                dimensionResult += "{";
                dimensionResult += "\"l\":" + dimensionVo.getL();
                dimensionResult += ",\"w\":" + dimensionVo.getW();
                dimensionResult += ",\"h\":" + dimensionVo.getH();
                dimensionResult += "}";
                this.setDimensionResult(dimensionResult);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String resetAddPiece() {
        Integer packageId = 0;
        Integer shipmentTypeId = 0;
        this.setCarrierType(String.valueOf(this.determineCarrierType()));
        try {
            packageId = Integer.parseInt(this.packageId);
            shipmentTypeId = Integer.parseInt(this.shipmentTypeId);
            this.loadShipmentTypePackage(packageId, shipmentTypeId);
            this.loadServiceId(shipmentTypeId);
            this.setContentType(contentType);
            shipmentPage = this.getShipmentPage();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String loadAdditionalConfig() {
        try {
            IServiceAdditionalOptionsService service = new ServiceAdditionalOptionsServiceImp();
            ServiceAddConFilter filter = new ServiceAddConFilter();
            Integer shipmentTypeId = 0;
            try {
                shipmentTypeId = Integer.parseInt(this.shipmentTypeId);
            } catch (Exception e) {
            }
            filter.setShipmentTypeId(shipmentTypeId);
            List<ServiceAddConVo> additionalConfigVos = service.getOptionsByServiceIdAndShipmentTypeId(filter);
            List<ServiceAddConModel> listAdditionalConfig = ModelUtils.createListModelFromVo(additionalConfigVos, ServiceAddConModel.class);
            this.listAdditionalConfig = listAdditionalConfig;
            // Check Agl Warranty
            FranchiseDao franchiseDao = new FranchiseDao();
            CustomerDao customerDao = new CustomerDao();
            WebshipLoginVo loggedWebship = WebUtils.getWebshipLoginInfo(request);
            Long customerCode = loggedWebship.getCustomerCode();
            Integer enableSi = 0;
            if ("00001".equalsIgnoreCase(String.valueOf(customerCode).substring(3))) {
                enableSi = franchiseDao.checkAglWarranty(customerCode);
            } else {
                enableSi = customerDao.checkAglWarranty(customerCode);
            }
            if (enableSi == 1) {
                this.setIsAglWarranty(true);
            } else {
                this.setIsAglWarranty(false);
            }
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
        }
        return "success";
    }

    public String loadStates() {
        List<StateModel> stateModels = new LinkedList<>();
        try {
            /*if (this.getChangeCountryId().equalsIgnoreCase("248") || this.getChangeCountryId().equalsIgnoreCase("257")) code by rakesh sir*/
        	
        	/*code by shahabuddin*/
        	if (this.getChangeCountryId().equalsIgnoreCase("225"))
        	{
                
                IStateService stateService = new StateServiceImp();
                List<StateVo> stateVos = stateService.getStateListByCountryId(Long.valueOf(this.getChangeCountryId()));
                stateModels = ModelUtils.createListModelFromVo(stateVos, StateModel.class);
            }
        	
        	if (this.getChangeCountryId().equalsIgnoreCase("37"))
        	{
               
                IStateService stateService = new StateServiceImp();
                List<StateVo> stateVos = stateService.getStateListByCountryId(Long.valueOf(this.getChangeCountryId()));
                stateModels = ModelUtils.createListModelFromVo(stateVos, StateModel.class);
            }
        	
        	
            this.setListStates(stateModels);
            shipmentPage = new ShipmentInfoModel();

            if(getIsSender())
            {
                AddressModel senderAddress = new AddressModel();
                senderAddress.setState(this.getSenderState());
                shipmentPage.setSenderAddress(senderAddress);
            }
            else
            {
                AddressModel receiverAddress = new AddressModel();
                receiverAddress.setState(this.getReceiverState());
                shipmentPage.setReceiverAddress(receiverAddress);
            }


        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected Integer getFirstServiceId() {
        Integer serviceId = 0;
        try {
            serviceId = Integer.parseInt(this.serviceList.get(0).getServiceId());
        } catch (Exception e) {
        }
        return serviceId;
    }

    protected Integer getFirstShipmentTypeId() {
        Integer shipmentTypeId = 0;
        try {
            shipmentTypeId = Integer.parseInt(this.shipmentTypeList.get(0).getShipmentTypeId());
        } catch (Exception e) {
        }
        return shipmentTypeId;
    }

    protected Integer getFirstPackageId() {
        Integer packageId = 0;
        try {
            packageId = Integer.parseInt(this.packageList.get(0).getPackageId());
        } catch (Exception e) {
        }
        return packageId;
    }

    protected void buildCountryList() throws Exception {
        ICountryService service = new CountryServiceImp();
        List<CountryVo> countryVos = service.getWebshipCountryList();
        List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        CountryModel headerValue = new CountryModel();
        headerValue.setCountryId("0");
        headerValue.setCountryName(this.getLocalizationText("Please select a country."));
        countryModels.remove(0);
        countryModels.add(0, headerValue);
        this.countryList = countryModels;
    }

    protected CustomerDefaultSettingVo getCustomerDefaultSetting() throws DaoException {
        IUserSettingsService service = new UserSettingsServiceImp();
        CustomerDefaultSettingVo defaultSettings = service.selectByCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
        if (defaultSettings == null) {
            defaultSettings = new CustomerDefaultSettingVo();
            defaultSettings.setCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
            defaultSettings.setDefaultFromAddressId(0L);
            defaultSettings.setDefaultToAddressId(0L);
        }
        return defaultSettings;
    }


    private boolean checkDefaultServiceValueWithServiceListByCountry(String defaultCarrierType, List<ServiceModel> carrierList)
    {
        boolean hasInList = false;
        if(defaultCarrierType != null)
        {
            for(ServiceModel serviceModel : carrierList)
            {
                if(serviceModel.getServiceId().equals(defaultCarrierType))
                {
                    hasInList = true;
                    break;
                }
            }

        }
        return hasInList;
    }

    protected void prepareData() throws Exception {
        this.buildCountryList();
        Integer carrierType = this.determineCarrierType();
        Integer serviceId = 0;
        Integer packageId = 0;
        Integer shipmentTypeId = 0;
        if (this.serviceList == null || this.serviceList.isEmpty()) {
            this.loadServiceList(carrierType);
            if(serviceList.size() == 0){
            	 this.loadServiceList(0);
            }
        }
        if (this.shipmentTypeList == null || this.shipmentTypeList.isEmpty()) {

            if (this.shipmentId != null) {
                serviceId = Integer.parseInt(shipmentPage.getServiceId());
            } else if (this.quoteId != null) {
                serviceId = Integer.parseInt(quoteJob.getShipmentType().getServiceId());
            } else {
                boolean isDefaultValueInList = checkDefaultServiceValueWithServiceListByCountry(shipmentPage.getDefaultCarrierType(), serviceList);
                if(isDefaultValueInList)
                    serviceId = Integer.parseInt(this.shipmentPage.getDefaultCarrierType());
                else
                    serviceId = Integer.parseInt(this.serviceList.get(0).getServiceId());
            }

            this.shipmentPage.setServiceId(serviceId.toString());
            this.loadShipmentTypeList(serviceId);
        }
        if (this.packageList == null || this.packageList.isEmpty()) {
            try {
                if (this.shipmentId != null) {
                    shipmentTypeId = Integer.parseInt(detailInfoModel.getShipmentTypeId());
                    shipmentPage.setShipmentTypeId(String.valueOf(shipmentTypeId));
                } else if (this.quoteId != null) {
                    shipmentTypeId = Integer.parseInt(quoteJob.getShipmentTypeId());
                    shipmentPage.setShipmentTypeId(String.valueOf(shipmentTypeId));
                } else if (this.getCustomerDefaultSetting().getDefaultServiceType() != null && !this.getCustomerDefaultSetting().getDefaultServiceType().equals("0")) {
                    shipmentTypeId = Integer.parseInt(this.getCustomerDefaultSetting().getDefaultServiceType());
                }
                else {
                    shipmentTypeId = Integer.parseInt(this.shipmentTypeList.get(0).getShipmentTypeId());
                }
            } catch (Exception e) {
            }
            this.loadPackageList(shipmentTypeId);
        }
        if (this.shipmentTypePackage == null) {
            try {
                if (this.shipmentId != null) {
                    shipmentTypeId = Integer.parseInt(detailInfoModel.getShipmentTypeId());
                    packageId = Integer.parseInt(detailInfoModel.getPackageId());
                    shipmentPage.setPackageId(String.valueOf(packageId));
                } else if (this.quoteId != null) {
                    shipmentTypeId = Integer.parseInt(quoteJob.getShipmentTypeId());
                    packageId = Integer.parseInt(quoteJob.getPackageId());
                    shipmentPage.setPackageId(String.valueOf(packageId));
                } else {
                    packageId = Integer.parseInt(this.packageList.get(0).getPackageId());
                    shipmentTypeId = Integer.parseInt(this.shipmentTypeList.get(0).getShipmentTypeId());
                }
            } catch (Exception e) {
            }
            this.loadShipmentTypePackage(packageId, shipmentTypeId);
        }
        this.loadDimensionUnitList();
        this.loadWeightUnit();
        this.loadCurrencyList();
        this.loadDimensionList();
    }

    
    protected void loadServiceList(Integer carrierType) throws Exception {
        IServiceService service = new ServiceServiceImp();
        ServiceFilter filter = new ServiceFilter();
        filter.setCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
        filter.setCarrierType(carrierType);
        List<ServiceVo> serviceVos = service.getServiceListByFilter(filter);
        List<ServiceVo> activeServiceVos = new LinkedList<ServiceVo>();
        for (ServiceVo serviceVo : serviceVos) {
            if (serviceVo.getInactive() == 0) {
                activeServiceVos.add(serviceVo);
            }
        }
        List<ServiceModel> carrierList = ModelUtils.createListModelFromVo(activeServiceVos, ServiceModel.class);
      
        this.serviceList = carrierList;
    }

   class NameComparator implements Comparator{  
    	public int compare(Object o1,Object o2){  
    		ShipmentTypeModel s1=(ShipmentTypeModel)o1;  
    		ShipmentTypeModel s2=(ShipmentTypeModel)o2;  
    	  
    	return s1.getShipmentTypeName().compareTo(s2.getShipmentTypeName());  
    	}  
    }
    
   
   class NameStatrTrackComparator implements Comparator{  
   	public int compare(Object o1,Object o2){  
   		ShipmentTypeModel s1=(ShipmentTypeModel)o1;  
   		ShipmentTypeModel s2=(ShipmentTypeModel)o2;  
   	  
   	return s1.getShipmentTypeId().compareTo(s2.getShipmentTypeId());  
   	}  
   }
    
    protected void loadShipmentTypeList(Integer serviceId) throws Exception {
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        ShipmentTypeFilter shipmentTypeFilter = new ShipmentTypeFilter();
        shipmentTypeFilter.setCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
        shipmentTypeFilter.setServiceId(serviceId);
        List<ShipmentTypeVo> shipmentTypeVos = service.getShowServiceWebshipByFilter(shipmentTypeFilter);
        List<ShipmentTypeVo> showShipmentTypeVos = new LinkedList<ShipmentTypeVo>();
        for (ShipmentTypeVo shipmentTypeVo : shipmentTypeVos) {
            if (shipmentTypeVo.getShowStatus() == 1) {
                showShipmentTypeVos.add(shipmentTypeVo);
            }
        }
        List<ShipmentTypeModel> shipmentTypeModels = ModelUtils.createListModelFromVo(showShipmentTypeVos, ShipmentTypeModel.class);
        for (int i = 0; i < shipmentTypeModels.size(); i++) {
            ShipmentTypeModel shipmentTypeModel = shipmentTypeModels.get(i);
            if (shipmentTypeModel.getShipmentTypeId().equals("0")) {
                shipmentTypeModels.remove(i);
            }
        }
        //------------------------------------_SORTING -------------------------------
        if( serviceId != 52)
        Collections.sort(shipmentTypeModels, new NameComparator());
        
        
        if( serviceId == 72){
        	Collections.sort(shipmentTypeModels, new NameStatrTrackComparator());
        	ShipmentTypeModel ShipmentTypeModel1 = shipmentTypeModels.get(0);
        	ShipmentTypeModel ShipmentTypeModel2 = shipmentTypeModels.get(1);
        	shipmentTypeModels.set(0, ShipmentTypeModel2);
        	shipmentTypeModels.set(1, ShipmentTypeModel1);
        }
        if( serviceId == 72 && this.getWebshipLoginInfo().getCustomerCode() == 10000023){
        	Collections.sort(shipmentTypeModels, new NameStatrTrackComparator());
        	ShipmentTypeModel ShipmentTypeModel1 = shipmentTypeModels.get(0);
        	ShipmentTypeModel ShipmentTypeModel2 = shipmentTypeModels.get(3);
        	shipmentTypeModels.set(0, ShipmentTypeModel2);
        	shipmentTypeModels.set(3, ShipmentTypeModel1);
        }
       
        	this.shipmentTypeList = shipmentTypeModels;
        
          //  Collections.sort(shipmentTypeModels, new NameStatrTrackComparator());
           
        //---------------------------------------------------------------------------
      /*  Collections.sort(shipmentTypeModels, new Comparator<ShipmentTypeModel>() {
            @Override
            public int compare(shipmentTypeModels c1, shipmentTypeModels c2) {
                //You should ensure that list doesn't contain null values!
                return collator.compare(c1.getName(), c2.getName());
            }
           });*/
        
    }

    protected void loadPackageList(Integer shipmentTypeId) throws Exception {
        IPackageService service = new PackageServiceImp();
        List<PackageVo> packageVos = service.getPackageListByShipmentTypeId(shipmentTypeId);
        List<PackageModel> packageList = ModelUtils.createListModelFromVo(packageVos, PackageModel.class);
        this.packageList = packageList;
    }

    protected void loadShipmentTypePackage(Integer packageId, Integer shipmentTypeId) throws Exception {
        IShipmentTypePackageService service = new ShipmentTypePackageServiceImp();
        ShipmentTypePackageVo filter = new ShipmentTypePackageVo();
        filter.setPackageId(packageId);
        filter.setShipmentTypeId(shipmentTypeId);
        ShipmentTypePackageVo shipmentTypePackageVo = service.getShipmentTypePackageListByShipmentTypeAndPackageId(filter);
        ShipmentTypePackageModel shipmentTypePackageModel = ModelUtils.createModelFromVo(shipmentTypePackageVo, ShipmentTypePackageModel.class);
        this.shipmentTypePackage = shipmentTypePackageModel;
    }

    protected void loadDimensionUnitList() {
        List<String> dimentionUnitList = new ArrayList<String>();
        dimentionUnitList.add("CM");
        /*dimentionUnitList.add("IN");*/
        this.dimensionUnitList = dimentionUnitList;
    }

    protected void loadCurrencyList() throws Exception {
        ICurrencyService service = new CurrencyServiceImp();
        List<CurrencyVo> currencyVos = service.getCurrencyList();
        List<CurrencyModel> currencyList = ModelUtils.createListModelFromVo(currencyVos, CurrencyModel.class);
        String defaultCurrencyCode = SystemSettingCache.getInstance().getValueByKey("Default Currency for Webship");
        if (shipmentPage != null) {
            this.shipmentPage.setCurrencyCode(defaultCurrencyCode);
        } else {
            ShipmentInfoModel shipmentInfoModel = new ShipmentInfoModel();
            shipmentInfoModel.setCurrencyCode(defaultCurrencyCode);
            this.shipmentPage = shipmentInfoModel;
        }
        this.currencyList = currencyList;
    }

    protected void loadWeightUnit() {
        List<String> weightUnitList = new ArrayList<String>();
        weightUnitList.add("KG");
       /* weightUnitList.add("LB");*/
        this.weightUnitList = weightUnitList;
    }

    protected void loadDimensionList() throws Exception {
        IDimensionService dimensionService = new DimensionServiceImp();
        DimensionFilter filter = new DimensionFilter();
        Long webshipId = this.getWebshipLoginInfo().getWebshipId();
        filter.setWebshipId(Integer.valueOf(webshipId.toString()));
        List<DimensionVo> dimensionVos = dimensionService.selectByFilter(filter);
        List<DimensionModel> dimensionModels = ModelUtils.createListModelFromVo(dimensionVos, DimensionModel.class);
        this.setDimensionList(dimensionModels);
    }


    private void buildCustomerShipmentDefault(CustomerDefaultSettingVo customerDefaultSetting) throws Exception {
        IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
        if (customerDefaultSetting.getDefaultServiceType() != null) {
            ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(Integer.parseInt(customerDefaultSetting.getDefaultServiceType()));
            shipmentPage.setServiceId(shipmentTypeVo.getServiceId().toString());
            shipmentPage.setDefaultCarrierType(shipmentTypeVo.getServiceId().toString());
            shipmentPage.setShipmentTypeId(shipmentTypeVo.getShipmentTypeId().toString());
            shipmentPage.setPackageId(customerDefaultSetting.getDefaultPackageType().toString());
        }
    }

    protected void buildCustomerDefaultAddress(CustomerDefaultSettingVo customerDefaultSetting, Boolean isReship) throws Exception {
        IAddressBookService addressService = new AddressBookServiceImp();
        CustomerAddressBookVo defaultSenderAddressVo = addressService.getCustomerAddressBookById(customerDefaultSetting.getDefaultFromAddressId());
        AddressModel addressModel = new AddressModel();
        if (defaultSenderAddressVo != null) {
            CustomerAddressBookModel defaultSenderAddress = ModelUtils.createModelFromVo(defaultSenderAddressVo, CustomerAddressBookModel.class);
            if (defaultSenderAddress != null) {
                addressModel.setCompanyName(defaultSenderAddress.getCompanyName());
                addressModel.setContactName(defaultSenderAddress.getContactName());
                addressModel.setPhone(defaultSenderAddress.getPhone());
                addressModel.setEmail(defaultSenderAddress.getEmail());
                addressModel.setCountry(defaultSenderAddress.getCountry());
                addressModel.setAddress(defaultSenderAddress.getAddress1());
                addressModel.setAddress2(defaultSenderAddress.getAddress2());
                addressModel.setResidential(defaultSenderAddress.getIsResidential());
                addressModel.setCity(defaultSenderAddress.getCity());
                addressModel.setPostalCode(defaultSenderAddress.getPostalCode());
                addressModel.setState(defaultSenderAddress.getState());
            }
        } else {
            ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
            CustomerAddressVo customerAddressVo = customerAddressService.getCustomerAddressByCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
            CustomerAddressModel customerAddressModel = ModelUtils.createModelFromVo(customerAddressVo, CustomerAddressModel.class);
            if (customerAddressModel != null) {
                addressModel.setCompanyName(customerAddressModel.getCustomerName());
                addressModel.setContactName(customerAddressModel.getContactName());
                addressModel.setPhone(customerAddressModel.getPhone());
                addressModel.setEmail(customerAddressModel.getEmail());
                addressModel.setCountry(customerAddressModel.getCountry());
                addressModel.setAddress(customerAddressModel.getAddress1());
                addressModel.setAddress2(customerAddressModel.getAddress2());
                addressModel.setCity(customerAddressModel.getCity());
                addressModel.setPostalCode(customerAddressModel.getPostalCode());
                addressModel.setState(customerAddressModel.getStateCode());
            }
        }
        Long receiverAddressId = 0L;
        if (StringUtils.isNotBlank(this.getAddressId())) {
            receiverAddressId = Long.parseLong(this.getAddressId());
        } else {
            receiverAddressId = customerDefaultSetting.getDefaultToAddressId();
        }
        CustomerAddressBookVo receiverModelVo = addressService.getCustomerAddressBookById(receiverAddressId);
        CustomerAddressBookModel receiverModel = ModelUtils.createModelFromVo(receiverModelVo, CustomerAddressBookModel.class);
        AddressModel receiverAddress = new AddressModel();
        if (receiverModel != null) {
            receiverAddress.setCompanyName(receiverModel.getCompanyName());
            receiverAddress.setContactName(receiverModel.getContactName());
            receiverAddress.setPhone(receiverModel.getPhone());
            receiverAddress.setEmail(receiverModel.getEmail());
            receiverAddress.setCountry(receiverModel.getCountry());
            receiverAddress.setAddress(receiverModel.getAddress1());
            receiverAddress.setAddress2(receiverModel.getAddress2());
            receiverAddress.setResidential(receiverModel.getIsResidential());
            receiverAddress.setCity(receiverModel.getCity());
            receiverAddress.setPostalCode(receiverModel.getPostalCode());
            receiverAddress.setState(receiverModel.getState());
            receiverAddress.setResidential(receiverModel.getIsResidential());
        }
        else
        {
           // receiverAddress.setCountry(DEFAULT_COUNTRY);
        }
        if (!isReship) {
            this.shipmentPage.setSenderAddress(addressModel);
            this.shipmentPage.setReceiverAddress(receiverAddress);
        }
        this.setDefaultAddressJson(GsonUtils.toGson(addressModel));
    }

    protected void tranferDefaultSettingToShipmentRequestModel(CustomerDefaultSettingVo customerDefaultSetting, int serviceType) throws Exception {
        if (StringUtils.isEmpty(shipmentId)) {
            if (this.shipmentPage == null) {
                this.shipmentPage = new ShipmentInfoModel();
            }
            IAddressBookService addressService = new AddressBookServiceImp();
            CustomerAddressBookVo defaultSenderAddressVo = addressService.getCustomerAddressBookById(customerDefaultSetting.getDefaultFromAddressId());
            CustomerAddressBookVo receiverModelVo = addressService.getCustomerAddressBookById(customerDefaultSetting.getDefaultToAddressId());
            // Load default addresses
            CustomerAddressBookModel defaultSenderAddress = ModelUtils.createModelFromVo(defaultSenderAddressVo, CustomerAddressBookModel.class);
            AddressModel senderAddress = new AddressModel();
            if (defaultSenderAddressVo != null) {
                senderAddress.setCompanyName(defaultSenderAddress.getCompanyName());
                senderAddress.setContactName(defaultSenderAddress.getContactName());
                senderAddress.setPhone(defaultSenderAddress.getPhone());
                senderAddress.setEmail(defaultSenderAddress.getEmail());
                senderAddress.setCountry(defaultSenderAddress.getCountry());
                senderAddress.setAddress(defaultSenderAddress.getAddress1());
                senderAddress.setAddress2(defaultSenderAddress.getAddress2());
                senderAddress.setResidential(defaultSenderAddress.getIsResidential());
                senderAddress.setCity(defaultSenderAddress.getCity());
                senderAddress.setPostalCode(defaultSenderAddress.getPostalCode());
                senderAddress.setState(defaultSenderAddress.getState());
                this.shipmentPage.setSenderAddress(senderAddress);
            } else {
                ICustomerAddressService customerAddressService = new CustomerAddressServiceImp();
                CustomerAddressVo customerAddressVo = customerAddressService.getCustomerAddressByCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
                CustomerAddressModel customerAddressModel = ModelUtils.createModelFromVo(customerAddressVo, CustomerAddressModel.class);
                senderAddress.setCompanyName(customerAddressModel.getCustomerName());
                senderAddress.setContactName(customerAddressModel.getContactName());
                senderAddress.setPhone(customerAddressModel.getPhone());
                senderAddress.setEmail(customerAddressModel.getEmail());
                senderAddress.setCountry(customerAddressModel.getCountry());
                senderAddress.setAddress(customerAddressModel.getAddress1());
                senderAddress.setAddress2(customerAddressModel.getAddress2());
                senderAddress.setCity(customerAddressModel.getCity());
                senderAddress.setPostalCode(customerAddressModel.getPostalCode());
                senderAddress.setState(customerAddressModel.getStateCode());
            }
            CustomerAddressBookModel receiverModel = ModelUtils.createModelFromVo(receiverModelVo, CustomerAddressBookModel.class);
            AddressModel receiverAddress = new AddressModel();
            if (receiverModelVo != null) {
                receiverAddress.setCompanyName(receiverModel.getCompanyName());
                receiverAddress.setContactName(receiverModel.getContactName());
                receiverAddress.setPhone(receiverModel.getPhone());
                receiverAddress.setEmail(receiverModel.getEmail());
                receiverAddress.setCountry(receiverModel.getCountry());
                receiverAddress.setAddress(receiverModel.getAddress1());
                receiverAddress.setAddress2(receiverModel.getAddress2());
                receiverAddress.setResidential(receiverModel.getIsResidential());
                receiverAddress.setCity(receiverModel.getCity());
                receiverAddress.setPostalCode(receiverModel.getPostalCode());
                receiverAddress.setState(receiverModel.getState());
            }
            // Is return service
            if (serviceType == 1) {
                AddressModel temp = senderAddress;
                senderAddress = receiverAddress;
                receiverAddress = temp;
            } else if (serviceType == 2) {
                // Is third party
                senderAddress = new AddressModel();
                receiverAddress = new AddressModel();
            }
            // Ship to from Address Book page
            if (!StringUtils.isBlank(addressId)) {
                CustomerAddressBookVo addressBookVo = addressService.getCustomerAddressBookById(Long.valueOf(addressId));
                CustomerAddressBookModel addressBookModel = ModelUtils.createModelFromVo(addressBookVo, CustomerAddressBookModel.class);
                if (addressBookVo != null) {
                    receiverAddress.setCompanyName(addressBookModel.getCompanyName());
                    receiverAddress.setContactName(addressBookModel.getContactName());
                    receiverAddress.setPhone(addressBookModel.getPhone());
                    receiverAddress.setEmail(addressBookModel.getEmail());
                    receiverAddress.setCountry(addressBookModel.getCountry());
                    receiverAddress.setAddress(addressBookModel.getAddress1());
                    receiverAddress.setAddress2(addressBookModel.getAddress2());
                    receiverAddress.setResidential(addressBookModel.getIsResidential());
                    receiverAddress.setCity(addressBookModel.getCity());
                    receiverAddress.setPostalCode(addressBookModel.getPostalCode());
                    receiverAddress.setState(addressBookModel.getState());
                }
            }
            this.shipmentPage.setSenderAddress(senderAddress);
            this.shipmentPage.setReceiverAddress(receiverAddress);
        }
    }

    protected Integer determineCarrierType() {
        Integer carrierType = INTERNATIONAL_TYPE;
        Integer senderCountryId = 0;
        Integer receiverCountryId = 0;
        Integer defaultOriginCountry = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey("Default Origin Country"));
        if (this.carrierType == null) {
            if(this.getShipmentPage() != null
                    && this.shipmentPage.getSenderAddress() != null
                    && !StringUtils.isEmpty(this.shipmentPage.getSenderAddress().getCountry()))
                senderCountryId = Integer.parseInt(this.shipmentPage.getSenderAddress().getCountry());

            if(this.getShipmentPage() != null
                    && this.shipmentPage.getReceiverAddress() != null
                    && !StringUtils.isEmpty(this.shipmentPage.getReceiverAddress().getCountry()))
                receiverCountryId = Integer.parseInt(this.shipmentPage.getReceiverAddress().getCountry());

            if (Objects.equals(senderCountryId, receiverCountryId) && Objects.equals(senderCountryId, defaultOriginCountry) ||
                    senderCountryId != 0 && receiverCountryId == 0) {
                carrierType = DOMESTIC_TYPE;
            }
        } else {
            carrierType = Integer.parseInt(this.carrierType);
        }
        return carrierType;
    }

    protected boolean validateCustomerInfo()
    {
        Boolean hasError = false;

        if (StringUtils.isBlank(getShipmentPage().getSenderAddress().getCompanyName())) {
            this.addFieldError("shipmentRequest.senderAddress.companyName", "Sender's company name can't be empty.");
            hasError = true;
        }
        if (StringUtils.isBlank(getShipmentPage().getSenderAddress().getPhone())) {
            this.addFieldError("shipmentRequest.senderAddress.phone", "Sender's phone number can't be empty.");
            hasError = true;
        }

        if (StringUtils.isBlank(getShipmentPage().getSenderAddress().getAddress())) {
            this.addFieldError("shipmentRequest.senderAddress.address", "Sender's address line 1 can't be empty.");
            hasError = true;
        }

        if (StringUtils.isBlank(getShipmentPage().getReceiverAddress().getCompanyName())) {
            this.addFieldError("shipmentRequest.receiverAddress.companyName", "Receiver's company name can't be empty.");
            hasError = true;
        }
        if (StringUtils.isBlank(getShipmentPage().getReceiverAddress().getPhone())) {
            this.addFieldError("shipmentRequest.receiverAddress.phone", "Receiver's phone number can't be empty.");
            hasError = true;
        }

        if (StringUtils.isBlank(getShipmentPage().getReceiverAddress().getAddress())) {
            this.addFieldError("shipmentRequest.receiverAddress.address", "Receiver's address line 1 can't be empty.");
            hasError = true;
        }

        if ("72".equalsIgnoreCase(getShipmentPage().getServiceId())) {
            if (getShipmentPage().getReceiverAddress().getPhone().replaceAll("[^a-zA-Z0-9]", "").length() != 10) {
                this.addFieldError("shipmentRequest.receiverAddress.phone", "The receiver phone number have to include 10 numbers.");
                hasError = true;
            }
        }

        return !hasError;
    }

    protected Boolean validateShipmentRequest() throws DaoException {
        Boolean hasError = false;

        // Validate sender
        if (StringUtils.isBlank(getShipmentPage().getSenderAddress().getContactName())) {
            getShipmentPage().getSenderAddress().setContactName("");
        }
        if (StringUtils.isBlank(getShipmentPage().getSenderAddress().getCity())) {
            this.addFieldError("shipmentRequest.senderAddress.city", "Sender's city can't be empty.");
            hasError = true;
        }
        if (StringUtils.isBlank(getShipmentPage().getSenderAddress().getCountry()) || getShipmentPage().getSenderAddress().getCountry().equals("0")) {
            this.addFieldError("shipmentRequest.senderAddress.country", "Please select sender's country.");
            hasError = true;
        }
    /*
	 * if
	 * (StringUtils.isBlank(getShipmentPage().getSenderAddress().getState(
	 * ))) { this.addFieldError("shipmentRequest.senderAddress.state",
	 * "Sender's state can't be empty."); hasError = true; }
	 */
        if (!HelperUtils.isEmailAddress(getShipmentPage().getSenderAddress().getEmail()) && !StringUtils.isBlank(getShipmentPage().getSenderAddress().getEmail())) {
            this.addFieldError("shipmentRequest.senderAddress.email", "Sender's email is not valid.");
            hasError = true;
        }
        if ("72".equalsIgnoreCase(getShipmentPage().getServiceId())) {
            if (getShipmentPage().getSenderAddress().getPhone().replaceAll("[^a-zA-Z0-9]", "").length() != 10) {
                this.addFieldError("shipmentRequest.senderAddress.phone", "The sender phone number have to include 10 numbers.");
                hasError = true;
            }
        }
        // Validate receiver
        if (StringUtils.isBlank(getShipmentPage().getReceiverAddress().getContactName())) {
            getShipmentPage().getReceiverAddress().setContactName("");
        }
        if (StringUtils.isBlank(getShipmentPage().getReceiverAddress().getCity())) {
            this.addFieldError("shipmentRequest.receiverAddress.city", "Receiver's city can't be empty.");
            hasError = true;
        }
        if (StringUtils.isBlank(getShipmentPage().getReceiverAddress().getCountry()) || getShipmentPage().getReceiverAddress().getCountry().equals("0")) {
            this.addFieldError("shipmentRequest.receiverAddress.country", "Please select receiver's country.");
            hasError = true;
        }
	/*
	 * if
	 * (StringUtils.isBlank(getShipmentPage().getReceiverAddress().getState
	 * ())) { this.addFieldError("shipmentRequest.receiverAddress.state",
	 * "Receiver's state can't be empty."); hasError = true; }
	 */
        if (!HelperUtils.isEmailAddress(getShipmentPage().getReceiverAddress().getEmail()) && !StringUtils.isBlank(getShipmentPage().getReceiverAddress().getEmail())) {
            this.addFieldError("shipmentRequest.receiverAddress.email", "Receiver's email is not valid.");
            hasError = true;
        }

        // Validate others
        if (StringUtils.isBlank(getShipmentPage().getContentType())) {
            this.addFieldError("shipmentRequest.content", "Please select content type.");
            hasError = true;
        }
        if (StringUtils.equalsIgnoreCase(getShipmentPage().getShipmentTypeId(), "0")) {
            this.addFieldError("shipmentRequest.shipmentTypeId", "Please select a service type.");
            hasError = true;
        }
	/*
	 * Date shipmentDate =
	 * DateUtils.convertStringToDate(this.shipmentPage.getShipmentDate(),
	 * AppConstants.APP_SETTINGS.getDefaultDateFormat(), ""); Date now =
	 * Calendar.getInstance().getTime(); if (shipmentDate.before(now)) { if
	 * (!org.apache.commons.lang3.time.DateUtils.isSameDay(shipmentDate,
	 * now)) { this.addFieldError("shipmentRequest.shipmentDate",
	 * "Shipment date cannot before today."); hasError = true; } }
	 */
        List<PieceModel> pieces = shipmentPage.getPieces();
        for (int i = 0; i < pieces.size(); i++) {
            PieceModel piece = pieces.get(i);
            int pieceNumber = i + 1;
            if (StringUtils.isBlank(piece.getWeight())) {
                this.addFieldError("shipmentRequest.pieces[" + i + "].weight", "Weight for piece " + pieceNumber + " can't be empty.");
                hasError = true;
            }
            if (!NumberUtils.isNumber(piece.getWeight())) {
                this.addFieldError("shipmentRequest.pieces[" + i + "].weight", "Weight for piece " + pieceNumber + " must be a number.");
                hasError = true;
            } else if (Double.parseDouble(piece.getWeight()) <= 0) {
                this.addFieldError("shipmentRequest.pieces[" + i + "].weight", "Weight for piece " + pieceNumber + " must be larger than 0.");
                hasError = true;
            }
            if (this.shipmentPage.getIsAddPiece() != null && this.shipmentPage.getIsAddPiece().equalsIgnoreCase("true")) {
                if (StringUtils.isBlank(piece.getDimensionL())) {
                    this.addFieldError("shipmentRequest.pieces[" + i + "].dimensionL", "Dimension L for piece " + pieceNumber + " can't be empty.");
                    hasError = true;
                }
                if (!NumberUtils.isNumber(piece.getDimensionL())) {
                    this.addFieldError("shipmentRequest.pieces[" + i + "].dimensionL", "Dimension L for piece " + pieceNumber + " must be a number.");
                    hasError = true;
                }
                if (StringUtils.isBlank(piece.getDimensionW())) {
                    this.addFieldError("shipmentRequest.pieces[" + i + "].dimensionW", "Dimension W for piece " + pieceNumber + " can't be empty.");
                    hasError = true;
                }
                if (!NumberUtils.isNumber(piece.getDimensionW())) {
                    this.addFieldError("shipmentRequest.pieces[" + i + "].dimensionW", "Dimension W for piece " + pieceNumber + " must be a number.");
                    hasError = true;
                }
                if (StringUtils.isBlank(piece.getDimensionH())) {
                    this.addFieldError("shipmentRequest.pieces[" + i + "].dimensionH", "Dimension H for piece " + pieceNumber + " can't be empty.");
                    hasError = true;
                }
                if (!NumberUtils.isNumber(piece.getDimensionH())) {
                    this.addFieldError("shipmentRequest.pieces[" + i + "].dimensionH", "Dimension H for piece " + pieceNumber + " must be a number.");
                    hasError = true;
                }
                if (StringUtils.isBlank(piece.getQuantity()) || piece.getQuantity().equalsIgnoreCase("0")) {
                    this.addFieldError("shipmentRequest.pieces[" + i + "].quantity", "Quantify for piece " + pieceNumber + " is required.");
                    hasError = true;
                }
                if (!NumberUtils.isNumber(piece.getQuantity())) {
                    this.addFieldError("shipmentRequest.pieces[" + i + "].quantity", "Quantify for piece " + pieceNumber + " must be a number.");
                    hasError = true;
                }
            }
            if (piece.getCustomValue() != null && StringUtils.isBlank(piece.getCustomValue())) {
                this.addFieldError("shipmentRequest.pieces[" + i + "].customeValue", "Shipment value for piece " + pieceNumber + " can't be empty.");
                hasError = true;
            }
            if (piece.getCustomValue() != null && !NumberUtils.isNumber(piece.getCustomValue())) {
                this.addFieldError("shipmentRequest.pieces[" + i + "].customeValue", "Shipment value for piece " + pieceNumber + " must be a number.");
                hasError = true;
            }
        }
        return !hasError;
    }

    private void prepareReshipQuoteLog() throws Exception {
        buildQuoteLogData();
        IAccessorialSerivce accessorialService = new AccessorialServiceImp();
        ShipmentInfoModel shipmentPageN = new ShipmentInfoModel();
        IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
        ShipmentTypeModel shipmentTypeModel = new ShipmentTypeModel();
        ShipmentTypeVo shipmentTypeVo = new ShipmentTypeVo();
        shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(Integer.parseInt(quoteJob.getShipmentTypeId()));
        shipmentTypeModel = ModelUtils.createModelFromVo(shipmentTypeVo, ShipmentTypeModel.class);
        quoteJob.setShipmentType(shipmentTypeModel);
        AddressModel sender = quoteJob.getSenderAddress();
        AddressModel receiver = quoteJob.getReceiverAddress();
        shipmentPageN.setCustomerCode(quoteJob.getCustomerCode());
        shipmentPageN.setServiceId(quoteJob.getShipmentType().getServiceId());
        List<QuotePieceModel> quotePieceModels = quoteJob.getQuotePieces();
        List<PieceModel> listPieceModels = new ArrayList<PieceModel>();
        for (QuotePieceModel quotePieceModel : quotePieceModels) {
            PieceModel pieceModel = new PieceModel();
            if (quotePieceModel.getCustomValue() != null) {
                pieceModel.setCustomValue(quotePieceModel.getCustomValue());
            }
            if (quotePieceModel.getWeight() != null) {
                pieceModel.setWeight(quotePieceModel.getWeight());
            }
            if (quotePieceModel.getQuantity() != null) {
                pieceModel.setQuantity(quotePieceModel.getQuantity());
            }
            if (quotePieceModel.getDimensionH() != null) {
                pieceModel.setDimensionH(quotePieceModel.getDimensionH());
            }
            if (quotePieceModel.getDimensionW() != null) {
                pieceModel.setDimensionW(quotePieceModel.getDimensionW());
            }
            if (quotePieceModel.getDimensionL() != null) {
                pieceModel.setDimensionL(quotePieceModel.getDimensionL());
            }
            listPieceModels.add(pieceModel);
        }
        shipmentPageN.setPieces(listPieceModels);
        shipmentPageN.setSenderAddress(sender);
        shipmentPageN.setReceiverAddress(receiver);
        shipmentPageN.setContentType(quoteJob.getContents());
        if (quoteJob.getQuoteLogDetails() != null) {
            for (WebshipQuoteLogDetailModel accessorialModel : quoteJob.getQuoteLogDetails()) {
                AccessorialVo accessorialVo = new AccessorialVo();
                accessorialVo = accessorialService.selectById(Long.parseLong(accessorialModel.getAccessorialId()));
                if (accessorialVo != null) {
                    if (accessorialVo.getDescription().equals("Residential Delivery")) {
                        receiver.setResidential("true");
                    } else {
                        receiver.setResidential("false");
                    }
                    if (accessorialVo.getDescription().equals("Residential Pickup")) {
                        sender.setResidential("true");
                    } else {
                        sender.setResidential("false");
                    }
                    if (accessorialVo.getDescription().equals("Pre-Clearance")) {
                        sender.setResidential("true");
                    } else {
                        sender.setResidential("false");
                    }
                }
            }
        }
        this.setShipmentPage(shipmentPageN);
        this.setCarrierType(String.valueOf(this.determineCarrierType()));
    }

    private void buildQuoteLogData() throws Exception {
        IQuoteJobService service = new QuoteJobServiceImp();
        QuoteJobVo quoteJobVo = new QuoteJobVo();
        QuoteJobFilter quoteJobFilter = new QuoteJobFilter();
        quoteJobFilter.setQuoteId(Long.parseLong(this.quoteId));
        quoteJobVo = service.getQuoteJobDetail(quoteJobFilter);
        if (quoteJobVo != null) {
            List<WebshipQuoteLogDetailVo> detailVos = quoteJobVo.getQuoteLogDetails();
            if (quoteJobVo.getWithInsurance() != null && quoteJobVo.getWithInsurance() != 0) {
                WebshipQuoteLogDetailVo detailVo = new WebshipQuoteLogDetailVo();
                AccessorialVo accessorialVo = new AccessorialVo();
                accessorialVo.setDescription("Additional protection");
                detailVo.setAccessorial(accessorialVo);
                detailVo.setAmount(quoteJobVo.getWithInsurance());
                detailVos.add(detailVo);
            }
            quoteJobVo.setQuoteLogDetails(detailVos);
            QuoteJobModel quoteJob = ModelUtils.createModelFromVo(quoteJobVo, QuoteJobModel.class);
            this.quoteJob = quoteJob;
        } else {
            this.addActionError(ERROR);
            this.addActionMessage("Not found this quote Id");
        }
    }

    protected void prepareReshipHistory() throws Exception {
        prepareHistoryDetail();
        IHistoryAddressService iService = new HistoryAddressServiceImp();
        IShipmentService iServiceSm = new ShipmentServiceImp();
        ShipmentVo shipmentVo = iServiceSm.selectById(Long.parseLong(shipmentId));
        this.setShipmentModel(ModelUtils.createModelFromVo(shipmentVo, ShipmentModel.class));
        ShipmentInfoModel shipmentPageN = new ShipmentInfoModel();
        AddressModel sender = ModelUtils.createModelFromVo(iService.selectById(Integer.parseInt(detailInfoModel.getsAddressId())), AddressModel.class);
        AddressModel receiver = ModelUtils.createModelFromVo(iService.selectById(Integer.parseInt(detailInfoModel.getrAddressId())), AddressModel.class);
        shipmentPageN.setCustomerCode(detailInfoModel.getReferenceNo());
        shipmentPageN.setServiceId(detailInfoModel.getServiceId());
        shipmentPageN.setPieces(pieceModels);
        shipmentPageN.setSenderAddress(sender);
        shipmentPageN.setReceiverAddress(receiver);
        shipmentPageN.setPackageId(String.valueOf(shipmentVo.getPackageId()));
        String contentType = "DOX";
        if (!StringUtils.isBlank(this.getDetailInfoModel().getContents()) && this.getDetailInfoModel().getContents().equalsIgnoreCase("1")) {
            contentType = "WPX";
        }
        shipmentPageN.setContentType(contentType);
        if (detailAccessorialModels != null) {
            for (HistoryDetailAccessorialModel accessorialModel : detailAccessorialModels) {
                if (accessorialModel.getDescription().equals("Residential Delivery")) {
                    receiver.setResidential("true");
                } else {
                    receiver.setResidential("false");
                }
                if (accessorialModel.getDescription().equals("Residential Pickup")) {
                    sender.setResidential("true");
                } else {
                    sender.setResidential("false");
                }
                if (accessorialModel.getDescription().equals("Pre-Clearance")) {
                    sender.setResidential("true");
                } else {
                    sender.setResidential("false");
                }
            }
        }
        this.setShipmentPage(shipmentPageN);
        this.setCarrierType(String.valueOf(this.determineCarrierType()));
    }

    protected void prepareHistoryDetail() throws Exception {
        prepareHistoryDetailFilter();
        IHistoryDetailService detailService = new HistoryDetailServiceImp();
        IShipmentService shipmentService = new ShipmentServiceImp();
        HistoryDetailFilter filter = new HistoryDetailFilter();
        filter = ModelUtils.createVoFromModel(this.getDetailFilterModel(), HistoryDetailFilter.class);
        this.setDetailInfoModel(ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class));
        List<HistoryDetailAccessorialVo> listAccessorialVo = new ArrayList<HistoryDetailAccessorialVo>();
        listAccessorialVo = detailService.selectHistoryDetailAccessorial(filter);
        List<HistoryDetailAccessorialModel> detailAccessorialModels = new ArrayList<HistoryDetailAccessorialModel>();
        detailAccessorialModels = ModelUtils.createListModelFromVo(listAccessorialVo, HistoryDetailAccessorialModel.class);
        this.setDetailAccessorialModels(detailAccessorialModels);
        this.setShipmentModel(ModelUtils.createModelFromVo(shipmentService.selectById(Long.parseLong(shipmentId)), ShipmentModel.class));
        // Get pieces.
        PieceDao pieceDao = new PieceDao();
        List<PieceVo> pieceVos = pieceDao.selectGroupedPiecesByShipmentId(Long.valueOf(shipmentId));
        List<PieceModel> pieceModels = new ArrayList<PieceModel>();
        PieceModel pieceModel;
        for (PieceVo pieceVo : pieceVos) {
            pieceModel = new PieceModel();
            pieceModel.setDimensionH(String.valueOf(pieceVo.getDimensionH().intValue()));
            pieceModel.setDimensionL(String.valueOf(pieceVo.getDimensionL().intValue()));
            pieceModel.setDimensionW(String.valueOf(pieceVo.getDimensionW().intValue()));
            pieceModel.setWeight(String.valueOf(pieceVo.getDeadWeight()));
            pieceModel.setQuantity(String.valueOf(pieceVo.getQuantity()));
            pieceModel.setCustomValue("0");
            pieceModels.add(pieceModel);
        }
        this.setPieceModels(pieceModels);
        loadShipmentTypePackage(Integer.parseInt(detailInfoModel.getPackageId()), Integer.parseInt(detailInfoModel.getShipmentTypeId()));
    }

    protected void prepareHistoryDetailFilter() throws Exception {
        HistoryDetailFilterModel detailFilterModelN = new HistoryDetailFilterModel();
        detailFilterModelN.setShipmentId(shipmentId);
        detailFilterModelN.setLbToKg("0.45359237");
        detailFilterModelN.setInToCm("2.54");
        detailFilterModelN.setWeightValue("5000");
        IHistoryDetailService detailService = new HistoryDetailServiceImp();
        HistoryDetailInfoModel historyDetailInfoModelN = new HistoryDetailInfoModel();
        HistoryDetailFilter filter = new HistoryDetailFilter();
        filter = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
        historyDetailInfoModelN = ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filter), HistoryDetailInfoModel.class);
        detailFilterModelN.setWeightValue(ShipmentUtils.getForceVolWeight(Integer.parseInt(historyDetailInfoModelN.getServiceId())).toString());
        this.setDetailFilterModel(detailFilterModelN);
    }

    public boolean isQuotable() {
        // Get customer default settings
        CustomerDefaultSettingVo customerDefaultSetting = null;
        try {
            customerDefaultSetting = this.getCustomerDefaultSetting();
        } catch (Exception e) {
        }
        // Determine this customer was disabled quote or not?
        if (customerDefaultSetting == null || customerDefaultSetting.getDisableQuote() == null) {
            return true;
        }
        return !customerDefaultSetting.getDisableQuote();
    }

    public List<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public List<PackageModel> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<PackageModel> packageList) {
        this.packageList = packageList;
    }

    public ShipmentInfoModel getShipmentPage() {
        return shipmentPage;
    }

    public void setShipmentPage(ShipmentInfoModel shipmentPage) {
        this.shipmentPage = shipmentPage;
    }

    public ShipmentTypePackageModel getShipmentTypePackage() {
        return shipmentTypePackage;
    }

    public void setShipmentTypePackage(ShipmentTypePackageModel shipmentTypePackage) {
        this.shipmentTypePackage = shipmentTypePackage;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public List<ServiceModel> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceModel> serviceList) {
        this.serviceList = serviceList;
    }

    public List<ShipmentTypeModel> getShipmentTypeList() {
        return shipmentTypeList;
    }

    public void setShipmentTypeList(List<ShipmentTypeModel> shipmentTypeList) {
        this.shipmentTypeList = shipmentTypeList;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPrevPackageName() {
        return prevPackageName;
    }

    public void setPrevPackageName(String prevPackageName) {
        this.prevPackageName = prevPackageName;
    }

    public List<String> getDimensionUnitList() {
        return dimensionUnitList;
    }

    public void setDimensionUnitList(List<String> dimensionUnitList) {
        this.dimensionUnitList = dimensionUnitList;
    }

    public List<CurrencyModel> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyModel> currencyList) {
        this.currencyList = currencyList;
    }

    public List<String> getWeightUnitList() {
        return weightUnitList;
    }

    public void setWeightUnitList(List<String> weightUnitList) {
        this.weightUnitList = weightUnitList;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public List<PieceModel> getPieceModels() {
        return pieceModels;
    }

    public void setPieceModels(List<PieceModel> pieceModels) {
        this.pieceModels = pieceModels;
    }

    public HistoryDetailInfoModel getDetailInfoModel() {
        return detailInfoModel;
    }

    public void setDetailInfoModel(HistoryDetailInfoModel detailInfoModel) {
        this.detailInfoModel = detailInfoModel;
    }

    public HistoryDetailFilterModel getDetailFilterModel() {
        return detailFilterModel;
    }

    public void setDetailFilterModel(HistoryDetailFilterModel detailFilterModel) {
        this.detailFilterModel = detailFilterModel;
    }

    public List<HistoryDetailAccessorialModel> getDetailAccessorialModels() {
        return detailAccessorialModels;
    }

    public void setDetailAccessorialModels(List<HistoryDetailAccessorialModel> detailAccessorialModels) {
        this.detailAccessorialModels = detailAccessorialModels;
    }

    public ShipmentModel getShipmentModel() {
        return shipmentModel;
    }

    public void setShipmentModel(ShipmentModel shipmentModel) {
        this.shipmentModel = shipmentModel;
    }

    public String getDefaultCurrencyCode() {
        return defaultCurrencyCode;
    }

    public void setDefaultCurrencyCode(String defaultCurrencyCode) {
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public List<ServiceAddConModel> getListAdditionalConfig() {
        return listAdditionalConfig;
    }

    public void setListAdditionalConfig(List<ServiceAddConModel> listAdditionalConfig) {
        this.listAdditionalConfig = listAdditionalConfig;
    }

    public QuoteJobModel getQuoteJob() {
        return quoteJob;
    }

    public void setQuoteJob(QuoteJobModel quoteJob) {
        this.quoteJob = quoteJob;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getDefaultAddressJson() {
        return defaultAddressJson;
    }

    public void setDefaultAddressJson(String defaultAddressJson) {
        this.defaultAddressJson = defaultAddressJson;
    }

    public String getDefCloseTime() {
        return defCloseTime;
    }

    public void setDefCloseTime(String defCloseTime) {
        this.defCloseTime = defCloseTime;
    }

    

    public String getChangeCountryId() {
        return changeCountryId;
    }

    public void setChangeCountryId(String changeCountryId) {
        this.changeCountryId = changeCountryId;
    }

    public List<StateModel> getListStates() {
        return listStates;
    }

    public void setListStates(List<StateModel> listStates) {
        this.listStates = listStates;
    }

    public Boolean getIsSender() {
        return isSender;
    }

    public void setIsSender(Boolean isSender) {
        this.isSender = isSender;
    }

    public DimensionModel getDimension() {
        return dimension;
    }

    public void setDimension(DimensionModel dimension) {
        this.dimension = dimension;
    }

    public List<DimensionModel> getDimensionList() {
        return dimensionList;
    }

    public void setDimensionList(List<DimensionModel> dimensionList) {
        this.dimensionList = dimensionList;
    }

    public Integer getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Integer dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getDimensionResult() {
        return dimensionResult;
    }

    public void setDimensionResult(String dimensionResult) {
        this.dimensionResult = dimensionResult;
    }

    public String getSenderState() {
        return senderState;
    }

    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

	public Boolean getIsAglWarranty() {
		return isAglWarranty;
	}

	public void setIsAglWarranty(Boolean isAglWarranty) {
		this.isAglWarranty = isAglWarranty;
	}
    
}
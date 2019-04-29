package com.gms.xms.weblib.controller.webship.settings;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.*;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.persistence.service.billingtype.BillingTypeServiceImp;
import com.gms.xms.persistence.service.billingtype.IBillingTypeService;
import com.gms.xms.persistence.service.collectiontype.CollectionTypeServiceImp;
import com.gms.xms.persistence.service.collectiontype.ICollectionTypeService;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.persistence.service.tradetype.ITradeTypeService;
import com.gms.xms.persistence.service.tradetype.TradeTypeServiceImp;
import com.gms.xms.persistence.service.webship.settings.IUserSettingsService;
import com.gms.xms.persistence.service.webship.settings.UserSettingsServiceImp;
import com.gms.xms.txndb.vo.webship.*;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from UserSettingsController
 * <p>
 * Author DatTV Date Jul 9, 2015 2:33:44 PM
 */
public class UserSettingsController extends JsonBaseController {
    private static final long serialVersionUID = -3491572415649247950L;

    private List<BillingTypeModel> billingTypeList;
    private List<ShipmentTypeModel> shipmentTypeList;
    private List<PackageModel> packageList;
    private List<TradeTypeModel> tradeTypeList;
    private List<CollectionTypeModel> collectionTypeList;
    private CustomerDefaultSettingModel settings;
    private Integer hasAdminFunction;

    public String show() {
        this.setPageTitle("Settings");
        this.setBreadCrumb("Settings");
        try {

            loadCustomerDefaultSetting();
            prepareData();
            detectAdminFunction();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String changeService() {
        try {
            int id = -1;
            try {
                id = Integer.parseInt(settings.getDefaultServiceType());
            } catch (Exception ex) {
                id = -1;
            }
            preparePackageList(id);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void save() {
        IUserSettingsService userSettingsService = new UserSettingsServiceImp();
        try {
            // Check login
            WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
            if (webshipLoginVo == null) {
                throw new CustomException("You don't have permission to do this action.");
            }
            // Check null
            if (settings == null) {
                throw new CustomException("You must input the settings information.");
            }
            // Is new settings
            CustomerDefaultSettingVo defaultSettingVo = ModelUtils.createVoFromModel(settings, CustomerDefaultSettingVo.class);
            if (StringUtils.isBlank(settings.getCustomerCode())) {
                defaultSettingVo.setCustomerCode(webshipLoginVo.getCustomerCode());
                defaultSettingVo.setDisableQuote(true);
                defaultSettingVo.setGlobalAddressBook(false);
                defaultSettingVo.setIndividualUserHistory(true);
                userSettingsService.insert(this.getAddInfoMap(), defaultSettingVo);
            } else {
                userSettingsService.update(this.getAddInfoMap(), defaultSettingVo);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    private void loadCustomerDefaultSetting() throws Exception {
        // Check login
        WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
        if (webshipLoginVo == null) {
            throw new Exception("You don't have permission to do this action.");
        }

        // Load default settings
        IUserSettingsService settingsService = new UserSettingsServiceImp();
        CustomerDefaultSettingVo customerDefaultSettingVo = settingsService.selectByCustomerCode(webshipLoginVo.getCustomerCode());
        if (customerDefaultSettingVo == null) {
            customerDefaultSettingVo = new CustomerDefaultSettingVo();
        }
        this.settings = ModelUtils.createModelFromVo(customerDefaultSettingVo, CustomerDefaultSettingModel.class);
    }

    protected void detectAdminFunction() throws Exception {
        Long webshipId = this.getWebshipLoginInfo().getWebshipId();
        WebshipDao webshipDao = new WebshipDao();
        this.hasAdminFunction = webshipDao.hasAdminFunction(webshipId) == true ? 1 : 0;
    }

    private void prepareData() throws Exception {
        this.prepareBillingTypeList();
        this.prepareCollectionTypeList();
        if(settings.getDefaultServiceType() != null)
            this.preparePackageList(Integer.parseInt(settings.getDefaultServiceType()));
        else
            this.preparePackageList(getFirstShipmentTypeId());
        this.prepareShipmentTypeList();
        this.prepareTradeTypeList();
    }

    private Integer getFirstShipmentTypeId() {
        ShipmentTypeModel firstShipmentType = shipmentTypeList == null ? null : shipmentTypeList.get(0);
        int shipmentTypeId = firstShipmentType != null ? Integer.parseInt(firstShipmentType.getShipmentTypeId()) : -1;
        return shipmentTypeId;
    }

    private void prepareBillingTypeList() throws Exception {
        IBillingTypeService service = new BillingTypeServiceImp();
        List<BillingTypeVo> billingTypeVos = service.getBillingTypeList();
        List<BillingTypeModel> billingTypeList = ModelUtils.createListModelFromVo(billingTypeVos, BillingTypeModel.class);
        this.billingTypeList = billingTypeList;
    }

    private void prepareShipmentTypeList() throws Exception {
        WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
        if (webshipLoginVo == null) {
            throw new Exception("You don't have permission to do this action.");
        }
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        List<ShipmentTypeVo> shipmentTypeVos = service.getShipmentTypeByCustomerCode(String.valueOf(webshipLoginVo.getCustomerCode()));
        List<ShipmentTypeModel> shipmentTypeModels = ModelUtils.createListModelFromVo(shipmentTypeVos, ShipmentTypeModel.class);
        this.shipmentTypeList = shipmentTypeModels;
    }

    private void preparePackageList(Integer shipmentTypeId) throws Exception {
        IPackageService service = new PackageServiceImp();
        List<PackageVo> packageVos = service.getPackageListByShipmentTypeId(shipmentTypeId);
        List<PackageModel> packageModelList = ModelUtils.createListModelFromVo(packageVos, PackageModel.class);
        this.packageList = packageModelList;
    }

    private void prepareTradeTypeList() throws Exception {
        ITradeTypeService service = new TradeTypeServiceImp();
        List<TradeTypeVo> tradeTypeVos = service.getTradeTypeList();
        List<TradeTypeModel> tradeTypeList = ModelUtils.createListModelFromVo(tradeTypeVos, TradeTypeModel.class);
        this.tradeTypeList = tradeTypeList;
    }

    private void prepareCollectionTypeList() throws Exception {
        ICollectionTypeService service = new CollectionTypeServiceImp();
        List<CollectionTypeVo> collectionTypeVos = service.getCollectionTypeList();
        List<CollectionTypeModel> collectionTypeModels = ModelUtils.createListModelFromVo(collectionTypeVos, CollectionTypeModel.class);
        this.collectionTypeList = collectionTypeModels;
    }

    public List<BillingTypeModel> getBillingTypeList() {
        return billingTypeList;
    }

    public void setBillingTypeList(List<BillingTypeModel> billingTypeList) {
        this.billingTypeList = billingTypeList;
    }

    public List<ShipmentTypeModel> getShipmentTypeList() {
        return shipmentTypeList;
    }

    public void setShipmentTypeList(List<ShipmentTypeModel> shipmentTypeList) {
        this.shipmentTypeList = shipmentTypeList;
    }

    public List<PackageModel> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<PackageModel> packageList) {
        this.packageList = packageList;
    }

    public List<TradeTypeModel> getTradeTypeList() {
        return tradeTypeList;
    }

    public void setTradeTypeList(List<TradeTypeModel> tradeTypeList) {
        this.tradeTypeList = tradeTypeList;
    }

    public List<CollectionTypeModel> getCollectionTypeList() {
        return collectionTypeList;
    }

    public void setCollectionTypeList(List<CollectionTypeModel> collectionTypeList) {
        this.collectionTypeList = collectionTypeList;
    }

    public CustomerDefaultSettingModel getSettings() {
        return settings;
    }

    public void setSettings(CustomerDefaultSettingModel settings) {
        this.settings = settings;
    }

    public Integer getHasAdminFunction() {
        return hasAdminFunction;
    }
}
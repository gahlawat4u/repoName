package com.gms.xms.weblib.controller.admin.customerprofiles;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.model.*;
import com.gms.xms.model.admin.administration.RateSheetColModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.ratesheet.IRateSheetService;
import com.gms.xms.persistence.service.ratesheet.RateSheetServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.utils.BaseRateUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Posted from CustomerProfilesBaseRateController
 * <p>
 * Author TANDT 28-10-2015
 */
public class CustomerProfilesBaseRateController extends CustomerProfilesAccountSetupController {
    private static final long serialVersionUID = -7110570240797000983L;

    private List<ServiceModel> services;
    private HashMap<String, String> listRateType;
    private List<ShipmentTypeModel> shipmentTypes;
    private HashMap<String, RateSheetColModel> rateSheets;
    protected String serviceId;
    protected String sheetId;
    protected String sheetName;
    protected RateSheetModel rateSheet;
    protected RateSheetModel rateSheetPer;
    protected CustomerModel customer;
    protected FranchiseModel franchise;
    private List<String> listPageSize;

    public String indexBaseRate() {
        try {
            if (this.preapareCustomerDetail()) {
                preapareListServiceActive();
                return "success";
            } else {
                return "error";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    public String showRateSheet() {
        try {

            if (!preapareSheetDetail()) {
                return "error";
            } else {
                preapareCustomerDetail();
                recalRateSheet();
                String pageSizeList = AppConstants.APP_SETTINGS.getPageSizeList();
                String[] pageSizes = pageSizeList.trim().split(",");
                this.setListPageSize(Arrays.asList(pageSizes));
                this.setSheetName(sheetName);
                return "success";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    protected boolean preapareCustomerDetail() throws Exception {
        if (StringUtils.isNotEmpty(franchiseCode)) {
            IFranchiseService serviceF = new FranchiseServiceImp();
            ManageFranchiseFilter filterF = new ManageFranchiseFilter();
            filterF.setFranchiseCode(Long.parseLong(franchiseCode.concat("000001")));
            this.setFranchise(ModelUtils.createModelFromVo(serviceF.selectFranchiseByFilter(filterF), FranchiseModel.class));

        } else {
            setErrorMessage("Please enter customer name");
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("Please enter customer name");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    protected void recalRateSheet() throws Exception {
        if (customer != null) {
            if (rateSheet != null) {
                for (RateSheetDetailModel rateSheetDetailModel : rateSheet.getRateSheetDetails()) {
                    if (StringUtils.isNotEmpty(franchise.getMinimunBaseCharge()) && (rateSheet.getShipmentType().getServiceId().equals("1") || rateSheet.getShipmentType().getServiceId().equals("15"))) {
                        rateSheetDetailModel.setValue(BaseRateUtils.recalRateSheet(rateSheetDetailModel.getValue(), "2", franchise.getMarkupRate()));
                    }
                    if (StringUtils.isNotEmpty(franchise.getTntMarkupRate()) && rateSheet.getShipmentType().getServiceId().equals("3")) {
                        rateSheetDetailModel.setBaseCharge(BaseRateUtils.recalRateSheet(rateSheetDetailModel.getBaseCharge(), "2", franchise.getTntMarkupRate()));
                        rateSheetDetailModel.setMinCharge(BaseRateUtils.recalRateSheet(rateSheetDetailModel.getMinCharge(), "2", franchise.getTntMarkupRate()));
                        rateSheetDetailModel.setPerKg(BaseRateUtils.recalRateSheet(rateSheetDetailModel.getPerKg(), "2", franchise.getTntMarkupRate()));
                    }
                    if (StringUtils.isNotEmpty(franchise.getTntInternationalMarkupRate()) && rateSheet.getShipmentType().getServiceId().equals("54")) {
                        rateSheetDetailModel.setValue(BaseRateUtils.recalRateSheet(rateSheetDetailModel.getValue(), "2", franchise.getTntInternationalMarkupRate()));
                    }
                    if (StringUtils.isNotEmpty(franchise.getTollMarkupRate()) && rateSheet.getShipmentType().getServiceId().equals("52")) {
                        rateSheetDetailModel.setValue(BaseRateUtils.recalRateSheet(rateSheetDetailModel.getValue(), "2", franchise.getTollMarkupRate()));
                    }
                    if (StringUtils.isNotEmpty(franchise.getTollIpecMarkupRate()) && rateSheet.getShipmentType().getServiceId().equals("59")) {
                        rateSheetDetailModel.setValue(BaseRateUtils.recalRateSheet(rateSheetDetailModel.getValue(), "2", franchise.getTollIpecMarkupRate()));
                    }
                }
            }
        }
    }

    protected boolean preapareSheetDetail() throws Exception {
        if (StringUtils.isEmpty(sheetId)) {
            setErrorMessage("Not found Sheet Id Data");
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError("Not found Sheet Id Data");
            return false;
        } else {
            IRateSheetService service = new RateSheetServiceImp();
            this.setRateSheet(ModelUtils.createModelFromVo(service.selectRateSheetFull(Long.parseLong(sheetId)), RateSheetModel.class));
            if (rateSheet != null) {

                if (rateSheet.getContent().equals("1")) {

                    if (rateSheet.getBound().equals("0")) {
                        if (StringUtils.isNotEmpty(rateSheet.getShipmentType().getCarrierPackageRate())) {
                            this.setRateSheet(ModelUtils.createModelFromVo(service.selectRateSheetFull(Long.parseLong(rateSheet.getShipmentType().getCarrierPackageRate())), RateSheetModel.class));
                        }
                        if (StringUtils.isNotEmpty(rateSheet.getShipmentType().getCarrierPackagePerWeightRate())) {
                            this.setRateSheetPer(ModelUtils.createModelFromVo(service.selectRateSheetFull(Long.parseLong(rateSheet.getShipmentType().getCarrierPackagePerWeightRate())), RateSheetModel.class));
                        }
                    }

                    if (rateSheet.getBound().equals("1")) {
                        if (StringUtils.isNotEmpty(rateSheet.getShipmentType().getCarrierPackageInboundRate())) {
                            this.setRateSheet(ModelUtils.createModelFromVo(service.selectRateSheetFull(Long.parseLong(rateSheet.getShipmentType().getCarrierPackageInboundRate())), RateSheetModel.class));
                        }
                        if (StringUtils.isNotEmpty(rateSheet.getShipmentType().getCarrierPackageInboundPerWeightRate())) {
                            this.setRateSheetPer(ModelUtils.createModelFromVo(service.selectRateSheetFull(Long.parseLong(rateSheet.getShipmentType().getCarrierPackageInboundPerWeightRate())), RateSheetModel.class));
                        }
                    }
                    if (rateSheet.getShipmentTypeId().equals("122")) {
                        rateSheet = null;
                    }

                }
                if (rateSheet.getShipmentType().getServiceId().equals("3")) {
                    this.setRateSheet(ModelUtils.createModelFromVo(service.selectRateSheetFullTntDom(Long.parseLong(sheetId)), RateSheetModel.class));
                }

            }
        }
        return true;
    }

    public String loadBaseRate() {
        try {
            prepareListRateType();
            prepareListServiType();
            prepareDataForIndexBaseRate();
            if (rateSheets != null) {
                for (RateSheetColModel value : rateSheets.values()) {
                    if (value.getShipmentType().getShowStatus().equals("0")) {
                        return "nonratesheet";
                    } else {
                        return "ratesheet";
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "ratesheet";
    }

    private void prepareDataForIndexBaseRate() throws Exception {
        rateSheets = BaseRateUtils.getBaseRateByShipmentTypes(shipmentTypes, profileId, "");
    }

    private void preapareListServiceActive() throws Exception {
        IServiceService sv = new ServiceServiceImp();
        CustomerProfileFilter filter = new CustomerProfileFilter();
        String activeCarrierList = AppConstants.APP_SETTINGS.getActiveCarriers();
        filter.setListServices(activeCarrierList);
        services = ModelUtils.createListModelFromVo(sv.getActiveServicesRe(filter), ServiceModel.class);
    }

    private void prepareListServiType() throws Exception {
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        ShipmentTypeFilter filter = new ShipmentTypeFilter();
        filter.setServiceId(Integer.parseInt(serviceId));
        List<ShipmentTypeVo> shipmentTypeVos = service.getShipmentTypeListByServiceId(filter);
        shipmentTypes = ModelUtils.createListModelFromVo(shipmentTypeVos, ShipmentTypeModel.class);
    }

    private void prepareListRateType() throws Exception {
        HashMap<String, String> rateTypes = new HashMap<String, String>();
        rateTypes.put("0", "DHL");
        rateTypes.put("1", "% Markup");
        rateTypes.put("2", "% Margin");
        rateTypes.put("3", "% Topup");
        this.setListRateType(rateTypes);
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

    public HashMap<String, String> getListRateType() {
        return listRateType;
    }

    public void setListRateType(HashMap<String, String> listRateType) {
        this.listRateType = listRateType;
    }

    public List<ShipmentTypeModel> getShipmentTypes() {
        return shipmentTypes;
    }

    public void setShipmentTypes(List<ShipmentTypeModel> shipmentTypes) {
        this.shipmentTypes = shipmentTypes;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public HashMap<String, RateSheetColModel> getRateSheets() {
        return rateSheets;
    }

    public void setRateSheets(HashMap<String, RateSheetColModel> rateSheets) {
        this.rateSheets = rateSheets;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public RateSheetModel getRateSheet() {
        return rateSheet;
    }

    public void setRateSheet(RateSheetModel rateSheet) {
        this.rateSheet = rateSheet;
    }

    public RateSheetModel getRateSheetPer() {
        return rateSheetPer;
    }

    public void setRateSheetPer(RateSheetModel rateSheetPer) {
        this.rateSheetPer = rateSheetPer;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public FranchiseModel getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseModel franchise) {
        this.franchise = franchise;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

}
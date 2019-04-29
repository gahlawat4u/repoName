package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.model.CustomerModel;
import com.gms.xms.model.FranchiseModel;
import com.gms.xms.model.RateSheetDetailModel;
import com.gms.xms.model.RateSheetModel;
import com.gms.xms.model.account.customers.manage.ManageCustomerBaseRateModel;
import com.gms.xms.model.account.customers.manage.SaveCustomerBaseRateModel;
import com.gms.xms.model.admin.administration.RateSheetColModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.persistence.service.ratesheet.IRateSheetService;
import com.gms.xms.persistence.service.ratesheet.RateSheetServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.PackageTypeVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.account.customers.manage.ManageCustomerBaseRateVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.utils.BaseRateUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Posted from ManageCustomerBaseRateController
 * <p>
 * Author DatTV Sep 17, 2015
 */
public class ManageCustomerBaseRateController extends JsonBaseController {

    private static final long serialVersionUID = 2084885792816914799L;

    private String customerCode;
    private String shipmentId;
    private String airbillNumber;
    private ManageCustomerBaseRateModel baseRate;
    private HashMap<String, String> listRateType;
    private String sheetId;
    protected RateSheetModel rateSheet;
    protected RateSheetModel rateSheetPer;
    protected CustomerModel customer;
    protected FranchiseModel franchise;
    private String sheetName;
    private List<String> listPageSize;
    private String shipmentTypeId;

    private SaveCustomerBaseRateModel saveBaseRate;

    private List<CustomerBaseRateVo> customerBaseRates;

    public String show() {
        try {
            prepareListRateType();
            loadData();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    private void loadData() throws Exception {
        ManageCustomerBaseRateVo baseRateVo = new ManageCustomerBaseRateVo();
        ICustomerService customerService = new CustomerServiceImp();
        // Load minimum base charge
        CustomerVo customerVo = customerService.selectByCode(customerCode);
        if (customerVo == null) {
            throw new Exception("Please choose a customer");
        }
        baseRateVo.setCustomerCode(customerVo.getCustomerCode());
        baseRateVo.setMinimunBaseCharge(customerVo.getMinimunBaseCharge());
        // Load active services/carriers
        IServiceService iService = new ServiceServiceImp();
        List<ServiceVo> serviceVos = iService.getActiveServicesWithBaseRates(customerCode);
        this.removeNotShowPackages(serviceVos);
        baseRateVo.setServices(serviceVos);
        ManageCustomerBaseRateModel baseRateModel = ModelUtils.createModelFromVo(baseRateVo, ManageCustomerBaseRateModel.class);
        this.setBaseRate(baseRateModel);
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

    public String saveBaseRate() throws Exception {
        try {
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(Attributes.CUSTOMER_BASE_RATE, ModelUtils.createListVoFromModel(saveBaseRate.getCustomerBaseRates(), CustomerBaseRateVo.class));
            context.put(Attributes.WFP_NAME, "Wfl-ManageFranchise-SaveBaseRate");
            context = WorkFlowManager2.getInstance().process(context);
            if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean preapareCustomerDetail() throws Exception {
        if (StringUtils.isNotEmpty(customerCode)) {
            if (customerCode.equals("0")) {
                CustomerModel customerN = new CustomerModel();
                customerN.setRegistrationId("0");
                customerN.setDhlAccount("3p");
                customerN.setTntAccount("3p");
                customerN.setTollPriorityAccount("3p");
                customerN.setWebshipGroupId("0");
                customerN.setInvoiceTerms("3");
                customerN.setGstId("0");
                customerN.setEmailInvoice("1");
                customerN.setInvoiceLateFee("0");
                customerN.setInvoiceSorting("0.00");
                this.setCustomer(customerN);
            } else {
                ICustomerService service = new CustomerServiceImp();
                ManageFranchiseFilter filter = new ManageFranchiseFilter();
                filter.setCustomerCode(Long.parseLong(customerCode));
                this.setCustomer(ModelUtils.createModelFromVo(service.selectCustomerByFilter(filter), CustomerModel.class));

                IFranchiseService serviceF = new FranchiseServiceImp();
                ManageFranchiseFilter filterF = new ManageFranchiseFilter();
                filterF.setFranchiseCode(Long.parseLong(StringUtils.substring(customerCode, 0, 3).concat("00001")));
                this.setFranchise(ModelUtils.createModelFromVo(serviceF.selectFranchiseByFilter(filterF), FranchiseModel.class));
            }

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
        if (!StringUtils.isEmpty(customerCode) && !StringUtils.isEmpty(airbillNumber) && !StringUtils.isEmpty(shipmentId)) {
            ShipmentTypeDao dao = new ShipmentTypeDao();
            ShipmentTypeVo shipmentType = new ShipmentTypeVo();
            if (shipmentTypeId != null) {
                shipmentType = dao.selectById(Integer.parseInt(shipmentTypeId));
            } else {
                shipmentType = dao.selectById(1);
            }
            ShipmentTypeModel shipmentTypeModel = ModelUtils.createModelFromVo(shipmentType, ShipmentTypeModel.class);
            List<ShipmentTypeModel> listShipmentType = new ArrayList<ShipmentTypeModel>();
            listShipmentType.add(shipmentTypeModel);
            HashMap<String, RateSheetColModel> baseRateResult = BaseRateUtils.getBaseRateByShipmentTypes(listShipmentType, "", "10000001");
            for (RateSheetColModel model : baseRateResult.values()) {
                this.setSheetId(model.getCarrierRateSheetCols().get(0).getSheetId());
            }
        }
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
                if (rateSheet.getShipmentType() != null) {
                    sheetName = rateSheet.getShipmentType().getShipmentTypeName();
                }
            }
        }
        return true;
    }

    private void removeNotShowPackages(List<ServiceVo> services) throws DaoException {
        for (ServiceVo service : services) {
            List<ShipmentTypeVo> shipmentTypes = service.getShipmentTypes();
            for (ShipmentTypeVo shipmentType : shipmentTypes) {
                List<PackageTypeVo> packageTypes = shipmentType.getPackageTypes();
                List<PackageTypeVo> removePackages = new ArrayList<PackageTypeVo>();
                for (PackageTypeVo packageType : packageTypes) {
                    boolean showPackage = false;
                    switch (packageType.getPackageType()) {
                        case 0: // Non Type
                            if (!shipmentType.getAllowCarrier() && !shipmentType.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentType.getAllowCarrier()) {
                                    if (shipmentType.getCarrierDocumentRate() != 0) {
                                        showPackage = true;
                                        packageType.setCarrierSheetId(shipmentType.getCarrierDocumentRate());
                                    }
                                }
                                if (shipmentType.getAllowNonCarrier()) {
                                    if (shipmentType.getNonCarrierDocumentRate() != 0) {
                                        showPackage = true;
                                        packageType.setNonCarrierSheetId(shipmentType.getNonCarrierDocumentRate());
                                    }
                                }
                            }
                            break;
                        case 1: // Document outbound
                            if (!shipmentType.getAllowCarrier() && !shipmentType.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentType.getAllowCarrier()) {
                                    if (shipmentType.getCarrierDocumentRate() != 0) {
                                        showPackage = true;
                                        packageType.setCarrierSheetId(shipmentType.getCarrierDocumentRate());
                                    }
                                }
                                if (shipmentType.getAllowNonCarrier()) {
                                    if (shipmentType.getNonCarrierDocumentRate() != 0) {
                                        showPackage = true;
                                        packageType.setNonCarrierSheetId(shipmentType.getNonCarrierDocumentRate());
                                    }
                                }
                            }
                            break;
                        case 2: // Document inbound
                            if (!shipmentType.getAllowCarrier() && !shipmentType.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentType.getAllowCarrier()) {
                                    if (shipmentType.getCarrierDocumentInboundRate() != 0) {
                                        showPackage = true;
                                        packageType.setCarrierSheetId(shipmentType.getCarrierDocumentInboundRate());
                                    }
                                }
                                if (shipmentType.getAllowNonCarrier()) {
                                    if (shipmentType.getNonCarrierDocumentInboundRate() != 0) {
                                        showPackage = true;
                                        packageType.setNonCarrierSheetId(shipmentType.getNonCarrierDocumentInboundRate());
                                    }
                                }
                            }
                            break;
                        case 3: // Package outbound
                            if (!shipmentType.getAllowCarrier() && !shipmentType.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentType.getAllowCarrier()) {
                                    if (shipmentType.getCarrierPackageRate() != 0 || shipmentType.getCarrierPackagePerWeightRate() != 0) {
                                        showPackage = true;
                                        if (shipmentType.getCarrierPackageRate() != 0) {
                                            packageType.setCarrierSheetId(shipmentType.getCarrierPackageRate());
                                        } else if (shipmentType.getCarrierPackagePerWeightRate() != 0) {
                                            packageType.setCarrierSheetId(shipmentType.getCarrierPackagePerWeightRate());
                                        }
                                    }
                                }
                                if (shipmentType.getAllowNonCarrier()) {
                                    if (shipmentType.getNonCarrierPackageRate() != 0) {
                                        showPackage = true;
                                        packageType.setNonCarrierSheetId(shipmentType.getNonCarrierPackageRate());
                                    }
                                }
                            }
                            break;
                        case 4: // Package inbound
                            if (!shipmentType.getAllowCarrier() && !shipmentType.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentType.getAllowCarrier()) {
                                    if (shipmentType.getCarrierPackageInboundRate() != 0 || shipmentType.getCarrierPackageInboundPerWeightRate() != 0) {
                                        showPackage = true;
                                        if (shipmentType.getCarrierPackageInboundRate() != 0) {
                                            packageType.setCarrierSheetId(shipmentType.getCarrierPackageInboundRate());
                                        } else if (shipmentType.getCarrierPackageInboundPerWeightRate() != 0) {
                                            packageType.setCarrierSheetId(shipmentType.getCarrierPackageInboundPerWeightRate());
                                        }
                                    }
                                }
                                if (shipmentType.getAllowNonCarrier()) {
                                    if (shipmentType.getNonCarrierPackageInboundRate() != 0) {
                                        showPackage = true;
                                        packageType.setNonCarrierSheetId(shipmentType.getNonCarrierPackageInboundRate());
                                    }
                                }
                            }
                            break;
                        case 5: // Pak outbound
                            if (!shipmentType.getAllowCarrier() && !shipmentType.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentType.getAllowCarrier()) {
                                    if (shipmentType.getCarrierPakRate() != 0) {
                                        showPackage = true;
                                        packageType.setCarrierSheetId(shipmentType.getCarrierPakRate());
                                    }
                                }
                                if (shipmentType.getAllowNonCarrier()) {
                                    if (shipmentType.getNonCarrierPakRate() != 0) {
                                        showPackage = true;
                                        packageType.setNonCarrierSheetId(shipmentType.getNonCarrierPakRate());
                                    }
                                }
                            }
                            break;
                        case 6: // Pak inbound
                            if (!shipmentType.getAllowCarrier() && !shipmentType.getAllowNonCarrier()) {
                                showPackage = true;
                            } else {
                                if (shipmentType.getAllowCarrier()) {
                                    if (shipmentType.getCarrierPakInboundRate() != 0) {
                                        showPackage = true;
                                        packageType.setCarrierSheetId(shipmentType.getCarrierPakInboundRate());
                                    }
                                }
                                if (shipmentType.getAllowNonCarrier()) {
                                    if (shipmentType.getNonCarrierPakInboundRate() != 0) {
                                        showPackage = true;
                                        packageType.setNonCarrierSheetId(shipmentType.getNonCarrierPakInboundRate());
                                    }
                                }
                            }
                            break;
                    }
                    if (!showPackage) {
                        removePackages.add(packageType);
                    } else {
                        IRateSheetService rateSheetService = new RateSheetServiceImp();
                        // Get carrier zone list
                        if (packageType.getCarrierSheetId() != null) {
                            packageType.setCarrierZone(rateSheetService.getColumnsBySheetId(packageType.getCarrierSheetId()));
                        }
                        // Get non carrier zone list
                        if (packageType.getNonCarrierSheetId() != null) {
                            packageType.setNonCarrierZone(rateSheetService.getColumnsBySheetId(packageType.getNonCarrierSheetId()));
                        }
                        boolean hasZone = packageType.getCarrierZone() != null || packageType.getNonCarrierZone() != null;
                        packageType.setHasZone(hasZone);
                    }
                } // End of package types loop
                if (removePackages.size() > 0) {
                    packageTypes.removeAll(removePackages);
                }
            }
        }
    }

    private void prepareListRateType() throws Exception {
        HashMap<String, String> rateTypes = new HashMap<String, String>();
        rateTypes.put("0", "DHL");
        rateTypes.put("1", "% Markup");
        rateTypes.put("2", "% Margin");
        rateTypes.put("3", "% Topup");
        this.setListRateType(rateTypes);
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public ManageCustomerBaseRateModel getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(ManageCustomerBaseRateModel baseRate) {
        this.baseRate = baseRate;
    }

    public HashMap<String, String> getListRateType() {
        return listRateType;
    }

    public void setListRateType(HashMap<String, String> listRateType) {
        this.listRateType = listRateType;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
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

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public List<CustomerBaseRateVo> getCustomerBaseRates() {
        return customerBaseRates;
    }

    public void setCustomerBaseRates(List<CustomerBaseRateVo> customerBaseRates) {
        this.customerBaseRates = customerBaseRates;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public SaveCustomerBaseRateModel getSaveBaseRate() {
        return saveBaseRate;
    }

    public void setSaveBaseRate(SaveCustomerBaseRateModel saveBaseRate) {
        this.saveBaseRate = saveBaseRate;
    }
}

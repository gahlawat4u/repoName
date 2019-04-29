package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.model.CarrierZoneModel;
import com.gms.xms.model.FranchiseModel;
import com.gms.xms.model.account.customers.manage.SaveCustomerBaseRateModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.CarrierZoneDao;
import com.gms.xms.persistence.dao.admin.customer.baserate.ManageCustBaseRateDao;
import com.gms.xms.persistence.service.baserate.BaseRateServiceImp;
import com.gms.xms.persistence.service.baserate.IBaseRateService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.model.admin.customer.baserate.OtherCustBaseRateModel;
import com.gms.xms.txndb.model.admin.customer.baserate.ServiceTypeModel;
import com.gms.xms.txndb.vo.CarrierZoneVo;
import com.gms.xms.txndb.vo.FranchiseVo;
import com.gms.xms.txndb.vo.RateType;
import com.gms.xms.txndb.vo.account.customers.manage.SaveCustomerBaseRateVo;
import com.gms.xms.txndb.vo.admin.customer.baserate.*;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Apr 7, 2016 9:16:55 AM
 * <p>
 * Author dattrinh
 */
public class ManageFranBaseRateController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private String franchiseCode;
    private FranchiseModel franchise;
    private List<ServiceTypeModel> dhl;
    private List<ServiceTypeModel> dhlDom;
    private List<ServiceTypeModel> tntDom;
    private List<ServiceTypeModel> tntIntl;
    private List<ServiceTypeModel> ups;
    private List<ServiceTypeModel> tollPriority;
    private List<ServiceTypeModel> tollIpec;
    private List<ServiceTypeModel> starTrack;
    private List<OtherCustBaseRateModel> others;
    private SaveCustomerBaseRateModel saveFranBaseRate;
    private List<CarrierZoneModel> tntDomColumns;
    private List<CarrierZoneModel> starTrackColumns;

    public String show() {
        try {
            // Validate franchise code.
            if (StringUtils.isBlank(this.getFranchiseCode())) {
                throw new CustomException("Please choose a franchise.");
            }
            // Load info of tabs.
            loadFranchise();
            loadDHL();
            loadDHLDom();
            loadTNTDom();
            loadTNTIntl();
            loadTollPriority();
            loadTollIpec();
            loadUPS();
            loadStarTrack();
            loadOthers();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doSaveBaseRate() {
        try {
            if (this.getSaveFranBaseRate() == null) {
                throw new CustomException("No franchise base rates information for saving.");
            }
            // Convert to vo.
            SaveCustomerBaseRateVo baseRateVo = ModelUtils.createVoFromModel(this.getSaveFranBaseRate(), SaveCustomerBaseRateVo.class);
            IBaseRateService baseRateService = new BaseRateServiceImp();
            baseRateService.saveFranBaseRates(this.getAddInfoMap(), baseRateVo);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private List<RateType> prepareRateTypes(Double weight, Integer serviceId) {
        List<RateType> rateTypes = new ArrayList<RateType>();
        switch (serviceId)
        {
            case 1:
                rateTypes.add(new RateType(0, "DHL"));
                break;
            case 400:
                rateTypes.add(new RateType(0, "UPS"));
                break;
        }

        rateTypes.add(new RateType(1, "% Markup"));
        rateTypes.add(new RateType(2, "% Margin"));
        if(weight!=0)
        {
            rateTypes.add(new RateType(3, "% Topup"));
        }
        return rateTypes;
    }

    private void loadFranchise() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        FranchiseVo franchiseVo = franchiseService.selectFranchiseByFranchiseCode(this.getFranchiseCode().substring(0, 3));
        FranchiseModel franchiseModel = ModelUtils.createModelFromVo(franchiseVo, FranchiseModel.class);
        this.setFranchise(franchiseModel);
    }

    protected void loadDHL() throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        // Get all service type of DHL carrier.
        List<ServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(1L);
        // Create the list of service type that will be show on the page.
        List<ServiceTypeVo> showServiceTypes = new ArrayList<ServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustBaseRates(this.getFranchiseCode(), serviceTypeVo);
            }
        }
        // Set to model.
        List<ServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, ServiceTypeModel.class);
        this.setDhl(serviceTypeModels);
    }

    protected void loadDHLDom() throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        // Get all service type of DHL Domestic carrier.
        List<ServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(15L);
        // Create the list of service type that will be show on the page.
        List<ServiceTypeVo> showServiceTypes = new ArrayList<ServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustBaseRates(this.getFranchiseCode(), serviceTypeVo);
            }
        }
        // Set to model.
        List<ServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, ServiceTypeModel.class);
        this.setDhlDom(serviceTypeModels);
    }

    protected void loadTNTDom() throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        // Get all service type of TNT Domestic carrier.
        List<ServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(3L);
        // Create the list of service type that will be show on the page.
        List<ServiceTypeVo> showServiceTypes = new ArrayList<ServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : serviceTypes) {
                // Remove Sameday Express and all shipment type that is not
                // show.
                if (serviceTypeVo.getShowStatus() && serviceTypeVo.getShipmentTypeId() != 23) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustBaseRates(this.getFranchiseCode(), serviceTypeVo);
            }
        }
        // Set to model.
        List<ServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, ServiceTypeModel.class);
        this.setTntDom(serviceTypeModels);
        // Load orgin.
        CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
        List<CarrierZoneVo> carrierZoneVos = carrierZoneDao.getZoneByCarrier(3L);
        List<CarrierZoneModel> carrierZoneModels = ModelUtils.createListModelFromVo(carrierZoneVos, CarrierZoneModel.class);
        this.setTntDomColumns(carrierZoneModels);
    }

    protected void loadTNTIntl() throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        // Get all service type of TNT International carrier.
        List<ServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(54L);
        // Create the list of service type that will be show on the page.
        List<ServiceTypeVo> showServiceTypes = new ArrayList<ServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustBaseRates(this.getFranchiseCode(), serviceTypeVo);
            }
        }
        // Set to model.
        List<ServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, ServiceTypeModel.class);
        this.setTntIntl(serviceTypeModels);
    }

    protected void loadTollPriority() throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        // Get all service type of Toll Priority carrier.
        List<ServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(52L);
        // Create the list of service type that will be show on the page.
        List<ServiceTypeVo> showServiceTypes = new ArrayList<ServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : serviceTypes) {
                if (!serviceTypeVo.getAllowCarrier()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustBaseRates(this.getFranchiseCode(), serviceTypeVo);
            }
        }
        // Set to model.
        List<ServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, ServiceTypeModel.class);
        this.setTollPriority(serviceTypeModels);
    }

    protected void loadTollIpec() throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        // Get all service type of Toll Ipec carrier.
        List<ServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(59L);
        // Create the list of service type that will be show on the page.
        List<ServiceTypeVo> showServiceTypes = new ArrayList<ServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : serviceTypes) {
                if (!serviceTypeVo.getAllowCarrier()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustBaseRates(this.getFranchiseCode(), serviceTypeVo);
            }
        }
        // Set to model.
        List<ServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, ServiceTypeModel.class);
        this.setTollIpec(serviceTypeModels);
    }

    protected void loadStarTrack() throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        // Get all service type of Star Track carrier.
        List<ServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(72L);
        // Create the list of service type that will be show on the page.
        List<ServiceTypeVo> showServiceTypes = new ArrayList<ServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustBaseRates(this.getFranchiseCode(), serviceTypeVo);
            }
        }
        // Load orgin.
        CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
        List<CarrierZoneVo> carrierZoneVos = carrierZoneDao.getZoneByCarrier(72L);
        List<CarrierZoneModel> carrierZoneModels = ModelUtils.createListModelFromVo(carrierZoneVos, CarrierZoneModel.class);
        this.setStarTrackColumns(carrierZoneModels);
        // Set to model.
        List<ServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, ServiceTypeModel.class);
        this.setStarTrack(serviceTypeModels);
    }

    private void loadUPS() throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        // Get all service type of TNT International carrier.
        List<ServiceTypeVo> serviceTypesFull = dao.getServiceTypeByCarrier(400L);
        List<ServiceTypeVo> serviceTypes = new ArrayList<>();
        for(ServiceTypeVo serviceTypeVo : serviceTypesFull){
            if(serviceTypeVo.getContent()==1)
            {
                serviceTypes.add(serviceTypeVo);
            }
        }
        // Create the list of service type that will be show on the page.
        List<ServiceTypeVo> showServiceTypes = new ArrayList<ServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : serviceTypes) {
                if (serviceTypeVo.getShowStatus()) {
                    showServiceTypes.add(serviceTypeVo);
                }
            }
        }
        // Loop over list of service type.
        if (showServiceTypes.size() > 0) {
            for (ServiceTypeVo serviceTypeVo : showServiceTypes) {
                determineNameAndRateSheet(serviceTypeVo);
                loadZones(serviceTypeVo);
                loadCustBaseRates(this.getFranchiseCode(), serviceTypeVo);
            }
        }
        // Set to model.
        List<ServiceTypeModel> serviceTypeModels = ModelUtils.createListModelFromVo(showServiceTypes, ServiceTypeModel.class);
        this.setUps(serviceTypeModels);
    }

    protected void loadOthers() throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        // Get others customer base rate by customer code.
        List<OtherCustBaseRateVo> baseRateVos = dao.getOtherBaseRateByCustomerCode(this.getFranchiseCode());
        if (baseRateVos != null && baseRateVos.size() > 0) {
            for (OtherCustBaseRateVo otherCustBaseRateVo : baseRateVos) {
                List<RateType> rateTypes = this.prepareRateTypes(0d, otherCustBaseRateVo.getServiceId());
                otherCustBaseRateVo.setRateTypes(rateTypes);
            }
        }
        List<OtherCustBaseRateModel> baseRateModels = ModelUtils.createListModelFromVo(baseRateVos, OtherCustBaseRateModel.class);
        this.setOthers(baseRateModels);
    }

    private void determineNameAndRateSheet(ServiceTypeVo serviceTypeVo) throws Exception {
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

    private void loadZones(ServiceTypeVo serviceTypeVo) throws Exception {
        if (serviceTypeVo.getRateSheetId() != 0) {
            ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
            List<String> zones = dao.getZonesBySheetId(serviceTypeVo.getRateSheetId());
            serviceTypeVo.setZones(zones);
        }
    }

    private void loadCustBaseRates(String customerCode, ServiceTypeVo serviceTypeVo) throws Exception {
        ManageCustBaseRateDao dao = new ManageCustBaseRateDao();
        CustBaseRateVo baseRateFilter = new CustBaseRateVo();
        baseRateFilter.setCustomerCode(customerCode);
        baseRateFilter.setShipmentTypeId(serviceTypeVo.getShipmentTypeId());
        baseRateFilter.setContent(serviceTypeVo.getContent() == -1 ? 0 : serviceTypeVo.getContent());
        baseRateFilter.setBound(serviceTypeVo.getBound());
        List<CustBaseRateVo> baseRateVos = dao.getCustBaseRateByFilter(baseRateFilter);
        if (baseRateVos != null && baseRateVos.size() > 0) {
            for (CustBaseRateVo custBaseRateVo : baseRateVos) {
                if (custBaseRateVo.getZoneCheck()) {
                    // Load customer base rate detail.
                    CustBaseRateDetailByFilter filter = new CustBaseRateDetailByFilter();
                    filter.setCustomerBaseRateId(custBaseRateVo.getCustomerBaseRateId());
                    filter.setZones(serviceTypeVo.getZones());
                    List<CustBaseRateDetailVo> baseRateDetailVos = dao.getCustBaseRateDetailByFilter(filter);
                    custBaseRateVo.setCustBaseRateDetails(baseRateDetailVos);
                    // Add new customer base rate detail with zone has no base
                    // rate detail.
                    for (int i = 0; i < serviceTypeVo.getZones().size(); i++) {
                        boolean isInList = false;
                        for (CustBaseRateDetailVo custBaseRateDetailVo : custBaseRateVo.getCustBaseRateDetails()) {
                            if (serviceTypeVo.getZones().get(i).equals(custBaseRateDetailVo.getZone())) {
                                isInList = true;
                                break;
                            }
                        }
                        if (!isInList) {
                            CustBaseRateDetailVo baseRateDetailVo = new CustBaseRateDetailVo();
                            baseRateDetailVo.setCustomerBaseRateId(custBaseRateVo.getCustomerBaseRateId());
                            baseRateDetailVo.setZone(serviceTypeVo.getZones().get(i));
                            baseRateDetailVo.setRate(0.0);
                            custBaseRateVo.getCustBaseRateDetails().add(i, baseRateDetailVo);
                        }
                    }
                } else {
                    // Create new customer base rate detail.
                    if (serviceTypeVo.getZones() != null && serviceTypeVo.getZones().size() > 0) {
                        List<CustBaseRateDetailVo> baseRateDetailVos = new ArrayList<CustBaseRateDetailVo>();
                        for (String zone : serviceTypeVo.getZones()) {
                            CustBaseRateDetailVo baseRateDetailVo = new CustBaseRateDetailVo();
                            baseRateDetailVo.setCustomerBaseRateId(custBaseRateVo.getCustomerBaseRateId());
                            baseRateDetailVo.setZone(zone);
                            baseRateDetailVo.setRate(custBaseRateVo.getRate());
                            baseRateDetailVos.add(baseRateDetailVo);
                        }
                        custBaseRateVo.setCustBaseRateDetails(baseRateDetailVos);
                    }
                }
                // Load rate types of customer base rate.
                List<RateType> rateTypes = this.prepareRateTypes(custBaseRateVo.getWeight(), serviceTypeVo.getServiceId());
                custBaseRateVo.setRateTypes(rateTypes);
            }
        } else {
            // Create new customer base rate.
            CustBaseRateVo baseRateVo = new CustBaseRateVo();
            baseRateVo.setCustomerCode(customerCode);
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
            List<RateType> rateTypes = this.prepareRateTypes(0d, serviceTypeVo.getServiceId());
            baseRateVo.setRateTypes(rateTypes);
            // Create new customer base rate detail.
            if (serviceTypeVo.getZones() != null && serviceTypeVo.getZones().size() > 0) {
                List<CustBaseRateDetailVo> baseRateDetailVos = new ArrayList<CustBaseRateDetailVo>();
                for (String zone : serviceTypeVo.getZones()) {
                    CustBaseRateDetailVo baseRateDetailVo = new CustBaseRateDetailVo();
                    baseRateDetailVo.setCustomerBaseRateId(baseRateVo.getCustomerBaseRateId());
                    baseRateDetailVo.setZone(zone);
                    baseRateDetailVo.setRate(baseRateVo.getRate());
                    baseRateDetailVos.add(baseRateDetailVo);
                }
                baseRateVo.setCustBaseRateDetails(baseRateDetailVos);
            }
            baseRateVos.add(baseRateVo);
        }
        serviceTypeVo.setCustBaseRates(baseRateVos);
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public FranchiseModel getFranchise() {
        return franchise;
    }

    public void setFranchise(FranchiseModel franchise) {
        this.franchise = franchise;
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

    public SaveCustomerBaseRateModel getSaveFranBaseRate() {
        return saveFranBaseRate;
    }

    public void setSaveFranBaseRate(SaveCustomerBaseRateModel saveFranBaseRate) {
        this.saveFranBaseRate = saveFranBaseRate;
    }

    public List<CarrierZoneModel> getTntDomColumns() {
        return tntDomColumns;
    }

    public void setTntDomColumns(List<CarrierZoneModel> tntDomColumns) {
        this.tntDomColumns = tntDomColumns;
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

    public List<ServiceTypeModel> getUps() {
        return ups;
    }

    public void setUps(List<ServiceTypeModel> ups) {
        this.ups = ups;
    }
}

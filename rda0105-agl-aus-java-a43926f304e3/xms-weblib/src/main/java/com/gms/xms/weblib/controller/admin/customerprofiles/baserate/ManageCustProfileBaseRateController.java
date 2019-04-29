package com.gms.xms.weblib.controller.admin.customerprofiles.baserate;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.CarrierZoneModel;
import com.gms.xms.model.customer.CustomerProfileModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.CarrierZoneDao;
import com.gms.xms.persistence.dao.admin.customer.baserate.ManageCustProfileBaseRateDao;
import com.gms.xms.persistence.service.baserate.BaseRateServiceImp;
import com.gms.xms.persistence.service.baserate.IBaseRateService;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.txndb.model.admin.customerprofile.baserate.CustProfileServiceTypeModel;
import com.gms.xms.txndb.model.admin.customerprofile.baserate.OtherCustProfileBaseRateModel;
import com.gms.xms.txndb.model.admin.customerprofile.baserate.SaveCustomerProfileBaseRateModel;
import com.gms.xms.txndb.vo.CarrierZoneVo;
import com.gms.xms.txndb.vo.RateType;
import com.gms.xms.txndb.vo.admin.customerprofile.baserate.*;
import com.gms.xms.txndb.vo.customer.CustomerProfileVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ManageCustProfileBaseRateController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private String profileId;
    private CustomerProfileModel profile;
    private List<CustProfileServiceTypeModel> dhl;
    private List<CustProfileServiceTypeModel> dhlDom;
    private List<CustProfileServiceTypeModel> tntDom;
    private List<CustProfileServiceTypeModel> tntIntl;
    private List<CustProfileServiceTypeModel> tollPriority;
    private List<CustProfileServiceTypeModel> tollIpec;
    private List<CustProfileServiceTypeModel> starTrack;
    private List<OtherCustProfileBaseRateModel> others;
    private SaveCustomerProfileBaseRateModel saveCustProfileBaseRate;
    private List<CarrierZoneModel> tntDomColumns;
    private List<CarrierZoneModel> starTrackColumns;

    public String show() {
        try {
            // Validate customer code.
            if (StringUtils.isBlank(this.getProfileId())) {
                throw new CustomException("Please choose a customer.");
            }
            // Load info of tabs.
            loadCustomer();
            loadDHL();
            loadDHLDom();
            loadTNTDom();
            loadTNTIntl();
            loadTollPriority();
            loadTollIpec();
            loadStarTrack();
            loadOthers();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doSaveBaseRate() {
        try {
            if (this.getSaveCustProfileBaseRate() == null) {
                throw new CustomException("No customer base rates information for saving.");
            }
            // Convert to vo.
            SaveCustomerProfileBaseRateVo saveCustomerProfileBaseRateVo = ModelUtils.createVoFromModel(this.getSaveCustProfileBaseRate(), SaveCustomerProfileBaseRateVo.class);
            IBaseRateService baseRateService = new BaseRateServiceImp();
            baseRateService.saveCustProfileBaseRate(this.getAddInfoMap(), saveCustomerProfileBaseRateVo);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected List<RateType> prepareRateTypes() throws Exception {
        List<RateType> rateTypes = new ArrayList<RateType>();
        rateTypes.add(new RateType(0, "DHL"));
        rateTypes.add(new RateType(1, "% Markup"));
        rateTypes.add(new RateType(2, "% Margin"));
        rateTypes.add(new RateType(3, "% Topup"));
        return rateTypes;
    }

    protected void loadCustomer() throws Exception {
        ICustomerProfileService customerProfileService = new CustomerProfileServiceImp();
        CustomerProfileVo customerProfileVo = customerProfileService.selectByProfileId(Long.valueOf(this.getProfileId()));
        CustomerProfileModel customerProfileModel = ModelUtils.createModelFromVo(customerProfileVo, CustomerProfileModel.class);
        this.setProfile(customerProfileModel);
    }

    protected void loadDHL() throws Exception {
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
        this.setDhl(serviceTypeModels);
    }

    protected void loadDHLDom() throws Exception {
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
        this.setDhlDom(serviceTypeModels);
    }

    protected void loadTNTDom() throws Exception {
        ManageCustProfileBaseRateDao dao = new ManageCustProfileBaseRateDao();
        // Get all service type of TNT Domestic carrier.
        List<CustProfileServiceTypeVo> serviceTypes = dao.getServiceTypeByCarrier(3L);
        // Create the list of service type that will be show on the page.
        List<CustProfileServiceTypeVo> showServiceTypes = new ArrayList<CustProfileServiceTypeVo>();
        if (serviceTypes != null && serviceTypes.size() > 0) {
            for (CustProfileServiceTypeVo serviceTypeVo : serviceTypes) {
                // Remove Sameday Express and all shipment type that is not
                // show.
                if (serviceTypeVo.getShowStatus() && serviceTypeVo.getShipmentTypeId() != 23) {
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
        this.setTntDom(serviceTypeModels);
        // Load orgin.
        CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
        List<CarrierZoneVo> carrierZoneVos = carrierZoneDao.getZoneByCarrier(3L);
        List<CarrierZoneModel> carrierZoneModels = ModelUtils.createListModelFromVo(carrierZoneVos, CarrierZoneModel.class);
        this.setTntDomColumns(carrierZoneModels);
    }

    protected void loadTNTIntl() throws Exception {
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
        this.setTntIntl(serviceTypeModels);
    }

    protected void loadTollPriority() throws Exception {
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
        this.setTollPriority(serviceTypeModels);
    }

    protected void loadTollIpec() throws Exception {
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
        this.setTollIpec(serviceTypeModels);
    }

    protected void loadStarTrack() throws Exception {
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
        this.setStarTrack(serviceTypeModels);
        // Load orgin.
        CarrierZoneDao carrierZoneDao = new CarrierZoneDao();
        List<CarrierZoneVo> carrierZoneVos = carrierZoneDao.getZoneByCarrier(72L);
        List<CarrierZoneModel> carrierZoneModels = ModelUtils.createListModelFromVo(carrierZoneVos, CarrierZoneModel.class);
        this.setStarTrackColumns(carrierZoneModels);
    }

    protected void loadOthers() throws Exception {
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
        this.setOthers(baseRateModels);
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

    public CustomerProfileModel getProfile() {
        return profile;
    }

    public void setProfile(CustomerProfileModel profile) {
        this.profile = profile;
    }

    public List<CustProfileServiceTypeModel> getDhl() {
        return dhl;
    }

    public void setDhl(List<CustProfileServiceTypeModel> dhl) {
        this.dhl = dhl;
    }

    public List<CustProfileServiceTypeModel> getDhlDom() {
        return dhlDom;
    }

    public void setDhlDom(List<CustProfileServiceTypeModel> dhlDom) {
        this.dhlDom = dhlDom;
    }

    public List<CustProfileServiceTypeModel> getTntDom() {
        return tntDom;
    }

    public void setTntDom(List<CustProfileServiceTypeModel> tntDom) {
        this.tntDom = tntDom;
    }

    public List<CustProfileServiceTypeModel> getTntIntl() {
        return tntIntl;
    }

    public void setTntIntl(List<CustProfileServiceTypeModel> tntIntl) {
        this.tntIntl = tntIntl;
    }

    public List<CustProfileServiceTypeModel> getTollPriority() {
        return tollPriority;
    }

    public void setTollPriority(List<CustProfileServiceTypeModel> tollPriority) {
        this.tollPriority = tollPriority;
    }

    public List<CustProfileServiceTypeModel> getTollIpec() {
        return tollIpec;
    }

    public void setTollIpec(List<CustProfileServiceTypeModel> tollIpec) {
        this.tollIpec = tollIpec;
    }

    public List<OtherCustProfileBaseRateModel> getOthers() {
        return others;
    }

    public void setOthers(List<OtherCustProfileBaseRateModel> others) {
        this.others = others;
    }

    public SaveCustomerProfileBaseRateModel getSaveCustProfileBaseRate() {
        return saveCustProfileBaseRate;
    }

    public void setSaveCustProfileBaseRate(SaveCustomerProfileBaseRateModel saveCustProfileBaseRate) {
        this.saveCustProfileBaseRate = saveCustProfileBaseRate;
    }

    public List<CarrierZoneModel> getTntDomColumns() {
        return tntDomColumns;
    }

    public void setTntDomColumns(List<CarrierZoneModel> tntDomColumns) {
        this.tntDomColumns = tntDomColumns;
    }

    public List<CustProfileServiceTypeModel> getStarTrack() {
        return starTrack;
    }

    public void setStarTrack(List<CustProfileServiceTypeModel> starTrack) {
        this.starTrack = starTrack;
    }

    public List<CarrierZoneModel> getStarTrackColumns() {
        return starTrackColumns;
    }

    public void setStarTrackColumns(List<CarrierZoneModel> starTrackColumns) {
        this.starTrackColumns = starTrackColumns;
    }
}

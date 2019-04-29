package com.gms.xms.weblib.controller.admin.customerprofiles.baserate;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.model.admin.administration.CustomerProfileBaseRateModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import com.gms.xms.weblib.controller.admin.customerprofiles.CustomerProfilesAccountSetupController;

import java.util.HashMap;
import java.util.List;

/**
 * Posted from CustomerProfilesBaseRateOtherController
 * <p>
 * Author TANDT 28-10-2015
 */
public class CustomerProfilesBaseRateOtherController extends CustomerProfilesAccountSetupController {
    private static final long serialVersionUID = -7110570240797000983L;

    private String serviceId;
    private List<CustomerProfileBaseRateModel> baseRateModels;
    private HashMap<String, String> listRateType;

    public String indexBaseRateOther() {
        try {
            prepareListRateType();
            prepareListOtherService();
            return "success";
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void prepareListOtherService() throws Exception {
        ICustomerProfileService service = new CustomerProfileServiceImp();
        CustomerProfileFilter filter = new CustomerProfileFilter();
        filter.setProfileId(Long.parseLong(profileId));
        filter.setListServices(SystemSettingCache.getInstance().getValueByKey("Carrier list for not show in Base Rates (Others) tab"));
        baseRateModels = ModelUtils.createListModelFromVo(service.selectByCarrierAndProfileId(filter), CustomerProfileBaseRateModel.class);
    }

    private void prepareListRateType() throws Exception {
        HashMap<String, String> rateTypes = new HashMap<String, String>();
        rateTypes.put("0", "DHL");
        rateTypes.put("1", "% Markup");
        rateTypes.put("2", "% Margin");
        rateTypes.put("3", "% Topup");
        this.setListRateType(rateTypes);
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<CustomerProfileBaseRateModel> getBaseRateModels() {
        return baseRateModels;
    }

    public HashMap<String, String> getListRateType() {
        return listRateType;
    }

    public void setListRateType(HashMap<String, String> listRateType) {
        this.listRateType = listRateType;
    }

    public void setBaseRateModels(List<CustomerProfileBaseRateModel> baseRateModels) {
        this.baseRateModels = baseRateModels;
    }

}
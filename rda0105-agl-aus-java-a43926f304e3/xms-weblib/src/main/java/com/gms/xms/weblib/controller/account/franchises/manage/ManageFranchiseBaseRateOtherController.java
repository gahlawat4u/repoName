package com.gms.xms.weblib.controller.account.franchises.manage;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.model.admin.administration.CustomerProfileBaseRateModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;

import java.util.HashMap;
import java.util.List;

/**
 * File Name: ManageFranchiseBaseRateOtherController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 24-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseBaseRateOtherController
 */
public class ManageFranchiseBaseRateOtherController extends ManageFranchiseAccountSetupController {

    private static final long serialVersionUID = -6352532946295869931L;
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
        filter.setProfileId(Long.parseLong(franchiseCode));
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
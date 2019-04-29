package com.gms.xms.txndb.model.admin.customerprofile.baserate;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.admin.administration.CustomerProfileBaseRateModel;

import java.util.List;

/**
 * Posted from ManageCustomerBaseRateVo
 * <p>
 * Author DatTV Sep 17, 2015
 */
public class SaveCustomerProfileBaseRateModel extends BaseModel {
    private static final long serialVersionUID = -2492552768269986187L;
    private String profileId;
    private String minimunBaseCharge;
    private List<CustomerProfileBaseRateModel> customerProfileBaseRates;

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(String minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public List<CustomerProfileBaseRateModel> getCustomerProfileBaseRates() {
        return customerProfileBaseRates;
    }

    public void setCustomerProfileBaseRates(List<CustomerProfileBaseRateModel> customerProfileBaseRates) {
        this.customerProfileBaseRates = customerProfileBaseRates;
    }

    @Override
    public String toString() {
        return "SaveCustomerProfileBaseRateModel [profileId=" + profileId + ", minimunBaseCharge=" + minimunBaseCharge + ", customerProfileBaseRates=" + customerProfileBaseRates + "]";
    }
}
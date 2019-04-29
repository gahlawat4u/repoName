package com.gms.xms.txndb.vo.admin.customerprofile.baserate;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.admin.administration.CustomerProfileBaseRateVo;

import java.util.List;

/**
 * Posted from ManageCustomerBaseRateVo
 * <p>
 * Author DatTV Sep 17, 2015
 */
public class SaveCustomerProfileBaseRateVo extends BaseVo {

    private static final long serialVersionUID = -6098577123616203120L;

    private Long profileId;
    private Double minimunBaseCharge;
    private List<CustomerProfileBaseRateVo> customerProfileBaseRates;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Double getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(Double minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public List<CustomerProfileBaseRateVo> getCustomerProfileBaseRates() {
        return customerProfileBaseRates;
    }

    public void setCustomerProfileBaseRates(List<CustomerProfileBaseRateVo> customerProfileBaseRates) {
        this.customerProfileBaseRates = customerProfileBaseRates;
    }
}
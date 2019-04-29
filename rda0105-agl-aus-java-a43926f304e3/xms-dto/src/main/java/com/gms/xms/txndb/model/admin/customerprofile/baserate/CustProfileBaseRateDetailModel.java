package com.gms.xms.txndb.model.admin.customerprofile.baserate;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustProfileBaseRateDetailModel
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class CustProfileBaseRateDetailModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String customerProfileBaseRateId;
    private String zone;
    private String rate;

    public String getCustomerProfileBaseRateId() {
        return customerProfileBaseRateId;
    }

    public void setCustomerProfileBaseRateId(String customerProfileBaseRateId) {
        this.customerProfileBaseRateId = customerProfileBaseRateId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CustProfileBaseRateDetailModel [customerProfileBaseRateId=" + customerProfileBaseRateId + ", zone=" + zone + ", rate=" + rate + "]";
    }
}

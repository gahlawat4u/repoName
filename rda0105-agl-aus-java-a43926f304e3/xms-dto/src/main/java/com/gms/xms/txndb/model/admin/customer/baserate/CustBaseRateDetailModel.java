package com.gms.xms.txndb.model.admin.customer.baserate;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Apr 4, 2016 11:09:58 PM
 * <p>
 * Author dattrinh
 */
public class CustBaseRateDetailModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String customerBaseRateId;
    private String zone;
    private String rate;

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

    public String getCustomerBaseRateId() {
        return customerBaseRateId;
    }

    public void setCustomerBaseRateId(String customerBaseRateId) {
        this.customerBaseRateId = customerBaseRateId;
    }

    @Override
    public String toString() {
        return "CustBaseRateDetailModel [customerBaseRateId=" + customerBaseRateId + ", zone=" + zone + ", rate=" + rate + "]";
    }
}

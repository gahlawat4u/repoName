package com.gms.xms.model;

/**
 * Posted from CustomerBaseRateDetailModel
 * <p>
 * Author DatTV Sep 14, 2015
 */
public class CustomerBaseRateDetailModel extends BaseModel {
    private static final long serialVersionUID = -4092581708314544399L;

    private String customerBaseRateId;

    private String zone;

    private String rate;

    @Override
    public String toString() {
        return "CustomerBaseRateDetailModel [customerBaseRateId=" + customerBaseRateId + ", zone=" + zone + ", rate=" + rate + "]";
    }

    public String getCustomerBaseRateId() {
        return customerBaseRateId;
    }

    public void setCustomerBaseRateId(String customerBaseRateId) {
        this.customerBaseRateId = customerBaseRateId;
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
}
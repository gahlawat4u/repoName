package com.gms.xms.model;

/**
 * Posted from AccessorialDetailModel
 * <p>
 * Author HungNT Date Apr 23, 2015
 */
public class AccessorialDetailModel extends BaseModel {
    private static final long serialVersionUID = -4479559325679984910L;

    private String accessorialId;

    private String defaultCharge;

    private String applyStartDate;

    private String defaultChargeCarrier;

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public String getDefaultCharge() {
        return defaultCharge;
    }

    public void setDefaultCharge(String defaultCharge) {
        this.defaultCharge = defaultCharge;
    }

    public String getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(String applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public String getDefaultChargeCarrier() {
        return defaultChargeCarrier;
    }

    public void setDefaultChargeCarrier(String defaultChargeCarrier) {
        this.defaultChargeCarrier = defaultChargeCarrier;
    }

    @Override
    public String toString() {
        return "AccessorialDetailModel [accessorialId=" + accessorialId + ", defaultCharge=" + defaultCharge + ", applyStartDate=" + applyStartDate + ", defaultChargeCarrier=" + defaultChargeCarrier + "]";
    }
}

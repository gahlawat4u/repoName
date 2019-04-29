package com.gms.xms.model.admin.ratesheets;

import com.gms.xms.model.BaseModel;

import java.util.Map;

/**
 * Posted from Apr 8, 2016 5:29:40 PM
 * <p>
 * Author dattrinh
 */
public class RequestRateSheetModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String type;
    private String code;
    private String serviceId;
    private String rateType;
    private String minimumRate;
    private String baseRate;
    private Map<String, String> zoneRates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public Map<String, String> getZoneRates() {
        return zoneRates;
    }

    public void setZoneRates(Map<String, String> zoneRates) {
        this.zoneRates = zoneRates;
    }

    public String getMinimumRate() {
        return minimumRate;
    }

    public void setMinimumRate(String minimumRate) {
        this.minimumRate = minimumRate;
    }

    public String getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(String baseRate) {
        this.baseRate = baseRate;
    }

    @Override
    public String toString() {
        return "RequestRateSheetModel [type=" + type + ", code=" + code + ", serviceId=" + serviceId + ", rateType=" + rateType + ", minimumRate=" + minimumRate + ", baseRate=" + baseRate + ", zoneRates=" + zoneRates + "]";
    }
}

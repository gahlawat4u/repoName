package com.gms.xms.txndb.vo.admin.ratesheets;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Map;

/**
 * Posted from Apr 8, 2016 5:29:40 PM
 * <p>
 * Author dattrinh
 */
public class RequestRateSheetVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer type;
    private String code;
    private Integer serviceId;
    private Integer rateType;
    private Double minimumRate;
    private Double baseRate;
    private Map<String, Double> zoneRates;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public Double getMinimumRate() {
        return minimumRate;
    }

    public void setMinimumRate(Double minimumRate) {
        this.minimumRate = minimumRate;
    }

    public Double getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(Double baseRate) {
        this.baseRate = baseRate;
    }

    public Map<String, Double> getZoneRates() {
        return zoneRates;
    }

    public void setZoneRates(Map<String, Double> zoneRates) {
        this.zoneRates = zoneRates;
    }

    @Override
    public String toString() {
        return "RequestRateSheetVo [type=" + type + ", code=" + code + ", serviceId=" + serviceId + ", rateType=" + rateType + ", minimumRate=" + minimumRate + ", baseRate=" + baseRate + ", zoneRates=" + zoneRates + "]";
    }
}

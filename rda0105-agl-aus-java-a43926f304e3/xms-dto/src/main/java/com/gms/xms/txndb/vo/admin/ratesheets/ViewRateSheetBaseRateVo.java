package com.gms.xms.txndb.vo.admin.ratesheets;

import java.util.Map;

/**
 * Posted from May 18, 2016 10:07:05 AM
 * <p>
 * Author dattrinh
 */
public class ViewRateSheetBaseRateVo {
    private Double weight;
    private Integer rateType;
    private Double rate;
    private Map<String, Double> zoneRates;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getRateType() {
        return rateType;
    }

    public void setRateType(Integer rateType) {
        this.rateType = rateType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Map<String, Double> getZoneRates() {
        return zoneRates;
    }

    public void setZoneRates(Map<String, Double> zoneRates) {
        this.zoneRates = zoneRates;
    }

    @Override
    public String toString() {
        return "ViewRateSheetBaseRateVo [weight=" + weight + ", rateType=" + rateType + ", rate=" + rate + ", zoneRates=" + zoneRates + "]";
    }
}

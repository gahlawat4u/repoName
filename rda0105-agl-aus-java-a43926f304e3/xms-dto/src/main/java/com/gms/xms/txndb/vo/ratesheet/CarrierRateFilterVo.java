package com.gms.xms.txndb.vo.ratesheet;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CarrierRateFilterVo
 * <p>
 * Author TANDT
 */
public class CarrierRateFilterVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 5195773844841153336L;

    private Long sheetId;
    private Double weight;
    private String zone;
    private String type;

    public Long getSheetId() {
        return sheetId;
    }

    public void setSheetId(Long sheetId) {
        this.sheetId = sheetId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CarrierRateFilterVo [sheetId=" + sheetId + ", weight=" + weight + ", zone=" + zone + ", type=" + type + "]";
    }

}

package com.gms.xms.txndb.vo.admin;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ShipmentDetailVo;

import java.util.List;

public class SaveWebshipHistoryVo extends BaseVo {
    private static final long serialVersionUID = -422042557165485479L;

    private Long shipmentId;
    private String airbillNumber;
    private Double baseCharge;
    private List<ShipmentDetailVo> surcharges;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public Double getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }

    public List<ShipmentDetailVo> getSurcharges() {
        return surcharges;
    }

    public void setSurcharges(List<ShipmentDetailVo> surcharges) {
        this.surcharges = surcharges;
    }

    @Override
    public String toString() {
        return "SaveWebshipHistoryVo [shipmentId=" + shipmentId + ", baseCharge=" + baseCharge + ", surcharges=" + surcharges + "]";
    }
}

package com.gms.xms.txndb.vo.adjustment;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from AdjustmentRequestFilterVo
 * <p>
 * Author DatTV Date May 18, 2015 11:45:15 AM
 */
public class AdjustmentRequestFilterVo extends BaseVo {
    private static final long serialVersionUID = 6858464838141481372L;
    private Long shipmentId;
    private String airbillNumber;
    private Integer adjustType;
    private String adjustmentType;

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public Integer getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(Integer adjustType) {
        this.adjustType = adjustType;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    @Override
    public String toString() {
        return "AdjustmentRequestFilterVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", adjustType=" + adjustType + ", adjustmentType=" + adjustmentType + "]";
    }
}

package com.gms.xms.txndb.vo.adjustmentrequest;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from AdjustmentRequestFilterVo
 * <p>
 * Author DatTV Date May 18, 2015 11:45:15 AM
 */
public class AirbillAdjustmentRequestFilter extends BaseVo {
    private static final long serialVersionUID = -5735158454388016770L;
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

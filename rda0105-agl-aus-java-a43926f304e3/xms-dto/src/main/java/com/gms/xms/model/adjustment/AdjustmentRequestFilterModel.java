package com.gms.xms.model.adjustment;

import com.gms.xms.model.BaseModel;

/**
 * Posted from AdjustmentRequestFilterModel
 * <p>
 * Author DatTV Date May 18, 2015 11:45:10 AM
 */
public class AdjustmentRequestFilterModel extends BaseModel {
    private static final long serialVersionUID = -8718053534296940665L;
    private String shipmentId;
    private String airbillNumber;
    private String adjustType;
    private String adjustmentType;

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getAdjustType() {
        return adjustType;
    }

    public void setAdjustType(String adjustType) {
        this.adjustType = adjustType;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    @Override
    public String toString() {
        return "AdjustmentRequestFilterModel [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", adjustType=" + adjustType + ", adjustmentType=" + adjustmentType + "]";
    }
}
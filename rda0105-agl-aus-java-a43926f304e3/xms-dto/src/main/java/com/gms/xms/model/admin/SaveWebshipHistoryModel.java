package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.webship.ShipmentDetailModel;

import java.util.List;

public class SaveWebshipHistoryModel extends BaseModel {
    private static final long serialVersionUID = -6988521263564145809L;

    private String shipmentId;
    private String airbillNumber;
    private String baseCharge;
    private List<ShipmentDetailModel> surcharges;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(String baseCharge) {
        this.baseCharge = baseCharge;
    }

    public List<ShipmentDetailModel> getSurcharges() {
        return surcharges;
    }

    public void setSurcharges(List<ShipmentDetailModel> surcharges) {
        this.surcharges = surcharges;
    }

    @Override
    public String toString() {
        return "SaveWebshipHistoryModel [shipmentId=" + shipmentId + ", baseCharge=" + baseCharge + ", surcharges=" + surcharges + "]";
    }
}

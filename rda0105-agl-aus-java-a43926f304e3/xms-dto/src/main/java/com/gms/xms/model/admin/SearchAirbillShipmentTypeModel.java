package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;

public class SearchAirbillShipmentTypeModel extends BaseModel {
    private static final long serialVersionUID = 4767235597086123548L;

    private String shipmentTypeKey;
    private String shipmentTypeName;

    public String getShipmentTypeKey() {
        return shipmentTypeKey;
    }

    public void setShipmentTypeKey(String shipmentTypeKey) {
        this.shipmentTypeKey = shipmentTypeKey;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    @Override
    public String toString() {
        return "SearchAirbillShipmentTypeModel [shipmentTypeKey=" + shipmentTypeKey + ", shipmentTypeName=" + shipmentTypeName + "]";
    }
}

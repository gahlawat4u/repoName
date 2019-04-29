package com.gms.xms.model.statistics;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Aug 17, 2016 11:29:18 AM
 * <p>
 * Author dattrinh
 */
public class StatServiceTypeModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String shipmentTypeId;
    private String shipmentTypeName;
    private String serviceType;
    private String serviceTypeName;
    private String shipmentCount;

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getShipmentCount() {
        return shipmentCount;
    }

    public void setShipmentCount(String shipmentCount) {
        this.shipmentCount = shipmentCount;
    }

    @Override
    public String toString() {
        return "StatServiceTypeModel [shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", serviceType=" + serviceType + ", serviceTypeName=" + serviceTypeName + ", shipmentCount=" + shipmentCount + "]";
    }
}

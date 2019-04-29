package com.gms.xms.model;

import com.gms.xms.model.webship.ShipmentModel;

/**
 * Posted from May 26, 2016 12:00:28 PM
 * <p>
 * Author huynd
 */
public class CheckShipmentModel extends ShipmentModel {

    private static final long serialVersionUID = 1L;

    private String shipmentTypeName;

    private String orgShipmentTypeName;

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getOrgShipmentTypeName() {
        return orgShipmentTypeName;
    }

    public void setOrgShipmentTypeName(String orgShipmentTypeName) {
        this.orgShipmentTypeName = orgShipmentTypeName;
    }

    @Override
    public String toString() {
        return "CheckShipmentModel [shipmentTypeName=" + shipmentTypeName + ", orgShipmentTypeName=" + orgShipmentTypeName + "]";
    }

}

package com.gms.xms.model;


/**
 * Posted from ShipmentForTrackingFilterModel
 * <p>
 * Author TanDT Date Apr 15, 2015
 */
public class ShipmentForTrackingFilterModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String shipmentId;
    private String day;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


}
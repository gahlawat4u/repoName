package com.gms.xms.txndb.vo;


/**
 * Posted from ShipmentForTrackingFilter
 * <p>
 * Author TanDT Date Apr 15, 2015
 */
public class ShipmentForTrackingFilter extends BaseVo {
    private static final long serialVersionUID = 1L;
    private long shipmentId;
    private int day;

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}
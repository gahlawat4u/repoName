package com.gms.xms.filter.webship;

/**
 * Created by thinhdd
 * Date 4/7/2017.
 */
public class UpsConnoteFilter {
    private Long shipmentId;
    private String connPrefix;
    private String connEnd;
    private String connStart;

    public UpsConnoteFilter() {
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getConnPrefix() {
        return connPrefix;
    }

    public void setConnPrefix(String connPrefix) {
        this.connPrefix = connPrefix;
    }

    public String getConnEnd() {
        return connEnd;
    }

    public void setConnEnd(String connEnd) {
        this.connEnd = connEnd;
    }

    public String getConnStart() {
        return connStart;
    }

    public void setConnStart(String connStart) {
        this.connStart = connStart;
    }
}

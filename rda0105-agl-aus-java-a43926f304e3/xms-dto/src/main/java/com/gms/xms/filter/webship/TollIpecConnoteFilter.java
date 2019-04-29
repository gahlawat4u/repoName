package com.gms.xms.filter.webship;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from TollIpecConnoteFilter
 * <p>
 * Author @author HungNT Feb 16, 2016
 */
public class TollIpecConnoteFilter extends BaseFilter {
    private Long shipmentId;
    private String connPrefix;
    private String connEnd;
    private String connStart;

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

    @Override
    public String toString() {
        return "TollIpecConnoteFilter [shipmentId=" + shipmentId + ", connPrefix=" + connPrefix + ", connEnd=" + connEnd + ", connStart=" + connStart + "]";
    }
}

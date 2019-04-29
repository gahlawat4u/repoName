package com.gms.xms.filter.webship;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from Sep 23, 2016 6:12:02 PM
 * <p>
 * Author huynd
 */
public class TollManifestFilter extends BaseFilter {

    private Long manifestId;
    private Long shipmentId;

    public Long getManifestId() {
        return manifestId;
    }

    public void setManifestId(Long manifestId) {
        this.manifestId = manifestId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    @Override
    public String toString() {
        return "TollManifestFilter [manifestId=" + manifestId + ", shipmentId=" + shipmentId + "]";
    }
}

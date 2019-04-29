package com.gms.xms.filter.webship;

import java.util.List;

/**
 * Created by thinhdd on 2/8/2017.
 */
public class TntConnoteShipmentInfoFilter {
    private Long shipmentTypeId;
    private Long connoteId;
    private String shipmentIds;

    public TntConnoteShipmentInfoFilter() {
    }

    public TntConnoteShipmentInfoFilter(Long shipmentTypeId, Long connoteId, String shipmentIds) {
        this.shipmentTypeId = shipmentTypeId;
        this.connoteId = connoteId;
        this.shipmentIds = shipmentIds;
    }

    public Long getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Long shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Long getConnoteId() {
        return connoteId;
    }

    public void setConnoteId(Long connoteId) {
        this.connoteId = connoteId;
    }

    public String getShipmentIds() {
        return shipmentIds;
    }

    public void setShipmentIds(String shipmentIds) {
        this.shipmentIds = shipmentIds;
    }
}

package com.gms.xms.workflow.client.integration.request;


/**
 * Posted from ReshipRequest
 * <p>
 * Author TanDT Date Apr 17, 2015
 */
public class ReshipRequest extends BaseRequest {
    private String shipmentId;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }


}

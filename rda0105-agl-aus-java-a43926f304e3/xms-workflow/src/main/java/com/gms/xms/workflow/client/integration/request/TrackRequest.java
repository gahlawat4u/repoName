package com.gms.xms.workflow.client.integration.request;


/**
 * Posted from TrackRequest
 * <p>
 * Author TanDT Date Apr 15, 2015
 */
public class TrackRequest extends BaseRequest {
    private Long shipmentId;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    @Override
    public String toString() {
        return "TrackRequest [shipmentId=" + shipmentId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TrackRequest other = (TrackRequest) obj;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }


}

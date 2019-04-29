package com.gms.delivery.toll.xmlpi.pickup.response;

/**
 * Posted from TollPickupResult
 * <p>
 * Author dattrinh Feb 15, 2016 5:26:43 PM
 */
public class TollPickupResult {
    private TollPickupResponse pickupResponse;
    private XmlEncodeError encodeError;

    public TollPickupResponse getPickupResponse() {
        return pickupResponse;
    }

    public void setPickupResponse(TollPickupResponse pickupResponse) {
        this.pickupResponse = pickupResponse;
    }

    public XmlEncodeError getEncodeError() {
        return encodeError;
    }

    public void setEncodeError(XmlEncodeError encodeError) {
        this.encodeError = encodeError;
    }

    @Override
    public String toString() {
        return "TollPickupResult [pickupResponse=" + pickupResponse + ", encodeError=" + encodeError + "]";
    }
}

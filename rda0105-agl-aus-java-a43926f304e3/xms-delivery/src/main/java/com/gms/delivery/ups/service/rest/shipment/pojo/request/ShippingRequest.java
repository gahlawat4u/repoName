
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingRequest {

    @SerializedName("UPSSecurity")
    @Expose
    private UPSSecurity uPSSecurity;
    @SerializedName("ShipmentRequest")
    @Expose
    private ShipmentRequest shipmentRequest;

    public UPSSecurity getUPSSecurity() {
        return uPSSecurity;
    }

    public void setUPSSecurity(UPSSecurity uPSSecurity) {
        this.uPSSecurity = uPSSecurity;
    }

    public ShipmentRequest getShipmentRequest() {
        return shipmentRequest;
    }

    public void setShipmentRequest(ShipmentRequest shipmentRequest) {
        this.shipmentRequest = shipmentRequest;
    }

}

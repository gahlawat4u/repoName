
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShipmentRequest {

    @SerializedName("Request")
    @Expose
    private Request request;
    @SerializedName("Shipment")
    @Expose
    private Shipment shipment;
    @SerializedName("LabelSpecification")
    @Expose
    private LabelSpecification labelSpecification;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public LabelSpecification getLabelSpecification() {
        return labelSpecification;
    }

    public void setLabelSpecification(LabelSpecification labelSpecification) {
        this.labelSpecification = labelSpecification;
    }

}

package com.gms.delivery.dhl.xmlpi.datatype.shipment.response;

import com.gms.delivery.dhl.xmlpi.datatype.error.response.DhlErrorResponse;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.ShipmentValidateErrorResponse;

public class ShipmentRequestResult {
    private ShipmentResponse shipmentResponse;
    private DhlErrorResponse errorResponse;
    private ShipmentValidateErrorResponse shipmentValidateErrorResponse;

    public ShipmentResponse getShipmentResponse() {
        return shipmentResponse;
    }

    public void setShipmentResponse(ShipmentResponse shipmentResponse) {
        this.shipmentResponse = shipmentResponse;
    }

    public DhlErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(DhlErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ShipmentValidateErrorResponse getShipmentValidateErrorResponse() {
        return shipmentValidateErrorResponse;
    }

    public void setShipmentValidateErrorResponse(ShipmentValidateErrorResponse shipmentValidateErrorResponse) {
        this.shipmentValidateErrorResponse = shipmentValidateErrorResponse;
    }
}

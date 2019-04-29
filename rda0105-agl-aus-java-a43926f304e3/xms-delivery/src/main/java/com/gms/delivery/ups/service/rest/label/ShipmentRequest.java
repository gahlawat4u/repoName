package com.gms.delivery.ups.service.rest.label;

public class ShipmentRequest {
private Request Request;
private Shipment Shipment;

public Request getRequest() {
	return Request;
}
public void setRequest(Request request) {
	Request = request;
}
public Shipment getShipment() {
	return Shipment;
}
public void setShipment(Shipment shipment) {
	Shipment = shipment;
}


}

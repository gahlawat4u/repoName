package com.gms.delivery.ups.service.rest.shipment.upsvoid;

public class VoidShipmentRequest {

	private Request request;
	private String ShipmentIdentificationNumber;
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public String getShipmentIdentificationNumber() {
		return ShipmentIdentificationNumber;
	}
	public void setShipmentIdentificationNumber(String shipmentIdentificationNumber) {
		ShipmentIdentificationNumber = shipmentIdentificationNumber;
	}
	
	
}

package com.gms.delivery.ups.service.rest.shipment.upsvoid;

public class VoidShipmentResponse {
	
	private StatusType statusType;
	private Response response;
	public StatusType getStatusType() {
		return statusType;
	}
	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

}

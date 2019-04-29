package com.gms.delivery.ups.service.rest.tracking.response.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrackResponse {
	
	private Response Response;
	private Shipment Shipment;
	private String Disclaimer;
	
	public Response getResponse() {
		return Response;
	}
	public void setResponse(Response response) {
		Response = response;
	}
	public Shipment getShipment() {
		return Shipment;
	}
	public void setShipment(Shipment shipment) {
		Shipment = shipment;
	}
	public String getDisclaimer() {
		return Disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		Disclaimer = disclaimer;
	}

}

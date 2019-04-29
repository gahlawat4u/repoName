package com.gms.delivery.ups.service.rest.transit.response.pojo;

public class TimeInTransitResponse {
	public Response getResponse() {
		return Response;
	}
	public void setResponse(Response response) {
		Response = response;
	}
	public TransitResponse getTransitResponse() {
		return TransitResponse;
	}
	public void setTransitResponse(TransitResponse transitResponse) {
		TransitResponse = transitResponse;
	}
	
	private Response Response;
	private TransitResponse  TransitResponse;

}

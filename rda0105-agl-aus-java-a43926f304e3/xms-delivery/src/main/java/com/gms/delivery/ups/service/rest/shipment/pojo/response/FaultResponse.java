package com.gms.delivery.ups.service.rest.shipment.pojo.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FaultResponse {
	private Fault fault;

	public Fault getFault() {
		return fault;
	}

	public void setFault(Fault fault) {
		this.fault = fault;
	}

	
	
	
	
}

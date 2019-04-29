package com.gms.delivery.ups.service.rest.transit;

import com.gms.delivery.ups.service.rest.transit.pojo.request.TimeInTransitRequest;

public class TimeInTransitVO {
	

	private Security Security;
	private TimeInTransitRequest TimeInTransitRequest;
	
	
	
	public Security getSecurity() {
		return Security;
	}

	public void setSecurity(Security security) {
		Security = security;
	}

	public TimeInTransitRequest getTimeInTransitRequest() {
		return TimeInTransitRequest;
	}

	public void setTimeInTransitRequest(TimeInTransitRequest timeInTransitRequest) {
		TimeInTransitRequest = timeInTransitRequest;
	}

}

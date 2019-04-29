package com.gms.delivery.ups.service.rest.transit.response.pojo;


public class ServiceSummary {


public String getGuaranteedIndicator() {
	return GuaranteedIndicator;
}
public void setGuaranteedIndicator(String guaranteedIndicator) {
	GuaranteedIndicator = guaranteedIndicator;
}

public Service getService() {
	return Service;
}
public void setService(Service service) {
	Service = service;
}
public EstimatedArrival getEstimatedArrival() {
	return EstimatedArrival;
}
public void setEstimatedArrival(EstimatedArrival estimatedArrival) {
	EstimatedArrival = estimatedArrival;
}


private EstimatedArrival EstimatedArrival;
private String GuaranteedIndicator;
private Service Service;

}

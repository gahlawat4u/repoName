package com.gms.delivery.ups.service.rest.transit.pojo.response;
import java.util.List;

import com.gms.delivery.ups.service.rest.transit.pojo.request.*;

public class TransitResponse {
	

	public ShipFrom getShipFrom() {
		return ShipFrom;
	}
	public void setShipFrom(ShipFrom shipFrom) {
		ShipFrom = shipFrom;
	}
	public ShipTo getShipTo() {
		return ShipTo;
	}
	public void setShipTo(ShipTo shipTo) {
		ShipTo = shipTo;
	}
	public String getPickupDate() {
		return PickupDate;
	}
	public void setPickupDate(String pickupDate) {
		PickupDate = pickupDate;
	}
	public ShipmentWeight getShipmentWeight() {
		return ShipmentWeight;
	}
	public void setShipmentWeight(ShipmentWeight shipmentWeight) {
		ShipmentWeight = shipmentWeight;
	}
	public String getMaximumListSize() {
		return MaximumListSize;
	}
	public void setMaximumListSize(String maximumListSize) {
		MaximumListSize = maximumListSize;
	}
	public List<ServiceSummary> getServiceSummary() {
		return ServiceSummary;
	}
	public void setServiceSummary(List<ServiceSummary> serviceSummary) {
		ServiceSummary = serviceSummary;
	}
	public String getDisclaimer() {
		return Disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		Disclaimer = disclaimer;
	}
	
	private ShipFrom ShipFrom;
	private ShipTo ShipTo;
	private String PickupDate;
	private ShipmentWeight ShipmentWeight;
	private String MaximumListSize;
	private List<ServiceSummary> ServiceSummary;
	private String Disclaimer;

}

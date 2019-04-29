package com.gms.delivery.ups.service.rest.transit.pojo.request;

public class TimeInTransitRequest {
private Request Request;
private ShipFrom  ShipFrom;
private ShipTo ShipTo;
private Pickup Pickup;
private ShipmentWeight ShipmentWeight;
private String MaximumListSize;






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

public Pickup getPickup() {
	return Pickup;
}

public void setPickup(Pickup pickup) {
	Pickup = pickup;
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


public Request getRequest() {
	return Request;
}

public void setRequest(Request request) {
	Request = request;
}
}

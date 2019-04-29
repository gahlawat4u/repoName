package com.gms.delivery.ups.service.rest.label;

public class ShippingRequestVo {
	

public UPSSecurity getUPSSecurity() {
	return UPSSecurity;
}
public void setUPSSecurity(UPSSecurity uPSSecurity) {
	UPSSecurity = uPSSecurity;
}
public LabelSpecification getLabelSpecification() {
	return LabelSpecification;
}
public void setLabelSpecification(LabelSpecification labelSpecification) {
	LabelSpecification = labelSpecification;
}
public ShipmentRequest getShipmentRequest() {
	return ShipmentRequest;
}
public void setShipmentRequest(ShipmentRequest shipmentRequest) {
	ShipmentRequest = shipmentRequest;
}

private UPSSecurity UPSSecurity;
private LabelSpecification LabelSpecification;
private ShipmentRequest ShipmentRequest;

}

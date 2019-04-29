package com.gms.delivery.ups.service.rest.tracking.response.pojo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Shipment {
	
	private InquiryNumber InquiryNumber;
	private String ShipperNumber;
	private List<ShipmentAddress> ShipmentAddress;
	private ShipmentWeight ShipmentWeight;
	private ReferenceNumber ReferenceNumber;
	private Service Service;
	private String PickupDate;
	private DeliveryDetail DeliveryDetail;
	private Package Package;
	
	public InquiryNumber getInquiryNumber() {
		return InquiryNumber;
	}
	public void setInquiryNumber(InquiryNumber inquiryNumber) {
		InquiryNumber = inquiryNumber;
	}
	public String getShipperNumber() {
		return ShipperNumber;
	}
	public void setShipperNumber(String shipperNumber) {
		ShipperNumber = shipperNumber;
	}
	public List<ShipmentAddress> getShipmentAddress() {
		return ShipmentAddress;
	}
	public void setShipmentAddress(List<ShipmentAddress> shipmentAddress) {
		ShipmentAddress = shipmentAddress;
	}
	public ShipmentWeight getShipmentWeight() {
		return ShipmentWeight;
	}
	public void setShipmentWeight(ShipmentWeight shipmentWeight) {
		ShipmentWeight = shipmentWeight;
	}
	public ReferenceNumber getReferenceNumber() {
		return ReferenceNumber;
	}
	public void setReferenceNumber(ReferenceNumber referenceNumber) {
		ReferenceNumber = referenceNumber;
	}
	public Service getService() {
		return Service;
	}
	public void setService(Service service) {
		Service = service;
	}
	public String getPickupDate() {
		return PickupDate;
	}
	public void setPickupDate(String pickupDate) {
		PickupDate = pickupDate;
	}
	public DeliveryDetail getDeliveryDetail() {
		return DeliveryDetail;
	}
	public void setDeliveryDetail(DeliveryDetail deliveryDetail) {
		DeliveryDetail = deliveryDetail;
	}
	public Package getPackage() {
		return Package;
	}
	public void setPackage(Package package1) {
		Package = package1;
	}

}

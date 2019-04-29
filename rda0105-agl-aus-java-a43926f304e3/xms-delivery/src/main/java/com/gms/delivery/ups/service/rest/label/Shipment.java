package com.gms.delivery.ups.service.rest.label;

public class Shipment {

	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Shipper getShipper() {
		return Shipper;
	}
	public void setShipper(Shipper shipper) {
		Shipper = shipper;
	}
	public ShipTo getShipTo() {
		return ShipTo;
	}
	public void setShipTo(ShipTo shipTo) {
		ShipTo = shipTo;
	}
	public ShipFrom getShipFrom() {
		return ShipFrom;
	}
	public void setShipFrom(ShipFrom shipFrom) {
		ShipFrom = shipFrom;
	}
	public PaymentInformation getPaymentInformation() {
		return PaymentInformation;
	}
	public void setPaymentInformation(PaymentInformation paymentInformation) {
		PaymentInformation = paymentInformation;
	}
	public Service getService() {
		return Service;
	}
	public void setService(Service service) {
		Service = service;
	}
	public Package getPackage() {
		return Package;
	}
	public void setPackage(Package package1) {
		Package = package1;
	}
	
	private String Description;
	private Shipper Shipper;
	private ShipTo ShipTo;
	private ShipFrom ShipFrom;
	private PaymentInformation PaymentInformation;
	private Service Service;
	private Package Package;
}

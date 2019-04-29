package com.gms.delivery.ups.service.rest.label;

public class Shipper {
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAttentionName() {
		return AttentionName;
	}
	public void setAttentionName(String attentionName) {
		AttentionName = attentionName;
	}
	public String getTaxIdentificationNumber() {
		return TaxIdentificationNumber;
	}
	public void setTaxIdentificationNumber(String taxIdentificationNumber) {
		TaxIdentificationNumber = taxIdentificationNumber;
	}
	public Phone getPhone() {
		return Phone;
	}
	public void setPhone(Phone phone) {
		Phone = phone;
	}
	public String getShipperNumber() {
		return ShipperNumber;
	}
	public void setShipperNumber(String shipperNumber) {
		ShipperNumber = shipperNumber;
	}
	public String getFaxNumber() {
		return FaxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		FaxNumber = faxNumber;
	}
	public Address getAddress() {
		return Address;
	}
	public void setAddress(Address address) {
		Address = address;
	}
	
	private String Name;
	private String AttentionName;
	private String TaxIdentificationNumber;
	private Phone Phone;
	private String ShipperNumber;
	private String FaxNumber;
	private Address Address;
	
}

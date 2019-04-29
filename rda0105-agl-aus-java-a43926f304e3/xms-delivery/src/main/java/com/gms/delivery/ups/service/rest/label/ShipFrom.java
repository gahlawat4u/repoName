package com.gms.delivery.ups.service.rest.label;

public class ShipFrom {
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
	public String getFaxNumber() {
		return FaxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		FaxNumber = faxNumber;
	}
	public Phone getPhone() {
		return Phone;
	}
	public void setPhone(Phone phone) {
		Phone = phone;
	}
	public Address getAddress() {
		return Address;
	}
	public void setAddress(Address address) {
		Address = address;
	}
	private String Name;
	private String AttentionName;
	private String FaxNumber;
	private Phone Phone;
	private Address Address;
}

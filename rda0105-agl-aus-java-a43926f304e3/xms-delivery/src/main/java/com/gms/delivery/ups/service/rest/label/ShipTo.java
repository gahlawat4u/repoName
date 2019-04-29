package com.gms.delivery.ups.service.rest.label;

public class ShipTo {
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
	private Phone Phone;
	private Address Address;
}
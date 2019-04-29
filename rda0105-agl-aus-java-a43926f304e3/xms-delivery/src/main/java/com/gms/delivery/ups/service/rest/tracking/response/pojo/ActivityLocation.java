package com.gms.delivery.ups.service.rest.tracking.response.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ActivityLocation {
	
	private Address Address;
	private String Code;
	private String Description;
	private String SignedForByName;
	
	public Address getAddress() {
		return Address;
	}
	public void setAddress(Address address) {
		Address = address;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getSignedForByName() {
		return SignedForByName;
	}
	public void setSignedForByName(String signedForByName) {
		SignedForByName = signedForByName;
	}

}

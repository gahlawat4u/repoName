package com.gms.delivery.ups.service.rest.shipment.upsvoid;

public class AccessRequest {

	private String AccessLicenseNumber;
	private String UserId;
	private String Password;
	public String getAccessLicenseNumber() {
		return AccessLicenseNumber;
	}
	public void setAccessLicenseNumber(String accessLicenseNumber) {
		AccessLicenseNumber = accessLicenseNumber;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	
	
}

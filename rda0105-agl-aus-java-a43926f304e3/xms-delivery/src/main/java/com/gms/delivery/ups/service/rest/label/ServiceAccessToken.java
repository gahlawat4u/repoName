package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceAccessToken {
	
	@SerializedName("AccessLicenseNumber")
	@Expose
	private String AccessLicenseNumber;

	public String getAccessLicenseNumber() {
		return AccessLicenseNumber;
	}

	public void setAccessLicenseNumber(String accessLicenseNumber) {
		AccessLicenseNumber = accessLicenseNumber;
	}

}

package com.gms.delivery.ups.service.rest.tracking;

public class UPSTrackingRequest {
	private String AWBNumber;
	private String authenticationKey;
	private String javaCode;

	public String getAWBNumber() {
		return AWBNumber;
	}

	public void setAWBNumber(String AWBNumber) {
		this.AWBNumber = AWBNumber;
	}

	public String getAuthenticationKey() {
		return authenticationKey;
	}

	public void setAuthenticationKey(String authenticationKey) {
		this.authenticationKey = authenticationKey;
	}

	public String getJavaCode() {
		return javaCode;
	}

	public void setJavaCode(String javaCode) {
		this.javaCode = javaCode;
	}
	
	

}

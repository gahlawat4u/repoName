package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReferenceValues {
	
	@SerializedName("ReferenceNumber")
    @Expose
	private ReferenceNumber referenceNumber ;
	
	@SerializedName("ShipperNumber")
    @Expose
	private String ShipperNumber;
	
	
	
	public ReferenceNumber getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(ReferenceNumber referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	public String getShipperNumber() {
		return ShipperNumber;
	}
	public void setShipperNumber(String shipperNumber) {
		ShipperNumber = shipperNumber;
	}
	
	
	
}

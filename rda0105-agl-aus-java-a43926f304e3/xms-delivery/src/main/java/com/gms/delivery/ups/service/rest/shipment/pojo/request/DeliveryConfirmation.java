package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryConfirmation {

	@SerializedName("DCISType")
    @Expose
    private String dCISType;

	public String getdCISType() {
		return dCISType;
	}

	public void setdCISType(String dCISType) {
		this.dCISType = dCISType;
	}
	
	
}

package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageServiceOptions {

	
	
	
	@SerializedName("InsuredValue")
    @Expose
    private InsuredValue insuredValue;

	@SerializedName("DeclaredValue")
    @Expose
    private DeclaredValue declaredValue;

	
    
	public InsuredValue getInsuredValue() {
		return insuredValue;
	}

	public void setInsuredValue(InsuredValue insuredValue) {
		this.insuredValue = insuredValue;
	}

	public DeclaredValue getDeclaredValue() {
		return declaredValue;
	}

	public void setDeclaredValue(DeclaredValue declaredValue) {
		this.declaredValue = declaredValue;
	}
	
	
}

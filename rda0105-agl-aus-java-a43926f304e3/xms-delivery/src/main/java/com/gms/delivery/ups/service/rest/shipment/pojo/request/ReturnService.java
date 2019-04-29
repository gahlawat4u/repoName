package com.gms.delivery.ups.service.rest.shipment.pojo.request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReturnService {
	
    @SerializedName("Code")
    @Expose
    private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    

}

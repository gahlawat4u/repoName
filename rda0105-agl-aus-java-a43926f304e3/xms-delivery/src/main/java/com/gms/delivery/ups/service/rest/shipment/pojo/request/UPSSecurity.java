
package com.gms.delivery.ups.service.rest.shipment.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UPSSecurity {

    @SerializedName("ServiceAccessToken")
    @Expose
    private ServiceAccessToken serviceAccessToken;
    @SerializedName("UsernameToken")
    @Expose
    private UsernameToken usernameToken;
    @SerializedName("PickupDate")
    @Expose
    private String pickupDate;

    
    public ServiceAccessToken getServiceAccessToken() {
        return serviceAccessToken;
    }

    public void setServiceAccessToken(ServiceAccessToken serviceAccessToken) {
        this.serviceAccessToken = serviceAccessToken;
    }

    public UsernameToken getUsernameToken() {
        return usernameToken;
    }

    public void setUsernameToken(UsernameToken usernameToken) {
        this.usernameToken = usernameToken;
    }

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

}

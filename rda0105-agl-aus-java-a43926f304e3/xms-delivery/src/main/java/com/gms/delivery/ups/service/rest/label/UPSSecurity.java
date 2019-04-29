package com.gms.delivery.ups.service.rest.label;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UPSSecurity {
	
	
	@SerializedName("ServiceAccessToken")
	@Expose	
private ServiceAccessToken ServiceAccessToken;
	
	@SerializedName("UsernameToken")
	@Expose
private UsernameToken UsernameToken;


public UsernameToken getUsernameToken() {
	return UsernameToken;
}

public void setUsernameToken(UsernameToken usernameToken) {
	UsernameToken = usernameToken;
}


public ServiceAccessToken getServiceAccessToken() {
	return ServiceAccessToken;
}

public void setServiceAccessToken(ServiceAccessToken serviceAccessToken) {
	ServiceAccessToken = serviceAccessToken;
}
}

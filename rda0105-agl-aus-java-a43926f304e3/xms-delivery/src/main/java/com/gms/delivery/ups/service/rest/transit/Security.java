package com.gms.delivery.ups.service.rest.transit;

public class Security {
private UsernameToken UsernameToken;
private UPSServiceAccessToken UPSServiceAccessToken;

public UPSServiceAccessToken getUPSServiceAccessToken() {
	return UPSServiceAccessToken;
}

public void setUPSServiceAccessToken(UPSServiceAccessToken uPSServiceAccessToken) {
	UPSServiceAccessToken = uPSServiceAccessToken;
}

public UsernameToken getUsernameToken() {
	return UsernameToken;
}

public void setUsernameToken(UsernameToken usernameToken) {
	UsernameToken = usernameToken;
}
}

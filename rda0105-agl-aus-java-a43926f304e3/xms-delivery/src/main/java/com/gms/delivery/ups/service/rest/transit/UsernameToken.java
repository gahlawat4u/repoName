package com.gms.delivery.ups.service.rest.transit;

public class UsernameToken {
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	private String Username;
	private String Password;
}

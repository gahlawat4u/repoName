package com.gms.delivery.ups.service.rest.label;

public class Phone {

	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getExtension() {
		return Extension;
	}
	public void setExtension(String extension) {
		Extension = extension;
	}
	private String Number;
	private String Extension;
}

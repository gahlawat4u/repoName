package com.gms.delivery.ups.service.rest.label;

public class Service {
/*private String Description;
private Packaging Packaging;
private Dimensions Dimensions;
private PackageWeight PackageWeight;*/
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	private String Code;
	private String Description;
}

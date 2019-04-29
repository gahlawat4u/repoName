package com.gms.delivery.ups.service.rest.label;

public class Dimensions {
	private UnitOfMeasurement UnitOfMeasurement;
	public UnitOfMeasurement getUnitOfMeasurement() {
		return UnitOfMeasurement;
	}
	public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
		UnitOfMeasurement = unitOfMeasurement;
	}
	public String getLength() {
		return Length;
	}
	public void setLength(String length) {
		Length = length;
	}
	public String getWidth() {
		return Width;
	}
	public void setWidth(String width) {
		Width = width;
	}
	public String getHeight() {
		return Height;
	}
	public void setHeight(String height) {
		Height = height;
	}
	private String Length;
	private String Width;
	private String Height;
}

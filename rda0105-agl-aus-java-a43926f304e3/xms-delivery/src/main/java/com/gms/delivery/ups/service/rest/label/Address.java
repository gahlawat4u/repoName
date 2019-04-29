package com.gms.delivery.ups.service.rest.label;

public class Address {
	public String getAddressLine() {
		return AddressLine;
	}
	public void setAddressLine(String addressLine) {
		AddressLine = addressLine;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getStateProvinceCode() {
		return StateProvinceCode;
	}
	public void setStateProvinceCode(String stateProvinceCode) {
		StateProvinceCode = stateProvinceCode;
	}
	public String getPostalCode() {
		return PostalCode;
	}
	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	private String AddressLine;
	private String City;
	private String StateProvinceCode;
	private String PostalCode;
	private String CountryCode;
}

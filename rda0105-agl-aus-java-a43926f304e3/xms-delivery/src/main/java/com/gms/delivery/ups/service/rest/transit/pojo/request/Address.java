package com.gms.delivery.ups.service.rest.transit.pojo.request;

public class Address {
	public String getStateProvinceCode() {
		return StateProvinceCode;
	}
	public void setStateProvinceCode(String stateProvinceCode) {
		StateProvinceCode = stateProvinceCode;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public String getPostalCode() {
		return PostalCode;
	}
	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	
	private String StateProvinceCode;
	private String CountryCode;
	private String PostalCode;
	private String City;
	private String Country;
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}

}

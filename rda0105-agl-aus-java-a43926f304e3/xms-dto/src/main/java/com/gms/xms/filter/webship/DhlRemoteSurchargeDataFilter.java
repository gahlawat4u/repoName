package com.gms.xms.filter.webship;

import com.gms.xms.filter.BaseFilter;

public class DhlRemoteSurchargeDataFilter extends BaseFilter {
    private String checkType;
    private String countryCode;
    private String cityName;
    private String postalCode;
    private String postalCodeCheck;

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCodeCheck() {
        return postalCodeCheck;
    }

    public void setPostalCodeCheck(String postalCodeCheck) {
        this.postalCodeCheck = postalCodeCheck;
    }
}

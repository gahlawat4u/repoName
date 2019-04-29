package com.gms.xms.model.webship.ship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from SearchPostalCodeModel
 * <p>
 * Author DatTV Aug 25, 2015
 */
public class SearchPostalCodeModel extends BaseModel {

    private static final long serialVersionUID = 2574080787232552448L;

    private String cityName;
    private String postalCode;
    private String stateCode;

    @Override
    public String toString() {
        return "SearchPostalCodeModel [cityName=" + cityName + ", postalCode=" + postalCode + ", stateCode=" + stateCode + "]";
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

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}

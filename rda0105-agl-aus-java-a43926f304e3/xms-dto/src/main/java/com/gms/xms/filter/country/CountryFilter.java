package com.gms.xms.filter.country;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from Aug 19, 2016 4:28:47 PM
 * <p>
 * Author huynd
 */
public class CountryFilter extends BaseFilter {

    private String cityName;
    private String stationCode;
    private Long countryId;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "CountryFilter [cityName=" + cityName + ", stationCode=" + stationCode + ", countryId=" + countryId + "]";
    }

}

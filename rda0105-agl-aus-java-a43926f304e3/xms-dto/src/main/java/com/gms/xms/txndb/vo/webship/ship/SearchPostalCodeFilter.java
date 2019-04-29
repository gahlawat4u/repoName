package com.gms.xms.txndb.vo.webship.ship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from SearchPostalCodeFilter
 * <p>
 * Author DatTV Aug 25, 2015
 */
public class SearchPostalCodeFilter extends BaseVo {

    private static final long serialVersionUID = 5593159767543255893L;

    private String cityName;
    private String postalCode;
    private Long countryId;

    @Override
    public String toString() {
        return "SearchPostalCodeFilter [cityName=" + cityName + ", postalCode=" + postalCode + ", countryId=" + countryId + "]";
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

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}

package com.gms.xms.txndb.vo.webship.ship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from SearchPostalCodeVo
 * <p>
 * Author DatTV Aug 25, 2015
 */
public class SearchPostalCodeVo extends BaseVo {

    private static final long serialVersionUID = -2259404370523892367L;

    private String cityName;
    private String postalCode;
    private String stateCode;

    @Override
    public String toString() {
        return "SearchPostalCodeVo [cityName=" + cityName + ", postalCode=" + postalCode + ", stateCode=" + stateCode + "]";
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

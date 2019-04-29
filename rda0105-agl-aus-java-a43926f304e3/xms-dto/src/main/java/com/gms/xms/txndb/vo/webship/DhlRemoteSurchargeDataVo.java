package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

public class DhlRemoteSurchargeDataVo extends BaseVo {
    private static final long serialVersionUID = -6058262210084335448L;

    private Integer id;
    private String countryCode;
    private String stateName;
    private String cityName;
    private String fromPostal;
    private String toPostal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getFromPostal() {
        return fromPostal;
    }

    public void setFromPostal(String fromPostal) {
        this.fromPostal = fromPostal;
    }

    public String getToPostal() {
        return toPostal;
    }

    public void setToPostal(String toPostal) {
        this.toPostal = toPostal;
    }

    @Override
    public String toString() {
        return "DhlRemoteSurchargeDataVo [id=" + id + ", countryCode=" + countryCode + ", stateName=" + stateName + ", cityName=" + cityName + ", fromPostal=" + fromPostal + ", toPostal=" + toPostal + "]";
    }
}
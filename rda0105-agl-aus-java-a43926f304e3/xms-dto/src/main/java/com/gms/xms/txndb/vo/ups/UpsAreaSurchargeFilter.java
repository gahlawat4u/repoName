package com.gms.xms.txndb.vo.ups;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Created by thinhdd
 * Date 3/29/2017.
 */
public class UpsAreaSurchargeFilter extends BaseVo {
    private String city;
    private String postalCode;
    private Long countryId;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

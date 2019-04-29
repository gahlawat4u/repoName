package com.gms.xms.txndb.vo.ups;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Created by thinhdd
 * Date 5/22/2017.
 */
public class UpsAreaSurchargeVo extends BaseVo {
    private String originSurcharge;
    private String destinationSurcharge;
    private String countryName;
    private String countryCode;

    public String getOriginSurcharge() {
        return originSurcharge;
    }

    public void setOriginSurcharge(String originSurcharge) {
        this.originSurcharge = originSurcharge;
    }

    public String getDestinationSurcharge() {
        return destinationSurcharge;
    }

    public void setDestinationSurcharge(String destinationSurcharge) {
        this.destinationSurcharge = destinationSurcharge;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}

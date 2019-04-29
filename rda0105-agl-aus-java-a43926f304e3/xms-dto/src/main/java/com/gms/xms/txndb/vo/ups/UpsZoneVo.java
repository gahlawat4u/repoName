package com.gms.xms.txndb.vo.ups;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Created by thinhdd
 * Date 3/29/2017.
 */
public class UpsZoneVo extends BaseVo {

    private static final long serialVersionUID = 8917969322805370267L;

    private String countryName;
    private String zone;

    public UpsZoneVo() {
    }


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}

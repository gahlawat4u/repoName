package com.gms.xms.txndb.vo.startrack;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Jun 13, 2016 3:04:26 PM
 * <p>
 * Author huynd
 */
public class StartrackZoneVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long startrackZoneId;
    private String postCode;
    private String cityName;
    private String directZone;
    private String ofwdZone;
    private String originLocation;

    public Long getStartrackZoneId() {
        return startrackZoneId;
    }

    public void setStartrackZoneId(Long startrackZoneId) {
        this.startrackZoneId = startrackZoneId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDirectZone() {
        return directZone;
    }

    public void setDirectZone(String directZone) {
        this.directZone = directZone;
    }

    public String getOfwdZone() {
        return ofwdZone;
    }

    public void setOfwdZone(String ofwdZone) {
        this.ofwdZone = ofwdZone;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }

    @Override
    public String toString() {
        return "StartrackZoneVo [startrackZoneId=" + startrackZoneId + ", postCode=" + postCode + ", cityName=" + cityName + ", directZone=" + directZone + ", ofwdZone=" + ofwdZone + ", originLocation=" + originLocation + "]";
    }

}

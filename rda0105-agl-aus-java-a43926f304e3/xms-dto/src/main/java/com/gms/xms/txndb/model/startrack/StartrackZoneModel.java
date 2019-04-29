package com.gms.xms.txndb.model.startrack;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Jun 13, 2016 3:04:26 PM
 * <p>
 * Author huynd
 */
public class StartrackZoneModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String startrackZoneId;
    private String postCode;
    private String cityName;
    private String directZone;
    private String ofwdZone;
    private String originLocation;

    public String getStartrackZoneId() {
        return startrackZoneId;
    }

    public void setStartrackZoneId(String startrackZoneId) {
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
        return "StartrackZoneModel [startrackZoneId=" + startrackZoneId + ", postCode=" + postCode + ", cityName=" + cityName + ", directZone=" + directZone + ", ofwdZone=" + ofwdZone + ", originLocation=" + originLocation + "]";
    }

}

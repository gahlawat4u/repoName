package com.gms.xms.model;

/**
 * Posted from Sep 1, 2016 3:59:12 PM
 * <p>
 * Author dattrinh
 */
public class MultiZoneModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String zoneId;
    private String originName;
    private String originCode;
    private String destinationName;
    private String destinationCode;
    private String zone;

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "MultiZoneModel [zoneId=" + zoneId + ", originName=" + originName + ", originCode=" + originCode + ", destinationName=" + destinationName + ", destinationCode=" + destinationCode + ", zone=" + zone + "]";
    }
}
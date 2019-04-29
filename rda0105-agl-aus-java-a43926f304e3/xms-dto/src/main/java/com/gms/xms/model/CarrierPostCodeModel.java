package com.gms.xms.model;

/**
 * Posted from Sep 1, 2016 2:41:02 PM
 * <p>
 * Author dattrinh
 */
public class CarrierPostCodeModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String zoneCode;
    private String postCode;
    private String carrier;

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "CarrierPostCodeModel [zoneCode=" + zoneCode + ", postCode=" + postCode + ", carrier=" + carrier + "]";
    }
}
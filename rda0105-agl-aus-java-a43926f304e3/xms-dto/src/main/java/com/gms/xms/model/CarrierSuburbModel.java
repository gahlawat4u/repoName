package com.gms.xms.model;

/**
 * Posted from Sep 1, 2016 3:10:02 PM
 * <p>
 * Author dattrinh
 */
public class CarrierSuburbModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String suburbName;
    private String stateCode;
    private String postCode;
    private String carrier;
    private String primaryPort;
    private String secondaryPort;
    private String countryFlag;

    public String getSuburbName() {
        return suburbName;
    }

    public void setSuburbName(String suburbName) {
        this.suburbName = suburbName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
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

    public String getPrimaryPort() {
        return primaryPort;
    }

    public void setPrimaryPort(String primaryPort) {
        this.primaryPort = primaryPort;
    }

    public String getSecondaryPort() {
        return secondaryPort;
    }

    public void setSecondaryPort(String secondaryPort) {
        this.secondaryPort = secondaryPort;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    @Override
    public String toString() {
        return "CarrierSuburbModel [suburbName=" + suburbName + ", stateCode=" + stateCode + ", postCode=" + postCode + ", carrier=" + carrier + ", primaryPort=" + primaryPort + ", secondaryPort=" + secondaryPort + ", countryFlag=" + countryFlag + "]";
    }
}
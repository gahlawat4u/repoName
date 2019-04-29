package com.gms.xms.common.enums;

public enum TntDomServiceType {
    ROAD_EXPRESS("Road Express"),
    OVERNIGHT_EXPRESS("Overnight Express"),
    SAMEDAY_EXPRESS("Sameday Express"),
    TECHNOLOGY_EXPRESS("Technology Express"),
    EXPRESS_9("9:00 Express"),
    EXPRESS_10("10:00 Express"),
    EXPRESS_12("12:00 Express");

    /**
     * Created by thinhdd
     * Date 3/17/2017.
     */
    private final String serviceType;

    TntDomServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceType() {
        return serviceType;
    }
}

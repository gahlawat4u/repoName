package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Sep 5, 2016 9:47:51 AM
 * <p>
 * Author dattrinh
 */
public class TntInternationalAuZoneModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String tntIntAuCountryId;
    private String originalFileCountryName;
    private String countryName;
    private String expressOutboundZone;
    private String economyExpressOutboundZone;
    private String expressInboundZone;
    private String economyExpressInboundZone;

    public String getTntIntAuCountryId() {
        return tntIntAuCountryId;
    }

    public void setTntIntAuCountryId(String tntIntAuCountryId) {
        this.tntIntAuCountryId = tntIntAuCountryId;
    }

    public String getOriginalFileCountryName() {
        return originalFileCountryName;
    }

    public void setOriginalFileCountryName(String originalFileCountryName) {
        this.originalFileCountryName = originalFileCountryName;
    }

    public String getExpressOutboundZone() {
        return expressOutboundZone;
    }

    public void setExpressOutboundZone(String expressOutboundZone) {
        this.expressOutboundZone = expressOutboundZone;
    }

    public String getEconomyExpressOutboundZone() {
        return economyExpressOutboundZone;
    }

    public void setEconomyExpressOutboundZone(String economyExpressOutboundZone) {
        this.economyExpressOutboundZone = economyExpressOutboundZone;
    }

    public String getExpressInboundZone() {
        return expressInboundZone;
    }

    public void setExpressInboundZone(String expressInboundZone) {
        this.expressInboundZone = expressInboundZone;
    }

    public String getEconomyExpressInboundZone() {
        return economyExpressInboundZone;
    }

    public void setEconomyExpressInboundZone(String economyExpressInboundZone) {
        this.economyExpressInboundZone = economyExpressInboundZone;
    }

    @Override
    public String toString() {
        return "TntInternationalAuZoneModel [tntIntAuCountryId=" + tntIntAuCountryId + ", originalFileCountryName=" + originalFileCountryName + ", countryName=" + countryName + ", expressOutboundZone=" + expressOutboundZone + ", economyExpressOutboundZone=" + economyExpressOutboundZone + ", expressInboundZone=" + expressInboundZone + ", economyExpressInboundZone=" + economyExpressInboundZone + "]";
    }
}
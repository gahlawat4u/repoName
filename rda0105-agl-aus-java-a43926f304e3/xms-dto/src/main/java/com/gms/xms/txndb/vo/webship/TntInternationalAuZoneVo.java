package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from TntInternationalAuZoneVo
 * <p>
 * Author HungNT Date Jul 24, 2015
 */
public class TntInternationalAuZoneVo extends BaseVo {
    private static final long serialVersionUID = 820141205633959857L;

    private Long tntIntAuCountryId;

    private String originalFileCountryName;

    private String countryName;

    private String expressOutboundZone;

    private String economyExpressOutboundZone;

    private String expressInboundZone;

    private String economyExpressInboundZone;

    public Long getTntIntAuCountryId() {
        return tntIntAuCountryId;
    }

    public void setTntIntAuCountryId(Long tntIntAuCountryId) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((economyExpressInboundZone == null) ? 0 : economyExpressInboundZone.hashCode());
        result = prime * result + ((economyExpressOutboundZone == null) ? 0 : economyExpressOutboundZone.hashCode());
        result = prime * result + ((expressInboundZone == null) ? 0 : expressInboundZone.hashCode());
        result = prime * result + ((expressOutboundZone == null) ? 0 : expressOutboundZone.hashCode());
        result = prime * result + ((originalFileCountryName == null) ? 0 : originalFileCountryName.hashCode());
        result = prime * result + ((tntIntAuCountryId == null) ? 0 : tntIntAuCountryId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TntInternationalAuZoneVo other = (TntInternationalAuZoneVo) obj;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        if (economyExpressInboundZone == null) {
            if (other.economyExpressInboundZone != null)
                return false;
        } else if (!economyExpressInboundZone.equals(other.economyExpressInboundZone))
            return false;
        if (economyExpressOutboundZone == null) {
            if (other.economyExpressOutboundZone != null)
                return false;
        } else if (!economyExpressOutboundZone.equals(other.economyExpressOutboundZone))
            return false;
        if (expressInboundZone == null) {
            if (other.expressInboundZone != null)
                return false;
        } else if (!expressInboundZone.equals(other.expressInboundZone))
            return false;
        if (expressOutboundZone == null) {
            if (other.expressOutboundZone != null)
                return false;
        } else if (!expressOutboundZone.equals(other.expressOutboundZone))
            return false;
        if (originalFileCountryName == null) {
            if (other.originalFileCountryName != null)
                return false;
        } else if (!originalFileCountryName.equals(other.originalFileCountryName))
            return false;
        if (tntIntAuCountryId == null) {
            if (other.tntIntAuCountryId != null)
                return false;
        } else if (!tntIntAuCountryId.equals(other.tntIntAuCountryId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TntInternationalAuZoneVo [tntIntAuCountryId=" + tntIntAuCountryId + ", originalFileCountryName=" + originalFileCountryName + ", countryName=" + countryName + ", expressOutboundZone=" + expressOutboundZone + ", economyExpressOutboundZone=" + economyExpressOutboundZone + ", expressInboundZone=" + expressInboundZone + ", economyExpressInboundZone=" + economyExpressInboundZone + "]";
    }
}
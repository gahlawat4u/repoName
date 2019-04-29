package com.gms.xms.txndb.vo.dhl;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from DhlZoneVo
 * <p>
 * Author HungNT Date May 18, 2015
 */
public class DhlZoneVo extends BaseVo {
    private static final long serialVersionUID = -2510569766519504499L;

    private String dhlCountryName;
    private String stateCode;
    private String dhlApZone;

    public String getDhlCountryName() {
        return dhlCountryName;
    }

    public void setDhlCountryName(String dhlCountryName) {
        this.dhlCountryName = dhlCountryName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDhlApZone() {
        return dhlApZone;
    }

    public void setDhlApZone(String dhlApZone) {
        this.dhlApZone = dhlApZone;
    }

    @Override
    public String toString() {
        return "DhlZoneVo [dhlCountryName=" + dhlCountryName + ", stateCode=" + stateCode + ", dhlApZone=" + dhlApZone + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dhlApZone == null) ? 0 : dhlApZone.hashCode());
        result = prime * result + ((dhlCountryName == null) ? 0 : dhlCountryName.hashCode());
        result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
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
        DhlZoneVo other = (DhlZoneVo) obj;
        if (dhlApZone == null) {
            if (other.dhlApZone != null)
                return false;
        } else if (!dhlApZone.equals(other.dhlApZone))
            return false;
        if (dhlCountryName == null) {
            if (other.dhlCountryName != null)
                return false;
        } else if (!dhlCountryName.equals(other.dhlCountryName))
            return false;
        if (stateCode == null) {
            if (other.stateCode != null)
                return false;
        } else if (!stateCode.equals(other.stateCode))
            return false;
        return true;
    }
}

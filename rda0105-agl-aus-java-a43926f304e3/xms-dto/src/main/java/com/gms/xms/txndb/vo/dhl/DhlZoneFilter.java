package com.gms.xms.txndb.vo.dhl;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from DhlZoneFilter
 * <p>
 * Author HungNT Date May 18, 2015
 */
public class DhlZoneFilter extends BaseVo {
    private static final long serialVersionUID = 1164557114707378501L;

    private String stateCode;
    private String countryId;
    private String cityName;
    private String postalCode;
    private String defaultCountryId;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDefaultCountryId() {
        return defaultCountryId;
    }

    public void setDefaultCountryId(String defaultCountryId) {
        this.defaultCountryId = defaultCountryId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
        result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
        result = prime * result + ((defaultCountryId == null) ? 0 : defaultCountryId.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
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
        DhlZoneFilter other = (DhlZoneFilter) obj;
        if (cityName == null) {
            if (other.cityName != null)
                return false;
        } else if (!cityName.equals(other.cityName))
            return false;
        if (countryId == null) {
            if (other.countryId != null)
                return false;
        } else if (!countryId.equals(other.countryId))
            return false;
        if (defaultCountryId == null) {
            if (other.defaultCountryId != null)
                return false;
        } else if (!defaultCountryId.equals(other.defaultCountryId))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (stateCode == null) {
            if (other.stateCode != null)
                return false;
        } else if (!stateCode.equals(other.stateCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DhlZoneFilter [stateCode=" + stateCode + ", countryId=" + countryId + ", cityName=" + cityName + ", postalCode=" + postalCode + ", defaultCountryId=" + defaultCountryId + "]";
    }
}

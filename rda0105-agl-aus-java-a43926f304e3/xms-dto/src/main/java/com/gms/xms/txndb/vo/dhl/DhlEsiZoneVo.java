package com.gms.xms.txndb.vo.dhl;

import com.gms.xms.txndb.vo.BaseVo;

public class DhlEsiZoneVo extends BaseVo {
    private static final long serialVersionUID = -4773705158741524074L;

    private Integer id;

    private String countryName;

    private String countryCode;

    private String cityName;

    private String fromPostcode;

    private String toPostcode;

    private String stationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getFromPostcode() {
        return fromPostcode;
    }

    public void setFromPostcode(String fromPostcode) {
        this.fromPostcode = fromPostcode == null ? null : fromPostcode.trim();
    }

    public String getToPostcode() {
        return toPostcode;
    }

    public void setToPostcode(String toPostcode) {
        this.toPostcode = toPostcode == null ? null : toPostcode.trim();
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }

    @Override
    public String toString() {
        return "DhlEsiZoneVo [id=" + id + ", countryName=" + countryName + ", countryCode=" + countryCode + ", cityName=" + cityName + ", fromPostcode=" + fromPostcode + ", toPostcode=" + toPostcode + ", stationCode=" + stationCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
        result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((fromPostcode == null) ? 0 : fromPostcode.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((stationCode == null) ? 0 : stationCode.hashCode());
        result = prime * result + ((toPostcode == null) ? 0 : toPostcode.hashCode());
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
        DhlEsiZoneVo other = (DhlEsiZoneVo) obj;
        if (cityName == null) {
            if (other.cityName != null)
                return false;
        } else if (!cityName.equals(other.cityName))
            return false;
        if (countryCode == null) {
            if (other.countryCode != null)
                return false;
        } else if (!countryCode.equals(other.countryCode))
            return false;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        if (fromPostcode == null) {
            if (other.fromPostcode != null)
                return false;
        } else if (!fromPostcode.equals(other.fromPostcode))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (stationCode == null) {
            if (other.stationCode != null)
                return false;
        } else if (!stationCode.equals(other.stationCode))
            return false;
        if (toPostcode == null) {
            if (other.toPostcode != null)
                return false;
        } else if (!toPostcode.equals(other.toPostcode))
            return false;
        return true;
    }
}
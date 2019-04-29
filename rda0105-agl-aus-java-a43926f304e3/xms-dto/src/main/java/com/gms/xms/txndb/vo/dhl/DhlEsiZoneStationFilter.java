package com.gms.xms.txndb.vo.dhl;

/**
 * Posted from DhlEsiZoneStationFilter
 * <p>
 * Author HungNT Date May 20, 2015
 */
public class DhlEsiZoneStationFilter extends DhlEsiZoneStationVo {
    private static final long serialVersionUID = 6597341906737558227L;

    private String cityName;
    private String postalCode;

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

    @Override
    public String toString() {
        return "DhlEsiZoneStationFilter [cityName=" + cityName + ", postalCode=" + postalCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DhlEsiZoneStationFilter other = (DhlEsiZoneStationFilter) obj;
        if (cityName == null) {
            if (other.cityName != null)
                return false;
        } else if (!cityName.equals(other.cityName))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        return true;
    }
}

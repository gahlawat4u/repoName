package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * @author tkvcl
 */
public class TollIpecZoneFilterModel extends BaseModel {
    private static final long serialVersionUID = -4034595113883908579L;

    /**
     *
     */

    private String postalCode;
    private String cityName;
    private String postalCodeR;
    private String cityNameR;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostalCodeR() {
        return postalCodeR;
    }

    public void setPostalCodeR(String postalCodeR) {
        this.postalCodeR = postalCodeR;
    }

    public String getCityNameR() {
        return cityNameR;
    }

    public void setCityNameR(String cityNameR) {
        this.cityNameR = cityNameR;
    }

    @Override
    public String toString() {
        return "TollIpecZoneFilterModel [postalCode=" + postalCode + ", cityName=" + cityName + ", postalCodeR=" + postalCodeR + ", cityNameR=" + cityNameR + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
        result = prime * result + ((cityNameR == null) ? 0 : cityNameR.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((postalCodeR == null) ? 0 : postalCodeR.hashCode());
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
        TollIpecZoneFilterModel other = (TollIpecZoneFilterModel) obj;
        if (cityName == null) {
            if (other.cityName != null)
                return false;
        } else if (!cityName.equals(other.cityName))
            return false;
        if (cityNameR == null) {
            if (other.cityNameR != null)
                return false;
        } else if (!cityNameR.equals(other.cityNameR))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (postalCodeR == null) {
            if (other.postalCodeR != null)
                return false;
        } else if (!postalCodeR.equals(other.postalCodeR))
            return false;
        return true;
    }


}
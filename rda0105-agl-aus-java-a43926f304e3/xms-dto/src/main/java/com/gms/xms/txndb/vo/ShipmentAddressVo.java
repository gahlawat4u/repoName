package com.gms.xms.txndb.vo;

/**
 * Posted from Sep 22, 2016 10:35:56 AM
 * <p>
 * Author huynd
 */
public class ShipmentAddressVo extends AddressVo {
    private static final long serialVersionUID = 3613971793563517136L;

    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "ShipmentAddressVo [countryName=" + countryName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
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
        ShipmentAddressVo other = (ShipmentAddressVo) obj;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        return true;
    }

}
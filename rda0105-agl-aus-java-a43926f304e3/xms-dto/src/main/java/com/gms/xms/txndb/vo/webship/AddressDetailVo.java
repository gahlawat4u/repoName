package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.AddressVo;

/**
 * Posted from AddressDetailVo
 * <p>
 * Author HungNT Date Jul 24, 2015
 */
public class AddressDetailVo extends AddressVo {
    private static final long serialVersionUID = -8339453186325919995L;
    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
        AddressDetailVo other = (AddressDetailVo) obj;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AddressDetailVo [countryName=" + countryName + "]";
    }
}

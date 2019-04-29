package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.CustomerAddressVo;

/**
 * Posted from CustomerAddressDetailVo
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public class CustomerAddressDetailVo extends CustomerAddressVo {
    private static final long serialVersionUID = 7405556521316749969L;

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
        CustomerAddressDetailVo other = (CustomerAddressDetailVo) obj;
        if (countryName == null) {
            if (other.countryName != null)
                return false;
        } else if (!countryName.equals(other.countryName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CustomerAddressDetailVo [countryName=" + countryName + "]";
    }
}

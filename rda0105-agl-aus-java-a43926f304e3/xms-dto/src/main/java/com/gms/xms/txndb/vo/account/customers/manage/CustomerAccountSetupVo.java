package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.CustomerVo;

/**
 * Posted from CustomerAccountSetupVo
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class CustomerAccountSetupVo extends CustomerVo {

    private static final long serialVersionUID = -8508129035409987660L;

    private CustomerAddressVo address;
    private String countryName;

    @Override
    public String toString() {
        return "CustomerAccountSetupVo [address=" + address + ", countryName=" + countryName + ", toString()=" + super.toString() + "]";
    }

    public CustomerAddressVo getAddress() {
        return address;
    }

    public void setAddress(CustomerAddressVo address) {
        this.address = address;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

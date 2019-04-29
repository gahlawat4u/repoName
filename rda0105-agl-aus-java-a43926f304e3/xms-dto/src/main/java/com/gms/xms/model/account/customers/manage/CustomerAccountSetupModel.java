package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.CustomerModel;

/**
 * Posted from CustomerAccountSetupModel
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class CustomerAccountSetupModel extends CustomerModel {

    private static final long serialVersionUID = 7306742684917506649L;

    private CustomerAddressModel address;
    private String countryName;

    @Override
    public String toString() {
        return "CustomerAccountSetupModel [address=" + address + ", countryName=" + countryName + ", toString()=" + super.toString() + "]";
    }

    public CustomerAddressModel getAddress() {
        return address;
    }

    public void setAddress(CustomerAddressModel address) {
        this.address = address;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

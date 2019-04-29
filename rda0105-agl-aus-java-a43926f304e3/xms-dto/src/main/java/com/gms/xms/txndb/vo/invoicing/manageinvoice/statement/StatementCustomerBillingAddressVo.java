package com.gms.xms.txndb.vo.invoicing.manageinvoice.statement;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from StatementCustomerBillingAddressVo
 * <p>
 * Author dattrinh Mar 16, 2016 3:29:53 PM
 */
public class StatementCustomerBillingAddressVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String customerName;
    private String address1;
    private String address2;
    private String postalCode;
    private String city;
    private String countryName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "StatementCustomerBillingAddressVo [customerName=" + customerName + ", address1=" + address1 + ", address2=" + address2 + ", postalCode=" + postalCode + ", city=" + city + ", countryName=" + countryName + "]";
    }
}

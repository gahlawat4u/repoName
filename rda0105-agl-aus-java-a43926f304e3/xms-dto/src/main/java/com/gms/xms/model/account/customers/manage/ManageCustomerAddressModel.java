package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.invoicing.CustomerBillingAddressModel;

/**
 * Posted from ManageCustomerAddressModel
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class ManageCustomerAddressModel extends BaseModel {

    private static final long serialVersionUID = 2705580005600413200L;

    private String customerCode;
    private CustomerAddressModel address;
    private CustomerBillingAddressModel billingAddress;

    @Override
    public String toString() {
        return "ManageCustomerAddressModel [customerCode=" + customerCode + ", address=" + address + ", billingAddress=" + billingAddress + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public CustomerAddressModel getAddress() {
        return address;
    }

    public void setAddress(CustomerAddressModel address) {
        this.address = address;
    }

    public CustomerBillingAddressModel getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(CustomerBillingAddressModel billingAddress) {
        this.billingAddress = billingAddress;
    }
}
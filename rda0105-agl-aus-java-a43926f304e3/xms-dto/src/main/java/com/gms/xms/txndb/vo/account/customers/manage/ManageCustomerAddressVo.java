package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;

/**
 * Posted from ManageCustomerAddressVo
 * <p>
 * Author DatTV Sep 10, 2015
 */
public class ManageCustomerAddressVo extends BaseVo {

    private static final long serialVersionUID = 2705580005600413200L;

    private String customerCode;
    private CustomerAddressVo address;
    private CustomerBillingAddressVo billingAddress;

    @Override
    public String toString() {
        return "ManageCustomerAddressVo [customerCode=" + customerCode + ", address=" + address + ", billingAddress=" + billingAddress + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public CustomerAddressVo getAddress() {
        return address;
    }

    public void setAddress(CustomerAddressVo address) {
        this.address = address;
    }

    public CustomerBillingAddressVo getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(CustomerBillingAddressVo billingAddress) {
        this.billingAddress = billingAddress;
    }
}
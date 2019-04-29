package com.gms.xms.dto.edigenerate;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Sep 22, 2016 11:44:51 AM
 * <p>
 * Author dattrinh
 */
public class TntShipmentForEtVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private Integer billingType;
    private String billingAccount;
    private Long count;
    private String companyName;
    private String contactName;
    private String phone;
    private String city;
    private String postalCode;
    private String state;
    private String address;
    private String address2;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getBillingType() {
        return billingType;
    }

    public void setBillingType(Integer billingType) {
        this.billingType = billingType;
    }

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Override
    public String toString() {
        return "TntShipmentForEtVo [customerCode=" + customerCode + ", billingType=" + billingType + ", billingAccount=" + billingAccount + ", count=" + count + ", companyName=" + companyName + ", contactName=" + contactName + ", phone=" + phone + ", city=" + city + ", postalCode=" + postalCode + ", state=" + state + ", address=" + address + ", address2=" + address2 + "]";
    }
}

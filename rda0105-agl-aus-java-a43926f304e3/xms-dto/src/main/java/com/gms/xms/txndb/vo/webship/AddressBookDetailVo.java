package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from AddressDetailVo
 * <p>
 * Author DatTV Date Jul 15, 2015 10:39:28 AM
 */
public class AddressBookDetailVo extends BaseVo {

    private static final long serialVersionUID = 8065289619346934980L;

    private Long addressId;
    private String companyName;
    private String phone;
    private String contactName;
    private String email;
    private Long country;
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
    private String state;
    private Integer isResidential;
    private String countryName;

    @Override
    public String toString() {
        return "AddressDetailVo [addressId=" + addressId + ", companyName=" + companyName + ", phone=" + phone + ", contactName=" + contactName + ", email=" + email + ", country=" + country + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", postalCode=" + postalCode + ", state=" + state + ", isResidential=" + isResidential + ", countryName=" + countryName + "]";
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
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

    public Integer getIsResidential() {
        return isResidential;
    }

    public void setIsResidential(Integer isResidential) {
        this.isResidential = isResidential;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
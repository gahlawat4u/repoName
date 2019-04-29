package com.gms.xms.txndb.vo.account.franchises;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from FranchiseDetailVo
 * <p>
 * Author DatTV Oct 22, 2015
 */
public class FranchiseDetailVo extends BaseVo {

    private static final long serialVersionUID = -8347887262442363572L;

    private Long franchiseCode;
    private String franchiseTerritory;
    private String customerName;
    private String address1;
    private String address2;
    private String city;
    private String countryName;
    private String postalCode;
    private String phone;
    private String email;
    private String billingCustomerName;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String billingCountryName;
    private String billingPostalCode;
    private String billingPhone;
    private String dhlInternationalAccount;
    private Date franchiseStartDate;
    private Date createDate;
    private Date activateDate;
    private Integer invoiceTerm;
    private Boolean emailInvoice;

    @Override
    public String toString() {
        return "FranchiseDetailVo [franchiseCode=" + franchiseCode + ", franchiseTerritory=" + franchiseTerritory + ", customerName=" + customerName + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", countryName=" + countryName + ", postalCode=" + postalCode + ", phone=" + phone + ", email=" + email + ", billingCustomerName=" + billingCustomerName + ", billingAddress1=" + billingAddress1 + ", billingAddress2=" + billingAddress2 + ", billingCity=" + billingCity
                + ", billingCountryName=" + billingCountryName + ", billingPostalCode=" + billingPostalCode + ", billingPhone=" + billingPhone + ", dhlInternationalAccount=" + dhlInternationalAccount + ", franchiseStartDate=" + franchiseStartDate + ", createDate=" + createDate + ", activateDate=" + activateDate + ", invoiceTerm=" + invoiceTerm + ", emailInvoice=" + emailInvoice + "]";
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getFranchiseTerritory() {
        return franchiseTerritory;
    }

    public void setFranchiseTerritory(String franchiseTerritory) {
        this.franchiseTerritory = franchiseTerritory;
    }

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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBillingCustomerName() {
        return billingCustomerName;
    }

    public void setBillingCustomerName(String billingCustomerName) {
        this.billingCustomerName = billingCustomerName;
    }

    public String getBillingAddress1() {
        return billingAddress1;
    }

    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingCountryName() {
        return billingCountryName;
    }

    public void setBillingCountryName(String billingCountryName) {
        this.billingCountryName = billingCountryName;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public String getDhlInternationalAccount() {
        return dhlInternationalAccount;
    }

    public void setDhlInternationalAccount(String dhlInternationalAccount) {
        this.dhlInternationalAccount = dhlInternationalAccount;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getFranchiseStartDate() {
        return franchiseStartDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setFranchiseStartDate(Date franchiseStartDate) {
        this.franchiseStartDate = franchiseStartDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getActivateDate() {
        return activateDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    public Integer getInvoiceTerm() {
        return invoiceTerm;
    }

    public void setInvoiceTerm(Integer invoiceTerm) {
        this.invoiceTerm = invoiceTerm;
    }

    public Boolean getEmailInvoice() {
        return emailInvoice;
    }

    public void setEmailInvoice(Boolean emailInvoice) {
        this.emailInvoice = emailInvoice;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}

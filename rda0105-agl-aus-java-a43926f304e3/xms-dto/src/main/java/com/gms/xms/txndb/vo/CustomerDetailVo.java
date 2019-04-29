package com.gms.xms.txndb.vo;

/**
 * Posted from CustomerDetailVo
 * <p>
 * Author DatTV Sep 1, 2015
 */
public class CustomerDetailVo extends CustomerVo {

    private static final long serialVersionUID = -4386553759189245549L;

    private String webshipGroupName;
    private String salesRepName;

    private String customerName;
    private String addressContactName;
    private String addressAddress1;
    private String addressAddress2;
    private String addressCity;
    private String addressCountry;
    private String addressPostalCode;
    private String addressPhone;
    private String addressEmail;

    private String billingCustomerName;
    private String billingContactName;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String billingCountry;
    private String billingPostalCode;
    private String billingPhone;
    private String billingEmail;

    private Integer invoiceDays;

    @Override
    public String toString() {
        return "CustomerDetailVo [webshipGroupName=" + webshipGroupName + ", salesRepName=" + salesRepName + ", customerName=" + customerName + ", addressContactName=" + addressContactName + ", addressAddress1=" + addressAddress1 + ", addressAddress2=" + addressAddress2 + ", addressCity=" + addressCity + ", addressCountry=" + addressCountry + ", addressPostalCode=" + addressPostalCode + ", addressPhone=" + addressPhone + ", addressEmail=" + addressEmail + ", billingCustomerName="
                + billingCustomerName + ", billingContactName=" + billingContactName + ", billingAddress1=" + billingAddress1 + ", billingAddress2=" + billingAddress2 + ", billingCity=" + billingCity + ", billingCountry=" + billingCountry + ", billingPostalCode=" + billingPostalCode + ", billingPhone=" + billingPhone + ", billingEmail=" + billingEmail + ", invoiceDays=" + invoiceDays + "]";
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getWebshipGroupName() {
        return webshipGroupName;
    }

    public void setWebshipGroupName(String webshipGroupName) {
        this.webshipGroupName = webshipGroupName;
    }

    public String getSalesRepName() {
        return salesRepName;
    }

    public void setSalesRepName(String salesRepName) {
        this.salesRepName = salesRepName;
    }

    public String getAddressContactName() {
        return addressContactName;
    }

    public void setAddressContactName(String addressContactName) {
        this.addressContactName = addressContactName;
    }

    public String getAddressAddress1() {
        return addressAddress1;
    }

    public void setAddressAddress1(String addressAddress1) {
        this.addressAddress1 = addressAddress1;
    }

    public String getAddressAddress2() {
        return addressAddress2;
    }

    public void setAddressAddress2(String addressAddress2) {
        this.addressAddress2 = addressAddress2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    public void setAddressPostalCode(String addressPostalCode) {
        this.addressPostalCode = addressPostalCode;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getAddressEmail() {
        return addressEmail;
    }

    public void setAddressEmail(String addressEmail) {
        this.addressEmail = addressEmail;
    }

    public String getBillingCustomerName() {
        return billingCustomerName;
    }

    public void setBillingCustomerName(String billingCustomerName) {
        this.billingCustomerName = billingCustomerName;
    }

    public String getBillingContactName() {
        return billingContactName;
    }

    public void setBillingContactName(String billingContactName) {
        this.billingContactName = billingContactName;
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

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
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

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public Integer getInvoiceDays() {
        return invoiceDays;
    }

    public void setInvoiceDays(Integer invoiceDays) {
        this.invoiceDays = invoiceDays;
    }
}
package com.gms.xms.model.admin.invoicing.manageinvoice;

import com.gms.xms.model.BaseModel;

/**
 * File Name: InvoiceInfoModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 16-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.manageinvoice <br/>
 */
public class InvoiceInfoModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String invoiceId;
    private String invoiceCode;
    private String invoiceDate;
    private String customerCode;
    private String status;
    private String noOfAirbills;
    private String dueDate;
    private String totalCost;
    private String gstTotalCost;
    private String nonGstTotalCost;
    private String totalTax;
    private String gstTotalTax;
    private String nonGstTotalTax;
    private String gstTaxPercent;
    private String nonGstTaxPercent;
    private String gstTotalAmount;
    private String nonGstTotalAmount;
    private String totalAmount;
    private String totalPaid;
    private String totalIfNotPaidByDueDate;
    private String remainingDue;
    private String billingCustomerName;
    private String billingContactName;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String billingStateCode;
    private String billingPostalCode;
    private String billingPhone;
    private String billingEmail;
    private String billingCountryName;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNoOfAirbills() {
        return noOfAirbills;
    }

    public void setNoOfAirbills(String noOfAirbills) {
        this.noOfAirbills = noOfAirbills;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getGstTotalCost() {
        return gstTotalCost;
    }

    public void setGstTotalCost(String gstTotalCost) {
        this.gstTotalCost = gstTotalCost;
    }

    public String getNonGstTotalCost() {
        return nonGstTotalCost;
    }

    public void setNonGstTotalCost(String nonGstTotalCost) {
        this.nonGstTotalCost = nonGstTotalCost;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getGstTotalTax() {
        return gstTotalTax;
    }

    public void setGstTotalTax(String gstTotalTax) {
        this.gstTotalTax = gstTotalTax;
    }

    public String getNonGstTotalTax() {
        return nonGstTotalTax;
    }

    public void setNonGstTotalTax(String nonGstTotalTax) {
        this.nonGstTotalTax = nonGstTotalTax;
    }

    public String getGstTaxPercent() {
        return gstTaxPercent;
    }

    public void setGstTaxPercent(String gstTaxPercent) {
        this.gstTaxPercent = gstTaxPercent;
    }

    public String getNonGstTaxPercent() {
        return nonGstTaxPercent;
    }

    public void setNonGstTaxPercent(String nonGstTaxPercent) {
        this.nonGstTaxPercent = nonGstTaxPercent;
    }

    public String getGstTotalAmount() {
        return gstTotalAmount;
    }

    public void setGstTotalAmount(String gstTotalAmount) {
        this.gstTotalAmount = gstTotalAmount;
    }

    public String getNonGstTotalAmount() {
        return nonGstTotalAmount;
    }

    public void setNonGstTotalAmount(String nonGstTotalAmount) {
        this.nonGstTotalAmount = nonGstTotalAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getTotalIfNotPaidByDueDate() {
        return totalIfNotPaidByDueDate;
    }

    public void setTotalIfNotPaidByDueDate(String totalIfNotPaidByDueDate) {
        this.totalIfNotPaidByDueDate = totalIfNotPaidByDueDate;
    }

    public String getRemainingDue() {
        return remainingDue;
    }

    public void setRemainingDue(String remainingDue) {
        this.remainingDue = remainingDue;
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

    public String getBillingStateCode() {
        return billingStateCode;
    }

    public void setBillingStateCode(String billingStateCode) {
        this.billingStateCode = billingStateCode;
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

    public String getBillingCountryName() {
        return billingCountryName;
    }

    public void setBillingCountryName(String billingCountryName) {
        this.billingCountryName = billingCountryName;
    }

    @Override
    public String toString() {
        return "InvoiceInfoModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", customerCode=" + customerCode + ", status=" + status + ", noOfAirbills=" + noOfAirbills + ", dueDate=" + dueDate + ", totalCost=" + totalCost + ", gstTotalCost=" + gstTotalCost + ", nonGstTotalCost=" + nonGstTotalCost + ", totalTax=" + totalTax + ", gstTotalTax=" + gstTotalTax + ", nonGstTotalTax=" + nonGstTotalTax + ", gstTaxPercent=" + gstTaxPercent
                + ", nonGstTaxPercent=" + nonGstTaxPercent + ", gstTotalAmount=" + gstTotalAmount + ", nonGstTotalAmount=" + nonGstTotalAmount + ", totalAmount=" + totalAmount + ", totalPaid=" + totalPaid + ", totalIfNotPaidByDueDate=" + totalIfNotPaidByDueDate + ", remainingDue=" + remainingDue + ", billingCustomerName=" + billingCustomerName + ", billingContactName=" + billingContactName + ", billingAddress1=" + billingAddress1 + ", billingAddress2=" + billingAddress2 + ", billingCity="
                + billingCity + ", billingStateCode=" + billingStateCode + ", billingPostalCode=" + billingPostalCode + ", billingPhone=" + billingPhone + ", billingEmail=" + billingEmail + ", billingCountryName=" + billingCountryName + "]";
    }
}

package com.gms.xms.txndb.vo.invoicing.manageinvoice;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * @author TANDT
 */
public class InvoiceInfoVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long invoiceId;
    private String invoiceCode;
    private Date invoiceDate;
    private String customerCode;
    private Integer status;
    private Integer noOfAirbills;
    private Date dueDate;
    private Double totalCost;
    private Double gstTotalCost;
    private Double nonGstTotalCost;
    private Double totalTax;
    private Double gstTotalTax;
    private Double nonGstTotalTax;
    private Double gstTaxPercent;
    private Double nonGstTaxPercent = 0.0;
    private Double gstTotalAmount;
    private Double nonGstTotalAmount;
    private Double totalAmount;
    private Double totalPaid;
    private Double totalIfNotPaidByDueDate;
    private Double remainingDue;
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

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNoOfAirbills() {
        return noOfAirbills;
    }

    public void setNoOfAirbills(Integer noOfAirbills) {
        this.noOfAirbills = noOfAirbills;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGstTotalCost() {
        return gstTotalCost;
    }

    public void setGstTotalCost(Double gstTotalCost) {
        this.gstTotalCost = gstTotalCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNonGstTotalCost() {
        return nonGstTotalCost;
    }

    public void setNonGstTotalCost(Double nonGstTotalCost) {
        this.nonGstTotalCost = nonGstTotalCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGstTotalTax() {
        return gstTotalTax;
    }

    public void setGstTotalTax(Double gstTotalTax) {
        this.gstTotalTax = gstTotalTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNonGstTotalTax() {
        return nonGstTotalTax;
    }

    public void setNonGstTotalTax(Double nonGstTotalTax) {
        this.nonGstTotalTax = nonGstTotalTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGstTaxPercent() {
        return gstTaxPercent;
    }

    public void setGstTaxPercent(Double gstTaxPercent) {
        this.gstTaxPercent = gstTaxPercent;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNonGstTaxPercent() {
        return nonGstTaxPercent;
    }

    public void setNonGstTaxPercent(Double nonGstTaxPercent) {
        this.nonGstTaxPercent = nonGstTaxPercent;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGstTotalAmount() {
        return gstTotalAmount;
    }

    public void setGstTotalAmount(Double gstTotalAmount) {
        this.gstTotalAmount = gstTotalAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNonGstTotalAmount() {
        return nonGstTotalAmount;
    }

    public void setNonGstTotalAmount(Double nonGstTotalAmount) {
        this.nonGstTotalAmount = nonGstTotalAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalIfNotPaidByDueDate() {
        return totalIfNotPaidByDueDate;
    }

    public void setTotalIfNotPaidByDueDate(Double totalIfNotPaidByDueDate) {
        this.totalIfNotPaidByDueDate = totalIfNotPaidByDueDate;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRemainingDue() {
        return remainingDue;
    }

    public void setRemainingDue(Double remainingDue) {
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
        return "InvoiceInfoVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", customerCode=" + customerCode + ", status=" + status + ", noOfAirbills=" + noOfAirbills + ", dueDate=" + dueDate + ", totalCost=" + totalCost + ", gstTotalCost=" + gstTotalCost + ", nonGstTotalCost=" + nonGstTotalCost + ", totalTax=" + totalTax + ", gstTotalTax=" + gstTotalTax + ", nonGstTotalTax=" + nonGstTotalTax + ", gstTaxPercent=" + gstTaxPercent
                + ", nonGstTaxPercent=" + nonGstTaxPercent + ", gstTotalAmount=" + gstTotalAmount + ", nonGstTotalAmount=" + nonGstTotalAmount + ", totalAmount=" + totalAmount + ", totalPaid=" + totalPaid + ", totalIfNotPaidByDueDate=" + totalIfNotPaidByDueDate + ", remainingDue=" + remainingDue + ", billingCustomerName=" + billingCustomerName + ", billingContactName=" + billingContactName + ", billingAddress1=" + billingAddress1 + ", billingAddress2=" + billingAddress2 + ", billingCity="
                + billingCity + ", billingStateCode=" + billingStateCode + ", billingPostalCode=" + billingPostalCode + ", billingPhone=" + billingPhone + ", billingEmail=" + billingEmail + ", billingCountryName=" + billingCountryName + "]";
    }
}

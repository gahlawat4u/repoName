package com.gms.xms.model.webship.invoices;

import com.gms.xms.model.BaseModel;

/**
 * Posted from TaxInvoiceModel
 * <p>
 * Author DatTV Date Jul 12, 2015 12:51:28 AM
 */
public class TaxInvoiceModel extends BaseModel {

    private static final long serialVersionUID = -7645753040697958924L;

    private String invoiceId;
    private String invoiceCode;
    private String invoiceDate;
    private String customerCode;
    private String airbillCount;
    private String dueDate;
    private String totalAmount;
    private String totalPayment;
    private String invoiceLateFee;
    private String ifNotPaidByDue;

    @Override
    public String toString() {
        return "TaxInvoiceModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", customerCode=" + customerCode + ", airbillCount=" + airbillCount + ", dueDate=" + dueDate + ", totalAmount=" + totalAmount + ", totalPayment=" + totalPayment + ", invoiceLateFee=" + invoiceLateFee + ", ifNotPaidByDue=" + ifNotPaidByDue + "]";
    }

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

    public String getAirbillCount() {
        return airbillCount;
    }

    public void setAirbillCount(String airbillCount) {
        this.airbillCount = airbillCount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getInvoiceLateFee() {
        return invoiceLateFee;
    }

    public void setInvoiceLateFee(String invoiceLateFee) {
        this.invoiceLateFee = invoiceLateFee;
    }

    public String getIfNotPaidByDue() {
        return ifNotPaidByDue;
    }

    public void setIfNotPaidByDue(String ifNotPaidByDue) {
        this.ifNotPaidByDue = ifNotPaidByDue;
    }
}

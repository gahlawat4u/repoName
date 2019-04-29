package com.gms.xms.txndb.model.admin.receivables.reminderletter;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Mar 31, 2016 4:53:43 PM
 * <p>
 * Author dattrinh
 */
public class ReminderInvoiceDetailModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private String customerName;
    private String email;
    private String invoiceDate;
    private String invoiceTerms;
    private String dueDate;
    private String invoiceCode;
    private String totalAmount;
    private String totalPaid;
    private String invoiceAge;
    private String lateFee;
    private String amountDue;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceTerms() {
        return invoiceTerms;
    }

    public void setInvoiceTerms(String invoiceTerms) {
        this.invoiceTerms = invoiceTerms;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
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

    public String getInvoiceAge() {
        return invoiceAge;
    }

    public void setInvoiceAge(String invoiceAge) {
        this.invoiceAge = invoiceAge;
    }

    public String getLateFee() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ReminderInvoiceDetailModel [customerCode=" + customerCode + ", customerName=" + customerName + ", email=" + email + ", invoiceDate=" + invoiceDate + ", invoiceTerms=" + invoiceTerms + ", dueDate=" + dueDate + ", invoiceCode=" + invoiceCode + ", totalAmount=" + totalAmount + ", totalPaid=" + totalPaid + ", invoiceAge=" + invoiceAge + ", lateFee=" + lateFee + ", amountDue=" + amountDue + "]";
    }
}

package com.gms.xms.model.admin.invoicing.manageinvoice.statement;

import com.gms.xms.model.BaseModel;

/**
 * Posted from StatementInvoiceModel
 * <p>
 * Author dattrinh Mar 16, 2016 4:21:00 PM
 */
public class StatementInvoiceModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String invoiceId;
    private String invoiceCode;
    private String invoiceDate;
    private String dueDate;
    private String invoiceAmount;
    private String lateFee;
    private String invoiceTotal;
    private String totalPaid;
    private String remainingDue;

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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getLateFee() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }

    public String getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(String invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getRemainingDue() {
        return remainingDue;
    }

    public void setRemainingDue(String remainingDue) {
        this.remainingDue = remainingDue;
    }

    @Override
    public String toString() {
        return "StatementInvoiceModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", invoiceAmount=" + invoiceAmount + ", lateFee=" + lateFee + ", invoiceTotal=" + invoiceTotal + ", totalPaid=" + totalPaid + ", remainingDue=" + remainingDue + "]";
    }
}

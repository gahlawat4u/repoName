package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ManageCustomerInvoiceModel
 * <p>
 * Author DatTV Sep 24, 2015
 */
public class ManageCustomerInvoiceModel extends BaseModel {

    private static final long serialVersionUID = -758996843402324961L;

    private String invoiceId;
    private String invoiceCode;
    private String total;
    private String lateFee;
    private String paid;
    private String owed;
    private String invoiceDate;
    private String dueDate;
    private String overDue;
    private String airbillCount;
    private String totalCost;
    private String status;

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLateFee() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getOwed() {
        return owed;
    }

    public void setOwed(String owed) {
        this.owed = owed;
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

    public String getOverDue() {
        return overDue;
    }

    public void setOverDue(String overDue) {
        this.overDue = overDue;
    }

    public String getAirbillCount() {
        return airbillCount;
    }

    public void setAirbillCount(String airbillCount) {
        this.airbillCount = airbillCount;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ManageCustomerInvoiceModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", total=" + total + ", lateFee=" + lateFee + ", paid=" + paid + ", owed=" + owed + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", overDue=" + overDue + ", airbillCount=" + airbillCount + ", totalCost=" + totalCost + ", status=" + status + "]";
    }
}

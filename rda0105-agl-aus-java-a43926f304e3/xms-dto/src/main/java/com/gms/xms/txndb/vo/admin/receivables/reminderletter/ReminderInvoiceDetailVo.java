package com.gms.xms.txndb.vo.admin.receivables.reminderletter;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * Posted from Mar 31, 2016 4:53:43 PM
 * <p>
 * Author dattrinh
 */
public class ReminderInvoiceDetailVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private String customerName;
    private String email;
    private Date invoiceDate;
    private Integer invoiceTerms;
    private Date dueDate;
    private String invoiceCode;
    private Double totalAmount;
    private Double totalPaid;
    private Integer invoiceAge;
    private Double lateFee;
    private Double amountDue;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getInvoiceTerms() {
        return invoiceTerms;
    }

    public void setInvoiceTerms(Integer invoiceTerms) {
        this.invoiceTerms = invoiceTerms;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Integer getInvoiceAge() {
        return invoiceAge;
    }

    public void setInvoiceAge(Integer invoiceAge) {
        this.invoiceAge = invoiceAge;
    }

    public Double getLateFee() {
        return lateFee;
    }

    public void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
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
        return "ReminderInvoiceDetailVo [customerCode=" + customerCode + ", customerName=" + customerName + ", email=" + email + ", invoiceDate=" + invoiceDate + ", invoiceTerms=" + invoiceTerms + ", dueDate=" + dueDate + ", invoiceCode=" + invoiceCode + ", totalAmount=" + totalAmount + ", totalPaid=" + totalPaid + ", invoiceAge=" + invoiceAge + ", lateFee=" + lateFee + ", amountDue=" + amountDue + "]";
    }
}

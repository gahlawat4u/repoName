package com.gms.xms.model.franchisepayable;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ReceivePaymentModel
 * <p>
 * Author DatTV Date Apr 10, 2015 8:58:34 AM
 */
public class ReceivePaymentModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String customerOrInvoiceCode;
    private String customerName;
    private String invoiceCode;
    private String customerCode;
    private String paymentType;
    private String cheque;
    private String amount;
    private String bankId;
    private String paymentDate;
    private String note;
    private String appliedAmount;
    private String unappliedAmount;
    private String submitType;
    private String totalOverpayment;
    private String searchOption;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(String appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public String getUnappliedAmount() {
        return unappliedAmount;
    }

    public void setUnappliedAmount(String unappliedAmount) {
        this.unappliedAmount = unappliedAmount;
    }

    public String getCustomerOrInvoiceCode() {
        return customerOrInvoiceCode;
    }

    public void setCustomerOrInvoiceCode(String customerOrInvoiceCode) {
        this.customerOrInvoiceCode = customerOrInvoiceCode;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public String getTotalOverpayment() {
        return totalOverpayment;
    }

    public void setTotalOverpayment(String totalOverpayment) {
        this.totalOverpayment = totalOverpayment;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "ReceivePaymentModel [customerOrInvoiceCode=" + customerOrInvoiceCode + ", customerName=" + customerName + ", invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", paymentType=" + paymentType + ", cheque=" + cheque + ", amount=" + amount + ", bankId=" + bankId + ", paymentDate=" + paymentDate + ", note=" + note + ", appliedAmount=" + appliedAmount + ", unappliedAmount=" + unappliedAmount + ", submitType=" + submitType + ", totalOverpayment=" + totalOverpayment
                + ", searchOption=" + searchOption + "]";
    }
}
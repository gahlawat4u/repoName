package com.gms.xms.model;

import java.util.List;

/**
 * Posted from CustomerPaymentModel
 * <p>
 * Author DatTV Sep 26, 2015
 */
public class CustomerPaymentModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String cusPaymentId;

    private String customerCode;

    private String amount;

    private String cheque;

    private String bankId;

    private String paymentDate;

    private String cimPaymentTransactionId;

    private String paymentType;

    private List<InvoicePaymentModel> invoicePayments;

    private List<NoteModel> notes;

    @Override
    public String toString() {
        return "CustomerPaymentModel [cusPaymentId=" + cusPaymentId + ", customerCode=" + customerCode + ", amount=" + amount + ", cheque=" + cheque + ", bankId=" + bankId + ", paymentDate=" + paymentDate + ", cimPaymentTransactionId=" + cimPaymentTransactionId + ", paymentType=" + paymentType + ", notes=" + notes + "]";
    }

    public String getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(String cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
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

    public String getCimPaymentTransactionId() {
        return cimPaymentTransactionId;
    }

    public void setCimPaymentTransactionId(String cimPaymentTransactionId) {
        this.cimPaymentTransactionId = cimPaymentTransactionId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<InvoicePaymentModel> getInvoicePayments() {
        return invoicePayments;
    }

    public void setInvoicePayments(List<InvoicePaymentModel> invoicePayments) {
        this.invoicePayments = invoicePayments;
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }
}
package com.gms.xms.model.admin.receivables.payments;

import com.gms.xms.model.BaseModel;

/**
 * Posted from PaymentDetailModel
 * <p>
 * Author dattrinh Mar 17, 2016 2:33:43 PM
 */
public class PaymentDetailModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String cusPaymentId;
    private String customerCode;
    private String customerName;
    private String paymentDate;
    private String amount;
    private String cheque;
    private String note;
    private String overAmount;
    private String creditType;
    private String overPaymentType;
    private String isCredit;
    private String invoiceList;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOverAmount() {
        return overAmount;
    }

    public void setOverAmount(String overAmount) {
        this.overAmount = overAmount;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getOverPaymentType() {
        return overPaymentType;
    }

    public void setOverPaymentType(String overPaymentType) {
        this.overPaymentType = overPaymentType;
    }

    public String getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(String isCredit) {
        this.isCredit = isCredit;
    }

    public String getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(String invoiceList) {
        this.invoiceList = invoiceList;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentDetailModel [cusPaymentId=" + cusPaymentId + ", customerCode=" + customerCode + ", customerName=" + customerName + ", paymentDate=" + paymentDate + ", amount=" + amount + ", cheque=" + cheque + ", note=" + note + ", overAmount=" + overAmount + ", creditType=" + creditType + ", overPaymentType=" + overPaymentType + ", isCredit=" + isCredit + ", invoiceList=" + invoiceList + "]";
    }
}

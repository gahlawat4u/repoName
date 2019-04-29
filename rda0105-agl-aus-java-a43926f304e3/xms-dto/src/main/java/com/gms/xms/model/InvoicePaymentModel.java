package com.gms.xms.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Posted from InvoicePaymentModel
 * <p>
 * Author DatTV Sep 26, 2015
 */
public class InvoicePaymentModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String invoicePaymentId;

    private String invoiceId;

    private String invoiceCode;

    private String amount;

    private String lateFee;

    private String cusPaymentId;

    private String reverseFlag;

    private String revInvoicePaymentId;

    private String applyDate;

    private CustomerPaymentModel customerPayment;

    private List<InvoicePaymentDetailModel> invoicePaymentDetails;

    private String canReverse;

    private BigDecimal remainningBalance;

    @Override
    public String toString() {
        return "InvoicePaymentModel [invoicePaymentId=" + invoicePaymentId + ", invoiceId=" + invoiceId + ", amount=" + amount + ", lateFee=" + lateFee + ", cusPaymentId=" + cusPaymentId + ", reverseFlag=" + reverseFlag + ", revInvoicePaymentId=" + revInvoicePaymentId + ", applyDate=" + applyDate + "]";
    }

    public String getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(String invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLateFee() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }

    public String getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(String cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public String getReverseFlag() {
        return reverseFlag;
    }

    public void setReverseFlag(String reverseFlag) {
        this.reverseFlag = reverseFlag;
    }

    public String getRevInvoicePaymentId() {
        return revInvoicePaymentId;
    }

    public void setRevInvoicePaymentId(String revInvoicePaymentId) {
        this.revInvoicePaymentId = revInvoicePaymentId;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public List<InvoicePaymentDetailModel> getInvoicePaymentDetails() {
        return invoicePaymentDetails;
    }

    public void setInvoicePaymentDetails(List<InvoicePaymentDetailModel> invoicePaymentDetails) {
        this.invoicePaymentDetails = invoicePaymentDetails;
    }

    public CustomerPaymentModel getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(CustomerPaymentModel customerPayment) {
        this.customerPayment = customerPayment;
    }

    public String getCanReverse() {
        return canReverse;
    }

    public void setCanReverse(String canReverse) {
        this.canReverse = canReverse;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public BigDecimal getRemainningBalance() {
        return remainningBalance;
    }

    public void setRemainningBalance(BigDecimal remainningBalance) {
        this.remainningBalance = remainningBalance;
    }
}
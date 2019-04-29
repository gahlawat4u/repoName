package com.gms.xms.txndb.model.admin.receivables.reminderletter;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Mar 31, 2016 2:52:59 PM
 * <p>
 * Author dattrinh
 */
public class ReminderLetterModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String invoiceId;
    private String invoiceCode;
    private String invoiceDate;
    private String customerCode;
    private String emailCustomerCode;

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

    public String getEmailCustomerCode() {
        return emailCustomerCode;
    }

    public void setEmailCustomerCode(String emailCustomerCode) {
        this.emailCustomerCode = emailCustomerCode;
    }

    @Override
    public String toString() {
        return "ReminderLetterModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", customerCode=" + customerCode + ", emailCustomerCode=" + emailCustomerCode + "]";
    }
}

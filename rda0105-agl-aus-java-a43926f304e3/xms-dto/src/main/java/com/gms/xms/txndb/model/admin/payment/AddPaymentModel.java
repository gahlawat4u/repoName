package com.gms.xms.txndb.model.admin.payment;

import com.gms.xms.model.CustomerPaymentModel;

/**
 * Posted from Apr 23, 2016 10:56:54 AM
 * <p>
 * Author dattrinh
 */
public class AddPaymentModel extends CustomerPaymentModel {

    private static final long serialVersionUID = 1L;

    private String invoicePaymentId;
    private String invoiceCode;
    private String note;

    public String getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(String invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
}

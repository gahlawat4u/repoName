package com.gms.xms.txndb.vo.admin.payment;

import com.gms.xms.txndb.vo.CustomerPaymentVo;

/**
 * Posted from Apr 23, 2016 10:56:54 AM
 * <p>
 * Author dattrinh
 */
public class AddPaymentVo extends CustomerPaymentVo {

    private static final long serialVersionUID = 1L;

    private Long invoicePaymentId;
    private String invoiceCode;
    private String note;

    public Long getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(Long invoicePaymentId) {
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

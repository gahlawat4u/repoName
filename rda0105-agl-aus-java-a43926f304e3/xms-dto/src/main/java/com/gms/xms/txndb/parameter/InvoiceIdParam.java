package com.gms.xms.txndb.parameter;

/**
 * Posted from InvoiceIdParam
 * <p>
 * Author dattrinh Mar 11, 2016 3:33:02 PM
 */
public class InvoiceIdParam {
    private Long newInvoiceId;
    private Long oldInvoiceId;

    public Long getNewInvoiceId() {
        return newInvoiceId;
    }

    public void setNewInvoiceId(Long newInvoiceId) {
        this.newInvoiceId = newInvoiceId;
    }

    public Long getOldInvoiceId() {
        return oldInvoiceId;
    }

    public void setOldInvoiceId(Long oldInvoiceId) {
        this.oldInvoiceId = oldInvoiceId;
    }

    @Override
    public String toString() {
        return "InvoiceIdParam [newInvoiceId=" + newInvoiceId + ", oldInvoiceId=" + oldInvoiceId + "]";
    }
}

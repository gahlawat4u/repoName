package com.gms.xms.txndb.vo;

/**
 * Posted from InvoiceTermVo
 * <p>
 * Author DatTV Sep 22, 2015
 */
public class InvoiceTermVo extends BaseVo {

    private static final long serialVersionUID = 3223045288043629435L;

    private Integer invoiceTermId;
    private Integer days;

    @Override
    public String toString() {
        return "InvoiceTermVo [invoiceTermId=" + invoiceTermId + ", days=" + days + "]";
    }

    public Integer getInvoiceTermId() {
        return invoiceTermId;
    }

    public void setInvoiceTermId(Integer invoiceTermId) {
        this.invoiceTermId = invoiceTermId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
package com.gms.xms.model;

public class InvoiceTermModel extends BaseModel {

    private static final long serialVersionUID = 4169895699537024585L;

    private String invoiceTermId;
    private String days;

    @Override
    public String toString() {
        return "InvoiceTermVo [invoiceTermId=" + invoiceTermId + ", days=" + days + "]";
    }

    public String getInvoiceTermId() {
        return invoiceTermId;
    }

    public void setInvoiceTermId(String invoiceTermId) {
        this.invoiceTermId = invoiceTermId;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
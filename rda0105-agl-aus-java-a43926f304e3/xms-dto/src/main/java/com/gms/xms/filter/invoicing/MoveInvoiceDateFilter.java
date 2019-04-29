package com.gms.xms.filter.invoicing;

import java.util.Date;

/**
 * Posted from MoveInvoiceDateFilter
 * <p>
 * Author dattrinh Mar 9, 2016 11:15:45 AM
 */
public class MoveInvoiceDateFilter {
    private String airbillList;
    private Date invoiceDate;

    public String getAirbillList() {
        return airbillList;
    }

    public void setAirbillList(String airbillList) {
        this.airbillList = airbillList;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public String toString() {
        return "MoveInvoiceDateFilter [airbillList=" + airbillList + ", invoiceDate=" + invoiceDate + "]";
    }
}

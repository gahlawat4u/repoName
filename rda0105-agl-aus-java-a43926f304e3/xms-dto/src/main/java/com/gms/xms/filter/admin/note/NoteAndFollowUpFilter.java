package com.gms.xms.filter.admin.note;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from Apr 26, 2016 2:01:26 PM
 * <p>
 * Author dattrinh
 */
public class NoteAndFollowUpFilter extends BaseFilter {
    private String catType;
    private String invoiceCode;

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
}

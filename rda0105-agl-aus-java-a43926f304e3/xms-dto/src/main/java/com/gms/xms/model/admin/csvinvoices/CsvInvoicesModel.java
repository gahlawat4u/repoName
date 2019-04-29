package com.gms.xms.model.admin.csvinvoices;

import com.gms.xms.model.BaseModel;

public class CsvInvoicesModel extends BaseModel {
    private static final long serialVersionUID = -7291491641365542228L;
    private String invoiceId;
    private String invoiceCode;
    private String invoiceStr;

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

    public String getInvoiceStr() {
        return invoiceStr;
    }

    public void setInvoiceStr(String invoiceStr) {
        this.invoiceStr = invoiceStr;
    }

    @Override
    public String toString() {
        return "CsvInvoicesModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceStr=" + invoiceStr + "]";
    }
}

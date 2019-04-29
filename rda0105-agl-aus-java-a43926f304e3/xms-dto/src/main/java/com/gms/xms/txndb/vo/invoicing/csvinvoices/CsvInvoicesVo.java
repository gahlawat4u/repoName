package com.gms.xms.txndb.vo.invoicing.csvinvoices;

import com.gms.xms.txndb.vo.BaseVo;

public class CsvInvoicesVo extends BaseVo {
    private static final long serialVersionUID = 5116206508783409347L;

    private Long invoiceId;
    private String invoiceCode;
    private String invoiceStr;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
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
        return "CsvInvoicesVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceStr=" + invoiceStr + "]";
    }
}

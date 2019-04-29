package com.gms.xms.filter.invoicing;

import com.gms.xms.filter.BaseFilter;

public class CsvInvoicesExportFilter extends BaseFilter {
    private Long invoiceId;
    private String franchiseCode;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }
}

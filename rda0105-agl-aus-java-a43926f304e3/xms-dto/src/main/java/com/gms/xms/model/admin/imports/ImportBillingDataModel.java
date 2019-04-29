package com.gms.xms.model.admin.imports;

import com.gms.xms.model.BaseModel;

/**
 * Posted from May 24, 2016 8:50:37 AM
 * <p>
 * Author huynd
 */
public class ImportBillingDataModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String carrierId;
    private String invoiceDate;
    private String hasHeader;

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(String hasHeader) {
        this.hasHeader = hasHeader;
    }

    @Override
    public String toString() {
        return "ImportBillingDataModel [carrierId=" + carrierId + ", invoiceDate=" + invoiceDate + ", hasHeader=" + hasHeader + "]";
    }
}

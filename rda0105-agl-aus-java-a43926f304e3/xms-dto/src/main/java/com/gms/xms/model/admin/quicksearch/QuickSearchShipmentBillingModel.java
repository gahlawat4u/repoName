package com.gms.xms.model.admin.quicksearch;

import com.gms.xms.model.ShipmentBillingModel;

/**
 * Posted from Apr 27, 2016 2:02:01 PM
 * <p>
 * Author huynd
 */
public class QuickSearchShipmentBillingModel extends ShipmentBillingModel {

    private static final long serialVersionUID = 1L;

    private String invoiceId;
    private String invoiceCode;
    private String importDateTime;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getImportDateTime() {
        return importDateTime;
    }

    public void setImportDateTime(String importDateTime) {
        this.importDateTime = importDateTime;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "QuickSearchShipmentBillingModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", importDateTime=" + importDateTime + "]";
    }
}

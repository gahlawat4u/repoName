package com.gms.xms.filter;

/**
 * Posted from Aug 26, 2016 5:13:27 PM
 * <p>
 * Author dattrinh
 */
public class AirbillChangeLogFilter extends BaseFilter {
    private Long invoiceId;
    private Long shipmentId;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}

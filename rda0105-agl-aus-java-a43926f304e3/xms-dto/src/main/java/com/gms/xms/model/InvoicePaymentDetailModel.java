package com.gms.xms.model;

/**
 * Posted from InvoicePaymentDetailModel
 * <p>
 * Author DatTV Sep 26, 2015
 */
public class InvoicePaymentDetailModel extends BaseModel {
    private static final long serialVersionUID = -3288278730257113173L;

    private String invoicePaymentId;

    private String shipmentId;

    private String airbillNumber;

    private String amount;

    @Override
    public String toString() {
        return "InvoicePaymentDetailModel [invoicePaymentId=" + invoicePaymentId + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", amount=" + amount + "]";
    }

    public String getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(String invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
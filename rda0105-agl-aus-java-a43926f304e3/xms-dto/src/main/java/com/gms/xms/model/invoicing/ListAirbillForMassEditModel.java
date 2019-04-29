package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

/**
 * Posted from May 11, 2016 4:03:03 PM
 * <p>
 * Author huynd
 */
public class ListAirbillForMassEditModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String shipmentId;
    private String airbillNumber;
    private String invoiceId;
    private String invoiceDate;
    private String customerCode;
    private String invoiceStatus;

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

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public String toString() {
        return "ListAirbillForMassEditModel [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", customerCode=" + customerCode + ", invoiceStatus=" + invoiceStatus + "]";
    }
}

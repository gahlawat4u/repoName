package com.gms.xms.model.payables.salesrep;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Mar 25, 2016 9:35:58 AM
 * <p>
 * Author dattrinh
 */
public class SalesRepAirbillStatModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String invoiceCode;
    private String customerCode;
    private String customerName;
    private String shipmentId;
    private String airbillNumber;
    private String totalPaid;
    private String customerCost;
    private String franchiseCost;
    private String margin;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(String customerCost) {
        this.customerCost = customerCost;
    }

    public String getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(String franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    @Override
    public String toString() {
        return "SalesRepAirbillStatModel [invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", customerName=" + customerName + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", totalPaid=" + totalPaid + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", margin=" + margin + "]";
    }
}

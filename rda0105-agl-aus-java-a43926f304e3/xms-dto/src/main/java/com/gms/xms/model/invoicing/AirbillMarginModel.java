package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

/**
 * Posted from AirbillMarginModel
 * <p>
 * Author dattrinh Mar 15, 2016 3:19:59 PM
 */
public class AirbillMarginModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String shipmentId;
    private String airbillNumber;
    private String invoiceId;
    private String invoiceCode;
    private String customerCost;
    private String franchiseCost;
    private String carrierCost;
    private String franchiseMargin;
    private String franchiseMarginPct;
    private String shipmentTypeName;

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

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
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

    public String getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(String carrierCost) {
        this.carrierCost = carrierCost;
    }

    public String getFranchiseMargin() {
        return franchiseMargin;
    }

    public void setFranchiseMargin(String franchiseMargin) {
        this.franchiseMargin = franchiseMargin;
    }

    public String getFranchiseMarginPct() {
        return franchiseMarginPct;
    }

    public void setFranchiseMarginPct(String franchiseMarginPct) {
        this.franchiseMarginPct = franchiseMarginPct;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    @Override
    public String toString() {
        return "AirbillMarginModel [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", franchiseMargin=" + franchiseMargin + ", franchiseMarginPct=" + franchiseMarginPct + ", shipmentTypeName=" + shipmentTypeName + "]";
    }
}

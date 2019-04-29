package com.gms.xms.model.admin.imports;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ReconcileAirbillModel
 * <p>
 * Author dattrinh Mar 14, 2016 5:42:28 PM
 */
public class ReconcileAirbillModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String shipmentId;
    private String airbillNumber;
    private String invoiceCode;
    private String importDate;
    private String carrierCost;
    private String customerCost;
    private String franchiseCost;

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

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(String carrierCost) {
        this.carrierCost = carrierCost;
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

    @Override
    public String toString() {
        return "ReconcileAirbillModel [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", invoiceCode=" + invoiceCode + ", importDate=" + importDate + ", carrierCost=" + carrierCost + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + "]";
    }
}

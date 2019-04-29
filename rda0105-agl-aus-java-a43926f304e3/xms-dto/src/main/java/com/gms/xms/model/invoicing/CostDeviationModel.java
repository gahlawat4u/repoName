package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CostDeviationModel
 * <p>
 * Author dattrinh Mar 14, 2016 10:44:57 AM
 */
public class CostDeviationModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String shipmentId;
    private String airbillNumber;
    private String invoiceId;
    private String invoiceCode;
    private String calculatedFranchiseCost;
    private String franchiseCost;
    private String difference;
    private String serviceName;
    private String weight;

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

    public String getCalculatedFranchiseCost() {
        return calculatedFranchiseCost;
    }

    public void setCalculatedFranchiseCost(String calculatedFranchiseCost) {
        this.calculatedFranchiseCost = calculatedFranchiseCost;
    }

    public String getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(String franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CostDeviationModel [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", calculatedFranchiseCost=" + calculatedFranchiseCost + ", franchiseCost=" + franchiseCost + ", difference=" + difference + ", serviceName=" + serviceName + ", weight=" + weight + "]";
    }
}

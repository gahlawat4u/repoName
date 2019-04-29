package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ImportTotalReportModel
 * <p>
 * Author dattrinh Mar 9, 2016 5:03:37 PM
 */
public class ImportTotalReportModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String invoiceId;
    private String invoiceCode;
    private String customerCost;
    private String franchiseCost;
    private String carrierCost;
    private String margin;
    private String airbillCount;

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

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getAirbillCount() {
        return airbillCount;
    }

    public void setAirbillCount(String airbillCount) {
        this.airbillCount = airbillCount;
    }

    @Override
    public String toString() {
        return "ImportTotalReportModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", margin=" + margin + ", airbillCount=" + airbillCount + "]";
    }
}

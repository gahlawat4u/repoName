package com.gms.xms.model.reports.customer.thirdteenweeks;

import com.gms.xms.model.BaseModel;

/**
 * Posted from WeekActivityModel.java
 * <p>
 * Author dattrinh 4:12:09 PM
 */
public class CustomerWeekActivityModel extends BaseModel {

    private static final long serialVersionUID = 8296077368320135101L;

    private String customerCode;
    private String customerName;
    private String revenue;
    private String franchiseCost;
    private String carrierCost;
    private String grossMargin;
    private String grossMarginPct;
    private String documentShipmentCount;
    private String documentRevenue;
    private String packageShipmentCount;
    private String packageRevenue;
    private String totalAirbills;
    private String grossMarginPerAwb;
    private String grossMarginPerAwbPct;

    @Override
    public String toString() {
        return "CustomerWeekActivityModel [customerCode=" + customerCode + ", customerName=" + customerName + ", revenue=" + revenue + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", grossMargin=" + grossMargin + ", grossMarginPct=" + grossMarginPct + ", documentShipmentCount=" + documentShipmentCount + ", documentRevenue=" + documentRevenue + ", packageShipmentCount=" + packageShipmentCount + ", packageRevenue=" + packageRevenue + ", totalAirbills=" + totalAirbills
                + ", grossMarginPerAwb=" + grossMarginPerAwb + ", grossMarginPerAwbPct=" + grossMarginPerAwbPct + "]";
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

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(String carrierCost) {
        this.carrierCost = carrierCost;
    }

    public String getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(String grossMargin) {
        this.grossMargin = grossMargin;
    }

    public String getGrossMarginPct() {
        return grossMarginPct;
    }

    public void setGrossMarginPct(String grossMarginPct) {
        this.grossMarginPct = grossMarginPct;
    }

    public String getDocumentShipmentCount() {
        return documentShipmentCount;
    }

    public void setDocumentShipmentCount(String documentShipmentCount) {
        this.documentShipmentCount = documentShipmentCount;
    }

    public String getDocumentRevenue() {
        return documentRevenue;
    }

    public void setDocumentRevenue(String documentRevenue) {
        this.documentRevenue = documentRevenue;
    }

    public String getPackageShipmentCount() {
        return packageShipmentCount;
    }

    public void setPackageShipmentCount(String packageShipmentCount) {
        this.packageShipmentCount = packageShipmentCount;
    }

    public String getPackageRevenue() {
        return packageRevenue;
    }

    public void setPackageRevenue(String packageRevenue) {
        this.packageRevenue = packageRevenue;
    }

    public String getTotalAirbills() {
        return totalAirbills;
    }

    public void setTotalAirbills(String totalAirbills) {
        this.totalAirbills = totalAirbills;
    }

    public String getGrossMarginPerAwb() {
        return grossMarginPerAwb;
    }

    public void setGrossMarginPerAwb(String grossMarginPerAwb) {
        this.grossMarginPerAwb = grossMarginPerAwb;
    }

    public String getGrossMarginPerAwbPct() {
        return grossMarginPerAwbPct;
    }

    public void setGrossMarginPerAwbPct(String grossMarginPerAwbPct) {
        this.grossMarginPerAwbPct = grossMarginPerAwbPct;
    }

    public String getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(String franchiseCost) {
        this.franchiseCost = franchiseCost;
    }
}

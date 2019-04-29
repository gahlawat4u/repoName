package com.gms.xms.txndb.vo.reports.customer.thirdteenweeks;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from WeekActivityVo.java
 * <p>
 * Author dattrinh 4:04:50 PM
 */
public class CustomerWeekActivityVo extends BaseVo {

    private static final long serialVersionUID = 7566644154038038569L;

    private String customerCode;
    private String customerName;
    private Double revenue;
    private Double franchiseCost;
    private Double carrierCost;
    private Double grossMargin;
    private Double grossMarginPct;
    private Long documentShipmentCount;
    private Double documentRevenue;
    private Long packageShipmentCount;
    private Double packageRevenue;
    private Long totalAirbills;
    private Double grossMarginPerAwb;
    private Double grossMarginPerAwbPct;

    @Override
    public String toString() {
        return "CustomerWeekActivityVo [customerCode=" + customerCode + ", customerName=" + customerName + ", revenue=" + revenue + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", grossMargin=" + grossMargin + ", grossMarginPct=" + grossMarginPct + ", documentShipmentCount=" + documentShipmentCount + ", documentRevenue=" + documentRevenue + ", packageShipmentCount=" + packageShipmentCount + ", packageRevenue=" + packageRevenue + ", totalAirbills=" + totalAirbills
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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(Double carrierCost) {
        this.carrierCost = carrierCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(Double grossMargin) {
        this.grossMargin = grossMargin;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMarginPct() {
        return grossMarginPct;
    }

    public void setGrossMarginPct(Double grossMarginPct) {
        this.grossMarginPct = grossMarginPct;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getDocumentShipmentCount() {
        return documentShipmentCount;
    }

    public void setDocumentShipmentCount(Long documentShipmentCount) {
        this.documentShipmentCount = documentShipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDocumentRevenue() {
        return documentRevenue;
    }

    public void setDocumentRevenue(Double documentRevenue) {
        this.documentRevenue = documentRevenue;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getPackageShipmentCount() {
        return packageShipmentCount;
    }

    public void setPackageShipmentCount(Long packageShipmentCount) {
        this.packageShipmentCount = packageShipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getPackageRevenue() {
        return packageRevenue;
    }

    public void setPackageRevenue(Double packageRevenue) {
        this.packageRevenue = packageRevenue;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getTotalAirbills() {
        return totalAirbills;
    }

    public void setTotalAirbills(Long totalAirbills) {
        this.totalAirbills = totalAirbills;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMarginPerAwb() {
        return grossMarginPerAwb;
    }

    public void setGrossMarginPerAwb(Double grossMarginPerAwb) {
        this.grossMarginPerAwb = grossMarginPerAwb;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMarginPerAwbPct() {
        return grossMarginPerAwbPct;
    }

    public void setGrossMarginPerAwbPct(Double grossMarginPerAwbPct) {
        this.grossMarginPerAwbPct = grossMarginPerAwbPct;
    }


    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCost() {
        return franchiseCost;
    }


    public void setFranchiseCost(Double franchiseCost) {
        this.franchiseCost = franchiseCost;
    }
}

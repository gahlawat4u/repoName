package com.gms.xms.txndb.vo.reports.customer;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from CustomerSummaryVo
 * <p>
 * Author DatTV Sep 15, 2015
 */
public class CustomerSummaryVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private String customerName;
    private String saleRepName;
    private Double customerRevenue;
    private Double franchiseCost;
    private Double carrierCost;
    private Double grossMargin;
    private Double grossMarginPct;
    private Double customerCostBaseCharge;
    private Double carrierCostBaseCharge;
    private Double marginOnBaseCharge;
    private Double dhlCost;
    private Double dhlRevenue;
    private Double dhlFranchiseCost;
    private Double dhlGrossMargin;
    private Long dhlShipmentCount;
    private Double tntDomCost;
    private Double tntDomRevenue;
    private Double tntDomFranchiseCost;
    private Double tntDomGrossMargin;
    private Long tntDomShipmentCount;
    private Double dhlDomCost;
    private Double dhlDomRevenue;
    private Double dhlDomFranchiseCost;
    private Double dhlDomGrossMargin;
    private Long dhlDomShipmentCount;
    private Double tollPriorityCost;
    private Double tollPriorityRevenue;
    private Double tollPriorityFranchiseCost;
    private Double tollPriorityGrossMargin;
    private Long tollPriorityShipmentCount;
    private Double otherCost;
    private Double otherRevenue;
    private Double otherFranchiseCost;
    private Double otherGrossMargin;
    private Long otherShipmentCount;
    private Long totalShipmentCount;
    private Double avgMarginPerAwb;

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

    public String getSaleRepName() {
        return saleRepName;
    }

    public void setSaleRepName(String saleRepName) {
        this.saleRepName = saleRepName;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerRevenue() {
        return customerRevenue;
    }

    public void setCustomerRevenue(Double customerRevenue) {
        this.customerRevenue = customerRevenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(Double franchiseCost) {
        this.franchiseCost = franchiseCost;
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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerCostBaseCharge() {
        return customerCostBaseCharge;
    }

    public void setCustomerCostBaseCharge(Double customerCostBaseCharge) {
        this.customerCostBaseCharge = customerCostBaseCharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCostBaseCharge() {
        return carrierCostBaseCharge;
    }

    public void setCarrierCostBaseCharge(Double carrierCostBaseCharge) {
        this.carrierCostBaseCharge = carrierCostBaseCharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginOnBaseCharge() {
        return marginOnBaseCharge;
    }

    public void setMarginOnBaseCharge(Double marginOnBaseCharge) {
        this.marginOnBaseCharge = marginOnBaseCharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDhlCost() {
        return dhlCost;
    }

    public void setDhlCost(Double dhlCost) {
        this.dhlCost = dhlCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDhlRevenue() {
        return dhlRevenue;
    }

    public void setDhlRevenue(Double dhlRevenue) {
        this.dhlRevenue = dhlRevenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDhlFranchiseCost() {
        return dhlFranchiseCost;
    }

    public void setDhlFranchiseCost(Double dhlFranchiseCost) {
        this.dhlFranchiseCost = dhlFranchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDhlGrossMargin() {
        return dhlGrossMargin;
    }

    public void setDhlGrossMargin(Double dhlGrossMargin) {
        this.dhlGrossMargin = dhlGrossMargin;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getDhlShipmentCount() {
        return dhlShipmentCount;
    }

    public void setDhlShipmentCount(Long dhlShipmentCount) {
        this.dhlShipmentCount = dhlShipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTntDomCost() {
        return tntDomCost;
    }

    public void setTntDomCost(Double tntDomCost) {
        this.tntDomCost = tntDomCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTntDomRevenue() {
        return tntDomRevenue;
    }

    public void setTntDomRevenue(Double tntDomRevenue) {
        this.tntDomRevenue = tntDomRevenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTntDomFranchiseCost() {
        return tntDomFranchiseCost;
    }

    public void setTntDomFranchiseCost(Double tntDomFranchiseCost) {
        this.tntDomFranchiseCost = tntDomFranchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTntDomGrossMargin() {
        return tntDomGrossMargin;
    }

    public void setTntDomGrossMargin(Double tntDomGrossMargin) {
        this.tntDomGrossMargin = tntDomGrossMargin;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getTntDomShipmentCount() {
        return tntDomShipmentCount;
    }

    public void setTntDomShipmentCount(Long tntDomShipmentCount) {
        this.tntDomShipmentCount = tntDomShipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDhlDomCost() {
        return dhlDomCost;
    }

    public void setDhlDomCost(Double dhlDomCost) {
        this.dhlDomCost = dhlDomCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDhlDomRevenue() {
        return dhlDomRevenue;
    }

    public void setDhlDomRevenue(Double dhlDomRevenue) {
        this.dhlDomRevenue = dhlDomRevenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDhlDomFranchiseCost() {
        return dhlDomFranchiseCost;
    }

    public void setDhlDomFranchiseCost(Double dhlDomFranchiseCost) {
        this.dhlDomFranchiseCost = dhlDomFranchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDhlDomGrossMargin() {
        return dhlDomGrossMargin;
    }

    public void setDhlDomGrossMargin(Double dhlDomGrossMargin) {
        this.dhlDomGrossMargin = dhlDomGrossMargin;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getDhlDomShipmentCount() {
        return dhlDomShipmentCount;
    }

    public void setDhlDomShipmentCount(Long dhlDomShipmentCount) {
        this.dhlDomShipmentCount = dhlDomShipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTollPriorityCost() {
        return tollPriorityCost;
    }

    public void setTollPriorityCost(Double tollPriorityCost) {
        this.tollPriorityCost = tollPriorityCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTollPriorityRevenue() {
        return tollPriorityRevenue;
    }

    public void setTollPriorityRevenue(Double tollPriorityRevenue) {
        this.tollPriorityRevenue = tollPriorityRevenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTollPriorityFranchiseCost() {
        return tollPriorityFranchiseCost;
    }

    public void setTollPriorityFranchiseCost(Double tollPriorityFranchiseCost) {
        this.tollPriorityFranchiseCost = tollPriorityFranchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTollPriorityGrossMargin() {
        return tollPriorityGrossMargin;
    }

    public void setTollPriorityGrossMargin(Double tollPriorityGrossMargin) {
        this.tollPriorityGrossMargin = tollPriorityGrossMargin;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getTollPriorityShipmentCount() {
        return tollPriorityShipmentCount;
    }

    public void setTollPriorityShipmentCount(Long tollPriorityShipmentCount) {
        this.tollPriorityShipmentCount = tollPriorityShipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getOtherRevenue() {
        return otherRevenue;
    }

    public void setOtherRevenue(Double otherRevenue) {
        this.otherRevenue = otherRevenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getOtherFranchiseCost() {
        return otherFranchiseCost;
    }

    public void setOtherFranchiseCost(Double otherFranchiseCost) {
        this.otherFranchiseCost = otherFranchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getOtherGrossMargin() {
        return otherGrossMargin;
    }

    public void setOtherGrossMargin(Double otherGrossMargin) {
        this.otherGrossMargin = otherGrossMargin;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getOtherShipmentCount() {
        return otherShipmentCount;
    }

    public void setOtherShipmentCount(Long otherShipmentCount) {
        this.otherShipmentCount = otherShipmentCount;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getTotalShipmentCount() {
        return totalShipmentCount;
    }

    public void setTotalShipmentCount(Long totalShipmentCount) {
        this.totalShipmentCount = totalShipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAvgMarginPerAwb() {
        return avgMarginPerAwb;
    }

    public void setAvgMarginPerAwb(Double avgMarginPerAwb) {
        this.avgMarginPerAwb = avgMarginPerAwb;
    }

    @Override
    public String toString() {
        return "CustomerSummaryVo [customerCode=" + customerCode + ", customerName=" + customerName + ", saleRepName=" + saleRepName + ", customerRevenue=" + customerRevenue + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", grossMargin=" + grossMargin + ", grossMarginPct=" + grossMarginPct + ", customerCostBaseCharge=" + customerCostBaseCharge + ", carrierCostBaseCharge=" + carrierCostBaseCharge + ", marginOnBaseCharge=" + marginOnBaseCharge + ", dhlCost=" + dhlCost
                + ", dhlRevenue=" + dhlRevenue + ", dhlFranchiseCost=" + dhlFranchiseCost + ", dhlGrossMargin=" + dhlGrossMargin + ", dhlShipmentCount=" + dhlShipmentCount + ", tntDomCost=" + tntDomCost + ", tntDomRevenue=" + tntDomRevenue + ", tntDomFranchiseCost=" + tntDomFranchiseCost + ", tntDomGrossMargin=" + tntDomGrossMargin + ", tntDomShipmentCount=" + tntDomShipmentCount + ", dhlDomCost=" + dhlDomCost + ", dhlDomRevenue=" + dhlDomRevenue + ", dhlDomFranchiseCost=" + dhlDomFranchiseCost
                + ", dhlDomGrossMargin=" + dhlDomGrossMargin + ", dhlDomShipmentCount=" + dhlDomShipmentCount + ", tollPriorityCost=" + tollPriorityCost + ", tollPriorityRevenue=" + tollPriorityRevenue + ", tollPriorityFranchiseCost=" + tollPriorityFranchiseCost + ", tollPriorityGrossMargin=" + tollPriorityGrossMargin + ", tollPriorityShipmentCount=" + tollPriorityShipmentCount + ", otherCost=" + otherCost + ", otherRevenue=" + otherRevenue + ", otherFranchiseCost=" + otherFranchiseCost
                + ", otherGrossMargin=" + otherGrossMargin + ", otherShipmentCount=" + otherShipmentCount + ", totalShipmentCount=" + totalShipmentCount + ", avgMarginPerAwb=" + avgMarginPerAwb + "]";
    }
}

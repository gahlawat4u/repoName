package com.gms.xms.txndb.vo.franchisepayable.sc;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from FranchisePayableSCOverviewVo
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class FranchisePayableSCOverviewVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private Long setups;
    private Long activations;
    private Long printedInvoices;
    private Long emailInvoices;
    private Double customerCost;
    private Double customerMarginableCost;
    private Double franchiseCost;
    private Double franchiseCostTaxable;
    private Double franchiseCostNonTaxable;
    private Double franchiseGst;
    private Double franchiseTotal;
    private Double marginShared;
    private Double managementFee;
    private Double marketingFee;
    private Double carrierCredits;
    private Double carrierCreditsTaxable;
    private Double carrierCreditsNonTaxable;
    private Double carrierCreditsGst;
    private Double carrierCreditsTotal;
    private Double techFeeOnIntlShipments;
    private Double techFeeOnDomShipments;
    private Long intlShipmentCount;
    private Long domShipmentCount;
    private Double netReceivable;
    private Double gst;
    private Double totalReceivable;
    private Double managementFeeOnCreditRevenue;
    private Double managementFeeOnCreditProfitShared;
    private String franchiseCode;

    @Override
    public String toString() {
        return "FranchisePayableSCOverviewVo [rptTxnId=" + rptTxnId + ", setups=" + setups + ", activations=" + activations + ", printedInvoices=" + printedInvoices + ", emailInvoices=" + emailInvoices + ", customerCost=" + customerCost + ", customerMarginableCost=" + customerMarginableCost + ", franchiseCost=" + franchiseCost + ", franchiseCostTaxable=" + franchiseCostTaxable + ", franchiseCostNonTaxable=" + franchiseCostNonTaxable + ", franchiseGst=" + franchiseGst + ", franchiseTotal="
                + franchiseTotal + ", marginShared=" + marginShared + ", managementFee=" + managementFee + ", marketingFee=" + marketingFee + ", carrierCredits=" + carrierCredits + ", carrierCreditsTaxable=" + carrierCreditsTaxable + ", carrierCreditsNonTaxable=" + carrierCreditsNonTaxable + ", carrierCreditsGst=" + carrierCreditsGst + ", carrierCreditsTotal=" + carrierCreditsTotal + ", techFeeOnIntlShipments=" + techFeeOnIntlShipments + ", techFeeOnDomShipments=" + techFeeOnDomShipments
                + ", intlShipmentCount=" + intlShipmentCount + ", domShipmentCount=" + domShipmentCount + ", netReceivable=" + netReceivable + ", gst=" + gst + ", totalReceivable=" + totalReceivable + ", managementFeeOnCreditRevenue=" + managementFeeOnCreditRevenue + ", managementFeeOnCreditProfitShared=" + managementFeeOnCreditProfitShared + ", franchiseCode=" + franchiseCode + "]";
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getSetups() {
        return setups;
    }

    public void setSetups(Long setups) {
        this.setups = setups;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getActivations() {
        return activations;
    }

    public void setActivations(Long activations) {
        this.activations = activations;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getPrintedInvoices() {
        return printedInvoices;
    }

    public void setPrintedInvoices(Long printedInvoices) {
        this.printedInvoices = printedInvoices;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getEmailInvoices() {
        return emailInvoices;
    }

    public void setEmailInvoices(Long emailInvoices) {
        this.emailInvoices = emailInvoices;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(Double customerCost) {
        this.customerCost = customerCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerMarginableCost() {
        return customerMarginableCost;
    }

    public void setCustomerMarginableCost(Double customerMarginableCost) {
        this.customerMarginableCost = customerMarginableCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(Double franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCostTaxable() {
        return franchiseCostTaxable;
    }

    public void setFranchiseCostTaxable(Double franchiseCostTaxable) {
        this.franchiseCostTaxable = franchiseCostTaxable;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCostNonTaxable() {
        return franchiseCostNonTaxable;
    }

    public void setFranchiseCostNonTaxable(Double franchiseCostNonTaxable) {
        this.franchiseCostNonTaxable = franchiseCostNonTaxable;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseGst() {
        return franchiseGst;
    }

    public void setFranchiseGst(Double franchiseGst) {
        this.franchiseGst = franchiseGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseTotal() {
        return franchiseTotal;
    }

    public void setFranchiseTotal(Double franchiseTotal) {
        this.franchiseTotal = franchiseTotal;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginShared() {
        return marginShared;
    }

    public void setMarginShared(Double marginShared) {
        this.marginShared = marginShared;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarketingFee() {
        return marketingFee;
    }

    public void setMarketingFee(Double marketingFee) {
        this.marketingFee = marketingFee;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCredits() {
        return carrierCredits;
    }

    public void setCarrierCredits(Double carrierCredits) {
        this.carrierCredits = carrierCredits;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCreditsTaxable() {
        return carrierCreditsTaxable;
    }

    public void setCarrierCreditsTaxable(Double carrierCreditsTaxable) {
        this.carrierCreditsTaxable = carrierCreditsTaxable;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCreditsNonTaxable() {
        return carrierCreditsNonTaxable;
    }

    public void setCarrierCreditsNonTaxable(Double carrierCreditsNonTaxable) {
        this.carrierCreditsNonTaxable = carrierCreditsNonTaxable;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCreditsGst() {
        return carrierCreditsGst;
    }

    public void setCarrierCreditsGst(Double carrierCreditsGst) {
        this.carrierCreditsGst = carrierCreditsGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCreditsTotal() {
        return carrierCreditsTotal;
    }

    public void setCarrierCreditsTotal(Double carrierCreditsTotal) {
        this.carrierCreditsTotal = carrierCreditsTotal;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTechFeeOnIntlShipments() {
        return techFeeOnIntlShipments;
    }

    public void setTechFeeOnIntlShipments(Double techFeeOnIntlShipments) {
        this.techFeeOnIntlShipments = techFeeOnIntlShipments;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTechFeeOnDomShipments() {
        return techFeeOnDomShipments;
    }

    public void setTechFeeOnDomShipments(Double techFeeOnDomShipments) {
        this.techFeeOnDomShipments = techFeeOnDomShipments;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getIntlShipmentCount() {
        return intlShipmentCount;
    }

    public void setIntlShipmentCount(Long intlShipmentCount) {
        this.intlShipmentCount = intlShipmentCount;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getDomShipmentCount() {
        return domShipmentCount;
    }

    public void setDomShipmentCount(Long domShipmentCount) {
        this.domShipmentCount = domShipmentCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNetReceivable() {
        return netReceivable;
    }

    public void setNetReceivable(Double netReceivable) {
        this.netReceivable = netReceivable;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalReceivable() {
        return totalReceivable;
    }

    public void setTotalReceivable(Double totalReceivable) {
        this.totalReceivable = totalReceivable;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getManagementFeeOnCreditRevenue() {
        return managementFeeOnCreditRevenue;
    }

    public void setManagementFeeOnCreditRevenue(Double managementFeeOnCreditRevenue) {
        this.managementFeeOnCreditRevenue = managementFeeOnCreditRevenue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getManagementFeeOnCreditProfitShared() {
        return managementFeeOnCreditProfitShared;
    }

    public void setManagementFeeOnCreditProfitShared(Double managementFeeOnCreditProfitShared) {
        this.managementFeeOnCreditProfitShared = managementFeeOnCreditProfitShared;
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }
}

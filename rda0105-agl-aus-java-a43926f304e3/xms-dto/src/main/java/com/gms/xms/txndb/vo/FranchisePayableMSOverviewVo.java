package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

/**
 * Posted from FranchisePayableMSOverviewVo.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:24:48 PM
 */
public class FranchisePayableMSOverviewVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private long setups;
    private long activations;
    private long printedInvoices;
    private long emailInvoices;
    private BigDecimal marginShare;
    private BigDecimal day61MarginShare;
    private BigDecimal nonCentralizedMarginShare;
    private BigDecimal lateFee;
    private BigDecimal grossPayables;
    private BigDecimal carrierCredits;
    private BigDecimal repaidCarrierDeductions;
    private BigDecimal nonCentralCarrierCostExcGst;
    private BigDecimal nonCentralCarrierCostGst;
    private BigDecimal carrierCostDeduction;
    private BigDecimal techFees;
    private BigDecimal marketingFees;
    private BigDecimal netPayablesExcGst;
    private BigDecimal netPayablesGst;
    private BigDecimal totalPayables;

    @Override
    public String toString() {
        return "FranchisePayableMSOverviewVo [setups=" + setups + ", activations=" + activations + ", printedInvoices=" + printedInvoices + ", emailInvoices=" + emailInvoices + ", marginShare=" + marginShare + ", day61MarginShare=" + day61MarginShare + ", nonCentralizedMarginShare=" + nonCentralizedMarginShare + ", lateFee=" + lateFee + ", grossPayables=" + grossPayables + ", carrierCredits=" + carrierCredits + ", repaidCarrierDeductions=" + repaidCarrierDeductions + ", nonCentralCarrierCostExcGst="
                + nonCentralCarrierCostExcGst + ", nonCentralCarrierCostGst" + nonCentralCarrierCostGst + ", carrierCostDeduction=" + carrierCostDeduction + ", techFees=" + techFees + ", marketingFees=" + marketingFees + ", netPayablesExcGst=" + netPayablesExcGst + ", netPayablesGst=" + netPayablesGst + ", totalPayables=" + totalPayables + "]";
    }

    public long getSetups() {
        return setups;
    }

    public void setSetups(long setups) {
        this.setups = setups;
    }

    public long getActivations() {
        return activations;
    }

    public void setActivations(long activations) {
        this.activations = activations;
    }

    public long getPrintedInvoices() {
        return printedInvoices;
    }

    public void setPrintedInvoices(long printedInvoices) {
        this.printedInvoices = printedInvoices;
    }

    public long getEmailInvoices() {
        return emailInvoices;
    }

    public void setEmailInvoices(long emailInvoices) {
        this.emailInvoices = emailInvoices;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getMarginShare() {
        return marginShare;
    }

    public void setMarginShare(BigDecimal marginShare) {
        this.marginShare = marginShare;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonCentralizedMarginShare() {
        return nonCentralizedMarginShare;
    }

    public void setNonCentralizedMarginShare(BigDecimal nonCentralizedMarginShare) {
        this.nonCentralizedMarginShare = nonCentralizedMarginShare;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getGrossPayables() {
        return grossPayables;
    }

    public void setGrossPayables(BigDecimal grossPayables) {
        this.grossPayables = grossPayables;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCarrierCredits() {
        return carrierCredits;
    }

    public void setCarrierCredits(BigDecimal carrierCredits) {
        this.carrierCredits = carrierCredits;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonCentralCarrierCostExcGst() {
        return nonCentralCarrierCostExcGst;
    }

    public void setNonCentralCarrierCostExcGst(BigDecimal nonCentralCarrierCostExcGst) {
        this.nonCentralCarrierCostExcGst = nonCentralCarrierCostExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCarrierCostDeduction() {
        return carrierCostDeduction;
    }

    public void setCarrierCostDeduction(BigDecimal carrierCostDeduction) {
        this.carrierCostDeduction = carrierCostDeduction;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTechFees() {
        return techFees;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public void setTechFees(BigDecimal techFees) {
        this.techFees = techFees;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getMarketingFees() {
        return marketingFees;
    }

    public void setMarketingFees(BigDecimal marketingFees) {
        this.marketingFees = marketingFees;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNetPayablesExcGst() {
        return netPayablesExcGst;
    }

    public void setNetPayablesExcGst(BigDecimal netPayablesExcGst) {
        this.netPayablesExcGst = netPayablesExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNetPayablesGst() {
        return netPayablesGst;
    }

    public void setNetPayablesGst(BigDecimal netPayablesGst) {
        this.netPayablesGst = netPayablesGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalPayables() {
        return totalPayables;
    }

    public void setTotalPayables(BigDecimal totalPayables) {
        this.totalPayables = totalPayables;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getDay61MarginShare() {
        return day61MarginShare;
    }

    public void setDay61MarginShare(BigDecimal day61MarginShare) {
        this.day61MarginShare = day61MarginShare;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getRepaidCarrierDeductions() {
        return repaidCarrierDeductions;
    }

    public void setRepaidCarrierDeductions(BigDecimal repaidCarrierDeductions) {
        this.repaidCarrierDeductions = repaidCarrierDeductions;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonCentralCarrierCostGst() {
        return nonCentralCarrierCostGst;
    }

    public void setNonCentralCarrierCostGst(BigDecimal nonCentralCarrierCostGst) {
        this.nonCentralCarrierCostGst = nonCentralCarrierCostGst;
    }
}

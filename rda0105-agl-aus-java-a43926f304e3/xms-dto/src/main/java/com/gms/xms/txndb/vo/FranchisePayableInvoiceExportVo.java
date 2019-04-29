package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

/**
 * Posted from FranchisePayableInvoiceVo
 * <p>
 * Author HungNT Date Jun 11, 2015
 */
public class FranchisePayableInvoiceExportVo extends BaseVo {
    private static final long serialVersionUID = 5927363357750429704L;

    private BigDecimal marginShareExcGst;
    private BigDecimal marginShareGst;
    private BigDecimal day61MarginShareExcGst;
    private BigDecimal day61MarginShareGst;
    private BigDecimal nonCentralizedMarginShareExcGst;
    private BigDecimal nonCentralizedMarginShareGst;
    private BigDecimal lateFeeExcGst;
    private BigDecimal lateFeeGst;
    private BigDecimal grossPayablesExcGst;
    private BigDecimal grossPayablesGst;
    private BigDecimal carrierCreditExcGst;
    private BigDecimal carrierCreditGst;
    private BigDecimal repaidCarrierDeductionsExcGst;
    private BigDecimal repaidCarrierDeductionsGst;
    private BigDecimal nonCentralCarrierCostExcGst;
    private BigDecimal nonCentralCarrierCostGst;
    private BigDecimal totalOtherPayablesExcGst;
    private BigDecimal totalOtherPayablesGst;
    private BigDecimal carrierCostDeductionExcGst;
    private BigDecimal carrierCostDeductionGst;
    private BigDecimal techFeesExcGst;
    private BigDecimal techFeesGst;
    private BigDecimal marketingFeesExcGst;
    private BigDecimal marketingFeesGst;
    private BigDecimal totalCostsExcGst;
    private BigDecimal totalCostsGst;

    private BigDecimal marginShare;
    private BigDecimal day61MarginShare;
    private BigDecimal nonCentralizedMarginShare;
    private BigDecimal lateFee;
    private BigDecimal grossPayables;
    private BigDecimal carrierCredit;
    private BigDecimal repaidCarrierDeductions;
    private BigDecimal nonCentralCarrierCost;
    private BigDecimal totalOtherPayables;
    private BigDecimal carrierCostDeduction;
    private BigDecimal techFees;
    private BigDecimal marketingFees;
    private BigDecimal totalCosts;

    private BigDecimal netPayablesExcGst;
    private BigDecimal netPayablesGst;
    private BigDecimal totalNetPayable;
    private BigDecimal totalPayableGst;
    private BigDecimal totalPayableExcGst;
    private BigDecimal totalPayable;

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getMarginShareExcGst() {
        return marginShareExcGst;
    }

    public void setMarginShareExcGst(BigDecimal marginShareExcGst) {
        this.marginShareExcGst = marginShareExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getMarginShareGst() {
        return marginShareGst;
    }

    public void setMarginShareGst(BigDecimal marginShareGst) {
        this.marginShareGst = marginShareGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getDay61MarginShareExcGst() {
        return day61MarginShareExcGst;
    }

    public void setDay61MarginShareExcGst(BigDecimal day61MarginShareExcGst) {
        this.day61MarginShareExcGst = day61MarginShareExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getDay61MarginShareGst() {
        return day61MarginShareGst;
    }

    public void setDay61MarginShareGst(BigDecimal day61MarginShareGst) {
        this.day61MarginShareGst = day61MarginShareGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonCentralizedMarginShareExcGst() {
        return nonCentralizedMarginShareExcGst;
    }

    public void setNonCentralizedMarginShareExcGst(BigDecimal nonCentralizedMarginShareExcGst) {
        this.nonCentralizedMarginShareExcGst = nonCentralizedMarginShareExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonCentralizedMarginShareGst() {
        return nonCentralizedMarginShareGst;
    }

    public void setNonCentralizedMarginShareGst(BigDecimal nonCentralizedMarginShareGst) {
        this.nonCentralizedMarginShareGst = nonCentralizedMarginShareGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getLateFeeExcGst() {
        return lateFeeExcGst;
    }

    public void setLateFeeExcGst(BigDecimal lateFeeExcGst) {
        this.lateFeeExcGst = lateFeeExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getLateFeeGst() {
        return lateFeeGst;
    }

    public void setLateFeeGst(BigDecimal lateFeeGst) {
        this.lateFeeGst = lateFeeGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getGrossPayablesExcGst() {
        return grossPayablesExcGst;
    }

    public void setGrossPayablesExcGst(BigDecimal grossPayablesExcGst) {
        this.grossPayablesExcGst = grossPayablesExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getGrossPayablesGst() {
        return grossPayablesGst;
    }

    public void setGrossPayablesGst(BigDecimal grossPayablesGst) {
        this.grossPayablesGst = grossPayablesGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCarrierCreditExcGst() {
        return carrierCreditExcGst;
    }

    public void setCarrierCreditExcGst(BigDecimal carrierCreditExcGst) {
        this.carrierCreditExcGst = carrierCreditExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCarrierCreditGst() {
        return carrierCreditGst;
    }

    public void setCarrierCreditGst(BigDecimal carrierCreditGst) {
        this.carrierCreditGst = carrierCreditGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getRepaidCarrierDeductionsExcGst() {
        return repaidCarrierDeductionsExcGst;
    }

    public void setRepaidCarrierDeductionsExcGst(BigDecimal repaidCarrierDeductionsExcGst) {
        this.repaidCarrierDeductionsExcGst = repaidCarrierDeductionsExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getRepaidCarrierDeductionsGst() {
        return repaidCarrierDeductionsGst;
    }

    public void setRepaidCarrierDeductionsGst(BigDecimal repaidCarrierDeductionsGst) {
        this.repaidCarrierDeductionsGst = repaidCarrierDeductionsGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonCentralCarrierCostExcGst() {
        return nonCentralCarrierCostExcGst;
    }

    public void setNonCentralCarrierCostExcGst(BigDecimal nonCentralCarrierCostExcGst) {
        this.nonCentralCarrierCostExcGst = nonCentralCarrierCostExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonCentralCarrierCostGst() {
        return nonCentralCarrierCostGst;
    }

    public void setNonCentralCarrierCostGst(BigDecimal nonCentralCarrierCostGst) {
        this.nonCentralCarrierCostGst = nonCentralCarrierCostGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalOtherPayablesExcGst() {
        return totalOtherPayablesExcGst;
    }

    public void setTotalOtherPayablesExcGst(BigDecimal totalOtherPayablesExcGst) {
        this.totalOtherPayablesExcGst = totalOtherPayablesExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalOtherPayablesGst() {
        return totalOtherPayablesGst;
    }

    public void setTotalOtherPayablesGst(BigDecimal totalOtherPayablesGst) {
        this.totalOtherPayablesGst = totalOtherPayablesGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCarrierCostDeductionExcGst() {
        return carrierCostDeductionExcGst;
    }

    public void setCarrierCostDeductionExcGst(BigDecimal carrierCostDeductionExcGst) {
        this.carrierCostDeductionExcGst = carrierCostDeductionExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCarrierCostDeductionGst() {
        return carrierCostDeductionGst;
    }

    public void setCarrierCostDeductionGst(BigDecimal carrierCostDeductionGst) {
        this.carrierCostDeductionGst = carrierCostDeductionGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTechFeesExcGst() {
        return techFeesExcGst;
    }

    public void setTechFeesExcGst(BigDecimal techFeesExcGst) {
        this.techFeesExcGst = techFeesExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTechFeesGst() {
        return techFeesGst;
    }

    public void setTechFeesGst(BigDecimal techFeesGst) {
        this.techFeesGst = techFeesGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getMarketingFeesExcGst() {
        return marketingFeesExcGst;
    }

    public void setMarketingFeesExcGst(BigDecimal marketingFeesExcGst) {
        this.marketingFeesExcGst = marketingFeesExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getMarketingFeesGst() {
        return marketingFeesGst;
    }

    public void setMarketingFeesGst(BigDecimal marketingFeesGst) {
        this.marketingFeesGst = marketingFeesGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalCostsExcGst() {
        return totalCostsExcGst;
    }

    public void setTotalCostsExcGst(BigDecimal totalCostsExcGst) {
        this.totalCostsExcGst = totalCostsExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalCostsGst() {
        return totalCostsGst;
    }

    public void setTotalCostsGst(BigDecimal totalCostsGst) {
        this.totalCostsGst = totalCostsGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getMarginShare() {
        BigDecimal marginShareExcGst = this.marginShareExcGst != null ? this.marginShareExcGst : BigDecimal.ZERO;
        BigDecimal marginShareGst = this.getMarginShareGst() != null ? this.getMarginShareGst() : BigDecimal.ZERO;
        return marginShareExcGst.add(marginShareGst);
    }

    public void setMarginShare(BigDecimal marginShare) {
        this.marginShare = marginShare;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getDay61MarginShare() {
        BigDecimal day61MarginShareExcGst = this.day61MarginShareExcGst != null ? this.day61MarginShareExcGst : BigDecimal.ZERO;
        BigDecimal day61MarginShareGst = this.getDay61MarginShareGst() != null ? this.getDay61MarginShareGst() : BigDecimal.ZERO;
        return day61MarginShareExcGst.add(day61MarginShareGst);
    }

    public void setDay61MarginShare(BigDecimal day61MarginShare) {
        this.day61MarginShare = day61MarginShare;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonCentralizedMarginShare() {
        BigDecimal nonCentralizedMarginShareExcGst = this.nonCentralizedMarginShareExcGst != null ? this.nonCentralizedMarginShareExcGst : BigDecimal.ZERO;
        BigDecimal nonCentralizedMarginShareGst = this.getNonCentralizedMarginShareGst() != null ? this.getNonCentralizedMarginShareGst() : BigDecimal.ZERO;
        return nonCentralizedMarginShareExcGst.add(nonCentralizedMarginShareGst);
    }

    public void setNonCentralizedMarginShare(BigDecimal nonCentralizedMarginShare) {
        this.nonCentralizedMarginShare = nonCentralizedMarginShare;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getLateFee() {
        BigDecimal lateFeeExcGst = this.lateFeeExcGst != null ? this.lateFeeExcGst : BigDecimal.ZERO;
        BigDecimal lateFeeGst = this.lateFeeGst != null ? this.lateFeeGst : BigDecimal.ZERO;
        return lateFeeExcGst.add(lateFeeGst);
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getGrossPayables() {
        BigDecimal grossPayablesExcGst = this.grossPayablesExcGst != null ? this.grossPayablesExcGst : BigDecimal.ZERO;
        BigDecimal grossPayablesGst = this.getGrossPayablesGst() != null ? this.getGrossPayablesGst() : BigDecimal.ZERO;
        return grossPayablesExcGst.add(grossPayablesGst);
    }

    public void setGrossPayables(BigDecimal grossPayables) {
        this.grossPayables = grossPayables;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCarrierCredit() {
        BigDecimal carrierCreditExcGst = this.carrierCreditExcGst != null ? this.carrierCreditExcGst : BigDecimal.ZERO;
        BigDecimal carrierCreditGst = this.getCarrierCreditGst() != null ? this.getCarrierCreditGst() : BigDecimal.ZERO;
        return carrierCreditExcGst.add(carrierCreditGst);
    }

    public void setCarrierCredit(BigDecimal carrierCredit) {
        this.carrierCredit = carrierCredit;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getRepaidCarrierDeductions() {
        return this.repaidCarrierDeductions;
    }

    public void setRepaidCarrierDeductions(BigDecimal repaidCarrierDeductions) {
        this.repaidCarrierDeductions = repaidCarrierDeductions;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonCentralCarrierCost() {
        BigDecimal nonCentralCarrierCostExcGst = this.nonCentralCarrierCostExcGst != null ? this.nonCentralCarrierCostExcGst : BigDecimal.ZERO;
        BigDecimal nonCentralCarrierCostGst = this.getNonCentralCarrierCostGst() != null ? this.getNonCentralCarrierCostGst() : BigDecimal.ZERO;
        return nonCentralCarrierCostExcGst.add(nonCentralCarrierCostGst);
    }

    public void setNonCentralCarrierCost(BigDecimal nonCentralCarrierCost) {
        this.nonCentralCarrierCost = nonCentralCarrierCost;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalOtherPayables() {
        BigDecimal totalOtherPayablesExcGst = this.totalOtherPayablesExcGst != null ? this.totalOtherPayablesExcGst : BigDecimal.ZERO;
        BigDecimal totalOtherPayablesGst = this.getTotalOtherPayablesGst() != null ? this.getTotalOtherPayablesGst() : BigDecimal.ZERO;
        return totalOtherPayablesExcGst.add(totalOtherPayablesGst);
    }

    public void setTotalOtherPayables(BigDecimal totalOtherPayables) {
        this.totalOtherPayables = totalOtherPayables;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCarrierCostDeduction() {
        return carrierCostDeductionExcGst.add(carrierCostDeductionGst);
    }

    public void setCarrierCostDeduction(BigDecimal carrierCostDeduction) {
        this.carrierCostDeduction = carrierCostDeduction;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTechFees() {
        BigDecimal techFeesExcGst = this.techFeesExcGst != null ? this.techFeesExcGst : BigDecimal.ZERO;
        BigDecimal techFeesGst = this.getTechFeesGst() != null ? this.getTechFeesGst() : BigDecimal.ZERO;
        return techFeesExcGst.add(techFeesGst);
    }

    public void setTechFees(BigDecimal techFees) {
        this.techFees = techFees;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getMarketingFees() {
        BigDecimal marketingFeesExcGst = this.marketingFeesExcGst != null ? this.marketingFeesExcGst : BigDecimal.ZERO;
        BigDecimal marketingFeesGst = this.getMarketingFeesGst() != null ? this.getMarketingFeesGst() : BigDecimal.ZERO;
        return marketingFeesExcGst.add(marketingFeesGst);
    }

    public void setMarketingFees(BigDecimal marketingFees) {
        this.marketingFees = marketingFees;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalCosts() {
        BigDecimal totalCostsExcGst = this.totalCostsExcGst != null ? this.totalCostsExcGst : BigDecimal.ZERO;
        BigDecimal totalCostsGst = this.getTotalCostsGst() != null ? this.getTotalCostsGst() : BigDecimal.ZERO;
        return totalCostsExcGst.add(totalCostsGst);
    }

    public void setTotalCosts(BigDecimal totalCosts) {
        this.totalCosts = totalCosts;
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
    public BigDecimal getTotalNetPayable() {
        return totalNetPayable;
    }

    public void setTotalNetPayable(BigDecimal totalNetPayable) {
        this.totalNetPayable = totalNetPayable;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalPayableGst() {
        return totalPayableGst;
    }

    public void setTotalPayableGst(BigDecimal totalPayableGst) {
        this.totalPayableGst = totalPayableGst;
    }
    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalPayableExcGst() {
        return totalPayableExcGst;
    }

    public void setTotalPayableExcGst(BigDecimal totalPayableExcGst) {
        this.totalPayableExcGst = totalPayableExcGst;
    }
    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalPayable() {
        return totalPayable;
    }

    public void setTotalPayable(BigDecimal totalPayable) {
        this.totalPayable = totalPayable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrierCostDeduction == null) ? 0 : carrierCostDeduction.hashCode());
        result = prime * result + ((carrierCostDeductionExcGst == null) ? 0 : carrierCostDeductionExcGst.hashCode());
        result = prime * result + ((carrierCostDeductionGst == null) ? 0 : carrierCostDeductionGst.hashCode());
        result = prime * result + ((carrierCredit == null) ? 0 : carrierCredit.hashCode());
        result = prime * result + ((carrierCreditExcGst == null) ? 0 : carrierCreditExcGst.hashCode());
        result = prime * result + ((carrierCreditGst == null) ? 0 : carrierCreditGst.hashCode());
        result = prime * result + ((day61MarginShare == null) ? 0 : day61MarginShare.hashCode());
        result = prime * result + ((day61MarginShareExcGst == null) ? 0 : day61MarginShareExcGst.hashCode());
        result = prime * result + ((day61MarginShareGst == null) ? 0 : day61MarginShareGst.hashCode());
        result = prime * result + ((grossPayables == null) ? 0 : grossPayables.hashCode());
        result = prime * result + ((grossPayablesExcGst == null) ? 0 : grossPayablesExcGst.hashCode());
        result = prime * result + ((grossPayablesGst == null) ? 0 : grossPayablesGst.hashCode());
        result = prime * result + ((netPayablesGst == null) ? 0 : netPayablesGst.hashCode());
        result = prime * result + ((lateFee == null) ? 0 : lateFee.hashCode());
        result = prime * result + ((lateFeeExcGst == null) ? 0 : lateFeeExcGst.hashCode());
        result = prime * result + ((lateFeeGst == null) ? 0 : lateFeeGst.hashCode());
        result = prime * result + ((marginShare == null) ? 0 : marginShare.hashCode());
        result = prime * result + ((marginShareExcGst == null) ? 0 : marginShareExcGst.hashCode());
        result = prime * result + ((marginShareGst == null) ? 0 : marginShareGst.hashCode());
        result = prime * result + ((marketingFees == null) ? 0 : marketingFees.hashCode());
        result = prime * result + ((marketingFeesExcGst == null) ? 0 : marketingFeesExcGst.hashCode());
        result = prime * result + ((marketingFeesGst == null) ? 0 : marketingFeesGst.hashCode());
        result = prime * result + ((netPayablesExcGst == null) ? 0 : netPayablesExcGst.hashCode());
        result = prime * result + ((nonCentralCarrierCost == null) ? 0 : nonCentralCarrierCost.hashCode());
        result = prime * result + ((nonCentralCarrierCostExcGst == null) ? 0 : nonCentralCarrierCostExcGst.hashCode());
        result = prime * result + ((nonCentralCarrierCostGst == null) ? 0 : nonCentralCarrierCostGst.hashCode());
        result = prime * result + ((nonCentralizedMarginShare == null) ? 0 : nonCentralizedMarginShare.hashCode());
        result = prime * result + ((nonCentralizedMarginShareExcGst == null) ? 0 : nonCentralizedMarginShareExcGst.hashCode());
        result = prime * result + ((nonCentralizedMarginShareGst == null) ? 0 : nonCentralizedMarginShareGst.hashCode());
        result = prime * result + ((repaidCarrierDeductions == null) ? 0 : repaidCarrierDeductions.hashCode());
        result = prime * result + ((repaidCarrierDeductionsExcGst == null) ? 0 : repaidCarrierDeductionsExcGst.hashCode());
        result = prime * result + ((repaidCarrierDeductionsGst == null) ? 0 : repaidCarrierDeductionsGst.hashCode());
        result = prime * result + ((techFees == null) ? 0 : techFees.hashCode());
        result = prime * result + ((techFeesExcGst == null) ? 0 : techFeesExcGst.hashCode());
        result = prime * result + ((techFeesGst == null) ? 0 : techFeesGst.hashCode());
        result = prime * result + ((totalCosts == null) ? 0 : totalCosts.hashCode());
        result = prime * result + ((totalCostsExcGst == null) ? 0 : totalCostsExcGst.hashCode());
        result = prime * result + ((totalCostsGst == null) ? 0 : totalCostsGst.hashCode());
        result = prime * result + ((totalOtherPayables == null) ? 0 : totalOtherPayables.hashCode());
        result = prime * result + ((totalOtherPayablesExcGst == null) ? 0 : totalOtherPayablesExcGst.hashCode());
        result = prime * result + ((totalOtherPayablesGst == null) ? 0 : totalOtherPayablesGst.hashCode());
        result = prime * result + ((totalNetPayable == null) ? 0 : totalNetPayable.hashCode());
        result = prime * result + ((totalPayableGst == null) ? 0 : totalPayableGst.hashCode());
        result = prime * result + ((totalPayableExcGst == null) ? 0 : totalPayableExcGst.hashCode());
        result = prime * result + ((totalPayable == null) ? 0 : totalPayable.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FranchisePayableInvoiceExportVo other = (FranchisePayableInvoiceExportVo) obj;
        if (carrierCostDeduction == null) {
            if (other.carrierCostDeduction != null)
                return false;
        } else if (!carrierCostDeduction.equals(other.carrierCostDeduction))
            return false;
        if (carrierCostDeductionExcGst == null) {
            if (other.carrierCostDeductionExcGst != null)
                return false;
        } else if (!carrierCostDeductionExcGst.equals(other.carrierCostDeductionExcGst))
            return false;
        if (carrierCostDeductionGst == null) {
            if (other.carrierCostDeductionGst != null)
                return false;
        } else if (!carrierCostDeductionGst.equals(other.carrierCostDeductionGst))
            return false;
        if (carrierCredit == null) {
            if (other.carrierCredit != null)
                return false;
        } else if (!carrierCredit.equals(other.carrierCredit))
            return false;
        if (carrierCreditExcGst == null) {
            if (other.carrierCreditExcGst != null)
                return false;
        } else if (!carrierCreditExcGst.equals(other.carrierCreditExcGst))
            return false;
        if (carrierCreditGst == null) {
            if (other.carrierCreditGst != null)
                return false;
        } else if (!carrierCreditGst.equals(other.carrierCreditGst))
            return false;
        if (day61MarginShare == null) {
            if (other.day61MarginShare != null)
                return false;
        } else if (!day61MarginShare.equals(other.day61MarginShare))
            return false;
        if (day61MarginShareExcGst == null) {
            if (other.day61MarginShareExcGst != null)
                return false;
        } else if (!day61MarginShareExcGst.equals(other.day61MarginShareExcGst))
            return false;
        if (day61MarginShareGst == null) {
            if (other.day61MarginShareGst != null)
                return false;
        } else if (!day61MarginShareGst.equals(other.day61MarginShareGst))
            return false;
        if (grossPayables == null) {
            if (other.grossPayables != null)
                return false;
        } else if (!grossPayables.equals(other.grossPayables))
            return false;
        if (grossPayablesExcGst == null) {
            if (other.grossPayablesExcGst != null)
                return false;
        } else if (!grossPayablesExcGst.equals(other.grossPayablesExcGst))
            return false;
        if (grossPayablesGst == null) {
            if (other.grossPayablesGst != null)
                return false;
        } else if (!grossPayablesGst.equals(other.grossPayablesGst))
            return false;
        if (netPayablesGst == null) {
            if (other.netPayablesGst != null)
                return false;
        } else if (!netPayablesGst.equals(other.netPayablesGst))
            return false;
        if (lateFee == null) {
            if (other.lateFee != null)
                return false;
        } else if (!lateFee.equals(other.lateFee))
            return false;
        if (lateFeeExcGst == null) {
            if (other.lateFeeExcGst != null)
                return false;
        } else if (!lateFeeExcGst.equals(other.lateFeeExcGst))
            return false;
        if (lateFeeGst == null) {
            if (other.lateFeeGst != null)
                return false;
        } else if (!lateFeeGst.equals(other.lateFeeGst))
            return false;
        if (marginShare == null) {
            if (other.marginShare != null)
                return false;
        } else if (!marginShare.equals(other.marginShare))
            return false;
        if (marginShareExcGst == null) {
            if (other.marginShareExcGst != null)
                return false;
        } else if (!marginShareExcGst.equals(other.marginShareExcGst))
            return false;
        if (marginShareGst == null) {
            if (other.marginShareGst != null)
                return false;
        } else if (!marginShareGst.equals(other.marginShareGst))
            return false;
        if (marketingFees == null) {
            if (other.marketingFees != null)
                return false;
        } else if (!marketingFees.equals(other.marketingFees))
            return false;
        if (marketingFeesExcGst == null) {
            if (other.marketingFeesExcGst != null)
                return false;
        } else if (!marketingFeesExcGst.equals(other.marketingFeesExcGst))
            return false;
        if (marketingFeesGst == null) {
            if (other.marketingFeesGst != null)
                return false;
        } else if (!marketingFeesGst.equals(other.marketingFeesGst))
            return false;
        if (netPayablesExcGst == null) {
            if (other.netPayablesExcGst != null)
                return false;
        } else if (!netPayablesExcGst.equals(other.netPayablesExcGst))
            return false;
        if (nonCentralCarrierCost == null) {
            if (other.nonCentralCarrierCost != null)
                return false;
        } else if (!nonCentralCarrierCost.equals(other.nonCentralCarrierCost))
            return false;
        if (nonCentralCarrierCostExcGst == null) {
            if (other.nonCentralCarrierCostExcGst != null)
                return false;
        } else if (!nonCentralCarrierCostExcGst.equals(other.nonCentralCarrierCostExcGst))
            return false;
        if (nonCentralCarrierCostGst == null) {
            if (other.nonCentralCarrierCostGst != null)
                return false;
        } else if (!nonCentralCarrierCostGst.equals(other.nonCentralCarrierCostGst))
            return false;
        if (nonCentralizedMarginShare == null) {
            if (other.nonCentralizedMarginShare != null)
                return false;
        } else if (!nonCentralizedMarginShare.equals(other.nonCentralizedMarginShare))
            return false;
        if (nonCentralizedMarginShareExcGst == null) {
            if (other.nonCentralizedMarginShareExcGst != null)
                return false;
        } else if (!nonCentralizedMarginShareExcGst.equals(other.nonCentralizedMarginShareExcGst))
            return false;
        if (nonCentralizedMarginShareGst == null) {
            if (other.nonCentralizedMarginShareGst != null)
                return false;
        } else if (!nonCentralizedMarginShareGst.equals(other.nonCentralizedMarginShareGst))
            return false;
        if (repaidCarrierDeductions == null) {
            if (other.repaidCarrierDeductions != null)
                return false;
        } else if (!repaidCarrierDeductions.equals(other.repaidCarrierDeductions))
            return false;
        if (repaidCarrierDeductionsExcGst == null) {
            if (other.repaidCarrierDeductionsExcGst != null)
                return false;
        } else if (!repaidCarrierDeductionsExcGst.equals(other.repaidCarrierDeductionsExcGst))
            return false;
        if (repaidCarrierDeductionsGst == null) {
            if (other.repaidCarrierDeductionsGst != null)
                return false;
        } else if (!repaidCarrierDeductionsGst.equals(other.repaidCarrierDeductionsGst))
            return false;
        if (techFees == null) {
            if (other.techFees != null)
                return false;
        } else if (!techFees.equals(other.techFees))
            return false;
        if (techFeesExcGst == null) {
            if (other.techFeesExcGst != null)
                return false;
        } else if (!techFeesExcGst.equals(other.techFeesExcGst))
            return false;
        if (techFeesGst == null) {
            if (other.techFeesGst != null)
                return false;
        } else if (!techFeesGst.equals(other.techFeesGst))
            return false;
        if (totalCosts == null) {
            if (other.totalCosts != null)
                return false;
        } else if (!totalCosts.equals(other.totalCosts))
            return false;
        if (totalCostsExcGst == null) {
            if (other.totalCostsExcGst != null)
                return false;
        } else if (!totalCostsExcGst.equals(other.totalCostsExcGst))
            return false;
        if (totalCostsGst == null) {
            if (other.totalCostsGst != null)
                return false;
        } else if (!totalCostsGst.equals(other.totalCostsGst))
            return false;
        if (totalOtherPayables == null) {
            if (other.totalOtherPayables != null)
                return false;
        } else if (!totalOtherPayables.equals(other.totalOtherPayables))
            return false;
        if (totalOtherPayablesExcGst == null) {
            if (other.totalOtherPayablesExcGst != null)
                return false;
        } else if (!totalOtherPayablesExcGst.equals(other.totalOtherPayablesExcGst))
            return false;
        if (totalOtherPayablesGst == null) {
            if (other.totalOtherPayablesGst != null)
                return false;
        } else if (!totalOtherPayablesGst.equals(other.totalOtherPayablesGst))
            return false;
        if (totalNetPayable == null) {
            if (other.totalNetPayable != null)
                return false;
        } else if (!totalNetPayable.equals(other.totalNetPayable))
            return false;
        if (totalPayableGst == null) {
            if (other.totalPayableGst != null)
                return false;
        } else if (!totalPayableGst.equals(other.totalPayableGst))
            return false;
        if (totalPayableExcGst == null) {
            if (other.totalPayableExcGst != null)
                return false;
        } else if (!totalPayableExcGst.equals(other.totalPayableExcGst))
            return false;
        if (totalPayable == null) {
            if (other.totalPayable != null)
                return false;
        } else if (!totalPayable.equals(other.totalPayable))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FranchisePayableInvoiceExportVo [marginShareExcGst=" + marginShareExcGst + ", marginShareGst=" + marginShareGst + ", day61MarginShareExcGst=" + day61MarginShareExcGst + ", day61MarginShareGst=" + day61MarginShareGst + ", nonCentralizedMarginShareExcGst=" + nonCentralizedMarginShareExcGst + ", nonCentralizedMarginShareGst=" + nonCentralizedMarginShareGst + ", lateFeeExcGst=" + lateFeeExcGst + ", lateFeeGst=" + lateFeeGst + ", grossPayablesExcGst=" + grossPayablesExcGst
                + ", grossPayablesGst=" + grossPayablesGst + ", carrierCreditExcGst=" + carrierCreditExcGst + ", carrierCreditGst=" + carrierCreditGst + ", repaidCarrierDeductionsExcGst=" + repaidCarrierDeductionsExcGst + ", repaidCarrierDeductionsGst=" + repaidCarrierDeductionsGst + ", nonCentralCarrierCostExcGst=" + nonCentralCarrierCostExcGst + ", nonCentralCarrierCostGst=" + nonCentralCarrierCostGst + ", totalOtherPayablesExcGst=" + totalOtherPayablesExcGst + ", totalOtherPayablesGst="
                + totalOtherPayablesGst + ", carrierCostDeductionExcGst=" + carrierCostDeductionExcGst + ", carrierCostDeductionGst=" + carrierCostDeductionGst + ", techFeesExcGst=" + techFeesExcGst + ", techFeesGst=" + techFeesGst + ", marketingFeesExcGst=" + marketingFeesExcGst + ", marketingFeesGst=" + marketingFeesGst + ", totalCostsExcGst=" + totalCostsExcGst + ", totalCostsGst=" + totalCostsGst + ", marginShare=" + marginShare + ", day61MarginShare=" + day61MarginShare
                + ", nonCentralizedMarginShare=" + nonCentralizedMarginShare + ", lateFee=" + lateFee + ", grossPayables=" + grossPayables + ", carrierCredit=" + carrierCredit + ", repaidCarrierDeductions=" + repaidCarrierDeductions + ", nonCentralCarrierCost=" + nonCentralCarrierCost + ", totalOtherPayables=" + totalOtherPayables + ", carrierCostDeduction=" + carrierCostDeduction + ", techFees=" + techFees + ", marketingFees=" + marketingFees + ", totalCosts=" + totalCosts + ", netPayablesExcGst="
                + netPayablesExcGst + ", netPayablesGst=" + netPayablesGst + ", totalNetPayable=" + totalNetPayable + ", totalPayableGst" + totalPayableGst + ", totalPayableExcGst" + totalPayableExcGst + ", totalPayable" + totalPayable +"]";
    }
}

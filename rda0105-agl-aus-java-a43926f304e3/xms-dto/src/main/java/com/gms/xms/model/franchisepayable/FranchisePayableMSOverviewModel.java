package com.gms.xms.model.franchisepayable;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableMSOverviewModel.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:17:13 PM
 */
public class FranchisePayableMSOverviewModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String setups;
    private String activations;
    private String printedInvoices;
    private String emailInvoices;
    private String marginShare;
    private String day61MarginShare;
    private String nonCentralizedMarginShare;
    private String lateFee;
    private String grossPayables;
    private String carrierCredits;
    private String repaidCarrierDeductions;
    private String nonCentralCarrierCostExcGst;
    private String nonCentralCarrierCostGst;
    private String carrierCostDeduction;
    private String techFees;
    private String marketingFees;
    private String netPayablesExcGst;
    private String netPayablesGst;
    private String totalPayables;

    @Override
    public String toString() {
        return "FranchisePayableMSOverviewModel [setups=" + setups + ", activations=" + activations + ", printedInvoices=" + printedInvoices + ", emailInvoices=" + emailInvoices + ", marginShare=" + marginShare + ", day61MarginShare=" + day61MarginShare + ", nonCentralizedMarginShare=" + nonCentralizedMarginShare + ", lateFee=" + lateFee + ", grossPayables=" + grossPayables + ", carrierCredits=" + carrierCredits + ", repaidCarrierDeductions=" + repaidCarrierDeductions
                + ", nonCentralCarrierCostExcGst=" + nonCentralCarrierCostExcGst + ", nonCentralCarrierCostGst" + nonCentralCarrierCostGst + ", carrierCostDeduction=" + carrierCostDeduction + ", techFees=" + techFees + ", marketingFees=" + marketingFees + ", netPayablesExcGst=" + netPayablesExcGst + ", netPayablesGst=" + netPayablesGst + ", totalPayables=" + totalPayables + "]";
    }

    public String getSetups() {
        return setups;
    }

    public void setSetups(String setups) {
        this.setups = setups;
    }

    public String getActivations() {
        return activations;
    }

    public void setActivations(String activations) {
        this.activations = activations;
    }

    public String getPrintedInvoices() {
        return printedInvoices;
    }

    public void setPrintedInvoices(String printedInvoices) {
        this.printedInvoices = printedInvoices;
    }

    public String getEmailInvoices() {
        return emailInvoices;
    }

    public void setEmailInvoices(String emailInvoices) {
        this.emailInvoices = emailInvoices;
    }

    public String getMarginShare() {
        return marginShare;
    }

    public void setMarginShare(String marginShare) {
        this.marginShare = marginShare;
    }

    public String getNonCentralizedMarginShare() {
        return nonCentralizedMarginShare;
    }

    public void setNonCentralizedMarginShare(String nonCentralizedMarginShare) {
        this.nonCentralizedMarginShare = nonCentralizedMarginShare;
    }

    public String getLateFee() {
        return lateFee;
    }

    public void setLateFee(String lateFee) {
        this.lateFee = lateFee;
    }

    public String getGrossPayables() {
        return grossPayables;
    }

    public void setGrossPayables(String grossPayables) {
        this.grossPayables = grossPayables;
    }

    public String getCarrierCredits() {
        return carrierCredits;
    }

    public void setCarrierCredits(String carrierCredits) {
        this.carrierCredits = carrierCredits;
    }

    public String getNonCentralCarrierCostExcGst() {
        return nonCentralCarrierCostExcGst;
    }

    public void setNonCentralCarrierCostExcGst(String nonCentralCarrierCostExcGst) {
        this.nonCentralCarrierCostExcGst = nonCentralCarrierCostExcGst;
    }

    public String getCarrierCostDeduction() {
        return carrierCostDeduction;
    }

    public void setCarrierCostDeduction(String carrierCostDeduction) {
        this.carrierCostDeduction = carrierCostDeduction;
    }

    public String getTechFees() {
        return techFees;
    }

    public void setTechFees(String techFees) {
        this.techFees = techFees;
    }

    public String getMarketingFees() {
        return marketingFees;
    }

    public void setMarketingFees(String marketingFees) {
        this.marketingFees = marketingFees;
    }

    public String getNetPayablesExcGst() {
        return netPayablesExcGst;
    }

    public void setNetPayablesExcGst(String netPayablesExcGst) {
        this.netPayablesExcGst = netPayablesExcGst;
    }

    public String getNetPayablesGst() {
        return netPayablesGst;
    }

    public void setNetPayablesGst(String netPayablesGst) {
        this.netPayablesGst = netPayablesGst;
    }

    public String getTotalPayables() {
        return totalPayables;
    }

    public void setTotalPayables(String totalPayables) {
        this.totalPayables = totalPayables;
    }

    public String getDay61MarginShare() {
        return day61MarginShare;
    }

    public void setDay61MarginShare(String day61MarginShare) {
        this.day61MarginShare = day61MarginShare;
    }

    public String getRepaidCarrierDeductions() {
        return repaidCarrierDeductions;
    }

    public void setRepaidCarrierDeductions(String repaidCarrierDeductions) {
        this.repaidCarrierDeductions = repaidCarrierDeductions;
    }

    public String getNonCentralCarrierCostGst() {
        return nonCentralCarrierCostGst;
    }

    public void setNonCentralCarrierCostGst(String nonCentralCarrierCostGst) {
        this.nonCentralCarrierCostGst = nonCentralCarrierCostGst;
    }
}

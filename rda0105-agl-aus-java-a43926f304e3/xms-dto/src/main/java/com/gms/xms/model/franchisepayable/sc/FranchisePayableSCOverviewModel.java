package com.gms.xms.model.franchisepayable.sc;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableSCOverviewModel
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class FranchisePayableSCOverviewModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String rptTxnId;
    private String setups;
    private String activations;
    private String printedInvoices;
    private String emailInvoices;
    private String customerCost;
    private String customerMarginableCost;
    private String franchiseCost;
    private String franchiseCostTaxable;
    private String franchiseCostNonTaxable;
    private String franchiseGst;
    private String franchiseTotal;
    private String marginShared;
    private String managementFee;
    private String marketingFee;
    private String carrierCredits;
    private String carrierCreditsTaxable;
    private String carrierCreditsNonTaxable;
    private String carrierCreditsGst;
    private String carrierCreditsTotal;
    private String techFeeOnIntlShipments;
    private String techFeeOnDomShipments;
    private String intlShipmentCount;
    private String domShipmentCount;
    private String netReceivable;
    private String gst;
    private String totalReceivable;
    private String managementFeeOnCreditRevenue;
    private String managementFeeOnCreditProfitShared;
    private String franchiseCode;

    @Override
    public String toString() {
        return "FranchisePayableSCOverviewModel [rptTxnId=" + rptTxnId + ", setups=" + setups + ", activations=" + activations + ", printedInvoices=" + printedInvoices + ", emailInvoices=" + emailInvoices + ", customerCost=" + customerCost + ", customerMarginableCost=" + customerMarginableCost + ", franchiseCost=" + franchiseCost + ", franchiseCostTaxable=" + franchiseCostTaxable + ", franchiseCostNonTaxable=" + franchiseCostNonTaxable + ", franchiseGst=" + franchiseGst + ", franchiseTotal="
                + franchiseTotal + ", marginShared=" + marginShared + ", managementFee=" + managementFee + ", marketingFee=" + marketingFee + ", carrierCredits=" + carrierCredits + ", carrierCreditsTaxable=" + carrierCreditsTaxable + ", carrierCreditsNonTaxable=" + carrierCreditsNonTaxable + ", carrierCreditsGst=" + carrierCreditsGst + ", carrierCreditsTotal=" + carrierCreditsTotal + ", techFeeOnIntlShipments=" + techFeeOnIntlShipments + ", techFeeOnDomShipments=" + techFeeOnDomShipments
                + ", intlShipmentCount=" + intlShipmentCount + ", domShipmentCount=" + domShipmentCount + ", netReceivable=" + netReceivable + ", gst=" + gst + ", totalReceivable=" + totalReceivable + ", managementFeeOnCreditRevenue=" + managementFeeOnCreditRevenue + ", managementFeeOnCreditProfitShared=" + managementFeeOnCreditProfitShared + ", franchiseCode=" + franchiseCode + "]";
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

    public String getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(String customerCost) {
        this.customerCost = customerCost;
    }

    public String getCustomerMarginableCost() {
        return customerMarginableCost;
    }

    public void setCustomerMarginableCost(String customerMarginableCost) {
        this.customerMarginableCost = customerMarginableCost;
    }

    public String getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(String franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    public String getFranchiseCostTaxable() {
        return franchiseCostTaxable;
    }

    public void setFranchiseCostTaxable(String franchiseCostTaxable) {
        this.franchiseCostTaxable = franchiseCostTaxable;
    }

    public String getFranchiseCostNonTaxable() {
        return franchiseCostNonTaxable;
    }

    public void setFranchiseCostNonTaxable(String franchiseCostNonTaxable) {
        this.franchiseCostNonTaxable = franchiseCostNonTaxable;
    }

    public String getFranchiseGst() {
        return franchiseGst;
    }

    public void setFranchiseGst(String franchiseGst) {
        this.franchiseGst = franchiseGst;
    }

    public String getFranchiseTotal() {
        return franchiseTotal;
    }

    public void setFranchiseTotal(String franchiseTotal) {
        this.franchiseTotal = franchiseTotal;
    }

    public String getMarginShared() {
        return marginShared;
    }

    public void setMarginShared(String marginShared) {
        this.marginShared = marginShared;
    }

    public String getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(String managementFee) {
        this.managementFee = managementFee;
    }

    public String getMarketingFee() {
        return marketingFee;
    }

    public void setMarketingFee(String marketingFee) {
        this.marketingFee = marketingFee;
    }

    public String getCarrierCredits() {
        return carrierCredits;
    }

    public void setCarrierCredits(String carrierCredits) {
        this.carrierCredits = carrierCredits;
    }

    public String getCarrierCreditsTaxable() {
        return carrierCreditsTaxable;
    }

    public void setCarrierCreditsTaxable(String carrierCreditsTaxable) {
        this.carrierCreditsTaxable = carrierCreditsTaxable;
    }

    public String getCarrierCreditsNonTaxable() {
        return carrierCreditsNonTaxable;
    }

    public void setCarrierCreditsNonTaxable(String carrierCreditsNonTaxable) {
        this.carrierCreditsNonTaxable = carrierCreditsNonTaxable;
    }

    public String getCarrierCreditsGst() {
        return carrierCreditsGst;
    }

    public void setCarrierCreditsGst(String carrierCreditsGst) {
        this.carrierCreditsGst = carrierCreditsGst;
    }

    public String getCarrierCreditsTotal() {
        return carrierCreditsTotal;
    }

    public void setCarrierCreditsTotal(String carrierCreditsTotal) {
        this.carrierCreditsTotal = carrierCreditsTotal;
    }

    public String getTechFeeOnIntlShipments() {
        return techFeeOnIntlShipments;
    }

    public void setTechFeeOnIntlShipments(String techFeeOnIntlShipments) {
        this.techFeeOnIntlShipments = techFeeOnIntlShipments;
    }

    public String getTechFeeOnDomShipments() {
        return techFeeOnDomShipments;
    }

    public void setTechFeeOnDomShipments(String techFeeOnDomShipments) {
        this.techFeeOnDomShipments = techFeeOnDomShipments;
    }

    public String getIntlShipmentCount() {
        return intlShipmentCount;
    }

    public void setIntlShipmentCount(String intlShipmentCount) {
        this.intlShipmentCount = intlShipmentCount;
    }

    public String getDomShipmentCount() {
        return domShipmentCount;
    }

    public void setDomShipmentCount(String domShipmentCount) {
        this.domShipmentCount = domShipmentCount;
    }

    public String getNetReceivable() {
        return netReceivable;
    }

    public void setNetReceivable(String netReceivable) {
        this.netReceivable = netReceivable;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getTotalReceivable() {
        return totalReceivable;
    }

    public void setTotalReceivable(String totalReceivable) {
        this.totalReceivable = totalReceivable;
    }

    public String getManagementFeeOnCreditRevenue() {
        return managementFeeOnCreditRevenue;
    }

    public void setManagementFeeOnCreditRevenue(String managementFeeOnCreditRevenue) {
        this.managementFeeOnCreditRevenue = managementFeeOnCreditRevenue;
    }

    public String getManagementFeeOnCreditProfitShared() {
        return managementFeeOnCreditProfitShared;
    }

    public void setManagementFeeOnCreditProfitShared(String managementFeeOnCreditProfitShared) {
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

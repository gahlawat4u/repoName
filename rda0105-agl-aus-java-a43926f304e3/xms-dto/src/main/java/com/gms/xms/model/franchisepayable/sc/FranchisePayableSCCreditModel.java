package com.gms.xms.model.franchisepayable.sc;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableSCCreditModel
 * <p>
 * Author DatTV Oct 28, 2015
 */
public class FranchisePayableSCCreditModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String paymentDate;
    private String customerNumber;
    private String customerName;
    private String invoiceNumber;
    private String airbillNumber;
    private String amountOutstanding;
    private String internationalDomestic;
    private String isTaxableShipment;
    private String customerTotalGst;
    private String customerTotalExcGst;
    private String franchiseCostGst;
    private String franchiseCostExcGst;
    private String grossMarginGst;
    private String grossMarginExcGst;
    private String previouslyPaid;
    private String paymentsReceived;
    private String creditsFranchiseCost;
    private String creditsCustomerCost;
    private String profitShareGst;
    private String profitShareExcGst;
    private String totalProfitShare;
    private String newMarginExcGst;
    private String newMarginGst;
    private String creditsFranchiseCostExcGst;
    private String creditsFranchiseCostGst;
    private String creditsCustomerCostExcGst;
    private String creditsCustomerCostGst;

    @Override
    public String toString() {
        return "FranchisePayableSCCreditModel [paymentDate=" + paymentDate + ", customerNumber=" + customerNumber + ", customerName=" + customerName + ", invoiceNumber=" + invoiceNumber + ", airbillNumber=" + airbillNumber + ", amountOutstanding=" + amountOutstanding + ", internationalDomestic=" + internationalDomestic + ", isTaxableShipment=" + isTaxableShipment + ", customerTotalGst=" + customerTotalGst + ", customerTotalExcGst=" + customerTotalExcGst + ", franchiseCostGst=" + franchiseCostGst
                + ", franchiseCostExcGst=" + franchiseCostExcGst + ", grossMarginGst=" + grossMarginGst + ", grossMarginExcGst=" + grossMarginExcGst + ", previouslyPaid=" + previouslyPaid + ", paymentsReceived=" + paymentsReceived + ", creditsFranchiseCost=" + creditsFranchiseCost + ", creditsCustomerCost=" + creditsCustomerCost + ", profitShareGst=" + profitShareGst + ", profitShareExcGst=" + profitShareExcGst + ", totalProfitShare=" + totalProfitShare + ", newMarginExcGst=" + newMarginExcGst
                + ", newMarginGst=" + newMarginGst + ", creditsFranchiseCostExcGst=" + creditsFranchiseCostExcGst + ", creditsFranchiseCostGst=" + creditsFranchiseCostGst + ", creditsCustomerCostExcGst=" + creditsCustomerCostExcGst + ", creditsCustomerCostGst=" + creditsCustomerCostGst + "]";
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getAmountOutstanding() {
        return amountOutstanding;
    }

    public void setAmountOutstanding(String amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }

    public String getInternationalDomestic() {
        return internationalDomestic;
    }

    public void setInternationalDomestic(String internationalDomestic) {
        this.internationalDomestic = internationalDomestic;
    }

    public String getCustomerTotalGst() {
        return customerTotalGst;
    }

    public void setCustomerTotalGst(String customerTotalGst) {
        this.customerTotalGst = customerTotalGst;
    }

    public String getCustomerTotalExcGst() {
        return customerTotalExcGst;
    }

    public void setCustomerTotalExcGst(String customerTotalExcGst) {
        this.customerTotalExcGst = customerTotalExcGst;
    }

    public String getFranchiseCostGst() {
        return franchiseCostGst;
    }

    public void setFranchiseCostGst(String franchiseCostGst) {
        this.franchiseCostGst = franchiseCostGst;
    }

    public String getFranchiseCostExcGst() {
        return franchiseCostExcGst;
    }

    public void setFranchiseCostExcGst(String franchiseCostExcGst) {
        this.franchiseCostExcGst = franchiseCostExcGst;
    }

    public String getGrossMarginGst() {
        return grossMarginGst;
    }

    public void setGrossMarginGst(String grossMarginGst) {
        this.grossMarginGst = grossMarginGst;
    }

    public String getGrossMarginExcGst() {
        return grossMarginExcGst;
    }

    public void setGrossMarginExcGst(String grossMarginExcGst) {
        this.grossMarginExcGst = grossMarginExcGst;
    }

    public String getPreviouslyPaid() {
        return previouslyPaid;
    }

    public void setPreviouslyPaid(String previouslyPaid) {
        this.previouslyPaid = previouslyPaid;
    }

    public String getPaymentsReceived() {
        return paymentsReceived;
    }

    public void setPaymentsReceived(String paymentsReceived) {
        this.paymentsReceived = paymentsReceived;
    }

    public String getCreditsFranchiseCost() {
        return creditsFranchiseCost;
    }

    public void setCreditsFranchiseCost(String creditsFranchiseCost) {
        this.creditsFranchiseCost = creditsFranchiseCost;
    }

    public String getCreditsCustomerCost() {
        return creditsCustomerCost;
    }

    public void setCreditsCustomerCost(String creditsCustomerCost) {
        this.creditsCustomerCost = creditsCustomerCost;
    }

    public String getProfitShareGst() {
        return profitShareGst;
    }

    public void setProfitShareGst(String profitShareGst) {
        this.profitShareGst = profitShareGst;
    }

    public String getProfitShareExcGst() {
        return profitShareExcGst;
    }

    public void setProfitShareExcGst(String profitShareExcGst) {
        this.profitShareExcGst = profitShareExcGst;
    }

    public String getTotalProfitShare() {
        return totalProfitShare;
    }

    public void setTotalProfitShare(String totalProfitShare) {
        this.totalProfitShare = totalProfitShare;
    }

    public String getNewMarginExcGst() {
        return newMarginExcGst;
    }

    public void setNewMarginExcGst(String newMarginExcGst) {
        this.newMarginExcGst = newMarginExcGst;
    }

    public String getNewMarginGst() {
        return newMarginGst;
    }

    public void setNewMarginGst(String newMarginGst) {
        this.newMarginGst = newMarginGst;
    }

    public String getCreditsFranchiseCostExcGst() {
        return creditsFranchiseCostExcGst;
    }

    public void setCreditsFranchiseCostExcGst(String creditsFranchiseCostExcGst) {
        this.creditsFranchiseCostExcGst = creditsFranchiseCostExcGst;
    }

    public String getCreditsFranchiseCostGst() {
        return creditsFranchiseCostGst;
    }

    public void setCreditsFranchiseCostGst(String creditsFranchiseCostGst) {
        this.creditsFranchiseCostGst = creditsFranchiseCostGst;
    }

    public String getCreditsCustomerCostExcGst() {
        return creditsCustomerCostExcGst;
    }

    public void setCreditsCustomerCostExcGst(String creditsCustomerCostExcGst) {
        this.creditsCustomerCostExcGst = creditsCustomerCostExcGst;
    }

    public String getCreditsCustomerCostGst() {
        return creditsCustomerCostGst;
    }

    public void setCreditsCustomerCostGst(String creditsCustomerCostGst) {
        this.creditsCustomerCostGst = creditsCustomerCostGst;
    }

    public String getIsTaxableShipment() {
        return isTaxableShipment;
    }

    public void setIsTaxableShipment(String isTaxableShipment) {
        this.isTaxableShipment = isTaxableShipment;
    }
}
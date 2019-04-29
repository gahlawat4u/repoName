package com.gms.xms.model.franchisepayable;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableMS61DaysModel.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:16:42 PM
 */
public class FranchisePayableMS61DaysModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String paymentDate;
    private String customerNumber;
    private String customerName;
    private String invoiceNumber;
    private String airbillNumber;
    private String amountOutstanding;
    private String internationalDomestic;
    private String customerTotalGst;
    private String customerTotalExcGst;
    private String franchiseCostGst;
    private String franchiseCostExcGst;
    private String grossMarginGst;
    private String grossMarginExcGst;
    private String previouslyPaid;
    private String creditsFranchiseCost;
    private String creditsCustomerCost;
    private String profitShareGst;
    private String profitShareExcGst;
    private String totalProfitShare;
    private String paymentsReceived;
    private String previouslyDeductedCost;
    private String profitShareOnPayments;
    private String profitShareOnLateFees;
    private String repaidCarrierDeductions;

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

    public String getPaymentsReceived() {
        return paymentsReceived;
    }

    public void setPaymentsReceived(String paymentsReceived) {
        this.paymentsReceived = paymentsReceived;
    }

    public String getPreviouslyDeductedCost() {
        return previouslyDeductedCost;
    }

    public void setPreviouslyDeductedCost(String previouslyDeductedCost) {
        this.previouslyDeductedCost = previouslyDeductedCost;
    }

    public String getProfitShareOnPayments() {
        return profitShareOnPayments;
    }

    public void setProfitShareOnPayments(String profitShareOnPayments) {
        this.profitShareOnPayments = profitShareOnPayments;
    }

    public String getProfitShareOnLateFees() {
        return profitShareOnLateFees;
    }

    public void setProfitShareOnLateFees(String profitShareOnLateFees) {
        this.profitShareOnLateFees = profitShareOnLateFees;
    }

    public String getRepaidCarrierDeductions() {
        return repaidCarrierDeductions;
    }

    public void setRepaidCarrierDeductions(String repaidCarrierDeductions) {
        this.repaidCarrierDeductions = repaidCarrierDeductions;
    }

    @Override
    public String toString() {
        return "FranchisePayableMS61DaysModel [paymentDate=" + paymentDate + ", customerNumber=" + customerNumber + ", customerName=" + customerName + ", invoiceNumber=" + invoiceNumber + ", airbillNumber=" + airbillNumber + ", amountOutstanding=" + amountOutstanding + ", internationalDomestic=" + internationalDomestic + ", customerTotalGst=" + customerTotalGst + ", customerTotalExcGst=" + customerTotalExcGst + ", franchiseCostGst=" + franchiseCostGst + ", franchiseCostExcGst="
                + franchiseCostExcGst + ", grossMarginGst=" + grossMarginGst + ", grossMarginExcGst=" + grossMarginExcGst + ", previouslyPaid=" + previouslyPaid + ", creditsFranchiseCost=" + creditsFranchiseCost + ", creditsCustomerCost=" + creditsCustomerCost + ", profitShareGst=" + profitShareGst + ", profitShareExcGst=" + profitShareExcGst + ", totalProfitShare=" + totalProfitShare + ", paymentsReceived=" + paymentsReceived + ", previouslyDeductedCost=" + previouslyDeductedCost
                + ", profitShareOnPayments=" + profitShareOnPayments + ", profitShareOnLateFees=" + profitShareOnLateFees + ", repaidCarrierDeductions=" + repaidCarrierDeductions + "]";
    }
}

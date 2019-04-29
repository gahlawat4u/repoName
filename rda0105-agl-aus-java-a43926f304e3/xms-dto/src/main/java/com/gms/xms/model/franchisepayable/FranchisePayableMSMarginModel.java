package com.gms.xms.model.franchisepayable;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableMSMarginModel.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:16:59 PM
 */
public class FranchisePayableMSMarginModel extends BaseModel {

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
    private String paymentsReceived;
    private String creditsFranchiseCost;
    private String creditsCustomerCost;
    private String profitShareGst;
    private String profitShareExcGst;
    private String totalProfitShare;

    public String getPaymentsReceived() {
        return paymentsReceived;
    }

    public void setPaymentsReceived(String paymentsReceived) {
        this.paymentsReceived = paymentsReceived;
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

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
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

}

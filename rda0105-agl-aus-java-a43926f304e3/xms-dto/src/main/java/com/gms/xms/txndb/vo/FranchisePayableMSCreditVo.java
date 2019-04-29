package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonObjectVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Posted from FranchisePayableMSCreditVo.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:24:30 PM
 */
public class FranchisePayableMSCreditVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private Date paymentDate;
    private String customerNumber;
    private String customerName;
    private String invoiceNumber;
    private String airbillNumber;
    private BigDecimal amountOutstanding;
    private String internationalDomestic;
    private BigDecimal customerTotalGst;
    private BigDecimal customerTotalExcGst;
    private BigDecimal franchiseCostGst;
    private BigDecimal franchiseCostExcGst;
    private BigDecimal grossMarginGst;
    private BigDecimal grossMarginExcGst;
    private BigDecimal previouslyPaid;
    private BigDecimal paymentsReceived;
    private BigDecimal profitShareGst;
    private BigDecimal profitShareExcGst;
    private BigDecimal totalProfitShare;
    private BigDecimal newMarginExcGst;
    private BigDecimal newMarginGst;
    private BigDecimal creditsFranchiseCostExcGst;
    private BigDecimal creditsFranchiseCostGst;
    private BigDecimal creditsCustomerCostExcGst;
    private BigDecimal creditsCustomerCostGst;

    @Override
    public String toString() {
        return "FranchisePayableMSCreditVo [paymentDate=" + paymentDate + ", customerNumber=" + customerNumber + ", customerName=" + customerName + ", invoiceNumber=" + invoiceNumber + ", airbillNumber=" + airbillNumber + ", amountOutstanding=" + amountOutstanding + ", internationalDomestic=" + internationalDomestic + ", customerTotalGst=" + customerTotalGst + ", customerTotalExcGst=" + customerTotalExcGst + ", franchiseCostGst=" + franchiseCostGst + ", franchiseCostExcGst="
                + franchiseCostExcGst + ", grossMarginGst=" + grossMarginGst + ", grossMarginExcGst=" + grossMarginExcGst + ", previouslyPaid=" + previouslyPaid + ", paymentsReceived=" + paymentsReceived + ", profitShareGst=" + profitShareGst + ", profitShareExcGst=" + profitShareExcGst + ", totalProfitShare=" + totalProfitShare + ", newMarginExcGst=" + newMarginExcGst + ", newMarginGst=" + newMarginGst + ", creditsFranchiseCostExcGst=" + creditsFranchiseCostExcGst + ", creditsFranchiseCostGst="
                + creditsFranchiseCostGst + ", creditsCustomerCostExcGst=" + creditsCustomerCostExcGst + ", creditsCustomerCostGst=" + creditsCustomerCostGst + "]";
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getPaymentsReceived() {
        return paymentsReceived;
    }

    public void setPaymentsReceived(BigDecimal paymentsReceived) {
        this.paymentsReceived = paymentsReceived;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getGrossMarginGst() {
        return grossMarginGst;
    }

    public void setGrossMarginGst(BigDecimal grossMarginGst) {
        this.grossMarginGst = grossMarginGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getGrossMarginExcGst() {
        return grossMarginExcGst;
    }

    public void setGrossMarginExcGst(BigDecimal grossMarginExcGst) {
        this.grossMarginExcGst = grossMarginExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getPreviouslyPaid() {
        return previouslyPaid;
    }

    public void setPreviouslyPaid(BigDecimal previouslyPaid) {
        this.previouslyPaid = previouslyPaid;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @JsonSerialize(using = JsonObjectVo2ModelSerializer.class)
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

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAmountOutstanding() {
        return amountOutstanding;
    }

    public void setAmountOutstanding(BigDecimal amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }

    public String getInternationalDomestic() {
        return internationalDomestic;
    }

    public void setInternationalDomestic(String internationalDomestic) {
        this.internationalDomestic = internationalDomestic;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCustomerTotalGst() {
        return customerTotalGst;
    }

    public void setCustomerTotalGst(BigDecimal customerTotalGst) {
        this.customerTotalGst = customerTotalGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCustomerTotalExcGst() {
        return customerTotalExcGst;
    }

    public void setCustomerTotalExcGst(BigDecimal customerTotalExcGst) {
        this.customerTotalExcGst = customerTotalExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranchiseCostGst() {
        return franchiseCostGst;
    }

    public void setFranchiseCostGst(BigDecimal franchiseCostGst) {
        this.franchiseCostGst = franchiseCostGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranchiseCostExcGst() {
        return franchiseCostExcGst;
    }

    public void setFranchiseCostExcGst(BigDecimal franchiseCostExcGst) {
        this.franchiseCostExcGst = franchiseCostExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getProfitShareGst() {
        return profitShareGst;
    }

    public void setProfitShareGst(BigDecimal profitShareGst) {
        this.profitShareGst = profitShareGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getProfitShareExcGst() {
        return profitShareExcGst;
    }

    public void setProfitShareExcGst(BigDecimal profitShareExcGst) {
        this.profitShareExcGst = profitShareExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalProfitShare() {
        return totalProfitShare;
    }

    public void setTotalProfitShare(BigDecimal totalProfitShare) {
        this.totalProfitShare = totalProfitShare;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNewMarginExcGst() {
        return newMarginExcGst;
    }

    public void setNewMarginExcGst(BigDecimal newMarginExcGst) {
        this.newMarginExcGst = newMarginExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNewMarginGst() {
        return newMarginGst;
    }

    public void setNewMarginGst(BigDecimal newMarginGst) {
        this.newMarginGst = newMarginGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCreditsFranchiseCostExcGst() {
        return creditsFranchiseCostExcGst;
    }

    public void setCreditsFranchiseCostExcGst(BigDecimal creditsFranchiseCostExcGst) {
        this.creditsFranchiseCostExcGst = creditsFranchiseCostExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCreditsFranchiseCostGst() {
        return creditsFranchiseCostGst;
    }

    public void setCreditsFranchiseCostGst(BigDecimal creditsFranchiseCostGst) {
        this.creditsFranchiseCostGst = creditsFranchiseCostGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCreditsCustomerCostExcGst() {
        return creditsCustomerCostExcGst;
    }

    public void setCreditsCustomerCostExcGst(BigDecimal creditsCustomerCostExcGst) {
        this.creditsCustomerCostExcGst = creditsCustomerCostExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCreditsCustomerCostGst() {
        return creditsCustomerCostGst;
    }

    public void setCreditsCustomerCostGst(BigDecimal creditsCustomerCostGst) {
        this.creditsCustomerCostGst = creditsCustomerCostGst;
    }
}

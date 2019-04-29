package com.gms.xms.txndb.vo.franchisepayable.sc;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonObjectVo2ModelSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from FranchisePayableSCCreditVo
 * <p>
 * Author DatTV Oct 28, 2015
 */
public class FranchisePayableSCCreditVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private Date paymentDate;
    private String customerNumber;
    private String customerName;
    private String invoiceNumber;
    private String airbillNumber;
    private Double amountOutstanding;
    private String internationalDomestic;
    private Boolean isTaxableShipment;
    private Double customerTotalGst;
    private Double customerTotalExcGst;
    private Double franchiseCostGst;
    private Double franchiseCostExcGst;
    private Double grossMarginGst;
    private Double grossMarginExcGst;
    private Double previouslyPaid;
    private Double paymentsReceived;
    private Double profitShareGst;
    private Double profitShareExcGst;
    private Double totalProfitShare;
    private Double newMarginExcGst;
    private Double newMarginGst;
    private Double creditsFranchiseCostExcGst;
    private Double creditsFranchiseCostGst;
    private Double creditsCustomerCostExcGst;
    private Double creditsCustomerCostGst;

    @Override
    public String toString() {
        return "FranchisePayableSCCreditVo [paymentDate=" + paymentDate + ", customerNumber=" + customerNumber + ", customerName=" + customerName + ", invoiceNumber=" + invoiceNumber + ", airbillNumber=" + airbillNumber + ", amountOutstanding=" + amountOutstanding + ", internationalDomestic=" + internationalDomestic + ", isTaxableShipment=" + isTaxableShipment + ", customerTotalGst=" + customerTotalGst + ", customerTotalExcGst=" + customerTotalExcGst + ", franchiseCostGst=" + franchiseCostGst
                + ", franchiseCostExcGst=" + franchiseCostExcGst + ", grossMarginGst=" + grossMarginGst + ", grossMarginExcGst=" + grossMarginExcGst + ", previouslyPaid=" + previouslyPaid + ", paymentsReceived=" + paymentsReceived + ", profitShareGst=" + profitShareGst + ", profitShareExcGst=" + profitShareExcGst + ", totalProfitShare=" + totalProfitShare + ", newMarginExcGst=" + newMarginExcGst + ", newMarginGst=" + newMarginGst + ", creditsFranchiseCostExcGst=" + creditsFranchiseCostExcGst
                + ", creditsFranchiseCostGst=" + creditsFranchiseCostGst + ", creditsCustomerCostExcGst=" + creditsCustomerCostExcGst + ", creditsCustomerCostGst=" + creditsCustomerCostGst + "]";
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getPaymentsReceived() {
        return paymentsReceived;
    }

    public void setPaymentsReceived(Double paymentsReceived) {
        this.paymentsReceived = paymentsReceived;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMarginGst() {
        return grossMarginGst;
    }

    public void setGrossMarginGst(Double grossMarginGst) {
        this.grossMarginGst = grossMarginGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMarginExcGst() {
        return grossMarginExcGst;
    }

    public void setGrossMarginExcGst(Double grossMarginExcGst) {
        this.grossMarginExcGst = grossMarginExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getPreviouslyPaid() {
        return previouslyPaid;
    }

    public void setPreviouslyPaid(Double previouslyPaid) {
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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAmountOutstanding() {
        return amountOutstanding;
    }

    public void setAmountOutstanding(Double amountOutstanding) {
        this.amountOutstanding = amountOutstanding;
    }

    public String getInternationalDomestic() {
        return internationalDomestic;
    }

    public void setInternationalDomestic(String internationalDomestic) {
        this.internationalDomestic = internationalDomestic;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerTotalGst() {
        return customerTotalGst;
    }

    public void setCustomerTotalGst(Double customerTotalGst) {
        this.customerTotalGst = customerTotalGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerTotalExcGst() {
        return customerTotalExcGst;
    }

    public void setCustomerTotalExcGst(Double customerTotalExcGst) {
        this.customerTotalExcGst = customerTotalExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCostGst() {
        return franchiseCostGst;
    }

    public void setFranchiseCostGst(Double franchiseCostGst) {
        this.franchiseCostGst = franchiseCostGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCostExcGst() {
        return franchiseCostExcGst;
    }

    public void setFranchiseCostExcGst(Double franchiseCostExcGst) {
        this.franchiseCostExcGst = franchiseCostExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getProfitShareGst() {
        return profitShareGst;
    }

    public void setProfitShareGst(Double profitShareGst) {
        this.profitShareGst = profitShareGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getProfitShareExcGst() {
        return profitShareExcGst;
    }

    public void setProfitShareExcGst(Double profitShareExcGst) {
        this.profitShareExcGst = profitShareExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalProfitShare() {
        return totalProfitShare;
    }

    public void setTotalProfitShare(Double totalProfitShare) {
        this.totalProfitShare = totalProfitShare;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNewMarginExcGst() {
        return newMarginExcGst;
    }

    public void setNewMarginExcGst(Double newMarginExcGst) {
        this.newMarginExcGst = newMarginExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNewMarginGst() {
        return newMarginGst;
    }

    public void setNewMarginGst(Double newMarginGst) {
        this.newMarginGst = newMarginGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCreditsFranchiseCostExcGst() {
        return creditsFranchiseCostExcGst;
    }

    public void setCreditsFranchiseCostExcGst(Double creditsFranchiseCostExcGst) {
        this.creditsFranchiseCostExcGst = creditsFranchiseCostExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCreditsFranchiseCostGst() {
        return creditsFranchiseCostGst;
    }

    public void setCreditsFranchiseCostGst(Double creditsFranchiseCostGst) {
        this.creditsFranchiseCostGst = creditsFranchiseCostGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCreditsCustomerCostExcGst() {
        return creditsCustomerCostExcGst;
    }

    public void setCreditsCustomerCostExcGst(Double creditsCustomerCostExcGst) {
        this.creditsCustomerCostExcGst = creditsCustomerCostExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCreditsCustomerCostGst() {
        return creditsCustomerCostGst;
    }

    public void setCreditsCustomerCostGst(Double creditsCustomerCostGst) {
        this.creditsCustomerCostGst = creditsCustomerCostGst;
    }

    public Boolean getIsTaxableShipment() {
        return isTaxableShipment;
    }

    public void setIsTaxableShipment(Boolean isTaxableShipment) {
        this.isTaxableShipment = isTaxableShipment;
    }
}

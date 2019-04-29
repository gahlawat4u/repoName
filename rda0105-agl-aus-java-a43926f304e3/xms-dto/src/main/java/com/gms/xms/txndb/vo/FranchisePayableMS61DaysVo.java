package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonObjectVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Posted from FranchisePayableMS61DaysVo.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:24:27 PM
 */
public class FranchisePayableMS61DaysVo extends BaseVo {

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
    private BigDecimal creditsFranchiseCost;
    private BigDecimal creditsCustomerCost;
    private BigDecimal profitShareGst;
    private BigDecimal profitShareExcGst;
    private BigDecimal totalProfitShare;
    private BigDecimal paymentsReceived;
    private String previouslyDeductedCost;
    private BigDecimal profitShareOnPayments;
    private BigDecimal profitShareOnLateFees;
    private BigDecimal repaidCarrierDeductions;

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
    public BigDecimal getFranchiseCostGst() {
        return franchiseCostGst;
    }

    public void setFranchiseCostGst(BigDecimal franchiseCostGst) {
        this.franchiseCostGst = franchiseCostGst;
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
    public BigDecimal getCreditsFranchiseCost() {
        return creditsFranchiseCost;
    }

    public void setCreditsFranchiseCost(BigDecimal creditsFranchiseCost) {
        this.creditsFranchiseCost = creditsFranchiseCost;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCreditsCustomerCost() {
        return creditsCustomerCost;
    }

    public void setCreditsCustomerCost(BigDecimal creditsCustomerCost) {
        this.creditsCustomerCost = creditsCustomerCost;
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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @JsonSerialize(using = JsonObjectVo2ModelSerializer.class)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCustomerTotalExcGst() {
        return customerTotalExcGst;
    }

    public void setCustomerTotalExcGst(BigDecimal customerTotalExcGst) {
        this.customerTotalExcGst = customerTotalExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranchiseCostExcGst() {
        return franchiseCostExcGst;
    }

    public void setFranchiseCostExcGst(BigDecimal franchiseCostExcGst) {
        this.franchiseCostExcGst = franchiseCostExcGst;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getPreviouslyPaid() {
        return previouslyPaid;
    }

    public void setPreviouslyPaid(BigDecimal previouslyPaid) {
        this.previouslyPaid = previouslyPaid;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getPaymentsReceived() {
        return paymentsReceived;
    }

    public void setPaymentsReceived(BigDecimal paymentsReceived) {
        this.paymentsReceived = paymentsReceived;
    }

    public String getPreviouslyDeductedCost() {
        return previouslyDeductedCost;
    }

    public void setPreviouslyDeductedCost(String previouslyDeductedCost) {
        this.previouslyDeductedCost = previouslyDeductedCost;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getProfitShareOnPayments() {
        return profitShareOnPayments;
    }

    public void setProfitShareOnPayments(BigDecimal profitShareOnPayments) {
        this.profitShareOnPayments = profitShareOnPayments;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getProfitShareOnLateFees() {
        return profitShareOnLateFees;
    }

    public void setProfitShareOnLateFees(BigDecimal profitShareOnLateFees) {
        this.profitShareOnLateFees = profitShareOnLateFees;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getRepaidCarrierDeductions() {
        return repaidCarrierDeductions;
    }

    public void setRepaidCarrierDeductions(BigDecimal repaidCarrierDeductions) {
        this.repaidCarrierDeductions = repaidCarrierDeductions;
    }

    @Override
    public String toString() {
        return "FranchisePayableMS61DaysVo [paymentDate=" + paymentDate + ", customerNumber=" + customerNumber + ", customerName=" + customerName + ", invoiceNumber=" + invoiceNumber + ", airbillNumber=" + airbillNumber + ", amountOutstanding=" + amountOutstanding + ", internationalDomestic=" + internationalDomestic + ", customerTotalGst=" + customerTotalGst + ", customerTotalExcGst=" + customerTotalExcGst + ", franchiseCostGst=" + franchiseCostGst + ", franchiseCostExcGst="
                + franchiseCostExcGst + ", grossMarginGst=" + grossMarginGst + ", grossMarginExcGst=" + grossMarginExcGst + ", previouslyPaid=" + previouslyPaid + ", creditsFranchiseCost=" + creditsFranchiseCost + ", creditsCustomerCost=" + creditsCustomerCost + ", profitShareGst=" + profitShareGst + ", profitShareExcGst=" + profitShareExcGst + ", totalProfitShare=" + totalProfitShare + ", paymentsReceived=" + paymentsReceived + ", previouslyDeductedCost=" + previouslyDeductedCost
                + ", profitShareOnPayments=" + profitShareOnPayments + ", profitShareOnLateFees=" + profitShareOnLateFees + ", repaidCarrierDeductions=" + repaidCarrierDeductions + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((amountOutstanding == null) ? 0 : amountOutstanding.hashCode());
        result = prime * result + ((creditsCustomerCost == null) ? 0 : creditsCustomerCost.hashCode());
        result = prime * result + ((creditsFranchiseCost == null) ? 0 : creditsFranchiseCost.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((customerNumber == null) ? 0 : customerNumber.hashCode());
        result = prime * result + ((customerTotalExcGst == null) ? 0 : customerTotalExcGst.hashCode());
        result = prime * result + ((customerTotalGst == null) ? 0 : customerTotalGst.hashCode());
        result = prime * result + ((franchiseCostExcGst == null) ? 0 : franchiseCostExcGst.hashCode());
        result = prime * result + ((franchiseCostGst == null) ? 0 : franchiseCostGst.hashCode());
        result = prime * result + ((grossMarginExcGst == null) ? 0 : grossMarginExcGst.hashCode());
        result = prime * result + ((grossMarginGst == null) ? 0 : grossMarginGst.hashCode());
        result = prime * result + ((internationalDomestic == null) ? 0 : internationalDomestic.hashCode());
        result = prime * result + ((invoiceNumber == null) ? 0 : invoiceNumber.hashCode());
        result = prime * result + ((paymentDate == null) ? 0 : paymentDate.hashCode());
        result = prime * result + ((paymentsReceived == null) ? 0 : paymentsReceived.hashCode());
        result = prime * result + ((previouslyDeductedCost == null) ? 0 : previouslyDeductedCost.hashCode());
        result = prime * result + ((previouslyPaid == null) ? 0 : previouslyPaid.hashCode());
        result = prime * result + ((profitShareExcGst == null) ? 0 : profitShareExcGst.hashCode());
        result = prime * result + ((profitShareGst == null) ? 0 : profitShareGst.hashCode());
        result = prime * result + ((profitShareOnLateFees == null) ? 0 : profitShareOnLateFees.hashCode());
        result = prime * result + ((profitShareOnPayments == null) ? 0 : profitShareOnPayments.hashCode());
        result = prime * result + ((repaidCarrierDeductions == null) ? 0 : repaidCarrierDeductions.hashCode());
        result = prime * result + ((totalProfitShare == null) ? 0 : totalProfitShare.hashCode());
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
        FranchisePayableMS61DaysVo other = (FranchisePayableMS61DaysVo) obj;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (amountOutstanding == null) {
            if (other.amountOutstanding != null)
                return false;
        } else if (!amountOutstanding.equals(other.amountOutstanding))
            return false;
        if (creditsCustomerCost == null) {
            if (other.creditsCustomerCost != null)
                return false;
        } else if (!creditsCustomerCost.equals(other.creditsCustomerCost))
            return false;
        if (creditsFranchiseCost == null) {
            if (other.creditsFranchiseCost != null)
                return false;
        } else if (!creditsFranchiseCost.equals(other.creditsFranchiseCost))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (customerNumber == null) {
            if (other.customerNumber != null)
                return false;
        } else if (!customerNumber.equals(other.customerNumber))
            return false;
        if (customerTotalExcGst == null) {
            if (other.customerTotalExcGst != null)
                return false;
        } else if (!customerTotalExcGst.equals(other.customerTotalExcGst))
            return false;
        if (customerTotalGst == null) {
            if (other.customerTotalGst != null)
                return false;
        } else if (!customerTotalGst.equals(other.customerTotalGst))
            return false;
        if (franchiseCostExcGst == null) {
            if (other.franchiseCostExcGst != null)
                return false;
        } else if (!franchiseCostExcGst.equals(other.franchiseCostExcGst))
            return false;
        if (franchiseCostGst == null) {
            if (other.franchiseCostGst != null)
                return false;
        } else if (!franchiseCostGst.equals(other.franchiseCostGst))
            return false;
        if (grossMarginExcGst == null) {
            if (other.grossMarginExcGst != null)
                return false;
        } else if (!grossMarginExcGst.equals(other.grossMarginExcGst))
            return false;
        if (grossMarginGst == null) {
            if (other.grossMarginGst != null)
                return false;
        } else if (!grossMarginGst.equals(other.grossMarginGst))
            return false;
        if (internationalDomestic == null) {
            if (other.internationalDomestic != null)
                return false;
        } else if (!internationalDomestic.equals(other.internationalDomestic))
            return false;
        if (invoiceNumber == null) {
            if (other.invoiceNumber != null)
                return false;
        } else if (!invoiceNumber.equals(other.invoiceNumber))
            return false;
        if (paymentDate == null) {
            if (other.paymentDate != null)
                return false;
        } else if (!paymentDate.equals(other.paymentDate))
            return false;
        if (paymentsReceived == null) {
            if (other.paymentsReceived != null)
                return false;
        } else if (!paymentsReceived.equals(other.paymentsReceived))
            return false;
        if (previouslyDeductedCost == null) {
            if (other.previouslyDeductedCost != null)
                return false;
        } else if (!previouslyDeductedCost.equals(other.previouslyDeductedCost))
            return false;
        if (previouslyPaid == null) {
            if (other.previouslyPaid != null)
                return false;
        } else if (!previouslyPaid.equals(other.previouslyPaid))
            return false;
        if (profitShareExcGst == null) {
            if (other.profitShareExcGst != null)
                return false;
        } else if (!profitShareExcGst.equals(other.profitShareExcGst))
            return false;
        if (profitShareGst == null) {
            if (other.profitShareGst != null)
                return false;
        } else if (!profitShareGst.equals(other.profitShareGst))
            return false;
        if (profitShareOnLateFees == null) {
            if (other.profitShareOnLateFees != null)
                return false;
        } else if (!profitShareOnLateFees.equals(other.profitShareOnLateFees))
            return false;
        if (profitShareOnPayments == null) {
            if (other.profitShareOnPayments != null)
                return false;
        } else if (!profitShareOnPayments.equals(other.profitShareOnPayments))
            return false;
        if (repaidCarrierDeductions == null) {
            if (other.repaidCarrierDeductions != null)
                return false;
        } else if (!repaidCarrierDeductions.equals(other.repaidCarrierDeductions))
            return false;
        if (totalProfitShare == null) {
            if (other.totalProfitShare != null)
                return false;
        } else if (!totalProfitShare.equals(other.totalProfitShare))
            return false;
        return true;
    }
}

package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonObjectVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

/**
 * Posted from FranchisePayableMSDeductVo.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:24:34 PM
 */
public class FranchisePayableMSDeductVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private String customerName;
    private String invoiceNumber;
    private String airbillNumber;
    private BigDecimal customerPayment;
    private BigDecimal customerCost;
    private BigDecimal customerTax;
    private BigDecimal franchiseCost;
    private BigDecimal franchiseTax;
    private BigDecimal franchiseCharge;

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
    public BigDecimal getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(BigDecimal customerPayment) {
        this.customerPayment = customerPayment;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(BigDecimal customerCost) {
        this.customerCost = customerCost;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCustomerTax() {
        return customerTax;
    }

    public void setCustomerTax(BigDecimal customerTax) {
        this.customerTax = customerTax;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(BigDecimal franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranchiseTax() {
        return franchiseTax;
    }

    public void setFranchiseTax(BigDecimal franchiseTax) {
        this.franchiseTax = franchiseTax;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranchiseCharge() {
        return franchiseCharge;
    }

    public void setFranchiseCharge(BigDecimal franchiseCharge) {
        this.franchiseCharge = franchiseCharge;
    }

    @Override
    public String toString() {
        return "FranchisePayablesCarrierCostDeduction [customerName=" + customerName + ", invoiceNumber=" + invoiceNumber + ", airbillNumber=" + airbillNumber + ", customerPayment=" + customerPayment + ", customerCost=" + customerCost + ", customerTax=" + customerTax + ", franchiseCost=" + franchiseCost + ", franchiseTax=" + franchiseTax + ", franchiseCharge=" + franchiseCharge + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((customerCost == null) ? 0 : customerCost.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((customerPayment == null) ? 0 : customerPayment.hashCode());
        result = prime * result + ((customerTax == null) ? 0 : customerTax.hashCode());
        result = prime * result + ((franchiseCharge == null) ? 0 : franchiseCharge.hashCode());
        result = prime * result + ((franchiseCost == null) ? 0 : franchiseCost.hashCode());
        result = prime * result + ((franchiseTax == null) ? 0 : franchiseTax.hashCode());
        result = prime * result + ((invoiceNumber == null) ? 0 : invoiceNumber.hashCode());
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
        FranchisePayableMSDeductVo other = (FranchisePayableMSDeductVo) obj;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (customerCost == null) {
            if (other.customerCost != null)
                return false;
        } else if (!customerCost.equals(other.customerCost))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (customerPayment == null) {
            if (other.customerPayment != null)
                return false;
        } else if (!customerPayment.equals(other.customerPayment))
            return false;
        if (customerTax == null) {
            if (other.customerTax != null)
                return false;
        } else if (!customerTax.equals(other.customerTax))
            return false;
        if (franchiseCharge == null) {
            if (other.franchiseCharge != null)
                return false;
        } else if (!franchiseCharge.equals(other.franchiseCharge))
            return false;
        if (franchiseCost == null) {
            if (other.franchiseCost != null)
                return false;
        } else if (!franchiseCost.equals(other.franchiseCost))
            return false;
        if (franchiseTax == null) {
            if (other.franchiseTax != null)
                return false;
        } else if (!franchiseTax.equals(other.franchiseTax))
            return false;
        if (invoiceNumber == null) {
            if (other.invoiceNumber != null)
                return false;
        } else if (!invoiceNumber.equals(other.invoiceNumber))
            return false;
        return true;
    }
}

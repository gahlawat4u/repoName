package com.gms.xms.model.webship.invoices;

import com.gms.xms.model.BaseModel;

/**
 * Posted from GSTSummaryModel
 * <p>
 * Author DatTV Date Jul 12, 2015 12:49:22 AM
 */
public class GSTSummaryModel extends BaseModel {

    private static final long serialVersionUID = -801302397862197678L;

    private String nonGstCustomerCost;
    private String gstCustomerCost;
    private String totalGstAmount;
    private String customerTaxAmount;
    private String customerTaxPercent;

    @Override
    public String toString() {
        return "GSTSummaryModel [nonGstCustomerCost=" + nonGstCustomerCost + ", gstCustomerCost=" + gstCustomerCost + ", totalGstAmount=" + totalGstAmount + ", customerTaxAmount=" + customerTaxAmount + ", customerTaxPercent=" + customerTaxPercent + "]";
    }

    public String getNonGstCustomerCost() {
        return nonGstCustomerCost;
    }

    public void setNonGstCustomerCost(String nonGstCustomerCost) {
        this.nonGstCustomerCost = nonGstCustomerCost;
    }

    public String getGstCustomerCost() {
        return gstCustomerCost;
    }

    public void setGstCustomerCost(String gstCustomerCost) {
        this.gstCustomerCost = gstCustomerCost;
    }

    public String getCustomerTaxAmount() {
        return customerTaxAmount;
    }

    public void setCustomerTaxAmount(String customerTaxAmount) {
        this.customerTaxAmount = customerTaxAmount;
    }

    public String getCustomerTaxPercent() {
        return customerTaxPercent;
    }

    public void setCustomerTaxPercent(String customerTaxPercent) {
        this.customerTaxPercent = customerTaxPercent;
    }

    public String getTotalGstAmount() {
        return totalGstAmount;
    }

    public void setTotalGstAmount(String totalGstAmount) {
        this.totalGstAmount = totalGstAmount;
    }
}

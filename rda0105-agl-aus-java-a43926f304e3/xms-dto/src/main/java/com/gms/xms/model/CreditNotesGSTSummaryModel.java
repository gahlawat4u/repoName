package com.gms.xms.model;

public class CreditNotesGSTSummaryModel extends BaseModel {
    private static final long serialVersionUID = 6977824957264702336L;

    private String customerAmount;
    private String gstCustomerAmount;
    private String customerTaxPercent;
    private String total;

    public String getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(String customerAmount) {
        this.customerAmount = customerAmount;
    }

    public String getGstCustomerAmount() {
        return gstCustomerAmount;
    }

    public void setGstCustomerAmount(String gstCustomerAmount) {
        this.gstCustomerAmount = gstCustomerAmount;
    }

    public String getCustomerTaxPercent() {
        return customerTaxPercent;
    }

    public void setCustomerTaxPercent(String customerTaxPercent) {
        this.customerTaxPercent = customerTaxPercent;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CreditNotesGSTSummaryModel [customerAmount=" + customerAmount + ", gstCustomerAmount=" + gstCustomerAmount + ", customerTaxPercent=" + customerTaxPercent + ", total=" + total + "]";
    }
}

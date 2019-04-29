package com.gms.xms.model.reports.customer.activation;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerActivationModel.java
 * <p>
 * Author dattrinh 11:04:09 AM
 */
public class CustomerActivationModel extends BaseModel {

    private static final long serialVersionUID = -1818165117004833703L;

    private String customerCode;
    private String customerName;
    private String saleRepName;
    private String submissionDate;
    private String activationDate;
    private String daysToActivate;
    private String firstInvoice;
    private String marginOnInvoice;

    @Override
    public String toString() {
        return "CustomerActivationModel [customerCode=" + customerCode + ", customerName=" + customerName + ", saleRepName=" + saleRepName + ", submissionDate=" + submissionDate + ", activationDate=" + activationDate + ", daysToActivate=" + daysToActivate + ", firstInvoice=" + firstInvoice + ", marginOnInvoice=" + marginOnInvoice + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSaleRepName() {
        return saleRepName;
    }

    public void setSaleRepName(String saleRepName) {
        this.saleRepName = saleRepName;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getDaysToActivate() {
        return daysToActivate;
    }

    public void setDaysToActivate(String daysToActivate) {
        this.daysToActivate = daysToActivate;
    }

    public String getFirstInvoice() {
        return firstInvoice;
    }

    public void setFirstInvoice(String firstInvoice) {
        this.firstInvoice = firstInvoice;
    }

    public String getMarginOnInvoice() {
        return marginOnInvoice;
    }

    public void setMarginOnInvoice(String marginOnInvoice) {
        this.marginOnInvoice = marginOnInvoice;
    }
}

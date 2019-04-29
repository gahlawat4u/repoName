package com.gms.xms.model.reports.customer.inactivity;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerInactivityModel.java
 * <p>
 * Author dattrinh 4:20:33 PM
 */
public class CustomerInactivityModel extends BaseModel {
    private static final long serialVersionUID = 5602739555777912149L;

    private String customerCode;
    private String customerName;
    private String email;
    private String saleRepName;
    private String submissionDate;
    private String activationDate;
    private String lastInvoiceDate;

    @Override
    public String toString() {
        return "CustomerInactivityModel [customerCode=" + customerCode + ", customerName=" + customerName + ", email=" + email + ", saleRepName=" + saleRepName + ", submissionDate=" + submissionDate + ", activationDate=" + activationDate + ", lastInvoiceDate=" + lastInvoiceDate + "]";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLastInvoiceDate() {
        return lastInvoiceDate;
    }

    public void setLastInvoiceDate(String lastInvoiceDate) {
        this.lastInvoiceDate = lastInvoiceDate;
    }
}

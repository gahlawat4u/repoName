package com.gms.xms.txndb.vo.reports.customer.inactivity;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from CustomerInactivityVo.java
 * <p>
 * Author dattrinh 4:09:26 PM
 */
public class CustomerInactivityVo extends BaseVo {

    private static final long serialVersionUID = -993297138567738426L;

    private String customerCode;
    private String customerName;
    private String email;
    private String saleRepName;
    private Date submissionDate;
    private Date activationDate;
    private Date lastInvoiceDate;

    @Override
    public String toString() {
        return "CustomerInactivityVo [customerCode=" + customerCode + ", customerName=" + customerName + ", email=" + email + ", saleRepName=" + saleRepName + ", submissionDate=" + submissionDate + ", activationDate=" + activationDate + ", lastInvoiceDate=" + lastInvoiceDate + "]";
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

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getLastInvoiceDate() {
        return lastInvoiceDate;
    }

    public void setLastInvoiceDate(Date lastInvoiceDate) {
        this.lastInvoiceDate = lastInvoiceDate;
    }
}

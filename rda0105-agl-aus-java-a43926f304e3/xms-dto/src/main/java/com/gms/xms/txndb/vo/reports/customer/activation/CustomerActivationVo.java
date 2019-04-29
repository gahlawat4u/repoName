package com.gms.xms.txndb.vo.reports.customer.activation;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from CustomerActivationVo.java
 * <p>
 * Author dattrinh 11:00:30 AM
 */
public class CustomerActivationVo extends BaseVo {

    private static final long serialVersionUID = -9011076015828278644L;

    private String customerCode;
    private String customerName;
    private String saleRepName;
    private Date submissionDate;
    private Date activationDate;
    private Long daysToActivate;
    private String firstInvoice;
    private Double marginOnInvoice;

    @Override
    public String toString() {
        return "CustomerActivationVo [customerCode=" + customerCode + ", customerName=" + customerName + ", saleRepName=" + saleRepName + ", submissionDate=" + submissionDate + ", activationDate=" + activationDate + ", daysToActivate=" + daysToActivate + ", firstInvoice=" + firstInvoice + ", marginOnInvoice=" + marginOnInvoice + "]";
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

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getDaysToActivate() {
        return daysToActivate;
    }

    public void setDaysToActivate(Long daysToActivate) {
        this.daysToActivate = daysToActivate;
    }

    public String getFirstInvoice() {
        return firstInvoice;
    }

    public void setFirstInvoice(String firstInvoice) {
        this.firstInvoice = firstInvoice;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginOnInvoice() {
        return marginOnInvoice;
    }

    public void setMarginOnInvoice(Double marginOnInvoice) {
        this.marginOnInvoice = marginOnInvoice;
    }
}

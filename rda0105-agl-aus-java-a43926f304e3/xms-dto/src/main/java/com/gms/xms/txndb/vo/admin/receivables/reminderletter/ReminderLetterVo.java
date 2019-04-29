package com.gms.xms.txndb.vo.admin.receivables.reminderletter;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * Posted from Mar 28, 2016 10:57:05 AM
 * <p>
 * Author dattrinh
 */
public class ReminderLetterVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long invoiceId;
    private String invoiceCode;
    private Date invoiceDate;
    private String customerCode;
    private String emailCustomerCode;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getEmailCustomerCode() {
        return emailCustomerCode;
    }

    public void setEmailCustomerCode(String emailCustomerCode) {
        this.emailCustomerCode = emailCustomerCode;
    }

    @Override
    public String toString() {
        return "ReminderLetterVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", customerCode=" + customerCode + ", emailCustomerCode=" + emailCustomerCode + "]";
    }
}

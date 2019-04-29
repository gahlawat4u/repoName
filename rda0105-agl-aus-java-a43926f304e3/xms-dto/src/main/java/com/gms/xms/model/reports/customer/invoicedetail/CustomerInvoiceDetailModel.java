package com.gms.xms.model.reports.customer.invoicedetail;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerInvoiceDetailModel.java
 * <p>
 * Author dattrinh 2:12:42 PM
 */
public class CustomerInvoiceDetailModel extends BaseModel {

    private static final long serialVersionUID = -5237051944343935156L;

    private String invoiceId;
    private String invoiceCode;
    private String customerName;
    private String invoiceDate;
    private String invoiceAmount;
    private String invoiceCredit;
    private String netAmount;
    private String gst;
    private String creditGst;
    private String netGst;
    private String total;

    @Override
    public String toString() {
        return "CustomerInvoiceDetailModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", customerName=" + customerName + ", invoiceDate=" + invoiceDate + ", invoiceAmount=" + invoiceAmount + ", invoiceCredit=" + invoiceCredit + ", netAmount=" + netAmount + ", gst=" + gst + ", creditGst=" + creditGst + ", netGst=" + netGst + ", total=" + total + "]";
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceCredit() {
        return invoiceCredit;
    }

    public void setInvoiceCredit(String invoiceCredit) {
        this.invoiceCredit = invoiceCredit;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCreditGst() {
        return creditGst;
    }

    public void setCreditGst(String creditGst) {
        this.creditGst = creditGst;
    }

    public String getNetGst() {
        return netGst;
    }

    public void setNetGst(String netGst) {
        this.netGst = netGst;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

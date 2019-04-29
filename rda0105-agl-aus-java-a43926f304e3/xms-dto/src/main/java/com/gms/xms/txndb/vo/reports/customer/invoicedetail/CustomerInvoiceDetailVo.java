package com.gms.xms.txndb.vo.reports.customer.invoicedetail;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from CustomerInvoiceDetailVo.java
 * <p>
 * Author dattrinh 10:24:07 AM
 */
public class CustomerInvoiceDetailVo extends BaseVo {

    private static final long serialVersionUID = -5237051944343935156L;

    private Long invoiceId;
    private String invoiceCode;
    private String customerName;
    private Date invoiceDate;
    private Double invoiceAmount;
    private Double invoiceCredit;
    private Double netAmount;
    private Double gst;
    private Double creditGst;
    private Double netGst;
    private Double total;

    @Override
    public String toString() {
        return "CustomerInvoiceDetailVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", customerName=" + customerName + ", invoiceDate=" + invoiceDate + ", invoiceAmount=" + invoiceAmount + ", invoiceCredit=" + invoiceCredit + ", netAmount=" + netAmount + ", gst=" + gst + ", creditGst=" + creditGst + ", netGst=" + netGst + ", total=" + total + "]";
    }

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getInvoiceCredit() {
        return invoiceCredit;
    }

    public void setInvoiceCredit(Double invoiceCredit) {
        this.invoiceCredit = invoiceCredit;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCreditGst() {
        return creditGst;
    }

    public void setCreditGst(Double creditGst) {
        this.creditGst = creditGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNetGst() {
        return netGst;
    }

    public void setNetGst(Double netGst) {
        this.netGst = netGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}

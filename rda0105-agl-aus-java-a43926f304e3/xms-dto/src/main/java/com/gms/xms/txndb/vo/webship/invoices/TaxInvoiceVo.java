package com.gms.xms.txndb.vo.webship.invoices;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from TaxInvoiceVo
 * <p>
 * Author DatTV Date Jul 12, 2015 12:13:03 AM
 */
public class TaxInvoiceVo extends BaseVo {

    private static final long serialVersionUID = 7013992070121737363L;

    private Long invoiceId;
    private String invoiceCode;
    private Date invoiceDate;
    private Long customerCode;
    private Integer airbillCount;
    private Date dueDate;
    private Double totalAmount;
    private Double totalPayment;
    private Double invoiceLateFee;
    private Double ifNotPaidByDue;

    @Override
    public String toString() {
        return "TaxInvoiceVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", customerCode=" + customerCode + ", airbillCount=" + airbillCount + ", dueDate=" + dueDate + ", totalAmount=" + totalAmount + ", totalPayment=" + totalPayment + ", invoiceLateFee=" + invoiceLateFee + ", ifNotPaidByDue=" + ifNotPaidByDue + "]";
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

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getAirbillCount() {
        return airbillCount;
    }

    public void setAirbillCount(Integer airbillCount) {
        this.airbillCount = airbillCount;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Double getInvoiceLateFee() {
        return invoiceLateFee;
    }

    public void setInvoiceLateFee(Double invoiceLateFee) {
        this.invoiceLateFee = invoiceLateFee;
    }

    public Double getIfNotPaidByDue() {
        return ifNotPaidByDue;
    }

    public void setIfNotPaidByDue(Double ifNotPaidByDue) {
        this.ifNotPaidByDue = ifNotPaidByDue;
    }
}

package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from ManageCustomerInvoiceVo
 * <p>
 * Author DatTV Sep 24, 2015
 */
public class ManageCustomerInvoiceVo extends BaseVo {

    private static final long serialVersionUID = -758996843402324961L;

    private Long invoiceId;
    private String invoiceCode;
    private Double total;
    private Double lateFee;
    private Double paid;
    private Double owed;
    private Date invoiceDate;
    private Date dueDate;
    private Integer overDue;
    private Integer airbillCount;
    private Double totalCost;
    private Integer status;

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

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getLateFee() {
        return lateFee;
    }

    public void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getOwed() {
        return owed;
    }

    public void setOwed(Double owed) {
        this.owed = owed;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getDueDate() {
        return dueDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getOverDue() {
        return overDue;
    }

    public void setOverDue(Integer overDue) {
        this.overDue = overDue;
    }

    public Integer getAirbillCount() {
        return airbillCount;
    }

    public void setAirbillCount(Integer airbillCount) {
        this.airbillCount = airbillCount;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ManageCustomerInvoiceVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", total=" + total + ", lateFee=" + lateFee + ", paid=" + paid + ", owed=" + owed + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", overDue=" + overDue + ", airbillCount=" + airbillCount + ", totalCost=" + totalCost + ", status=" + status + "]";
    }
}

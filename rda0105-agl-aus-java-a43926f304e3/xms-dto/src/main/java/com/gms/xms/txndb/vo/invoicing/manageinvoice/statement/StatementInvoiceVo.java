package com.gms.xms.txndb.vo.invoicing.manageinvoice.statement;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from StatementInvoiceVo
 * <p>
 * Author dattrinh Mar 16, 2016 4:17:51 PM
 */
public class StatementInvoiceVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long invoiceId;
    private String invoiceCode;
    private Date invoiceDate;
    private Date dueDate;
    private Double invoiceAmount;
    private Double lateFee;
    private Double invoiceTotal;
    private Double totalPaid;
    private Double remainingDue;

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

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getLateFee() {
        return lateFee;
    }

    public void setLateFee(Double lateFee) {
        this.lateFee = lateFee;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(Double invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRemainingDue() {
        return remainingDue;
    }

    public void setRemainingDue(Double remainingDue) {
        this.remainingDue = remainingDue;
    }

    @Override
    public String toString() {
        return "StatementInvoiceVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", dueDate=" + dueDate + ", invoiceAmount=" + invoiceAmount + ", lateFee=" + lateFee + ", invoiceTotal=" + invoiceTotal + ", totalPaid=" + totalPaid + ", remainingDue=" + remainingDue + "]";
    }
}

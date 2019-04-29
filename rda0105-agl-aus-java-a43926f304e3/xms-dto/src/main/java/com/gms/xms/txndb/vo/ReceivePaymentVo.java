package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Posted from ReceivePaymentVo
 * <p>
 * Author DatTV Date Apr 16, 2015 3:20:44 PM
 */
public class ReceivePaymentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String customerOrInvoiceCode;
    private String customerName;
    private String invoiceCode;
    private Long customerCode;
    private Integer paymentType;
    private String cheque;
    private BigDecimal amount;
    private Long bankId;
    private Date paymentDate;
    private String note;
    private BigDecimal appliedAmount;
    private BigDecimal unappliedAmount;
    private String submitType;
    private BigDecimal totalOverpayment;
    private String searchOption;

    public String getCustomerOrInvoiceCode() {
        return customerOrInvoiceCode;
    }

    public void setCustomerOrInvoiceCode(String customerOrInvoiceCode) {
        this.customerOrInvoiceCode = customerOrInvoiceCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getPaymentDate() {
        return paymentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getUnappliedAmount() {
        return unappliedAmount;
    }

    public void setUnappliedAmount(BigDecimal unappliedAmount) {
        this.unappliedAmount = unappliedAmount;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalOverpayment() {
        return totalOverpayment;
    }

    public void setTotalOverpayment(BigDecimal totalOverpayment) {
        this.totalOverpayment = totalOverpayment;
    }

    public String getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(String searchOption) {
        this.searchOption = searchOption;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "ReceivePaymentVo [customerOrInvoiceCode=" + customerOrInvoiceCode + ", customerName=" + customerName + ", invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", paymentType=" + paymentType + ", cheque=" + cheque + ", amount=" + amount + ", bankId=" + bankId + ", paymentDate=" + paymentDate + ", note=" + note + ", appliedAmount=" + appliedAmount + ", unappliedAmount=" + unappliedAmount + ", submitType=" + submitType + ", totalOverpayment=" + totalOverpayment
                + ", searchOption=" + searchOption + "]";
    }
}
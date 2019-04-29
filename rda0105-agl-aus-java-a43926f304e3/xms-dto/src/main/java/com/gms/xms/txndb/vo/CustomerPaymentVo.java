package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Posted from CustomerPaymentVo
 * <p>
 * Author DatTV Date Apr 8, 2015 5:03:27 PM
 */
public class CustomerPaymentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private Long cusPaymentId;

    private Long customerCode;

    private BigDecimal amount;

    private String cheque;

    private Long bankId;

    private Date paymentDate;

    private String cimPaymentTransactionId;

    private Integer paymentType;

    private List<InvoicePaymentVo> invoicePayments;

    private List<NoteVo> notes;

    public Long getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(Long cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getPaymentDate() {
        return paymentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCimPaymentTransactionId() {
        return cimPaymentTransactionId;
    }

    public void setCimPaymentTransactionId(String cimPaymentTransactionId) {
        this.cimPaymentTransactionId = cimPaymentTransactionId;
    }

    public List<InvoicePaymentVo> getInvoicePayments() {
        return invoicePayments;
    }

    public void setInvoicePayments(List<InvoicePaymentVo> invoicePayments) {
        this.invoicePayments = invoicePayments;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public List<NoteVo> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteVo> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CustomerPaymentVo [cusPaymentId=" + cusPaymentId + ", customerCode=" + customerCode + ", amount=" + amount + ", cheque=" + cheque + ", bankId=" + bankId + ", paymentDate=" + paymentDate + ", cimPaymentTransactionId=" + cimPaymentTransactionId + ", paymentType=" + paymentType + ", invoicePayments=" + invoicePayments + ", notes=" + notes + "]";
    }
}
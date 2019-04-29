package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Posted from InvoicePaymentVo
 * <p>
 * Author DatTV Date Apr 8, 2015 5:08:44 PM
 */
public class InvoicePaymentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private Long invoicePaymentId;

    private Long invoiceId;

    private String invoiceCode;

    private BigDecimal amount;

    private BigDecimal lateFee;

    private Long cusPaymentId;

    private Byte reverseFlag;

    private Long revInvoicePaymentId;

    private Date applyDate;

    private CustomerPaymentVo customerPayment;

    private List<InvoicePaymentDetailVo> invoicePaymentDetails;

    private Integer canReverse;

    private BigDecimal remainningBalance;

    @Override
    public String toString() {
        return "InvoicePaymentVo [invoicePaymentId=" + invoicePaymentId + ", invoiceId=" + invoiceId + ", amount=" + amount + ", lateFee=" + lateFee + ", cusPaymentId=" + cusPaymentId + ", reverseFlag=" + reverseFlag + ", revInvoicePaymentId=" + revInvoicePaymentId + ", applyDate=" + applyDate + ", invoicePaymentDetails=" + invoicePaymentDetails + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((cusPaymentId == null) ? 0 : cusPaymentId.hashCode());
        result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
        result = prime * result + ((invoicePaymentId == null) ? 0 : invoicePaymentId.hashCode());
        result = prime * result + ((lateFee == null) ? 0 : lateFee.hashCode());
        result = prime * result + ((revInvoicePaymentId == null) ? 0 : revInvoicePaymentId.hashCode());
        result = prime * result + ((reverseFlag == null) ? 0 : reverseFlag.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InvoicePaymentVo other = (InvoicePaymentVo) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (cusPaymentId == null) {
            if (other.cusPaymentId != null)
                return false;
        } else if (!cusPaymentId.equals(other.cusPaymentId))
            return false;
        if (invoiceId == null) {
            if (other.invoiceId != null)
                return false;
        } else if (!invoiceId.equals(other.invoiceId))
            return false;
        if (invoicePaymentId == null) {
            if (other.invoicePaymentId != null)
                return false;
        } else if (!invoicePaymentId.equals(other.invoicePaymentId))
            return false;
        if (lateFee == null) {
            if (other.lateFee != null)
                return false;
        } else if (!lateFee.equals(other.lateFee))
            return false;
        if (revInvoicePaymentId == null) {
            if (other.revInvoicePaymentId != null)
                return false;
        } else if (!revInvoicePaymentId.equals(other.revInvoicePaymentId))
            return false;
        if (reverseFlag == null) {
            if (other.reverseFlag != null)
                return false;
        } else if (!reverseFlag.equals(other.reverseFlag))
            return false;
        return true;
    }

    public Long getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(Long invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public Long getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(Long cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public Byte getReverseFlag() {
        return reverseFlag;
    }

    public void setReverseFlag(Byte reverseFlag) {
        this.reverseFlag = reverseFlag;
    }

    public Long getRevInvoicePaymentId() {
        return revInvoicePaymentId;
    }

    public void setRevInvoicePaymentId(Long revInvoicePaymentId) {
        this.revInvoicePaymentId = revInvoicePaymentId;
    }

    public List<InvoicePaymentDetailVo> getInvoicePaymentDetails() {
        return invoicePaymentDetails;
    }

    public void setInvoicePaymentDetails(List<InvoicePaymentDetailVo> invoicePaymentDetails) {
        this.invoicePaymentDetails = invoicePaymentDetails;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getApplyDate() {
        return applyDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public CustomerPaymentVo getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(CustomerPaymentVo customerPayment) {
        this.customerPayment = customerPayment;
    }

    public Integer getCanReverse() {
        return canReverse;
    }

    public void setCanReverse(Integer canReverse) {
        this.canReverse = canReverse;
    }

    public BigDecimal getRemainningBalance() {
        return remainningBalance;
    }

    public void setRemainningBalance(BigDecimal remainningBalance) {
        this.remainningBalance = remainningBalance;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
}
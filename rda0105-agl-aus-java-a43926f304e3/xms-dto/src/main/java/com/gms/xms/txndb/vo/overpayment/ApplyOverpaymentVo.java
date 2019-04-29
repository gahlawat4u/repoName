package com.gms.xms.txndb.vo.overpayment;

import com.gms.xms.txndb.vo.BaseVo;

import java.math.BigDecimal;

/**
 * Posted from ApplyOverpaymentVo
 * <p>
 * Author DatTV Date Apr 27, 2015 3:14:09 PM
 */
public class ApplyOverpaymentVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private String customerName;
    private String source;
    private Long cusPaymentId;
    private BigDecimal amountToApply;
    private BigDecimal unappliedAmount;
    private BigDecimal appliedAmount;

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

    public Long getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(Long cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public BigDecimal getAmountToApply() {
        return amountToApply;
    }

    public void setAmountToApply(BigDecimal amountToApply) {
        this.amountToApply = amountToApply;
    }

    public BigDecimal getUnappliedAmount() {
        return unappliedAmount;
    }

    public void setUnappliedAmount(BigDecimal unappliedAmount) {
        this.unappliedAmount = unappliedAmount;
    }

    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ApplyOverpaymentVo [customerCode=" + customerCode + ", customerName=" + customerName + ", source=" + source + ", cusPaymentId=" + cusPaymentId + ", amountToApply=" + amountToApply + ", unappliedAmount=" + unappliedAmount + ", appliedAmount=" + appliedAmount + "]";
    }
}
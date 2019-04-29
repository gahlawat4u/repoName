package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

/**
 * Posted from OverpaymentVo
 * <p>
 * Author DatTV Date Apr 23, 2015 11:23:30 AM
 */
public class OverpaymentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private Long cusPaymentId;

    private BigDecimal overAmount;

    private Integer countOverPay;

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getOverAmount() {
        return overAmount;
    }

    public void setOverAmount(BigDecimal overAmount) {
        this.overAmount = overAmount;
    }

    public Long getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(Long cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public Integer getCountOverPay() {
        return countOverPay;
    }

    public void setCountOverPay(Integer countOverPay) {
        this.countOverPay = countOverPay;
    }

    @Override
    public String toString() {
        return "OverpaymentVo [cusPaymentId=" + cusPaymentId + ", overAmount=" + overAmount + ", countOverPay=" + countOverPay + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countOverPay == null) ? 0 : countOverPay.hashCode());
        result = prime * result + ((cusPaymentId == null) ? 0 : cusPaymentId.hashCode());
        result = prime * result + ((overAmount == null) ? 0 : overAmount.hashCode());
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
        OverpaymentVo other = (OverpaymentVo) obj;
        if (countOverPay == null) {
            if (other.countOverPay != null)
                return false;
        } else if (!countOverPay.equals(other.countOverPay))
            return false;
        if (cusPaymentId == null) {
            if (other.cusPaymentId != null)
                return false;
        } else if (!cusPaymentId.equals(other.cusPaymentId))
            return false;
        if (overAmount == null) {
            if (other.overAmount != null)
                return false;
        } else if (!overAmount.equals(other.overAmount))
            return false;
        return true;
    }

}
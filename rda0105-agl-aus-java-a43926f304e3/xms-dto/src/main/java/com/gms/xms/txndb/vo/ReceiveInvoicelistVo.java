package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

/**
 * Posted from Jun 8, 2016 3:59:19 PM
 * <p>
 * Author huynd
 */
public class ReceiveInvoicelistVo extends InvoiceVo {

    private static final long serialVersionUID = 1L;

    private Integer detailCount;
    private Double detailAmount;
    private Double customerCost;
    private Double paymentPaid;
    private Long cusPaymentId;
    private BigDecimal remain;

    public Integer getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Integer detailCount) {
        this.detailCount = detailCount;
    }

    public Double getDetailAmount() {
        return detailAmount;
    }

    public void setDetailAmount(Double detailAmount) {
        this.detailAmount = detailAmount;
    }

    public Double getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(Double customerCost) {
        this.customerCost = customerCost;
    }

    public Double getPaymentPaid() {
        return paymentPaid;
    }

    public void setPaymentPaid(Double paymentPaid) {
        this.paymentPaid = paymentPaid;
    }

    public Long getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(Long cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getRemain() {
        return remain;
    }

    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }

    @Override
    public String toString() {
        return "ReceiveInvoicelistVo [detailCount=" + detailCount + ", detailAmount=" + detailAmount + ", customerCost=" + customerCost + ", paymentPaid=" + paymentPaid + ", cusPaymentId=" + cusPaymentId + ", remain=" + remain + "]";
    }

}

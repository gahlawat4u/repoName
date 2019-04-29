package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

/**
 * Posted from Jun 9, 2016 9:29:40 AM
 * <p>
 * Author huynd
 */
public class ShipmentDetailInvoiceVo extends InvoiceVo {

    private static final long serialVersionUID = 1L;

    private Long shipmentId;
    private String airbillNumber;
    private Double customerCost;
    private Double paymentPaid;
    private BigDecimal remain;
    private Double adjCredit;
    private Double penAdj;
    private Double denied;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
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

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getRemain() {
        return remain;
    }

    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }

    public Double getAdjCredit() {
        return adjCredit;
    }

    public void setAdjCredit(Double adjCredit) {
        this.adjCredit = adjCredit;
    }

    public Double getPenAdj() {
        return penAdj;
    }

    public void setPenAdj(Double penAdj) {
        this.penAdj = penAdj;
    }

    public Double getDenied() {
        return denied;
    }

    public void setDenied(Double denied) {
        this.denied = denied;
    }

    @Override
    public String toString() {
        return "ShipmentDetailInvoiceVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", customerCost=" + customerCost + ", paymentPaid=" + paymentPaid + ", remain=" + remain + ", adjCredit=" + adjCredit + ", penAdj=" + penAdj + ", denied=" + denied + "]";
    }

}

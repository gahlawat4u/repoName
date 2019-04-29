package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;

/**
 * Posted from InvoicePaymentDetailVo
 * <p>
 * Author DatTV Date Apr 8, 2015 5:11:19 PM
 */
public class InvoicePaymentDetailVo extends BaseVo {
    private static final long serialVersionUID = -3288278730257113173L;

    private Long invoicePaymentId;

    private Long shipmentId;

    private String airbillNumber;

    private BigDecimal amount;

    @Override
    public String toString() {
        return "InvoicePaymentDetailVo [invoicePaymentId=" + invoicePaymentId + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", amount=" + amount + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((invoicePaymentId == null) ? 0 : invoicePaymentId.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
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
        InvoicePaymentDetailVo other = (InvoicePaymentDetailVo) obj;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (invoicePaymentId == null) {
            if (other.invoicePaymentId != null)
                return false;
        } else if (!invoicePaymentId.equals(other.invoicePaymentId))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }

    public Long getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(Long invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

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

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
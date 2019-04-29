package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.List;

/**
 * Posted from ShipmentInvoiceVo
 * <p>
 * Author DatTV Date Apr 9, 2015 9:46:04 AM
 */
public class ShipmentInvoiceVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private Long invoiceId;

    private Long shipmentId;

    private String airbillNumber;

    private String cusPaymentId;

    private BigDecimal paidCustomerCost;

    private BigDecimal paidCarrierCost;

    private String invoicePaymentId;

    private BigDecimal totalAmount;

    private BigDecimal totalPayment;

    private BigDecimal amountDue;

    private BigDecimal adjustmentCredit;

    private BigDecimal pendingAdjustment;

    private BigDecimal deniedAdjustment;

    private BigDecimal payment;

    private List<ShipmentBillingVo> shipmentBillings;

    public List<ShipmentBillingVo> getShipmentBillings() {
        return shipmentBillings;
    }

    public void setShipmentBillings(List<ShipmentBillingVo> shipmentBillings) {
        this.shipmentBillings = shipmentBillings;
    }

    @Override
    public String toString() {
        return "ShipmentInvoiceVo [invoiceId=" + invoiceId + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", cusPaymentId=" + cusPaymentId + ", paidCustomerCost=" + paidCustomerCost + ", paidCarrierCost=" + paidCarrierCost + ", invoicePaymentId=" + invoicePaymentId + ", totalAmount=" + totalAmount + ", totalPayment=" + totalPayment + ", amountDue=" + amountDue + ", adjustmentCredit=" + adjustmentCredit + ", pendingAdjustment=" + pendingAdjustment + ", deniedAdjustment="
                + deniedAdjustment + ", payment=" + payment + ", shipmentBillings=" + shipmentBillings + "]";
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
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

    public String getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(String cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getPaidCustomerCost() {
        return paidCustomerCost;
    }

    public void setPaidCustomerCost(BigDecimal paidCustomerCost) {
        this.paidCustomerCost = paidCustomerCost;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getPaidCarrierCost() {
        return paidCarrierCost;
    }

    public void setPaidCarrierCost(BigDecimal paidCarrierCost) {
        this.paidCarrierCost = paidCarrierCost;
    }

    public String getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(String invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAdjustmentCredit() {
        return adjustmentCredit;
    }

    public void setAdjustmentCredit(BigDecimal adjustmentCredit) {
        this.adjustmentCredit = adjustmentCredit;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getPendingAdjustment() {
        return pendingAdjustment;
    }

    public void setPendingAdjustment(BigDecimal pendingAdjustment) {
        this.pendingAdjustment = pendingAdjustment;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getDeniedAdjustment() {
        return deniedAdjustment;
    }

    public void setDeniedAdjustment(BigDecimal deniedAdjustment) {
        this.deniedAdjustment = deniedAdjustment;
    }

    public void setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }

    public BigDecimal getAmountDue() {
        BigDecimal total = totalAmount == null ? BigDecimal.ZERO : totalAmount;
        BigDecimal payment = totalPayment == null ? BigDecimal.ZERO : totalPayment;
        BigDecimal due = total.subtract(payment);
        return due.compareTo(BigDecimal.ZERO) == 1 ? due : BigDecimal.ZERO;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adjustmentCredit == null) ? 0 : adjustmentCredit.hashCode());
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((amountDue == null) ? 0 : amountDue.hashCode());
        result = prime * result + ((cusPaymentId == null) ? 0 : cusPaymentId.hashCode());
        result = prime * result + ((deniedAdjustment == null) ? 0 : deniedAdjustment.hashCode());
        result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
        result = prime * result + ((invoicePaymentId == null) ? 0 : invoicePaymentId.hashCode());
        result = prime * result + ((paidCarrierCost == null) ? 0 : paidCarrierCost.hashCode());
        result = prime * result + ((paidCustomerCost == null) ? 0 : paidCustomerCost.hashCode());
        result = prime * result + ((payment == null) ? 0 : payment.hashCode());
        result = prime * result + ((pendingAdjustment == null) ? 0 : pendingAdjustment.hashCode());
        result = prime * result + ((shipmentBillings == null) ? 0 : shipmentBillings.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
        result = prime * result + ((totalPayment == null) ? 0 : totalPayment.hashCode());
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
        ShipmentInvoiceVo other = (ShipmentInvoiceVo) obj;
        if (adjustmentCredit == null) {
            if (other.adjustmentCredit != null)
                return false;
        } else if (!adjustmentCredit.equals(other.adjustmentCredit))
            return false;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (amountDue == null) {
            if (other.amountDue != null)
                return false;
        } else if (!amountDue.equals(other.amountDue))
            return false;
        if (cusPaymentId == null) {
            if (other.cusPaymentId != null)
                return false;
        } else if (!cusPaymentId.equals(other.cusPaymentId))
            return false;
        if (deniedAdjustment == null) {
            if (other.deniedAdjustment != null)
                return false;
        } else if (!deniedAdjustment.equals(other.deniedAdjustment))
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
        if (paidCarrierCost == null) {
            if (other.paidCarrierCost != null)
                return false;
        } else if (!paidCarrierCost.equals(other.paidCarrierCost))
            return false;
        if (paidCustomerCost == null) {
            if (other.paidCustomerCost != null)
                return false;
        } else if (!paidCustomerCost.equals(other.paidCustomerCost))
            return false;
        if (payment == null) {
            if (other.payment != null)
                return false;
        } else if (!payment.equals(other.payment))
            return false;
        if (pendingAdjustment == null) {
            if (other.pendingAdjustment != null)
                return false;
        } else if (!pendingAdjustment.equals(other.pendingAdjustment))
            return false;
        if (shipmentBillings == null) {
            if (other.shipmentBillings != null)
                return false;
        } else if (!shipmentBillings.equals(other.shipmentBillings))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (totalAmount == null) {
            if (other.totalAmount != null)
                return false;
        } else if (!totalAmount.equals(other.totalAmount))
            return false;
        if (totalPayment == null) {
            if (other.totalPayment != null)
                return false;
        } else if (!totalPayment.equals(other.totalPayment))
            return false;
        return true;
    }
}

package com.gms.xms.model.franchisepayable;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.ShipmentBillingModel;

import java.util.List;

/**
 * Posted from ShipmentInvoiceModel
 * <p>
 * Author DatTV Date Apr 9, 2015 9:45:46 AM
 */
public class ShipmentInvoiceModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String invoiceId;

    private String shipmentId;

    private String airbillNumber;

    private String cusPaymentId;

    private String paidCustomerCost;

    private String paidCarrierCost;

    private String invoicePaymentId;

    private String totalAmount;

    private String totalPayment;

    private String amountDue;

    private String payment;

    private String adjustmentCredit;

    private String pendingAdjustment;

    private String deniedAdjustment;

    private List<ShipmentBillingModel> shipmentBillings;

    public List<ShipmentBillingModel> getShipmentBillings() {
        return shipmentBillings;
    }

    public void setShipmentBillings(List<ShipmentBillingModel> shipmentBillings) {
        this.shipmentBillings = shipmentBillings;
    }

    @Override
    public String toString() {
        return "ShipmentInvoiceModel [invoiceId=" + invoiceId + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", cusPaymentId=" + cusPaymentId + ", paidCustomerCost=" + paidCustomerCost + ", paidCarrierCost=" + paidCarrierCost + ", invoicePaymentId=" + invoicePaymentId + ", totalAmount=" + totalAmount + ", totalPayment=" + totalPayment + ", amountDue=" + amountDue + ", payment=" + payment + ", adjustmentCredit=" + adjustmentCredit + ", pendingAdjustment="
                + pendingAdjustment + ", deniedAdjustment=" + deniedAdjustment + ", shipmentBillings=" + shipmentBillings + "]";
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
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

    public String getPaidCustomerCost() {
        return paidCustomerCost;
    }

    public void setPaidCustomerCost(String paidCustomerCost) {
        this.paidCustomerCost = paidCustomerCost;
    }

    public String getPaidCarrierCost() {
        return paidCarrierCost;
    }

    public void setPaidCarrierCost(String paidCarrierCost) {
        this.paidCarrierCost = paidCarrierCost;
    }

    public String getInvoicePaymentId() {
        return invoicePaymentId;
    }

    public void setInvoicePaymentId(String invoicePaymentId) {
        this.invoicePaymentId = invoicePaymentId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAdjustmentCredit() {
        return adjustmentCredit;
    }

    public void setAdjustmentCredit(String adjustmentCredit) {
        this.adjustmentCredit = adjustmentCredit;
    }

    public String getPendingAdjustment() {
        return pendingAdjustment;
    }

    public void setPendingAdjustment(String pendingAdjustment) {
        this.pendingAdjustment = pendingAdjustment;
    }

    public String getDeniedAdjustment() {
        return deniedAdjustment;
    }

    public void setDeniedAdjustment(String deniedAdjustment) {
        this.deniedAdjustment = deniedAdjustment;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
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
        ShipmentInvoiceModel other = (ShipmentInvoiceModel) obj;
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
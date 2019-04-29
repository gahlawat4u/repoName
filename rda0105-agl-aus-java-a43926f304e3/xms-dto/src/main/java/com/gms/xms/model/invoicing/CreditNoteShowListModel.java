package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CreditNoteShowListModel
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class CreditNoteShowListModel extends BaseModel {
    private static final long serialVersionUID = 6832824278180120620L;
    private String invoiceCode;
    private String creditCode;
    private String airbillNumber;
    private String invoiceDate;
    private String reason;
    private String shipmentAmount;
    private String gstAmount;
    private String adjustmentId;
    private String totalCredit;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getShipmentAmount() {
        return shipmentAmount;
    }

    public void setShipmentAmount(String shipmentAmount) {
        this.shipmentAmount = shipmentAmount;
    }

    public String getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(String gstAmount) {
        this.gstAmount = gstAmount;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(String totalCredit) {
        this.totalCredit = totalCredit;
    }

    @Override
    public String toString() {
        return "CreditNoteShowListModel [invoiceCode=" + invoiceCode + ", creditCode=" + creditCode + ", airbillNumber=" + airbillNumber + ", invoiceDate=" + invoiceDate + ", reason=" + reason + ", shipmentAmount=" + shipmentAmount + ", gstAmount=" + gstAmount + ", adjustmentId=" + adjustmentId + ", totalCredit=" + totalCredit + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adjustmentId == null) ? 0 : adjustmentId.hashCode());
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((creditCode == null) ? 0 : creditCode.hashCode());
        result = prime * result + ((gstAmount == null) ? 0 : gstAmount.hashCode());
        result = prime * result + ((invoiceCode == null) ? 0 : invoiceCode.hashCode());
        result = prime * result + ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
        result = prime * result + ((reason == null) ? 0 : reason.hashCode());
        result = prime * result + ((shipmentAmount == null) ? 0 : shipmentAmount.hashCode());
        result = prime * result + ((totalCredit == null) ? 0 : totalCredit.hashCode());
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
        CreditNoteShowListModel other = (CreditNoteShowListModel) obj;
        if (adjustmentId == null) {
            if (other.adjustmentId != null)
                return false;
        } else if (!adjustmentId.equals(other.adjustmentId))
            return false;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (creditCode == null) {
            if (other.creditCode != null)
                return false;
        } else if (!creditCode.equals(other.creditCode))
            return false;
        if (gstAmount == null) {
            if (other.gstAmount != null)
                return false;
        } else if (!gstAmount.equals(other.gstAmount))
            return false;
        if (invoiceCode == null) {
            if (other.invoiceCode != null)
                return false;
        } else if (!invoiceCode.equals(other.invoiceCode))
            return false;
        if (invoiceDate == null) {
            if (other.invoiceDate != null)
                return false;
        } else if (!invoiceDate.equals(other.invoiceDate))
            return false;
        if (reason == null) {
            if (other.reason != null)
                return false;
        } else if (!reason.equals(other.reason))
            return false;
        if (shipmentAmount == null) {
            if (other.shipmentAmount != null)
                return false;
        } else if (!shipmentAmount.equals(other.shipmentAmount))
            return false;
        if (totalCredit == null) {
            if (other.totalCredit != null)
                return false;
        } else if (!totalCredit.equals(other.totalCredit))
            return false;
        return true;
    }

}

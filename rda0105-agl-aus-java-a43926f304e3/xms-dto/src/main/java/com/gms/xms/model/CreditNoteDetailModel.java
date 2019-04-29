package com.gms.xms.model;

/**
 * Posted from CreditNoteDetailModel
 * <p>
 * Author HungNT Date May 22, 2015
 */
public class CreditNoteDetailModel extends BaseModel {
    private static final long serialVersionUID = -3565223739040436260L;

    private String creditNoteId;

    private String adjustmentId;

    private String cusPaymentId;

    private String creditAirbillNumber;

    private String amount;

    private String gstAmount;

    private String reason;

    public String getCreditNoteId() {
        return creditNoteId;
    }

    public void setCreditNoteId(String creditNoteId) {
        this.creditNoteId = creditNoteId;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(String cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public String getCreditAirbillNumber() {
        return creditAirbillNumber;
    }

    public void setCreditAirbillNumber(String creditAirbillNumber) {
        this.creditAirbillNumber = creditAirbillNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(String gstAmount) {
        this.gstAmount = gstAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adjustmentId == null) ? 0 : adjustmentId.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((creditAirbillNumber == null) ? 0 : creditAirbillNumber.hashCode());
        result = prime * result + ((creditNoteId == null) ? 0 : creditNoteId.hashCode());
        result = prime * result + ((cusPaymentId == null) ? 0 : cusPaymentId.hashCode());
        result = prime * result + ((gstAmount == null) ? 0 : gstAmount.hashCode());
        result = prime * result + ((reason == null) ? 0 : reason.hashCode());
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
        CreditNoteDetailModel other = (CreditNoteDetailModel) obj;
        if (adjustmentId == null) {
            if (other.adjustmentId != null)
                return false;
        } else if (!adjustmentId.equals(other.adjustmentId))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (creditAirbillNumber == null) {
            if (other.creditAirbillNumber != null)
                return false;
        } else if (!creditAirbillNumber.equals(other.creditAirbillNumber))
            return false;
        if (creditNoteId == null) {
            if (other.creditNoteId != null)
                return false;
        } else if (!creditNoteId.equals(other.creditNoteId))
            return false;
        if (cusPaymentId == null) {
            if (other.cusPaymentId != null)
                return false;
        } else if (!cusPaymentId.equals(other.cusPaymentId))
            return false;
        if (gstAmount == null) {
            if (other.gstAmount != null)
                return false;
        } else if (!gstAmount.equals(other.gstAmount))
            return false;
        if (reason == null) {
            if (other.reason != null)
                return false;
        } else if (!reason.equals(other.reason))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CreditNoteDetailModel [creditNoteId=" + creditNoteId + ", adjustmentId=" + adjustmentId + ", cusPaymentId=" + cusPaymentId + ", creditAirbillNumber=" + creditAirbillNumber + ", amount=" + amount + ", gstAmount=" + gstAmount + ", reason=" + reason + "]";
    }
}

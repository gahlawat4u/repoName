package com.gms.xms.txndb.vo;

/**
 * Posted from CreditNoteDetailVo
 * <p>
 * Author DatTV Date May 20, 2015 3:27:07 PM
 */
public class CreditNoteDetailVo extends BaseVo {
    private static final long serialVersionUID = 7513340316324427798L;

    private Long creditNoteId;

    private Long adjustmentId;

    private Long cusPaymentId;

    private String creditAirbillNumber;

    private Double amount;

    private Double gstAmount;

    private String reason;

    public Long getCreditNoteId() {
        return creditNoteId;
    }

    public void setCreditNoteId(Long creditNoteId) {
        this.creditNoteId = creditNoteId;
    }

    public Long getAdjustmentid() {
        return adjustmentId;
    }

    public void setAdjustmentid(Long adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public Long getCusPaymentid() {
        return cusPaymentId;
    }

    public void setCusPaymentid(Long cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public String getCreditAirbillNumber() {
        return creditAirbillNumber;
    }

    public void setCreditAirbillNumber(String creditAirbillNumber) {
        this.creditAirbillNumber = creditAirbillNumber == null ? null : creditAirbillNumber.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(Double gstAmount) {
        this.gstAmount = gstAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    @Override
    public String toString() {
        return "CreditNoteDetailVo [creditNoteId=" + creditNoteId + ", adjustmentId=" + adjustmentId + ", cusPaymentId=" + cusPaymentId + ", creditAirbillNumber=" + creditAirbillNumber + ", amount=" + amount + ", gstAmount=" + gstAmount + ", reason=" + reason + "]";
    }
}
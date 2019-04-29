package com.gms.xms.model.invoicing;

import com.gms.xms.model.BaseModel;

public class AirbillCreditNoteModel extends BaseModel {
    private static final long serialVersionUID = 9020422414191761951L;
    private String adjustmentId;
    private String adjustmentType;
    private String shipmentId;
    private String cusPaymentId;
    private String reason;
    private String credit_note_status;
    private String invoiceId;
    private String customerCode;
    private String creditCode;
    private String customerAmount;
    private String gstCustomerAmount;
    private String totalAmount;
    private String awbTotal;
    private String awbPaid;
    private String adjustmentStatus;
    private String airbillNumber;
    private String cusPaymentid;
    private String creditType;

    @Override
    public String toString() {
        return "AirbillCreditNoteModel [adjustmentId=" + adjustmentId + ", adjustmentType=" + adjustmentType + ", shipmentId=" + shipmentId + ", cusPaymentId=" + cusPaymentId + ", reason=" + reason + ", credit_note_status=" + credit_note_status + ", invoiceId=" + invoiceId + ", customerCode=" + customerCode + ", creditCode=" + creditCode + ", customerAmount=" + customerAmount + ", gstCustomerAmount=" + gstCustomerAmount + ", totalAmount=" + totalAmount + ", awbTotal=" + awbTotal + ", awbPaid="
                + awbPaid + ", adjustmentStatus=" + adjustmentStatus + ", airbillNumber=" + airbillNumber + ", cusPaymentid=" + cusPaymentid + ", creditType=" + creditType + "]";
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(String cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCredit_note_status() {
        return credit_note_status;
    }

    public void setCredit_note_status(String credit_note_status) {
        this.credit_note_status = credit_note_status;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(String customerAmount) {
        this.customerAmount = customerAmount;
    }

    public String getGstCustomerAmount() {
        return gstCustomerAmount;
    }

    public void setGstCustomerAmount(String gstCustomerAmount) {
        this.gstCustomerAmount = gstCustomerAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAwbTotal() {
        return awbTotal;
    }

    public void setAwbTotal(String awbTotal) {
        this.awbTotal = awbTotal;
    }

    public String getAwbPaid() {
        return awbPaid;
    }

    public void setAwbPaid(String awbPaid) {
        this.awbPaid = awbPaid;
    }

    public String getAdjustmentStatus() {
        return adjustmentStatus;
    }

    public void setAdjustmentStatus(String adjustmentStatus) {
        this.adjustmentStatus = adjustmentStatus;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCusPaymentid() {
        return cusPaymentid;
    }

    public void setCusPaymentid(String cusPaymentid) {
        this.cusPaymentid = cusPaymentid;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }
}
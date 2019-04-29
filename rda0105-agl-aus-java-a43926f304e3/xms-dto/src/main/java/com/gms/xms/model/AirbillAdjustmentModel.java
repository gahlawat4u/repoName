package com.gms.xms.model;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Posted from AirbillAdjustmentModel
 * <p>
 * Author DatTV Date May 12, 2015 2:00:24 PM
 */
public class AirbillAdjustmentModel extends BaseModel {

    private static final long serialVersionUID = 207393189399903949L;

    private String adjustmentId;

    private String adjustmentType;

    private String shipmentId;

    private String airbillNumber;

    private String createDate;

    private String carrierAmount;

    private String gstCarrierAmount;

    private String customerAmount;

    private String gstCustomerAmount;

    private String carrierCredit;

    private String gstCarrierCredit;

    private String requestCarrier;

    private String creditType;

    private String applyGstType;

    private String status;

    private String actualDeliveryDate;

    private String franchiseAmount;

    private String gstFranchiseAmount;

    private String note;

    private String reasonForDeleting;

    private String franchiseCommentsToFsc;

    private String fscCreditNote;

    private String subStatus;

    private String startPausingDate;

    private String pausingDay;

    public String getReasonForDeleting() {
        return reasonForDeleting;
    }

    public void setReasonForDeleting(String reasonForDeleting) {
        this.reasonForDeleting = reasonForDeleting;
    }

    public String getFranchiseCommentsToFsc() {
        return franchiseCommentsToFsc;
    }

    public void setFranchiseCommentsToFsc(String franchiseCommentsToFsc) {
        this.franchiseCommentsToFsc = franchiseCommentsToFsc;
    }

    public String getFscCreditNote() {
        return fscCreditNote;
    }

    public void setFscCreditNote(String fscCreditNote) {
        this.fscCreditNote = fscCreditNote;
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

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCarrierAmount() {
        return carrierAmount;
    }

    public void setCarrierAmount(String carrierAmount) {
        this.carrierAmount = carrierAmount;
    }

    public String getGstCarrierAmount() {
        return gstCarrierAmount;
    }

    public void setGstCarrierAmount(String gstCarrierAmount) {
        this.gstCarrierAmount = gstCarrierAmount;
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

    public String getCarrierCredit() {
        return carrierCredit;
    }

    public void setCarrierCredit(String carrierCredit) {
        this.carrierCredit = carrierCredit;
    }

    public String getGstCarrierCredit() {
        return gstCarrierCredit;
    }

    public void setGstCarrierCredit(String gstCarrierCredit) {
        this.gstCarrierCredit = gstCarrierCredit;
    }

    public String getRequestCarrier() {
        return requestCarrier;
    }

    public void setRequestCarrier(String requestCarrier) {
        this.requestCarrier = requestCarrier;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getApplyGstType() {
        return applyGstType;
    }

    public void setApplyGstType(String applyGstType) {
        this.applyGstType = applyGstType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getFranchiseAmount() {
        return franchiseAmount;
    }

    public void setFranchiseAmount(String franchiseAmount) {
        this.franchiseAmount = franchiseAmount;
    }

    public String getGstFranchiseAmount() {
        return gstFranchiseAmount;
    }

    public void setGstFranchiseAmount(String gstFranchiseAmount) {
        this.gstFranchiseAmount = gstFranchiseAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    public String getStartPausingDate() {
        return startPausingDate;
    }

    public void setStartPausingDate(String startPausingDate) {
        this.startPausingDate = startPausingDate;
    }

    @JsonIgnore
    public String getCreditTypeName() {
        String creditTypeName = "";
        switch (this.getCreditType()) {
            case "0":
                creditTypeName = "Upon Carrier approval";
                break;
            case "1":
                creditTypeName = "Credit Now";
                break;
            case "2":
                creditTypeName = "Agl Warranty";
                break;
            default:
                creditTypeName = "Unknown Credit Type";
                break;
        }
        return creditTypeName;
    }

    public String getPausingDay() {
        return pausingDay;
    }

    public void setPausingDay(String pausingDay) {
        this.pausingDay = pausingDay;
    }

    @Override
    public String toString() {
        return "AirbillAdjustmentModel [adjustmentId=" + adjustmentId + ", adjustmentType=" + adjustmentType + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", createDate=" + createDate + ", carrierAmount=" + carrierAmount + ", gstCarrierAmount=" + gstCarrierAmount + ", customerAmount=" + customerAmount + ", gstCustomerAmount=" + gstCustomerAmount + ", carrierCredit=" + carrierCredit + ", gstCarrierCredit=" + gstCarrierCredit + ", requestCarrier=" + requestCarrier
                + ", creditType=" + creditType + ", applyGstType=" + applyGstType + ", status=" + status + ", actualDeliveryDate=" + actualDeliveryDate + ", franchiseAmount=" + franchiseAmount + ", gstFranchiseAmount=" + gstFranchiseAmount + ", note=" + note + ", reasonForDeleting=" + reasonForDeleting + ", franchiseCommentsToFsc=" + franchiseCommentsToFsc + ", fscCreditNote=" + fscCreditNote + ", subStatus=" + subStatus + ", startPausingDate=" + startPausingDate + "]";
    }
}
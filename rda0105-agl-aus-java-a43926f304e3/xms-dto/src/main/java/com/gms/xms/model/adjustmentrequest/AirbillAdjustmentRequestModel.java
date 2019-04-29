package com.gms.xms.model.adjustmentrequest;

import com.gms.xms.model.BaseModel;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Posted from AirbillAdjustmentRequestModel
 * </p>
 *
 * @author hungnt - Nov 2, 2015
 */
public class AirbillAdjustmentRequestModel extends BaseModel {
    private static final long serialVersionUID = -7864411678185831573L;

    private String adjustmentRequestId;

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

    public String getAdjustmentRequestId() {
        return adjustmentRequestId;
    }

    public void setAdjustmentRequestId(String adjustmentRequestId) {
        this.adjustmentRequestId = adjustmentRequestId;
    }

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
        return "0".equalsIgnoreCase(this.creditType) ? "Upon Carrier approval" : "Credit Now";
    }

    public String getPausingDay() {
        return pausingDay;
    }

    public void setPausingDay(String pausingDay) {
        this.pausingDay = pausingDay;
    }

    @Override
    public String toString() {
        return "AirbillAdjustmentRequestModel [adjustmentRequestId=" + adjustmentRequestId + ", adjustmentType=" + adjustmentType + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", createDate=" + createDate + ", carrierAmount=" + carrierAmount + ", gstCarrierAmount=" + gstCarrierAmount + ", customerAmount=" + customerAmount + ", gstCustomerAmount=" + gstCustomerAmount + ", carrierCredit=" + carrierCredit + ", gstCarrierCredit=" + gstCarrierCredit + ", requestCarrier="
                + requestCarrier + ", creditType=" + creditType + ", applyGstType=" + applyGstType + ", status=" + status + ", actualDeliveryDate=" + actualDeliveryDate + ", franchiseAmount=" + franchiseAmount + ", gstFranchiseAmount=" + gstFranchiseAmount + ", note=" + note + ", reasonForDeleting=" + reasonForDeleting + ", franchiseCommentsToFsc=" + franchiseCommentsToFsc + ", fscCreditNote=" + fscCreditNote + ", subStatus=" + subStatus + ", startPausingDate=" + startPausingDate
                + ", pausingDay=" + pausingDay + "]";
    }
}
package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from AirbillAdjustmentVo
 * <p>
 * Author DatTV Date May 12, 2015 2:00:32 PM
 */
public class AirbillAdjustmentVo extends BaseVo {
    private static final long serialVersionUID = 9020422414191761951L;

    private Long adjustmentId;

    private String adjustmentType;

    private Long shipmentId;

    private String airbillNumber;

    private Date createDate;

    private Double carrierAmount;

    private Double gstCarrierAmount;

    private Double customerAmount;

    private Double gstCustomerAmount;

    private Double carrierCredit;

    private Double gstCarrierCredit;

    private Byte requestCarrier;

    private Byte creditType;

    private Byte applyGstType;

    private Byte status;

    private Date actualDeliveryDate;

    private Double franchiseAmount;

    private Double gstFranchiseAmount;

    private String note;

    private String reasonForDeleting;

    private String franchiseCommentsToFsc;

    private String fscCreditNote;

    private Integer subStatus;

    private Date startPausingDate;

    private Long pausingDay;

    public Long getAdjustmentId() {
        return adjustmentId;
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

    public void setAdjustmentId(Long adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
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

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getCarrierAmount() {
        return carrierAmount;
    }

    public void setCarrierAmount(Double carrierAmount) {
        this.carrierAmount = carrierAmount;
    }

    public Double getGstCarrierAmount() {
        return gstCarrierAmount;
    }

    public void setGstCarrierAmount(Double gstCarrierAmount) {
        this.gstCarrierAmount = gstCarrierAmount;
    }

    public Double getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(Double customerAmount) {
        this.customerAmount = customerAmount;
    }

    public Double getGstCustomerAmount() {
        return gstCustomerAmount;
    }

    public void setGstCustomerAmount(Double gstCustomerAmount) {
        this.gstCustomerAmount = gstCustomerAmount;
    }

    public Double getCarrierCredit() {
        return carrierCredit;
    }

    public void setCarrierCredit(Double carrierCredit) {
        this.carrierCredit = carrierCredit;
    }

    public Double getGstCarrierCredit() {
        return gstCarrierCredit;
    }

    public void setGstCarrierCredit(Double gstCarrierCredit) {
        this.gstCarrierCredit = gstCarrierCredit;
    }

    public Byte getRequestCarrier() {
        return requestCarrier;
    }

    public void setRequestCarrier(Byte requestCarrier) {
        this.requestCarrier = requestCarrier;
    }

    public Byte getCreditType() {
        return creditType;
    }

    public void setCreditType(Byte creditType) {
        this.creditType = creditType;
    }

    public Byte getApplyGstType() {
        return applyGstType;
    }

    public void setApplyGstType(Byte applyGstType) {
        this.applyGstType = applyGstType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public Double getFranchiseAmount() {
        return franchiseAmount;
    }

    public void setFranchiseAmount(Double franchiseAmount) {
        this.franchiseAmount = franchiseAmount;
    }

    public Double getGstFranchiseAmount() {
        return gstFranchiseAmount;
    }

    public void setGstFranchiseAmount(Double gstFranchiseAmount) {
        this.gstFranchiseAmount = gstFranchiseAmount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    public Date getStartPausingDate() {
        return startPausingDate;
    }

    public void setStartPausingDate(Date startPausingDate) {
        this.startPausingDate = startPausingDate;
    }

    public Long getPausingDay() {
        return pausingDay;
    }

    public void setPausingDay(Long pausingDay) {
        this.pausingDay = pausingDay;
    }

    /**
     * Create a not null AirbillAdjustmentVo object
     *
     * @return {@link AirbillAdjustmentVo}
     */
    public static AirbillAdjustmentVo createNotNullObject() {
        AirbillAdjustmentVo adjustmentVo = new AirbillAdjustmentVo();
        adjustmentVo.setCarrierAmount(0.00);
        adjustmentVo.setGstCarrierAmount(0.00);
        adjustmentVo.setFranchiseAmount(0.00);
        adjustmentVo.setGstFranchiseAmount(0.00);
        adjustmentVo.setCustomerAmount(0.00);
        adjustmentVo.setGstCustomerAmount(0.00);
        return adjustmentVo;
    }

    /**
     * Remove null values from the adjustment
     *
     * @param adjustment
     * @return {@link AirbillAdjustmentVo}
     */
    public static AirbillAdjustmentVo removeNullValue(AirbillAdjustmentVo adjustment) {
        AirbillAdjustmentVo adjustmentResultVo = new AirbillAdjustmentVo();
        adjustmentResultVo.setCarrierAmount(adjustment.getCarrierAmount() == null ? 0.00 : adjustment.getCarrierAmount());
        adjustmentResultVo.setGstCarrierAmount(adjustment.getGstCarrierAmount() == null ? 0.00 : adjustment.getGstCarrierAmount());
        adjustmentResultVo.setFranchiseAmount(adjustment.getFranchiseAmount() == null ? 0.00 : adjustment.getFranchiseAmount());
        adjustmentResultVo.setGstFranchiseAmount(adjustment.getGstFranchiseAmount() == null ? 0.00 : adjustment.getGstFranchiseAmount());
        adjustmentResultVo.setCustomerAmount(adjustment.getCustomerAmount() == null ? 0.00 : adjustment.getCustomerAmount());
        adjustmentResultVo.setGstCustomerAmount(adjustment.getGstCustomerAmount() == null ? 0.00 : adjustment.getGstCustomerAmount());
        return adjustmentResultVo;
    }

    @Override
    public String toString() {
        return "AirbillAdjustmentVo [adjustmentId=" + adjustmentId + ", adjustmentType=" + adjustmentType + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", createDate=" + createDate + ", carrierAmount=" + carrierAmount + ", gstCarrierAmount=" + gstCarrierAmount + ", customerAmount=" + customerAmount + ", gstCustomerAmount=" + gstCustomerAmount + ", carrierCredit=" + carrierCredit + ", gstCarrierCredit=" + gstCarrierCredit + ", requestCarrier=" + requestCarrier
                + ", creditType=" + creditType + ", applyGstType=" + applyGstType + ", status=" + status + ", actualDeliveryDate=" + actualDeliveryDate + ", franchiseAmount=" + franchiseAmount + ", gstFranchiseAmount=" + gstFranchiseAmount + ", note=" + note + ", reasonForDeleting=" + reasonForDeleting + ", franchiseCommentsToFsc=" + franchiseCommentsToFsc + ", fscCreditNote=" + fscCreditNote + ", subStatus=" + subStatus + ", startPausingDate=" + startPausingDate + ", pausingDay=" + pausingDay
                + "]";
    }

}
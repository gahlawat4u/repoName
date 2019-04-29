package com.gms.xms.txndb.vo;

public class AirbillCreditNoteVo extends BaseVo {
    private static final long serialVersionUID = 9020422414191761951L;
    private Long adjustmentId;
    private String invoiceCode;
    private Long invoiceId;
    private Long customerCode;
    private Long cusPaymentId;
    private Long shipmentId;
    private String airbillNumber;
    private Double customerAmount;
    private Double gstCustomerAmount;
    private Double totalAmount;
    private Double awbTotal;
    private Double awbPaid;
    private Integer creditType;

    // Unuse
    private String adjustmentType;
    private String reason;
    private Integer creditNoteStatus;
    private String creditCode;
    private Byte adjustmentStatus;

    @Override
    public String toString() {
        return "AirbillCreditNoteVo [adjustmentId=" + adjustmentId + ", invoiceCode=" + invoiceCode + ", invoiceId=" + invoiceId + ", customerCode=" + customerCode + ", cusPaymentId=" + cusPaymentId + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", customerAmount=" + customerAmount + ", gstCustomerAmount=" + gstCustomerAmount + ", totalAmount=" + totalAmount + ", awbTotal=" + awbTotal + ", awbPaid=" + awbPaid + ", creditType=" + creditType + ", adjustmentType="
                + adjustmentType + ", reason=" + reason + ", creditNoteStatus=" + creditNoteStatus + ", creditCode=" + creditCode + ", adjustmentStatus=" + adjustmentStatus + "]";
    }

    public Long getAdjustmentId() {
        return adjustmentId;
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

    public Long getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(Long cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getAwbTotal() {
        return awbTotal;
    }

    public void setAwbTotal(Double awbTotal) {
        this.awbTotal = awbTotal;
    }

    public Double getAwbPaid() {
        return awbPaid;
    }

    public void setAwbPaid(Double awbPaid) {
        this.awbPaid = awbPaid;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public Integer getCreditType() {
        return creditType;
    }

    public void setCreditType(Integer creditType) {
        this.creditType = creditType;
    }

    public Integer getCreditNoteStatus() {
        return creditNoteStatus;
    }

    public void setCreditNoteStatus(Integer creditNoteStatus) {
        this.creditNoteStatus = creditNoteStatus;
    }

    public Byte getAdjustmentStatus() {
        return adjustmentStatus;
    }

    public void setAdjustmentStatus(Byte adjustmentStatus) {
        this.adjustmentStatus = adjustmentStatus;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }
}
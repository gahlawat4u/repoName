package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Posted from InvoiceVo
 * <p>
 * Author DatTV Date Mar 30, 2015
 */
public class InvoiceVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long invoiceId;

    private String invoiceCode;

    private Long customerCode;

    private Date invoiceDate;

    private BigDecimal lateFee;

    private Integer status;

    private Integer paid;

    private Date invCreateDate;

    private BigDecimal totalAmount;

    private BigDecimal totalPayment;

    private BigDecimal remainningBalance;

    private BigDecimal totalAwbPayment;

    private BigDecimal unreconcileAmount;

    private Date dueDate;

    private List<ShipmentInvoiceVo> shipmentInvoices;

    private BigDecimal payment;

    private Integer awbLevel;

    private BigDecimal appliedAmount;

    private BigDecimal unappliedAmount;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getInvCreateDate() {
        return invCreateDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setInvCreateDate(Date invCreateDate) {
        this.invCreateDate = invCreateDate;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
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

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getDueDate() {
        return dueDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setRemainningBalance(BigDecimal remainningBalance) {
        this.remainningBalance = remainningBalance;
    }

    public BigDecimal getRemainningBalance() {
        BigDecimal total = totalAmount == null ? BigDecimal.ZERO : totalAmount;
        BigDecimal pay = totalPayment == null ? BigDecimal.ZERO : totalPayment;
        BigDecimal remainning = total.subtract(pay);
        return remainning.compareTo(BigDecimal.ZERO) == 1 ? remainning : BigDecimal.ZERO;
    }

    public List<ShipmentInvoiceVo> getShipmentInvoices() {
        return shipmentInvoices;
    }

    public void setShipmentInvoices(List<ShipmentInvoiceVo> shipmentInvoices) {
        this.shipmentInvoices = shipmentInvoices;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getAwbLevel() {
        return awbLevel;
    }

    public void setAwbLevel(Integer awbLevel) {
        this.awbLevel = awbLevel;
    }

    public BigDecimal getUnreconcileAmount() {
        BigDecimal awbPayment = this.getTotalAwbPayment();
        BigDecimal total = totalAmount == null ? BigDecimal.ZERO : totalAmount;
        BigDecimal result = total.subtract(awbPayment);
        return result.compareTo(BigDecimal.ZERO) == 1 ? result : BigDecimal.ZERO;
    }

    public void setUnreconcileAmount(BigDecimal unreconcileAmount) {
        this.unreconcileAmount = unreconcileAmount;
    }

    public BigDecimal getTotalAwbPayment() {
        BigDecimal totalAwbPayment = BigDecimal.ZERO;
        if (shipmentInvoices == null || shipmentInvoices.size() == 0)
            return totalAwbPayment;
        for (ShipmentInvoiceVo shipmentInvoiceVo : this.getShipmentInvoices()) {
            BigDecimal siTotalPayment = shipmentInvoiceVo.getTotalPayment() == null ? BigDecimal.ZERO : shipmentInvoiceVo.getTotalPayment();
            totalAwbPayment = totalAwbPayment.add(siTotalPayment);
        }
        return totalAwbPayment;
    }

    public void setTotalAwbPayment(BigDecimal totalAwbPayment) {
        this.totalAwbPayment = totalAwbPayment;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getUnappliedAmount() {
        return unappliedAmount;
    }

    public void setUnappliedAmount(BigDecimal unappliedAmount) {
        this.unappliedAmount = unappliedAmount;
    }

    @Override
    public String toString() {
        return "InvoiceVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", invoiceDate=" + invoiceDate + ", lateFee=" + lateFee + ", status=" + status + ", paid=" + paid + ", invCreateDate=" + invCreateDate + ", totalAmount=" + totalAmount + ", totalPayment=" + totalPayment + ", remainningBalance=" + remainningBalance + ", totalAwbPayment=" + totalAwbPayment + ", unreconcileAmount=" + unreconcileAmount + ", dueDate=" + dueDate
                + ", shipmentInvoices=" + shipmentInvoices + ", payment=" + payment + ", awbLevel=" + awbLevel + ", appliedAmount=" + appliedAmount + ", unappliedAmount=" + unappliedAmount + "]";
    }
}
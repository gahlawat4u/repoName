package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Posted from FranPabDeductVo
 * <p>
 * Author DatTV Date Apr 22, 2015 3:51:03 PM
 */
public class FranPabDeductVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 722903894810830584L;

    private String rptTxnId;

    private String franchiseCode;

    private Long customerCode;

    private String customerName;

    private String invoiceCode;

    private Date invoiceDate;

    private Date invoiceDate61;

    private String airbillNumber;

    private BigDecimal customerPayment;

    private BigDecimal custCost;

    private BigDecimal custTax;

    private BigDecimal franCost;

    private BigDecimal franTax;

    private BigDecimal franchiseCharge;

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId == null ? null : rptTxnId.trim();
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode == null ? null : franchiseCode.trim();
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate61() {
        return invoiceDate61;
    }

    public void setInvoiceDate61(Date invoiceDate61) {
        this.invoiceDate61 = invoiceDate61;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber == null ? null : airbillNumber.trim();
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(BigDecimal customerPayment) {
        this.customerPayment = customerPayment;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCustCost() {
        return custCost;
    }

    public void setCustCost(BigDecimal custCost) {
        this.custCost = custCost;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCustTax() {
        return custTax;
    }

    public void setCustTax(BigDecimal custTax) {
        this.custTax = custTax;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranCost() {
        return franCost;
    }

    public void setFranCost(BigDecimal franCost) {
        this.franCost = franCost;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranTax() {
        return franTax;
    }

    public void setFranTax(BigDecimal franTax) {
        this.franTax = franTax;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranchiseCharge() {
        return franchiseCharge;
    }

    public void setFranchiseCharge(BigDecimal franchiseCharge) {
        this.franchiseCharge = franchiseCharge;
    }

    @Override
    public String toString() {
        return "FranPabDeductVo [rptTxnId=" + rptTxnId + ", franchiseCode=" + franchiseCode + ", customerCode=" + customerCode + ", customerName=" + customerName + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", invoiceDate61=" + invoiceDate61 + ", airbillNumber=" + airbillNumber + ", customerPayment=" + customerPayment + ", custCost=" + custCost + ", custTax=" + custTax + ", franCost=" + franCost + ", franTax=" + franTax + ", franchiseCharge=" + franchiseCharge + "]";
    }
}
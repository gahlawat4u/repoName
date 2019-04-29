package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDoubleScale2Serializer;
import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from CreditNoteInfoVo
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class CreditNoteInfoVo extends BaseVo {
    private static final long serialVersionUID = -1324722517983931112L;

    private String creditCode;
    private Date createDate;
    private Long customerCode;
    private Integer credits;
    private Double shipmentAmount;
    private Double gstAmount;
    private Double nonShipmentAmount;
    private Double nonGstAmount;
    private Double totalCredited;
    private String billingCustomerName;
    private String billingContactName;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String countryName;
    private String billingPostalCode;
    private Integer status;
    private Double totalAmount;
    private Double nonTotalAmount;
    private Double taxPercent;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getShipmentAmount() {
        return shipmentAmount;
    }

    public void setShipmentAmount(Double shipmentAmount) {
        this.shipmentAmount = shipmentAmount;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getGstAmount() {
        return gstAmount;
    }

    public void setGstAmount(Double gstAmount) {
        this.gstAmount = gstAmount;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getNonShipmentAmount() {
        return nonShipmentAmount;
    }

    public void setNonShipmentAmount(Double nonShipmentAmount) {
        this.nonShipmentAmount = nonShipmentAmount;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getNonGstAmount() {
        return nonGstAmount;
    }

    public void setNonGstAmount(Double nonGstAmount) {
        this.nonGstAmount = nonGstAmount;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getTotalCredited() {
        return totalCredited;
    }

    public void setTotalCredited(Double totalCredited) {
        this.totalCredited = totalCredited;
    }

    public String getBillingCustomerName() {
        return billingCustomerName;
    }

    public void setBillingCustomerName(String billingCustomerName) {
        this.billingCustomerName = billingCustomerName;
    }

    public String getBillingContactName() {
        return billingContactName;
    }

    public void setBillingContactName(String billingContactName) {
        this.billingContactName = billingContactName;
    }

    public String getBillingAddress1() {
        return billingAddress1;
    }

    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getNonTotalAmount() {
        return nonTotalAmount;
    }

    public void setNonTotalAmount(Double nonTotalAmount) {
        this.nonTotalAmount = nonTotalAmount;
    }

    @JsonSerialize(using = JsonDoubleScale2Serializer.class)
    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    @Override
    public String toString() {
        return "CreditNoteInfoVo [creditCode=" + creditCode + ", createDate=" + createDate + ", customerCode=" + customerCode + ", credits=" + credits + ", shipmentAmount=" + shipmentAmount + ", gstAmount=" + gstAmount + ", nonShipmentAmount=" + nonShipmentAmount + ", nonGstAmount=" + nonGstAmount + ", totalCredited=" + totalCredited + ", billingCustomerName=" + billingCustomerName + ", billingContactName=" + billingContactName + ", billingAddress1=" + billingAddress1 + ", billingAddress2="
                + billingAddress2 + ", billingCity=" + billingCity + ", countryName=" + countryName + ", billingPostalCode=" + billingPostalCode + ", status=" + status + ", totalAmount=" + totalAmount + ", nonTotalAmount=" + nonTotalAmount + ", taxPercent=" + taxPercent + "]";
    }

}
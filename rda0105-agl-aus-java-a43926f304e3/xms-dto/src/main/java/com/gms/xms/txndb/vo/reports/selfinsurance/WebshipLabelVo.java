package com.gms.xms.txndb.vo.reports.selfinsurance;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from WebshipLabelVo
 * <p>
 * Author dattrinh Mar 18, 2016 1:56:37 PM
 */
public class WebshipLabelVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String invoiceCode;
    private String customerCode;
    private Long shipmentId;
    private String airbillNumber;
    private String carrier;
    private String service;
    private String voided;
    private Double shipmentValue;
    private Double customerInsuredAmount;
    private Date createDate;
    private Date shipDate;
    private Date pickupDate;
    private Long noOfPieces;
    private Double weight;
    private Double deadWeight;
    private String weightUnit;
    private String dimension;
    private String dimensionUnit;
    private Double quoted;
    private String senderCompanyName;
    private String senderPhone;
    private String senderContactName;
    private String senderEmail;
    private String senderCountryName;
    private String senderAddress1;
    private String senderAddress2;
    private String senderCity;
    private String senderPostalCode;
    private String senderState;
    private String receiverCompanyName;
    private String receiverPhone;
    private String receiverContactName;
    private String receiverEmail;
    private String receiverCountryName;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverCity;
    private String receiverPostalCode;
    private String receiverState;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVoided() {
        return voided;
    }

    public void setVoided(String voided) {
        this.voided = voided;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getShipmentValue() {
        return shipmentValue;
    }

    public void setShipmentValue(Double shipmentValue) {
        this.shipmentValue = shipmentValue;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerInsuredAmount() {
        return customerInsuredAmount;
    }

    public void setCustomerInsuredAmount(Double customerInsuredAmount) {
        this.customerInsuredAmount = customerInsuredAmount;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Long getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Long noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDeadWeight() {
        return deadWeight;
    }

    public void setDeadWeight(Double deadWeight) {
        this.deadWeight = deadWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getQuoted() {
        return quoted;
    }

    public void setQuoted(Double quoted) {
        this.quoted = quoted;
    }

    public String getSenderCompanyName() {
        return senderCompanyName;
    }

    public void setSenderCompanyName(String senderCompanyName) {
        this.senderCompanyName = senderCompanyName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderContactName() {
        return senderContactName;
    }

    public void setSenderContactName(String senderContactName) {
        this.senderContactName = senderContactName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderCountryName() {
        return senderCountryName;
    }

    public void setSenderCountryName(String senderCountryName) {
        this.senderCountryName = senderCountryName;
    }

    public String getSenderAddress1() {
        return senderAddress1;
    }

    public void setSenderAddress1(String senderAddress1) {
        this.senderAddress1 = senderAddress1;
    }

    public String getSenderAddress2() {
        return senderAddress2;
    }

    public void setSenderAddress2(String senderAddress2) {
        this.senderAddress2 = senderAddress2;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderPostalCode() {
        return senderPostalCode;
    }

    public void setSenderPostalCode(String senderPostalCode) {
        this.senderPostalCode = senderPostalCode;
    }

    public String getSenderState() {
        return senderState;
    }

    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }

    public String getReceiverCompanyName() {
        return receiverCompanyName;
    }

    public void setReceiverCompanyName(String receiverCompanyName) {
        this.receiverCompanyName = receiverCompanyName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverContactName() {
        return receiverContactName;
    }

    public void setReceiverContactName(String receiverContactName) {
        this.receiverContactName = receiverContactName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverCountryName() {
        return receiverCountryName;
    }

    public void setReceiverCountryName(String receiverCountryName) {
        this.receiverCountryName = receiverCountryName;
    }

    public String getReceiverAddress1() {
        return receiverAddress1;
    }

    public void setReceiverAddress1(String receiverAddress1) {
        this.receiverAddress1 = receiverAddress1;
    }

    public String getReceiverAddress2() {
        return receiverAddress2;
    }

    public void setReceiverAddress2(String receiverAddress2) {
        this.receiverAddress2 = receiverAddress2;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverPostalCode() {
        return receiverPostalCode;
    }

    public void setReceiverPostalCode(String receiverPostalCode) {
        this.receiverPostalCode = receiverPostalCode;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    @Override
    public String toString() {
        return "WebshipLabelVo [invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", carrier=" + carrier + ", service=" + service + ", voided=" + voided + ", shipmentValue=" + shipmentValue + ", customerInsuredAmount=" + customerInsuredAmount + ", createDate=" + createDate + ", shipDate=" + shipDate + ", pickupDate=" + pickupDate + ", noOfPieces=" + noOfPieces + ", weight=" + weight + ", deadWeight=" + deadWeight
                + ", weightUnit=" + weightUnit + ", dimension=" + dimension + ", dimensionUnit=" + dimensionUnit + ", quoted=" + quoted + ", senderCompanyName=" + senderCompanyName + ", senderPhone=" + senderPhone + ", senderContactName=" + senderContactName + ", senderEmail=" + senderEmail + ", senderCountryName=" + senderCountryName + ", senderAddress1=" + senderAddress1 + ", senderAddress2=" + senderAddress2 + ", senderCity=" + senderCity + ", senderPostalCode=" + senderPostalCode
                + ", senderState=" + senderState + ", receiverCompanyName=" + receiverCompanyName + ", receiverPhone=" + receiverPhone + ", receiverContactName=" + receiverContactName + ", receiverEmail=" + receiverEmail + ", receiverCountryName=" + receiverCountryName + ", receiverAddress1=" + receiverAddress1 + ", receiverAddress2=" + receiverAddress2 + ", receiverCity=" + receiverCity + ", receiverPostalCode=" + receiverPostalCode + ", receiverState=" + receiverState + "]";
    }
}

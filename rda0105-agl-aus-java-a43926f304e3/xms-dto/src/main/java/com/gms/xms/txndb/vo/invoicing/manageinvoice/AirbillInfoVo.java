package com.gms.xms.txndb.vo.invoicing.manageinvoice;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.List;

/**
 * File Name: AirbillInfoVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 16-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing.manageinvoice <br/>
 */
public class AirbillInfoVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 4578856607564377417L;
    private Long shipmentId;
    private String serviceName;
    private String airbillNumber;
    private String serviceAreaCodeOrigin;
    private String serviceAreaCodeDestination;
    private Date shipmentDate;
    private Long customerCode;
    private String reference;
    private String reference2;
    private String senderCompanyName;
    private String senderContactName;
    private String senderAddress1;
    private String senderAddress2;
    private String senderCity;
    private String senderStates;
    private String senderPostalCode;
    private String senderCountryName;
    private String receiverCompanyName;
    private String receiverContactName;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverCity;
    private String receiverState;
    private String receiverPostalCode;
    private String receiverCountryName;
    private Double weight;
    private String noOfPieces;
    private String zone;
    private Double totalCost;
    private Double totalTax;
    private Double total;
    private List<ChargeVo> charges;
    private Long senderAddressId;
    private Long receiverAddressId;

    public Long getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(Long senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public Long getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Long receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getServiceAreaCodeOrigin() {
        return serviceAreaCodeOrigin;
    }

    public void setServiceAreaCodeOrigin(String serviceAreaCodeOrigin) {
        this.serviceAreaCodeOrigin = serviceAreaCodeOrigin;
    }

    public String getServiceAreaCodeDestination() {
        return serviceAreaCodeDestination;
    }

    public void setServiceAreaCodeDestination(String serviceAreaCodeDestination) {
        this.serviceAreaCodeDestination = serviceAreaCodeDestination;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReference2() {
        return reference2;
    }

    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }

    public String getSenderCompanyName() {
        return senderCompanyName;
    }

    public void setSenderCompanyName(String senderCompanyName) {
        this.senderCompanyName = senderCompanyName;
    }

    public String getSenderContactName() {
        return senderContactName;
    }

    public void setSenderContactName(String senderContactName) {
        this.senderContactName = senderContactName;
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

    public String getSenderStates() {
        return senderStates;
    }

    public void setSenderStates(String senderStates) {
        this.senderStates = senderStates;
    }

    public String getSenderPostalCode() {
        return senderPostalCode;
    }

    public void setSenderPostalCode(String senderPostalCode) {
        this.senderPostalCode = senderPostalCode;
    }

    public String getSenderCountryName() {
        return senderCountryName;
    }

    public void setSenderCountryName(String senderCountryName) {
        this.senderCountryName = senderCountryName;
    }

    public String getReceiverCompanyName() {
        return receiverCompanyName;
    }

    public void setReceiverCompanyName(String receiverCompanyName) {
        this.receiverCompanyName = receiverCompanyName;
    }

    public String getReceiverContactName() {
        return receiverContactName;
    }

    public void setReceiverContactName(String receiverContactName) {
        this.receiverContactName = receiverContactName;
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

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverPostalCode() {
        return receiverPostalCode;
    }

    public void setReceiverPostalCode(String receiverPostalCode) {
        this.receiverPostalCode = receiverPostalCode;
    }

    public String getReceiverCountryName() {
        return receiverCountryName;
    }

    public void setReceiverCountryName(String receiverCountryName) {
        this.receiverCountryName = receiverCountryName;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(String noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ChargeVo> getCharges() {
        return charges;
    }

    public void setCharges(List<ChargeVo> charges) {
        this.charges = charges;
    }

    @Override
    public String toString() {
        return "AirbillInfoVo [shipmentId=" + shipmentId + ", serviceName=" + serviceName + ", airbillNumber=" + airbillNumber + ", serviceAreaCodeOrigin=" + serviceAreaCodeOrigin + ", serviceAreaCodeDestination=" + serviceAreaCodeDestination + ", shipmentDate=" + shipmentDate + ", customerCode=" + customerCode + ", reference=" + reference + ", reference2=" + reference2 + ", senderCompanyName=" + senderCompanyName + ", senderContactName=" + senderContactName + ", senderAddress1="
                + senderAddress1 + ", senderAddress2=" + senderAddress2 + ", senderCity=" + senderCity + ", senderStates=" + senderStates + ", senderPostalCode=" + senderPostalCode + ", senderCountryName=" + senderCountryName + ", receiverCompanyName=" + receiverCompanyName + ", receiverContactName=" + receiverContactName + ", receiverAddress1=" + receiverAddress1 + ", receiverAddress2=" + receiverAddress2 + ", receiverCity=" + receiverCity + ", receiverState=" + receiverState
                + ", receiverPostalCode=" + receiverPostalCode + ", receiverCountryName=" + receiverCountryName + ", weight=" + weight + ", noOfPieces=" + noOfPieces + ", zone=" + zone + ", totalCost=" + totalCost + ", totalTax=" + totalTax + ", total=" + total + ", charges=" + charges + ", senderAddressId=" + senderAddressId + ", receiverAddressId=" + receiverAddressId + "]";
    }

}

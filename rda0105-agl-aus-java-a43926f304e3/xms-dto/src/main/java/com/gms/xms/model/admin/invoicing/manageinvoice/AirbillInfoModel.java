package com.gms.xms.model.admin.invoicing.manageinvoice;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * File Name: AirbillInfoModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 16-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.manageinvoice <br/>
 */
public class AirbillInfoModel extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 4578856607564377417L;
    private String shipmentId;
    private String serviceName;
    private String airbillNumber;
    private String serviceAreaCodeOrigin;
    private String serviceAreaCodeDestination;
    private String shipmentDate;
    private String customerCode;
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
    private String weight;
    private String noOfPieces;
    private String zone;
    private String totalCost;
    private String totalTax;
    private String total;
    private List<ChargeModel> charges;
    private String senderAddressId;
    private String receiverAddressId;

    public String getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(String senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public String getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(String receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
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

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ChargeModel> getCharges() {
        return charges;
    }

    public void setCharges(List<ChargeModel> charges) {
        this.charges = charges;
    }

    @Override
    public String toString() {
        return "AirbillInfoModel [shipmentId=" + shipmentId + ", serviceName=" + serviceName + ", airbillNumber=" + airbillNumber + ", serviceAreaCodeOrigin=" + serviceAreaCodeOrigin + ", serviceAreaCodeDestination=" + serviceAreaCodeDestination + ", shipmentDate=" + shipmentDate + ", customerCode=" + customerCode + ", reference=" + reference + ", reference2=" + reference2 + ", senderCompanyName=" + senderCompanyName + ", senderContactName=" + senderContactName + ", senderAddress1="
                + senderAddress1 + ", senderAddress2=" + senderAddress2 + ", senderCity=" + senderCity + ", senderStates=" + senderStates + ", senderPostalCode=" + senderPostalCode + ", senderCountryName=" + senderCountryName + ", receiverCompanyName=" + receiverCompanyName + ", receiverContactName=" + receiverContactName + ", receiverAddress1=" + receiverAddress1 + ", receiverAddress2=" + receiverAddress2 + ", receiverCity=" + receiverCity + ", receiverState=" + receiverState
                + ", receiverPostalCode=" + receiverPostalCode + ", receiverCountryName=" + receiverCountryName + ", weight=" + weight + ", noOfPieces=" + noOfPieces + ", zone=" + zone + ", totalCost=" + totalCost + ", totalTax=" + totalTax + ", total=" + total + ", charges=" + charges + ", senderAddressId=" + senderAddressId + ", receiverAddressId=" + receiverAddressId + "]";
    }

}

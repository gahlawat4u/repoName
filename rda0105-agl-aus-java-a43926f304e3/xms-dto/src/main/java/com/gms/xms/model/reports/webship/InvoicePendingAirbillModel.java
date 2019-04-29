package com.gms.xms.model.reports.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from InvoicePendingAirbillModel
 * <p>
 * Author DatTV Dec 4, 2015
 */
public class InvoicePendingAirbillModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String shipmentId;
    private String airbillNumber;
    private String createDate;
    private String shipmentDate;
    private String customerCode;
    private String customerName;
    private String carrierName;
    private String serviceName;
    private String weightUnit;
    private String city;
    private String postalCode;
    private String countryName;
    private String weight;
    private String noOfPieces;

    @Override
    public String toString() {
        return "InvoicePendingAirbillModel [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", createDate=" + createDate + ", shipmentDate=" + shipmentDate + ", customerCode=" + customerCode + ", customerName=" + customerName + ", carrierName=" + carrierName + ", serviceName=" + serviceName + ", weightUnit=" + weightUnit + ", city=" + city + ", postalCode=" + postalCode + ", countryName=" + countryName + ", weight=" + weight + ", noOfPieces=" + noOfPieces + "]";
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
}

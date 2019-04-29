package com.gms.xms.txndb.vo.reports.webship;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from InvoicePendingAirbillVo
 * <p>
 * Author DatTV Dec 4, 2015
 */
public class InvoicePendingAirbillVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long shipmentId;
    private String airbillNumber;
    private Date createDate;
    private Date shipmentDate;
    private String customerCode;
    private String customerName;
    private String carrierName;
    private String serviceName;
    private String weightUnit;
    private String city;
    private String postalCode;
    private String countryName;
    private Double weight;
    private Long noOfPieces;

    @Override
    public String toString() {
        return "InvoicePendingAirbillVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", createDate=" + createDate + ", shipmentDate=" + shipmentDate + ", customerCode=" + customerCode + ", customerName=" + customerName + ", carrierName=" + carrierName + ", serviceName=" + serviceName + ", weightUnit=" + weightUnit + ", city=" + city + ", postalCode=" + postalCode + ", countryName=" + countryName + ", weight=" + weight + ", noOfPieces=" + noOfPieces + "]";
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

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Long noOfPieces) {
        this.noOfPieces = noOfPieces;
    }
}

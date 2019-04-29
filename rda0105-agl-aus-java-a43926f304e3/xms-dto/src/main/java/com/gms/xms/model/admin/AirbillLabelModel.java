package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Aug 27, 2016 5:56:06 PM
 * <p>
 * Author dattrinh
 */
public class AirbillLabelModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String shipmentId;
    private String customerCode;
    private String customerName;
    private String airbillNumber;
    private String serviceName;
    private String voidedStatus;
    private String createDate;
    private String shipmentDate;
    private String noOfPieces;
    private String weight;
    private String weightUnit;
    private String scheduleCollectionId;
    private String collectionTypeName;
    private String confirmationNo;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
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

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getVoidedStatus() {
        return voidedStatus;
    }

    public void setVoidedStatus(String voidedStatus) {
        this.voidedStatus = voidedStatus;
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

    public String getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(String noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getScheduleCollectionId() {
        return scheduleCollectionId;
    }

    public void setScheduleCollectionId(String scheduleCollectionId) {
        this.scheduleCollectionId = scheduleCollectionId;
    }

    public String getCollectionTypeName() {
        return collectionTypeName;
    }

    public void setCollectionTypeName(String collectionTypeName) {
        this.collectionTypeName = collectionTypeName;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    @Override
    public String toString() {
        return "AirbillLabelModel [shipmentId=" + shipmentId + ", customerCode=" + customerCode + ", customerName=" + customerName + ", airbillNumber=" + airbillNumber + ", serviceName=" + serviceName + ", voidedStatus=" + voidedStatus + ", createDate=" + createDate + ", shipmentDate=" + shipmentDate + ", noOfPieces=" + noOfPieces + ", weight=" + weight + ", weightUnit=" + weightUnit + ", scheduleCollectionId=" + scheduleCollectionId + ", collectionTypeName=" + collectionTypeName
                + ", confirmationNo=" + confirmationNo + "]";
    }
}

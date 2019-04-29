package com.gms.xms.txndb.vo.admin;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from Aug 27, 2016 5:56:06 PM
 * <p>
 * Author dattrinh
 */
public class AirbillLabelVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long shipmentId;
    private String customerCode;
    private String customerName;
    private String airbillNumber;
    private String serviceName;
    private String voidedStatus;
    private Date createDate;
    private Date shipmentDate;
    private Integer noOfPieces;
    private Double weight;
    private String weightUnit;
    private Long scheduleCollectionId;
    private String collectionTypeName;
    private String confirmationNo;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
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

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
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

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Long getScheduleCollectionId() {
        return scheduleCollectionId;
    }

    public void setScheduleCollectionId(Long scheduleCollectionId) {
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
        return "AirbillLabelVo [shipmentId=" + shipmentId + ", customerCode=" + customerCode + ", customerName=" + customerName + ", airbillNumber=" + airbillNumber + ", serviceName=" + serviceName + ", voidedStatus=" + voidedStatus + ", createDate=" + createDate + ", shipmentDate=" + shipmentDate + ", noOfPieces=" + noOfPieces + ", weight=" + weight + ", weightUnit=" + weightUnit + ", scheduleCollectionId=" + scheduleCollectionId + ", collectionTypeName=" + collectionTypeName
                + ", confirmationNo=" + confirmationNo + "]";
    }
}

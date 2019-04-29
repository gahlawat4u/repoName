package com.gms.xms.model.email.ups;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.webship.AddressModel;
import com.gms.xms.model.webship.ScheduleCollectionModel;

import java.util.List;

public class UpsIntlBookingEmailModel extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = 4052014247988068323L;
    private String franchiseName;
    private String customerName;
    private String customerCode;
    private String airbillNumber;
    private String carrierName;
    private String message;
    private String shipmentDate;
    private String shipmentTypeName;
    private String packageName;
    private String noOfPieces;
    private String totalWeight;
    private List<String> dimensions;
    private String collectionInstruction;
    private String insurance;
    private AddressModel senderAddress;
    private AddressModel receiverAddress;
    private ScheduleCollectionModel scheduleInfo;
    private String locationDescription;
    private String locationCode;

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(String noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<String> dimensions) {
        this.dimensions = dimensions;
    }

    public String getCollectionInstruction() {
        return collectionInstruction;
    }

    public void setCollectionInstruction(String collectionInstruction) {
        this.collectionInstruction = collectionInstruction;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public AddressModel getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressModel senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressModel getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(AddressModel receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public ScheduleCollectionModel getScheduleInfo() {
        return scheduleInfo;
    }

    public void setScheduleInfo(ScheduleCollectionModel scheduleInfo) {
        this.scheduleInfo = scheduleInfo;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    @Override
    public String toString() {
        return "UpsIntlBookingEmailModel [franchiseName=" + franchiseName + ", customerName=" + customerName + ", customerCode=" + customerCode + ", airbillNumber=" + airbillNumber + ", carrierName=" + carrierName + ", message=" + message + ", shipmentDate=" + shipmentDate + ", shipmentTypeName=" + shipmentTypeName + ", packageName=" + packageName + ", noOfPieces=" + noOfPieces + ", totalWeight=" + totalWeight + ", dimensions=" + dimensions + ", collectionInstruction=" + collectionInstruction
                + ", insurance=" + insurance + ", senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + "]";
    }
}

package com.gms.xms.txndb.vo.email.tollpriority;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.List;

public class TollPriorityShipmentRequestEmailVo extends BaseVo {
    private static final long serialVersionUID = -5351866588482694719L;

    private String franchiseName;
    private String customerName;
    private String customerCode;
    private String airbillNumber;
    private String carrierName;
    private String message;
    private Date shipmentDate;
    private String shipmentTypeName;
    private String packageName;
    private Integer noOfPieces;
    private Double totalWeight;
    private List<String> dimensions;
    private String collectionInstruction;
    private String insurance;
    private AddressVo senderAddress;
    private AddressVo receiverAddress;
    private String timeCriticial;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setShipmentDate(Date shipmentDate) {
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

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
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

    public AddressVo getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressVo senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressVo getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(AddressVo receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getTimeCriticial() {
        return timeCriticial;
    }

    public void setTimeCriticial(String timeCriticial) {
        this.timeCriticial = timeCriticial;
    }

    @Override
    public String toString() {
        return "TollPriorityShipmentRequestEmailVo [franchiseName=" + franchiseName + ", customerName=" + customerName + ", customerCode=" + customerCode + ", airbillNumber=" + airbillNumber + ", carrierName=" + carrierName + ", message=" + message + ", shipmentDate=" + shipmentDate + ", shipmentTypeName=" + shipmentTypeName + ", packageName=" + packageName + ", noOfPieces=" + noOfPieces + ", totalWeight=" + totalWeight + ", dimensions=" + dimensions + ", collectionInstruction="
                + collectionInstruction + ", insurance=" + insurance + ", senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress + ", timeCriticial=" + timeCriticial + "]";
    }
}

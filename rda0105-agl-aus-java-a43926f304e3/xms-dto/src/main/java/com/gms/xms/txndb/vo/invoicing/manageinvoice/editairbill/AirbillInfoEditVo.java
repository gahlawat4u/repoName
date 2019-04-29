package com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * @author TANDT
 */
public class AirbillInfoEditVo extends BaseVo {
    private static final long serialVersionUID = 1991225316672308659L;
    private Integer serviceId;
    private String description;
    private String airbillNumber;
    private Integer shipmentTypeId;
    private String serviceName;
    private Double carrierBaseCharge;
    private Double calculatedCharge;
    private Boolean requireCalculate;
    private Double franchiseCost;
    private String freightClass;
    private Date shipmentDate;
    private Double forceCustCharge;
    private Double weight;
    private Integer reweightWeight;
    private String actualDimensions;
    private Integer originId;
    private Integer destinationId;
    private String originCity;
    private String destinationCity;
    private String zone;
    private Integer noOfPieces;
    private String reference;
    private String reference2;
    private String reference3;
    private String receivedBy;
    private Date receivedDate;
    private Date createDate;
    private Date modifiedDate;
    private Double taxCode;
    private Double taxAmount;
    private Double tax;
    private Boolean isGst;
    private String serviceCode;
    private Long shipmentId;
    private Double carrierTaxPercent;
    private Integer senderAddressId;
    private Integer receiverAddressId;
    private Long customerCode;

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(Integer senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public Integer getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Integer receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public Double getCarrierTaxPercent() {
        return carrierTaxPercent;
    }

    public void setCarrierTaxPercent(Double carrierTaxPercent) {
        this.carrierTaxPercent = carrierTaxPercent;
    }

    public Boolean getRequireCalculate() {
        return requireCalculate;
    }

    public void setRequireCalculate(Boolean requireCalculate) {
        this.requireCalculate = requireCalculate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public String getFreightClass() {
        return freightClass;
    }

    public void setFreightClass(String freightClass) {
        this.freightClass = freightClass;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getCarrierBaseCharge() {
        return carrierBaseCharge;
    }

    public void setCarrierBaseCharge(Double carrierBaseCharge) {
        this.carrierBaseCharge = carrierBaseCharge;
    }

    public Double getCalculatedCharge() {
        return calculatedCharge;
    }

    public void setCalculatedCharge(Double calculatedCharge) {
        this.calculatedCharge = calculatedCharge;
    }

    public Double getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(Double franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Double getForceCustCharge() {
        return forceCustCharge;
    }

    public void setForceCustCharge(Double forceCustCharge) {
        this.forceCustCharge = forceCustCharge;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getReweightWeight() {
        return reweightWeight;
    }

    public void setReweightWeight(Integer reweightWeight) {
        this.reweightWeight = reweightWeight;
    }

    public String getActualDimensions() {
        return actualDimensions;
    }

    public void setActualDimensions(String actualDimensions) {
        this.actualDimensions = actualDimensions;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
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

    public String getReference3() {
        return reference3;
    }

    public void setReference3(String reference3) {
        this.reference3 = reference3;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getReceivedDate() {
        return receivedDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Double getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(Double taxCode) {
        this.taxCode = taxCode;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Boolean getIsGst() {
        return isGst;
    }

    public void setIsGst(Boolean isGst) {
        this.isGst = isGst;
    }

    @Override
    public String toString() {
        return "AirbillInfoEditVo [serviceId=" + serviceId + ", description=" + description + ", airbillNumber=" + airbillNumber + ", shipmentTypeId=" + shipmentTypeId + ", serviceName=" + serviceName + ", carrierBaseCharge=" + carrierBaseCharge + ", calculatedCharge=" + calculatedCharge + ", requireCalculate=" + requireCalculate + ", franchiseCost=" + franchiseCost + ", freightClass=" + freightClass + ", shipmentDate=" + shipmentDate + ", forceCustCharge=" + forceCustCharge + ", weight="
                + weight + ", reweightWeight=" + reweightWeight + ", actualDimensions=" + actualDimensions + ", originId=" + originId + ", destinationId=" + destinationId + ", originCity=" + originCity + ", destinationCity=" + destinationCity + ", zone=" + zone + ", noOfPieces=" + noOfPieces + ", reference=" + reference + ", reference2=" + reference2 + ", reference3=" + reference3 + ", receivedBy=" + receivedBy + ", receivedDate=" + receivedDate + ", createDate=" + createDate + ", modifiedDate="
                + modifiedDate + ", taxCode=" + taxCode + ", taxAmount=" + taxAmount + ", tax=" + tax + ", isGst=" + isGst + ", serviceCode=" + serviceCode + ", shipmentId=" + shipmentId + ", carrierTaxPercent=" + carrierTaxPercent + ", senderAddressId=" + senderAddressId + ", receiverAddressId=" + receiverAddressId + ", customerCode=" + customerCode + "]";
    }

}

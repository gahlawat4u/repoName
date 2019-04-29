package com.gms.xms.model.webship.invoices;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from AirbillDetailModel
 * <p>
 * Author DatTV Date Jul 12, 2015 12:48:17 AM
 */
public class AirbillDetailModel extends BaseModel {

    private static final long serialVersionUID = 708326387323584846L;

    private String serviceName;
    private String airbillNumber;
    private String senderCountryCode;
    private String receiverCountryCode;
    private String shipmentDate;
    private String customerCode;
    private String shipperReference;
    private String billingReference;
    private String billActualDimension;
    private String senderCompanyName;
    private String senderContactName;
    private String senderAddress;
    private String senderAddress2;
    private String senderCity;
    private String senderPostalCode;
    private String senderState;
    private String senderCountry;
    private String receiverCompanyName;
    private String receiverContactName;
    private String receiverAddress;
    private String receiverAddress2;
    private String receiverCity;
    private String receiverPostalCode;
    private String receiverState;
    private String receiverCountry;
    private String noOfPieces;
    private String packageTypeCode;
    private String receiverZone;
    private String senderZone;
    private String carrier;
    private String shipmentId;
    private String weight;
    private String serviceAreaCodeOrigin;
    private String serviceAreaCodeDestination;
    private String senderAddressId;
    private String receiverAddressId;
    private String zone;
    private String displayDescription;

    private List<AirbillChargeModel> charges;
    private String total;

    @Override
    public String toString() {
        return "AirbillDetailModel [serviceName=" + serviceName + ", airbillNumber=" + airbillNumber + ", senderCountryCode=" + senderCountryCode + ", receiverCountryCode=" + receiverCountryCode + ", shipmentDate=" + shipmentDate + ", customerCode=" + customerCode + ", shipperReference=" + shipperReference + ", billingReference=" + billingReference + ", billActualDimension=" + billActualDimension + ", senderCompanyName=" + senderCompanyName + ", senderContactName=" + senderContactName
                + ", senderAddress=" + senderAddress + ", senderAddress2=" + senderAddress2 + ", senderCity=" + senderCity + ", senderPostalCode=" + senderPostalCode + ", senderState=" + senderState + ", senderCountry=" + senderCountry + ", receiverCompanyName=" + receiverCompanyName + ", receiverContactName=" + receiverContactName + ", receiverAddress=" + receiverAddress + ", receiverAddress2=" + receiverAddress2 + ", receiverCity=" + receiverCity + ", receiverPostalCode=" + receiverPostalCode
                + ", receiverState=" + receiverState + ", receiverCountry=" + receiverCountry + ", noOfPieces=" + noOfPieces + ", packageTypeCode=" + packageTypeCode + ", receiverZone=" + receiverZone + ", senderZone=" + senderZone + ", carrier=" + carrier + ", shipmentId=" + shipmentId + ", weight=" + weight + ", serviceAreaCodeOrigin=" + serviceAreaCodeOrigin + ", serviceAreaCodeDestination=" + serviceAreaCodeDestination + ", senderAddressId=" + senderAddressId + ", receiverAddressId="
                + receiverAddressId + ", zone=" + zone + ", displayDescription=" + displayDescription + ", charges=" + charges + ", total=" + total + "]";
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

    public String getSenderCountryCode() {
        return senderCountryCode;
    }

    public void setSenderCountryCode(String senderCountryCode) {
        this.senderCountryCode = senderCountryCode;
    }

    public String getReceiverCountryCode() {
        return receiverCountryCode;
    }

    public void setReceiverCountryCode(String receiverCountryCode) {
        this.receiverCountryCode = receiverCountryCode;
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

    public String getShipperReference() {
        return shipperReference;
    }

    public void setShipperReference(String shipperReference) {
        this.shipperReference = shipperReference;
    }

    public String getBillingReference() {
        return billingReference;
    }

    public void setBillingReference(String billingReference) {
        this.billingReference = billingReference;
    }

    public String getBillActualDimension() {
        return billActualDimension;
    }

    public void setBillActualDimension(String billActualDimension) {
        this.billActualDimension = billActualDimension;
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

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
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

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
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

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
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

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(String noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public String getPackageTypeCode() {
        return packageTypeCode;
    }

    public void setPackageTypeCode(String packageTypeCode) {
        this.packageTypeCode = packageTypeCode;
    }

    public String getReceiverZone() {
        return receiverZone;
    }

    public void setReceiverZone(String receiverZone) {
        this.receiverZone = receiverZone;
    }

    public String getSenderZone() {
        return senderZone;
    }

    public void setSenderZone(String senderZone) {
        this.senderZone = senderZone;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public List<AirbillChargeModel> getCharges() {
        return charges;
    }

    public void setCharges(List<AirbillChargeModel> charges) {
        this.charges = charges;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

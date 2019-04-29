package com.gms.xms.model.webship;

import java.util.List;

/**
 * Posted from ShipmentRequestModel
 * <p>
 * Author HungNT Date Apr 15, 2015
 */
public class ShipmentInfoModel extends ShipmentModel {
    private static final long serialVersionUID = -3045561409115987472L;

    private AddressModel senderAddress;
    private AddressModel receiverAddress;
    private String serviceId;
    private String shipmentTypeId;
    private String contentType;
    private String currency;
    private List<PieceModel> pieces;
    private String bound;
    private String isAddPiece;
    private String residentialDelivery;
    private String residentialPickup;
    private List<ServiceAddConModel> addCons;
    private String shipperAccount;
    private String shipperReference;
    private String totalWeight;
    private String weightType;
    private String selContents;
    private List<PieceModel> shipmentRequestPieces;
    private String dhInterAccout;
    private String dutiesBillTo;
    private String dutiesAccount;
    private String billingParty;
    private String specialDelivery;

    private String isSaveSenderAddressBook;
    private String isSaveRecipientAddressBook;

    private String insuranceUserAmount;
    private String packageId;
    private String errorMsg;

    private String defaultCarrierType;

    private String defaultServiceType;

    private String defaultPackageType;

    public String getIsSaveSenderAddressBook() {
        return isSaveSenderAddressBook;
    }

    public void setIsSaveSenderAddressBook(String isSaveSenderAddressBook) {
        this.isSaveSenderAddressBook = isSaveSenderAddressBook;
    }

    public String getIsSaveRecipientAddressBook() {
        return isSaveRecipientAddressBook;
    }

    public void setIsSaveRecipientAddressBook(String isSaveRecipientAddressBook) {
        this.isSaveRecipientAddressBook = isSaveRecipientAddressBook;
    }

    public String getSpecialDelivery() {
        return specialDelivery;
    }

    public void setSpecialDelivery(String specialDelivery) {
        this.specialDelivery = specialDelivery;
    }

    public String getBillingParty() {
        return billingParty;
    }

    public void setBillingParty(String billingParty) {
        this.billingParty = billingParty;
    }

    public String getDhInterAccout() {
        return dhInterAccout;
    }

    public void setDhInterAccout(String dhInterAccout) {
        this.dhInterAccout = dhInterAccout;
    }

    public String getDutiesBillTo() {
        return dutiesBillTo;
    }

    public void setDutiesBillTo(String dutiesBillTo) {
        this.dutiesBillTo = dutiesBillTo;
    }

    @Override
    public String getDutiesAccount() {
        return dutiesAccount;
    }

    @Override
    public void setDutiesAccount(String dutiesAccount) {
        this.dutiesAccount = dutiesAccount;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    @Override
    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<PieceModel> getPieces() {
        return pieces;
    }

    public void setPieces(List<PieceModel> pieces) {
        this.pieces = pieces;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getIsAddPiece() {
        return isAddPiece;
    }

    public void setIsAddPiece(String isAddPiece) {
        this.isAddPiece = isAddPiece;
    }

    public String getResidentialDelivery() {
        return residentialDelivery;
    }

    public void setResidentialDelivery(String residentialDelivery) {
        this.residentialDelivery = residentialDelivery;
    }

    public String getResidentialPickup() {
        return residentialPickup;
    }

    public void setResidentialPickup(String residentialPickup) {
        this.residentialPickup = residentialPickup;
    }

    public List<ServiceAddConModel> getAddCons() {
        return addCons;
    }

    public void setAddCons(List<ServiceAddConModel> addCons) {
        this.addCons = addCons;
    }

    public String getShipperAccount() {
        return shipperAccount;
    }

    public void setShipperAccount(String shipperAccount) {
        this.shipperAccount = shipperAccount;
    }

    public String getShipperReference() {
        return shipperReference;
    }

    public void setShipperReference(String shipperReference) {
        this.shipperReference = shipperReference;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getWeightType() {
        return weightType;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
    }

    public String getSelContents() {
        return selContents;
    }

    public void setSelContents(String selContents) {
        this.selContents = selContents;
    }

    public List<PieceModel> getShipmentRequestPieces() {
        return shipmentRequestPieces;
    }

    public void setShipmentRequestPieces(List<PieceModel> shipmentRequestPieces) {
        this.shipmentRequestPieces = shipmentRequestPieces;
    }

    @Override
	public String toString() {
		return "ShipmentInfoModel [senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress
				+ ", serviceId=" + serviceId + ", shipmentTypeId=" + shipmentTypeId + ", contentType=" + contentType
				+ ", currency=" + currency + ", pieces=" + pieces + ", bound=" + bound + ", isAddPiece=" + isAddPiece
				+ ", residentialDelivery=" + residentialDelivery + ", residentialPickup=" + residentialPickup
				+ ", addCons=" + addCons + ", shipperAccount=" + shipperAccount + ", shipperReference="
				+ shipperReference + ", totalWeight=" + totalWeight + ", weightType=" + weightType + ", selContents="
				+ selContents + ", shipmentRequestPieces=" + shipmentRequestPieces + ", dhInterAccout=" + dhInterAccout
				+ ", dutiesBillTo=" + dutiesBillTo + ", dutiesAccount=" + dutiesAccount + ", billingParty="
				+ billingParty + ", specialDelivery=" + specialDelivery + ", isSaveSenderAddressBook="
				+ isSaveSenderAddressBook + ", isSaveRecipientAddressBook=" + isSaveRecipientAddressBook
				+ ", insuranceUserAmount=" + insuranceUserAmount + ", packageId=" + packageId
				+ ", errorMsg=" + errorMsg + ", defaultCarrierType=" + defaultCarrierType + ", defaultServiceType="
				+ defaultServiceType + ", defaultPackageType=" + defaultPackageType + "]";
	}

    public String getDefaultServiceType() {
        return defaultServiceType;
    }

    public void setDefaultServiceType(String defaultServiceType) {
        this.defaultServiceType = defaultServiceType;
    }

    public String getDefaultPackageType() {
        return defaultPackageType;
    }

    public void setDefaultPackageType(String defaultPackageType) {
        this.defaultPackageType = defaultPackageType;
    }

    public String getDefaultCarrierType() {
        return defaultCarrierType;
    }

    public void setDefaultCarrierType(String defaultCarrierType) {
        this.defaultCarrierType = defaultCarrierType;
    }

	public String getInsuranceUserAmount() {
		return insuranceUserAmount;
	}

	public void setInsuranceUserAmount(String insuranceUserAmount) {
		this.insuranceUserAmount = insuranceUserAmount;
	}

	@Override
	public String getPackageId() {
		return packageId;
	}

	@Override
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
    
    
}
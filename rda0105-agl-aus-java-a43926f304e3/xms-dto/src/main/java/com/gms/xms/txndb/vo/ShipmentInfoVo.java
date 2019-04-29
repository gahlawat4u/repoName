package com.gms.xms.txndb.vo;

import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from ShipmentInfoVo
 * <p>
 * Author HungNT Date Jul 22, 2015
 */
public class ShipmentInfoVo extends ShipmentVo {
    private static final long serialVersionUID = -6939154910792022237L;
    private AddressVo senderAddress;
    private AddressVo receiverAddress;
    private Integer serviceId;
    private Integer shipmentTypeId;
    private String contentType;
    private String currency;
    private List<PieceVo> pieces;
    private Integer bound;
    private String isAddPiece;
    private Boolean residentialDelivery;
    private Boolean residentialPickup;
    private List<ServiceAddConVo> addCons;
    private String shipperAccount;
    private String shipperReference;
    private Double totalWeight;
    private String weightType;
    private String selContents;
    private List<PieceVo> shipmentRequestPieces;
    private String dhInterAccout;
    private String dutiesBillTo;
    private String dutiesAccount;
    private String billingParty;
    private String specialDelivery;

    private Integer isSaveSenderAddressBook;
    private Integer isSaveRecipientAddressBook;

    private String defaultCarrierType;

    private String defaultServiceType;

    private String defaultPackageType;
    
    private String insuranceUserAmount;
    private Integer packageId;
    private String errorMsg;

    public Integer getIsSaveSenderAddressBook() {
        return isSaveSenderAddressBook;
    }

    public void setIsSaveSenderAddressBook(Integer isSaveSenderAddressBook) {
        this.isSaveSenderAddressBook = isSaveSenderAddressBook;
    }

    public Integer getIsSaveRecipientAddressBook() {
        return isSaveRecipientAddressBook;
    }

    public void setIsSaveRecipientAddressBook(Integer isSaveRecipientAddressBook) {
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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    @Override
    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<PieceVo> getPieces() {
        return pieces;
    }

    public void setPieces(List<PieceVo> pieces) {
        this.pieces = pieces;
    }

    public Integer getBound() {
        return bound;
    }

    public void setBound(Integer bound) {
        this.bound = bound;
    }

    public String getIsAddPiece() {
        return isAddPiece;
    }

    public void setIsAddPiece(String isAddPiece) {
        this.isAddPiece = isAddPiece;
    }

    public Boolean getResidentialDelivery() {
        return residentialDelivery;
    }

    public void setResidentialDelivery(Boolean residentialDelivery) {
        this.residentialDelivery = residentialDelivery;
    }

    public Boolean getResidentialPickup() {
        return residentialPickup;
    }

    public void setResidentialPickup(Boolean residentialPickup) {
        this.residentialPickup = residentialPickup;
    }

    public List<ServiceAddConVo> getAddCons() {
        return addCons;
    }

    public void setAddCons(List<ServiceAddConVo> addCons) {
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

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
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

    public List<PieceVo> getShipmentRequestPieces() {
        return shipmentRequestPieces;
    }

    public void setShipmentRequestPieces(List<PieceVo> shipmentRequestPieces) {
        this.shipmentRequestPieces = shipmentRequestPieces;
    }

    @JsonIgnore
    public Map<String, ServiceAddConVo> getServiceAddConMap() {
        Map<String, ServiceAddConVo> serviceAddCon = new HashMap<String, ServiceAddConVo>();
        if (!this.addCons.isEmpty()) {
            for (ServiceAddConVo serviceAddConVo : this.addCons) {
                serviceAddCon.put(serviceAddConVo.getAddConCode(), serviceAddConVo);
            }
        }
        return serviceAddCon;
    }

    @Override
	public String toString() {
		return "ShipmentInfoVo [senderAddress=" + senderAddress + ", receiverAddress=" + receiverAddress
				+ ", serviceId=" + serviceId + ", shipmentTypeId=" + shipmentTypeId + ", contentType=" + contentType
				+ ", currency=" + currency + ", pieces=" + pieces + ", bound=" + bound + ", isAddPiece=" + isAddPiece
				+ ", residentialDelivery=" + residentialDelivery + ", residentialPickup=" + residentialPickup
				+ ", addCons=" + addCons + ", shipperAccount=" + shipperAccount + ", shipperReference="
				+ shipperReference + ", totalWeight=" + totalWeight + ", weightType=" + weightType + ", selContents="
				+ selContents + ", shipmentRequestPieces=" + shipmentRequestPieces + ", dhInterAccout=" + dhInterAccout
				+ ", dutiesBillTo=" + dutiesBillTo + ", dutiesAccount=" + dutiesAccount + ", billingParty="
				+ billingParty + ", specialDelivery=" + specialDelivery + ", isSaveSenderAddressBook="
				+ isSaveSenderAddressBook + ", isSaveRecipientAddressBook=" + isSaveRecipientAddressBook
				+ ", defaultCarrierType=" + defaultCarrierType + ", defaultServiceType=" + defaultServiceType
				+ ", defaultPackageType=" + defaultPackageType + ", insuranceUserAmount=" + insuranceUserAmount
				+ ", packageId=" + packageId + ", errorMsg=" + errorMsg + "]";
	}

    public String getDefaultCarrierType() {
        return defaultCarrierType;
    }

    public void setDefaultCarrierType(String defaultCarrierType) {
        this.defaultCarrierType = defaultCarrierType;
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

	public String getInsuranceUserAmount() {
		return insuranceUserAmount;
	}

	public void setInsuranceUserAmount(String insuranceUserAmount) {
		this.insuranceUserAmount = insuranceUserAmount;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
    
}

package com.gms.xms.txndb.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gms.xms.common.json.JsonBigDecimalVo2ModelSerializer;
import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;

/**
 * Posted from ShipmentVo
 * <p>
 * Author TanDT Date Jul 7, 2015
 */
public class ShipmentVo extends BaseVo {

    private static final long serialVersionUID = -6540756605377243546L;

    private Long shipmentId;

    private Long customerCode;

    private Long webshipId;

    private String airbillNumber;

    private Date createDate;

    private Date shipmentDate;

    private BigDecimal nonStandardCharge;

    private BigDecimal manualHandlingSurcharge;

    private Integer senderAddressId;

    private Integer receiverAddressId;

    private Integer shipmentTypeId;

    private Integer packageId;

    private Integer contents;

    private String weightUnit;

    private String dimensionUnit;

    private Integer noOfPieces;

    private BigDecimal withInsurance;

    private BigDecimal baseCharge;

    private BigDecimal carrierCost;

    private Integer day;

    private String dhlNote;

    private String billingCode;

    private String currencyCode;

    private String courierMessage;

    private String dhlRoutingCode;

    private String awbBarcode;

    private String originDestiBarcode;

    private String dhlRoutingBarcode;

    private String productContentCode;

    private Integer status;

    private String contentDescription;

    private BigDecimal totalCustomValue;

    private Integer commercialInvoiceId;

    private Integer collectionTypeId;

    private Integer billingType;

    private String billingAccount;

    private Integer dutiesType;

    private String dutiesAccount;

    private String termOfTrade;

    private String serviceAreaCodeDestination;

    private String serviceAreaCodeOrigin;

    private String receiverTaxId;

    private String reasonForExport;

    private String specialDeliveryinst;

    private String reference;

    private String reference2;

    private String reference3;

    private String receivedBy;

    private Date receivedDate;

    private String freightClass;

    private Integer rewiightWeight;

    private String actualDimension;

    private BigDecimal franchiseBaseCharge;

    private Date modifiedDate;

    private Integer packingList;

    private Integer boundStatus;

    private Long salesRepId;

    private Integer carrierPayment;

    private Long oldCustomerCode;

    private String globalProductCode;

    private String dhlRoutingDataId;

    private String awbProductContentCode;

    private String internalServiceCode;

    private String deliveryDateCode;

    private String deliveryTimeCode;

    private String zone;

    private String labelPdf;

    private String manifestPdf;

    private List<String> upsAirbillNo ;
    
    private List<String> upsAwbBarCode ;
    
    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Long getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(Long webshipId) {
        this.webshipId = webshipId;
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

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getNonStandardCharge() {
        return nonStandardCharge;
    }

    public void setNonStandardCharge(BigDecimal nonStandardCharge) {
        this.nonStandardCharge = nonStandardCharge;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getManualHandlingSurcharge() {
        return manualHandlingSurcharge;
    }

    public void setManualHandlingSurcharge(BigDecimal manualHandlingSurcharge) {
        this.manualHandlingSurcharge = manualHandlingSurcharge;
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

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getContents() {
        return contents;
    }

    public void setContents(Integer contents) {
        this.contents = contents;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getWithInsurance() {
        return withInsurance;
    }

    public void setWithInsurance(BigDecimal withInsurance) {
        this.withInsurance = withInsurance;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(BigDecimal baseCharge) {
        this.baseCharge = baseCharge;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(BigDecimal carrierCost) {
        this.carrierCost = carrierCost;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDhlNote() {
        return dhlNote;
    }

    public void setDhlNote(String dhlNote) {
        this.dhlNote = dhlNote;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCourierMessage() {
        return courierMessage;
    }

    public void setCourierMessage(String courierMessage) {
        this.courierMessage = courierMessage;
    }

    public String getDhlRoutingCode() {
        return dhlRoutingCode;
    }

    public void setDhlRoutingCode(String dhlRoutingCode) {
        this.dhlRoutingCode = dhlRoutingCode;
    }

    public String getAwbBarcode() {
        return awbBarcode;
    }

    public void setAwbBarcode(String awbBarcode) {
        this.awbBarcode = awbBarcode;
    }

    public String getOriginDestiBarcode() {
        return originDestiBarcode;
    }

    public void setOriginDestiBarcode(String originDestiBarcode) {
        this.originDestiBarcode = originDestiBarcode;
    }

    public String getDhlRoutingBarcode() {
        return dhlRoutingBarcode;
    }

    public void setDhlRoutingBarcode(String dhlRoutingBarcode) {
        this.dhlRoutingBarcode = dhlRoutingBarcode;
    }

    public String getProductContentCode() {
        return productContentCode;
    }

    public void setProductContentCode(String productContentCode) {
        this.productContentCode = productContentCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getTotalCustomValue() {
        return totalCustomValue;
    }

    public void setTotalCustomValue(BigDecimal totalCustomValue) {
        this.totalCustomValue = totalCustomValue;
    }

    public Integer getCommercialInvoiceId() {
        return commercialInvoiceId;
    }

    public void setCommercialInvoiceId(Integer commercialInvoiceId) {
        this.commercialInvoiceId = commercialInvoiceId;
    }

    public Integer getCollectionTypeId() {
        return collectionTypeId;
    }

    public void setCollectionTypeId(Integer collectionTypeId) {
        this.collectionTypeId = collectionTypeId;
    }

    public Integer getBillingType() {
        return billingType;
    }

    public void setBillingType(Integer billingType) {
        this.billingType = billingType;
    }

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount;
    }

    public Integer getDutiesType() {
        return dutiesType;
    }

    public void setDutiesType(Integer dutiesType) {
        this.dutiesType = dutiesType;
    }

    public String getDutiesAccount() {
        return dutiesAccount;
    }

    public void setDutiesAccount(String dutiesAccount) {
        this.dutiesAccount = dutiesAccount;
    }

    public String getTermOfTrade() {
        return termOfTrade;
    }

    public void setTermOfTrade(String termOfTrade) {
        this.termOfTrade = termOfTrade;
    }

    public String getServiceAreaCodeDestination() {
        return serviceAreaCodeDestination;
    }

    public void setServiceAreaCodeDestination(String serviceAreaCodeDestination) {
        this.serviceAreaCodeDestination = serviceAreaCodeDestination;
    }

    public String getServiceAreaCodeOrigin() {
        return serviceAreaCodeOrigin;
    }

    public void setServiceAreaCodeOrigin(String serviceAreaCodeOrigin) {
        this.serviceAreaCodeOrigin = serviceAreaCodeOrigin;
    }

    public String getReceiverTaxId() {
        return receiverTaxId;
    }

    public void setReceiverTaxId(String receiverTaxId) {
        this.receiverTaxId = receiverTaxId;
    }

    public String getReasonForExport() {
        return reasonForExport;
    }

    public void setReasonForExport(String reasonForExport) {
        this.reasonForExport = reasonForExport;
    }

    public String getSpecialDeliveryinst() {
        return specialDeliveryinst;
    }

    public void setSpecialDeliveryinst(String specialDeliveryinst) {
        this.specialDeliveryinst = specialDeliveryinst;
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

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getFreightClass() {
        return freightClass;
    }

    public void setFreightClass(String freightClass) {
        this.freightClass = freightClass;
    }

    public Integer getRewiightWeight() {
        return rewiightWeight;
    }

    public void setRewiightWeight(Integer rewiightWeight) {
        this.rewiightWeight = rewiightWeight;
    }

    public String getActualDimension() {
        return actualDimension;
    }

    public void setActualDimension(String actualDimension) {
        this.actualDimension = actualDimension;
    }

    @JsonSerialize(using = JsonBigDecimalVo2ModelSerializer.class)
    public BigDecimal getFranchiseBaseCharge() {
        return franchiseBaseCharge;
    }

    public void setFranchiseBaseCharge(BigDecimal franchiseBaseCharge) {
        this.franchiseBaseCharge = franchiseBaseCharge;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getPackingList() {
        return packingList;
    }

    public void setPackingList(Integer packingList) {
        this.packingList = packingList;
    }

    public Integer getBoundStatus() {
        return boundStatus;
    }

    public void setBoundStatus(Integer boundStatus) {
        this.boundStatus = boundStatus;
    }

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Integer getCarrierPayment() {
        return carrierPayment;
    }

    public void setCarrierPayment(Integer carrierPayment) {
        this.carrierPayment = carrierPayment;
    }

    public Long getOldCustomerCode() {
        return oldCustomerCode;
    }

    public void setOldCustomerCode(Long oldCustomerCode) {
        this.oldCustomerCode = oldCustomerCode;
    }

    public String getGlobalProductCode() {
        return globalProductCode;
    }

    public void setGlobalProductCode(String globalProductCode) {
        this.globalProductCode = globalProductCode;
    }

    public String getDhlRoutingDataId() {
        return dhlRoutingDataId;
    }

    public void setDhlRoutingDataId(String dhlRoutingDataId) {
        this.dhlRoutingDataId = dhlRoutingDataId;
    }

    public String getAwbProductContentCode() {
        return awbProductContentCode;
    }

    public void setAwbProductContentCode(String awbProductContentCode) {
        this.awbProductContentCode = awbProductContentCode;
    }

    public String getInternalServiceCode() {
        return internalServiceCode;
    }

    public void setInternalServiceCode(String internalServiceCode) {
        this.internalServiceCode = internalServiceCode;
    }

    public String getDeliveryDateCode() {
        return deliveryDateCode;
    }

    public void setDeliveryDateCode(String deliveryDateCode) {
        this.deliveryDateCode = deliveryDateCode;
    }

    public String getDeliveryTimeCode() {
        return deliveryTimeCode;
    }

    public void setDeliveryTimeCode(String deliveryTimeCode) {
        this.deliveryTimeCode = deliveryTimeCode;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLabelPdf() {
        return labelPdf;
    }

    public void setLabelPdf(String labelPdf) {
        this.labelPdf = labelPdf;
    }

    public String getManifestPdf() {
        return manifestPdf;
    }

    public void setManifestPdf(String manifestPdf) {
        this.manifestPdf = manifestPdf;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actualDimension == null) ? 0 : actualDimension.hashCode());
		result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
		result = prime * result + ((awbBarcode == null) ? 0 : awbBarcode.hashCode());
		result = prime * result + ((awbProductContentCode == null) ? 0 : awbProductContentCode.hashCode());
		result = prime * result + ((baseCharge == null) ? 0 : baseCharge.hashCode());
		result = prime * result + ((billingAccount == null) ? 0 : billingAccount.hashCode());
		result = prime * result + ((billingCode == null) ? 0 : billingCode.hashCode());
		result = prime * result + ((billingType == null) ? 0 : billingType.hashCode());
		result = prime * result + ((boundStatus == null) ? 0 : boundStatus.hashCode());
		result = prime * result + ((carrierCost == null) ? 0 : carrierCost.hashCode());
		result = prime * result + ((carrierPayment == null) ? 0 : carrierPayment.hashCode());
		result = prime * result + ((collectionTypeId == null) ? 0 : collectionTypeId.hashCode());
		result = prime * result + ((commercialInvoiceId == null) ? 0 : commercialInvoiceId.hashCode());
		result = prime * result + ((contentDescription == null) ? 0 : contentDescription.hashCode());
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((courierMessage == null) ? 0 : courierMessage.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((customerCode == null) ? 0 : customerCode.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((deliveryDateCode == null) ? 0 : deliveryDateCode.hashCode());
		result = prime * result + ((deliveryTimeCode == null) ? 0 : deliveryTimeCode.hashCode());
		result = prime * result + ((dhlNote == null) ? 0 : dhlNote.hashCode());
		result = prime * result + ((dhlRoutingBarcode == null) ? 0 : dhlRoutingBarcode.hashCode());
		result = prime * result + ((dhlRoutingCode == null) ? 0 : dhlRoutingCode.hashCode());
		result = prime * result + ((dhlRoutingDataId == null) ? 0 : dhlRoutingDataId.hashCode());
		result = prime * result + ((dimensionUnit == null) ? 0 : dimensionUnit.hashCode());
		result = prime * result + ((dutiesAccount == null) ? 0 : dutiesAccount.hashCode());
		result = prime * result + ((dutiesType == null) ? 0 : dutiesType.hashCode());
		result = prime * result + ((franchiseBaseCharge == null) ? 0 : franchiseBaseCharge.hashCode());
		result = prime * result + ((freightClass == null) ? 0 : freightClass.hashCode());
		result = prime * result + ((globalProductCode == null) ? 0 : globalProductCode.hashCode());
		result = prime * result + ((internalServiceCode == null) ? 0 : internalServiceCode.hashCode());
		result = prime * result + ((labelPdf == null) ? 0 : labelPdf.hashCode());
		result = prime * result + ((manifestPdf == null) ? 0 : manifestPdf.hashCode());
		result = prime * result + ((manualHandlingSurcharge == null) ? 0 : manualHandlingSurcharge.hashCode());
		result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result + ((noOfPieces == null) ? 0 : noOfPieces.hashCode());
		result = prime * result + ((nonStandardCharge == null) ? 0 : nonStandardCharge.hashCode());
		result = prime * result + ((oldCustomerCode == null) ? 0 : oldCustomerCode.hashCode());
		result = prime * result + ((originDestiBarcode == null) ? 0 : originDestiBarcode.hashCode());
		result = prime * result + ((packageId == null) ? 0 : packageId.hashCode());
		result = prime * result + ((packingList == null) ? 0 : packingList.hashCode());
		result = prime * result + ((productContentCode == null) ? 0 : productContentCode.hashCode());
		result = prime * result + ((reasonForExport == null) ? 0 : reasonForExport.hashCode());
		result = prime * result + ((receivedBy == null) ? 0 : receivedBy.hashCode());
		result = prime * result + ((receivedDate == null) ? 0 : receivedDate.hashCode());
		result = prime * result + ((receiverAddressId == null) ? 0 : receiverAddressId.hashCode());
		result = prime * result + ((receiverTaxId == null) ? 0 : receiverTaxId.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((reference2 == null) ? 0 : reference2.hashCode());
		result = prime * result + ((reference3 == null) ? 0 : reference3.hashCode());
		result = prime * result + ((rewiightWeight == null) ? 0 : rewiightWeight.hashCode());
		result = prime * result + ((salesRepId == null) ? 0 : salesRepId.hashCode());
		result = prime * result + ((senderAddressId == null) ? 0 : senderAddressId.hashCode());
		result = prime * result + ((serviceAreaCodeDestination == null) ? 0 : serviceAreaCodeDestination.hashCode());
		result = prime * result + ((serviceAreaCodeOrigin == null) ? 0 : serviceAreaCodeOrigin.hashCode());
		result = prime * result + ((shipmentDate == null) ? 0 : shipmentDate.hashCode());
		result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
		result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
		result = prime * result + ((specialDeliveryinst == null) ? 0 : specialDeliveryinst.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((termOfTrade == null) ? 0 : termOfTrade.hashCode());
		result = prime * result + ((totalCustomValue == null) ? 0 : totalCustomValue.hashCode());
		result = prime * result + ((upsAirbillNo == null) ? 0 : upsAirbillNo.hashCode());
		result = prime * result + ((upsAwbBarCode == null) ? 0 : upsAwbBarCode.hashCode());
		result = prime * result + ((webshipId == null) ? 0 : webshipId.hashCode());
		result = prime * result + ((weightUnit == null) ? 0 : weightUnit.hashCode());
		result = prime * result + ((withInsurance == null) ? 0 : withInsurance.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShipmentVo other = (ShipmentVo) obj;
		if (actualDimension == null) {
			if (other.actualDimension != null)
				return false;
		} else if (!actualDimension.equals(other.actualDimension))
			return false;
		if (airbillNumber == null) {
			if (other.airbillNumber != null)
				return false;
		} else if (!airbillNumber.equals(other.airbillNumber))
			return false;
		if (awbBarcode == null) {
			if (other.awbBarcode != null)
				return false;
		} else if (!awbBarcode.equals(other.awbBarcode))
			return false;
		if (awbProductContentCode == null) {
			if (other.awbProductContentCode != null)
				return false;
		} else if (!awbProductContentCode.equals(other.awbProductContentCode))
			return false;
		if (baseCharge == null) {
			if (other.baseCharge != null)
				return false;
		} else if (!baseCharge.equals(other.baseCharge))
			return false;
		if (billingAccount == null) {
			if (other.billingAccount != null)
				return false;
		} else if (!billingAccount.equals(other.billingAccount))
			return false;
		if (billingCode == null) {
			if (other.billingCode != null)
				return false;
		} else if (!billingCode.equals(other.billingCode))
			return false;
		if (billingType == null) {
			if (other.billingType != null)
				return false;
		} else if (!billingType.equals(other.billingType))
			return false;
		if (boundStatus == null) {
			if (other.boundStatus != null)
				return false;
		} else if (!boundStatus.equals(other.boundStatus))
			return false;
		if (carrierCost == null) {
			if (other.carrierCost != null)
				return false;
		} else if (!carrierCost.equals(other.carrierCost))
			return false;
		if (carrierPayment == null) {
			if (other.carrierPayment != null)
				return false;
		} else if (!carrierPayment.equals(other.carrierPayment))
			return false;
		if (collectionTypeId == null) {
			if (other.collectionTypeId != null)
				return false;
		} else if (!collectionTypeId.equals(other.collectionTypeId))
			return false;
		if (commercialInvoiceId == null) {
			if (other.commercialInvoiceId != null)
				return false;
		} else if (!commercialInvoiceId.equals(other.commercialInvoiceId))
			return false;
		if (contentDescription == null) {
			if (other.contentDescription != null)
				return false;
		} else if (!contentDescription.equals(other.contentDescription))
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (courierMessage == null) {
			if (other.courierMessage != null)
				return false;
		} else if (!courierMessage.equals(other.courierMessage))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (currencyCode == null) {
			if (other.currencyCode != null)
				return false;
		} else if (!currencyCode.equals(other.currencyCode))
			return false;
		if (customerCode == null) {
			if (other.customerCode != null)
				return false;
		} else if (!customerCode.equals(other.customerCode))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (deliveryDateCode == null) {
			if (other.deliveryDateCode != null)
				return false;
		} else if (!deliveryDateCode.equals(other.deliveryDateCode))
			return false;
		if (deliveryTimeCode == null) {
			if (other.deliveryTimeCode != null)
				return false;
		} else if (!deliveryTimeCode.equals(other.deliveryTimeCode))
			return false;
		if (dhlNote == null) {
			if (other.dhlNote != null)
				return false;
		} else if (!dhlNote.equals(other.dhlNote))
			return false;
		if (dhlRoutingBarcode == null) {
			if (other.dhlRoutingBarcode != null)
				return false;
		} else if (!dhlRoutingBarcode.equals(other.dhlRoutingBarcode))
			return false;
		if (dhlRoutingCode == null) {
			if (other.dhlRoutingCode != null)
				return false;
		} else if (!dhlRoutingCode.equals(other.dhlRoutingCode))
			return false;
		if (dhlRoutingDataId == null) {
			if (other.dhlRoutingDataId != null)
				return false;
		} else if (!dhlRoutingDataId.equals(other.dhlRoutingDataId))
			return false;
		if (dimensionUnit == null) {
			if (other.dimensionUnit != null)
				return false;
		} else if (!dimensionUnit.equals(other.dimensionUnit))
			return false;
		if (dutiesAccount == null) {
			if (other.dutiesAccount != null)
				return false;
		} else if (!dutiesAccount.equals(other.dutiesAccount))
			return false;
		if (dutiesType == null) {
			if (other.dutiesType != null)
				return false;
		} else if (!dutiesType.equals(other.dutiesType))
			return false;
		if (franchiseBaseCharge == null) {
			if (other.franchiseBaseCharge != null)
				return false;
		} else if (!franchiseBaseCharge.equals(other.franchiseBaseCharge))
			return false;
		if (freightClass == null) {
			if (other.freightClass != null)
				return false;
		} else if (!freightClass.equals(other.freightClass))
			return false;
		if (globalProductCode == null) {
			if (other.globalProductCode != null)
				return false;
		} else if (!globalProductCode.equals(other.globalProductCode))
			return false;
		if (internalServiceCode == null) {
			if (other.internalServiceCode != null)
				return false;
		} else if (!internalServiceCode.equals(other.internalServiceCode))
			return false;
		if (labelPdf == null) {
			if (other.labelPdf != null)
				return false;
		} else if (!labelPdf.equals(other.labelPdf))
			return false;
		if (manifestPdf == null) {
			if (other.manifestPdf != null)
				return false;
		} else if (!manifestPdf.equals(other.manifestPdf))
			return false;
		if (manualHandlingSurcharge == null) {
			if (other.manualHandlingSurcharge != null)
				return false;
		} else if (!manualHandlingSurcharge.equals(other.manualHandlingSurcharge))
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		if (noOfPieces == null) {
			if (other.noOfPieces != null)
				return false;
		} else if (!noOfPieces.equals(other.noOfPieces))
			return false;
		if (nonStandardCharge == null) {
			if (other.nonStandardCharge != null)
				return false;
		} else if (!nonStandardCharge.equals(other.nonStandardCharge))
			return false;
		if (oldCustomerCode == null) {
			if (other.oldCustomerCode != null)
				return false;
		} else if (!oldCustomerCode.equals(other.oldCustomerCode))
			return false;
		if (originDestiBarcode == null) {
			if (other.originDestiBarcode != null)
				return false;
		} else if (!originDestiBarcode.equals(other.originDestiBarcode))
			return false;
		if (packageId == null) {
			if (other.packageId != null)
				return false;
		} else if (!packageId.equals(other.packageId))
			return false;
		if (packingList == null) {
			if (other.packingList != null)
				return false;
		} else if (!packingList.equals(other.packingList))
			return false;
		if (productContentCode == null) {
			if (other.productContentCode != null)
				return false;
		} else if (!productContentCode.equals(other.productContentCode))
			return false;
		if (reasonForExport == null) {
			if (other.reasonForExport != null)
				return false;
		} else if (!reasonForExport.equals(other.reasonForExport))
			return false;
		if (receivedBy == null) {
			if (other.receivedBy != null)
				return false;
		} else if (!receivedBy.equals(other.receivedBy))
			return false;
		if (receivedDate == null) {
			if (other.receivedDate != null)
				return false;
		} else if (!receivedDate.equals(other.receivedDate))
			return false;
		if (receiverAddressId == null) {
			if (other.receiverAddressId != null)
				return false;
		} else if (!receiverAddressId.equals(other.receiverAddressId))
			return false;
		if (receiverTaxId == null) {
			if (other.receiverTaxId != null)
				return false;
		} else if (!receiverTaxId.equals(other.receiverTaxId))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (reference2 == null) {
			if (other.reference2 != null)
				return false;
		} else if (!reference2.equals(other.reference2))
			return false;
		if (reference3 == null) {
			if (other.reference3 != null)
				return false;
		} else if (!reference3.equals(other.reference3))
			return false;
		if (rewiightWeight == null) {
			if (other.rewiightWeight != null)
				return false;
		} else if (!rewiightWeight.equals(other.rewiightWeight))
			return false;
		if (salesRepId == null) {
			if (other.salesRepId != null)
				return false;
		} else if (!salesRepId.equals(other.salesRepId))
			return false;
		if (senderAddressId == null) {
			if (other.senderAddressId != null)
				return false;
		} else if (!senderAddressId.equals(other.senderAddressId))
			return false;
		if (serviceAreaCodeDestination == null) {
			if (other.serviceAreaCodeDestination != null)
				return false;
		} else if (!serviceAreaCodeDestination.equals(other.serviceAreaCodeDestination))
			return false;
		if (serviceAreaCodeOrigin == null) {
			if (other.serviceAreaCodeOrigin != null)
				return false;
		} else if (!serviceAreaCodeOrigin.equals(other.serviceAreaCodeOrigin))
			return false;
		if (shipmentDate == null) {
			if (other.shipmentDate != null)
				return false;
		} else if (!shipmentDate.equals(other.shipmentDate))
			return false;
		if (shipmentId == null) {
			if (other.shipmentId != null)
				return false;
		} else if (!shipmentId.equals(other.shipmentId))
			return false;
		if (shipmentTypeId == null) {
			if (other.shipmentTypeId != null)
				return false;
		} else if (!shipmentTypeId.equals(other.shipmentTypeId))
			return false;
		if (specialDeliveryinst == null) {
			if (other.specialDeliveryinst != null)
				return false;
		} else if (!specialDeliveryinst.equals(other.specialDeliveryinst))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (termOfTrade == null) {
			if (other.termOfTrade != null)
				return false;
		} else if (!termOfTrade.equals(other.termOfTrade))
			return false;
		if (totalCustomValue == null) {
			if (other.totalCustomValue != null)
				return false;
		} else if (!totalCustomValue.equals(other.totalCustomValue))
			return false;
		if (upsAirbillNo == null) {
			if (other.upsAirbillNo != null)
				return false;
		} else if (!upsAirbillNo.equals(other.upsAirbillNo))
			return false;
		if (upsAwbBarCode == null) {
			if (other.upsAwbBarCode != null)
				return false;
		} else if (!upsAwbBarCode.equals(other.upsAwbBarCode))
			return false;
		if (webshipId == null) {
			if (other.webshipId != null)
				return false;
		} else if (!webshipId.equals(other.webshipId))
			return false;
		if (weightUnit == null) {
			if (other.weightUnit != null)
				return false;
		} else if (!weightUnit.equals(other.weightUnit))
			return false;
		if (withInsurance == null) {
			if (other.withInsurance != null)
				return false;
		} else if (!withInsurance.equals(other.withInsurance))
			return false;
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone))
			return false;
		return true;
	}

	public List<String> getUpsAirbillNo() {
		return upsAirbillNo;
	}

	public void setUpsAirbillNo(List<String> upsAirbillNo) {
		this.upsAirbillNo = upsAirbillNo;
	}

	public List<String> getUpsAwbBarCode() {
		return upsAwbBarCode;
	}

	public void setUpsAwbBarCode(List<String> upsAwbBarCode) {
		this.upsAwbBarCode = upsAwbBarCode;
	}

	@Override
	public String toString() {
		return "ShipmentVo [shipmentId=" + shipmentId + ", customerCode=" + customerCode + ", webshipId=" + webshipId
				+ ", airbillNumber=" + airbillNumber + ", createDate=" + createDate + ", shipmentDate=" + shipmentDate
				+ ", nonStandardCharge=" + nonStandardCharge + ", manualHandlingSurcharge=" + manualHandlingSurcharge
				+ ", senderAddressId=" + senderAddressId + ", receiverAddressId=" + receiverAddressId
				+ ", shipmentTypeId=" + shipmentTypeId + ", packageId=" + packageId + ", contents=" + contents
				+ ", weightUnit=" + weightUnit + ", dimensionUnit=" + dimensionUnit + ", noOfPieces=" + noOfPieces
				+ ", withInsurance=" + withInsurance + ", baseCharge=" + baseCharge + ", carrierCost=" + carrierCost
				+ ", day=" + day + ", dhlNote=" + dhlNote + ", billingCode=" + billingCode + ", currencyCode="
				+ currencyCode + ", courierMessage=" + courierMessage + ", dhlRoutingCode=" + dhlRoutingCode
				+ ", awbBarcode=" + awbBarcode + ", originDestiBarcode=" + originDestiBarcode + ", dhlRoutingBarcode="
				+ dhlRoutingBarcode + ", productContentCode=" + productContentCode + ", status=" + status
				+ ", contentDescription=" + contentDescription + ", totalCustomValue=" + totalCustomValue
				+ ", commercialInvoiceId=" + commercialInvoiceId + ", collectionTypeId=" + collectionTypeId
				+ ", billingType=" + billingType + ", billingAccount=" + billingAccount + ", dutiesType=" + dutiesType
				+ ", dutiesAccount=" + dutiesAccount + ", termOfTrade=" + termOfTrade + ", serviceAreaCodeDestination="
				+ serviceAreaCodeDestination + ", serviceAreaCodeOrigin=" + serviceAreaCodeOrigin + ", receiverTaxId="
				+ receiverTaxId + ", reasonForExport=" + reasonForExport + ", specialDeliveryinst="
				+ specialDeliveryinst + ", reference=" + reference + ", reference2=" + reference2 + ", reference3="
				+ reference3 + ", receivedBy=" + receivedBy + ", receivedDate=" + receivedDate + ", freightClass="
				+ freightClass + ", rewiightWeight=" + rewiightWeight + ", actualDimension=" + actualDimension
				+ ", franchiseBaseCharge=" + franchiseBaseCharge + ", modifiedDate=" + modifiedDate + ", packingList="
				+ packingList + ", boundStatus=" + boundStatus + ", salesRepId=" + salesRepId + ", carrierPayment="
				+ carrierPayment + ", oldCustomerCode=" + oldCustomerCode + ", globalProductCode=" + globalProductCode
				+ ", dhlRoutingDataId=" + dhlRoutingDataId + ", awbProductContentCode=" + awbProductContentCode
				+ ", internalServiceCode=" + internalServiceCode + ", deliveryDateCode=" + deliveryDateCode
				+ ", deliveryTimeCode=" + deliveryTimeCode + ", zone=" + zone + ", labelPdf=" + labelPdf
				+ ", manifestPdf=" + manifestPdf + ", upsAirbillNo=" + upsAirbillNo + ", upsAwbBarCode=" + upsAwbBarCode
				+ "]";
	}

}
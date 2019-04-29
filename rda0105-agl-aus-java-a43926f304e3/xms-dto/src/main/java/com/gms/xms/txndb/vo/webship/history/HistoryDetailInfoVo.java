package com.gms.xms.txndb.vo.webship.history;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.common.json.JsonString2DoubleDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from HistoryDetailInfoVo
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailInfoVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 4758453033777900139L;
    private Long shipmentId;
    private String airbillNumber;
    private String courierMessage;
    private String contentDescription;
    private Integer sAddressId;
    private Integer rAddressId;
    private Float withInsurance;
    private Float nonStandardCharge;
    private Float manualHandlingSurcharge;
    private String serviceType;
    private String packageType;
    private String referenceNo;
    private String zone;
    private Integer noOfPieces;
    private String weightUnit;
    private Date shipmentDate;
    private String tracking;
    private String confirmationNo;
    private String dimWeight;
    private String actualWeight;
    private String billingCode;
    private String sCompanyName;
    private String sContactName;
    private String sAddress;
    private String sAddress2;
    private String sCity;
    private String sPostalCode;
    private String sState;
    private String sCountryName;
    private String sPhone;
    private String rCompanyName;
    private String rContactName;
    private String rAddress;
    private String rAddress2;
    private String rCity;
    private String serviceAreaCodeOrigin;
    private String awbProductContentCode;
    private String rPostalCode;
    private String rState;
    private String rCountryName;
    private String rCountryCode;
    private String rPhone;
    private Double baseCharge;
    private String schId;
    private String pickupDate;
    private String pickupTime;
    private String pickupTimeNoLater;
    private String totalWeight;
    private String specialInstructions;
    private String scAddressId;
    private String scConfirmationNo;
    private String location;
    private String locationCodeId;
    private String scDescription;
    private String createDate;
    private Integer serviceId;
    private Integer packageId;
    private Integer shipmentTypeId;
    private Integer scStatus;
    private Integer status;
    private String billingAccount;
    private String serviceCode;
    private String serviceGroup;
    private String connNumber;
    private String dhlRoutingCode;
    private String dhlRoutingDataId;
    private String customerCode;
    private String serviceName;
    private String zoneCode;
    private String zoneName;
    private String primaryPort;
    private String secondaryPort;
    private String productContentCode;
    private String originDepot;
    private String reasonForExport;
    private String depotcCode;
    private String receiverTaxId;
    private String gstId;
    private String termOfTrade;
    private String dimensionUnit;
    private String deliveryDateCode;
    private String deliveryTimeCode;
    private String internalServiceCode;
    private String awbBarcode;
    private String originDestiBarcode;
    private String dhlRoutingBarcode;
    private String serviceAreaCodeDestination;
    private Integer contents;
    private Double totalCustomValue;
    private String currencyCode;
    private Integer bound;
    private String specialDeliveryinst;

    private String weightType;


    public Integer getContents() {
        return contents;
    }

    public void setContents(Integer contents) {
        this.contents = contents;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalCustomValue() {
        return totalCustomValue;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setTotalCustomValue(Double totalCustomValue) {
        this.totalCustomValue = totalCustomValue;
    }

    public String getServiceAreaCodeDestination() {
        return serviceAreaCodeDestination;
    }

    public void setServiceAreaCodeDestination(String serviceAreaCodeDestination) {
        this.serviceAreaCodeDestination = serviceAreaCodeDestination;
    }

    public String getrCountryCode() {
        return rCountryCode;
    }

    public void setrCountryCode(String rCountryCode) {
        this.rCountryCode = rCountryCode;
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

    public String getServiceAreaCodeOrigin() {
        return serviceAreaCodeOrigin;
    }

    public void setServiceAreaCodeOrigin(String serviceAreaCodeOrigin) {
        this.serviceAreaCodeOrigin = serviceAreaCodeOrigin;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    public String getAwbProductContentCode() {
        return awbProductContentCode;
    }

    public void setAwbProductContentCode(String awbProductContentCode) {
        this.awbProductContentCode = awbProductContentCode;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getTermOfTrade() {
        return termOfTrade;
    }

    public void setTermOfTrade(String termOfTrade) {
        this.termOfTrade = termOfTrade;
    }

    public String getGstId() {
        return gstId;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public String getReceiverTaxId() {
        return receiverTaxId;
    }

    public void setReceiverTaxId(String receiverTaxId) {
        this.receiverTaxId = receiverTaxId;
    }

    public String getDepotcCode() {
        return depotcCode;
    }

    public void setDepotcCode(String depotcCode) {
        this.depotcCode = depotcCode;
    }

    public String getReasonForExport() {
        return reasonForExport;
    }

    public void setReasonForExport(String reasonForExport) {
        this.reasonForExport = reasonForExport;
    }

    public String getOriginDepot() {
        return originDepot;
    }

    public void setOriginDepot(String originDepot) {
        this.originDepot = originDepot;
    }

    public String getProductContentCode() {
        return productContentCode;
    }

    public void setProductContentCode(String productContentCode) {
        this.productContentCode = productContentCode;
    }

    public String getPrimaryPort() {
        return primaryPort;
    }

    public void setPrimaryPort(String primaryPort) {
        this.primaryPort = primaryPort;
    }

    public String getSecondaryPort() {
        return secondaryPort;
    }

    public void setSecondaryPort(String secondaryPort) {
        this.secondaryPort = secondaryPort;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getDhlRoutingDataId() {
        return dhlRoutingDataId;
    }

    public void setDhlRoutingDataId(String dhlRoutingDataId) {
        this.dhlRoutingDataId = dhlRoutingDataId;
    }

    public String getDhlRoutingCode() {
        return dhlRoutingCode;
    }

    public void setDhlRoutingCode(String dhlRoutingCode) {
        this.dhlRoutingCode = dhlRoutingCode;
    }

    public String getConnNumber() {
        return connNumber;
    }

    public void setConnNumber(String connNumber) {
        this.connNumber = connNumber;
    }

    public String getServiceGroup() {
        return serviceGroup;
    }

    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getScStatus() {
        return scStatus;
    }

    public void setScStatus(Integer scStatus) {
        this.scStatus = scStatus;
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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getSchId() {
        return schId;
    }

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Integer getsAddressId() {
        return sAddressId;
    }

    public void setsAddressId(Integer sAddressId) {
        this.sAddressId = sAddressId;
    }

    public Integer getrAddressId() {
        return rAddressId;
    }

    public void setrAddressId(Integer rAddressId) {
        this.rAddressId = rAddressId;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getPickupTimeNoLater() {
        return pickupTimeNoLater;
    }

    public void setPickupTimeNoLater(String pickupTimeNoLater) {
        this.pickupTimeNoLater = pickupTimeNoLater;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getScAddressId() {
        return scAddressId;
    }

    public void setScAddressId(String scAddressId) {
        this.scAddressId = scAddressId;
    }

    public String getScConfirmationNo() {
        return scConfirmationNo;
    }

    public void setScConfirmationNo(String scConfirmationNo) {
        this.scConfirmationNo = scConfirmationNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationCodeId() {
        return locationCodeId;
    }

    public void setLocationCodeId(String locationCodeId) {
        this.locationCodeId = locationCodeId;
    }

    public String getScDescription() {
        return scDescription;
    }

    public void setScDescription(String scDescription) {
        this.scDescription = scDescription;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public String getCourierMessage() {
        return courierMessage;
    }

    public void setCourierMessage(String courierMessage) {
        this.courierMessage = courierMessage;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getTracking() {
        return tracking;
    }

    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public String getDimWeight() {
        return dimWeight;
    }

    public void setDimWeight(String dimWeight) {
        this.dimWeight = dimWeight;
    }

    public String getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(String actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getsCompanyName() {
        return sCompanyName;
    }

    public void setsCompanyName(String sCompanyName) {
        this.sCompanyName = sCompanyName;
    }

    public String getsContactName() {
        return sContactName;
    }

    public void setsContactName(String sContactName) {
        this.sContactName = sContactName;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsAddress2() {
        return sAddress2;
    }

    public void setsAddress2(String sAddress2) {
        this.sAddress2 = sAddress2;
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity;
    }

    public String getsPostalCode() {
        return sPostalCode;
    }

    public void setsPostalCode(String sPostalCode) {
        this.sPostalCode = sPostalCode;
    }

    public String getsState() {
        return sState;
    }

    public void setsState(String sState) {
        this.sState = sState;
    }

    public String getsCountryName() {
        return sCountryName;
    }

    public void setsCountryName(String sCountryName) {
        this.sCountryName = sCountryName;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getrCompanyName() {
        return rCompanyName;
    }

    public void setrCompanyName(String rCompanyName) {
        this.rCompanyName = rCompanyName;
    }

    public String getrContactName() {
        return rContactName;
    }

    public void setrContactName(String rContactName) {
        this.rContactName = rContactName;
    }

    public String getrAddress() {
        return rAddress;
    }

    public void setrAddress(String rAddress) {
        this.rAddress = rAddress;
    }

    public String getrAddress2() {
        return rAddress2;
    }

    public void setrAddress2(String rAddress2) {
        this.rAddress2 = rAddress2;
    }

    public String getrCity() {
        return rCity;
    }

    public void setrCity(String rCity) {
        this.rCity = rCity;
    }

    public String getrPostalCode() {
        return rPostalCode;
    }

    public void setrPostalCode(String rPostalCode) {
        this.rPostalCode = rPostalCode;
    }

    public String getrState() {
        return rState;
    }

    public void setrState(String rState) {
        this.rState = rState;
    }

    public String getrCountryName() {
        return rCountryName;
    }

    public void setrCountryName(String rCountryName) {
        this.rCountryName = rCountryName;
    }

    public String getrPhone() {
        return rPhone;
    }

    public void setrPhone(String rPhone) {
        this.rPhone = rPhone;
    }

    public Float getWithInsurance() {
        return withInsurance;
    }

    public void setWithInsurance(Float withInsurance) {
        this.withInsurance = withInsurance;
    }

    public Float getNonStandardCharge() {
        return nonStandardCharge;
    }

    public void setNonStandardCharge(Float nonStandardCharge) {
        this.nonStandardCharge = nonStandardCharge;
    }

    public Float getManualHandlingSurcharge() {
        return manualHandlingSurcharge;
    }

    public void setManualHandlingSurcharge(Float manualHandlingSurcharge) {
        this.manualHandlingSurcharge = manualHandlingSurcharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getBaseCharge() {
        return baseCharge;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getBound() {
        return bound;
    }

    public void setBound(Integer bound) {
        this.bound = bound;
    }

    public String getSpecialDeliveryinst() {
        return specialDeliveryinst;
    }

    public void setSpecialDeliveryinst(String specialDeliveryinst) {
        this.specialDeliveryinst = specialDeliveryinst;
    }

    public String getWeightType() {
        return weightType;
    }

    public void setWeightType(String weightType) {
        this.weightType = weightType;
    }

    @Override
    public String toString() {
        return "HistoryDetailInfoVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", courierMessage=" + courierMessage + ", contentDescription=" + contentDescription + ", sAddressId=" + sAddressId + ", rAddressId=" + rAddressId + ", withInsurance=" + withInsurance + ", nonStandardCharge=" + nonStandardCharge + ", manualHandlingSurcharge=" + manualHandlingSurcharge + ", serviceType=" + serviceType + ", packageType=" + packageType + ", referenceNo=" + referenceNo + ", zone=" + zone + ", noOfPieces=" + noOfPieces + ", weightUnit="
                + weightUnit + ", shipmentDate=" + shipmentDate + ", tracking=" + tracking + ", confirmationNo=" + confirmationNo + ", dimWeight=" + dimWeight + ", actualWeight=" + actualWeight + ", billingCode=" + billingCode + ", sCompanyName=" + sCompanyName + ", sContactName=" + sContactName + ", sAddress=" + sAddress + ", sAddress2=" + sAddress2 + ", sCity=" + sCity + ", sPostalCode=" + sPostalCode + ", sState=" + sState + ", sCountryName=" + sCountryName + ", sPhone=" + sPhone
                + ", rCompanyName=" + rCompanyName + ", rContactName=" + rContactName + ", rAddress=" + rAddress + ", rAddress2=" + rAddress2 + ", rCity=" + rCity + ", serviceAreaCodeOrigin=" + serviceAreaCodeOrigin + ", awbProductContentCode=" + awbProductContentCode + ", rPostalCode=" + rPostalCode + ", rState=" + rState + ", rCountryName=" + rCountryName + ", rCountryCode=" + rCountryCode + ", rPhone=" + rPhone + ", baseCharge=" + baseCharge + ", schId=" + schId + ", pickupDate=" + pickupDate
                + ", pickupTime=" + pickupTime + ", pickupTimeNoLater=" + pickupTimeNoLater + ", totalWeight=" + totalWeight + ", specialInstructions=" + specialInstructions + ", scAddressId=" + scAddressId + ", scConfirmationNo=" + scConfirmationNo + ", location=" + location + ", locationCodeId=" + locationCodeId + ", scDescription=" + scDescription + ", createDate=" + createDate + ", serviceId=" + serviceId + ", packageId=" + packageId + ", shipmentTypeId=" + shipmentTypeId + ", scStatus="
                + scStatus + ", status=" + status + ", billingAccount=" + billingAccount + ", serviceCode=" + serviceCode + ", serviceGroup=" + serviceGroup + ", connNumber=" + connNumber + ", dhlRoutingCode=" + dhlRoutingCode + ", dhlRoutingDataId=" + dhlRoutingDataId + ", customerCode=" + customerCode + ", serviceName=" + serviceName + ", zoneCode=" + zoneCode + ", zoneName=" + zoneName + ", primaryPort=" + primaryPort + ", secondaryPort=" + secondaryPort + ", productContentCode="
                + productContentCode + ", originDepot=" + originDepot + ", reasonForExport=" + reasonForExport + ", depotcCode=" + depotcCode + ", receiverTaxId=" + receiverTaxId + ", gstId=" + gstId + ", termOfTrade=" + termOfTrade + ", dimensionUnit=" + dimensionUnit + ", deliveryDateCode=" + deliveryDateCode + ", deliveryTimeCode=" + deliveryTimeCode + ", internalServiceCode=" + internalServiceCode + ", awbBarcode=" + awbBarcode + ", originDestiBarcode=" + originDestiBarcode
                + ", dhlRoutingBarcode=" + dhlRoutingBarcode + ", serviceAreaCodeDestination=" + serviceAreaCodeDestination + ", contents=" + contents + ", totalCustomValue=" + totalCustomValue + ", currencyCode=" + currencyCode + ", bound=" + bound + "]";
    }
}

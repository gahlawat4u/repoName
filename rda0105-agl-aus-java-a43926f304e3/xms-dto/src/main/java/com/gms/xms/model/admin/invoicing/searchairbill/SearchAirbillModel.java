package com.gms.xms.model.admin.invoicing.searchairbill;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * File Name: SearchAirbillModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 21-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.searchairbill <br/>
 */
public class SearchAirbillModel extends BaseModel {
    private static final long serialVersionUID = 2208364713111949606L;
    private String rateType;
    private String senderCountryId;
    private String receiverCountryId;
    private String contents;
    private String shipmentTypeId;
    private String shipmentTypeName;
    private String sbBound;
    private String packageFlag;
    private String pal;
    private String carrierTaxPercent;
    private String destinationCountryId;
    private String originCountryId;
    private String invoiceId;
    private String invoiceCode;
    private String serviceName;
    private String airbillNumber;
    private String shipmentId;
    private String senderCountryCode;
    private String receiverCountryCode;
    private String shipmentDate;
    private String customerCode;
    private String shipperReference;
    private String billingReference2;
    private String billingReference3;
    private String paid;
    private String senderCompanyname;
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
    private String receiverAddress3;
    private String receiverCity;
    private String receiverPostalCode;
    private String receiverState;
    private String receiverCountry;
    private String noOfPieces;
    private String dhlApZone;
    private String senderZone;
    private String receiverZone;
    private String status;
    private String carrier;
    private String weight;
    private String serviceAreaCodeOrigin;
    private String serviceAreaCodeDestination;
    private String senderAddressId;
    private String receiverAddressId;
    private String zone;
    private String billActualDimension;
    private String billingContents;
    private String baseCharge;
    private String withInSurance;
    private String nonStandardCharge;
    private String manualHandlingSurcharge;
    private String gstTaxAmount;
    private String franchiseTaxAmount;
    private String totalCustomerAmount;
    private String totalFranchiseAmount;
    private String totalCarrierAmount;
    private String totalMargin;
    private String invoiceStatus;
    private String invoiceDate;
    private List<ChargeAirbillModel> listCharge;
    private List<QuoteAirbillModel> listQuote;
    private List<RefunAdjustmentModel> listAdjustment;
    private String totalCharges;

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getTotalCustomerAmount() {
        return totalCustomerAmount;
    }

    public void setTotalCustomerAmount(String totalCustomerAmount) {
        this.totalCustomerAmount = totalCustomerAmount;
    }

    public String getTotalFranchiseAmount() {
        return totalFranchiseAmount;
    }

    public void setTotalFranchiseAmount(String totalFranchiseAmount) {
        this.totalFranchiseAmount = totalFranchiseAmount;
    }

    public String getTotalMargin() {
        return totalMargin;
    }

    public void setTotalMargin(String totalMargin) {
        this.totalMargin = totalMargin;
    }

    public String getFranchiseTaxAmount() {
        return franchiseTaxAmount;
    }

    public void setFranchiseTaxAmount(String franchiseTaxAmount) {
        this.franchiseTaxAmount = franchiseTaxAmount;
    }

    public String getGstTaxAmount() {
        return gstTaxAmount;
    }

    public void setGstTaxAmount(String gstTaxAmount) {
        this.gstTaxAmount = gstTaxAmount;
    }

    public List<RefunAdjustmentModel> getListAdjustment() {
        return listAdjustment;
    }

    public void setListAdjustment(List<RefunAdjustmentModel> listAdjustment) {
        this.listAdjustment = listAdjustment;
    }

    public List<QuoteAirbillModel> getListQuote() {
        return listQuote;
    }

    public void setListQuote(List<QuoteAirbillModel> listQuote) {
        this.listQuote = listQuote;
    }

    public List<ChargeAirbillModel> getListCharge() {
        return listCharge;
    }

    public void setListCharge(List<ChargeAirbillModel> listCharge) {
        this.listCharge = listCharge;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getSenderCountryId() {
        return senderCountryId;
    }

    public void setSenderCountryId(String senderCountryId) {
        this.senderCountryId = senderCountryId;
    }

    public String getReceiverCountryId() {
        return receiverCountryId;
    }

    public void setReceiverCountryId(String receiverCountryId) {
        this.receiverCountryId = receiverCountryId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getSbBound() {
        return sbBound;
    }

    public void setSbBound(String sbBound) {
        this.sbBound = sbBound;
    }

    public String getPackageFlag() {
        return packageFlag;
    }

    public void setPackageFlag(String packageFlag) {
        this.packageFlag = packageFlag;
    }

    public String getPal() {
        return pal;
    }

    public void setPal(String pal) {
        this.pal = pal;
    }

    public String getCarrierTaxPercent() {
        return carrierTaxPercent;
    }

    public void setCarrierTaxPercent(String carrierTaxPercent) {
        this.carrierTaxPercent = carrierTaxPercent;
    }

    public String getDestinationCountryId() {
        return destinationCountryId;
    }

    public void setDestinationCountryId(String destinationCountryId) {
        this.destinationCountryId = destinationCountryId;
    }

    public String getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(String originCountryId) {
        this.originCountryId = originCountryId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
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

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
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

    public String getBillingReference2() {
        return billingReference2;
    }

    public void setBillingReference2(String billingReference2) {
        this.billingReference2 = billingReference2;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getSenderCompanyname() {
        return senderCompanyname;
    }

    public void setSenderCompanyname(String senderCompanyname) {
        this.senderCompanyname = senderCompanyname;
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

    public String getDhlApZone() {
        return dhlApZone;
    }

    public void setDhlApZone(String dhlApZone) {
        this.dhlApZone = dhlApZone;
    }

    public String getSenderZone() {
        return senderZone;
    }

    public void setSenderZone(String senderZone) {
        this.senderZone = senderZone;
    }

    public String getReceiverZone() {
        return receiverZone;
    }

    public void setReceiverZone(String receiverZone) {
        this.receiverZone = receiverZone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
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

    public String getBillActualDimension() {
        return billActualDimension;
    }

    public void setBillActualDimension(String billActualDimension) {
        this.billActualDimension = billActualDimension;
    }

    public String getBillingContents() {
        return billingContents;
    }

    public void setBillingContents(String billingContents) {
        this.billingContents = billingContents;
    }

    public String getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(String baseCharge) {
        this.baseCharge = baseCharge;
    }

    public String getWithInSurance() {
        return withInSurance;
    }

    public void setWithInSurance(String withInSurance) {
        this.withInSurance = withInSurance;
    }

    public String getNonStandardCharge() {
        return nonStandardCharge;
    }

    public void setNonStandardCharge(String nonStandardCharge) {
        this.nonStandardCharge = nonStandardCharge;
    }

    public String getManualHandlingSurcharge() {
        return manualHandlingSurcharge;
    }

    public void setManualHandlingSurcharge(String manualHandlingSurcharge) {
        this.manualHandlingSurcharge = manualHandlingSurcharge;
    }

    public String getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(String totalCharges) {
        this.totalCharges = totalCharges;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getReceiverAddress3() {
        return receiverAddress3;
    }

    public void setReceiverAddress3(String receiverAddress3) {
        this.receiverAddress3 = receiverAddress3;
    }

    public String getTotalCarrierAmount() {
        return totalCarrierAmount;
    }

    public void setTotalCarrierAmount(String totalCarrierAmount) {
        this.totalCarrierAmount = totalCarrierAmount;
    }

    public String getBillingReference3() {
        return billingReference3;
    }

    public void setBillingReference3(String billingReference3) {
        this.billingReference3 = billingReference3;
    }

    @Override
    public String toString() {
        return "SearchAirbillModel [rateType=" + rateType + ", senderCountryId=" + senderCountryId + ", receiverCountryId=" + receiverCountryId + ", contents=" + contents + ", shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", sbBound=" + sbBound + ", packageFlag=" + packageFlag + ", pal=" + pal + ", carrierTaxPercent=" + carrierTaxPercent + ", destinationCountryId=" + destinationCountryId + ", originCountryId=" + originCountryId + ", invoiceId=" + invoiceId
                + ", invoiceCode=" + invoiceCode + ", serviceName=" + serviceName + ", airbillNumber=" + airbillNumber + ", shipmentId=" + shipmentId + ", senderCountryCode=" + senderCountryCode + ", receiverCountryCode=" + receiverCountryCode + ", shipmentDate=" + shipmentDate + ", customerCode=" + customerCode + ", shipperReference=" + shipperReference + ", billingReference2=" + billingReference2 + ", billingReference3=" + billingReference3 + ", paid=" + paid + ", senderCompanyname="
                + senderCompanyname + ", senderContactName=" + senderContactName + ", senderAddress=" + senderAddress + ", senderAddress2=" + senderAddress2 + ", senderCity=" + senderCity + ", senderPostalCode=" + senderPostalCode + ", senderState=" + senderState + ", senderCountry=" + senderCountry + ", receiverCompanyName=" + receiverCompanyName + ", receiverContactName=" + receiverContactName + ", receiverAddress=" + receiverAddress + ", receiverAddress2=" + receiverAddress2
                + ", receiverAddress3=" + receiverAddress3 + ", receiverCity=" + receiverCity + ", receiverPostalCode=" + receiverPostalCode + ", receiverState=" + receiverState + ", receiverCountry=" + receiverCountry + ", noOfPieces=" + noOfPieces + ", dhlApZone=" + dhlApZone + ", senderZone=" + senderZone + ", receiverZone=" + receiverZone + ", status=" + status + ", carrier=" + carrier + ", weight=" + weight + ", serviceAreaCodeOrigin=" + serviceAreaCodeOrigin
                + ", serviceAreaCodeDestination=" + serviceAreaCodeDestination + ", senderAddressId=" + senderAddressId + ", receiverAddressId=" + receiverAddressId + ", zone=" + zone + ", billActualDimension=" + billActualDimension + ", billingContents=" + billingContents + ", baseCharge=" + baseCharge + ", withInSurance=" + withInSurance + ", nonStandardCharge=" + nonStandardCharge + ", manualHandlingSurcharge=" + manualHandlingSurcharge +", gstTaxAmount=" + gstTaxAmount + ", franchiseTaxAmount=" + franchiseTaxAmount + ", totalCustomerAmount="
                + totalCustomerAmount + ", totalFranchiseAmount=" + totalFranchiseAmount + ", totalCarrierAmount=" + totalCarrierAmount + ", totalMargin=" + totalMargin + ", invoiceStatus=" + invoiceStatus + ", invoiceDate=" + invoiceDate + ", listCharge=" + listCharge + ", listQuote=" + listQuote + ", listAdjustment=" + listAdjustment + ", totalCharges=" + totalCharges + "]";
    }
}

package com.gms.xms.txndb.vo.invoicing.searchairbill;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;
import java.util.List;

/**
 * File Name: SearchAirbillVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 21-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing.searchairbill <br/>
 */
public class SearchAirbillVo extends BaseVo {
    private static final long serialVersionUID = -4068234768050694224L;
    private String rateType;
    private Long senderCountryId;
    private Long receiverCountryId;
    private String contents;
    private Integer shipmentTypeId;
    private String shipmentTypeName;
    private String sbBound;
    private String packageFlag;
    private Integer pal;
    private Double carrierTaxPercent;
    private Long destinationCountryId;
    private Long originCountryId;
    private Long invoiceId;
    private String invoiceCode;
    private String serviceName;
    private String airbillNumber;
    private Long shipmentId;
    private String senderCountryCode;
    private String receiverCountryCode;
    private Date shipmentDate;
    private Long customerCode;
    private String shipperReference;
    private String billingReference2;
    private String billingReference3;
    private Double paid;
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
    private String receiverCity;
    private String receiverPostalCode;
    private String receiverState;
    private String receiverCountry;
    private Integer noOfPieces;
    private String dhlApZone;
    private String senderZone;
    private String receiverZone;
    private Integer status;
    private Long carrier;
    private Double weight;
    private String serviceAreaCodeOrigin;
    private String serviceAreaCodeDestination;
    private Long senderAddressId;
    private Long receiverAddressId;
    private String zone;
    private String billActualDimension;
    private String billingContents;
    private Double baseCharge;
    private Double withInSurance;
    private Double nonStandardCharge;
    private Double manualHandlingSurcharge;
    private Double gstTaxAmount;
    private Double franchiseTaxAmount;
    private Double totalCustomerAmount;
    private Double totalCarrierAmount;
    private Double totalFranchiseAmount;
    private Double totalMargin;
    private Integer invoiceStatus;
    private Date invoiceDate;
    private List<ChargeAirbillVo> listCharge;
    private List<QuoteAirbillVo> listQuote;
    private List<RefunAdjustmentVo> listAdjustment;
    private Double totalCharges;

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalCustomerAmount() {
        return totalCustomerAmount;
    }

    public void setTotalCustomerAmount(Double totalCustomerAmount) {
        this.totalCustomerAmount = totalCustomerAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalFranchiseAmount() {
        return totalFranchiseAmount;
    }

    public void setTotalFranchiseAmount(Double totalFranchiseAmount) {
        this.totalFranchiseAmount = totalFranchiseAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalMargin() {
        return totalMargin;
    }

    public void setTotalMargin(Double totalMargin) {
        this.totalMargin = totalMargin;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseTaxAmount() {
        return franchiseTaxAmount;
    }

    public void setFranchiseTaxAmount(Double franchiseTaxAmount) {
        this.franchiseTaxAmount = franchiseTaxAmount;
    }

    public List<RefunAdjustmentVo> getListAdjustment() {
        return listAdjustment;
    }

    public void setListAdjustment(List<RefunAdjustmentVo> listAdjustment) {
        this.listAdjustment = listAdjustment;
    }

    public List<QuoteAirbillVo> getListQuote() {
        return listQuote;
    }

    public void setListQuote(List<QuoteAirbillVo> listQuote) {
        this.listQuote = listQuote;
    }

    public List<ChargeAirbillVo> getListCharge() {
        return listCharge;
    }

    public void setListCharge(List<ChargeAirbillVo> listCharge) {
        this.listCharge = listCharge;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public Long getSenderCountryId() {
        return senderCountryId;
    }

    public void setSenderCountryId(Long senderCountryId) {
        this.senderCountryId = senderCountryId;
    }

    public Long getReceiverCountryId() {
        return receiverCountryId;
    }

    public void setReceiverCountryId(Long receiverCountryId) {
        this.receiverCountryId = receiverCountryId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
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

    public Integer getPal() {
        return pal;
    }

    public void setPal(Integer pal) {
        this.pal = pal;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierTaxPercent() {
        return carrierTaxPercent;
    }

    public void setCarrierTaxPercent(Double carrierTaxPercent) {
        this.carrierTaxPercent = carrierTaxPercent;
    }

    public Long getDestinationCountryId() {
        return destinationCountryId;
    }

    public void setDestinationCountryId(Long destinationCountryId) {
        this.destinationCountryId = destinationCountryId;
    }

    public Long getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(Long originCountryId) {
        this.originCountryId = originCountryId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
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

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
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

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getPaid() {
        return paid;
    }

    public void setPaid(Double paid) {
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

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCarrier() {
        return carrier;
    }

    public void setCarrier(Long carrier) {
        this.carrier = carrier;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
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

    public Long getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(Long senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public Long getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Long receiverAddressId) {
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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(Double baseCharge) {
        this.baseCharge = baseCharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWithInSurance() {
        return withInSurance;
    }

    public void setWithInSurance(Double withInSurance) {
        this.withInSurance = withInSurance;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getNonStandardCharge() {
        return nonStandardCharge;
    }

    public void setNonStandardCharge(Double nonStandardCharge) {
        this.nonStandardCharge = nonStandardCharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getManualHandlingSurcharge() {
        return manualHandlingSurcharge;
    }

    public void setManualHandlingSurcharge(Double manualHandlingSurcharge) {
        this.manualHandlingSurcharge = manualHandlingSurcharge;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGstTaxAmount() {
        return gstTaxAmount;
    }

    public void setGstTaxAmount(Double gstTaxAmount) {
        this.gstTaxAmount = gstTaxAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(Double totalCharges) {
        this.totalCharges = totalCharges;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    @Override
    public String toString() {
        return "SearchAirbillVo [rateType=" + rateType + ", senderCountryId=" + senderCountryId + ", receiverCountryId=" + receiverCountryId + ", contents=" + contents + ", shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", sbBound=" + sbBound + ", packageFlag=" + packageFlag + ", pal=" + pal + ", carrierTaxPercent=" + carrierTaxPercent + ", destinationCountryId=" + destinationCountryId + ", originCountryId=" + originCountryId + ", invoiceId=" + invoiceId
                + ", invoiceCode=" + invoiceCode + ", serviceName=" + serviceName + ", airbillNumber=" + airbillNumber + ", shipmentId=" + shipmentId + ", senderCountryCode=" + senderCountryCode + ", receiverCountryCode=" + receiverCountryCode + ", shipmentDate=" + shipmentDate + ", customerCode=" + customerCode + ", shipperReference=" + shipperReference + ", billingReference2=" + billingReference2 + ", paid=" + paid + ", senderCompanyname=" + senderCompanyname + ", senderContactName="
                + senderContactName + ", senderAddress=" + senderAddress + ", senderAddress2=" + senderAddress2 + ", senderCity=" + senderCity + ", senderPostalCode=" + senderPostalCode + ", senderState=" + senderState + ", senderCountry=" + senderCountry + ", receiverCompanyName=" + receiverCompanyName + ", receiverContactName=" + receiverContactName + ", receiverAddress=" + receiverAddress + ", receiverAddress2=" + receiverAddress2 + ", receiverCity=" + receiverCity + ", receiverPostalCode="
                + receiverPostalCode + ", receiverState=" + receiverState + ", receiverCountry=" + receiverCountry + ", noOfPieces=" + noOfPieces + ", dhlApZone=" + dhlApZone + ", senderZone=" + senderZone + ", receiverZone=" + receiverZone + ", status=" + status + ", carrier=" + carrier + ", weight=" + weight + ", serviceAreaCodeOrigin=" + serviceAreaCodeOrigin + ", serviceAreaCodeDestination=" + serviceAreaCodeDestination + ", senderAddressId=" + senderAddressId + ", receiverAddressId="
                + receiverAddressId + ", zone=" + zone + ", billActualDimension=" + billActualDimension + ", billingContents=" + billingContents + ", baseCharge=" + baseCharge + ", withInSurance=" + withInSurance + ", nonStandardCharge=" + nonStandardCharge + ", manualHandlingSurcharge=" + manualHandlingSurcharge + ", gstTaxAmount=" + gstTaxAmount + ", franchiseTaxAmount=" + franchiseTaxAmount + ", totalCustomerAmount=" + totalCustomerAmount + ", totalCarrierAmount=" + totalCarrierAmount + ", totalFranchiseAmount=" + totalFranchiseAmount
                + ", totalMargin=" + totalMargin + ", invoiceStatus=" + invoiceStatus + ", invoiceDate=" + invoiceDate + ", listCharge=" + listCharge + ", listQuote=" + listQuote + ", listAdjustment=" + listAdjustment + ", totalCharges=" + totalCharges + "]";
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalCarrierAmount() {
        return totalCarrierAmount;
    }

    public void setTotalCarrierAmount(Double totalCarrierAmount) {
        this.totalCarrierAmount = totalCarrierAmount;
    }

    public String getBillingReference3() {
        return billingReference3;
    }

    public void setBillingReference3(String billingReference3) {
        this.billingReference3 = billingReference3;
    }
}

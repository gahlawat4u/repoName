package com.gms.xms.model.admin.csvinvoices;

import com.gms.xms.model.BaseModel;

import java.util.List;
import java.util.Map;

public class CsvInvoicesExportModel extends BaseModel {

    private static final long serialVersionUID = 8924865239212653579L;

    private String invoiceId;
    private String invoiceCode;
    private String invoiceDate;
    private String airbillNumber;
    private String shipperAccount;
    private String shipperName;
    private String shipperAttention;
    private String shipperAddress1;
    private String shipperAddress2;
    private String shipperCity;
    private String shipperState;
    private String shipperZipcode;
    private String shipperReference;
    private String consigneeName;
    private String consigneeAttention;
    private String consigneeAddress1;
    private String consigneeAddress2;
    private String consigneeCity;
    private String consigneeState;
    private String consigneeZipcode;
    private String consigneeCountryCode;
    private String thirdPartyNumber;
    private String thirdPartyName;
    private String thirdPartyAddress1;
    private String thirdPartyAddress2;
    private String thirdPartyCity;
    private String thirdPartyState;
    private String thirdPartyZipcode;
    private String shipmentDate;
    private String productCode;
    private String zone;
    private String billedWeight;
    private String dimensionWeight;
    private String pieces;
    private List<Map<String, String>> listSurcharges;
    private String baseChargeType;
    private String baseChargeAmount;

    private String reference2;
    private String reference3;

    private String shipmentId;

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

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getShipperAccount() {
        return shipperAccount;
    }

    public void setShipperAccount(String shipperAccount) {
        this.shipperAccount = shipperAccount;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperAttention() {
        return shipperAttention;
    }

    public void setShipperAttention(String shipperAttention) {
        this.shipperAttention = shipperAttention;
    }

    public String getShipperAddress1() {
        return shipperAddress1;
    }

    public void setShipperAddress1(String shipperAddress1) {
        this.shipperAddress1 = shipperAddress1;
    }

    public String getShipperAddress2() {
        return shipperAddress2;
    }

    public void setShipperAddress2(String shipperAddress2) {
        this.shipperAddress2 = shipperAddress2;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getShipperState() {
        return shipperState;
    }

    public void setShipperState(String shipperState) {
        this.shipperState = shipperState;
    }

    public String getShipperZipcode() {
        return shipperZipcode;
    }

    public void setShipperZipcode(String shipperZipcode) {
        this.shipperZipcode = shipperZipcode;
    }

    public String getShipperReference() {
        return shipperReference;
    }

    public void setShipperReference(String shipperReference) {
        this.shipperReference = shipperReference;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeAttention() {
        return consigneeAttention;
    }

    public void setConsigneeAttention(String consigneeAttention) {
        this.consigneeAttention = consigneeAttention;
    }

    public String getConsigneeAddress1() {
        return consigneeAddress1;
    }

    public void setConsigneeAddress1(String consigneeAddress1) {
        this.consigneeAddress1 = consigneeAddress1;
    }

    public String getConsigneeAddress2() {
        return consigneeAddress2;
    }

    public void setConsigneeAddress2(String consigneeAddress2) {
        this.consigneeAddress2 = consigneeAddress2;
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
    }

    public String getConsigneeState() {
        return consigneeState;
    }

    public void setConsigneeState(String consigneeState) {
        this.consigneeState = consigneeState;
    }

    public String getConsigneeZipcode() {
        return consigneeZipcode;
    }

    public void setConsigneeZipcode(String consigneeZipcode) {
        this.consigneeZipcode = consigneeZipcode;
    }

    public String getConsigneeCountryCode() {
        return consigneeCountryCode;
    }

    public void setConsigneeCountryCode(String consigneeCountryCode) {
        this.consigneeCountryCode = consigneeCountryCode;
    }

    public String getThirdPartyNumber() {
        return thirdPartyNumber;
    }

    public void setThirdPartyNumber(String thirdPartyNumber) {
        this.thirdPartyNumber = thirdPartyNumber;
    }

    public String getThirdPartyName() {
        return thirdPartyName;
    }

    public void setThirdPartyName(String thirdPartyName) {
        this.thirdPartyName = thirdPartyName;
    }

    public String getThirdPartyAddress1() {
        return thirdPartyAddress1;
    }

    public void setThirdPartyAddress1(String thirdPartyAddress1) {
        this.thirdPartyAddress1 = thirdPartyAddress1;
    }

    public String getThirdPartyAddress2() {
        return thirdPartyAddress2;
    }

    public void setThirdPartyAddress2(String thirdPartyAddress2) {
        this.thirdPartyAddress2 = thirdPartyAddress2;
    }

    public String getThirdPartyCity() {
        return thirdPartyCity;
    }

    public void setThirdPartyCity(String thirdPartyCity) {
        this.thirdPartyCity = thirdPartyCity;
    }

    public String getThirdPartyState() {
        return thirdPartyState;
    }

    public void setThirdPartyState(String thirdPartyState) {
        this.thirdPartyState = thirdPartyState;
    }

    public String getThirdPartyZipcode() {
        return thirdPartyZipcode;
    }

    public void setThirdPartyZipcode(String thirdPartyZipcode) {
        this.thirdPartyZipcode = thirdPartyZipcode;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getBilledWeight() {
        return billedWeight;
    }

    public void setBilledWeight(String billedWeight) {
        this.billedWeight = billedWeight;
    }

    public String getDimensionWeight() {
        return dimensionWeight;
    }

    public void setDimensionWeight(String dimensionWeight) {
        this.dimensionWeight = dimensionWeight;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }

    public String getBaseChargeType() {
        return baseChargeType;
    }

    public void setBaseChargeType(String baseChargeType) {
        this.baseChargeType = baseChargeType;
    }

    public String getBaseChargeAmount() {
        return baseChargeAmount;
    }

    public void setBaseChargeAmount(String baseChargeAmount) {
        this.baseChargeAmount = baseChargeAmount;
    }

    public List<Map<String, String>> getListSurcharges() {
        return listSurcharges;
    }

    public void setListSurcharges(List<Map<String, String>> listSurcharges) {
        this.listSurcharges = listSurcharges;
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

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    @Override
    public String toString() {
        return "CsvInvoicesExportModel [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", invoiceDate=" + invoiceDate + ", airbillNumber=" + airbillNumber + ", shipperAccount=" + shipperAccount + ", shipperName=" + shipperName + ", shipperAttention=" + shipperAttention + ", shipperAddress1=" + shipperAddress1 + ", shipperAddress2=" + shipperAddress2 + ", shipperCity=" + shipperCity + ", shipperState=" + shipperState + ", shipperZipcode=" + shipperZipcode + ", shipperReference="
                + shipperReference + ", consigneeName=" + consigneeName + ", consigneeAttention=" + consigneeAttention + ", consigneeAddress1=" + consigneeAddress1 + ", consigneeAddress2=" + consigneeAddress2 + ", consigneeCity=" + consigneeCity + ", consigneeState=" + consigneeState + ", consigneeZipcode=" + consigneeZipcode + ", consigneeCountryCode=" + consigneeCountryCode + ", thirdPartyNumber=" + thirdPartyNumber + ", thirdPartyName=" + thirdPartyName + ", thirdPartyAddress1="
                + thirdPartyAddress1 + ", thirdPartyAddress2=" + thirdPartyAddress2 + ", thirdPartyCity=" + thirdPartyCity + ", thirdPartyState=" + thirdPartyState + ", thirdPartyZipcode=" + thirdPartyZipcode + ", shipmentDate=" + shipmentDate + ", productCode=" + productCode + ", zone=" + zone + ", billedWeight=" + billedWeight + ", dimensionWeight=" + dimensionWeight + ", pieces=" + pieces + ", listSurcharges=" + listSurcharges + ", baseChargeType=" + baseChargeType + ", baseChargeAmount="
                + baseChargeAmount + ", reference2=" + reference2 + ", reference3=" + reference3 + ", shipmentId=" + shipmentId + "]";
    }
}

package com.gms.xms.txndb.model.reports.selfinsurance;

import com.gms.xms.model.BaseModel;

/**
 * Posted from InvoicedAirbillModel
 * <p>
 * Author dattrinh Mar 19, 2016 4:07:03 PM
 */
public class InvoicedAirbillModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String invoiceCode;
    private String customerCode;
    private String airbillNumber;
    private String carrier;
    private String service;
    private String shipmentValue;
    private String insuredAmount;
    private String insuredGst;
    private String insuredTotal;
    private String shipDate;
    private String importDate;
    private String invoiceDate;
    private String noOfPieces;
    private String deadWeight;
    private String weightUnit;
    private String dimension;
    private String dimensionUnit;
    private String quoted;
    private String charges;
    private String total;
    private String senderCompanyName;
    private String senderPhone;
    private String senderContactName;
    private String senderEmail;
    private String senderCountryName;
    private String senderAddress1;
    private String senderAddress2;
    private String senderCity;
    private String senderPostalCode;
    private String senderState;
    private String receiverCompanyName;
    private String receiverPhone;
    private String receiverContactName;
    private String receiverEmail;
    private String receiverCountryName;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverCity;
    private String receiverPostalCode;
    private String receiverState;
    private String bound;
    private String domWarrantyGst;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getShipmentValue() {
        return shipmentValue;
    }

    public void setShipmentValue(String shipmentValue) {
        this.shipmentValue = shipmentValue;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(String noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public String getDeadWeight() {
        return deadWeight;
    }

    public void setDeadWeight(String deadWeight) {
        this.deadWeight = deadWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public String getQuoted() {
        return quoted;
    }

    public void setQuoted(String quoted) {
        this.quoted = quoted;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSenderCompanyName() {
        return senderCompanyName;
    }

    public void setSenderCompanyName(String senderCompanyName) {
        this.senderCompanyName = senderCompanyName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderContactName() {
        return senderContactName;
    }

    public void setSenderContactName(String senderContactName) {
        this.senderContactName = senderContactName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderCountryName() {
        return senderCountryName;
    }

    public void setSenderCountryName(String senderCountryName) {
        this.senderCountryName = senderCountryName;
    }

    public String getSenderAddress1() {
        return senderAddress1;
    }

    public void setSenderAddress1(String senderAddress1) {
        this.senderAddress1 = senderAddress1;
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

    public String getReceiverCompanyName() {
        return receiverCompanyName;
    }

    public void setReceiverCompanyName(String receiverCompanyName) {
        this.receiverCompanyName = receiverCompanyName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverContactName() {
        return receiverContactName;
    }

    public void setReceiverContactName(String receiverContactName) {
        this.receiverContactName = receiverContactName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverCountryName() {
        return receiverCountryName;
    }

    public void setReceiverCountryName(String receiverCountryName) {
        this.receiverCountryName = receiverCountryName;
    }

    public String getReceiverAddress1() {
        return receiverAddress1;
    }

    public void setReceiverAddress1(String receiverAddress1) {
        this.receiverAddress1 = receiverAddress1;
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

    public String getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(String insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public String getInsuredGst() {
        return insuredGst;
    }

    public void setInsuredGst(String insuredGst) {
        this.insuredGst = insuredGst;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public String getDomWarrantyGst() {
        return domWarrantyGst;
    }

    public void setDomWarrantyGst(String domWarrantyGst) {
        this.domWarrantyGst = domWarrantyGst;
    }

    public String getInsuredTotal() {
        return insuredTotal;
    }

    public void setInsuredTotal(String insuredTotal) {
        this.insuredTotal = insuredTotal;
    }

    @Override
    public String toString() {
        return "InvoicedAirbillModel [invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", airbillNumber=" + airbillNumber + ", carrier=" + carrier + ", service=" + service + ", shipmentValue=" + shipmentValue + ", insuredAmount=" + insuredAmount + ", insuredGst=" + insuredGst + ", insuredTotal=" + insuredTotal + ", shipDate=" + shipDate + ", importDate=" + importDate + ", invoiceDate=" + invoiceDate + ", noOfPieces=" + noOfPieces + ", deadWeight=" + deadWeight + ", weightUnit="
                + weightUnit + ", dimension=" + dimension + ", dimensionUnit=" + dimensionUnit + ", quoted=" + quoted + ", charges=" + charges + ", total=" + total + ", senderCompanyName=" + senderCompanyName + ", senderPhone=" + senderPhone + ", senderContactName=" + senderContactName + ", senderEmail=" + senderEmail + ", senderCountryName=" + senderCountryName + ", senderAddress1=" + senderAddress1 + ", senderAddress2=" + senderAddress2 + ", senderCity=" + senderCity + ", senderPostalCode="
                + senderPostalCode + ", senderState=" + senderState + ", receiverCompanyName=" + receiverCompanyName + ", receiverPhone=" + receiverPhone + ", receiverContactName=" + receiverContactName + ", receiverEmail=" + receiverEmail + ", receiverCountryName=" + receiverCountryName + ", receiverAddress1=" + receiverAddress1 + ", receiverAddress2=" + receiverAddress2 + ", receiverCity=" + receiverCity + ", receiverPostalCode=" + receiverPostalCode + ", receiverState=" + receiverState
                + ", bound=" + bound + ", domWarrantyGst=" + domWarrantyGst + "]";
    }
}

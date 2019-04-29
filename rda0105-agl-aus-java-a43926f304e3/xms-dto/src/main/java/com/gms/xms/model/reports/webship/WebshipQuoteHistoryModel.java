package com.gms.xms.model.reports.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from WebshipQuoteHistoryModel.java
 * <p>
 * Author dattrinh 10:24:46 AM
 */
public class WebshipQuoteHistoryModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String quoteId;
    private String customerCode;
    private String customerName;
    private String quoteDate;
    private String senderCompanyName;
    private String senderContactName;
    private String senderAddress1;
    private String senderAddress2;
    private String senderCity;
    private String senderPostalCode;
    private String senderState;
    private String senderCountry;
    private String senderPhone;
    private String receiverCompanyName;
    private String receiverContactName;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverCity;
    private String receiverPostalCode;
    private String receiverState;
    private String receiverCountry;
    private String receiverPhone;
    private String shipmentTypeName;
    private String packageName;
    private String noOfPieces;
    private String weight;
    private String weightLine;
    private String dimensionL;
    private String dimensionW;
    private String dimensionH;
    private String dimensionLine;
    private String weightUnit;
    private String dimensionUnit;
    private String content;
    private String ipAddress;

    @Override
    public String toString() {
        return "WebshipQuoteHistoryModel [quoteId=" + quoteId + ", customerCode=" + customerCode + ", customerName=" + customerName + ", quoteDate=" + quoteDate + ", senderCompanyName=" + senderCompanyName + ", senderContactName=" + senderContactName + ", senderAddress1=" + senderAddress1 + ", senderAddress2=" + senderAddress2 + ", senderCity=" + senderCity + ", senderPostalCode=" + senderPostalCode + ", senderState=" + senderState + ", senderCountry=" + senderCountry + ", senderPhone="
                + senderPhone + ", receiverCompanyName=" + receiverCompanyName + ", receiverContactName=" + receiverContactName + ", receiverAddress1=" + receiverAddress1 + ", receiverAddress2=" + receiverAddress2 + ", receiverCity=" + receiverCity + ", receiverPostalCode=" + receiverPostalCode + ", receiverState=" + receiverState + ", receiverCountry=" + receiverCountry + ", receiverPhone=" + receiverPhone + ", shipmentTypeName=" + shipmentTypeName + ", packageName=" + packageName
                + ", noOfPieces=" + noOfPieces + ", weight=" + weight + ", weightLine=" + weightLine + ", dimensionL=" + dimensionL + ", dimensionW=" + dimensionW + ", dimensionH=" + dimensionH + ", dimensionLine=" + dimensionLine + ", weightUnit=" + weightUnit + ", dimensionUnit=" + dimensionUnit + ", content=" + content + ", ipAddress=" + ipAddress + "]";
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(String quoteDate) {
        this.quoteDate = quoteDate;
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

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
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

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(String noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightLine() {
        return weightLine;
    }

    public void setWeightLine(String weightLine) {
        this.weightLine = weightLine;
    }

    public String getDimensionL() {
        return dimensionL;
    }

    public void setDimensionL(String dimensionL) {
        this.dimensionL = dimensionL;
    }

    public String getDimensionW() {
        return dimensionW;
    }

    public void setDimensionW(String dimensionW) {
        this.dimensionW = dimensionW;
    }

    public String getDimensionH() {
        return dimensionH;
    }

    public void setDimensionH(String dimensionH) {
        this.dimensionH = dimensionH;
    }

    public String getDimensionLine() {
        return dimensionLine;
    }

    public void setDimensionLine(String dimensionLine) {
        this.dimensionLine = dimensionLine;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }
}

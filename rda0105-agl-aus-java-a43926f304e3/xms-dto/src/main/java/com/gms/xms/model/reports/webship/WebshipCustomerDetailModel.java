package com.gms.xms.model.reports.webship;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from WebshipCustomerDetailModel
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String invoiceCode;
    private String shipmentId;
    private String airbillNumber;
    private String customerCode;
    private String shipmentDate;
    private String termOfTrade;
    private String courierMessage;
    private String preClearance;
    private String senderCompanyName;
    private String senderContactName;
    private String senderAddress1;
    private String senderAddress2;
    private String senderCity;
    private String senderPostalCode;
    private String senderState;
    private String senderCountryName;
    private String receiverCompanyName;
    private String receiverContactName;
    private String receiverAddress1;
    private String receiverAddress2;
    private String receiverCity;
    private String receiverPostalCode;
    private String receiverState;
    private String receiverCountryName;
    private String noOfPieces;
    private String weight;
    private String weightUnit;
    private String quoted;
    private String serviceType;
    private List<WebshipCustomerDetailChargeModel> charges;
    private String totalCustomerCost;
    private String totalFranchiseCost;
    private String totalMargin;
    private String dutiesTaxesFee;
    private String insurance;
    private String dangerousGoods;
    private String withInsurance;

    @Override
    public String toString() {
        return "WebshipCustomerDetailModel [invoiceCode=" + invoiceCode + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", customerCode=" + customerCode + ", shipmentDate=" + shipmentDate + ", termOfTrade=" + termOfTrade + ", courierMessage=" + courierMessage + ", preClearance=" + preClearance + ", senderCompanyName=" + senderCompanyName + ", senderContactName=" + senderContactName + ", senderAddress1=" + senderAddress1 + ", senderAddress2=" + senderAddress2
                + ", senderCity=" + senderCity + ", senderPostalCode=" + senderPostalCode + ", senderState=" + senderState + ", senderCountryName=" + senderCountryName + ", receiverCompanyName=" + receiverCompanyName + ", receiverContactName=" + receiverContactName + ", receiverAddress1=" + receiverAddress1 + ", receiverAddress2=" + receiverAddress2 + ", receiverCity=" + receiverCity + ", receiverPostalCode=" + receiverPostalCode + ", receiverState=" + receiverState + ", receiverCountryName="
                + receiverCountryName + ", noOfPieces=" + noOfPieces + ", weight=" + weight + ", weightUnit=" + weightUnit + ", quoted=" + quoted + ", serviceType=" + serviceType + ", charges=" + charges + ", totalCustomerCost=" + totalCustomerCost + ", totalFranchiseCost=" + totalFranchiseCost + ", totalMargin=" + totalMargin + ", dutiesTaxesFee=" + dutiesTaxesFee + ", insurance=" + insurance + ", dangerousGoods=" + dangerousGoods + ", withInsurance=" + withInsurance + "]";
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getTermOfTrade() {
        return termOfTrade;
    }

    public void setTermOfTrade(String termOfTrade) {
        this.termOfTrade = termOfTrade;
    }

    public String getCourierMessage() {
        return courierMessage;
    }

    public void setCourierMessage(String courierMessage) {
        this.courierMessage = courierMessage;
    }

    public String getPreClearance() {
        return preClearance;
    }

    public void setPreClearance(String preClearance) {
        this.preClearance = preClearance;
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

    public String getSenderCountryName() {
        return senderCountryName;
    }

    public void setSenderCountryName(String senderCountryName) {
        this.senderCountryName = senderCountryName;
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

    public String getReceiverCountryName() {
        return receiverCountryName;
    }

    public void setReceiverCountryName(String receiverCountryName) {
        this.receiverCountryName = receiverCountryName;
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

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getQuoted() {
        return quoted;
    }

    public void setQuoted(String quoted) {
        this.quoted = quoted;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<WebshipCustomerDetailChargeModel> getCharges() {
        return charges;
    }

    public void setCharges(List<WebshipCustomerDetailChargeModel> charges) {
        this.charges = charges;
    }

    public String getTotalCustomerCost() {
        return totalCustomerCost;
    }

    public void setTotalCustomerCost(String totalCustomerCost) {
        this.totalCustomerCost = totalCustomerCost;
    }

    public String getTotalFranchiseCost() {
        return totalFranchiseCost;
    }

    public void setTotalFranchiseCost(String totalFranchiseCost) {
        this.totalFranchiseCost = totalFranchiseCost;
    }

    public String getTotalMargin() {
        return totalMargin;
    }

    public void setTotalMargin(String totalMargin) {
        this.totalMargin = totalMargin;
    }

    public String getDutiesTaxesFee() {
        return dutiesTaxesFee;
    }

    public void setDutiesTaxesFee(String dutiesTaxesFee) {
        this.dutiesTaxesFee = dutiesTaxesFee;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getDangerousGoods() {
        return dangerousGoods;
    }

    public void setDangerousGoods(String dangerousGoods) {
        this.dangerousGoods = dangerousGoods;
    }

    public String getWithInsurance() {
        return withInsurance;
    }

    public void setWithInsurance(String withInsurance) {
        this.withInsurance = withInsurance;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }
}

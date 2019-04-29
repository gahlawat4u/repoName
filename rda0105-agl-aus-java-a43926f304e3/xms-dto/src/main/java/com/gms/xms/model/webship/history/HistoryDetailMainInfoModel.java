package com.gms.xms.model.webship.history;

import com.gms.xms.model.BaseModel;

/**
 * Posted from HistoryDetailInfoModel
 * <p>
 * Author TanDT Date Jul 14, 2015
 */
public class HistoryDetailMainInfoModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = 8181047065086542963L;
    /**
     *
     */
    private String shipmentId;
    private String airbillNumber;
    private String courierMessage;
    private String serviceType;
    private String serviceCode;
    private String referenceNo;
    private String noOfPieces;
    private String shipmentDate;
    private String dimWeight;
    private String actualWeight;
    private String sCompanyName;
    private String sContactName;
    private String sAddress;
    private String sCity;
    private String sPostalCode;
    private String sState;
    private String sCountryName;
    private String sPhone;
    private String rCompanyName;
    private String rContactName;
    private String rAddress;
    private String rCity;
    private String rPostalCode;
    private String rState;
    private String baseCharge;
    private String billingAccount;

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

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(String noOfPieces) {
        this.noOfPieces = noOfPieces;
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

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
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

    public String getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(String baseCharge) {
        this.baseCharge = baseCharge;
    }

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount;
    }
}
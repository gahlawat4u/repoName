package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Created by thinhdd on 2/22/2017.
 */
public class HistoryDetailScheduleInfoVo extends BaseVo {
    private static final long serialVersionUID = -6208532383292526231L;

    private String airbillNumber;
    private String recCompanyName;
    private String recContactName;
    private String recAddress;
    private String recAddress2;
    private String recCity;
    private String recPostalCode;
    private String recPhone;
    private String recState;
    private String shiCompanyName;
    private String shiContactName;
    private String shiAddress;
    private String shiAddress2;
    private String shiCity;
    private String shiPostalCode;
    private String shiState;
    private String shiPhone;

    public HistoryDetailScheduleInfoVo(String airbillNumber, String recCompanyName, String recContactName,
                                       String recAddress, String recAddress2, String recCity, String recPostalCode,
                                       String recPhone, String recState, String shiCompanyName, String shiContactName,
                                       String shiAddress, String shiAddress2, String shiCity, String shiPostalCode,
                                       String shiState, String shiPhone) {
        this.airbillNumber = airbillNumber;
        this.recCompanyName = recCompanyName;
        this.recContactName = recContactName;
        this.recAddress = recAddress;
        this.recAddress2 = recAddress2;
        this.recCity = recCity;
        this.recPostalCode = recPostalCode;
        this.recPhone = recPhone;
        this.recState = recState;
        this.shiCompanyName = shiCompanyName;
        this.shiContactName = shiContactName;
        this.shiAddress = shiAddress;
        this.shiAddress2 = shiAddress2;
        this.shiCity = shiCity;
        this.shiPostalCode = shiPostalCode;
        this.shiState = shiState;
        this.shiPhone = shiPhone;
    }

    public HistoryDetailScheduleInfoVo() {
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getRecCompanyName() {
        return recCompanyName;
    }

    public void setRecCompanyName(String recCompanyName) {
        this.recCompanyName = recCompanyName;
    }

    public String getRecContactName() {
        return recContactName;
    }

    public void setRecContactName(String recContactName) {
        this.recContactName = recContactName;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecAddress2() {
        return recAddress2;
    }

    public void setRecAddress2(String recAddress2) {
        this.recAddress2 = recAddress2;
    }

    public String getRecCity() {
        return recCity;
    }

    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }

    public String getRecPostalCode() {
        return recPostalCode;
    }

    public void setRecPostalCode(String recPostalCode) {
        this.recPostalCode = recPostalCode;
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    public String getRecState() {
        return recState;
    }

    public void setRecState(String recState) {
        this.recState = recState;
    }

    public String getShiCompanyName() {
        return shiCompanyName;
    }

    public void setShiCompanyName(String shiCompanyName) {
        this.shiCompanyName = shiCompanyName;
    }

    public String getShiContactName() {
        return shiContactName;
    }

    public void setShiContactName(String shiContactName) {
        this.shiContactName = shiContactName;
    }

    public String getShiAddress() {
        return shiAddress;
    }

    public void setShiAddress(String shiAddress) {
        this.shiAddress = shiAddress;
    }

    public String getShiAddress2() {
        return shiAddress2;
    }

    public void setShiAddress2(String shiAddress2) {
        this.shiAddress2 = shiAddress2;
    }

    public String getShiCity() {
        return shiCity;
    }

    public void setShiCity(String shiCity) {
        this.shiCity = shiCity;
    }

    public String getShiPostalCode() {
        return shiPostalCode;
    }

    public void setShiPostalCode(String shiPostalCode) {
        this.shiPostalCode = shiPostalCode;
    }

    public String getShiState() {
        return shiState;
    }

    public void setShiState(String shiState) {
        this.shiState = shiState;
    }

    public String getShiPhone() {
        return shiPhone;
    }

    public void setShiPhone(String shiPhone) {
        this.shiPhone = shiPhone;
    }
}

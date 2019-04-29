package com.gms.xms.model.webship.history;

import com.gms.xms.model.BaseModel;

/**
 * Created by thinhdd
 * Date 4/22/2017.
 */
public class HistoryDetailTollManifestModel extends BaseModel {
    private String referenceNo;
    private String sCompanyName;
    private String isDg;
    private String connoteNumber;
    private String rCompanyName;
    private Long billingAccount;
    private String rCity;
    private String rPostalCode;
    private Integer noOfPieces;
    private String cubicWeight;
    private String deadWeight;

    public String getIsDg() {
        return isDg;
    }

    public void setIsDg(String isDg) {
        this.isDg = isDg;
    }

    public String getConnoteNumber() {
        return connoteNumber;
    }

    public void setConnoteNumber(String connoteNumber) {
        this.connoteNumber = connoteNumber;
    }

    public String getrCompanyName() {
        return rCompanyName;
    }

    public void setrCompanyName(String rCompanyName) {
        this.rCompanyName = rCompanyName;
    }

    public String getCubicWeight() {
        return cubicWeight;
    }

    public void setCubicWeight(String cubicWeight) {
        this.cubicWeight = cubicWeight;
    }

    public String getDeadWeight() {
        return deadWeight;
    }

    public void setDeadWeight(String deadWeight) {
        this.deadWeight = deadWeight;
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

    public Integer getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(Integer noOfPieces) {
        this.noOfPieces = noOfPieces;
    }



    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getsCompanyName() {
        return sCompanyName;
    }

    public void setsCompanyName(String sCompanyName) {
        this.sCompanyName = sCompanyName;
    }

    public Long getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(Long billingAccount) {
        this.billingAccount = billingAccount;
    }
}

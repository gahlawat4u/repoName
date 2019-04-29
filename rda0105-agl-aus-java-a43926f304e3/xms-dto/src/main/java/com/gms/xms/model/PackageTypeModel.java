package com.gms.xms.model;

import java.util.List;

/**
 * Posted from PackageTypeModel
 * <p>
 * Author DatTV Sep 18, 2015
 */
public class PackageTypeModel extends BaseModel {

    private static final long serialVersionUID = 4846511743978261127L;

    private String packageType;
    private String packageTypeName;
    private String content;
    private String bound;
    private String carrierSheetId;
    private String nonCarrierSheetId;
    private List<RateSheetColumnModel> carrierZone;
    private List<RateSheetColumnModel> nonCarrierZone;
    private String hasZone;
    private List<CustomerBaseRateModel> baseRates;

    @Override
    public String toString() {
        return "PackageTypeModel [packageType=" + packageType + ", packageTypeName=" + packageTypeName + ", content=" + content + ", bound=" + bound + ", carrierSheetId=" + carrierSheetId + ", nonCarrierSheetId=" + nonCarrierSheetId + ", carrierZone=" + carrierZone + ", nonCarrierZone=" + nonCarrierZone + ", baseRates=" + baseRates + "]";
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageTypeName() {
        return packageTypeName;
    }

    public void setPackageTypeName(String packageTypeName) {
        this.packageTypeName = packageTypeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public List<CustomerBaseRateModel> getBaseRates() {
        return baseRates;
    }

    public void setBaseRates(List<CustomerBaseRateModel> baseRates) {
        this.baseRates = baseRates;
    }

    public String getCarrierSheetId() {
        return carrierSheetId;
    }

    public void setCarrierSheetId(String carrierSheetId) {
        this.carrierSheetId = carrierSheetId;
    }

    public String getNonCarrierSheetId() {
        return nonCarrierSheetId;
    }

    public void setNonCarrierSheetId(String nonCarrierSheetId) {
        this.nonCarrierSheetId = nonCarrierSheetId;
    }

    public List<RateSheetColumnModel> getCarrierZone() {
        return carrierZone;
    }

    public void setCarrierZone(List<RateSheetColumnModel> carrierZone) {
        this.carrierZone = carrierZone;
    }

    public List<RateSheetColumnModel> getNonCarrierZone() {
        return nonCarrierZone;
    }

    public void setNonCarrierZone(List<RateSheetColumnModel> nonCarrierZone) {
        this.nonCarrierZone = nonCarrierZone;
    }


    public String getHasZone() {
        return hasZone;
    }

    public void setHasZone(String hasZone) {
        this.hasZone = hasZone;
    }
}
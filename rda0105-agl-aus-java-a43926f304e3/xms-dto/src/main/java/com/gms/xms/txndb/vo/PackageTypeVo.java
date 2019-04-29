package com.gms.xms.txndb.vo;

import java.util.List;

/**
 * Posted from PackageTypeVo
 * <p>
 * Author DatTV Sep 18, 2015
 */
public class PackageTypeVo extends BaseVo {

    private static final long serialVersionUID = 2142625983797474686L;

    private Integer packageType;
    private String packageTypeName;
    private Integer content;
    private Integer bound;
    private Long carrierSheetId;
    private Long nonCarrierSheetId;
    private List<RateSheetColumnVo> carrierZone;
    private List<RateSheetColumnVo> nonCarrierZone;
    private Boolean hasZone;
    private List<CustomerBaseRateVo> baseRates;

    @Override
    public String toString() {
        return "PackageTypeVo [packageType=" + packageType + ", packageTypeName=" + packageTypeName + ", content=" + content + ", bound=" + bound + ", carrierSheetId=" + carrierSheetId + ", nonCarrierSheetId=" + nonCarrierSheetId + ", carrierZone=" + carrierZone + ", nonCarrierZone=" + nonCarrierZone + ", baseRates=" + baseRates + "]";
    }

    public Integer getPackageType() {
        return packageType;
    }

    public void setPackageType(Integer packageType) {
        this.packageType = packageType;
    }

    public String getPackageTypeName() {
        return packageTypeName;
    }

    public void setPackageTypeName(String packageTypeName) {
        this.packageTypeName = packageTypeName;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getBound() {
        return bound;
    }

    public void setBound(Integer bound) {
        this.bound = bound;
    }

    public List<CustomerBaseRateVo> getBaseRates() {
        return baseRates;
    }

    public void setBaseRates(List<CustomerBaseRateVo> baseRates) {
        this.baseRates = baseRates;
    }


    public Long getCarrierSheetId() {
        return carrierSheetId;
    }

    public void setCarrierSheetId(Long carrierSheetId) {
        this.carrierSheetId = carrierSheetId;
    }

    public Long getNonCarrierSheetId() {
        return nonCarrierSheetId;
    }

    public void setNonCarrierSheetId(Long nonCarrierSheetId) {
        this.nonCarrierSheetId = nonCarrierSheetId;
    }

    public List<RateSheetColumnVo> getCarrierZone() {
        return carrierZone;
    }

    public void setCarrierZone(List<RateSheetColumnVo> carrierZone) {
        this.carrierZone = carrierZone;
    }

    public List<RateSheetColumnVo> getNonCarrierZone() {
        return nonCarrierZone;
    }

    public void setNonCarrierZone(List<RateSheetColumnVo> nonCarrierZone) {
        this.nonCarrierZone = nonCarrierZone;
    }


    public Boolean getHasZone() {
        return hasZone;
    }

    public void setHasZone(Boolean hasZone) {
        this.hasZone = hasZone;
    }
}
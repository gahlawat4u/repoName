package com.gms.xms.txndb.vo.admin.customerprofile.baserate;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.List;

/**
 * Posted from CustProfileServiceTypeVo
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class CustProfileServiceTypeVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer shipmentTypeId;
    private String shipmentTypeName;
    private Integer serviceId;
    private Boolean allowCarrier;
    private Boolean allowNonCarrier;
    private Boolean showStatus;
    private Integer documentType;
    private Integer content;
    private Integer bound;
    private Long carrierDocRate;
    private Long carrierDocInRate;
    private Long carrierPackRate;
    private Long carrierPackPerWeightRate;
    private Long carrierPackInRate;
    private Long carrierPackInPerWeightRate;
    private Long carrierPakRate;
    private Long carrierPakPerWeightRate;
    private Long carrierPakInRate;
    private Long carrierPakInPerWeightRate;
    private Long nonCarrierDocRate;
    private Long nonCarrierDocInRate;
    private Long nonCarrierPackRate;
    private Long nonCarrierPackPerWeightRate;
    private Long nonCarrierPackInRate;
    private Long nonCarrierPackInPerWeightRate;
    private Long nonCarrierPakRate;
    private Long nonCarrierPakPerWeightRate;
    private Long nonCarrierPakInRate;
    private Long nonCarrierPakInPerWeightRate;
    private List<CustProfileBaseRateVo> custProfileBaseRates;
    private List<String> zones;
    private String displayName;
    private Long rateSheetId;
    private Long perWeightRateSheetId;
    private Long ncRateSheetId;
    private Long ncPerWeightRateSheetId;

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Boolean getAllowCarrier() {
        return allowCarrier;
    }

    public void setAllowCarrier(Boolean allowCarrier) {
        this.allowCarrier = allowCarrier;
    }

    public Boolean getAllowNonCarrier() {
        return allowNonCarrier;
    }

    public void setAllowNonCarrier(Boolean allowNonCarrier) {
        this.allowNonCarrier = allowNonCarrier;
    }

    public Boolean getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Boolean showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Integer documentType) {
        this.documentType = documentType;
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

    public Long getCarrierDocRate() {
        return carrierDocRate;
    }

    public void setCarrierDocRate(Long carrierDocRate) {
        this.carrierDocRate = carrierDocRate;
    }

    public Long getCarrierDocInRate() {
        return carrierDocInRate;
    }

    public void setCarrierDocInRate(Long carrierDocInRate) {
        this.carrierDocInRate = carrierDocInRate;
    }

    public Long getCarrierPackRate() {
        return carrierPackRate;
    }

    public void setCarrierPackRate(Long carrierPackRate) {
        this.carrierPackRate = carrierPackRate;
    }

    public Long getCarrierPackPerWeightRate() {
        return carrierPackPerWeightRate;
    }

    public void setCarrierPackPerWeightRate(Long carrierPackPerWeightRate) {
        this.carrierPackPerWeightRate = carrierPackPerWeightRate;
    }

    public Long getCarrierPackInRate() {
        return carrierPackInRate;
    }

    public void setCarrierPackInRate(Long carrierPackInRate) {
        this.carrierPackInRate = carrierPackInRate;
    }

    public Long getCarrierPackInPerWeightRate() {
        return carrierPackInPerWeightRate;
    }

    public void setCarrierPackInPerWeightRate(Long carrierPackInPerWeightRate) {
        this.carrierPackInPerWeightRate = carrierPackInPerWeightRate;
    }

    public Long getCarrierPakRate() {
        return carrierPakRate;
    }

    public void setCarrierPakRate(Long carrierPakRate) {
        this.carrierPakRate = carrierPakRate;
    }

    public Long getCarrierPakPerWeightRate() {
        return carrierPakPerWeightRate;
    }

    public void setCarrierPakPerWeightRate(Long carrierPakPerWeightRate) {
        this.carrierPakPerWeightRate = carrierPakPerWeightRate;
    }

    public Long getCarrierPakInRate() {
        return carrierPakInRate;
    }

    public void setCarrierPakInRate(Long carrierPakInRate) {
        this.carrierPakInRate = carrierPakInRate;
    }

    public Long getCarrierPakInPerWeightRate() {
        return carrierPakInPerWeightRate;
    }

    public void setCarrierPakInPerWeightRate(Long carrierPakInPerWeightRate) {
        this.carrierPakInPerWeightRate = carrierPakInPerWeightRate;
    }

    public Long getNonCarrierDocRate() {
        return nonCarrierDocRate;
    }

    public void setNonCarrierDocRate(Long nonCarrierDocRate) {
        this.nonCarrierDocRate = nonCarrierDocRate;
    }

    public Long getNonCarrierDocInRate() {
        return nonCarrierDocInRate;
    }

    public void setNonCarrierDocInRate(Long nonCarrierDocInRate) {
        this.nonCarrierDocInRate = nonCarrierDocInRate;
    }

    public Long getNonCarrierPackRate() {
        return nonCarrierPackRate;
    }

    public void setNonCarrierPackRate(Long nonCarrierPackRate) {
        this.nonCarrierPackRate = nonCarrierPackRate;
    }

    public Long getNonCarrierPackPerWeightRate() {
        return nonCarrierPackPerWeightRate;
    }

    public void setNonCarrierPackPerWeightRate(Long nonCarrierPackPerWeightRate) {
        this.nonCarrierPackPerWeightRate = nonCarrierPackPerWeightRate;
    }

    public Long getNonCarrierPackInRate() {
        return nonCarrierPackInRate;
    }

    public void setNonCarrierPackInRate(Long nonCarrierPackInRate) {
        this.nonCarrierPackInRate = nonCarrierPackInRate;
    }

    public Long getNonCarrierPackInPerWeightRate() {
        return nonCarrierPackInPerWeightRate;
    }

    public void setNonCarrierPackInPerWeightRate(Long nonCarrierPackInPerWeightRate) {
        this.nonCarrierPackInPerWeightRate = nonCarrierPackInPerWeightRate;
    }

    public Long getNonCarrierPakRate() {
        return nonCarrierPakRate;
    }

    public void setNonCarrierPakRate(Long nonCarrierPakRate) {
        this.nonCarrierPakRate = nonCarrierPakRate;
    }

    public Long getNonCarrierPakPerWeightRate() {
        return nonCarrierPakPerWeightRate;
    }

    public void setNonCarrierPakPerWeightRate(Long nonCarrierPakPerWeightRate) {
        this.nonCarrierPakPerWeightRate = nonCarrierPakPerWeightRate;
    }

    public Long getNonCarrierPakInRate() {
        return nonCarrierPakInRate;
    }

    public void setNonCarrierPakInRate(Long nonCarrierPakInRate) {
        this.nonCarrierPakInRate = nonCarrierPakInRate;
    }

    public Long getNonCarrierPakInPerWeightRate() {
        return nonCarrierPakInPerWeightRate;
    }

    public void setNonCarrierPakInPerWeightRate(Long nonCarrierPakInPerWeightRate) {
        this.nonCarrierPakInPerWeightRate = nonCarrierPakInPerWeightRate;
    }

    public List<CustProfileBaseRateVo> getCustProfileBaseRates() {
        return custProfileBaseRates;
    }

    public void setCustProfileBaseRates(List<CustProfileBaseRateVo> custProfileBaseRates) {
        this.custProfileBaseRates = custProfileBaseRates;
    }

    public List<String> getZones() {
        return zones;
    }

    public void setZones(List<String> zones) {
        this.zones = zones;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getRateSheetId() {
        return rateSheetId;
    }

    public void setRateSheetId(Long rateSheetId) {
        this.rateSheetId = rateSheetId;
    }

    public Long getPerWeightRateSheetId() {
        return perWeightRateSheetId;
    }

    public void setPerWeightRateSheetId(Long perWeightRateSheetId) {
        this.perWeightRateSheetId = perWeightRateSheetId;
    }

    public Long getNcRateSheetId() {
        return ncRateSheetId;
    }

    public void setNcRateSheetId(Long ncRateSheetId) {
        this.ncRateSheetId = ncRateSheetId;
    }

    public Long getNcPerWeightRateSheetId() {
        return ncPerWeightRateSheetId;
    }

    public void setNcPerWeightRateSheetId(Long ncPerWeightRateSheetId) {
        this.ncPerWeightRateSheetId = ncPerWeightRateSheetId;
    }

    @Override
    public String toString() {
        return "CustProfileServiceTypeVo [shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", serviceId=" + serviceId + ", allowCarrier=" + allowCarrier + ", allowNonCarrier=" + allowNonCarrier + ", showStatus=" + showStatus + ", documentType=" + documentType + ", content=" + content + ", bound=" + bound + ", carrierDocRate=" + carrierDocRate + ", carrierDocInRate=" + carrierDocInRate + ", carrierPackRate=" + carrierPackRate + ", carrierPackPerWeightRate="
                + carrierPackPerWeightRate + ", carrierPackInRate=" + carrierPackInRate + ", carrierPackInPerWeightRate=" + carrierPackInPerWeightRate + ", carrierPakRate=" + carrierPakRate + ", carrierPakPerWeightRate=" + carrierPakPerWeightRate + ", carrierPakInRate=" + carrierPakInRate + ", carrierPakInPerWeightRate=" + carrierPakInPerWeightRate + ", nonCarrierDocRate=" + nonCarrierDocRate + ", nonCarrierDocInRate=" + nonCarrierDocInRate + ", nonCarrierPackRate=" + nonCarrierPackRate
                + ", nonCarrierPackPerWeightRate=" + nonCarrierPackPerWeightRate + ", nonCarrierPackInRate=" + nonCarrierPackInRate + ", nonCarrierPackInPerWeightRate=" + nonCarrierPackInPerWeightRate + ", nonCarrierPakRate=" + nonCarrierPakRate + ", nonCarrierPakPerWeightRate=" + nonCarrierPakPerWeightRate + ", nonCarrierPakInRate=" + nonCarrierPakInRate + ", nonCarrierPakInPerWeightRate=" + nonCarrierPakInPerWeightRate + ", custProfileBaseRates=" + custProfileBaseRates + ", zones=" + zones
                + ", displayName=" + displayName + ", rateSheetId=" + rateSheetId + ", perWeightRateSheetId=" + perWeightRateSheetId + ", ncRateSheetId=" + ncRateSheetId + ", ncPerWeightRateSheetId=" + ncPerWeightRateSheetId + "]";
    }
}

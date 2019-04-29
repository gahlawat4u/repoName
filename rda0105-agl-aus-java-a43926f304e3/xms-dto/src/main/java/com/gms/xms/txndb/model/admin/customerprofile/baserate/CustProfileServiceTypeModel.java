package com.gms.xms.txndb.model.admin.customerprofile.baserate;

import com.gms.xms.model.BaseModel;

import java.util.List;

/**
 * Posted from CustProfileServiceTypeModel
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class CustProfileServiceTypeModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String shipmentTypeId;
    private String shipmentTypeName;
    private String serviceId;
    private String allowCarrier;
    private String allowNonCarrier;
    private String showStatus;
    private String documentType;
    private String content;
    private String bound;
    private String carrierDocRate;
    private String carrierDocInRate;
    private String carrierPackRate;
    private String carrierPackPerWeightRate;
    private String carrierPackInRate;
    private String carrierPackInPerWeightRate;
    private String carrierPakRate;
    private String carrierPakPerWeightRate;
    private String carrierPakInRate;
    private String carrierPakInPerWeightRate;
    private String nonCarrierDocRate;
    private String nonCarrierDocInRate;
    private String nonCarrierPackRate;
    private String nonCarrierPackPerWeightRate;
    private String nonCarrierPackInRate;
    private String nonCarrierPackInPerWeightRate;
    private String nonCarrierPakRate;
    private String nonCarrierPakPerWeightRate;
    private String nonCarrierPakInRate;
    private String nonCarrierPakInPerWeightRate;
    private List<CustProfileBaseRateModel> custProfileBaseRates;
    private List<String> zones;
    private String displayName;
    private String rateSheetId;
    private String perWeightRateSheetId;
    private String ncRateSheetId;
    private String ncPerWeightRateSheetId;

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getAllowCarrier() {
        return allowCarrier;
    }

    public void setAllowCarrier(String allowCarrier) {
        this.allowCarrier = allowCarrier;
    }

    public String getAllowNonCarrier() {
        return allowNonCarrier;
    }

    public void setAllowNonCarrier(String allowNonCarrier) {
        this.allowNonCarrier = allowNonCarrier;
    }

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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

    public String getCarrierDocRate() {
        return carrierDocRate;
    }

    public void setCarrierDocRate(String carrierDocRate) {
        this.carrierDocRate = carrierDocRate;
    }

    public String getCarrierDocInRate() {
        return carrierDocInRate;
    }

    public void setCarrierDocInRate(String carrierDocInRate) {
        this.carrierDocInRate = carrierDocInRate;
    }

    public String getCarrierPackRate() {
        return carrierPackRate;
    }

    public void setCarrierPackRate(String carrierPackRate) {
        this.carrierPackRate = carrierPackRate;
    }

    public String getCarrierPackPerWeightRate() {
        return carrierPackPerWeightRate;
    }

    public void setCarrierPackPerWeightRate(String carrierPackPerWeightRate) {
        this.carrierPackPerWeightRate = carrierPackPerWeightRate;
    }

    public String getCarrierPackInRate() {
        return carrierPackInRate;
    }

    public void setCarrierPackInRate(String carrierPackInRate) {
        this.carrierPackInRate = carrierPackInRate;
    }

    public String getCarrierPackInPerWeightRate() {
        return carrierPackInPerWeightRate;
    }

    public void setCarrierPackInPerWeightRate(String carrierPackInPerWeightRate) {
        this.carrierPackInPerWeightRate = carrierPackInPerWeightRate;
    }

    public String getCarrierPakRate() {
        return carrierPakRate;
    }

    public void setCarrierPakRate(String carrierPakRate) {
        this.carrierPakRate = carrierPakRate;
    }

    public String getCarrierPakPerWeightRate() {
        return carrierPakPerWeightRate;
    }

    public void setCarrierPakPerWeightRate(String carrierPakPerWeightRate) {
        this.carrierPakPerWeightRate = carrierPakPerWeightRate;
    }

    public String getCarrierPakInRate() {
        return carrierPakInRate;
    }

    public void setCarrierPakInRate(String carrierPakInRate) {
        this.carrierPakInRate = carrierPakInRate;
    }

    public String getCarrierPakInPerWeightRate() {
        return carrierPakInPerWeightRate;
    }

    public void setCarrierPakInPerWeightRate(String carrierPakInPerWeightRate) {
        this.carrierPakInPerWeightRate = carrierPakInPerWeightRate;
    }

    public String getNonCarrierDocRate() {
        return nonCarrierDocRate;
    }

    public void setNonCarrierDocRate(String nonCarrierDocRate) {
        this.nonCarrierDocRate = nonCarrierDocRate;
    }

    public String getNonCarrierDocInRate() {
        return nonCarrierDocInRate;
    }

    public void setNonCarrierDocInRate(String nonCarrierDocInRate) {
        this.nonCarrierDocInRate = nonCarrierDocInRate;
    }

    public String getNonCarrierPackRate() {
        return nonCarrierPackRate;
    }

    public void setNonCarrierPackRate(String nonCarrierPackRate) {
        this.nonCarrierPackRate = nonCarrierPackRate;
    }

    public String getNonCarrierPackPerWeightRate() {
        return nonCarrierPackPerWeightRate;
    }

    public void setNonCarrierPackPerWeightRate(String nonCarrierPackPerWeightRate) {
        this.nonCarrierPackPerWeightRate = nonCarrierPackPerWeightRate;
    }

    public String getNonCarrierPackInRate() {
        return nonCarrierPackInRate;
    }

    public void setNonCarrierPackInRate(String nonCarrierPackInRate) {
        this.nonCarrierPackInRate = nonCarrierPackInRate;
    }

    public String getNonCarrierPackInPerWeightRate() {
        return nonCarrierPackInPerWeightRate;
    }

    public void setNonCarrierPackInPerWeightRate(String nonCarrierPackInPerWeightRate) {
        this.nonCarrierPackInPerWeightRate = nonCarrierPackInPerWeightRate;
    }

    public String getNonCarrierPakRate() {
        return nonCarrierPakRate;
    }

    public void setNonCarrierPakRate(String nonCarrierPakRate) {
        this.nonCarrierPakRate = nonCarrierPakRate;
    }

    public String getNonCarrierPakPerWeightRate() {
        return nonCarrierPakPerWeightRate;
    }

    public void setNonCarrierPakPerWeightRate(String nonCarrierPakPerWeightRate) {
        this.nonCarrierPakPerWeightRate = nonCarrierPakPerWeightRate;
    }

    public String getNonCarrierPakInRate() {
        return nonCarrierPakInRate;
    }

    public void setNonCarrierPakInRate(String nonCarrierPakInRate) {
        this.nonCarrierPakInRate = nonCarrierPakInRate;
    }

    public String getNonCarrierPakInPerWeightRate() {
        return nonCarrierPakInPerWeightRate;
    }

    public void setNonCarrierPakInPerWeightRate(String nonCarrierPakInPerWeightRate) {
        this.nonCarrierPakInPerWeightRate = nonCarrierPakInPerWeightRate;
    }

    public List<CustProfileBaseRateModel> getCustProfileBaseRates() {
        return custProfileBaseRates;
    }

    public void setCustProfileBaseRates(List<CustProfileBaseRateModel> custProfileBaseRates) {
        this.custProfileBaseRates = custProfileBaseRates;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getZones() {
        return zones;
    }

    public void setZones(List<String> zones) {
        this.zones = zones;
    }

    public String getRateSheetId() {
        return rateSheetId;
    }

    public void setRateSheetId(String rateSheetId) {
        this.rateSheetId = rateSheetId;
    }

    public String getPerWeightRateSheetId() {
        return perWeightRateSheetId;
    }

    public void setPerWeightRateSheetId(String perWeightRateSheetId) {
        this.perWeightRateSheetId = perWeightRateSheetId;
    }

    public String getNcRateSheetId() {
        return ncRateSheetId;
    }

    public void setNcRateSheetId(String ncRateSheetId) {
        this.ncRateSheetId = ncRateSheetId;
    }

    public String getNcPerWeightRateSheetId() {
        return ncPerWeightRateSheetId;
    }

    public void setNcPerWeightRateSheetId(String ncPerWeightRateSheetId) {
        this.ncPerWeightRateSheetId = ncPerWeightRateSheetId;
    }

    @Override
    public String toString() {
        return "CustProfileServiceTypeModel [shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", serviceId=" + serviceId + ", allowCarrier=" + allowCarrier + ", allowNonCarrier=" + allowNonCarrier + ", showStatus=" + showStatus + ", documentType=" + documentType + ", content=" + content + ", bound=" + bound + ", carrierDocRate=" + carrierDocRate + ", carrierDocInRate=" + carrierDocInRate + ", carrierPackRate=" + carrierPackRate + ", carrierPackPerWeightRate="
                + carrierPackPerWeightRate + ", carrierPackInRate=" + carrierPackInRate + ", carrierPackInPerWeightRate=" + carrierPackInPerWeightRate + ", carrierPakRate=" + carrierPakRate + ", carrierPakPerWeightRate=" + carrierPakPerWeightRate + ", carrierPakInRate=" + carrierPakInRate + ", carrierPakInPerWeightRate=" + carrierPakInPerWeightRate + ", nonCarrierDocRate=" + nonCarrierDocRate + ", nonCarrierDocInRate=" + nonCarrierDocInRate + ", nonCarrierPackRate=" + nonCarrierPackRate
                + ", nonCarrierPackPerWeightRate=" + nonCarrierPackPerWeightRate + ", nonCarrierPackInRate=" + nonCarrierPackInRate + ", nonCarrierPackInPerWeightRate=" + nonCarrierPackInPerWeightRate + ", nonCarrierPakRate=" + nonCarrierPakRate + ", nonCarrierPakPerWeightRate=" + nonCarrierPakPerWeightRate + ", nonCarrierPakInRate=" + nonCarrierPakInRate + ", nonCarrierPakInPerWeightRate=" + nonCarrierPakInPerWeightRate + ", custProfileBaseRates=" + custProfileBaseRates + ", zones=" + zones
                + ", displayName=" + displayName + ", rateSheetId=" + rateSheetId + ", perWeightRateSheetId=" + perWeightRateSheetId + ", ncRateSheetId=" + ncRateSheetId + ", ncPerWeightRateSheetId=" + ncPerWeightRateSheetId + "]";
    }
}

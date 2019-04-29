package com.gms.xms.txndb.vo.ratesheet;

/**
 * Posted from Jul 23, 2016 9:15:22 AM
 * <p>
 * Author dattrinh
 */
public class ViewRateSheetShipmentVo {
    private Long shipmentId;
    private String airbillNumber;
    private String customerCode;
    private Double minimunBaseCharge;
    private Long carrier;
    private String description;
    private String displayDescription;
    private String serviceAreaCodeOrigin;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
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

    public Double getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(Double minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public Long getCarrier() {
        return carrier;
    }

    public void setCarrier(Long carrier) {
        this.carrier = carrier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public String getServiceAreaCodeOrigin() {
        return serviceAreaCodeOrigin;
    }

    public void setServiceAreaCodeOrigin(String serviceAreaCodeOrigin) {
        this.serviceAreaCodeOrigin = serviceAreaCodeOrigin;
    }

    @Override
    public String toString() {
        return "ViewRateSheetShipmentVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", customerCode=" + customerCode + ", minimunBaseCharge=" + minimunBaseCharge + ", carrier=" + carrier + ", description=" + description + ", displayDescription=" + displayDescription + ", serviceAreaCodeOrigin=" + serviceAreaCodeOrigin + "]";
    }
}

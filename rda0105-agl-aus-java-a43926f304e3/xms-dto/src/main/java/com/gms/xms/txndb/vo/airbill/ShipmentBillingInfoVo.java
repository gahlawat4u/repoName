package com.gms.xms.txndb.vo.airbill;

/**
 * Posted from May 16, 2016 2:37:51 PM
 * <p>
 * Author dattrinh
 */
public class ShipmentBillingInfoVo {
    private Long shipmentId;
    private String airbillNumber;
    private String customerCode;
    private Integer shipmentTypeId;
    private Integer content;
    private Integer bound;
    private Integer serviceId;

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

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "ShipmentBillingInfoVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", customerCode=" + customerCode + ", shipmentTypeId=" + shipmentTypeId + ", content=" + content + ", bound=" + bound + ", serviceId=" + serviceId + "]";
    }
}

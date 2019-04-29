package com.gms.xms.model.statistics;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Aug 17, 2016 11:25:06 AM
 * <p>
 * Author dattrinh
 */
public class StatWebshipLogModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private String createDate;
    private String customerName;
    private String shipmentStatus;
    private String airbillNumber;
    private String serviceName;
    private String logInfo;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    @Override
    public String toString() {
        return "StatWebshipLogModel [customerCode=" + customerCode + ", createDate=" + createDate + ", customerName=" + customerName + ", shipmentStatus=" + shipmentStatus + ", airbillNumber=" + airbillNumber + ", serviceName=" + serviceName + "]";
    }
}
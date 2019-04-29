package com.gms.xms.model;

/**
 * Posted from AccountServiceModel
 * <p>
 * Author DatTV Sep 30, 2015
 */
public class AccountServiceModel extends BaseModel {

    private static final long serialVersionUID = 8411529213690505396L;

    private String customerCode;
    private String userType;
    private String serviceId;
    private String shipmentTypeId;

    @Override
    public String toString() {
        return "AccountServiceModel [customerCode=" + customerCode + ", userType=" + userType + ", serviceId=" + serviceId + ", shipmentTypeId=" + shipmentTypeId + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }
}

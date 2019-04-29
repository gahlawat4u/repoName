package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.BaseModel;

/**
 * Posted from ShipmentTypeSettingVo
 * <p>
 * Author DatTV Sep 30, 2015
 */
public class ShipmentTypeSettingModel extends BaseModel {

    private static final long serialVersionUID = -2685259938813399592L;

    private String shipmentTypeId;
    private String shipmentTypeName;
    private String serviceId;
    private String customerCode;
    private String userType;
    private String checked;

    @Override
    public String toString() {
        return "ShipmentTypeSettingModel [shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", serviceId=" + serviceId + ", customerCode=" + customerCode + ", userType=" + userType + ", checked=" + checked + "]";
    }

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

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}

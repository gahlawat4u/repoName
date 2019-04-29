package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from ShipmentTypeSettingVo
 * <p>
 * Author DatTV Sep 30, 2015
 */
public class ShipmentTypeSettingVo extends BaseVo {

    private static final long serialVersionUID = -9193843293589392601L;

    private Integer shipmentTypeId;
    private String shipmentTypeName;
    private Integer serviceId;
    private Long customerCode;
    private Integer userType;
    private Boolean checked;

    @Override
    public String toString() {
        return "ShipmentTypeSettingVo [shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", serviceId=" + serviceId + ", customerCode=" + customerCode + ", userType=" + userType + ", checked=" + checked + "]";
    }

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

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}

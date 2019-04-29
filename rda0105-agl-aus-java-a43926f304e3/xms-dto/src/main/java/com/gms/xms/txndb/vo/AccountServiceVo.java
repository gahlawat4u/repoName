package com.gms.xms.txndb.vo;

/**
 * Posted from AccountServiceVo
 * <p>
 * Author DatTV Sep 30, 2015
 */
public class AccountServiceVo extends BaseVo {

    private static final long serialVersionUID = 2289227034183780572L;

    private Long customerCode;
    private Integer userType;
    private Integer serviceId;
    private Integer shipmentTypeId;

    @Override
    public String toString() {
        return "AccountServiceVo [customerCode=" + customerCode + ", userType=" + userType + ", serviceId=" + serviceId + ", shipmentTypeId=" + shipmentTypeId + "]";
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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }
}

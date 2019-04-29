package com.gms.xms.filter.admin.ratesheets;

/**
 * Posted from Apr 9, 2016 10:19:14 AM
 * <p>
 * Author dattrinh
 */
public class ServiceMarkupRateFilter {
    private Integer serviceId;
    private Integer shipmentTypeId;
    private String code;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    @Override
    public String toString() {
        return "ServiceMarkupRateFilter [serviceId=" + serviceId + ", shipmentTypeId=" + shipmentTypeId + ", code=" + code + "]";
    }
}

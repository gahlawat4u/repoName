package com.gms.xms.dto.statistics;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Aug 17, 2016 11:25:06 AM
 * <p>
 * Author dattrinh
 */
public class StatServiceTypeVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer shipmentTypeId;
    private String shipmentTypeName;
    private Integer serviceType;
    private String serviceTypeName;
    private Long shipmentCount;

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

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public Long getShipmentCount() {
        return shipmentCount;
    }

    public void setShipmentCount(Long shipmentCount) {
        this.shipmentCount = shipmentCount;
    }

    @Override
    public String toString() {
        return "StatServiceTypeVo [shipmentTypeId=" + shipmentTypeId + ", shipmentTypeName=" + shipmentTypeName + ", serviceType=" + serviceType + ", serviceTypeName=" + serviceTypeName + ", shipmentCount=" + shipmentCount + "]";
    }
}

package com.gms.xms.filter.account.franchises;

import com.gms.xms.filter.BaseFilter;

public class FranchiseServiceMarkupFilter extends BaseFilter {

    private String franchiseCode;
    private Integer serviceId;
    private Integer shipmentTypeId;

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
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

    @Override
    public String toString() {
        return "FranchiseServiceMarkupFilter [franchiseCode=" + franchiseCode + ", serviceId=" + serviceId + ", shipmentTypeId=" + shipmentTypeId + "]";
    }

}

package com.gms.xms.filter.account.franchises;

import com.gms.xms.filter.BaseFilter;

public class MarkupRateFilter extends BaseFilter {

    private Integer shipmentTypeId;

    private Long customerCode;

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

}

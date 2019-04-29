package com.gms.xms.txndb.vo.admin.ratesheets;

import java.util.List;

/**
 * Posted from May 18, 2016 10:01:23 AM
 * <p>
 * Author dattrinh
 */
public class ViewRateSheetRequestVo {
    private Integer type; // type=0 -> customer, type=1 -> franchise, type=2 -> customer profile.
    private String customerCode;
    private Integer shipmentTypeId;
    private Integer content;
    private Integer bound;
    private String columnName;
    private Double minimumBaseCharge;
    private List<ViewRateSheetBaseRateVo> baseRates;

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

    public Double getMinimumBaseCharge() {
        return minimumBaseCharge;
    }

    public void setMinimumBaseCharge(Double minimumBaseCharge) {
        this.minimumBaseCharge = minimumBaseCharge;
    }

    public List<ViewRateSheetBaseRateVo> getBaseRates() {
        return baseRates;
    }

    public void setBaseRates(List<ViewRateSheetBaseRateVo> baseRates) {
        this.baseRates = baseRates;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ViewRateSheetRequestVo [type=" + type + ", customerCode=" + customerCode + ", shipmentTypeId=" + shipmentTypeId + ", content=" + content + ", bound=" + bound + ", columnName=" + columnName + ", minimumBaseCharge=" + minimumBaseCharge + ", baseRates=" + baseRates + "]";
    }
}

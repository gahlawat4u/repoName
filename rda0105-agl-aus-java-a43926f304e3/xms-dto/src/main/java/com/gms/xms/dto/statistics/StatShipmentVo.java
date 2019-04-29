package com.gms.xms.dto.statistics;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Aug 18, 2016 9:16:28 AM
 * <p>
 * Author dattrinh
 */
public class StatShipmentVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String columnName;
    private Long shipmentCount;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Long getShipmentCount() {
        return shipmentCount;
    }

    public void setShipmentCount(Long shipmentCount) {
        this.shipmentCount = shipmentCount;
    }

    @Override
    public String toString() {
        return "StatShipmentVo [columnName=" + columnName + ", shipmentCount=" + shipmentCount + "]";
    }
}

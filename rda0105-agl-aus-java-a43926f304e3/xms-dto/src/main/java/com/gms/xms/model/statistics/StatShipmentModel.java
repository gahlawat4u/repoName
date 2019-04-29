package com.gms.xms.model.statistics;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Aug 18, 2016 9:16:28 AM
 * <p>
 * Author dattrinh
 */
public class StatShipmentModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String columnName;
    private String shipmentCount;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getShipmentCount() {
        return shipmentCount;
    }

    public void setShipmentCount(String shipmentCount) {
        this.shipmentCount = shipmentCount;
    }

    @Override
    public String toString() {
        return "StatShipmentModel [columnName=" + columnName + ", shipmentCount=" + shipmentCount + "]";
    }
}

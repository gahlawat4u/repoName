package com.gms.xms.txndb.vo.admin;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from SearchAirbillShipmentTypeVo
 * <p>
 * Author @author thanh Mar 2, 2016
 */
public class SearchAirbillShipmentTypeVo extends BaseVo {
    private static final long serialVersionUID = 719526749905264371L;

    private String shipmentTypeKey;
    private String shipmentTypeName;

    public String getShipmentTypeKey() {
        return shipmentTypeKey;
    }

    public void setShipmentTypeKey(String shipmentTypeKey) {
        this.shipmentTypeKey = shipmentTypeKey;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    @Override
    public String toString() {
        return "SearchAirbillShipmentTypeVo [shipmentTypeKey=" + shipmentTypeKey + ", shipmentTypeName=" + shipmentTypeName + "]";
    }
}

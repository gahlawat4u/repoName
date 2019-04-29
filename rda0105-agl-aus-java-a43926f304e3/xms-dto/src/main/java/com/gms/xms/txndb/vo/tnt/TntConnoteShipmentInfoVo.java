package com.gms.xms.txndb.vo.tnt;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Created by thinhdd on 2/8/2017.
 */
public class TntConnoteShipmentInfoVo extends BaseVo {
    private Long shipmentid;
    private Long shipmentTypeId;
    private Long connoteId;

    public TntConnoteShipmentInfoVo(Long shipmentid, Long shipmentTypeId, Long connoteId) {
        this.shipmentid = shipmentid;
        this.shipmentTypeId = shipmentTypeId;
        this.connoteId = connoteId;
    }

    public TntConnoteShipmentInfoVo() {
    }

    public Long getShipmentid() {
        return shipmentid;
    }

    public void setShipmentid(Long shipmentid) {
        this.shipmentid = shipmentid;
    }

    public Long getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Long shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Long getConnoteId() {
        return connoteId;
    }

    public void setConnoteId(Long connoteId) {
        this.connoteId = connoteId;
    }
}

package com.gms.xms.txndb.vo.othercarrier;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from OtherConnoteVo
 * <p>
 * Author @author HungNT Feb 19, 2016
 */
public class OtherConnoteVo extends BaseVo {
    private static final long serialVersionUID = 1041456726572002484L;
    private Integer connoteId;
    private Long shipmentId;
    private String connNumber;

    public Integer getConnoteId() {
        return connoteId;
    }

    public void setConnoteId(Integer connoteId) {
        this.connoteId = connoteId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getConnNumber() {
        return connNumber;
    }

    public void setConnNumber(String connNumber) {
        this.connNumber = connNumber;
    }

    @Override
    public String toString() {
        return "OtherConnoteVo [connoteId=" + connoteId + ", shipmentId=" + shipmentId + ", connNumber=" + connNumber + "]";
    }
}

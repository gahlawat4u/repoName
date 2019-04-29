package com.gms.xms.txndb.vo.ups;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from TollConnoteVo
 * <p>
 * Author @author HungNT Feb 16, 2016
 */
public class UpsConnoteVo extends BaseVo {
    private static final long serialVersionUID = -1957992171573643050L;

    private Integer connoteId;
    private Long shipmentId;
    private String connNumber;
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpsConnoteVo [connoteId=" + connoteId + ", shipmentId=" + shipmentId + ", connNumber=" + connNumber + ", status=" + status + "]";
    }
}

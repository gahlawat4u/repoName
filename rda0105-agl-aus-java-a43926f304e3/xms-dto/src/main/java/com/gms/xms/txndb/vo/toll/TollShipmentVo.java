package com.gms.xms.txndb.vo.toll;

import com.gms.xms.txndb.vo.ShipmentVo;

/**
 * Posted from Sep 15, 2016 4:35:05 PM
 * <p>
 * Author huynd
 */
public class TollShipmentVo extends ShipmentVo {

    private static final long serialVersionUID = 1L;

    private Integer connoteId;
    private Long shipmentId;
    private String connNumber;
    private Integer status;
    private Integer manifestStatus;
    private String specialInstructions;

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

    public Integer getManifestStatus() {
        return manifestStatus;
    }

    public void setManifestStatus(Integer manifestStatus) {
        this.manifestStatus = manifestStatus;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    @Override
    public String toString() {
        return "TollShipmentVo [connoteId=" + connoteId + ", shipmentId=" + shipmentId + ", connNumber=" + connNumber + ", status=" + status + ", manifestStatus=" + manifestStatus + ", specialInstructions=" + specialInstructions + "]";
    }
}

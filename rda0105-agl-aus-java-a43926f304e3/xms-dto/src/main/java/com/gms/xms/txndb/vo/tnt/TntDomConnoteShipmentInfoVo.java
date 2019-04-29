package com.gms.xms.txndb.vo.tnt;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Created by thinhdd on 2/8/2017.
 */
public class TntDomConnoteShipmentInfoVo extends BaseVo {
    private Long shipmentId;
    private Long wmanifestId;
    private String manifestTime;
    private String manifestDate;

    public TntDomConnoteShipmentInfoVo() {
    }

    public TntDomConnoteShipmentInfoVo(Long shipmentId, Long wmanifestId, String manifestTime, String manifestDate) {
        this.shipmentId = shipmentId;
        this.wmanifestId = wmanifestId;
        this.manifestTime = manifestTime;
        this.manifestDate = manifestDate;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getWmanifestId() {
        return wmanifestId;
    }

    public void setWmanifestId(Long wmanifestId) {
        this.wmanifestId = wmanifestId;
    }

    public String getManifestTime() {
        return manifestTime;
    }

    public void setManifestTime(String manifestTime) {
        this.manifestTime = manifestTime;
    }

    public String getManifestDate() {
        return manifestDate;
    }

    public void setManifestDate(String manifestDate) {
        this.manifestDate = manifestDate;
    }
}

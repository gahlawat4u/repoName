package com.gms.xms.model.tnt;

import com.gms.xms.model.BaseModel;

public class TntConnoteModel extends BaseModel {
    private static final long serialVersionUID = -4551269517294346845L;

    private String connoteId;
    private String shipmentId;
    private String connNumber;
    private String transId;
    private String manifestIdentifier;
    private String wmanifestId;
    private String wmanifestCreatedate;

    public String getConnoteId() {
        return connoteId;
    }

    public void setConnoteId(String connoteId) {
        this.connoteId = connoteId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getConnNumber() {
        return connNumber;
    }

    public void setConnNumber(String connNumber) {
        this.connNumber = connNumber;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getManifestIdentifier() {
        return manifestIdentifier;
    }

    public void setManifestIdentifier(String manifestIdentifier) {
        this.manifestIdentifier = manifestIdentifier;
    }

    public String getWmanifestId() {
        return wmanifestId;
    }

    public void setWmanifestId(String wmanifestId) {
        this.wmanifestId = wmanifestId;
    }

    public String getWmanifestCreatedate() {
        return wmanifestCreatedate;
    }

    public void setWmanifestCreatedate(String wmanifestCreatedate) {
        this.wmanifestCreatedate = wmanifestCreatedate;
    }

    @Override
    public String toString() {
        return "TntConnoteModel [connoteId=" + connoteId + ", shipmentId=" + shipmentId + ", connNumber=" + connNumber + ", transId=" + transId + ", manifestIdentifier=" + manifestIdentifier + ", wmanifestId=" + wmanifestId + ", wmanifestCreatedate=" + wmanifestCreatedate + "]";
    }
}

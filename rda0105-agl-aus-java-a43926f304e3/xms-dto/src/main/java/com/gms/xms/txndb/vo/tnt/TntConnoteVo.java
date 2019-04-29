package com.gms.xms.txndb.vo.tnt;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

public class TntConnoteVo extends BaseVo {
    private static final long serialVersionUID = -2471111521342970067L;

    private Long connoteId;
    private Long shipmentId;
    private Long connNumber;
    private Long transId;
    private Long manifestIdentifier;
    private Integer wmanifestId;
    private Date wmanifestCreatedate;

    public Long getConnoteId() {
        return connoteId;
    }

    public void setConnoteId(Long connoteId) {
        this.connoteId = connoteId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Long getConnNumber() {
        return connNumber;
    }

    public void setConnNumber(Long connNumber) {
        this.connNumber = connNumber;
    }

    public Long getTransId() {
        return transId;
    }

    public void setTransId(Long transId) {
        this.transId = transId;
    }

    public Long getManifestIdentifier() {
        return manifestIdentifier;
    }

    public void setManifestIdentifier(Long manifestIdentifier) {
        this.manifestIdentifier = manifestIdentifier;
    }

    public Integer getWmanifestId() {
        return wmanifestId;
    }

    public void setWmanifestId(Integer wmanifestId) {
        this.wmanifestId = wmanifestId;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getWmanifestCreatedate() {
        return wmanifestCreatedate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setWmanifestCreatedate(Date wmanifestCreatedate) {
        this.wmanifestCreatedate = wmanifestCreatedate;
    }

    @Override
    public String toString() {
        return "TntConnoteVo [connoteId=" + connoteId + ", shipmentId=" + shipmentId + ", connNumber=" + connNumber + ", transId=" + transId + ", manifestIdentifier=" + manifestIdentifier + ", wmanifestId=" + wmanifestId + ", wmanifestCreatedate=" + wmanifestCreatedate + "]";
    }
}

package com.gms.xms.txndb.vo.admin.systemstats;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Aug 31, 2016 3:18:18 PM
 * <p>
 * Author dattrinh
 */
public class IdsVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String webshipId;
    private String shipmentId;
    private String senderAddressId;
    private String receiverAddressId;
    private String scheduleCollectionId;
    private String scheduleAddressId;
    private String pieceId;

    public String getWebshipId() {
        return webshipId;
    }

    public void setWebshipId(String webshipId) {
        this.webshipId = webshipId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(String senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public String getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(String receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public String getScheduleCollectionId() {
        return scheduleCollectionId;
    }

    public void setScheduleCollectionId(String scheduleCollectionId) {
        this.scheduleCollectionId = scheduleCollectionId;
    }

    public String getScheduleAddressId() {
        return scheduleAddressId;
    }

    public void setScheduleAddressId(String scheduleAddressId) {
        this.scheduleAddressId = scheduleAddressId;
    }

    public String getPieceId() {
        return pieceId;
    }

    public void setPieceId(String pieceId) {
        this.pieceId = pieceId;
    }

    @Override
    public String toString() {
        return "IdsVo [webshipId=" + webshipId + ", shipmentId=" + shipmentId + ", senderAddressId=" + senderAddressId + ", receiverAddressId=" + receiverAddressId + ", scheduleCollectionId=" + scheduleCollectionId + ", scheduleAddressId=" + scheduleAddressId + ", pieceId=" + pieceId + "]";
    }
}

package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.model.SendAirlbillHistoryFillterModel;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentFilter;
import com.gms.xms.txndb.vo.ShipmentNoteVo;

import java.util.Arrays;

/**
 * Posted from HistoryRequest
 * <p>
 * Author TanDT Date Apr 18, 2015
 */
public class HistoryRequest extends BaseRequest {
    private Long shipmentId;
    private Integer shipmentTypeId;
    private Integer schID;
    private Integer isforcevoid;
    private ShipmentFilter shipmentFilter;
    private ScheduleCollectionVo scheduleCollectionVo;
    private ShipmentNoteVo shipmentNoteVo;
    private SendAirlbillHistoryFillterModel sendAirlbillHistoryFillterModel;
    private String[] listShipmentId;
    private String collectionNoNew;

    public String getCollectionNoNew() {
        return collectionNoNew;
    }

    public void setCollectionNoNew(String collectionNoNew) {
        this.collectionNoNew = collectionNoNew;
    }

    public String[] getListShipmentId() {
        return listShipmentId;
    }

    public void setListShipmentId(String[] listShipmentId) {
        this.listShipmentId = listShipmentId;
    }

    public ShipmentNoteVo getShipmentNoteVo() {
        return shipmentNoteVo;
    }

    public void setShipmentNoteVo(ShipmentNoteVo shipmentNoteVo) {
        this.shipmentNoteVo = shipmentNoteVo;
    }

    private AddressVo addressVo;

    public ScheduleCollectionVo getScheduleCollectionVo() {
        return scheduleCollectionVo;
    }

    public void setScheduleCollectionVo(ScheduleCollectionVo scheduleCollectionVo) {
        this.scheduleCollectionVo = scheduleCollectionVo;
    }

    public AddressVo getAddressVo() {
        return addressVo;
    }

    public SendAirlbillHistoryFillterModel getSendAirlbillHistoryFillterModel() {
        return sendAirlbillHistoryFillterModel;
    }

    public void setSendAirlbillHistoryFillterModel(SendAirlbillHistoryFillterModel sendAirlbillHistoryFillterModel) {
        this.sendAirlbillHistoryFillterModel = sendAirlbillHistoryFillterModel;
    }

    public void setAddressVo(AddressVo addressVo) {
        this.addressVo = addressVo;
    }

    public ShipmentFilter getShipmentFilter() {
        return shipmentFilter;
    }

    public void setShipmentFilter(ShipmentFilter shipmentFilter) {
        this.shipmentFilter = shipmentFilter;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Integer getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(Integer shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public Integer getSchID() {
        return schID;
    }

    public void setSchID(Integer schID) {
        this.schID = schID;
    }

    public Integer getIsforcevoid() {
        return isforcevoid;
    }

    public void setIsforcevoid(Integer isforcevoid) {
        this.isforcevoid = isforcevoid;
    }

    @Override
    public String toString() {
        return "HistoryRequest [shipmentId=" + shipmentId + ", shipmentTypeId=" + shipmentTypeId + ", schID=" + schID + ", isforcevoid=" + isforcevoid + ", shipmentFilter=" + shipmentFilter + ", scheduleCollectionVo=" + scheduleCollectionVo + ", shipmentNoteVo=" + shipmentNoteVo + ", sendAirlbillHistoryFillterModel=" + sendAirlbillHistoryFillterModel + ", listShipmentId=" + Arrays.toString(listShipmentId) + ", collectionNoNew=" + collectionNoNew + ", addressVo=" + addressVo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressVo == null) ? 0 : addressVo.hashCode());
        result = prime * result + ((collectionNoNew == null) ? 0 : collectionNoNew.hashCode());
        result = prime * result + ((isforcevoid == null) ? 0 : isforcevoid.hashCode());
        result = prime * result + Arrays.hashCode(listShipmentId);
        result = prime * result + ((schID == null) ? 0 : schID.hashCode());
        result = prime * result + ((scheduleCollectionVo == null) ? 0 : scheduleCollectionVo.hashCode());
        result = prime * result + ((sendAirlbillHistoryFillterModel == null) ? 0 : sendAirlbillHistoryFillterModel.hashCode());
        result = prime * result + ((shipmentFilter == null) ? 0 : shipmentFilter.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((shipmentNoteVo == null) ? 0 : shipmentNoteVo.hashCode());
        result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HistoryRequest other = (HistoryRequest) obj;
        if (addressVo == null) {
            if (other.addressVo != null)
                return false;
        } else if (!addressVo.equals(other.addressVo))
            return false;
        if (collectionNoNew == null) {
            if (other.collectionNoNew != null)
                return false;
        } else if (!collectionNoNew.equals(other.collectionNoNew))
            return false;
        if (isforcevoid == null) {
            if (other.isforcevoid != null)
                return false;
        } else if (!isforcevoid.equals(other.isforcevoid))
            return false;
        if (!Arrays.equals(listShipmentId, other.listShipmentId))
            return false;
        if (schID == null) {
            if (other.schID != null)
                return false;
        } else if (!schID.equals(other.schID))
            return false;
        if (scheduleCollectionVo == null) {
            if (other.scheduleCollectionVo != null)
                return false;
        } else if (!scheduleCollectionVo.equals(other.scheduleCollectionVo))
            return false;
        if (sendAirlbillHistoryFillterModel == null) {
            if (other.sendAirlbillHistoryFillterModel != null)
                return false;
        } else if (!sendAirlbillHistoryFillterModel.equals(other.sendAirlbillHistoryFillterModel))
            return false;
        if (shipmentFilter == null) {
            if (other.shipmentFilter != null)
                return false;
        } else if (!shipmentFilter.equals(other.shipmentFilter))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (shipmentNoteVo == null) {
            if (other.shipmentNoteVo != null)
                return false;
        } else if (!shipmentNoteVo.equals(other.shipmentNoteVo))
            return false;
        if (shipmentTypeId == null) {
            if (other.shipmentTypeId != null)
                return false;
        } else if (!shipmentTypeId.equals(other.shipmentTypeId))
            return false;
        return true;
    }

}

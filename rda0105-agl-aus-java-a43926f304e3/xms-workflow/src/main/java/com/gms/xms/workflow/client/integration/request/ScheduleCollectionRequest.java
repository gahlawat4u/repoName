package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;

/**
 * Posted from ScheduleCollectionRequest
 * <p>
 * Author TanDT Date Apr 22, 2015
 */
public class ScheduleCollectionRequest extends BaseRequest {
    private Long shipmentId;
    private ScheduleCollectionVo scheduleCollectionVo;
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

    public void setAddressVo(AddressVo addressVo) {
        this.addressVo = addressVo;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressVo == null) ? 0 : addressVo.hashCode());
        result = prime * result + ((scheduleCollectionVo == null) ? 0 : scheduleCollectionVo.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
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
        ScheduleCollectionRequest other = (ScheduleCollectionRequest) obj;
        if (addressVo == null) {
            if (other.addressVo != null)
                return false;
        } else if (!addressVo.equals(other.addressVo))
            return false;
        if (scheduleCollectionVo == null) {
            if (other.scheduleCollectionVo != null)
                return false;
        } else if (!scheduleCollectionVo.equals(other.scheduleCollectionVo))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ScheduleCollectionRequest [shipmentId=" + shipmentId + ", scheduleCollectionVo=" + scheduleCollectionVo + ", addressVo=" + addressVo + "]";
    }

}

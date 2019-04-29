package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentVo;

/**
 * Posted from ScheduleCollectionResponse
 * <p>
 * Author TanDT Date Apr 22, 2015
 */
public class ScheduleCollectionResponse extends BaseResponse {
    private ScheduleCollectionVo scheduleCollectionVo;
    private AddressVo addressVo;
    private ShipmentVo shipmentVo;

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

    public ShipmentVo getShipmentVo() {
        return shipmentVo;
    }

    public void setShipmentVo(ShipmentVo shipmentVo) {
        this.shipmentVo = shipmentVo;
    }

    @Override
    public String toString() {
        return "ScheduleCollectionResponse [scheduleCollectionVo=" + scheduleCollectionVo + ", addressVo=" + addressVo + ", shipmentVo=" + shipmentVo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addressVo == null) ? 0 : addressVo.hashCode());
        result = prime * result + ((scheduleCollectionVo == null) ? 0 : scheduleCollectionVo.hashCode());
        result = prime * result + ((shipmentVo == null) ? 0 : shipmentVo.hashCode());
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
        ScheduleCollectionResponse other = (ScheduleCollectionResponse) obj;
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
        if (shipmentVo == null) {
            if (other.shipmentVo != null)
                return false;
        } else if (!shipmentVo.equals(other.shipmentVo))
            return false;
        return true;
    }

}

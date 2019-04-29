package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.TrackingVo;

import java.util.List;

/**
 * Posted from TrackingResponse
 * <p>
 * Author TanDT Date Apr 15, 2015
 */
public class TrackingResponse extends BaseResponse {
    private List<TrackingVo> listTrackingVo;
    private ShipmentVo shipmentVo;
    private List<ShipmentVo> shipmentVos;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((listTrackingVo == null) ? 0 : listTrackingVo.hashCode());
        result = prime * result + ((shipmentVo == null) ? 0 : shipmentVo.hashCode());
        result = prime * result + ((shipmentVos == null) ? 0 : shipmentVos.hashCode());
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
        TrackingResponse other = (TrackingResponse) obj;
        if (listTrackingVo == null) {
            if (other.listTrackingVo != null)
                return false;
        } else if (!listTrackingVo.equals(other.listTrackingVo))
            return false;
        if (shipmentVo == null) {
            if (other.shipmentVo != null)
                return false;
        } else if (!shipmentVo.equals(other.shipmentVo))
            return false;
        if (shipmentVos == null) {
            if (other.shipmentVos != null)
                return false;
        } else if (!shipmentVos.equals(other.shipmentVos))
            return false;
        return true;
    }

    public List<ShipmentVo> getShipmentVos() {
        return shipmentVos;
    }

    public void setShipmentVos(List<ShipmentVo> shipmentVos) {
        this.shipmentVos = shipmentVos;
    }

    public List<TrackingVo> getListTrackingVo() {
        return listTrackingVo;
    }

    public void setListTrackingVo(List<TrackingVo> listTrackingVo) {
        this.listTrackingVo = listTrackingVo;
    }

    public ShipmentVo getShipmentVo() {
        return shipmentVo;
    }

    public void setShipmentVo(ShipmentVo shipmentVo) {
        this.shipmentVo = shipmentVo;
    }

    @Override
    public String toString() {
        return "TrackingResponse [listTrackingVo=" + listTrackingVo + ", shipmentVo=" + shipmentVo + ", shipmentVos=" + shipmentVos + "]";
    }

}

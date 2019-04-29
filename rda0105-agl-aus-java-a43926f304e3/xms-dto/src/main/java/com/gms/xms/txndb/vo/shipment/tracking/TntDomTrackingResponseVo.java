package com.gms.xms.txndb.vo.shipment.tracking;

import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;

import java.util.List;

/**
 * Posted from TntDomTrackingResponseVo
 * <p>
 * Author dattrinh Feb 3, 2016 11:07:32 AM
 */
public class TntDomTrackingResponseVo {
    private HistoryDetailInfoVo shipment;
    List<TrackingVo> trackingList;

    public HistoryDetailInfoVo getShipment() {
        return shipment;
    }

    public void setShipment(HistoryDetailInfoVo shipment) {
        this.shipment = shipment;
    }

    public List<TrackingVo> getTrackingList() {
        return trackingList;
    }

    public void setTrackingList(List<TrackingVo> trackingList) {
        this.trackingList = trackingList;
    }

    @Override
    public String toString() {
        return "TntDomTrackingResponseVo [shipment=" + shipment + ", trackingList=" + trackingList + "]";
    }
}

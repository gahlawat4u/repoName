package com.gms.xms.dto.delivery.dhl;

import com.gms.xms.txndb.vo.DhlCountryVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentVo;

/**
 * Posted from DhlCancelPickupRequestVo
 * <p>
 * Author dattrinh Jan 18, 2016 4:04:29 PM
 */
public class DhlCancelPickupRequestVo {
    private ScheduleCollectionVo scheduleCollection;
    private ShipmentVo shipment;
    private String reason;
    private String cancelTime;
    private DhlCountryVo dhlCountry;

    public ScheduleCollectionVo getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(ScheduleCollectionVo scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public ShipmentVo getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentVo shipment) {
        this.shipment = shipment;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    @Override
    public String toString() {
        return "DHLCancelPickupRequestVo [scheduleCollection=" + scheduleCollection + ", shipment=" + shipment + ", reason=" + reason + ", cancelTime=" + cancelTime + ", dhlCountry=" + dhlCountry + "]";
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DhlCountryVo getDhlCountry() {
        return dhlCountry;
    }

    public void setDhlCountry(DhlCountryVo dhlCountry) {
        this.dhlCountry = dhlCountry;
    }
}

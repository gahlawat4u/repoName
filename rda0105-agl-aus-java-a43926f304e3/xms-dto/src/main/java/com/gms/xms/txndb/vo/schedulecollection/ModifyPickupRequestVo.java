package com.gms.xms.txndb.vo.schedulecollection;

import com.gms.xms.txndb.vo.*;

/**
 * Posted from ModifyPickupRequestVo
 * <p>
 * Author dattrinh Jan 22, 2016 5:19:10 PM
 */
public class ModifyPickupRequestVo {
    private ScheduleCollectionVo scheduleCollection;
    private AddressVo senderAddress;
    private AddressVo pickupAddress;
    private ShipmentVo shipment;
    private LocationCodeVo locationCode;
    private DhlCountryVo dhlCountry;

    public ScheduleCollectionVo getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(ScheduleCollectionVo scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public AddressVo getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressVo senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressVo getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(AddressVo pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public ShipmentVo getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentVo shipment) {
        this.shipment = shipment;
    }

    public LocationCodeVo getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(LocationCodeVo locationCode) {
        this.locationCode = locationCode;
    }

    public DhlCountryVo getDhlCountry() {
        return dhlCountry;
    }

    public void setDhlCountry(DhlCountryVo dhlCountry) {
        this.dhlCountry = dhlCountry;
    }

    @Override
    public String toString() {
        return "ModifyPickupRequestVo [scheduleCollection=" + scheduleCollection + ", senderAddress=" + senderAddress + ", pickupAddress=" + pickupAddress + ", shipment=" + shipment + ", locationCode=" + locationCode + ", dhlCountry=" + dhlCountry + "]";
    }
}

package com.gms.xms.txndb.vo.shipment;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.PackageVo;

/**
 * Posted from TntShipmentRequestVo
 * <p>
 * Author dattrinh Jan 30, 2016 9:53:13 AM
 */
public class TntDomShipmentRequestVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private ShipmentInfoVo shipmentInfo;
    private String shipmentReference;
    private ScheduleCollectionVo scheduleCollection;
    private String serviceCode;
    private PackageVo packageInfo;

    public PackageVo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageVo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public ShipmentInfoVo getShipmentInfo() {
        return shipmentInfo;
    }

    public void setShipmentInfo(ShipmentInfoVo shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }

    public String getShipmentReference() {
        return shipmentReference;
    }

    public void setShipmentReference(String shipmentReference) {
        this.shipmentReference = shipmentReference;
    }

    public ScheduleCollectionVo getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(ScheduleCollectionVo scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    @Override
    public String toString() {
        return "TntShipmentRequestVo [shipmentInfo=" + shipmentInfo + ", shipmentReference=" + shipmentReference + ", scheduleCollection=" + scheduleCollection + ", serviceCode=" + serviceCode + ", packageInfo=" + packageInfo + "]";
    }
}

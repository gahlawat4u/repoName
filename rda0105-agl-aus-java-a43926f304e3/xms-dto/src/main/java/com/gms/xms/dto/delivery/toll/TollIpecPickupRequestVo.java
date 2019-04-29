package com.gms.xms.dto.delivery.toll;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;

/**
 * Posted from TollPickupRequestVo
 * <p>
 * Author dattrinh Feb 16, 2016 8:51:00 AM
 */
public class TollIpecPickupRequestVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private String documentType;
    private String documentId;
    private String accountCode;
    private ScheduleCollectionVo scheduleCollection;
    private ShipmentInfoVo shipment;
    private Boolean bringConNote;
    private String serviceId;
    private String productId;
    private String largestItemType;
    private Double weight;
    private Integer length;
    private Integer width;
    private Integer height;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public ScheduleCollectionVo getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(ScheduleCollectionVo scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    public ShipmentInfoVo getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentInfoVo shipment) {
        this.shipment = shipment;
    }

    public Boolean getBringConNote() {
        return bringConNote;
    }

    public void setBringConNote(Boolean bringConNote) {
        this.bringConNote = bringConNote;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLargestItemType() {
        return largestItemType;
    }

    public void setLargestItemType(String largestItemType) {
        this.largestItemType = largestItemType;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "TollIpecPickupRequestVo [documentType=" + documentType + ", documentId=" + documentId + ", accountCode=" + accountCode + ", scheduleCollection=" + scheduleCollection + ", shipment=" + shipment + ", bringConNote=" + bringConNote + ", serviceId=" + serviceId + ", productId=" + productId + ", largestItemType=" + largestItemType + ", weight=" + weight + ", length=" + length + ", width=" + width + ", height=" + height + "]";
    }
}

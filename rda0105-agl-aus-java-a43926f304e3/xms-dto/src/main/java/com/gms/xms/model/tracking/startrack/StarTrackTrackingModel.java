package com.gms.xms.model.tracking.startrack;

import java.util.List;

/**
 * Posted from Jul 19, 2016 11:49:18 AM
 * <p>
 * Author dattrinh
 */
public class StarTrackTrackingModel {
    private String trackingNumber;
    private String shippedBy;
    private String signedForBy;
    private String destination;
    private String consignee;
    private String shipDate;
    private String deliveryDate;
    private String serviceType;
    private String weight;
    private String status;
    private String dimensionWeight;
    private List<StarTrackTrackingEventModel> events;

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShippedBy() {
        return shippedBy;
    }

    public void setShippedBy(String shippedBy) {
        this.shippedBy = shippedBy;
    }

    public String getSignedForBy() {
        return signedForBy;
    }

    public void setSignedForBy(String signedForBy) {
        this.signedForBy = signedForBy;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDimensionWeight() {
        return dimensionWeight;
    }

    public void setDimensionWeight(String dimensionWeight) {
        this.dimensionWeight = dimensionWeight;
    }

    public List<StarTrackTrackingEventModel> getEvents() {
        return events;
    }

    public void setEvents(List<StarTrackTrackingEventModel> events) {
        this.events = events;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    @Override
    public String toString() {
        return "StarTrackTrackingModel [trackingNumber=" + trackingNumber + ", shippedBy=" + shippedBy + ", signedForBy=" + signedForBy + ", destination=" + destination + ", consignee=" + consignee + ", shipDate=" + shipDate + ", deliveryDate=" + deliveryDate + ", serviceType=" + serviceType + ", weight=" + weight + ", status=" + status + ", dimensionWeight=" + dimensionWeight + ", events=" + events + "]";
    }
}

package com.gms.xms.model;


/**
 * Posted from TrackingModel
 * <p>
 * Author TanDT Date Apr 14, 2015
 */
public class TrackingModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -559884804752791638L;

    private String shipmentId;

    private String airbillNumber;

    private String trackDate;

    private String trackTime;

    private String eventCode;

    private String eventDescription;

    private String signatory;

    private String serviceAreaCode;

    private String serviceAreaDescription;

    private String shipperName;

    private String consigneeName;

    private String shipmentDate;

    private String weight;

    private String weightUnit;

    private String serviceType;

    private String destinationServiceArea;

    private String originServiceArea;

    private String trackStatus;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getTrackDate() {
        return trackDate;
    }

    public void setTrackDate(String trackDate) {
        this.trackDate = trackDate;
    }

    public String getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(String trackTime) {
        this.trackTime = trackTime;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getSignatory() {
        return signatory;
    }

    public void setSignatory(String signatory) {
        this.signatory = signatory;
    }

    public String getServiceAreaCode() {
        return serviceAreaCode;
    }

    public void setServiceAreaCode(String serviceAreaCode) {
        this.serviceAreaCode = serviceAreaCode;
    }

    public String getServiceAreaDescription() {
        return serviceAreaDescription;
    }

    public void setServiceAreaDescription(String serviceAreaDescription) {
        this.serviceAreaDescription = serviceAreaDescription;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDestinationServiceArea() {
        return destinationServiceArea;
    }

    public void setDestinationServiceArea(String destinationServiceArea) {
        this.destinationServiceArea = destinationServiceArea;
    }

    public String getOriginServiceArea() {
        return originServiceArea;
    }

    public void setOriginServiceArea(String originServiceArea) {
        this.originServiceArea = originServiceArea;
    }

    public String getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(String trackStatus) {
        this.trackStatus = trackStatus;
    }

    @Override
    public String toString() {
        return "TrackingModel [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", trackDate=" + trackDate + ", trackTime=" + trackTime + ", eventCode=" + eventCode + ", eventDescription=" + eventDescription + ", signatory=" + signatory + ", serviceAreaCode=" + serviceAreaCode + ", serviceAreaDescription=" + serviceAreaDescription + ", shipperName=" + shipperName + ", consigneeName=" + consigneeName + ", shipmentDate=" + shipmentDate + ", weight=" + weight + ", weightUnit="
                + weightUnit + ", serviceType=" + serviceType + ", destinationServiceArea=" + destinationServiceArea + ", originServiceArea=" + originServiceArea + ", trackStatus=" + trackStatus + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airbillNumber == null) ? 0 : airbillNumber.hashCode());
        result = prime * result + ((consigneeName == null) ? 0 : consigneeName.hashCode());
        result = prime * result + ((destinationServiceArea == null) ? 0 : destinationServiceArea.hashCode());
        result = prime * result + ((eventCode == null) ? 0 : eventCode.hashCode());
        result = prime * result + ((eventDescription == null) ? 0 : eventDescription.hashCode());
        result = prime * result + ((originServiceArea == null) ? 0 : originServiceArea.hashCode());
        result = prime * result + ((serviceAreaCode == null) ? 0 : serviceAreaCode.hashCode());
        result = prime * result + ((serviceAreaDescription == null) ? 0 : serviceAreaDescription.hashCode());
        result = prime * result + ((serviceType == null) ? 0 : serviceType.hashCode());
        result = prime * result + ((shipmentDate == null) ? 0 : shipmentDate.hashCode());
        result = prime * result + ((shipmentId == null) ? 0 : shipmentId.hashCode());
        result = prime * result + ((shipperName == null) ? 0 : shipperName.hashCode());
        result = prime * result + ((signatory == null) ? 0 : signatory.hashCode());
        result = prime * result + ((trackDate == null) ? 0 : trackDate.hashCode());
        result = prime * result + ((trackStatus == null) ? 0 : trackStatus.hashCode());
        result = prime * result + ((trackTime == null) ? 0 : trackTime.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((weightUnit == null) ? 0 : weightUnit.hashCode());
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
        TrackingModel other = (TrackingModel) obj;
        if (airbillNumber == null) {
            if (other.airbillNumber != null)
                return false;
        } else if (!airbillNumber.equals(other.airbillNumber))
            return false;
        if (consigneeName == null) {
            if (other.consigneeName != null)
                return false;
        } else if (!consigneeName.equals(other.consigneeName))
            return false;
        if (destinationServiceArea == null) {
            if (other.destinationServiceArea != null)
                return false;
        } else if (!destinationServiceArea.equals(other.destinationServiceArea))
            return false;
        if (eventCode == null) {
            if (other.eventCode != null)
                return false;
        } else if (!eventCode.equals(other.eventCode))
            return false;
        if (eventDescription == null) {
            if (other.eventDescription != null)
                return false;
        } else if (!eventDescription.equals(other.eventDescription))
            return false;
        if (originServiceArea == null) {
            if (other.originServiceArea != null)
                return false;
        } else if (!originServiceArea.equals(other.originServiceArea))
            return false;
        if (serviceAreaCode == null) {
            if (other.serviceAreaCode != null)
                return false;
        } else if (!serviceAreaCode.equals(other.serviceAreaCode))
            return false;
        if (serviceAreaDescription == null) {
            if (other.serviceAreaDescription != null)
                return false;
        } else if (!serviceAreaDescription.equals(other.serviceAreaDescription))
            return false;
        if (serviceType == null) {
            if (other.serviceType != null)
                return false;
        } else if (!serviceType.equals(other.serviceType))
            return false;
        if (shipmentDate == null) {
            if (other.shipmentDate != null)
                return false;
        } else if (!shipmentDate.equals(other.shipmentDate))
            return false;
        if (shipmentId == null) {
            if (other.shipmentId != null)
                return false;
        } else if (!shipmentId.equals(other.shipmentId))
            return false;
        if (shipperName == null) {
            if (other.shipperName != null)
                return false;
        } else if (!shipperName.equals(other.shipperName))
            return false;
        if (signatory == null) {
            if (other.signatory != null)
                return false;
        } else if (!signatory.equals(other.signatory))
            return false;
        if (trackDate == null) {
            if (other.trackDate != null)
                return false;
        } else if (!trackDate.equals(other.trackDate))
            return false;
        if (trackStatus == null) {
            if (other.trackStatus != null)
                return false;
        } else if (!trackStatus.equals(other.trackStatus))
            return false;
        if (trackTime == null) {
            if (other.trackTime != null)
                return false;
        } else if (!trackTime.equals(other.trackTime))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (weightUnit == null) {
            if (other.weightUnit != null)
                return false;
        } else if (!weightUnit.equals(other.weightUnit))
            return false;
        return true;
    }


}
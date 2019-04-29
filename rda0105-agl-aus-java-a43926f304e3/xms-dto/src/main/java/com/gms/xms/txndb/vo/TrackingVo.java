package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from TrackingVo
 * <p>
 * Author TanDT Date Apr 14, 2015
 */
public class TrackingVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -1953757889132868722L;

    private Long shipmentId;

    private String airbillNumber;

    private Date trackDate;

    private String trackTime;

    private String eventCode;

    private String eventDescription;

    private String signatory;

    private String serviceAreaCode;

    private String serviceAreaDescription;

    private String shipperName;

    private String consigneeName;

    private Date shipmentDate;

    private String weight;

    private String weightUnit;

    private String serviceType;

    private String destinationServiceArea;

    private String originServiceArea;

    private Byte trackStatus;

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber == null ? null : airbillNumber.trim();
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getTrackDate() {
        return trackDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setTrackDate(Date trackDate) {
        this.trackDate = trackDate;
    }

    public String getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(String trackTime) {
        this.trackTime = trackTime == null ? null : trackTime.trim();
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription == null ? null : eventDescription.trim();
    }

    public String getSignatory() {
        return signatory;
    }

    public void setSignatory(String signatory) {
        this.signatory = signatory == null ? null : signatory.trim();
    }

    public String getServiceAreaCode() {
        return serviceAreaCode;
    }

    public void setServiceAreaCode(String serviceAreaCode) {
        this.serviceAreaCode = serviceAreaCode == null ? null : serviceAreaCode.trim();
    }

    public String getServiceAreaDescription() {
        return serviceAreaDescription;
    }

    public void setServiceAreaDescription(String serviceAreaDescription) {
        this.serviceAreaDescription = serviceAreaDescription == null ? null : serviceAreaDescription.trim();
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName == null ? null : shipperName.trim();
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getShipmentDate() {
        return shipmentDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit == null ? null : weightUnit.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }

    public String getDestinationServiceArea() {
        return destinationServiceArea;
    }

    public void setDestinationServiceArea(String destinationServiceArea) {
        this.destinationServiceArea = destinationServiceArea == null ? null : destinationServiceArea.trim();
    }

    public String getOriginServiceArea() {
        return originServiceArea;
    }

    public void setOriginServiceArea(String originServiceArea) {
        this.originServiceArea = originServiceArea == null ? null : originServiceArea.trim();
    }

    public Byte getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(Byte trackStatus) {
        this.trackStatus = trackStatus;
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
        TrackingVo other = (TrackingVo) obj;
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

    @Override
    public String toString() {
        return "TrackingVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", trackDate=" + trackDate + ", trackTime=" + trackTime + ", eventCode=" + eventCode + ", eventDescription=" + eventDescription + ", signatory=" + signatory + ", serviceAreaCode=" + serviceAreaCode + ", serviceAreaDescription=" + serviceAreaDescription + ", shipperName=" + shipperName + ", consigneeName=" + consigneeName + ", shipmentDate=" + shipmentDate + ", weight=" + weight + ", weightUnit="
                + weightUnit + ", serviceType=" + serviceType + ", destinationServiceArea=" + destinationServiceArea + ", originServiceArea=" + originServiceArea + ", trackStatus=" + trackStatus + "]";
    }

}
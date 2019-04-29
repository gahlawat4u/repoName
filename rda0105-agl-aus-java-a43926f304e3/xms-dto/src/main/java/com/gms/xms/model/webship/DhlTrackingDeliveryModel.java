package com.gms.xms.model.webship;


import com.gms.xms.model.BaseModel;


/**
 * Posted from DhlTrackingDeliveryModel
 * <p>
 * Author TanDT Date Apr 14, 2015
 */
public class DhlTrackingDeliveryModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -475777796386143130L;
    private String messageTime;
    private String messageReference;
    private String siteId;
    private String status;
    private ShipmentModel shipment;
    private String languageCode;

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageReference() {
        return messageReference;
    }

    public void setMessageReference(String messageReference) {
        this.messageReference = messageReference;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ShipmentModel getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentModel shipment) {
        this.shipment = shipment;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public String toString() {
        return "DhlTrackingDeliveryModel [messageTime=" + messageTime + ", messageReference=" + messageReference + ", siteId=" + siteId + ", status=" + status + ", shipment=" + shipment + ", languageCode=" + languageCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((languageCode == null) ? 0 : languageCode.hashCode());
        result = prime * result + ((messageReference == null) ? 0 : messageReference.hashCode());
        result = prime * result + ((messageTime == null) ? 0 : messageTime.hashCode());
        result = prime * result + ((shipment == null) ? 0 : shipment.hashCode());
        result = prime * result + ((siteId == null) ? 0 : siteId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        DhlTrackingDeliveryModel other = (DhlTrackingDeliveryModel) obj;
        if (languageCode == null) {
            if (other.languageCode != null)
                return false;
        } else if (!languageCode.equals(other.languageCode))
            return false;
        if (messageReference == null) {
            if (other.messageReference != null)
                return false;
        } else if (!messageReference.equals(other.messageReference))
            return false;
        if (messageTime == null) {
            if (other.messageTime != null)
                return false;
        } else if (!messageTime.equals(other.messageTime))
            return false;
        if (shipment == null) {
            if (other.shipment != null)
                return false;
        } else if (!shipment.equals(other.shipment))
            return false;
        if (siteId == null) {
            if (other.siteId != null)
                return false;
        } else if (!siteId.equals(other.siteId))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }


}

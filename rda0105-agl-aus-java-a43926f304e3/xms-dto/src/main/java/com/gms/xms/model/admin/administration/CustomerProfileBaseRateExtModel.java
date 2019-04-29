package com.gms.xms.model.admin.administration;

import com.gms.xms.model.BaseModel;

/**
 * Posted from CustomerProfileBaseRateDetailModel
 * <p>
 * Author TANDT 29-10-2015
 */
public class CustomerProfileBaseRateExtModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private String customerProfileBaseRateId;
    private String customerBaseRateId;
    private String profileId;
    private String shipmentTypeId;
    private String rateType;
    private String weight;
    private String rate;
    private String rateP;
    private String zoneCheck;
    private String content;
    private String bound;
    private String baseRateDescription;
    private String carrierId;
    private String zone;

    public String getCustomerBaseRateId() {
        return customerBaseRateId;
    }

    public void setCustomerBaseRateId(String customerBaseRateId) {
        this.customerBaseRateId = customerBaseRateId;
    }

    public String getCustomerProfileBaseRateId() {
        return customerProfileBaseRateId;
    }

    public void setCustomerProfileBaseRateId(String customerProfileBaseRateId) {
        this.customerProfileBaseRateId = customerProfileBaseRateId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getShipmentTypeId() {
        return shipmentTypeId;
    }

    public void setShipmentTypeId(String shipmentTypeId) {
        this.shipmentTypeId = shipmentTypeId;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateP() {
        return rateP;
    }

    public void setRateP(String rateP) {
        this.rateP = rateP;
    }

    public String getZoneCheck() {
        return zoneCheck;
    }

    public void setZoneCheck(String zoneCheck) {
        this.zoneCheck = zoneCheck;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public String getBaseRateDescription() {
        return baseRateDescription;
    }

    public void setBaseRateDescription(String baseRateDescription) {
        this.baseRateDescription = baseRateDescription;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "CustomerProfileBaseRateExtModel [customerProfileBaseRateId=" + customerProfileBaseRateId + ", customerBaseRateId=" + customerBaseRateId + ", profileId=" + profileId + ", shipmentTypeId=" + shipmentTypeId + ", rateType=" + rateType + ", weight=" + weight + ", rate=" + rate + ", rateP=" + rateP + ", zoneCheck=" + zoneCheck + ", content=" + content + ", bound=" + bound + ", baseRateDescription=" + baseRateDescription + ", carrierId=" + carrierId + ", zone=" + zone + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((baseRateDescription == null) ? 0 : baseRateDescription.hashCode());
        result = prime * result + ((bound == null) ? 0 : bound.hashCode());
        result = prime * result + ((carrierId == null) ? 0 : carrierId.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((customerBaseRateId == null) ? 0 : customerBaseRateId.hashCode());
        result = prime * result + ((customerProfileBaseRateId == null) ? 0 : customerProfileBaseRateId.hashCode());
        result = prime * result + ((profileId == null) ? 0 : profileId.hashCode());
        result = prime * result + ((rate == null) ? 0 : rate.hashCode());
        result = prime * result + ((rateP == null) ? 0 : rateP.hashCode());
        result = prime * result + ((rateType == null) ? 0 : rateType.hashCode());
        result = prime * result + ((shipmentTypeId == null) ? 0 : shipmentTypeId.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((zone == null) ? 0 : zone.hashCode());
        result = prime * result + ((zoneCheck == null) ? 0 : zoneCheck.hashCode());
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
        CustomerProfileBaseRateExtModel other = (CustomerProfileBaseRateExtModel) obj;
        if (baseRateDescription == null) {
            if (other.baseRateDescription != null)
                return false;
        } else if (!baseRateDescription.equals(other.baseRateDescription))
            return false;
        if (bound == null) {
            if (other.bound != null)
                return false;
        } else if (!bound.equals(other.bound))
            return false;
        if (carrierId == null) {
            if (other.carrierId != null)
                return false;
        } else if (!carrierId.equals(other.carrierId))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (customerBaseRateId == null) {
            if (other.customerBaseRateId != null)
                return false;
        } else if (!customerBaseRateId.equals(other.customerBaseRateId))
            return false;
        if (customerProfileBaseRateId == null) {
            if (other.customerProfileBaseRateId != null)
                return false;
        } else if (!customerProfileBaseRateId.equals(other.customerProfileBaseRateId))
            return false;
        if (profileId == null) {
            if (other.profileId != null)
                return false;
        } else if (!profileId.equals(other.profileId))
            return false;
        if (rate == null) {
            if (other.rate != null)
                return false;
        } else if (!rate.equals(other.rate))
            return false;
        if (rateP == null) {
            if (other.rateP != null)
                return false;
        } else if (!rateP.equals(other.rateP))
            return false;
        if (rateType == null) {
            if (other.rateType != null)
                return false;
        } else if (!rateType.equals(other.rateType))
            return false;
        if (shipmentTypeId == null) {
            if (other.shipmentTypeId != null)
                return false;
        } else if (!shipmentTypeId.equals(other.shipmentTypeId))
            return false;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        if (zoneCheck == null) {
            if (other.zoneCheck != null)
                return false;
        } else if (!zoneCheck.equals(other.zoneCheck))
            return false;
        return true;
    }

}

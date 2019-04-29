package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from LocationCodeModel
 * <p>
 * Author HungNT Date Apr 25, 2015
 */
public class LocationCodeModel extends BaseModel {
    private static final long serialVersionUID = 1871385507658283206L;

    private String locationCodeId;
    private String locationCodeName;
    private String locationCodeDescription;
    private String localizationId;
    private String carrierId;

    public String getLocationCodeId() {
        return locationCodeId;
    }

    public void setLocationCodeId(String locationCodeId) {
        this.locationCodeId = locationCodeId;
    }

    public String getLocationCodeName() {
        return locationCodeName;
    }

    public void setLocationCodeName(String locationCodeName) {
        this.locationCodeName = locationCodeName;
    }

    public String getLocationCodeDescription() {
        return locationCodeDescription;
    }

    public void setLocationCodeDescription(String locationCodeDescription) {
        this.locationCodeDescription = locationCodeDescription;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    public String toString() {
        return "LocationCodeModel [locationCodeId=" + locationCodeId + ", locationCodeName=" + locationCodeName + ", locationCodeDescription=" + locationCodeDescription + ", localizationId=" + localizationId + ", carrierId=" + carrierId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrierId == null) ? 0 : carrierId.hashCode());
        result = prime * result + ((localizationId == null) ? 0 : localizationId.hashCode());
        result = prime * result + ((locationCodeDescription == null) ? 0 : locationCodeDescription.hashCode());
        result = prime * result + ((locationCodeId == null) ? 0 : locationCodeId.hashCode());
        result = prime * result + ((locationCodeName == null) ? 0 : locationCodeName.hashCode());
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
        LocationCodeModel other = (LocationCodeModel) obj;
        if (carrierId == null) {
            if (other.carrierId != null)
                return false;
        } else if (!carrierId.equals(other.carrierId))
            return false;
        if (localizationId == null) {
            if (other.localizationId != null)
                return false;
        } else if (!localizationId.equals(other.localizationId))
            return false;
        if (locationCodeDescription == null) {
            if (other.locationCodeDescription != null)
                return false;
        } else if (!locationCodeDescription.equals(other.locationCodeDescription))
            return false;
        if (locationCodeId == null) {
            if (other.locationCodeId != null)
                return false;
        } else if (!locationCodeId.equals(other.locationCodeId))
            return false;
        if (locationCodeName == null) {
            if (other.locationCodeName != null)
                return false;
        } else if (!locationCodeName.equals(other.locationCodeName))
            return false;
        return true;
    }
}
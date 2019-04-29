package com.gms.xms.txndb.vo;

public class LocationCodeVo extends BaseVo {
    private static final long serialVersionUID = 644745249098590535L;

    private Integer locationCodeId;

    private String locationCodeName;

    private String locationCodeDescription;

    private Long localizationId;

    private Integer carrierId;

    public Integer getLocationCodeId() {
        return locationCodeId;
    }

    public void setLocationCodeId(Integer locationCodeId) {
        this.locationCodeId = locationCodeId;
    }

    public String getLocationCodeName() {
        return locationCodeName;
    }

    public void setLocationCodeName(String locationCodeName) {
        this.locationCodeName = locationCodeName == null ? null : locationCodeName.trim();
    }

    public String getLocationCodeDescription() {
        return locationCodeDescription;
    }

    public void setLocationCodeDescription(String locationCodeDescription) {
        this.locationCodeDescription = locationCodeDescription == null ? null : locationCodeDescription.trim();
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
        this.localizationId = localizationId;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    public String toString() {
        return "LocationCodeVo [locationCodeId=" + locationCodeId + ", locationCodeName=" + locationCodeName + ", locationCodeDescription=" + locationCodeDescription + ", localizationId=" + localizationId + ", carrierId=" + carrierId + "]";
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
        LocationCodeVo other = (LocationCodeVo) obj;
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
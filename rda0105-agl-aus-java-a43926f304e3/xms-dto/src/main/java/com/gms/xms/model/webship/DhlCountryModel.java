package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from DhlCountryModel
 * <p>
 * Author TanDT Date Apr 20, 2015
 */
public class DhlCountryModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -6962118997792926977L;

    private String dhlCountryId;

    private String dhlApCode;

    private String dhlApZone;

    private String dhlCountryName;

    private String dhlRegion;

    private String dhlTimeZone;

    private Boolean isElevatedRisk;

    private Boolean isRestrictedDestination;

    public String getDhlCountryId() {
        return dhlCountryId;
    }

    public void setDhlCountryId(String dhlCountryId) {
        this.dhlCountryId = dhlCountryId;
    }

    public String getDhlApCode() {
        return dhlApCode;
    }

    public void setDhlApCode(String dhlApCode) {
        this.dhlApCode = dhlApCode;
    }

    public String getDhlApZone() {
        return dhlApZone;
    }

    public void setDhlApZone(String dhlApZone) {
        this.dhlApZone = dhlApZone;
    }

    public String getDhlCountryName() {
        return dhlCountryName;
    }

    public void setDhlCountryName(String dhlCountryName) {
        this.dhlCountryName = dhlCountryName;
    }

    public String getDhlRegion() {
        return dhlRegion;
    }

    public void setDhlRegion(String dhlRegion) {
        this.dhlRegion = dhlRegion;
    }

    public String getDhlTimeZone() {
        return dhlTimeZone;
    }

    public void setDhlTimeZone(String dhlTimeZone) {
        this.dhlTimeZone = dhlTimeZone;
    }

    public Boolean getElevatedRisk() {
        return isElevatedRisk;
    }

    public void setElevatedRisk(Boolean elevatedRisk) {
        isElevatedRisk = elevatedRisk;
    }

    public Boolean getRestrictedDestination() {
        return isRestrictedDestination;
    }

    public void setRestrictedDestination(Boolean restrictedDestination) {
        isRestrictedDestination = restrictedDestination;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dhlApCode == null) ? 0 : dhlApCode.hashCode());
        result = prime * result + ((dhlApZone == null) ? 0 : dhlApZone.hashCode());
        result = prime * result + ((dhlCountryId == null) ? 0 : dhlCountryId.hashCode());
        result = prime * result + ((dhlCountryName == null) ? 0 : dhlCountryName.hashCode());
        result = prime * result + ((dhlRegion == null) ? 0 : dhlRegion.hashCode());
        result = prime * result + ((dhlTimeZone == null) ? 0 : dhlTimeZone.hashCode());
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
        DhlCountryModel other = (DhlCountryModel) obj;
        if (dhlApCode == null) {
            if (other.dhlApCode != null)
                return false;
        } else if (!dhlApCode.equals(other.dhlApCode))
            return false;
        if (dhlApZone == null) {
            if (other.dhlApZone != null)
                return false;
        } else if (!dhlApZone.equals(other.dhlApZone))
            return false;
        if (dhlCountryId == null) {
            if (other.dhlCountryId != null)
                return false;
        } else if (!dhlCountryId.equals(other.dhlCountryId))
            return false;
        if (dhlCountryName == null) {
            if (other.dhlCountryName != null)
                return false;
        } else if (!dhlCountryName.equals(other.dhlCountryName))
            return false;
        if (dhlRegion == null) {
            if (other.dhlRegion != null)
                return false;
        } else if (!dhlRegion.equals(other.dhlRegion))
            return false;
        if (dhlTimeZone == null) {
            if (other.dhlTimeZone != null)
                return false;
        } else if (!dhlTimeZone.equals(other.dhlTimeZone))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DhlCountryModel [dhlCountryId=" + dhlCountryId + ", dhlApCode=" + dhlApCode + ", dhlApZone=" + dhlApZone + ", dhlCountryName=" + dhlCountryName + ", dhlRegion=" + dhlRegion + ", dhlTimeZone=" + dhlTimeZone + "]";
    }

}

package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;


/**
 * Posted from TerritoryModel
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class TerritoryModel extends BaseModel {
    /**
     *
     */
    private static final long serialVersionUID = -3265481811907141192L;

    private String territoryId;
    private String territoryName;

    public String getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(String territoryId) {
        this.territoryId = territoryId;
    }

    @Override
    public String toString() {
        return "TerritoryModel [territoryId=" + territoryId + ", territoryName=" + territoryName + "]";
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(String territoryName) {
        this.territoryName = territoryName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((territoryId == null) ? 0 : territoryId.hashCode());
        result = prime * result + ((territoryName == null) ? 0 : territoryName.hashCode());
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
        TerritoryModel other = (TerritoryModel) obj;
        if (territoryId == null) {
            if (other.territoryId != null)
                return false;
        } else if (!territoryId.equals(other.territoryId))
            return false;
        if (territoryName == null) {
            if (other.territoryName != null)
                return false;
        } else if (!territoryName.equals(other.territoryName))
            return false;
        return true;
    }

}
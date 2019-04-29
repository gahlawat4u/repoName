package com.gms.xms.txndb.vo.admin;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * @author TanDT
 */
public class TerritoryVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -3265481811907141192L;

    private Integer territoryId;
    private String territoryName;

    public Integer getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(Integer territoryId) {
        this.territoryId = territoryId;
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
        TerritoryVo other = (TerritoryVo) obj;
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

    @Override
    public String toString() {
        return "TerritoryVo [territoryId=" + territoryId + ", territoryName=" + territoryName + "]";
    }

}
package com.gms.xms.txndb.vo.dhl;

import com.gms.xms.txndb.vo.BaseVo;

public class DhlEsiZoneStationVo extends BaseVo {
    private static final long serialVersionUID = 2054420491969822314L;

    private Integer id;

    private String stationCode;

    private Integer bound;

    private Integer zone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode == null ? null : stationCode.trim();
    }

    public Integer getBound() {
        return bound;
    }

    public void setBound(Integer bound) {
        this.bound = bound;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "DhlEsiZoneStationVo [id=" + id + ", stationCode=" + stationCode + ", bound=" + bound + ", zone=" + zone + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bound == null) ? 0 : bound.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((stationCode == null) ? 0 : stationCode.hashCode());
        result = prime * result + ((zone == null) ? 0 : zone.hashCode());
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
        DhlEsiZoneStationVo other = (DhlEsiZoneStationVo) obj;
        if (bound == null) {
            if (other.bound != null)
                return false;
        } else if (!bound.equals(other.bound))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (stationCode == null) {
            if (other.stationCode != null)
                return false;
        } else if (!stationCode.equals(other.stationCode))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        return true;
    }
}
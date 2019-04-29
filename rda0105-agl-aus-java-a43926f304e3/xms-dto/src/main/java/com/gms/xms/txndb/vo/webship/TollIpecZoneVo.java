package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * @author tkvcl
 */
public class TollIpecZoneVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 8385933736114425614L;

    private String business;

    private String country;

    private String state;

    private String postcode;

    private String townName;

    private String zone;

    private String local;

    private Integer scNr;

    private String scName;

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getScNr() {
        return scNr;
    }

    public void setScNr(Integer scNr) {
        this.scNr = scNr;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    @Override
    public String toString() {
        return "TollIpecZone [business=" + business + ", country=" + country + ", state=" + state + ", postcode=" + postcode + ", townName=" + townName + ", zone=" + zone + ", local=" + local + ", scNr=" + scNr + ", scName=" + scName + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((business == null) ? 0 : business.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((local == null) ? 0 : local.hashCode());
        result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
        result = prime * result + ((scName == null) ? 0 : scName.hashCode());
        result = prime * result + ((scNr == null) ? 0 : scNr.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((townName == null) ? 0 : townName.hashCode());
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
        TollIpecZoneVo other = (TollIpecZoneVo) obj;
        if (business == null) {
            if (other.business != null)
                return false;
        } else if (!business.equals(other.business))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (local == null) {
            if (other.local != null)
                return false;
        } else if (!local.equals(other.local))
            return false;
        if (postcode == null) {
            if (other.postcode != null)
                return false;
        } else if (!postcode.equals(other.postcode))
            return false;
        if (scName == null) {
            if (other.scName != null)
                return false;
        } else if (!scName.equals(other.scName))
            return false;
        if (scNr == null) {
            if (other.scNr != null)
                return false;
        } else if (!scNr.equals(other.scNr))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (townName == null) {
            if (other.townName != null)
                return false;
        } else if (!townName.equals(other.townName))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        return true;
    }


}
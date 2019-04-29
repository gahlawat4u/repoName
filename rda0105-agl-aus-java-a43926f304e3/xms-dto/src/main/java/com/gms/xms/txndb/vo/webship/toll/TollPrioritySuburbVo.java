package com.gms.xms.txndb.vo.webship.toll;

import com.gms.xms.txndb.vo.BaseVo;

public class TollPrioritySuburbVo extends BaseVo {
    private static final long serialVersionUID = -7695994682409637707L;

    private String businessUnit;

    private String country;

    private String stateCode;

    private String postCode;

    private String suburbName;

    private String zone;

    private String local;

    private String collectionDepot;

    private String collectionDepotName;

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit == null ? null : businessUnit.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode == null ? null : stateCode.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getSuburbName() {
        return suburbName;
    }

    public void setSuburbName(String suburbName) {
        this.suburbName = suburbName == null ? null : suburbName.trim();
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone == null ? null : zone.trim();
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local == null ? null : local.trim();
    }

    public String getCollectionDepot() {
        return collectionDepot;
    }

    public void setCollectionDepot(String collectionDepot) {
        this.collectionDepot = collectionDepot == null ? null : collectionDepot.trim();
    }

    public String getCollectionDepotName() {
        return collectionDepotName;
    }

    public void setCollectionDepotName(String collectionDepotName) {
        this.collectionDepotName = collectionDepotName == null ? null : collectionDepotName.trim();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((businessUnit == null) ? 0 : businessUnit.hashCode());
        result = prime * result + ((collectionDepot == null) ? 0 : collectionDepot.hashCode());
        result = prime * result + ((collectionDepotName == null) ? 0 : collectionDepotName.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((local == null) ? 0 : local.hashCode());
        result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
        result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
        result = prime * result + ((suburbName == null) ? 0 : suburbName.hashCode());
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
        TollPrioritySuburbVo other = (TollPrioritySuburbVo) obj;
        if (businessUnit == null) {
            if (other.businessUnit != null)
                return false;
        } else if (!businessUnit.equals(other.businessUnit))
            return false;
        if (collectionDepot == null) {
            if (other.collectionDepot != null)
                return false;
        } else if (!collectionDepot.equals(other.collectionDepot))
            return false;
        if (collectionDepotName == null) {
            if (other.collectionDepotName != null)
                return false;
        } else if (!collectionDepotName.equals(other.collectionDepotName))
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
        if (postCode == null) {
            if (other.postCode != null)
                return false;
        } else if (!postCode.equals(other.postCode))
            return false;
        if (stateCode == null) {
            if (other.stateCode != null)
                return false;
        } else if (!stateCode.equals(other.stateCode))
            return false;
        if (suburbName == null) {
            if (other.suburbName != null)
                return false;
        } else if (!suburbName.equals(other.suburbName))
            return false;
        if (zone == null) {
            if (other.zone != null)
                return false;
        } else if (!zone.equals(other.zone))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TollPrioritySuburbVo [businessUnit=" + businessUnit + ", country=" + country + ", stateCode=" + stateCode + ", postCode=" + postCode + ", suburbName=" + suburbName + ", zone=" + zone + ", local=" + local + ", collectionDepot=" + collectionDepot + ", collectionDepotName=" + collectionDepotName + "]";
    }
}
package com.gms.xms.model.webship.toll;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Sep 1, 2016 4:24:38 PM
 * <p>
 * Author dattrinh
 */
public class TollPrioritySuburbModel extends BaseModel {

    private static final long serialVersionUID = 1;

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
        this.businessUnit = businessUnit;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSuburbName() {
        return suburbName;
    }

    public void setSuburbName(String suburbName) {
        this.suburbName = suburbName;
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

    public String getCollectionDepot() {
        return collectionDepot;
    }

    public void setCollectionDepot(String collectionDepot) {
        this.collectionDepot = collectionDepot;
    }

    public String getCollectionDepotName() {
        return collectionDepotName;
    }

    public void setCollectionDepotName(String collectionDepotName) {
        this.collectionDepotName = collectionDepotName;
    }

    @Override
    public String toString() {
        return "TollPrioritySuburbModel [businessUnit=" + businessUnit + ", country=" + country + ", stateCode=" + stateCode + ", postCode=" + postCode + ", suburbName=" + suburbName + ", zone=" + zone + ", local=" + local + ", collectionDepot=" + collectionDepot + ", collectionDepotName=" + collectionDepotName + "]";
    }
}
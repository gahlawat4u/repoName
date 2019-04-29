package com.gms.xms.model.webship.toll;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Sep 1, 2016 5:22:08 PM
 * <p>
 * Author dattrinh
 */
public class TollRemoteAreaModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String town;
    private Integer postCode;
    private String type;

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TollRemoteAreaModel [town=" + town + ", postCode=" + postCode + ", type=" + type + "]";
    }
}
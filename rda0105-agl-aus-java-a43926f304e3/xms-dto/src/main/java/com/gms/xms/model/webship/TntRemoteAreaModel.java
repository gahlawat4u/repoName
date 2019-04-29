package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Sep 1, 2016 3:34:10 PM
 * <p>
 * Author dattrinh
 */
public class TntRemoteAreaModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String postCode;
    private String town;
    private String state;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "TntRemoteAreaModel [postCode=" + postCode + ", town=" + town + ", state=" + state + "]";
    }
}
package com.gms.xms.model.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from WebshipGroupModel
 * <p>
 * Author DatTV Sep 1, 2015
 */
public class WebshipGroupModel extends BaseModel {

    private static final long serialVersionUID = 4914080566317109032L;

    private String webshipGroupId;
    private String webshipGroupName;
    private String ownerCustomerId;

    @Override
    public String toString() {
        return "WebshipGroupModel [webshipGroupId=" + webshipGroupId + ", webshipGroupName=" + webshipGroupName + ", ownerCustomerId=" + ownerCustomerId + "]";
    }

    public String getWebshipGroupId() {
        return webshipGroupId;
    }

    public void setWebshipGroupId(String webshipGroupId) {
        this.webshipGroupId = webshipGroupId;
    }

    public String getWebshipGroupName() {
        return webshipGroupName;
    }

    public void setWebshipGroupName(String webshipGroupName) {
        this.webshipGroupName = webshipGroupName;
    }

    public String getOwnerCustomerId() {
        return ownerCustomerId;
    }

    public void setOwnerCustomerId(String ownerCustomerId) {
        this.ownerCustomerId = ownerCustomerId;
    }
}
package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from WebshipGroupVo
 * <p>
 * Author DatTV Sep 1, 2015
 */
public class WebshipGroupVo extends BaseVo {

    private static final long serialVersionUID = -473987857675784242L;

    private Integer webshipGroupId;
    private String webshipGroupName;
    private Long ownerCustomerId;

    public Long getOwnerCustomerId() {
        return ownerCustomerId;
    }

    public void setOwnerCustomerId(Long ownerCustomerId) {
        this.ownerCustomerId = ownerCustomerId;
    }

    public Integer getWebshipGroupId() {
        return webshipGroupId;
    }

    public void setWebshipGroupId(Integer webshipGroupId) {
        this.webshipGroupId = webshipGroupId;
    }

    public String getWebshipGroupName() {
        return webshipGroupName;
    }

    public void setWebshipGroupName(String webshipGroupName) {
        this.webshipGroupName = webshipGroupName;
    }

    @Override
    public String toString() {
        return "WebshipGroupVo [webshipGroupId=" + webshipGroupId + ", webshipGroupName=" + webshipGroupName + ", ownerCustomerId=" + ownerCustomerId + "]";
    }
}
package com.gms.xms.txndb.vo.webship.toll;

import com.gms.xms.txndb.vo.BaseVo;

public class TollRemoteAreaVo extends BaseVo {
    private static final long serialVersionUID = -7067749175893762233L;

    private String town;

    private Integer postCode;

    private String type;

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town == null ? null : town.trim();
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
        this.type = type == null ? null : type.trim();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
        result = prime * result + ((town == null) ? 0 : town.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        TollRemoteAreaVo other = (TollRemoteAreaVo) obj;
        if (postCode == null) {
            if (other.postCode != null)
                return false;
        } else if (!postCode.equals(other.postCode))
            return false;
        if (town == null) {
            if (other.town != null)
                return false;
        } else if (!town.equals(other.town))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TollRemoteAreaVo [town=" + town + ", postCode=" + postCode + ", type=" + type + "]";
    }
}
package com.gms.xms.txndb.vo.webship;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from TntRemoteAreaVo
 * <p>
 * Author HungNT Date Aug 12, 2015
 */
public class TntRemoteAreaVo extends BaseVo {
    private static final long serialVersionUID = 7407281946304851249L;

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
        this.town = town == null ? null : town.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    @Override
    public String toString() {
        return "TntRemoteAreaVo [postCode=" + postCode + ", town=" + town + ", state=" + state + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((town == null) ? 0 : town.hashCode());
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
        TntRemoteAreaVo other = (TntRemoteAreaVo) obj;
        if (postCode == null) {
            if (other.postCode != null)
                return false;
        } else if (!postCode.equals(other.postCode))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (town == null) {
            if (other.town != null)
                return false;
        } else if (!town.equals(other.town))
            return false;
        return true;
    }
}
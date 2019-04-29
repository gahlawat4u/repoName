package com.gms.xms.txndb.vo.admin;

import com.gms.xms.txndb.vo.BaseVo;

public class FranchiseSettingVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = -3265481811907141192L;

    private Integer franchiseSettingId;

    private Long franchiseCode;

    private String franchiseSettingName;

    private String franchiseSettingValue;

    private Integer userLevel;

    private String description;

    public Integer getFranchiseSettingId() {
        return franchiseSettingId;
    }

    public void setFranchiseSettingId(Integer franchiseSettingId) {
        this.franchiseSettingId = franchiseSettingId;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getFranchiseSettingName() {
        return franchiseSettingName;
    }

    public void setFranchiseSettingName(String franchiseSettingName) {
        this.franchiseSettingName = franchiseSettingName == null ? null : franchiseSettingName.trim();
    }

    public String getFranchiseSettingValue() {
        return franchiseSettingValue;
    }

    public void setFranchiseSettingValue(String franchiseSettingValue) {
        this.franchiseSettingValue = franchiseSettingValue == null ? null : franchiseSettingValue.trim();
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "FranchiseSettingVo [franchiseSettingId=" + franchiseSettingId + ", franchiseCode=" + franchiseCode + ", franchiseSettingName=" + franchiseSettingName + ", franchiseSettingValue=" + franchiseSettingValue + ", userLevel=" + userLevel + ", description=" + description + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((franchiseCode == null) ? 0 : franchiseCode.hashCode());
        result = prime * result + ((franchiseSettingId == null) ? 0 : franchiseSettingId.hashCode());
        result = prime * result + ((franchiseSettingName == null) ? 0 : franchiseSettingName.hashCode());
        result = prime * result + ((franchiseSettingValue == null) ? 0 : franchiseSettingValue.hashCode());
        result = prime * result + ((userLevel == null) ? 0 : userLevel.hashCode());
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
        FranchiseSettingVo other = (FranchiseSettingVo) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (franchiseCode == null) {
            if (other.franchiseCode != null)
                return false;
        } else if (!franchiseCode.equals(other.franchiseCode))
            return false;
        if (franchiseSettingId == null) {
            if (other.franchiseSettingId != null)
                return false;
        } else if (!franchiseSettingId.equals(other.franchiseSettingId))
            return false;
        if (franchiseSettingName == null) {
            if (other.franchiseSettingName != null)
                return false;
        } else if (!franchiseSettingName.equals(other.franchiseSettingName))
            return false;
        if (franchiseSettingValue == null) {
            if (other.franchiseSettingValue != null)
                return false;
        } else if (!franchiseSettingValue.equals(other.franchiseSettingValue))
            return false;
        if (userLevel == null) {
            if (other.userLevel != null)
                return false;
        } else if (!userLevel.equals(other.userLevel))
            return false;
        return true;
    }

}
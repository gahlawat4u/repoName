package com.gms.xms.filter.admin;

import com.gms.xms.filter.BaseFilter;

public class FranchiseSettingListExtFilter extends BaseFilter {


    private Integer userLevelCode;
    private Integer franchiseCode;

    public Integer getUserLevelCode() {
        return userLevelCode;
    }

    public void setUserLevelCode(Integer userLevelCode) {
        this.userLevelCode = userLevelCode;
    }

    public Integer getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Integer franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    @Override
    public String toString() {
        return "FranchiseSettingListExtVo [userLevelCode=" + userLevelCode + ", franchiseCode=" + franchiseCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((franchiseCode == null) ? 0 : franchiseCode.hashCode());
        result = prime * result + ((userLevelCode == null) ? 0 : userLevelCode.hashCode());
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
        FranchiseSettingListExtFilter other = (FranchiseSettingListExtFilter) obj;
        if (franchiseCode == null) {
            if (other.franchiseCode != null)
                return false;
        } else if (!franchiseCode.equals(other.franchiseCode))
            return false;
        if (userLevelCode == null) {
            if (other.userLevelCode != null)
                return false;
        } else if (!userLevelCode.equals(other.userLevelCode))
            return false;
        return true;
    }


}
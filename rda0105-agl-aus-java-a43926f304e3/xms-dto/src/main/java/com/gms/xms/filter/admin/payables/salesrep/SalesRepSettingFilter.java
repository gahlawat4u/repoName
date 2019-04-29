package com.gms.xms.filter.admin.payables.salesrep;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from SalesRepSettingFilter
 * <p>
 * Author dattrinh Mar 21, 2016 1:36:43 PM
 */
public class SalesRepSettingFilter extends BaseFilter {
    private String franchiseList;
    private Long userId;
    private Integer userLevel;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }
}

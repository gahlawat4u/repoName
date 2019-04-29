package com.gms.xms.filter.account.users.manage;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from UserFilter
 * <p>
 * Author DatTV Oct 20, 2015
 */
public class UserFilter extends BaseFilter {
    private String userCode;
    private Integer userLevelId;
    private String franchiseList;
    private Long userId;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

package com.gms.xms.filter.admin;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from Apr 26, 2016 3:53:17 PM
 * <p>
 * Author huynd
 */
public class AdminQuickSearchFilter extends BaseFilter {

    private Byte searchType;
    private String searchValue;
    private String franchiseList;
    private String prospectList;
    private Long userId;
    private Integer userLevel;

    public Byte getSearchType() {
        return searchType;
    }

    public void setSearchType(Byte searchType) {
        this.searchType = searchType;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public String getProspectList() {
        return prospectList;
    }

    public void setProspectList(String prospectList) {
        this.prospectList = prospectList;
    }

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
}

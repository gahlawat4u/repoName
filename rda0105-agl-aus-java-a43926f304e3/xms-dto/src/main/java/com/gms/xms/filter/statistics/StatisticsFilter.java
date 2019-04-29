package com.gms.xms.filter.statistics;

/**
 * Posted from Aug 19, 2016 2:36:30 PM
 * <p>
 * Author dattrinh
 */
public class StatisticsFilter {
    private String franchiseList;
    private Long userId;
    private Integer userLevel;
    private Integer periodType;
    private String userList;
    private String prospectList;
    private Integer status;
    private Integer month;

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

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }

    public String getProspectList() {
        return prospectList;
    }

    public void setProspectList(String prospectList) {
        this.prospectList = prospectList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}

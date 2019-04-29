package com.gms.xms.txndb.vo.account.customers;

/**
 * Posted from BasicCustomerFilter
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class BasicCustomerFilter {

    private String franchiseCode;
    private String searchText;
    private Integer userLevel;
    private Long userId;

    @Override
    public String toString() {
        return "BasicCustomerFilter [franchiseCode=" + franchiseCode + ", searchText=" + searchText + "]";
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

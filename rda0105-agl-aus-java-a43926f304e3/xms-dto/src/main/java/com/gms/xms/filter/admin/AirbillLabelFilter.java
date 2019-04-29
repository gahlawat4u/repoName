package com.gms.xms.filter.admin;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from Aug 27, 2016 6:11:31 PM
 * <p>
 * Author dattrinh
 */
public class AirbillLabelFilter extends BaseFilter {
    private Integer periodType;
    private String searchDate;
    private String franchiseList;

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }
}

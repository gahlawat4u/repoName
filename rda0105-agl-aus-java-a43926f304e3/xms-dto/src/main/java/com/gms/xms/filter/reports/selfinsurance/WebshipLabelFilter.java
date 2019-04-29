package com.gms.xms.filter.reports.selfinsurance;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from WebshipLabelFilter
 * <p>
 * Author dattrinh Mar 18, 2016 3:27:37 PM
 */
public class WebshipLabelFilter extends BaseFilter {
    private String franchiseList;
    private Integer searchType;
    private Date startDate;
    private Date endDate;

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }
}

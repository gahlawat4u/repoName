package com.gms.xms.filter.invoicing;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from CostDeviationFilter
 * <p>
 * Author dattrinh Mar 14, 2016 10:47:10 AM
 */
public class CostDeviationFilter extends BaseFilter {
    private String franchiseList;
    private Date startDate;
    private Date endDate;

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
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
}

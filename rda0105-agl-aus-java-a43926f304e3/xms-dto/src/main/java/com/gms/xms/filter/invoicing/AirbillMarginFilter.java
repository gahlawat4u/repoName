package com.gms.xms.filter.invoicing;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from AirbillMarginFilter
 * <p>
 * Author dattrinh Mar 15, 2016 3:22:03 PM
 */
public class AirbillMarginFilter extends BaseFilter {
    private String franchiseList;
    private Date startDate;
    private Date endDate;
    private Double minMargin;

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

    public Double getMinMargin() {
        return minMargin;
    }

    public void setMinMargin(Double minMargin) {
        this.minMargin = minMargin;
    }
}

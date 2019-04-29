package com.gms.xms.filter.admin.payables.salesrep;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from SalesRepReportFilter
 * <p>
 * Author dattrinh Mar 23, 2016 4:36:37 PM
 */
public class SalesRepReportFilter extends BaseFilter {
    private String franchiseList;
    private Long userId;
    private Date startDate;
    private Date endDate;

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

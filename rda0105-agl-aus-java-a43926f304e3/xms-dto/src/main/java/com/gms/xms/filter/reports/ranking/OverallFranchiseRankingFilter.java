package com.gms.xms.filter.reports.ranking;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from OverallFranchiseRankingFilter.java
 * <p>
 * Author dattrinh 3:36:21 PM
 */
public class OverallFranchiseRankingFilter extends BaseFilter {
    private String rptTxnId;
    private Date startDate;
    private Date endDate;

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
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

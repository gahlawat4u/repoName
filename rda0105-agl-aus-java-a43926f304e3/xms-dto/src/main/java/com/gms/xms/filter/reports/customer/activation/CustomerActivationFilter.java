package com.gms.xms.filter.reports.customer.activation;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from CustomerActivationFilter.java
 * <p>
 * Author dattrinh 10:59:02 AM
 */
public class CustomerActivationFilter extends BaseFilter {
    private String rptTxnId;
    private Date startDate;
    private Date endDate;
    private String franchiseList;

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

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }
}

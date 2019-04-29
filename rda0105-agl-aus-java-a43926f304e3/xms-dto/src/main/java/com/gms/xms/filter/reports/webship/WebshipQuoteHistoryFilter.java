package com.gms.xms.filter.reports.webship;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from WebshipQuoteHistoryFilter.java
 * <p>
 * Author dattrinh 10:27:28 AM
 */
public class WebshipQuoteHistoryFilter extends BaseFilter {
    private Date startDate;
    private Date endDate;
    private String franchiseList;
    private String customerCode;

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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }
}

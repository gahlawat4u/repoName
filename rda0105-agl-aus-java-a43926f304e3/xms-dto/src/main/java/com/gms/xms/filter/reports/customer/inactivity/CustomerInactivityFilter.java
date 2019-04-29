package com.gms.xms.filter.reports.customer.inactivity;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from CustomerInactivityFilter.java
 * <p>
 * Author dattrinh 4:25:50 PM
 */
public class CustomerInactivityFilter extends BaseFilter {
    private String franchiseList;
    private Integer showCustomerOption;
    private Date startDate;
    private Date endDate;

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public Integer getShowCustomerOption() {
        return showCustomerOption;
    }

    public void setShowCustomerOption(Integer showCustomerOption) {
        this.showCustomerOption = showCustomerOption;
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

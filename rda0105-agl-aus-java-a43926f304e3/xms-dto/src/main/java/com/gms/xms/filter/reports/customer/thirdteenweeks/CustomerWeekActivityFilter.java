package com.gms.xms.filter.reports.customer.thirdteenweeks;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from WeekActivityFilter.java
 * <p>
 * Author dattrinh 4:14:16 PM
 */
public class CustomerWeekActivityFilter extends BaseFilter {
    private String franchiseList;

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }
}

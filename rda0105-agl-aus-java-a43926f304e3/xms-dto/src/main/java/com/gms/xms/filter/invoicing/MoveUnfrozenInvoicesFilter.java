package com.gms.xms.filter.invoicing;

import java.util.Date;

/**
 * Posted from MoveUnfrozenInvoicesFilter
 * <p>
 * Author dattrinh Mar 10, 2016 3:50:46 PM
 */
public class MoveUnfrozenInvoicesFilter {
    private String franchiseList;
    private Date fromDate;
    private Date toDate;
    private Boolean exclude;
    private String customerList;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Boolean getExclude() {
        return exclude;
    }

    public void setExclude(Boolean exclude) {
        this.exclude = exclude;
    }

    public String getCustomerList() {
        return customerList;
    }

    public void setCustomerList(String customerList) {
        this.customerList = customerList;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    @Override
    public String toString() {
        return "MoveUnfrozenInvoicesFilter [franchiseList=" + franchiseList + ", fromDate=" + fromDate + ", toDate=" + toDate + ", exclude=" + exclude + ", customerList=" + customerList + "]";
    }
}
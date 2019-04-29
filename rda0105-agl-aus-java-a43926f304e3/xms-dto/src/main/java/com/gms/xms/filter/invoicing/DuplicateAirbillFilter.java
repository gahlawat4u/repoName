package com.gms.xms.filter.invoicing;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * File Name: DuplicateAirbillFilter.java <br/>
 * Author: TANDT <br/>
 * Create Date: 02-12-2015 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.filter.invoicing <br/>
 * Class: DuplicateAirbillFilter
 */
public class DuplicateAirbillFilter extends BaseFilter {
    private Date toDate;
    private Date fromDate;
    private String franchiseList;

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }
}

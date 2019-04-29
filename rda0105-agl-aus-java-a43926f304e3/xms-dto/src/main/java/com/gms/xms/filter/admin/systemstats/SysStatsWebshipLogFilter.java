package com.gms.xms.filter.admin.systemstats;

import java.util.Date;

/**
 * Posted from Aug 30, 2016 10:49:06 AM
 * <p>
 * Author dattrinh
 */
public class SysStatsWebshipLogFilter extends SysStatsFilter {
    private String airbillNumber;
    private String action;
    private Date startDate;
    private Date endDate;
    private String filterCollections;

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getFilterCollections() {
        return filterCollections;
    }

    public void setFilterCollections(String filterCollections) {
        this.filterCollections = filterCollections;
    }
}

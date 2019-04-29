package com.gms.xms.filter.admin.systemstats;

import java.util.Date;

/**
 * Posted from Aug 30, 2016 10:49:06 AM
 * <p>
 * Author dattrinh
 */
public class SysStatsAdjustmentLogFilter extends SysStatsFilter {
    private String airbillNumber;
    private String requestType;
    private Integer status;
    private Date startDate;
    private Date endDate;

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

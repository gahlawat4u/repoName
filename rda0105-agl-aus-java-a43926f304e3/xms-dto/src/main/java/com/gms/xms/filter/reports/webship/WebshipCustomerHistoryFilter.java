package com.gms.xms.filter.reports.webship;

import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.BaseFilter;

import java.util.Date;
import java.util.List;

/**
 * Posted from WebshipCustomerHistoryFilter.java
 * <p>
 * Author dattrinh 10:28:14 AM
 */
public class WebshipCustomerHistoryFilter extends BaseFilter {
    private String rptTxnId;
    private Integer serviceId;
    private Integer carrierId;
    private String franchiseList;
    private Integer periodType;
    private Date startDate;
    private Date endDate;

    public List<Date> getPeriodList() throws Exception {
        if (startDate == null || endDate == null) {
            throw new Exception("Invalid date range");
        }
        if (periodType == 1) {
            return DateUtils.createDailyPeriod(startDate, endDate);
        } else if (periodType == 2) {
            return DateUtils.createWeeklyPeriod(startDate, endDate);
        } else {
            throw new Exception("Unknown period type");
        }
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
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


    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }
}

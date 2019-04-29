package com.gms.xms.filter.reports.customer.status;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;
import java.util.List;

/**
 * Posted from CustomerStatusFilter
 * <p>
 * Author DatTV Nov 5, 2015
 */
public class CustomerStatusFilter extends BaseFilter {
    private String rptTxnId;
    private String franchiseList;
    private Date reportDate;
    private List<Integer> serviceList;

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }


    public List<Integer> getServiceList() {
        return serviceList;
    }


    public void setServiceList(List<Integer> serviceList) {
        this.serviceList = serviceList;
    }
}

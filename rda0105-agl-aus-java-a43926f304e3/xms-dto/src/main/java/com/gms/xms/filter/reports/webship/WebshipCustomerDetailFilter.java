package com.gms.xms.filter.reports.webship;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from WebshipCustomerDetailFilter
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailFilter extends BaseFilter {
    private String franchiseList;
    private String customerCode;
    private Integer serviceId;
    private Date startDate;
    private Date endDate;

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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

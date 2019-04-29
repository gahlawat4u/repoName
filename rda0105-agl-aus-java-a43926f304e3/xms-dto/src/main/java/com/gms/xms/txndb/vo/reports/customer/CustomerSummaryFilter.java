package com.gms.xms.txndb.vo.reports.customer;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;
import java.util.List;

/**
 * Posted from CustomerSummaryFilter
 * <p>
 * Author DatTV Sep 15, 2015
 */
public class CustomerSummaryFilter extends BaseFilter {
    private String rptTxnId;
    private String franchiseList;
    private String serviceList;
    private Long saleRepId;
    private Date startDate;
    private Date endDate;
    private Boolean excludeGst;
    private Boolean excludeDuty;
    private List<Integer> carriers;
    private Long userId;
    private Integer userLevel;

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

    public String getServiceList() {
        return serviceList;
    }

    public void setServiceList(String serviceList) {
        this.serviceList = serviceList;
    }

    public Long getSaleRepId() {
        return saleRepId;
    }

    public void setSaleRepId(Long saleRepId) {
        this.saleRepId = saleRepId;
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

    public Boolean getExcludeGst() {
        return excludeGst;
    }

    public void setExcludeGst(Boolean excludeGst) {
        this.excludeGst = excludeGst;
    }

    public Boolean getExcludeDuty() {
        return excludeDuty;
    }

    public void setExcludeDuty(Boolean excludeDuty) {
        this.excludeDuty = excludeDuty;
    }

    public List<Integer> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Integer> carriers) {
        this.carriers = carriers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}

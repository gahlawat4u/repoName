package com.gms.xms.filter.reports.selfinsurance;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from InvoicedAirbillFilter
 * <p>
 * Author dattrinh Mar 19, 2016 4:09:52 PM
 */
public class InvoicedAirbillFilter extends BaseFilter {
    private String rptTxnId;
    private String franchiseList;
    private Date startDate;
    private Date endDate;
    private Integer searchType;
    private String currencySymbol;

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
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

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }
}

package com.gms.xms.txndb.vo.receivables.customeraging;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from CustomerAgingFilter
 * <p>
 * Author DatTV Date Aug 10, 2015 11:06:02 AM
 */
public class CustomerAgingFilter extends BaseFilter {
    private String rptTxnId;
    private Long collectorId;
    private Long salesRepId;
    private String franchiseCodeList;
    private Boolean withBalanceOnly;
    private Integer minAvgDaysToPay;
    private Integer maxAvgDaysToPay;
    private Integer minInvoiceAge;
    private Integer maxInvoiceAge;
    private Integer minDaysOverdue;
    private Integer maxDaysOverdue;
    private Date endDate;
    private Integer agingType;
    private String filterDateInvoiceAgeRange;

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    public Long getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Long collectorId) {
        this.collectorId = collectorId;
    }

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getFranchiseCodeList() {
        return franchiseCodeList;
    }

    public void setFranchiseCodeList(String franchiseCodeList) {
        this.franchiseCodeList = franchiseCodeList;
    }

    public Boolean getWithBalanceOnly() {
        return withBalanceOnly;
    }

    public void setWithBalanceOnly(Boolean withBalanceOnly) {
        this.withBalanceOnly = withBalanceOnly;
    }

    public Integer getMinAvgDaysToPay() {
        return minAvgDaysToPay;
    }

    public void setMinAvgDaysToPay(Integer minAvgDaysToPay) {
        this.minAvgDaysToPay = minAvgDaysToPay;
    }

    public Integer getMaxAvgDaysToPay() {
        return maxAvgDaysToPay;
    }

    public void setMaxAvgDaysToPay(Integer maxAvgDaysToPay) {
        this.maxAvgDaysToPay = maxAvgDaysToPay;
    }

    public Integer getMinInvoiceAge() {
        return minInvoiceAge;
    }

    public void setMinInvoiceAge(Integer minInvoiceAge) {
        this.minInvoiceAge = minInvoiceAge;
    }

    public Integer getMaxInvoiceAge() {
        return maxInvoiceAge;
    }

    public void setMaxInvoiceAge(Integer maxInvoiceAge) {
        this.maxInvoiceAge = maxInvoiceAge;
    }

    public Integer getMinDaysOverdue() {
        return minDaysOverdue;
    }

    public void setMinDaysOverdue(Integer minDaysOverdue) {
        this.minDaysOverdue = minDaysOverdue;
    }

    public Integer getMaxDaysOverdue() {
        return maxDaysOverdue;
    }

    public void setMaxDaysOverdue(Integer maxDaysOverdue) {
        this.maxDaysOverdue = maxDaysOverdue;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAgingType() {
        return agingType;
    }

    public void setAgingType(Integer agingType) {
        this.agingType = agingType;
    }

    public String getFilterDateInvoiceAgeRange() {
        return filterDateInvoiceAgeRange;
    }

    public void setFilterDateInvoiceAgeRange(String filterDateInvoiceAgeRange) {
        this.filterDateInvoiceAgeRange = filterDateInvoiceAgeRange;
    }
}

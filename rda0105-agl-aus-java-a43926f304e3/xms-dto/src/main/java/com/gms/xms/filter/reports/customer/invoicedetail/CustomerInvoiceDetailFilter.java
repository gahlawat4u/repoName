package com.gms.xms.filter.reports.customer.invoicedetail;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from CustomerInvoiceDetailFilter.java
 * <p>
 * Author dattrinh 2:34:05 PM
 */
public class CustomerInvoiceDetailFilter extends BaseFilter {
    private String rptTxnId;
    private String franchiseList;
    private Date startDate;
    private Date endDate;

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

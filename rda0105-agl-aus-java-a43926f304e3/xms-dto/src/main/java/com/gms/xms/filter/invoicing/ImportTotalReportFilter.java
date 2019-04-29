package com.gms.xms.filter.invoicing;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from ImportTotalReportFilter
 * <p>
 * Author dattrinh Mar 9, 2016 4:46:40 PM
 */
public class ImportTotalReportFilter extends BaseFilter {
    private String franchiseList;
    private Long carrier;
    private Date startDate;
    private Date endDate;

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public Long getCarrier() {
        return carrier;
    }

    public void setCarrier(Long carrier) {
        this.carrier = carrier;
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

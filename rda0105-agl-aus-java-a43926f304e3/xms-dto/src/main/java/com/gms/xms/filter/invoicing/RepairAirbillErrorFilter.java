package com.gms.xms.filter.invoicing;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from RepairAirbillErrorFilter
 * <p>
 * Author dattrinh Mar 7, 2016 4:13:01 PM
 */
public class RepairAirbillErrorFilter extends BaseFilter {
    private Long carrier;
    private Date toDate;
    private Date fromDate;

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Long getCarrier() {
        return carrier;
    }

    public void setCarrier(Long carrier) {
        this.carrier = carrier;
    }
}

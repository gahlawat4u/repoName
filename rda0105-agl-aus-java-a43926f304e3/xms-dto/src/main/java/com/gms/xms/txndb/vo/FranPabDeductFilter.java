package com.gms.xms.txndb.vo;

import java.util.Date;

/**
 * Posted from FranPabDeductFilter
 * <p>
 * Author DatTV Date Apr 22, 2015 4:22:28 PM
 */
public class FranPabDeductFilter extends FranPabDeductVo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Date startDate;

    private Date endDate;

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
package com.gms.xms.dto;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * Posted from Sep 19, 2016 1:51:42 PM
 * <p>
 * Author dattrinh
 */
public class MonthlyVo extends BaseVo {

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

    @Override
    public String toString() {
        return "MonthlyVo [startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}

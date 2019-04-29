package com.gms.xms.dto;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * Posted from Sep 28, 2016 9:27:28 AM
 * <p>
 * Author dattrinh
 */
public class FranInvoiceNumberingVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer month;
    private Integer year;
    private String franchiseCode;
    private Date startDate;
    private Date endDate;
    private Integer counter;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
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

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "FranInvoiceNumberingVo [month=" + month + ", year=" + year + ", franchiseCode=" + franchiseCode + ", startDate=" + startDate + ", endDate=" + endDate + ", counter=" + counter + "]";
    }
}

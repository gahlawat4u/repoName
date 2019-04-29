package com.gms.xms.txndb.vo;

/**
 * Posted from BankFilter
 * <p>
 * Author DatTV Date Apr 9, 2015 11:40:51 AM
 */
public class BankFilter extends BankVo {
    private static final long serialVersionUID = 1L;
    private int startRecord;
    private int recordSize;
    private String orderBy;

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
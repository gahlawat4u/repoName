package com.gms.xms.txndb.vo.admin;

/**
 * Posted from RateSheetsPageFilter
 *
 * @author HungNT - @since Oct 1, 2015
 */
public class RateSheetsPageFilter extends RateSheetsPageVo {
    private static final long serialVersionUID = 4610091994360417644L;

    private Integer recordSize;
    private Integer startRecord;

    public Integer getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(Integer recordSize) {
        this.recordSize = recordSize;
    }

    public Integer getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(Integer startRecord) {
        this.startRecord = startRecord;
    }

    @Override
    public String toString() {
        return "RateSheetsPageFilter [recordSize=" + recordSize + ", startRecord=" + startRecord + "]";
    }
}

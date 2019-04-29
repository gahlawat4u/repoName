package com.gms.xms.txndb.vo.webship;

/**
 * Posted from DimensionFilter
 * <p>
 * Author DatTV Date Jul 14, 2015 2:40:26 PM
 */
public class DimensionFilter extends DimensionVo {

    private static final long serialVersionUID = -8209272720317720145L;

    private Integer startRecord;
    private Integer recordSize;
    private String orderBy;

    public Integer getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(Integer startRecord) {
        this.startRecord = startRecord;
    }

    public Integer getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(Integer recordSize) {
        this.recordSize = recordSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "DimensionFilter [startRecord=" + startRecord + ", recordSize=" + recordSize + ", orderBy=" + orderBy + "]";
    }
}
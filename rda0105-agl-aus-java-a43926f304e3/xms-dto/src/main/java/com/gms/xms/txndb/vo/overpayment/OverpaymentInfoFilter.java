package com.gms.xms.txndb.vo.overpayment;

import java.util.List;

/**
 * Posted from OverpaymentInfoFilter
 * <p>
 * Author DatTV Date Apr 23, 2015 11:26:26 AM
 */
public class OverpaymentInfoFilter extends OverpaymentInfoVo {

    private static final long serialVersionUID = 1L;
    private List<String> franchiseList;
    private int startRecord;
    private int recordSize;
    private String orderBy;

    public List<String> getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(List<String> franchiseList) {
        this.franchiseList = franchiseList;
    }

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
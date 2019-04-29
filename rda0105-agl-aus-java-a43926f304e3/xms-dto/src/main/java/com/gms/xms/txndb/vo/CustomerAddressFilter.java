package com.gms.xms.txndb.vo;

/**
 * Posted from CustomerAddressFilter
 * <p>
 * Author DatTV Date Apr 7, 2015 4:40:51 PM
 */
public class CustomerAddressFilter extends CustomerAddressVo {
    private static final long serialVersionUID = 1L;
    private int startRecord;
    private int recordSize;
    private String orderBy;
    private String password;
    private String franchiseList;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }
}
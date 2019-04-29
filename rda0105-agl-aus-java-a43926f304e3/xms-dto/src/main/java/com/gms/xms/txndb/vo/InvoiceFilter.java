package com.gms.xms.txndb.vo;

import java.util.List;

/**
 * Posted from InvoiceFilter
 * <p>
 * Author DatTV Date Mar 30, 2015
 */
public class InvoiceFilter extends InvoiceVo {

    private static final long serialVersionUID = 1533832959700910488L;
    private int startRecord;
    private int recordSize;
    private String orderBy;
    private List<String> franchiseCodes;
    private String franchiseCode;
    private Integer emailInvoice;

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

    public List<String> getFranchiseCodes() {
        return franchiseCodes;
    }

    public void setFranchiseCodes(List<String> franchiseCodes) {
        this.franchiseCodes = franchiseCodes;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public Integer getEmailInvoice() {
        return emailInvoice;
    }

    public void setEmailInvoice(Integer emailInvoice) {
        this.emailInvoice = emailInvoice;
    }

    @Override
    public String toString() {
        return "InvoiceFilter [startRecord=" + startRecord + ", recordSize=" + recordSize + ", orderBy=" + orderBy + ", franchiseCodes=" + franchiseCodes + ", franchiseCode=" + franchiseCode + ", emailInvoice=" + emailInvoice + "]";
    }
}
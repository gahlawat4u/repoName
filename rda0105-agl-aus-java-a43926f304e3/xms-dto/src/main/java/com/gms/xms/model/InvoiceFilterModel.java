package com.gms.xms.model;

import java.util.List;

/**
 * Posted from InvoiceFilterModel
 * <p>
 * Author TANDT Date Mar 30, 2015
 */
public class InvoiceFilterModel extends InvoiceModel {

    /**
     *
     */
    private static final long serialVersionUID = 8002903938489267922L;
    private String startRecord;
    private String recordSize;
    private String orderBy;
    private List<String> franchiseList;
    private String fromDate;
    private String toDate;
    private String franchiseSearchTypeValue;
    private String franchiseSearchType;
    private String minAirbills;
    private String maxAirbills;
    private String nonEmailInvoice;

    public String getNonEmailInvoice() {
        return nonEmailInvoice;
    }

    public void setNonEmailInvoice(String nonEmailInvoice) {
        this.nonEmailInvoice = nonEmailInvoice;
    }

    public String getMinAirbills() {
        return minAirbills;
    }

    public void setMinAirbills(String minAirbills) {
        this.minAirbills = minAirbills;
    }

    public String getMaxAirbills() {
        return maxAirbills;
    }

    public void setMaxAirbills(String maxAirbills) {
        this.maxAirbills = maxAirbills;
    }

    public String getFranchiseSearchTypeValue() {
        return franchiseSearchTypeValue;
    }

    public void setFranchiseSearchTypeValue(String franchiseSearchTypeValue) {
        this.franchiseSearchTypeValue = franchiseSearchTypeValue;
    }

    public String getFranchiseSearchType() {
        return franchiseSearchType;
    }

    public void setFranchiseSearchType(String franchiseSearchType) {
        this.franchiseSearchType = franchiseSearchType;
    }

    public String getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(String startRecord) {
        this.startRecord = startRecord;
    }

    public String getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(String recordSize) {
        this.recordSize = recordSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<String> getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(List<String> franchiseList) {
        this.franchiseList = franchiseList;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "InvoiceFilterModel [startRecord=" + startRecord + ", recordSize=" + recordSize + ", orderBy=" + orderBy + ", franchiseList=" + franchiseList + ", fromDate=" + fromDate + ", toDate=" + toDate + ", franchiseSearchTypeValue=" + franchiseSearchTypeValue + ", franchiseSearchType=" + franchiseSearchType + ", minAirbills=" + minAirbills + ", maxAirbills=" + maxAirbills + ", nonEmailInvoice=" + nonEmailInvoice + "]";
    }

}
package com.gms.xms.filter.admin.receivables.payments;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * Posted from PaymentFilter
 * <p>
 * Author dattrinh Mar 17, 2016 2:48:04 PM
 */
public class PaymentFilter extends BaseFilter {
    private Date startDate;
    private Date endDate;
    private Long bankId;
    private String customerCode;
    private Double min;
    private Double max;
    private String note;
    private String cheque;
    private String franchiseList;

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

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }
}

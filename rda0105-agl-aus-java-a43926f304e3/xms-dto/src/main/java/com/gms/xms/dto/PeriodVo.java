package com.gms.xms.dto;

import com.gms.xms.txndb.vo.BaseVo;

import java.util.Date;

/**
 * Posted from Sep 19, 2016 11:49:15 AM
 * <p>
 * Author dattrinh
 */
public class PeriodVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Date startDate;
    private Date endDate;
    private Integer franchisePayableStatus;
    private Integer freezeCalculateStatus;
    private Integer franchisePayableRevenueShare;
    private Integer franchiseReceivablesRevenueShare;
    private String rptTxnId;

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

    public Integer getFranchisePayableStatus() {
        return franchisePayableStatus;
    }

    public void setFranchisePayableStatus(Integer franchisePayableStatus) {
        this.franchisePayableStatus = franchisePayableStatus;
    }

    public Integer getFreezeCalculateStatus() {
        return freezeCalculateStatus;
    }

    public void setFreezeCalculateStatus(Integer freezeCalculateStatus) {
        this.freezeCalculateStatus = freezeCalculateStatus;
    }

    public Integer getFranchisePayableRevenueShare() {
        return franchisePayableRevenueShare;
    }

    public void setFranchisePayableRevenueShare(Integer franchisePayableRevenueShare) {
        this.franchisePayableRevenueShare = franchisePayableRevenueShare;
    }

    public Integer getFranchiseReceivablesRevenueShare() {
        return franchiseReceivablesRevenueShare;
    }

    public void setFranchiseReceivablesRevenueShare(Integer franchiseReceivablesRevenueShare) {
        this.franchiseReceivablesRevenueShare = franchiseReceivablesRevenueShare;
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

    @Override
    public String toString() {
        return "PeriodVo [startDate=" + startDate + ", endDate=" + endDate + ", franchisePayableStatus=" + franchisePayableStatus + ", freezeCalculateStatus=" + freezeCalculateStatus + ", franchisePayableRevenueShare=" + franchisePayableRevenueShare + ", franchiseReceivablesRevenueShare=" + franchiseReceivablesRevenueShare + ", rptTxnId=" + rptTxnId + "]";
    }
}

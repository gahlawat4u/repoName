package com.gms.xms.model.franchisepayable;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayablePeriodModel.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:17:16 PM
 */
public class FranchisePayablePeriodModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private String startDate;
    private String endDate;
    private String franchisePayableStatus;
    private String freezeCalculateStatus;
    private String franchisePayableRevenueShare;
    private String franchiseReceivablesRevenueShare;
    private String rptTxnId;

    @Override
    public String toString() {
        return "FranchisePayablePeriodModel [startDate=" + startDate + ", endDate=" + endDate + ", franchisePayableStatus=" + franchisePayableStatus + ", freezeCalculateStatus=" + freezeCalculateStatus + ", franchisePayableRevenueShare=" + franchisePayableRevenueShare + ", franchiseReceivablesRevenueShare=" + franchiseReceivablesRevenueShare + ", rptTxnId=" + rptTxnId + "]";
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFranchisePayableStatus() {
        return franchisePayableStatus;
    }

    public void setFranchisePayableStatus(String franchisePayableStatus) {
        this.franchisePayableStatus = franchisePayableStatus;
    }

    public String getFreezeCalculateStatus() {
        return freezeCalculateStatus;
    }

    public void setFreezeCalculateStatus(String freezeCalculateStatus) {
        this.freezeCalculateStatus = freezeCalculateStatus;
    }

    public String getFranchisePayableRevenueShare() {
        return franchisePayableRevenueShare;
    }

    public void setFranchisePayableRevenueShare(String franchisePayableRevenueShare) {
        this.franchisePayableRevenueShare = franchisePayableRevenueShare;
    }

    public String getFranchiseReceivablesRevenueShare() {
        return franchiseReceivablesRevenueShare;
    }

    public void setFranchiseReceivablesRevenueShare(String franchiseReceivablesRevenueShare) {
        this.franchiseReceivablesRevenueShare = franchiseReceivablesRevenueShare;
    }

    public String getRptTxnId() {
        return rptTxnId;
    }

    public void setRptTxnId(String rptTxnId) {
        this.rptTxnId = rptTxnId;
    }

}

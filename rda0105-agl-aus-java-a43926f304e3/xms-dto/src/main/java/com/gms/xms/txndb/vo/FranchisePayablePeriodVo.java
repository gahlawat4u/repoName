package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from FranchisePayablePeriodVo.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:24:52 PM
 */
public class FranchisePayablePeriodVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private Date startDate;
    private Date endDate;
    private Integer franchisePayableStatus;
    private Integer freezeCalculateStatus;
    private Integer franchisePayableRevenueShare;
    private Integer franchiseReceivablesRevenueShare;
    private String rptTxnId;

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
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
        return "FranchisePayablePeriodVo [startDate=" + startDate + ", endDate=" + endDate + ", franchisePayableStatus=" + franchisePayableStatus + ", freezeCalculateStatus=" + freezeCalculateStatus + ", franchisePayableRevenueShare=" + franchisePayableRevenueShare + ", franchiseReceivablesRevenueShare=" + franchiseReceivablesRevenueShare + ", rptTxnId=" + rptTxnId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((franchisePayableRevenueShare == null) ? 0 : franchisePayableRevenueShare.hashCode());
        result = prime * result + ((franchisePayableStatus == null) ? 0 : franchisePayableStatus.hashCode());
        result = prime * result + ((franchiseReceivablesRevenueShare == null) ? 0 : franchiseReceivablesRevenueShare.hashCode());
        result = prime * result + ((freezeCalculateStatus == null) ? 0 : freezeCalculateStatus.hashCode());
        result = prime * result + ((rptTxnId == null) ? 0 : rptTxnId.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FranchisePayablePeriodVo other = (FranchisePayablePeriodVo) obj;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (franchisePayableRevenueShare == null) {
            if (other.franchisePayableRevenueShare != null)
                return false;
        } else if (!franchisePayableRevenueShare.equals(other.franchisePayableRevenueShare))
            return false;
        if (franchisePayableStatus == null) {
            if (other.franchisePayableStatus != null)
                return false;
        } else if (!franchisePayableStatus.equals(other.franchisePayableStatus))
            return false;
        if (franchiseReceivablesRevenueShare == null) {
            if (other.franchiseReceivablesRevenueShare != null)
                return false;
        } else if (!franchiseReceivablesRevenueShare.equals(other.franchiseReceivablesRevenueShare))
            return false;
        if (freezeCalculateStatus == null) {
            if (other.freezeCalculateStatus != null)
                return false;
        } else if (!freezeCalculateStatus.equals(other.freezeCalculateStatus))
            return false;
        if (rptTxnId == null) {
            if (other.rptTxnId != null)
                return false;
        } else if (!rptTxnId.equals(other.rptTxnId))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }
}

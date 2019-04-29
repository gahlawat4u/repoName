package com.gms.xms.txndb.vo.adjustment;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CarrierAdjustmentCountStaticVo
 * <p>
 * Author TanDT Date Jun 2, 2015
 */
public class CarrierAdjustmentCountStaticVo extends BaseVo {

    private static final long serialVersionUID = 5934376138362925386L;
    private Integer countSubmitted;
    private Integer countPending;
    private Integer countDisputed;
    private Integer countApproved;
    private Integer countDisputedDenied;

    public Integer getCountSubmitted() {
        return countSubmitted;
    }

    public void setCountSubmitted(Integer countSubmitted) {
        this.countSubmitted = countSubmitted;
    }

    public Integer getCountPending() {
        return countPending;
    }

    public void setCountPending(Integer countPending) {
        this.countPending = countPending;
    }

    public Integer getCountDisputed() {
        return countDisputed;
    }

    public void setCountDisputed(Integer countDisputed) {
        this.countDisputed = countDisputed;
    }

    public Integer getCountApproved() {
        return countApproved;
    }

    public void setCountApproved(Integer countApproved) {
        this.countApproved = countApproved;
    }

    public Integer getCountDisputedDenied() {
        return countDisputedDenied;
    }

    public void setCountDisputedDenied(Integer countDisputedDenied) {
        this.countDisputedDenied = countDisputedDenied;
    }

    @Override
    public String toString() {
        return "CarrierAdjustmentCountStaticVo [countSubmitted=" + countSubmitted + ", countPending=" + countPending + ", countDisputed=" + countDisputed + ", countApproved=" + countApproved + ", countDisputedDenied=" + countDisputedDenied + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((countApproved == null) ? 0 : countApproved.hashCode());
        result = prime * result + ((countDisputed == null) ? 0 : countDisputed.hashCode());
        result = prime * result + ((countDisputedDenied == null) ? 0 : countDisputedDenied.hashCode());
        result = prime * result + ((countPending == null) ? 0 : countPending.hashCode());
        result = prime * result + ((countSubmitted == null) ? 0 : countSubmitted.hashCode());
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
        CarrierAdjustmentCountStaticVo other = (CarrierAdjustmentCountStaticVo) obj;
        if (countApproved == null) {
            if (other.countApproved != null)
                return false;
        } else if (!countApproved.equals(other.countApproved))
            return false;
        if (countDisputed == null) {
            if (other.countDisputed != null)
                return false;
        } else if (!countDisputed.equals(other.countDisputed))
            return false;
        if (countDisputedDenied == null) {
            if (other.countDisputedDenied != null)
                return false;
        } else if (!countDisputedDenied.equals(other.countDisputedDenied))
            return false;
        if (countPending == null) {
            if (other.countPending != null)
                return false;
        } else if (!countPending.equals(other.countPending))
            return false;
        if (countSubmitted == null) {
            if (other.countSubmitted != null)
                return false;
        } else if (!countSubmitted.equals(other.countSubmitted))
            return false;
        return true;
    }

}

package com.gms.xms.txndb.vo;

import java.util.Date;

/**
 * Posted from CarrierCreditVo
 * <p>
 * Author TanDT Date May 29, 2015
 */
public class CarrierCreditVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 7910873994592339213L;

    private Long carrierCreditId;

    private Date creditDate;

    private Double carrierCost;

    private Double taxAmount;

    private Long adjustmentId;

    public Long getCarrierCreditId() {
        return carrierCreditId;
    }

    public void setCarrierCreditId(Long carrierCreditId) {
        this.carrierCreditId = carrierCreditId;
    }

    public Date getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(Double carrierCost) {
        this.carrierCost = carrierCost;
    }

    public Long getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(Long adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    @Override
    public String toString() {
        return "CarrierCreditVo [carrierCreditId=" + carrierCreditId + ", creditDate=" + creditDate + ", carrierCost=" + carrierCost + ", taxAmount=" + taxAmount + ", adjustmentId=" + adjustmentId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adjustmentId == null) ? 0 : adjustmentId.hashCode());
        result = prime * result + ((carrierCost == null) ? 0 : carrierCost.hashCode());
        result = prime * result + ((carrierCreditId == null) ? 0 : carrierCreditId.hashCode());
        result = prime * result + ((creditDate == null) ? 0 : creditDate.hashCode());
        result = prime * result + ((taxAmount == null) ? 0 : taxAmount.hashCode());
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
        CarrierCreditVo other = (CarrierCreditVo) obj;
        if (adjustmentId == null) {
            if (other.adjustmentId != null)
                return false;
        } else if (!adjustmentId.equals(other.adjustmentId))
            return false;
        if (carrierCost == null) {
            if (other.carrierCost != null)
                return false;
        } else if (!carrierCost.equals(other.carrierCost))
            return false;
        if (carrierCreditId == null) {
            if (other.carrierCreditId != null)
                return false;
        } else if (!carrierCreditId.equals(other.carrierCreditId))
            return false;
        if (creditDate == null) {
            if (other.creditDate != null)
                return false;
        } else if (!creditDate.equals(other.creditDate))
            return false;
        if (taxAmount == null) {
            if (other.taxAmount != null)
                return false;
        } else if (!taxAmount.equals(other.taxAmount))
            return false;
        return true;
    }

}
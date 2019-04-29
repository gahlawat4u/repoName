package com.gms.xms.txndb.vo.dhl;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from DhlRateSheetsVo
 * <p>
 * Author HungNT Date May 18, 2015
 */
public class DhlRateSheetsVo extends BaseVo {
    private static final long serialVersionUID = 872850607731860002L;

    private Long carrierDocumentRate;
    private Long carrierPackageRate;
    private Long carrierPackagePerWeightRate;
    private Long carrierPakRate;
    private Long carrierPakPerWeightRate;

    private Long nonCarrierDocumentRate;
    private Long nonCarrierPackageRate;
    private Long nonCarrierPackagePerWeightRate;
    private Long nonCarrierPakRate;
    private Long nonCarrierPakPerWeightRate;

    public Long getCarrierDocumentRate() {
        return carrierDocumentRate;
    }

    public void setCarrierDocumentRate(Long carrierDocumentRate) {
        this.carrierDocumentRate = carrierDocumentRate;
    }

    public Long getCarrierPackageRate() {
        return carrierPackageRate;
    }

    public void setCarrierPackageRate(Long carrierPackageRate) {
        this.carrierPackageRate = carrierPackageRate;
    }

    public Long getCarrierPackagePerWeightRate() {
        return carrierPackagePerWeightRate;
    }

    public void setCarrierPackagePerWeightRate(Long carrierPackagePerWeightRate) {
        this.carrierPackagePerWeightRate = carrierPackagePerWeightRate;
    }

    public Long getNonCarrierDocumentRate() {
        return nonCarrierDocumentRate;
    }

    public void setNonCarrierDocumentRate(Long nonCarrierDocumentRate) {
        this.nonCarrierDocumentRate = nonCarrierDocumentRate;
    }

    public Long getNonCarrierPackageRate() {
        return nonCarrierPackageRate;
    }

    public void setNonCarrierPackageRate(Long nonCarrierPackageRate) {
        this.nonCarrierPackageRate = nonCarrierPackageRate;
    }

    public Long getNonCarrierPackagePerWeightRate() {
        return nonCarrierPackagePerWeightRate;
    }

    public void setNonCarrierPackagePerWeightRate(Long nonCarrierPackagePerWeightRate) {
        this.nonCarrierPackagePerWeightRate = nonCarrierPackagePerWeightRate;
    }

    public Long getCarrierPakRate() {
        return carrierPakRate;
    }

    public void setCarrierPakRate(Long carrierPakRate) {
        this.carrierPakRate = carrierPakRate;
    }

    public Long getCarrierPakPerWeightRate() {
        return carrierPakPerWeightRate;
    }

    public void setCarrierPakPerWeightRate(Long carrierPakPerWeightRate) {
        this.carrierPakPerWeightRate = carrierPakPerWeightRate;
    }

    public Long getNonCarrierPakRate() {
        return nonCarrierPakRate;
    }

    public void setNonCarrierPakRate(Long nonCarrierPakRate) {
        this.nonCarrierPakRate = nonCarrierPakRate;
    }

    public Long getNonCarrierPakPerWeightRate() {
        return nonCarrierPakPerWeightRate;
    }

    public void setNonCarrierPakPerWeightRate(Long nonCarrierPakPerWeightRate) {
        this.nonCarrierPakPerWeightRate = nonCarrierPakPerWeightRate;
    }

    @Override
    public String toString() {
        return "DhlRateSheetsVo [carrierDocumentRate=" + carrierDocumentRate + ", carrierPackageRate=" + carrierPackageRate + ", carrierPackagePerWeightRate=" + carrierPackagePerWeightRate + ", carrierPakRate=" + carrierPakRate + ", carrierPakPerWeightRate=" + carrierPakPerWeightRate + ", nonCarrierDocumentRate=" + nonCarrierDocumentRate + ", nonCarrierPackageRate=" + nonCarrierPackageRate + ", nonCarrierPackagePerWeightRate=" + nonCarrierPackagePerWeightRate + ", nonCarrierPakRate="
                + nonCarrierPakRate + ", nonCarrierPakPerWeightRate=" + nonCarrierPakPerWeightRate + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carrierDocumentRate == null) ? 0 : carrierDocumentRate.hashCode());
        result = prime * result + ((carrierPackagePerWeightRate == null) ? 0 : carrierPackagePerWeightRate.hashCode());
        result = prime * result + ((carrierPackageRate == null) ? 0 : carrierPackageRate.hashCode());
        result = prime * result + ((carrierPakPerWeightRate == null) ? 0 : carrierPakPerWeightRate.hashCode());
        result = prime * result + ((carrierPakRate == null) ? 0 : carrierPakRate.hashCode());
        result = prime * result + ((nonCarrierDocumentRate == null) ? 0 : nonCarrierDocumentRate.hashCode());
        result = prime * result + ((nonCarrierPackagePerWeightRate == null) ? 0 : nonCarrierPackagePerWeightRate.hashCode());
        result = prime * result + ((nonCarrierPackageRate == null) ? 0 : nonCarrierPackageRate.hashCode());
        result = prime * result + ((nonCarrierPakPerWeightRate == null) ? 0 : nonCarrierPakPerWeightRate.hashCode());
        result = prime * result + ((nonCarrierPakRate == null) ? 0 : nonCarrierPakRate.hashCode());
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
        DhlRateSheetsVo other = (DhlRateSheetsVo) obj;
        if (carrierDocumentRate == null) {
            if (other.carrierDocumentRate != null)
                return false;
        } else if (!carrierDocumentRate.equals(other.carrierDocumentRate))
            return false;
        if (carrierPackagePerWeightRate == null) {
            if (other.carrierPackagePerWeightRate != null)
                return false;
        } else if (!carrierPackagePerWeightRate.equals(other.carrierPackagePerWeightRate))
            return false;
        if (carrierPackageRate == null) {
            if (other.carrierPackageRate != null)
                return false;
        } else if (!carrierPackageRate.equals(other.carrierPackageRate))
            return false;
        if (carrierPakPerWeightRate == null) {
            if (other.carrierPakPerWeightRate != null)
                return false;
        } else if (!carrierPakPerWeightRate.equals(other.carrierPakPerWeightRate))
            return false;
        if (carrierPakRate == null) {
            if (other.carrierPakRate != null)
                return false;
        } else if (!carrierPakRate.equals(other.carrierPakRate))
            return false;
        if (nonCarrierDocumentRate == null) {
            if (other.nonCarrierDocumentRate != null)
                return false;
        } else if (!nonCarrierDocumentRate.equals(other.nonCarrierDocumentRate))
            return false;
        if (nonCarrierPackagePerWeightRate == null) {
            if (other.nonCarrierPackagePerWeightRate != null)
                return false;
        } else if (!nonCarrierPackagePerWeightRate.equals(other.nonCarrierPackagePerWeightRate))
            return false;
        if (nonCarrierPackageRate == null) {
            if (other.nonCarrierPackageRate != null)
                return false;
        } else if (!nonCarrierPackageRate.equals(other.nonCarrierPackageRate))
            return false;
        if (nonCarrierPakPerWeightRate == null) {
            if (other.nonCarrierPakPerWeightRate != null)
                return false;
        } else if (!nonCarrierPakPerWeightRate.equals(other.nonCarrierPakPerWeightRate))
            return false;
        if (nonCarrierPakRate == null) {
            if (other.nonCarrierPakRate != null)
                return false;
        } else if (!nonCarrierPakRate.equals(other.nonCarrierPakRate))
            return false;
        return true;
    }
}

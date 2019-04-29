package com.gms.xms.txndb.vo.ratesheet;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from CarrierRateVo
 * <p>
 * Author TANDT
 */
public class CarrierRateVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -2349342119546905893L;

    private Double carrierRate;
    private Double nonCarrierRate;
    private Double carrierPerWeightRate;
    private Double nonCarrierPerWeightRate;
    private Double fuelSurcharge;

    public Double getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(Double fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    public Double getCarrierRate() {
        return carrierRate;
    }

    public void setCarrierRate(Double carrierRate) {
        this.carrierRate = carrierRate;
    }

    public Double getNonCarrierRate() {
        return nonCarrierRate;
    }

    public void setNonCarrierRate(Double nonCarrierRate) {
        this.nonCarrierRate = nonCarrierRate;
    }

    public Double getCarrierPerWeightRate() {
        return carrierPerWeightRate;
    }

    public void setCarrierPerWeightRate(Double carrierPerWeightRate) {
        this.carrierPerWeightRate = carrierPerWeightRate;
    }

    public Double getNonCarrierPerWeightRate() {
        return nonCarrierPerWeightRate;
    }

    public void setNonCarrierPerWeightRate(Double nonCarrierPerWeightRate) {
        this.nonCarrierPerWeightRate = nonCarrierPerWeightRate;
    }

    @Override
    public String toString() {
        return "CarrierRateVo [carrierRate=" + carrierRate + ", nonCarrierRate=" + nonCarrierRate + ", carrierPerWeightRate=" + carrierPerWeightRate + ", nonCarrierPerWeightRate=" + nonCarrierPerWeightRate + ", fuelSurcharge=" + fuelSurcharge + "]";
    }

}

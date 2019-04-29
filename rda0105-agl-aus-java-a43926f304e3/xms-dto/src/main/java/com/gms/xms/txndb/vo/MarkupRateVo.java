package com.gms.xms.txndb.vo;

/**
 * Posted from Aug 16, 2016 11:07:19 AM
 * <p>
 * Author huynd
 */
public class MarkupRateVo extends FranchiseVo {

    private static final long serialVersionUID = 1L;

    private Double tntServiceMarkupRate;

    public Double getTntServiceMarkupRate() {
        return tntServiceMarkupRate;
    }

    public void setTntServiceMarkupRate(Double tntServiceMarkupRate) {
        this.tntServiceMarkupRate = tntServiceMarkupRate;
    }

    @Override
    public String toString() {
        return "MarkupRateVo [tntServiceMarkupRate=" + tntServiceMarkupRate + "]";
    }
}

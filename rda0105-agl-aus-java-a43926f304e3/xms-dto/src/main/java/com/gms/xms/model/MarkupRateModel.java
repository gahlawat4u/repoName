package com.gms.xms.model;

/**
 * Posted from Aug 16, 2016 11:07:19 AM
 * <p>
 * Author huynd
 */
public class MarkupRateModel extends FranchiseModel {

    private static final long serialVersionUID = 1L;

    private String tntServiceMarkupRate;

    public String getTntServiceMarkupRate() {
        return tntServiceMarkupRate;
    }

    public void setTntServiceMarkupRate(String tntServiceMarkupRate) {
        this.tntServiceMarkupRate = tntServiceMarkupRate;
    }

    @Override
    public String toString() {
        return "MarkupRateModel [tntServiceMarkupRate=" + tntServiceMarkupRate + "]";
    }
}

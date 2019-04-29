package com.gms.xms.txndb.vo.ratesheet;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from MaxMinWeightVo
 * <p>
 * Author TANDT
 */
public class MaxMinWeightVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 7046697025392125267L;
    private String detail;
    private Double weight;
    private String rateValue;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

    @Override
    public String toString() {
        return "MaxMinWeightVo [detail=" + detail + ", weight=" + weight + ", rateValue=" + rateValue + "]";
    }

}

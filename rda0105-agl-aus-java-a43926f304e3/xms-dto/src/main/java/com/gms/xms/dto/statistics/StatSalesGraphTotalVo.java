package com.gms.xms.dto.statistics;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Aug 23, 2016 10:11:51 AM
 * <p>
 * Author dattrinh
 */
public class StatSalesGraphTotalVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer saleState;
    private Double revenue;

    public Integer getSaleState() {
        return saleState;
    }

    public void setSaleState(Integer saleState) {
        this.saleState = saleState;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "StatSalesGraphTotalVo [saleState=" + saleState + ", revenue=" + revenue + "]";
    }
}

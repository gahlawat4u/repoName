package com.gms.xms.dto.statistics;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Aug 23, 2016 2:19:47 PM
 * <p>
 * Author dattrinh
 */
public class StatSalesRepBySalePersonVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String name;
    private Long total1;
    private Double total2;
    private String total3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotal1() {
        return total1;
    }

    public void setTotal1(Long total1) {
        this.total1 = total1;
    }

    public Double getTotal2() {
        return total2;
    }

    public void setTotal2(Double total2) {
        this.total2 = total2;
    }

    public String getTotal3() {
        return total3;
    }

    public void setTotal3(String total3) {
        this.total3 = total3;
    }

    @Override
    public String toString() {
        return "StatSalesRepBySalePersonVo [name=" + name + ", total1=" + total1 + ", total2=" + total2 + ", total3=" + total3 + "]";
    }
}

package com.gms.xms.dto.statistics;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Aug 23, 2016 11:43:37 AM
 * <p>
 * Author dattrinh
 */
public class StatSalesRepByTeleMarketerVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer no;
    private Long count;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "StatSalesRepByTeleMarketerVo [no=" + no + ", count=" + count + "]";
    }
}

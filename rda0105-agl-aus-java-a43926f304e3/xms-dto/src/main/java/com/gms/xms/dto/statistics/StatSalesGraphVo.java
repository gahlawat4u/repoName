package com.gms.xms.dto.statistics;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Aug 22, 2016 2:17:28 PM
 * <p>
 * Author dattrinh
 */
public class StatSalesGraphVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer srno;
    private Integer status;
    private String statusName;
    private Long count;

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "StatSalesGraphVo [srno=" + srno + ", status=" + status + ", statusName=" + statusName + ", count=" + count + "]";
    }
}

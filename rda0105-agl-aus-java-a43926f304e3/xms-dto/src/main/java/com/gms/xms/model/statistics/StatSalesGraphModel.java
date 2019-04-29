package com.gms.xms.model.statistics;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Aug 22, 2016 2:17:28 PM
 * <p>
 * Author dattrinh
 */
public class StatSalesGraphModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String srno;
    private String status;
    private String statusName;
    private String count;

    public String getSrno() {
        return srno;
    }

    public void setSrno(String srno) {
        this.srno = srno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "StatSalesGraphModel [srno=" + srno + ", status=" + status + ", statusName=" + statusName + ", count=" + count + "]";
    }
}

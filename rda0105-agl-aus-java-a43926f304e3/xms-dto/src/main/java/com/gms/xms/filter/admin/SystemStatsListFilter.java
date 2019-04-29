package com.gms.xms.filter.admin;

import com.gms.xms.filter.BaseFilter;
import com.gms.xms.txndb.vo.FranchiseInfoVo;

import java.util.Date;
import java.util.List;

/**
 * @author TanDt
 */
public class SystemStatsListFilter extends BaseFilter {
    private Date toDate;
    private Date fromDate;
    private List<FranchiseInfoVo> franchiseInforVos;
    private String errorStatus;
    private String actionType;
    private String airbillNumber;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public List<FranchiseInfoVo> getFranchiseInforVos() {
        return franchiseInforVos;
    }

    public void setFranchiseInforVos(List<FranchiseInfoVo> franchiseInforVos) {
        this.franchiseInforVos = franchiseInforVos;
    }

}

package com.gms.xms.filter.admin;

import com.gms.xms.filter.BaseFilter;

import java.util.Date;

/**
 * @author TanDt
 */
public class EventLogFilter extends BaseFilter {
    private Date fromDate;
    private Date toDate;
    private String userCode;
    private String userName;
    private String changesMode;
    private String actionType;
    private String actionTable;
    private String filter;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChangesMode() {
        return changesMode;
    }

    public void setChangesMode(String changesMode) {
        this.changesMode = changesMode;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionTable() {
        return actionTable;
    }

    public void setActionTable(String actionTable) {
        this.actionTable = actionTable;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

}

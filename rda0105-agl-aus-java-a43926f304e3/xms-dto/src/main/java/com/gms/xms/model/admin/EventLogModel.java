package com.gms.xms.model.admin;

import com.gms.xms.model.BaseModel;

/**
 * @author TANDT
 */
public class EventLogModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private String userCode;
    private String userId;
    private String userName;
    private String actionDate;
    private String actionType;
    private String actionTable;
    private String filter;
    private String changesMode;
    private String ipAddress;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
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

    public String getChangesMode() {
        return changesMode;
    }

    public void setChangesMode(String changesMode) {
        this.changesMode = changesMode;
    }


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "EventLogModel [userCode=" + userCode + ", userId=" + userId + ", userName=" + userName + ", actionDate=" + actionDate + ", actionType=" + actionType + ", actionTable=" + actionTable + ", filter=" + filter + ", changesMode=" + changesMode + ", ipAddress=" + ipAddress + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actionDate == null) ? 0 : actionDate.hashCode());
        result = prime * result + ((actionTable == null) ? 0 : actionTable.hashCode());
        result = prime * result + ((actionType == null) ? 0 : actionType.hashCode());
        result = prime * result + ((changesMode == null) ? 0 : changesMode.hashCode());
        result = prime * result + ((filter == null) ? 0 : filter.hashCode());
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventLogModel other = (EventLogModel) obj;
        if (actionDate == null) {
            if (other.actionDate != null)
                return false;
        } else if (!actionDate.equals(other.actionDate))
            return false;
        if (actionTable == null) {
            if (other.actionTable != null)
                return false;
        } else if (!actionTable.equals(other.actionTable))
            return false;
        if (actionType == null) {
            if (other.actionType != null)
                return false;
        } else if (!actionType.equals(other.actionType))
            return false;
        if (changesMode == null) {
            if (other.changesMode != null)
                return false;
        } else if (!changesMode.equals(other.changesMode))
            return false;
        if (filter == null) {
            if (other.filter != null)
                return false;
        } else if (!filter.equals(other.filter))
            return false;
        if (ipAddress == null) {
            if (other.ipAddress != null)
                return false;
        } else if (!ipAddress.equals(other.ipAddress))
            return false;
        if (userCode == null) {
            if (other.userCode != null)
                return false;
        } else if (!userCode.equals(other.userCode))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }


}

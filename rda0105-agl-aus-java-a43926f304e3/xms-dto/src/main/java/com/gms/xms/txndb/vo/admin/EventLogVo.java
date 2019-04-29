package com.gms.xms.txndb.vo.admin;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonString2DateTimeDeserializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * @author TANDT
 */
public class EventLogVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private Long userCode;
    private Long userId;
    private String userName;
    private Date actionDate;
    private String actionType;
    private String actionTable;
    private String changesMode;
    private String filter;
    private String ipAddress;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }


    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getActionDate() {
        return actionDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setActionDate(Date actionDate) {
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
        EventLogVo other = (EventLogVo) obj;
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

    @Override
    public String toString() {
        return "EventLogVo [userCode=" + userCode + ", userId=" + userId + ", userName=" + userName + ", actionDate="
                + actionDate + ", actionType=" + actionType + ", actionTable=" + actionTable + ", changesMode="
                + changesMode + ", filter=" + filter + ", ipAddress=" + ipAddress + "]";
    }


}

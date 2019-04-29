package com.gms.xms.txndb.vo.admin;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * @author TANDT
 */
public class WebshipLogVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = 343780907764393629L;
    private Long userCode;
    private String userName;
    private Date actionDate;
    private String actionType;
    private String actionTable;
    private String filter;
    private String email;
    private String changesMode;
    private String ipAddress;
    private Long userId;
    private Integer userType;

    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
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

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "WebshipLogVo [userCode=" + userCode + ", userName=" + userName + ", actionDate=" + actionDate + ", actionType=" + actionType + ", actionTable=" + actionTable + ", filter=" + filter + ", email=" + email + ", changesMode=" + changesMode + ", ipAddress=" + ipAddress + ", userId=" + userId + ", userType=" + userType + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actionDate == null) ? 0 : actionDate.hashCode());
        result = prime * result + ((actionTable == null) ? 0 : actionTable.hashCode());
        result = prime * result + ((actionType == null) ? 0 : actionType.hashCode());
        result = prime * result + ((changesMode == null) ? 0 : changesMode.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((filter == null) ? 0 : filter.hashCode());
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((userType == null) ? 0 : userType.hashCode());
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
        WebshipLogVo other = (WebshipLogVo) obj;
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
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
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
        if (userType == null) {
            if (other.userType != null)
                return false;
        } else if (!userType.equals(other.userType))
            return false;
        return true;
    }

}

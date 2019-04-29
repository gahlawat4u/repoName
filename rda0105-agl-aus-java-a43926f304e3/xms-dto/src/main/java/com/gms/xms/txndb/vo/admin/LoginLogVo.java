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
public class LoginLogVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -6327432314161746204L;

    private Integer id;
    private String userName;
    private Date loginDate;
    private Long franchiseCode;
    private String description;
    private String ipAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getLoginDate() {
        return loginDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "LoginLogVo [id=" + id + ", userName=" + userName + ", loginDate=" + loginDate + ", franchiseCode=" + franchiseCode + ", description=" + description + ", ipAddress=" + ipAddress + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((franchiseCode == null) ? 0 : franchiseCode.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = prime * result + ((loginDate == null) ? 0 : loginDate.hashCode());
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
        LoginLogVo other = (LoginLogVo) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (franchiseCode == null) {
            if (other.franchiseCode != null)
                return false;
        } else if (!franchiseCode.equals(other.franchiseCode))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (ipAddress == null) {
            if (other.ipAddress != null)
                return false;
        } else if (!ipAddress.equals(other.ipAddress))
            return false;
        if (loginDate == null) {
            if (other.loginDate != null)
                return false;
        } else if (!loginDate.equals(other.loginDate))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

}

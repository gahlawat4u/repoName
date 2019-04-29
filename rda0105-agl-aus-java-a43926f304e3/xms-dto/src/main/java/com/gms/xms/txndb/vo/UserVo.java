package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonString2DateTimeDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Posted from UserVo.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:25:29 PM
 */
public class UserVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long userCode;
    private String userName;
    private String password;
    private Integer userLevelId;
    private String displayName;
    private String email;
    private String phone;
    private String fax;
    private Boolean isRequireChangePassword;
    private Boolean isCollector;
    private Date lastChange;
    private Long parentUserId;
    private Integer language;
    private Integer targetPhoneCall;
    private Integer overdueDay;
    private Integer targetSuccess;
    private BigDecimal userLevelCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Boolean getIsRequireChangePassword() {
        return isRequireChangePassword;
    }

    public void setIsRequireChangePassword(Boolean isRequireChangePassword) {
        this.isRequireChangePassword = isRequireChangePassword;
    }

    public Boolean getIsCollector() {
        return isCollector;
    }

    public void setIsCollector(Boolean isCollector) {
        this.isCollector = isCollector;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getLastChange() {
        return lastChange;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public Long getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getTargetPhoneCall() {
        return targetPhoneCall;
    }

    public void setTargetPhoneCall(Integer targetPhoneCall) {
        this.targetPhoneCall = targetPhoneCall;
    }

    public Integer getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(Integer overdueDay) {
        this.overdueDay = overdueDay;
    }

    public Integer getTargetSuccess() {
        return targetSuccess;
    }

    public void setTargetSuccess(Integer targetSuccess) {
        this.targetSuccess = targetSuccess;
    }

    public BigDecimal getUserLevelCode() {
        return userLevelCode;
    }

    public void setUserLevelCode(BigDecimal userLevelCode) {
        this.userLevelCode = userLevelCode;
    }

    @Override
    public String toString() {
        return "UserVo [userId=" + userId + ", userCode=" + userCode + ", userName=" + userName + ", password=" + password + ", userLevelId=" + userLevelId + ", displayName=" + displayName + ", email=" + email + ", phone=" + phone + ", fax=" + fax + ", isRequireChangePassword=" + isRequireChangePassword + ", isCollector=" + isCollector + ", lastChange=" + lastChange + ", parentUserId=" + parentUserId + ", language=" + language + ", targetPhoneCall=" + targetPhoneCall + ", overdueDay="
                + overdueDay + ", targetSuccess=" + targetSuccess + ", userLevelCode=" + userLevelCode + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fax == null) ? 0 : fax.hashCode());
        result = prime * result + ((isCollector == null) ? 0 : isCollector.hashCode());
        result = prime * result + ((isRequireChangePassword == null) ? 0 : isRequireChangePassword.hashCode());
        result = prime * result + ((language == null) ? 0 : language.hashCode());
        result = prime * result + ((lastChange == null) ? 0 : lastChange.hashCode());
        result = prime * result + ((overdueDay == null) ? 0 : overdueDay.hashCode());
        result = prime * result + ((parentUserId == null) ? 0 : parentUserId.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((targetPhoneCall == null) ? 0 : targetPhoneCall.hashCode());
        result = prime * result + ((targetSuccess == null) ? 0 : targetSuccess.hashCode());
        result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((userLevelCode == null) ? 0 : userLevelCode.hashCode());
        result = prime * result + ((userLevelId == null) ? 0 : userLevelId.hashCode());
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
        UserVo other = (UserVo) obj;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (fax == null) {
            if (other.fax != null)
                return false;
        } else if (!fax.equals(other.fax))
            return false;
        if (isCollector == null) {
            if (other.isCollector != null)
                return false;
        } else if (!isCollector.equals(other.isCollector))
            return false;
        if (isRequireChangePassword == null) {
            if (other.isRequireChangePassword != null)
                return false;
        } else if (!isRequireChangePassword.equals(other.isRequireChangePassword))
            return false;
        if (language == null) {
            if (other.language != null)
                return false;
        } else if (!language.equals(other.language))
            return false;
        if (lastChange == null) {
            if (other.lastChange != null)
                return false;
        } else if (!lastChange.equals(other.lastChange))
            return false;
        if (overdueDay == null) {
            if (other.overdueDay != null)
                return false;
        } else if (!overdueDay.equals(other.overdueDay))
            return false;
        if (parentUserId == null) {
            if (other.parentUserId != null)
                return false;
        } else if (!parentUserId.equals(other.parentUserId))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (targetPhoneCall == null) {
            if (other.targetPhoneCall != null)
                return false;
        } else if (!targetPhoneCall.equals(other.targetPhoneCall))
            return false;
        if (targetSuccess == null) {
            if (other.targetSuccess != null)
                return false;
        } else if (!targetSuccess.equals(other.targetSuccess))
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
        if (userLevelCode == null) {
            if (other.userLevelCode != null)
                return false;
        } else if (!userLevelCode.equals(other.userLevelCode))
            return false;
        if (userLevelId == null) {
            if (other.userLevelId != null)
                return false;
        } else if (!userLevelId.equals(other.userLevelId))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

}
package com.gms.xms.model;

/**
 * Posted from UserModel.java
 * <p>
 * Author Toantq Date Mar 22, 2015 Time: 5:15:49 PM
 */
public class UserModel extends BaseModel {

    private static final long serialVersionUID = 803588620188908308L;

    private String userId;
    private String userCode;
    private String userName;
    private String password;
    private String userLevelId;
    private String displayName;
    private String email;
    private String phone;
    private String fax;
    private String isRequireChangePassword;
    private String isCollector;
    private String lastChange;
    private String parentUserId;
    private String language;
    private String targetPhoneCall;
    private String overdueDay;
    private String targetSuccess;
    private String userLevelCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
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

    public String getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(String userLevelId) {
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

    public String getIsRequireChangePassword() {
        return isRequireChangePassword;
    }

    public void setIsRequireChangePassword(String isRequireChangePassword) {
        this.isRequireChangePassword = isRequireChangePassword;
    }

    public String getIsCollector() {
        return isCollector;
    }

    public void setIsCollector(String isCollector) {
        this.isCollector = isCollector;
    }

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTargetPhoneCall() {
        return targetPhoneCall;
    }

    public void setTargetPhoneCall(String targetPhoneCall) {
        this.targetPhoneCall = targetPhoneCall;
    }

    public String getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(String overdueDay) {
        this.overdueDay = overdueDay;
    }

    public String getTargetSuccess() {
        return targetSuccess;
    }

    public void setTargetSuccess(String targetSuccess) {
        this.targetSuccess = targetSuccess;
    }

    public String getUserLevelCode() {
        return userLevelCode;
    }

    public void setUserLevelCode(String userLevelCode) {
        this.userLevelCode = userLevelCode;
    }

    @Override
    public String toString() {
        return "UserModel [userId=" + userId + ", userCode=" + userCode + ", userName=" + userName + ", password=" + password + ", userLevelId=" + userLevelId + ", displayName=" + displayName + ", email=" + email + ", phone=" + phone + ", fax=" + fax + ", isRequireChangePassword=" + isRequireChangePassword + ", isCollector=" + isCollector + ", lastChange=" + lastChange + ", parentUserId=" + parentUserId + ", language=" + language + ", targetPhoneCall=" + targetPhoneCall + ", overdueDay="
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
        UserModel other = (UserModel) obj;
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

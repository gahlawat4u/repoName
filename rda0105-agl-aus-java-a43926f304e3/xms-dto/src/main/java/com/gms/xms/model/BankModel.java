package com.gms.xms.model;

/**
 * Posted from BankModel
 * <p>
 * Author DatTV Date Apr 7, 2015 5:05:27 PM
 */
public class BankModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String bankId;

    private String bankName;

    private String userLevelId;

    private String modifiedDate;

    private UserLevelModel userLevel;

    public UserLevelModel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevelModel userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public String toString() {
        return "BankModel [bankId=" + bankId + ", bankName=" + bankName + ", userLevelId=" + userLevelId + ", modifiedDate=" + modifiedDate + ", userLevel=" + userLevel + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bankId == null) ? 0 : bankId.hashCode());
        result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
        result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
        result = prime * result + ((userLevel == null) ? 0 : userLevel.hashCode());
        result = prime * result + ((userLevelId == null) ? 0 : userLevelId.hashCode());
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
        BankModel other = (BankModel) obj;
        if (bankId == null) {
            if (other.bankId != null)
                return false;
        } else if (!bankId.equals(other.bankId))
            return false;
        if (bankName == null) {
            if (other.bankName != null)
                return false;
        } else if (!bankName.equals(other.bankName))
            return false;
        if (modifiedDate == null) {
            if (other.modifiedDate != null)
                return false;
        } else if (!modifiedDate.equals(other.modifiedDate))
            return false;
        if (userLevel == null) {
            if (other.userLevel != null)
                return false;
        } else if (!userLevel.equals(other.userLevel))
            return false;
        if (userLevelId == null) {
            if (other.userLevelId != null)
                return false;
        } else if (!userLevelId.equals(other.userLevelId))
            return false;
        return true;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(String userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
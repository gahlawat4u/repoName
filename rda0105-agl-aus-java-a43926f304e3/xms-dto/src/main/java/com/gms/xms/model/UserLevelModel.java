package com.gms.xms.model;

/**
 * Posted from UserLevelModel
 * <p>
 * Author DatTV Date May 22, 2015 11:41:34 AM
 */
public class UserLevelModel extends BaseModel {
    private static final long serialVersionUID = -6954713879322132207L;

    private String userLevelId;

    private String userLevelName;

    private String userLevelCode;

    private String localizationId;

    @Override
    public String toString() {
        return "UserLevelModel [userLevelId=" + userLevelId + ", userLevelName=" + userLevelName + ", userLevelCode=" + userLevelCode + ", localizationId=" + localizationId + "]";
    }

    public String getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(String userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getUserLevelName() {
        return userLevelName;
    }

    public void setUserLevelName(String userLevelName) {
        this.userLevelName = userLevelName;
    }

    public String getUserLevelCode() {
        return userLevelCode;
    }

    public void setUserLevelCode(String userLevelCode) {
        this.userLevelCode = userLevelCode;
    }

    public String getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(String localizationId) {
        this.localizationId = localizationId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((localizationId == null) ? 0 : localizationId.hashCode());
        result = prime * result + ((userLevelCode == null) ? 0 : userLevelCode.hashCode());
        result = prime * result + ((userLevelId == null) ? 0 : userLevelId.hashCode());
        result = prime * result + ((userLevelName == null) ? 0 : userLevelName.hashCode());
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
        UserLevelModel other = (UserLevelModel) obj;
        if (localizationId == null) {
            if (other.localizationId != null)
                return false;
        } else if (!localizationId.equals(other.localizationId))
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
        if (userLevelName == null) {
            if (other.userLevelName != null)
                return false;
        } else if (!userLevelName.equals(other.userLevelName))
            return false;
        return true;
    }

}
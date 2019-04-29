package com.gms.xms.txndb.vo;

/**
 * Posted from UserLevelVo
 * <p>
 * Author DatTV Date May 22, 2015 11:39:41 AM
 */
public class UserLevelVo extends BaseVo {
    private static final long serialVersionUID = -5770791961401833506L;

    private Integer userLevelId;

    private String userLevelName;

    private Double userLevelCode;

    private Long localizationId;

    @Override
    public String toString() {
        return "UserLevelVo [userLevelId=" + userLevelId + ", userLevelName=" + userLevelName + ", userLevelCode=" + userLevelCode + ", localizationId=" + localizationId + "]";
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getUserLevelName() {
        return userLevelName;
    }

    public void setUserLevelName(String userLevelName) {
        this.userLevelName = userLevelName == null ? null : userLevelName.trim();
    }

    public Double getUserLevelCode() {
        return userLevelCode;
    }

    public void setUserLevelCode(Double userLevelCode) {
        this.userLevelCode = userLevelCode;
    }

    public Long getLocalizationId() {
        return localizationId;
    }

    public void setLocalizationId(Long localizationId) {
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
        UserLevelVo other = (UserLevelVo) obj;
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
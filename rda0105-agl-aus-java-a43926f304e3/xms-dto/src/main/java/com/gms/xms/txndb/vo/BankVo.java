package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonString2DateTimeDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from BankVo
 * <p>
 * Author DatTV Date Apr 7, 2015 3:41:33 PM
 */
public class BankVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private Long bankId;

    private String bankName;

    private Integer userLevelId;

    private Date modifiedDate;

    private UserLevelVo userLevel;

    @Override
    public String toString() {
        return "BankVo [bankId=" + bankId + ", bankName=" + bankName + ", userLevelId=" + userLevelId + ", modifiedDate=" + modifiedDate + ", userLevel=" + userLevel + "]";
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

    public UserLevelVo getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevelVo userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BankVo other = (BankVo) obj;
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

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @JsonDeserialize(using = JsonString2DateTimeDeserializer.class)
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
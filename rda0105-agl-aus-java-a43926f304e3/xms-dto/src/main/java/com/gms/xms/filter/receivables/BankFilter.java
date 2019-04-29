package com.gms.xms.filter.receivables;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from BankFilter
 * <p>
 * Author HoangPH Nov 4, 2015
 */
public class BankFilter extends BaseFilter {
    private Integer userLevelId;
    private String bankName;

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "BankFilter [userLevelId=" + userLevelId + ", bankName=" + bankName + "]";
    }

}

package com.gms.xms.filter.importbilling;

import com.gms.xms.filter.BaseFilter;

public class CustomerAccountFilter extends BaseFilter {

    private String accountNo;
    private String bound;
    private String fieldStr;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBound() {
        return bound;
    }

    public void setBound(String bound) {
        this.bound = bound;
    }

    public String getFieldStr() {
        return fieldStr;
    }

    public void setFieldStr(String fieldStr) {
        this.fieldStr = fieldStr;
    }

    @Override
    public String toString() {
        return "ImportBillingFilter [accountNo=" + accountNo + ", bound=" + bound + ", fieldStr=" + fieldStr + "]";
    }

}

package com.gms.xms.txndb.vo.webship.login;

import com.gms.xms.filter.BaseFilter;

/**
 * File Name: WebshipResetFilter.java <br/>
 * Author: TANDT <br/>
 * Create Date: Jan 6, 2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.webship.login <br/>
 * Class: WebshipResetFilter
 */
public class WebshipResetFilter extends BaseFilter {
    private String customerCode;
    private String email;
    private String userName;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
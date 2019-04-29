package com.gms.xms.txndb.vo;

/**
 * Created by thinhdd on 2/22/2017.
 */
public class CustomerCodeFilter {
    private String customerCode;
    private String franchiseCode;

    public CustomerCodeFilter() {
    }

    public CustomerCodeFilter(String customerCode, String franchiseCode) {
        this.customerCode = customerCode;
        this.franchiseCode = franchiseCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }
}

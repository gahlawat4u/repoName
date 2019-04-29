package com.gms.xms.txndb.vo;

/**
 * Created by thinhdd on 2/22/2017.
 */
public class CustomerFranchiseInfoVo extends BaseVo {
    private String customerName;
    private String franchiseName;
    private String franchiseEmail;
    private String email;

    public CustomerFranchiseInfoVo() {
    }

    public CustomerFranchiseInfoVo(String customerName, String franchiseName, String franchiseEmail, String email) {
        this.customerName = customerName;
        this.franchiseName = franchiseName;
        this.franchiseEmail = franchiseEmail;
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFranchiseName() {
        return franchiseName;
    }

    public void setFranchiseName(String franchiseName) {
        this.franchiseName = franchiseName;
    }

    public String getFranchiseEmail() {
        return franchiseEmail;
    }

    public void setFranchiseEmail(String franchiseEmail) {
        this.franchiseEmail = franchiseEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

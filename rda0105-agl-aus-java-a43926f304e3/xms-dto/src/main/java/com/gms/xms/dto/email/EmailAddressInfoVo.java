package com.gms.xms.dto.email;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from Jul 26, 2016 2:36:42 PM
 * <p>
 * Author dattrinh
 */
public class EmailAddressInfoVo extends BaseVo {

    private static final long serialVersionUID = 1L;
    private String customerCode;
    private String customerName;
    private String email;
    private String phone;
    private String franchiseCode;
    private String franchiseName;
    private String franchiseEmail;
    private String franchisePhone;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
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

    public String getFranchisePhone() {
        return franchisePhone;
    }

    public void setFranchisePhone(String franchisePhone) {
        this.franchisePhone = franchisePhone;
    }

    @Override
    public String toString() {
        return "EmailAddressInfoVo [customerCode=" + customerCode + ", customerName=" + customerName + ", email=" + email + ", phone=" + phone + ", franchiseCode=" + franchiseCode + ", franchiseName=" + franchiseName + ", franchiseEmail=" + franchiseEmail + ", franchisePhone=" + franchisePhone + "]";
    }
}

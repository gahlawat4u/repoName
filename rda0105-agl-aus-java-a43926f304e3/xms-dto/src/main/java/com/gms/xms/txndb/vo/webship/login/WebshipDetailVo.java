package com.gms.xms.txndb.vo.webship.login;

import com.gms.xms.txndb.vo.webship.WebshipVo;

/**
 * Posted from WebshipDetailVo
 * <p>
 * Author DatTV Date Jul 9, 2015 9:19:11 AM
 */
public class WebshipDetailVo extends WebshipVo {
    private static final long serialVersionUID = 5058057332297901280L;

    private String customerName;
    private String email;

    @Override
    public String toString() {
        return "WebshipDetailVo [customerName=" + customerName + ", email=" + email + ", toString()=" + super.toString() + "]";
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
}
package com.gms.xms.model.webship.login;

import com.gms.xms.model.webship.WebshipModel;

/**
 * Posted from WebshipDetailModel
 * <p>
 * Author DatTV Date Jul 8, 2015 4:35:06 PM
 */
public class WebshipDetailModel extends WebshipModel {
    private static final long serialVersionUID = 2655196006705767674L;

    private String customerName;
    private String email;

    @Override
    public String toString() {
        return "WebshipDetailModel [customerName=" + customerName + ", email=" + email + ", toString()=" + super.toString() + "]";
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
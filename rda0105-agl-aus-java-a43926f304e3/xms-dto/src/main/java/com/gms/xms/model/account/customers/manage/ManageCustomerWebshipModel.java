package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.CustomerModel;

import java.util.List;

/**
 * Posted from Apr 13, 2016 10:11:00 AM
 * <p>
 * Author dattrinh
 */
public class ManageCustomerWebshipModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private CustomerModel customer;
    List<ServiceSettingModel> services;

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public List<ServiceSettingModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceSettingModel> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "ManageCustomerWebshipModel [customer=" + customer + ", services=" + services + "]";
    }
}

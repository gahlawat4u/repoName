package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.CustomerVo;

import java.util.List;

/**
 * Posted from Apr 13, 2016 10:11:00 AM
 * <p>
 * Author dattrinh
 */
public class ManageCustomerWebshipVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private CustomerVo customer;
    List<ServiceSettingVo> services;

    public CustomerVo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerVo customer) {
        this.customer = customer;
    }

    public List<ServiceSettingVo> getServices() {
        return services;
    }

    public void setServices(List<ServiceSettingVo> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "ManageCustomerWebshipVo [customer=" + customer + ", services=" + services + "]";
    }
}

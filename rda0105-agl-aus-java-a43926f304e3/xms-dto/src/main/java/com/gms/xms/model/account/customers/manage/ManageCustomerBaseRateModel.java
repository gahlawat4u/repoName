package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.ServiceModel;

import java.util.List;

public class ManageCustomerBaseRateModel extends BaseModel {

    private static final long serialVersionUID = 5437924870617521018L;

    private String customerCode;
    private String minimunBaseCharge;
    private List<ServiceModel> services;

    @Override
    public String toString() {
        return "ManageCustomerBaseRateModel [customerCode=" + customerCode + ", minimunBaseCharge=" + minimunBaseCharge + ", services=" + services + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(String minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }
}
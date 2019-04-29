package com.gms.xms.model.account.customers.manage;

import com.gms.xms.model.BaseModel;
import com.gms.xms.model.CustomerBaseRateModel;

import java.util.List;

/**
 * Posted from ManageCustomerBaseRateVo
 * <p>
 * Author DatTV Sep 17, 2015
 */
public class SaveCustomerBaseRateModel extends BaseModel {
    private static final long serialVersionUID = -2492552768269986187L;
    private String customerCode;
    private String minimunBaseCharge;
    private List<CustomerBaseRateModel> customerBaseRates;

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

    public List<CustomerBaseRateModel> getCustomerBaseRates() {
        return customerBaseRates;
    }

    public void setCustomerBaseRates(List<CustomerBaseRateModel> customerBaseRates) {
        this.customerBaseRates = customerBaseRates;
    }

    @Override
    public String toString() {
        return "SaveCustomerBaseRateModel [customerCode=" + customerCode + ", minimunBaseCharge=" + minimunBaseCharge + ", customerBaseRates=" + customerBaseRates + "]";
    }
}
package com.gms.xms.txndb.vo.account.customers.manage;

import com.gms.xms.txndb.vo.BaseVo;
import com.gms.xms.txndb.vo.CustomerBaseRateVo;

import java.util.List;

/**
 * Posted from ManageCustomerBaseRateVo
 * <p>
 * Author DatTV Sep 17, 2015
 */
public class SaveCustomerBaseRateVo extends BaseVo {

    private static final long serialVersionUID = -6098577123616203120L;

    private Long customerCode;
    private Double minimunBaseCharge;
    private List<CustomerBaseRateVo> customerBaseRates;

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Double getMinimunBaseCharge() {
        return minimunBaseCharge;
    }

    public void setMinimunBaseCharge(Double minimunBaseCharge) {
        this.minimunBaseCharge = minimunBaseCharge;
    }

    public List<CustomerBaseRateVo> getCustomerBaseRates() {
        return customerBaseRates;
    }

    public void setCustomerBaseRates(List<CustomerBaseRateVo> customerBaseRates) {
        this.customerBaseRates = customerBaseRates;
    }

    @Override
    public String toString() {
        return "SaveCustomerBaseRateVo [customerCode=" + customerCode + ", minimunBaseCharge=" + minimunBaseCharge + ", customerBaseRates=" + customerBaseRates + "]";
    }
}
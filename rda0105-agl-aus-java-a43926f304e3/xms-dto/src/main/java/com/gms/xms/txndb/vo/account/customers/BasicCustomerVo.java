package com.gms.xms.txndb.vo.account.customers;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from BasicCustomerVo
 * <p>
 * Author DatTV Sep 9, 2015
 */
public class BasicCustomerVo extends BaseVo {

    private static final long serialVersionUID = 6543111687065393632L;

    private Long customerCode;
    private String customerName;

    @Override
    public String toString() {
        return "BasicCustomerVo [customerCode=" + customerCode + ", customerName=" + customerName + "]";
    }

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}

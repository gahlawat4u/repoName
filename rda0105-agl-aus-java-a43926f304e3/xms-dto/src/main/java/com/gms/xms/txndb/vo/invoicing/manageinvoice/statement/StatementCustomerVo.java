package com.gms.xms.txndb.vo.invoicing.manageinvoice.statement;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from StatementCustomerVo
 * <p>
 * Author dattrinh Mar 16, 2016 11:34:34 AM
 */
public class StatementCustomerVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private String customerName;

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

    @Override
    public String toString() {
        return "StatementCustomerVo [customerCode=" + customerCode + ", customerName=" + customerName + "]";
    }
}

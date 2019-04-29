package com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * File Name: InvoiceCustomerVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 15-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill <br/>
 */
public class InvoiceCustomerVo extends BaseVo {

    private static final long serialVersionUID = 2429261877073095022L;
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
        return "InvoiceCustomerModel [customerCode=" + customerCode + ", customerName=" + customerName + "]";
    }

}

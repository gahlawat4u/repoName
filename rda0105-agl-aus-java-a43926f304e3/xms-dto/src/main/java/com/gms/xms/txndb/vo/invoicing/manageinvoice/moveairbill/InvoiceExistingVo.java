package com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * File Name: InvoiceExistingVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 15-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill <br/>
 */
public class InvoiceExistingVo extends BaseVo {

    /**
     *
     */
    private static final long serialVersionUID = -8233930086086241379L;
    private String invoiceCode;
    private String customerName;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "InvoiceExistingVo [invoiceCode=" + invoiceCode + ", customerName=" + customerName + "]";
    }

}

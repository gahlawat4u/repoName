package com.gms.xms.model.admin.invoicing.manageinvoice.moveairbill;

import com.gms.xms.model.BaseModel;

/**
 * File Name: InvoiceExistingModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 15-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.manageinvoice.moveairbill
 * <br/>
 */
public class InvoiceExistingModel extends BaseModel {

    /**
     *
     */
    private static final long serialVersionUID = -8426578622049752920L;
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

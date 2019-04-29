package com.gms.xms.model.admin.invoicing.manageinvoice.moveairbill;

import com.gms.xms.model.BaseModel;

/**
 * File Name: InvoiceCustomerModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 15-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.manageinvoice.moveairbill
 * <br/>
 */
public class InvoiceCustomerModel extends BaseModel {

    private static final long serialVersionUID = 4510167222565201891L;
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

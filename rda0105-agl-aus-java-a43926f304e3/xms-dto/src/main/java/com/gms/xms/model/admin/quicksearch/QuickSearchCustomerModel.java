package com.gms.xms.model.admin.quicksearch;

import com.gms.xms.model.CustomerModel;

/**
 * Posted from Apr 27, 2016 10:58:20 AM
 * <p>
 * Author huynd
 */
public class QuickSearchCustomerModel extends CustomerModel {

    private static final long serialVersionUID = 1L;

    private String customerName;
    private String invoiceToCustomerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getInvoiceToCustomerName() {
        return invoiceToCustomerName;
    }

    public void setInvoiceToCustomerName(String invoiceToCustomerName) {
        this.invoiceToCustomerName = invoiceToCustomerName;
    }

    @Override
    public String toString() {
        return "QuickSearchCustomerModel [customerName=" + customerName + ", invoiceToCustomerName=" + invoiceToCustomerName + "]";
    }

}

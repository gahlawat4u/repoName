package com.gms.xms.txndb.vo.admin.quicksearch;

import com.gms.xms.txndb.vo.CustomerVo;

/**
 * Posted from Apr 27, 2016 10:56:18 AM
 * <p>
 * Author huynd
 */
public class QuickSearchCustomerVo extends CustomerVo {

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
        return "QuickSearchCustomerVo [customerName=" + customerName + ", invoiceToCustomerName=" + invoiceToCustomerName + "]";
    }

}

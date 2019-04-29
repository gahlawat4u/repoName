package com.gms.xms.filter.invoicing;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from StatementInvoiceFilter
 * <p>
 * Author dattrinh Mar 16, 2016 4:37:53 PM
 */
public class StatementInvoiceFilter extends BaseFilter {
    private String customerCode;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}

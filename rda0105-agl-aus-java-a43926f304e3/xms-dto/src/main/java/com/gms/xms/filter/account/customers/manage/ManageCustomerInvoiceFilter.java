package com.gms.xms.filter.account.customers.manage;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from ManageCustomerInvoiceFilter
 * <p>
 * Author DatTV Sep 24, 2015
 */
public class ManageCustomerInvoiceFilter extends BaseFilter {
    private Integer filterType;
    private String customerCode;

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}

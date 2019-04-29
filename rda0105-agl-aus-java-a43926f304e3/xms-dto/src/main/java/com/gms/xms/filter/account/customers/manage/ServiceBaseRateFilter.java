package com.gms.xms.filter.account.customers.manage;

/**
 * Posted from ServiceBaseRateFilter
 * <p>
 * Author DatTV Oct 15, 2015
 */
public class ServiceBaseRateFilter {
    private String customerCode;
    private String activeCarrierList;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getActiveCarrierList() {
        return activeCarrierList;
    }

    public void setActiveCarrierList(String activeCarrierList) {
        this.activeCarrierList = activeCarrierList;
    }
}

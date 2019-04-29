package com.gms.xms.filter.customer;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from CustomerAccessorialFilter
 * <p>
 * Author DatTV Oct 7, 2015
 */
public class CustomerAccessorialFilter extends BaseFilter {
    private Long customerCode;
    private Long accessorialId;

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Long getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(Long accessorialId) {
        this.accessorialId = accessorialId;
    }
}

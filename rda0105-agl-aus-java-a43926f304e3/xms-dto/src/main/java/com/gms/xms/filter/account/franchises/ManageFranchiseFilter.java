package com.gms.xms.filter.account.franchises;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from ManageFranchiseFilter
 * <p>
 * Author TANDT 09-11-2015
 */
public class ManageFranchiseFilter extends BaseFilter {
    private Long franchiseCode;
    private Long customerCode;

    public Long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

}

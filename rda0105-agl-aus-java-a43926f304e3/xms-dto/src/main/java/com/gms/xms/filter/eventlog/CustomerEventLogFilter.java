package com.gms.xms.filter.eventlog;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from CustomerEventLogFilter
 * <p>
 * Author DatTV Oct 5, 2015
 */
public class CustomerEventLogFilter extends BaseFilter {
    private String customerCode;
    private Long profileId;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}

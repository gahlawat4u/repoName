package com.gms.xms.filter.admin;

import com.gms.xms.filter.BaseFilter;

public class InvoiceSettingFilter extends BaseFilter {
    private Long franchiseCode;
    private String invSettingName;

    public Long getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(Long franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getInvSettingName() {
        return invSettingName;
    }

    public void setInvSettingName(String invSettingName) {
        this.invSettingName = invSettingName;
    }

}

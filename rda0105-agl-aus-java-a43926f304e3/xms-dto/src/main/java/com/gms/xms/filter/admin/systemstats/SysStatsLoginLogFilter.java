package com.gms.xms.filter.admin.systemstats;

import java.util.Date;

/**
 * Posted from Aug 30, 2016 10:49:06 AM
 * <p>
 * Author dattrinh
 */
public class SysStatsLoginLogFilter extends SysStatsFilter {
    private Integer loginType;
    private Date startDate;
    private Date endDate;

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

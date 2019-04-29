package com.gms.xms.txndb.vo.payables.salesrep;

import com.gms.xms.txndb.vo.SalesRepVo;
import com.gms.xms.txndb.vo.UserVo;

import java.util.List;

/**
 * Posted from SalesRepSettingVo
 * <p>
 * Author dattrinh Mar 21, 2016 11:31:35 AM
 */
public class SalesRepSettingVo extends SalesRepVo {

    private static final long serialVersionUID = 1L;

    private UserVo user;
    private List<SalesRepPayoutGoalVo> salesRepServices;
    private Integer active;

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public List<SalesRepPayoutGoalVo> getSalesRepServices() {
        return salesRepServices;
    }

    public void setSalesRepServices(List<SalesRepPayoutGoalVo> salesRepServices) {
        this.salesRepServices = salesRepServices;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}

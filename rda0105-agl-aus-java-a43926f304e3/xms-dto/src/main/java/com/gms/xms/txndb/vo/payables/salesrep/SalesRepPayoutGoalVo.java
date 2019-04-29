package com.gms.xms.txndb.vo.payables.salesrep;

import com.gms.xms.txndb.vo.SalesRepServiceVo;
import com.gms.xms.txndb.vo.ServiceVo;

/**
 * Posted from SalesRepPayoutGoalVo
 * <p>
 * Author dattrinh Mar 21, 2016 11:33:50 AM
 */
public class SalesRepPayoutGoalVo extends SalesRepServiceVo {

    private static final long serialVersionUID = 1L;

    private ServiceVo service;

    public ServiceVo getService() {
        return service;
    }

    public void setService(ServiceVo service) {
        this.service = service;
    }
}

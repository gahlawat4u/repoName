package com.gms.xms.txndb.vo.admin.customer.baserate;

import java.util.List;

/**
 * Posted from Apr 6, 2016 10:02:42 AM
 * <p>
 * Author dattrinh
 */
public class CustBaseRateDetailByFilter {
    private Long customerBaseRateId;
    private List<String> zones;

    public Long getCustomerBaseRateId() {
        return customerBaseRateId;
    }

    public void setCustomerBaseRateId(Long customerBaseRateId) {
        this.customerBaseRateId = customerBaseRateId;
    }

    public List<String> getZones() {
        return zones;
    }

    public void setZones(List<String> zones) {
        this.zones = zones;
    }
}

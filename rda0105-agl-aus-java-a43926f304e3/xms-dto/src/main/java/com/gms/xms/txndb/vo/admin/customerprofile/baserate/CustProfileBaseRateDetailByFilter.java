package com.gms.xms.txndb.vo.admin.customerprofile.baserate;

import java.util.List;

/**
 * Posted from CustBaseRateDetailByFilter
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class CustProfileBaseRateDetailByFilter {
    private Long customerProfileBaseRateId;
    private List<String> zones;

    public Long getCustomerProfileBaseRateId() {
        return customerProfileBaseRateId;
    }

    public void setCustomerProfileBaseRateId(Long customerProfileBaseRateId) {
        this.customerProfileBaseRateId = customerProfileBaseRateId;
    }

    public List<String> getZones() {
        return zones;
    }

    public void setZones(List<String> zones) {
        this.zones = zones;
    }
}

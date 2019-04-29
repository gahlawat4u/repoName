package com.gms.xms.txndb.vo.admin.customerprofile.baserate;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from CustProfileBaseRateDetailVo
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class CustProfileBaseRateDetailVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long customerProfileBaseRateId;
    private String zone;
    private Double rate;

    public Long getCustomerProfileBaseRateId() {
        return customerProfileBaseRateId;
    }

    public void setCustomerProfileBaseRateId(Long customerProfileBaseRateId) {
        this.customerProfileBaseRateId = customerProfileBaseRateId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CustProfileBaseRateDetailVo [customerProfileBaseRateId=" + customerProfileBaseRateId + ", zone=" + zone + ", rate=" + rate + "]";
    }
}

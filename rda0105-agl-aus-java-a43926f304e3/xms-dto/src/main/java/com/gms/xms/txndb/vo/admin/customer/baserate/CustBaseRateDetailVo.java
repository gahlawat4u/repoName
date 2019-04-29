package com.gms.xms.txndb.vo.admin.customer.baserate;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from Apr 4, 2016 11:09:58 PM
 * <p>
 * Author dattrinh
 */
public class CustBaseRateDetailVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long customerBaseRateId;
    private String zone;
    private Double rate;

    public Long getCustomerBaseRateId() {
        return customerBaseRateId;
    }

    public void setCustomerBaseRateId(Long customerBaseRateId) {
        this.customerBaseRateId = customerBaseRateId;
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
        return "CustBaseRateDetailVo [customerBaseRateId=" + customerBaseRateId + ", zone=" + zone + ", rate=" + rate + "]";
    }
}

package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonString2DoubleDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class CustomerBaseRateDetailVo extends BaseVo {
    private static final long serialVersionUID = -4092581708314544399L;

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
        this.zone = zone == null ? null : zone.trim();
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRate() {
        return rate;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CustomerBaseRateDetailVo [customerBaseRateId=" + customerBaseRateId + ", zone=" + zone + ", rate=" + rate + "]";
    }
}
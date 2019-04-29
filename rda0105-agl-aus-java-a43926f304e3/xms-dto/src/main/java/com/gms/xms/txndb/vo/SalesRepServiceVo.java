package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonString2DoubleDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from SalesRepServiceVo
 * <p>
 * Author HoangPH Nov 20, 2015
 */
public class SalesRepServiceVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    private Long salesRepId;
    private Long serviceId;
    private Double goal;
    private Double payout;

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGoal() {
        return goal;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setGoal(Double goal) {
        this.goal = goal;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getPayout() {
        return payout;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setPayout(Double payout) {
        this.payout = payout;
    }

    @Override
    public String toString() {
        return "SalesRepServiceVo [salesRepId=" + salesRepId + ", serviceId=" + serviceId + ", goal=" + goal + ", payout=" + payout + "]";
    }
}
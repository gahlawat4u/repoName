package com.gms.xms.txndb.vo.payables.salesrep;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from Mar 24, 2016 11:28:34 AM
 * <p>
 * Author dattrinh
 */
public class SalesRepServiceStatVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String serviceName;
    private Double goal;
    private Long actualShipments;
    private Double goalPct;
    private Double actualMargin;
    private Double payout;
    private Double payoutAmount;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGoal() {
        return goal;
    }

    public void setGoal(Double goal) {
        this.goal = goal;
    }

    public Long getActualShipments() {
        return actualShipments;
    }

    public void setActualShipments(Long actualShipments) {
        this.actualShipments = actualShipments;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGoalPct() {
        return goalPct;
    }

    public void setGoalPct(Double goalPct) {
        this.goalPct = goalPct;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getActualMargin() {
        return actualMargin;
    }

    public void setActualMargin(Double actualMargin) {
        this.actualMargin = actualMargin;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getPayout() {
        return payout;
    }

    public void setPayout(Double payout) {
        this.payout = payout;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(Double payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    @Override
    public String toString() {
        return "SalesRepServiceStatVo [serviceName=" + serviceName + ", goal=" + goal + ", actualShipments=" + actualShipments + ", goalPct=" + goalPct + ", actualMargin=" + actualMargin + ", payout=" + payout + ", payoutAmount=" + payoutAmount + "]";
    }
}

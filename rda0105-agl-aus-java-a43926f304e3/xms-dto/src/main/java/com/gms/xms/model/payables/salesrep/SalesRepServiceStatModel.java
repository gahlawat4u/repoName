package com.gms.xms.model.payables.salesrep;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Mar 24, 2016 11:29:17 AM
 * <p>
 * Author dattrinh
 */
public class SalesRepServiceStatModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String serviceName;
    private String goal;
    private String actualShipments;
    private String goalPct;
    private String actualMargin;
    private String payout;
    private String payoutAmount;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getActualShipments() {
        return actualShipments;
    }

    public void setActualShipments(String actualShipments) {
        this.actualShipments = actualShipments;
    }

    public String getGoalPct() {
        return goalPct;
    }

    public void setGoalPct(String goalPct) {
        this.goalPct = goalPct;
    }

    public String getActualMargin() {
        return actualMargin;
    }

    public void setActualMargin(String actualMargin) {
        this.actualMargin = actualMargin;
    }

    public String getPayout() {
        return payout;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    public String getPayoutAmount() {
        return payoutAmount;
    }

    public void setPayoutAmount(String payoutAmount) {
        this.payoutAmount = payoutAmount;
    }

    @Override
    public String toString() {
        return "SalesRepServiceStatModel [serviceName=" + serviceName + ", goal=" + goal + ", actualShipments=" + actualShipments + ", goalPct=" + goalPct + ", actualMargin=" + actualMargin + ", payout=" + payout + ", payoutAmount=" + payoutAmount + "]";
    }
}

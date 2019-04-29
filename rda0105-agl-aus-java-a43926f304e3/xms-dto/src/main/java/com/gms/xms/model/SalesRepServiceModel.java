package com.gms.xms.model;

/**
 * Posted from SalesRepServiceModel
 * <p>
 * Author HoangPH Nov 20, 2015
 */
public class SalesRepServiceModel extends BaseModel {
    private static final long serialVersionUID = 1L;

    private String salesRepId;
    private String serviceId;
    private String goal;
    private String payout;

    public String getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(String salesRepId) {
        this.salesRepId = salesRepId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getPayout() {
        return payout;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    @Override
    public String toString() {
        return "SalesRepServiceModel [salesRepId=" + salesRepId + ", serviceId=" + serviceId + ", goal=" + goal + ", payout=" + payout + "]";
    }
}
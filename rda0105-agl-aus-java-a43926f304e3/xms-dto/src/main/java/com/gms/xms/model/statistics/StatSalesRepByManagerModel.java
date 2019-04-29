package com.gms.xms.model.statistics;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Aug 22, 2016 2:17:28 PM
 * <p>
 * Author dattrinh
 */
public class StatSalesRepByManagerModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String userId;
    private String displayName;
    private String opportunities;
    private String amount;
    private String opportunitiesOver;
    private String totalCall;
    private String targetCall;
    private String targetSuccess;
    private String overdueDay;
    private String count;
    private String successRate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(String opportunities) {
        this.opportunities = opportunities;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOpportunitiesOver() {
        return opportunitiesOver;
    }

    public void setOpportunitiesOver(String opportunitiesOver) {
        this.opportunitiesOver = opportunitiesOver;
    }

    public String getTotalCall() {
        return totalCall;
    }

    public void setTotalCall(String totalCall) {
        this.totalCall = totalCall;
    }

    public String getTargetCall() {
        return targetCall;
    }

    public void setTargetCall(String targetCall) {
        this.targetCall = targetCall;
    }

    public String getTargetSuccess() {
        return targetSuccess;
    }

    public void setTargetSuccess(String targetSuccess) {
        this.targetSuccess = targetSuccess;
    }

    public String getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(String overdueDay) {
        this.overdueDay = overdueDay;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    @Override
    public String toString() {
        return "StatSalesRepByManagerModel [userId=" + userId + ", displayName=" + displayName + ", opportunities=" + opportunities + ", amount=" + amount + ", opportunitiesOver=" + opportunitiesOver + ", totalCall=" + totalCall + ", targetCall=" + targetCall + ", targetSuccess=" + targetSuccess + ", overdueDay=" + overdueDay + ", count=" + count + "]";
    }
}

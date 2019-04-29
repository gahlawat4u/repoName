package com.gms.xms.dto.statistics;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from Aug 22, 2016 2:17:28 PM
 * <p>
 * Author dattrinh
 */
public class StatSalesRepByManagerVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String displayName;
    private Long opportunities;
    private Double amount;
    private Long opportunitiesOver;
    private Long totalCall;
    private Long targetCall;
    private Long targetSuccess;
    private Long overdueDay;
    private Long count;
    private Double successRate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(Long opportunities) {
        this.opportunities = opportunities;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getOpportunitiesOver() {
        return opportunitiesOver;
    }

    public void setOpportunitiesOver(Long opportunitiesOver) {
        this.opportunitiesOver = opportunitiesOver;
    }

    public Long getTotalCall() {
        return totalCall;
    }

    public void setTotalCall(Long totalCall) {
        this.totalCall = totalCall;
    }

    public Long getTargetCall() {
        return targetCall;
    }

    public void setTargetCall(Long targetCall) {
        this.targetCall = targetCall;
    }

    public Long getTargetSuccess() {
        return targetSuccess;
    }

    public void setTargetSuccess(Long targetSuccess) {
        this.targetSuccess = targetSuccess;
    }

    public Long getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(Long overdueDay) {
        this.overdueDay = overdueDay;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    @Override
    public String toString() {
        return "StatSalesRepByManagerVo [userId=" + userId + ", displayName=" + displayName + ", opportunities=" + opportunities + ", amount=" + amount + ", opportunitiesOver=" + opportunitiesOver + ", totalCall=" + totalCall + ", targetCall=" + targetCall + ", targetSuccess=" + targetSuccess + ", overdueDay=" + overdueDay + ", count=" + count + "]";
    }
}

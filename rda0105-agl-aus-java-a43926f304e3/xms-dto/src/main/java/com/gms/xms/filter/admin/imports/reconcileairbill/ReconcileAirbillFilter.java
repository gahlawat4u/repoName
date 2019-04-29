package com.gms.xms.filter.admin.imports.reconcileairbill;

import com.gms.xms.filter.BaseFilter;

/**
 * Posted from ReconcileAirbillFilter
 * <p>
 * Author dattrinh Mar 14, 2016 5:46:57 PM
 */
public class ReconcileAirbillFilter extends BaseFilter {
    private String franchiseList;
    private String airbillList;
    private Integer userLevel;

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public String getAirbillList() {
        return airbillList;
    }

    public void setAirbillList(String airbillList) {
        this.airbillList = airbillList;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}

package com.gms.xms.model.admin.invoicing.searchairbill;

import com.gms.xms.model.BaseModel;

/**
 * File Name: TotalSearchAirbillModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 29-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.searchairbill <br/>
 */
public class TotalSearchAirbillModel extends BaseModel {
    private static final long serialVersionUID = 3307146928980986399L;
    private String totalAirbills;
    private String totalCustomerAmount;
    private String totalFranchiseAmount;
    private String totalMargin;

    public String getTotalAirbills() {
        return totalAirbills;
    }

    public void setTotalAirbills(String totalAirbills) {
        this.totalAirbills = totalAirbills;
    }

    public String getTotalCustomerAmount() {
        return totalCustomerAmount;
    }

    public void setTotalCustomerAmount(String totalCustomerAmount) {
        this.totalCustomerAmount = totalCustomerAmount;
    }

    public String getTotalFranchiseAmount() {
        return totalFranchiseAmount;
    }

    public void setTotalFranchiseAmount(String totalFranchiseAmount) {
        this.totalFranchiseAmount = totalFranchiseAmount;
    }

    public String getTotalMargin() {
        return totalMargin;
    }

    public void setTotalMargin(String totalMargin) {
        this.totalMargin = totalMargin;
    }

    @Override
    public String toString() {
        return "TotalSearchAirbillModel [totalAirbills=" + totalAirbills + ", totalCustomerAmount=" + totalCustomerAmount + ", totalFranchiseAmount=" + totalFranchiseAmount + ", totalMargin=" + totalMargin + "]";
    }

}

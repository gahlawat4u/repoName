package com.gms.xms.model.reports.webship;

import com.gms.xms.model.BaseModel;

/**
 * Posted from WebshipCustomerDetailChargeModel
 * <p>
 * Author DatTV Dec 11, 2015
 */
public class WebshipCustomerDetailChargeModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String description;
    private String customerCost;
    private String franchiseCost;
    private String margin;
    private String isBaseCharge;

    @Override
    public String toString() {
        return "WebshipCustomerDetailChargeModel [description=" + description + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", margin=" + margin + ", isBaseCharge=" + isBaseCharge + "]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(String customerCost) {
        this.customerCost = customerCost;
    }

    public String getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(String franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getIsBaseCharge() {
        return isBaseCharge;
    }

    public void setIsBaseCharge(String isBaseCharge) {
        this.isBaseCharge = isBaseCharge;
    }
}

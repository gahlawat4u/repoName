package com.gms.xms.model.webship.invoices;

import com.gms.xms.model.BaseModel;

/**
 * Posted from AirbillChargeModel
 * <p>
 * Author DatTV Date Jul 13, 2015 11:35:40 AM
 */
public class AirbillChargeModel extends BaseModel {

    private static final long serialVersionUID = -5815975760579112718L;

    private String description;
    private String displayDescription;
    private String customerCost;
    private String customerTaxAmount;

    @Override
    public String toString() {
        return "AirbillChargeModel [description=" + description + ", displayDescription=" + displayDescription + ", customerCost=" + customerCost + ", customerTaxAmount=" + customerTaxAmount + "]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    public String getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(String customerCost) {
        this.customerCost = customerCost;
    }

    public String getCustomerTaxAmount() {
        return customerTaxAmount;
    }

    public void setCustomerTaxAmount(String customerTaxAmount) {
        this.customerTaxAmount = customerTaxAmount;
    }
}

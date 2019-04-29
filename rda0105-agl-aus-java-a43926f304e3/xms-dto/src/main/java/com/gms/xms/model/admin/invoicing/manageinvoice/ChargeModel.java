package com.gms.xms.model.admin.invoicing.manageinvoice;

import com.gms.xms.model.BaseModel;

/**
 * @author TANDT
 */
public class ChargeModel extends BaseModel {

    private static final long serialVersionUID = -1;

    private String awbDescription;
    private String awbCustomerCost;
    private String awbCustomerTax;

    public String getAwbDescription() {
        return awbDescription;
    }

    public void setAwbDescription(String awbDescription) {
        this.awbDescription = awbDescription;
    }

    public String getAwbCustomerCost() {
        return awbCustomerCost;
    }

    public void setAwbCustomerCost(String awbCustomerCost) {
        this.awbCustomerCost = awbCustomerCost;
    }

    public String getAwbCustomerTax() {
        return awbCustomerTax;
    }

    public void setAwbCustomerTax(String awbCustomerTax) {
        this.awbCustomerTax = awbCustomerTax;
    }

    @Override
    public String toString() {
        return "ChargeModel [awbDescription=" + awbDescription + ", awbCustomerCost=" + awbCustomerCost + ", awbCustomerTax=" + awbCustomerTax + "]";
    }
}

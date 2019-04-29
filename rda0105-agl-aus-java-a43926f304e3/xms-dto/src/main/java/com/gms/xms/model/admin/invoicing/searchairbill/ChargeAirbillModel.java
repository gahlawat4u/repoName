package com.gms.xms.model.admin.invoicing.searchairbill;

import com.gms.xms.model.BaseModel;

/**
 * File Name: ChargeAirbillModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 21-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.searchairbill <br/>
 */
public class ChargeAirbillModel extends BaseModel {
    private static final long serialVersionUID = -2703435494550163395L;
    private String chargeDescription;
    private String customerCost;
    private String franchiseCost;
    private String carrierCost;
    private String margin;
    private String customerCostWithTax;
    private String carrierCostWithTax;
    private String magrinWithTax;
    private String accessorialCount;
    private String customerTaxAmount;
    private String isBaseCharge;

    public String getChargeDescription() {
        return chargeDescription;
    }

    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
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

    public String getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(String carrierCost) {
        this.carrierCost = carrierCost;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getCustomerCostWithTax() {
        return customerCostWithTax;
    }

    public void setCustomerCostWithTax(String customerCostWithTax) {
        this.customerCostWithTax = customerCostWithTax;
    }

    public String getCarrierCostWithTax() {
        return carrierCostWithTax;
    }

    public void setCarrierCostWithTax(String carrierCostWithTax) {
        this.carrierCostWithTax = carrierCostWithTax;
    }

    public String getMagrinWithTax() {
        return magrinWithTax;
    }

    public void setMagrinWithTax(String magrinWithTax) {
        this.magrinWithTax = magrinWithTax;
    }

    public String getAccessorialCount() {
        return accessorialCount;
    }

    public void setAccessorialCount(String accessorialCount) {
        this.accessorialCount = accessorialCount;
    }

    public String getCustomerTaxAmount() {
        return customerTaxAmount;
    }

    public void setCustomerTaxAmount(String customerTaxAmount) {
        this.customerTaxAmount = customerTaxAmount;
    }

    public String getIsBaseCharge() {
        return isBaseCharge;
    }

    public void setIsBaseCharge(String isBaseCharge) {
        this.isBaseCharge = isBaseCharge;
    }

    @Override
    public String toString() {
        return "ChargeAirbillModel [chargeDescription=" + chargeDescription + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", margin=" + margin + ", customerCostWithTax=" + customerCostWithTax + ", carrierCostWithTax=" + carrierCostWithTax + ", magrinWithTax=" + magrinWithTax + ", accessorialCount=" + accessorialCount + ", customerTaxAmount=" + customerTaxAmount + ", isBaseCharge=" + isBaseCharge + "]";
    }

}

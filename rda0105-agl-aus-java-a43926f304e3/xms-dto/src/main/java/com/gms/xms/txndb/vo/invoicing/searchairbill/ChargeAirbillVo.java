package com.gms.xms.txndb.vo.invoicing.searchairbill;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * File Name: ChargeAirbillVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 21-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing.searchairbill <br/>
 */
public class ChargeAirbillVo extends BaseVo {
    private static final long serialVersionUID = -9197844617154323547L;
    private String chargeDescription;
    private Double customerCost;
    private Double franchiseCost;
    private Double carrierCost;
    private Double margin;
    private Double customerCostWithTax;
    private Double carrierCostWithTax;
    private Double magrinWithTax;
    private Integer accessorialCount;
    private Double customerTaxAmount;
    private Boolean isBaseCharge;

    public String getChargeDescription() {
        return chargeDescription;
    }

    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(Double customerCost) {
        this.customerCost = customerCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(Double franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCost() {
        return carrierCost;
    }

    public void setCarrierCost(Double carrierCost) {
        this.carrierCost = carrierCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerCostWithTax() {
        return customerCostWithTax;
    }

    public void setCustomerCostWithTax(Double customerCostWithTax) {
        this.customerCostWithTax = customerCostWithTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierCostWithTax() {
        return carrierCostWithTax;
    }

    public void setCarrierCostWithTax(Double carrierCostWithTax) {
        this.carrierCostWithTax = carrierCostWithTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMagrinWithTax() {
        return magrinWithTax;
    }

    public void setMagrinWithTax(Double magrinWithTax) {
        this.magrinWithTax = magrinWithTax;
    }

    public Integer getAccessorialCount() {
        return accessorialCount;
    }

    public void setAccessorialCount(Integer accessorialCount) {
        this.accessorialCount = accessorialCount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerTaxAmount() {
        return customerTaxAmount;
    }

    public void setCustomerTaxAmount(Double customerTaxAmount) {
        this.customerTaxAmount = customerTaxAmount;
    }

    public Boolean getIsBaseCharge() {
        return isBaseCharge;
    }

    public void setIsBaseCharge(Boolean isBaseCharge) {
        this.isBaseCharge = isBaseCharge;
    }

    @Override
    public String toString() {
        return "ChargeAirbillVo [chargeDescription=" + chargeDescription + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", margin=" + margin + ", customerCostWithTax=" + customerCostWithTax + ", carrierCostWithTax=" + carrierCostWithTax + ", magrinWithTax=" + magrinWithTax + ", accessorialCount=" + accessorialCount + ", customerTaxAmount=" + customerTaxAmount + ", isBaseCharge=" + isBaseCharge + "]";
    }

}

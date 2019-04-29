package com.gms.xms.model.franchisepayable.sc;

import com.gms.xms.model.BaseModel;

/**
 * Posted from FranchisePayableSCShipmentTotalModel
 * <p>
 * Author DatTV Dec 9, 2015
 */
public class FranchisePayableSCShipmentTotalModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String custCost;
    private String custTax;
    private String custMarginable;
    private String custMarginableTax;
    private String franCost;
    private String franTax;
    private String grossMargin;
    private String grossMarginTax;
    private String franCredit;
    private String custCredit;
    private String managementFee;
    private String marketingFee;
    private String profitShare;
    private String taxableShipmentCount;
    private String nonTaxableShipmentCount;

    @Override
    public String toString() {
        return "FranchisePayableSCShipmentTotalModel [custCost=" + custCost + ", custTax=" + custTax + ", custMarginable=" + custMarginable + ", custMarginableTax=" + custMarginableTax + ", franCost=" + franCost + ", franTax=" + franTax + ", grossMargin=" + grossMargin + ", grossMarginTax=" + grossMarginTax + ", franCredit=" + franCredit + ", custCredit=" + custCredit + ", managementFee=" + managementFee + ", marketingFee=" + marketingFee + ", profitShare=" + profitShare
                + ", taxableShipmentCount=" + taxableShipmentCount + ", nonTaxableShipmentCount=" + nonTaxableShipmentCount + "]";
    }

    public String getCustCost() {
        return custCost;
    }

    public void setCustCost(String custCost) {
        this.custCost = custCost;
    }

    public String getCustTax() {
        return custTax;
    }

    public void setCustTax(String custTax) {
        this.custTax = custTax;
    }

    public String getCustMarginable() {
        return custMarginable;
    }

    public void setCustMarginable(String custMarginable) {
        this.custMarginable = custMarginable;
    }

    public String getCustMarginableTax() {
        return custMarginableTax;
    }

    public void setCustMarginableTax(String custMarginableTax) {
        this.custMarginableTax = custMarginableTax;
    }

    public String getFranCost() {
        return franCost;
    }

    public void setFranCost(String franCost) {
        this.franCost = franCost;
    }

    public String getFranTax() {
        return franTax;
    }

    public void setFranTax(String franTax) {
        this.franTax = franTax;
    }

    public String getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(String grossMargin) {
        this.grossMargin = grossMargin;
    }

    public String getGrossMarginTax() {
        return grossMarginTax;
    }

    public void setGrossMarginTax(String grossMarginTax) {
        this.grossMarginTax = grossMarginTax;
    }

    public String getFranCredit() {
        return franCredit;
    }

    public void setFranCredit(String franCredit) {
        this.franCredit = franCredit;
    }

    public String getCustCredit() {
        return custCredit;
    }

    public void setCustCredit(String custCredit) {
        this.custCredit = custCredit;
    }

    public String getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(String managementFee) {
        this.managementFee = managementFee;
    }

    public String getMarketingFee() {
        return marketingFee;
    }

    public void setMarketingFee(String marketingFee) {
        this.marketingFee = marketingFee;
    }

    public String getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(String profitShare) {
        this.profitShare = profitShare;
    }

    public String getTaxableShipmentCount() {
        return taxableShipmentCount;
    }

    public void setTaxableShipmentCount(String taxableShipmentCount) {
        this.taxableShipmentCount = taxableShipmentCount;
    }

    public String getNonTaxableShipmentCount() {
        return nonTaxableShipmentCount;
    }

    public void setNonTaxableShipmentCount(String nonTaxableShipmentCount) {
        this.nonTaxableShipmentCount = nonTaxableShipmentCount;
    }
}

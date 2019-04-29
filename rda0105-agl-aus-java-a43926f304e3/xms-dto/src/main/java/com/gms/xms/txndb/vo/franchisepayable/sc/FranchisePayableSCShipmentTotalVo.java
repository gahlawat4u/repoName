package com.gms.xms.txndb.vo.franchisepayable.sc;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from FranchisePayableSCShipmentTotalVo
 * <p>
 * Author DatTV Dec 9, 2015
 */
public class FranchisePayableSCShipmentTotalVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Double custCost;
    private Double custTax;
    private Double custMarginable;
    private Double custMarginableTax;
    private Double franCost;
    private Double franTax;
    private Double grossMargin;
    private Double grossMarginTax;
    private Double franCredit;
    private Double custCredit;
    private Double managementFee;
    private Double marketingFee;
    private Double profitShare;
    private Long taxableShipmentCount;
    private Long nonTaxableShipmentCount;

    @Override
    public String toString() {
        return "FranchisePayableSCShipmentTotalVo [custCost=" + custCost + ", custTax=" + custTax + ", custMarginable=" + custMarginable + ", custMarginableTax=" + custMarginableTax + ", franCost=" + franCost + ", franTax=" + franTax + ", grossMargin=" + grossMargin + ", grossMarginTax=" + grossMarginTax + ", franCredit=" + franCredit + ", custCredit=" + custCredit + ", managementFee=" + managementFee + ", marketingFee=" + marketingFee + ", profitShare=" + profitShare + ", taxableShipmentCount="
                + taxableShipmentCount + ", nonTaxableShipmentCount=" + nonTaxableShipmentCount + "]";
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustCost() {
        return custCost;
    }

    public void setCustCost(Double custCost) {
        this.custCost = custCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustTax() {
        return custTax;
    }

    public void setCustTax(Double custTax) {
        this.custTax = custTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustMarginable() {
        return custMarginable;
    }

    public void setCustMarginable(Double custMarginable) {
        this.custMarginable = custMarginable;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustMarginableTax() {
        return custMarginableTax;
    }

    public void setCustMarginableTax(Double custMarginableTax) {
        this.custMarginableTax = custMarginableTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranCost() {
        return franCost;
    }

    public void setFranCost(Double franCost) {
        this.franCost = franCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranTax() {
        return franTax;
    }

    public void setFranTax(Double franTax) {
        this.franTax = franTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(Double grossMargin) {
        this.grossMargin = grossMargin;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGrossMarginTax() {
        return grossMarginTax;
    }

    public void setGrossMarginTax(Double grossMarginTax) {
        this.grossMarginTax = grossMarginTax;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranCredit() {
        return franCredit;
    }

    public void setFranCredit(Double franCredit) {
        this.franCredit = franCredit;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustCredit() {
        return custCredit;
    }

    public void setCustCredit(Double custCredit) {
        this.custCredit = custCredit;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarketingFee() {
        return marketingFee;
    }

    public void setMarketingFee(Double marketingFee) {
        this.marketingFee = marketingFee;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(Double profitShare) {
        this.profitShare = profitShare;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getTaxableShipmentCount() {
        return taxableShipmentCount;
    }

    public void setTaxableShipmentCount(Long taxableShipmentCount) {
        this.taxableShipmentCount = taxableShipmentCount;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getNonTaxableShipmentCount() {
        return nonTaxableShipmentCount;
    }

    public void setNonTaxableShipmentCount(Long nonTaxableShipmentCount) {
        this.nonTaxableShipmentCount = nonTaxableShipmentCount;
    }
}

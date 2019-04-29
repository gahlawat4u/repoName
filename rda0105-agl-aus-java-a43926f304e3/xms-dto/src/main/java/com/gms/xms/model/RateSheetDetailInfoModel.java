package com.gms.xms.model;

/**
 * Posted from Jul 7, 2016 3:23:32 PM
 * <p>
 * Author dattrinh
 */
public class RateSheetDetailInfoModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private RateSheetDetailModel rate;
    private String customerCost;
    private String franchiseCost;
    private String margin;
    private String percent;
    private String minCustomerCost;
    private String minFranchiseCost;
    private String minMargin;
    private String minPercent;
    private String baseCustomerCost;
    private String baseFranchiseCost;
    private String baseMargin;
    private String basePercent;
    private String kgCustomerCost;
    private String kgFranchiseCost;
    private String kgMargin;
    private String kgPercent;

    public RateSheetDetailModel getRate() {
        return rate;
    }

    public void setRate(RateSheetDetailModel rate) {
        this.rate = rate;
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

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getMinCustomerCost() {
        return minCustomerCost;
    }

    public void setMinCustomerCost(String minCustomerCost) {
        this.minCustomerCost = minCustomerCost;
    }

    public String getMinFranchiseCost() {
        return minFranchiseCost;
    }

    public void setMinFranchiseCost(String minFranchiseCost) {
        this.minFranchiseCost = minFranchiseCost;
    }

    public String getMinMargin() {
        return minMargin;
    }

    public void setMinMargin(String minMargin) {
        this.minMargin = minMargin;
    }

    public String getMinPercent() {
        return minPercent;
    }

    public void setMinPercent(String minPercent) {
        this.minPercent = minPercent;
    }

    public String getBaseCustomerCost() {
        return baseCustomerCost;
    }

    public void setBaseCustomerCost(String baseCustomerCost) {
        this.baseCustomerCost = baseCustomerCost;
    }

    public String getBaseFranchiseCost() {
        return baseFranchiseCost;
    }

    public void setBaseFranchiseCost(String baseFranchiseCost) {
        this.baseFranchiseCost = baseFranchiseCost;
    }

    public String getBaseMargin() {
        return baseMargin;
    }

    public void setBaseMargin(String baseMargin) {
        this.baseMargin = baseMargin;
    }

    public String getBasePercent() {
        return basePercent;
    }

    public void setBasePercent(String basePercent) {
        this.basePercent = basePercent;
    }

    public String getKgCustomerCost() {
        return kgCustomerCost;
    }

    public void setKgCustomerCost(String kgCustomerCost) {
        this.kgCustomerCost = kgCustomerCost;
    }

    public String getKgFranchiseCost() {
        return kgFranchiseCost;
    }

    public void setKgFranchiseCost(String kgFranchiseCost) {
        this.kgFranchiseCost = kgFranchiseCost;
    }

    public String getKgMargin() {
        return kgMargin;
    }

    public void setKgMargin(String kgMargin) {
        this.kgMargin = kgMargin;
    }

    public String getKgPercent() {
        return kgPercent;
    }

    public void setKgPercent(String kgPercent) {
        this.kgPercent = kgPercent;
    }
}
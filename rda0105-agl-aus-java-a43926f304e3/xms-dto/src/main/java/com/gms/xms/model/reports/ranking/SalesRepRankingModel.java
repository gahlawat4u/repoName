package com.gms.xms.model.reports.ranking;

import com.gms.xms.model.BaseModel;

/**
 * Posted from SalesRepRankingModel.java
 * <p>
 * Author dattrinh 10:43:39 AM
 */
public class SalesRepRankingModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String rank;
    private String saleRepName;
    private String franchiseCode;
    private String territory;
    private String activateCustomers;
    private String activations;
    private String shipments;
    private String weights;
    private String revenueIncGst;
    private String revenueExcGst;
    private String revenuePerShipIncGst;
    private String revenuePerShipExcGst;
    private String franchiseCostIncGst;
    private String franchiseCostExcGst;
    private String marginIncGst;
    private String marginExcGst;
    private String marginPerShipIncGst;
    private String marginPerShipExcGst;
    private String marginPerWeightIncGst;
    private String marginPerWeightExcGst;

    @Override
    public String toString() {
        return "SalesRepRankingModel [rank=" + rank + ", saleRepName=" + saleRepName + ", franchiseCode=" + franchiseCode + ", territory=" + territory + ", activateCustomers=" + activateCustomers + ", activations=" + activations + ", shipments=" + shipments + ", weights=" + weights + ", revenueIncGst=" + revenueIncGst + ", revenueExcGst=" + revenueExcGst + ", revenuePerShipIncGst=" + revenuePerShipIncGst + ", revenuePerShipExcGst=" + revenuePerShipExcGst + ", franchiseCostIncGst="
                + franchiseCostIncGst + ", franchiseCostExcGst=" + franchiseCostExcGst + ", marginIncGst=" + marginIncGst + ", marginExcGst=" + marginExcGst + ", marginPerShipIncGst=" + marginPerShipIncGst + ", marginPerShipExcGst=" + marginPerShipExcGst + ", marginPerWeightIncGst=" + marginPerWeightIncGst + ", marginPerWeightExcGst=" + marginPerWeightExcGst + "]";
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getActivateCustomers() {
        return activateCustomers;
    }

    public void setActivateCustomers(String activateCustomers) {
        this.activateCustomers = activateCustomers;
    }

    public String getActivations() {
        return activations;
    }

    public void setActivations(String activations) {
        this.activations = activations;
    }

    public String getShipments() {
        return shipments;
    }

    public void setShipments(String shipments) {
        this.shipments = shipments;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public String getRevenueIncGst() {
        return revenueIncGst;
    }

    public void setRevenueIncGst(String revenueIncGst) {
        this.revenueIncGst = revenueIncGst;
    }

    public String getRevenueExcGst() {
        return revenueExcGst;
    }

    public void setRevenueExcGst(String revenueExcGst) {
        this.revenueExcGst = revenueExcGst;
    }

    public String getRevenuePerShipIncGst() {
        return revenuePerShipIncGst;
    }

    public void setRevenuePerShipIncGst(String revenuePerShipIncGst) {
        this.revenuePerShipIncGst = revenuePerShipIncGst;
    }

    public String getRevenuePerShipExcGst() {
        return revenuePerShipExcGst;
    }

    public void setRevenuePerShipExcGst(String revenuePerShipExcGst) {
        this.revenuePerShipExcGst = revenuePerShipExcGst;
    }

    public String getFranchiseCostIncGst() {
        return franchiseCostIncGst;
    }

    public void setFranchiseCostIncGst(String franchiseCostIncGst) {
        this.franchiseCostIncGst = franchiseCostIncGst;
    }

    public String getFranchiseCostExcGst() {
        return franchiseCostExcGst;
    }

    public void setFranchiseCostExcGst(String franchiseCostExcGst) {
        this.franchiseCostExcGst = franchiseCostExcGst;
    }

    public String getMarginIncGst() {
        return marginIncGst;
    }

    public void setMarginIncGst(String marginIncGst) {
        this.marginIncGst = marginIncGst;
    }

    public String getMarginExcGst() {
        return marginExcGst;
    }

    public void setMarginExcGst(String marginExcGst) {
        this.marginExcGst = marginExcGst;
    }

    public String getMarginPerShipIncGst() {
        return marginPerShipIncGst;
    }

    public void setMarginPerShipIncGst(String marginPerShipIncGst) {
        this.marginPerShipIncGst = marginPerShipIncGst;
    }

    public String getMarginPerShipExcGst() {
        return marginPerShipExcGst;
    }

    public void setMarginPerShipExcGst(String marginPerShipExcGst) {
        this.marginPerShipExcGst = marginPerShipExcGst;
    }

    public String getMarginPerWeightIncGst() {
        return marginPerWeightIncGst;
    }

    public void setMarginPerWeightIncGst(String marginPerWeightIncGst) {
        this.marginPerWeightIncGst = marginPerWeightIncGst;
    }

    public String getMarginPerWeightExcGst() {
        return marginPerWeightExcGst;
    }

    public void setMarginPerWeightExcGst(String marginPerWeightExcGst) {
        this.marginPerWeightExcGst = marginPerWeightExcGst;
    }

    public String getSaleRepName() {
        return saleRepName;
    }

    public void setSaleRepName(String saleRepName) {
        this.saleRepName = saleRepName;
    }
}

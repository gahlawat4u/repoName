package com.gms.xms.txndb.vo.reports.ranking;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class SalesRepRankingVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long rank;
    private String saleRepName;
    private String franchiseCode;
    private String territory;
    private Long activateCustomers;
    private Long activations;
    private Long shipments;
    private Double weights;
    private Double revenueIncGst;
    private Double revenueExcGst;
    private Double revenuePerShipIncGst;
    private Double revenuePerShipExcGst;
    private Double franchiseCostIncGst;
    private Double franchiseCostExcGst;
    private Double marginIncGst;
    private Double marginExcGst;
    private Double marginPerShipIncGst;
    private Double marginPerShipExcGst;
    private Double marginPerWeightIncGst;
    private Double marginPerWeightExcGst;

    @Override
    public String toString() {
        return "SalesRepRankingVo [rank=" + rank + ", saleRepName=" + saleRepName + ", franchiseCode=" + franchiseCode + ", territory=" + territory + ", activateCustomers=" + activateCustomers + ", activations=" + activations + ", shipments=" + shipments + ", weights=" + weights + ", revenueIncGst=" + revenueIncGst + ", revenueExcGst=" + revenueExcGst + ", revenuePerShipIncGst=" + revenuePerShipIncGst + ", revenuePerShipExcGst=" + revenuePerShipExcGst + ", franchiseCostIncGst="
                + franchiseCostIncGst + ", franchiseCostExcGst=" + franchiseCostExcGst + ", marginIncGst=" + marginIncGst + ", marginExcGst=" + marginExcGst + ", marginPerShipIncGst=" + marginPerShipIncGst + ", marginPerShipExcGst=" + marginPerShipExcGst + ", marginPerWeightIncGst=" + marginPerWeightIncGst + ", marginPerWeightExcGst=" + marginPerWeightExcGst + "]";
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
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

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getActivateCustomers() {
        return activateCustomers;
    }

    public void setActivateCustomers(Long activateCustomers) {
        this.activateCustomers = activateCustomers;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getActivations() {
        return activations;
    }

    public void setActivations(Long activations) {
        this.activations = activations;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getShipments() {
        return shipments;
    }

    public void setShipments(Long shipments) {
        this.shipments = shipments;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWeights() {
        return weights;
    }

    public void setWeights(Double weights) {
        this.weights = weights;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRevenueIncGst() {
        return revenueIncGst;
    }

    public void setRevenueIncGst(Double revenueIncGst) {
        this.revenueIncGst = revenueIncGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRevenueExcGst() {
        return revenueExcGst;
    }

    public void setRevenueExcGst(Double revenueExcGst) {
        this.revenueExcGst = revenueExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRevenuePerShipIncGst() {
        return revenuePerShipIncGst;
    }

    public void setRevenuePerShipIncGst(Double revenuePerShipIncGst) {
        this.revenuePerShipIncGst = revenuePerShipIncGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getRevenuePerShipExcGst() {
        return revenuePerShipExcGst;
    }

    public void setRevenuePerShipExcGst(Double revenuePerShipExcGst) {
        this.revenuePerShipExcGst = revenuePerShipExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCostIncGst() {
        return franchiseCostIncGst;
    }

    public void setFranchiseCostIncGst(Double franchiseCostIncGst) {
        this.franchiseCostIncGst = franchiseCostIncGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCostExcGst() {
        return franchiseCostExcGst;
    }

    public void setFranchiseCostExcGst(Double franchiseCostExcGst) {
        this.franchiseCostExcGst = franchiseCostExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginIncGst() {
        return marginIncGst;
    }

    public void setMarginIncGst(Double marginIncGst) {
        this.marginIncGst = marginIncGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginExcGst() {
        return marginExcGst;
    }

    public void setMarginExcGst(Double marginExcGst) {
        this.marginExcGst = marginExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginPerShipIncGst() {
        return marginPerShipIncGst;
    }

    public void setMarginPerShipIncGst(Double marginPerShipIncGst) {
        this.marginPerShipIncGst = marginPerShipIncGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginPerShipExcGst() {
        return marginPerShipExcGst;
    }

    public void setMarginPerShipExcGst(Double marginPerShipExcGst) {
        this.marginPerShipExcGst = marginPerShipExcGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginPerWeightIncGst() {
        return marginPerWeightIncGst;
    }

    public void setMarginPerWeightIncGst(Double marginPerWeightIncGst) {
        this.marginPerWeightIncGst = marginPerWeightIncGst;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getMarginPerWeightExcGst() {
        return marginPerWeightExcGst;
    }

    public void setMarginPerWeightExcGst(Double marginPerWeightExcGst) {
        this.marginPerWeightExcGst = marginPerWeightExcGst;
    }

    public String getSaleRepName() {
        return saleRepName;
    }

    public void setSaleRepName(String saleRepName) {
        this.saleRepName = saleRepName;
    }
}

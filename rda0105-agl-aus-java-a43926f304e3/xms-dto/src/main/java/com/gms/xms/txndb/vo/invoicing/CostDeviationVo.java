package com.gms.xms.txndb.vo.invoicing;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from CostDeviationVo
 * <p>
 * Author dattrinh Mar 14, 2016 10:42:14 AM
 */
public class CostDeviationVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long shipmentId;
    private String airbillNumber;
    private Long invoiceId;
    private String invoiceCode;
    private Double calculatedFranchiseCost;
    private Double franchiseCost;
    private Double difference;
    private String serviceName;
    private Double weight;

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCalculatedFranchiseCost() {
        return calculatedFranchiseCost;
    }

    public void setCalculatedFranchiseCost(Double calculatedFranchiseCost) {
        this.calculatedFranchiseCost = calculatedFranchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseCost() {
        return franchiseCost;
    }

    public void setFranchiseCost(Double franchiseCost) {
        this.franchiseCost = franchiseCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CostDeviationVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", calculatedFranchiseCost=" + calculatedFranchiseCost + ", franchiseCost=" + franchiseCost + ", difference=" + difference + ", serviceName=" + serviceName + ", weight=" + weight + "]";
    }
}

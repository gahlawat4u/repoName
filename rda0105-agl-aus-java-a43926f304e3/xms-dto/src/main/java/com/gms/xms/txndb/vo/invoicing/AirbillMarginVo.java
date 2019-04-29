package com.gms.xms.txndb.vo.invoicing;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from AirbillMarginVo
 * <p>
 * Author dattrinh Mar 15, 2016 2:43:11 PM
 */
public class AirbillMarginVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long shipmentId;
    private String airbillNumber;
    private Long invoiceId;
    private String invoiceCode;
    private Double customerCost;
    private Double franchiseCost;
    private Double carrierCost;
    private Double franchiseMargin;
    private Double franchiseMarginPct;
    private String shipmentTypeName;

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
    public Double getFranchiseMargin() {
        return franchiseMargin;
    }

    public void setFranchiseMargin(Double franchiseMargin) {
        this.franchiseMargin = franchiseMargin;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getFranchiseMarginPct() {
        return franchiseMarginPct;
    }

    public void setFranchiseMarginPct(Double franchiseMarginPct) {
        this.franchiseMarginPct = franchiseMarginPct;
    }

    public String getShipmentTypeName() {
        return shipmentTypeName;
    }

    public void setShipmentTypeName(String shipmentTypeName) {
        this.shipmentTypeName = shipmentTypeName;
    }

    @Override
    public String toString() {
        return "AirbillMarginVo [shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", franchiseMargin=" + franchiseMargin + ", franchiseMarginPct=" + franchiseMarginPct + ", shipmentTypeName=" + shipmentTypeName + "]";
    }
}

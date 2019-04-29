package com.gms.xms.txndb.vo.payables.salesrep;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from Mar 25, 2016 9:29:50 AM
 * <p>
 * Author dattrinh
 */
public class SalesRepAirbillStatVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String invoiceCode;
    private String customerCode;
    private String customerName;
    private Long shipmentId;
    private String airbillNumber;
    private Double totalPaid;
    private Double customerCost;
    private Double franchiseCost;
    private Double margin;

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

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

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(Double totalPaid) {
        this.totalPaid = totalPaid;
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
    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    @Override
    public String toString() {
        return "SalesRepAirbillStatVo [invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", customerName=" + customerName + ", shipmentId=" + shipmentId + ", airbillNumber=" + airbillNumber + ", totalPaid=" + totalPaid + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", margin=" + margin + "]";
    }
}

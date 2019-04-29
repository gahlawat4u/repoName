package com.gms.xms.txndb.vo.invoicing;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonLong2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from ImportTotalReportVo
 * <p>
 * Author dattrinh Mar 9, 2016 5:01:38 PM
 */
public class ImportTotalReportVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long invoiceId;
    private String invoiceCode;
    private Double customerCost;
    private Double franchiseCost;
    private Double carrierCost;
    private Double margin;
    private Long airbillCount;

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
    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    @JsonSerialize(using = JsonLong2StringSerializer.class)
    public Long getAirbillCount() {
        return airbillCount;
    }

    public void setAirbillCount(Long airbillCount) {
        this.airbillCount = airbillCount;
    }

    @Override
    public String toString() {
        return "ImportTotalReportVo [invoiceId=" + invoiceId + ", invoiceCode=" + invoiceCode + ", customerCost=" + customerCost + ", franchiseCost=" + franchiseCost + ", carrierCost=" + carrierCost + ", margin=" + margin + ", airbillCount=" + airbillCount + "]";
    }
}

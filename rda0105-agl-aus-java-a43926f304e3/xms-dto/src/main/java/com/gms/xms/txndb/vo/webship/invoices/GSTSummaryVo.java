package com.gms.xms.txndb.vo.webship.invoices;

import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from GSTSummaryVo
 * <p>
 * Author DatTV Date Jul 12, 2015 12:19:22 AM
 */
public class GSTSummaryVo extends BaseVo {

    private static final long serialVersionUID = 7207125189863121049L;

    private Double nonGstCustomerCost;
    private Double gstCustomerCost;
    private Double totalGstAmount;
    private Double customerTaxAmount;
    private Double customerTaxPercent;

    @Override
    public String toString() {
        return "GSTSummaryVo [nonGstCustomerCost=" + nonGstCustomerCost + ", gstCustomerCost=" + gstCustomerCost + ", totalGstAmount=" + totalGstAmount + ", customerTaxAmount=" + customerTaxAmount + ", customerTaxPercent=" + customerTaxPercent + "]";
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getNonGstCustomerCost() {
        return nonGstCustomerCost;
    }

    public void setNonGstCustomerCost(Double nonGstCustomerCost) {
        this.nonGstCustomerCost = nonGstCustomerCost;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getGstCustomerCost() {
        return gstCustomerCost;
    }

    public void setGstCustomerCost(Double gstCustomerCost) {
        this.gstCustomerCost = gstCustomerCost;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getCustomerTaxAmount() {
        return customerTaxAmount;
    }

    public void setCustomerTaxAmount(Double customerTaxAmount) {
        this.customerTaxAmount = customerTaxAmount;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getCustomerTaxPercent() {
        return customerTaxPercent;
    }

    public void setCustomerTaxPercent(Double customerTaxPercent) {
        this.customerTaxPercent = customerTaxPercent;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getTotalGstAmount() {
        return totalGstAmount;
    }

    public void setTotalGstAmount(Double totalGstAmount) {
        this.totalGstAmount = totalGstAmount;
    }
}

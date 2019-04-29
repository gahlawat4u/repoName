package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class CreditNotesGSTSummaryVo extends BaseVo {
    private static final long serialVersionUID = 6977824957264702336L;

    private Double customerAmount;
    private Double gstCustomerAmount;
    private Double customerTaxPercent;
    private Double total;

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(Double customerAmount) {
        this.customerAmount = customerAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getGstCustomerAmount() {
        return gstCustomerAmount;
    }

    public void setGstCustomerAmount(Double gstCustomerAmount) {
        this.gstCustomerAmount = gstCustomerAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerTaxPercent() {
        return customerTaxPercent;
    }

    public void setCustomerTaxPercent(Double customerTaxPercent) {
        this.customerTaxPercent = customerTaxPercent;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CreditNotesGSTSummaryVo [customerAmount=" + customerAmount + ", gstCustomerAmount=" + gstCustomerAmount + ", customerTaxPercent=" + customerTaxPercent + ", total=" + total + "]";
    }
}

package com.gms.xms.txndb.vo.webship.invoices;

import com.gms.xms.common.json.JsonDoubleSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Posted from AirbillChargeVo
 * <p>
 * Author DatTV Date Jul 13, 2015 11:23:30 AM
 */
public class AirbillChargeVo extends BaseVo {

    private static final long serialVersionUID = -3035220686527017158L;

    private String description;
    private String displayDescription;
    private Double customerCost;
    private Double customerTaxAmount;

    @Override
    public String toString() {
        return "AirbillChargeVo [description=" + description + ", displayDescription=" + displayDescription + ", customerCost=" + customerCost + ", customerTaxAmount=" + customerTaxAmount + "]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(Double customerCost) {
        this.customerCost = customerCost;
    }

    @JsonSerialize(using = JsonDoubleSerializer.class)
    public Double getCustomerTaxAmount() {
        return customerTaxAmount;
    }

    public void setCustomerTaxAmount(Double customerTaxAmount) {
        this.customerTaxAmount = customerTaxAmount;
    }
}
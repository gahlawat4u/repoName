package com.gms.xms.txndb.vo.invoicing.manageinvoice;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author TANDT
 */
public class ChargeVo extends BaseVo {

    private static final long serialVersionUID = -1;

    private String awbDescription;
    private Double awbCustomerCost;
    private Double awbCustomerTax;

    public String getAwbDescription() {
        return awbDescription;
    }

    public void setAwbDescription(String awbDescription) {
        this.awbDescription = awbDescription;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAwbCustomerCost() {
        return awbCustomerCost;
    }

    public void setAwbCustomerCost(Double awbCustomerCost) {
        this.awbCustomerCost = awbCustomerCost;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAwbCustomerTax() {
        return awbCustomerTax;
    }

    public void setAwbCustomerTax(Double awbCustomerTax) {
        this.awbCustomerTax = awbCustomerTax;
    }

    @Override
    public String toString() {
        return "ChargeVo [awbDescription=" + awbDescription + ", awbCustomerCost=" + awbCustomerCost + ", awbCustomerTax=" + awbCustomerTax + "]";
    }
}

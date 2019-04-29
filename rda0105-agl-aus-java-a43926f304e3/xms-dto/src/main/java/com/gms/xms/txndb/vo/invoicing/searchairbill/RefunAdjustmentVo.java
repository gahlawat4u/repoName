package com.gms.xms.txndb.vo.invoicing.searchairbill;

import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * File Name: RefunAdjustmentVo.java <br/>
 * Author: TANDT <br/>
 * Create Date: 22-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.txndb.vo.invoicing.searchairbill <br/>
 */
public class RefunAdjustmentVo extends BaseVo {
    private static final long serialVersionUID = 6004244654471025289L;
    private String adjustmentType;
    private Long adjustmentId;
    private Double carrierAmount;
    private Double customerAmount;

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public Long getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(Long adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierAmount() {
        return carrierAmount;
    }

    public void setCarrierAmount(Double carrierAmount) {
        this.carrierAmount = carrierAmount;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(Double customerAmount) {
        this.customerAmount = customerAmount;
    }

    @Override
    public String toString() {
        return "RefunAdjustmentVo [adjustmentType=" + adjustmentType + ", adjustmentId=" + adjustmentId + ", carrierAmount=" + carrierAmount + ", customerAmount=" + customerAmount + "]";
    }

}

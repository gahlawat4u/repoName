package com.gms.xms.model.admin.invoicing.searchairbill;

import com.gms.xms.model.BaseModel;

/**
 * File Name: RefunAdjustmentModel.java <br/>
 * Author: TANDT <br/>
 * Create Date: 22-03-2016 <br/>
 * Project Name: xms-dto <br/>
 * package Name: com.gms.xms.model.admin.invoicing.searchairbill <br/>
 */
public class RefunAdjustmentModel extends BaseModel {
    private static final long serialVersionUID = 6004244654471025289L;
    private String adjustmentType;
    private String adjustmentId;
    private String carrierAmount;
    private String customerAmount;

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getCarrierAmount() {
        return carrierAmount;
    }

    public void setCarrierAmount(String carrierAmount) {
        this.carrierAmount = carrierAmount;
    }

    public String getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(String customerAmount) {
        this.customerAmount = customerAmount;
    }

    @Override
    public String toString() {
        return "RefunAdjustmentModel [adjustmentType=" + adjustmentType + ", adjustmentId=" + adjustmentId + ", carrierAmount=" + carrierAmount + ", customerAmount=" + customerAmount + "]";
    }

}

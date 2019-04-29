package com.gms.xms.model.adjustment;

import com.gms.xms.model.BaseModel;

/**
 * Posted from AirbillAdjustmentTotalModel
 * <p>
 * Author DatTV Date May 13, 2015 11:17:00 AM
 */
public class AirbillAdjustmentTotalModel extends BaseModel {

    private static final long serialVersionUID = 8762956453212250231L;
    private String recordCount;
    private String carrierAmount;
    private String customerAmount;
    private String approvedCarrierAmount;
    private String approvedCustomerAmount;

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
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

    public String getApprovedCarrierAmount() {
        return approvedCarrierAmount;
    }

    public void setApprovedCarrierAmount(String approvedCarrierAmount) {
        this.approvedCarrierAmount = approvedCarrierAmount;
    }

    public String getApprovedCustomerAmount() {
        return approvedCustomerAmount;
    }

    public void setApprovedCustomerAmount(String approvedCustomerAmount) {
        this.approvedCustomerAmount = approvedCustomerAmount;
    }

    @Override
    public String toString() {
        return "AirbillAdjustmentTotalModel [recordCount=" + recordCount + ", carrierAmount=" + carrierAmount + ", customerAmount=" + customerAmount + ", approvedCarrierAmount=" + approvedCarrierAmount + ", approvedCustomerAmount=" + approvedCustomerAmount + "]";
    }
}

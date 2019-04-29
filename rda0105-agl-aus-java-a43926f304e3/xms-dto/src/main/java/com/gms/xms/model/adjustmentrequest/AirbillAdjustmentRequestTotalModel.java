package com.gms.xms.model.adjustmentrequest;

import com.gms.xms.model.BaseModel;

/**
 * Posted from AirbillAdjustmentRequestTotalModel
 * </p>
 *
 * @author hungnt - Nov 4, 2015
 */
public class AirbillAdjustmentRequestTotalModel extends BaseModel {

    private static final long serialVersionUID = 2493450212684810899L;
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

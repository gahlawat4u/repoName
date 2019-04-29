package com.gms.xms.txndb.vo.adjustment;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from AirbillAdjustmentTotalVo
 * <p>
 * Author DatTV Date May 12, 2015 4:12:03 PM
 */
public class AirbillAdjustmentTotalVo extends BaseVo {

    private static final long serialVersionUID = 5934376138362925386L;
    private Integer recordCount;
    private Double carrierAmount;
    private Double customerAmount;
    private Double approvedCarrierAmount;
    private Double approvedCustomerAmount;

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Double getCarrierAmount() {
        return carrierAmount;
    }

    public void setCarrierAmount(Double carrierAmount) {
        this.carrierAmount = carrierAmount;
    }

    public Double getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(Double customerAmount) {
        this.customerAmount = customerAmount;
    }

    public Double getApprovedCarrierAmount() {
        return approvedCarrierAmount;
    }

    public void setApprovedCarrierAmount(Double approvedCarrierAmount) {
        this.approvedCarrierAmount = approvedCarrierAmount;
    }

    public Double getApprovedCustomerAmount() {
        return approvedCustomerAmount;
    }

    public void setApprovedCustomerAmount(Double approvedCustomerAmount) {
        this.approvedCustomerAmount = approvedCustomerAmount;
    }

    @Override
    public String toString() {
        return "AirbillAdjustmentTotalVo [recordCount=" + recordCount + ", carrierAmount=" + carrierAmount + ", customerAmount=" + customerAmount + ", approvedCarrierAmount=" + approvedCarrierAmount + ", approvedCustomerAmount=" + approvedCustomerAmount + "]";
    }
}

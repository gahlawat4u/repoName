package com.gms.xms.txndb.vo.adjustmentrequest;

import com.gms.xms.txndb.vo.BaseVo;

/**
 * Posted from AirbillAdjustmenRequesttTotalVo
 * </p>
 *
 * @author hungnt - Nov 3, 2015
 */
public class AirbillAdjustmentRequestTotalVo extends BaseVo {

    private static final long serialVersionUID = -3688368853310806323L;
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

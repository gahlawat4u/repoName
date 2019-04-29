package com.gms.xms.model.overpayment;

import com.gms.xms.model.BaseModel;

/**
 * Posted from OverpaymentInfoVo
 * <p>
 * Author DatTV Date Apr 23, 2015 11:24:56 AM
 */
public class OverpaymentInfoModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String franchiseCode;
    private String customerCode;
    private String customerName;
    private String totalOverpayment;
    private String status;
    private String source;

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getTotalOverpayment() {
        return totalOverpayment;
    }

    public void setTotalOverpayment(String totalOverpayment) {
        this.totalOverpayment = totalOverpayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OverpaymentInfoModel [franchiseCode=" + franchiseCode + ", customerCode=" + customerCode + ", customerName=" + customerName + ", totalOverpayment=" + totalOverpayment + ", status=" + status + ", source=" + source + "]";
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
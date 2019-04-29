package com.gms.xms.model.admin.systemstats;

import com.gms.xms.model.BaseModel;

/**
 * Posted from Sep 1, 2016 9:14:05 AM
 * <p>
 * Author dattrinh
 */
public class SysStatAdjustmentLogModel extends BaseModel {

    private static final long serialVersionUID = 1L;

    private String adjustmentId;
    private String adjustmentType;
    private String airbillNumber;
    private String customerCode;
    private String userId;
    private String actionDate;
    private String carrierAmt;
    private String customerAmt;
    private String status;
    private String statusName;
    private String userName;
    private String displayName;

    public String getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(String adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActionDate() {
        return actionDate;
    }

    public void setActionDate(String actionDate) {
        this.actionDate = actionDate;
    }

    public String getCarrierAmt() {
        return carrierAmt;
    }

    public void setCarrierAmt(String carrierAmt) {
        this.carrierAmt = carrierAmt;
    }

    public String getCustomerAmt() {
        return customerAmt;
    }

    public void setCustomerAmt(String customerAmt) {
        this.customerAmt = customerAmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "SysStatAdjustmentLogModel [adjustmentId=" + adjustmentId + ", adjustmentType=" + adjustmentType + ", airbillNumber=" + airbillNumber + ", customerCode=" + customerCode + ", userId=" + userId + ", actionDate=" + actionDate + ", carrierAmt=" + carrierAmt + ", customerAmt=" + customerAmt + ", status=" + status + ", statusName=" + statusName + ", userName=" + userName + ", displayName=" + displayName + "]";
    }
}

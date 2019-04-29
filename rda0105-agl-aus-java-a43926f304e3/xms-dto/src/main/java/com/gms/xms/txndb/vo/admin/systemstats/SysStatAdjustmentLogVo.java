package com.gms.xms.txndb.vo.admin.systemstats;

import com.gms.xms.common.json.JsonDateTime2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.txndb.vo.BaseVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from Sep 1, 2016 9:14:05 AM
 * <p>
 * Author dattrinh
 */
public class SysStatAdjustmentLogVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long adjustmentId;
    private String adjustmentType;
    private String airbillNumber;
    private String customerCode;
    private Long userId;
    private Date actionDate;
    private Double carrierAmt;
    private Double customerAmt;
    private Integer status;
    private String statusName;
    private String userName;
    private String displayName;

    public Long getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(Long adjustmentId) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JsonSerialize(using = JsonDateTime2StringSerializer.class)
    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCarrierAmt() {
        return carrierAmt;
    }

    public void setCarrierAmt(Double carrierAmt) {
        this.carrierAmt = carrierAmt;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getCustomerAmt() {
        return customerAmt;
    }

    public void setCustomerAmt(Double customerAmt) {
        this.customerAmt = customerAmt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
        return "SysStatAdjustmentLogVo [adjustmentId=" + adjustmentId + ", adjustmentType=" + adjustmentType + ", airbillNumber=" + airbillNumber + ", customerCode=" + customerCode + ", userId=" + userId + ", actionDate=" + actionDate + ", carrierAmt=" + carrierAmt + ", customerAmt=" + customerAmt + ", status=" + status + ", statusName=" + statusName + ", userName=" + userName + ", displayName=" + displayName + "]";
    }
}

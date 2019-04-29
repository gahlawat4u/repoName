package com.gms.xms.txndb.vo.adjustment;

import com.gms.xms.common.json.JsonDateVo2ModelSerializer;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from AirbillAdjustmentDetailVo
 * <p>
 * Author DatTV Date May 12, 2015 2:24:23 PM
 */
public class AirbillAdjustmentDetailVo extends AirbillAdjustmentVo {

    private static final long serialVersionUID = -1266011113634568570L;
    private String customerCode;
    private Date requestDate;
    private Integer serviceId;
    private String franchiseCode;
    private Integer creditNoteStatus;
    private Date responseDate;

    @Override
    public String toString() {
        return "AirbillAdjustmentDetailVo [customerCode=" + customerCode + ", requestDate=" + requestDate + ", serviceId=" + serviceId + ", franchiseCode=" + franchiseCode + ", creditNoteStatus=" + creditNoteStatus + ", responseDate=" + responseDate + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public Integer getCreditNoteStatus() {
        return creditNoteStatus;
    }

    public void setCreditNoteStatus(Integer creditNoteStatus) {
        this.creditNoteStatus = creditNoteStatus;
    }

    @JsonSerialize(using = JsonDateVo2ModelSerializer.class)
    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
}

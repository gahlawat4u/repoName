package com.gms.xms.model.adjustmentrequest;

import com.gms.xms.common.constants.AppConstants;

/**
 * Posted from AirbillAdjustmentRequestDetailModel
 * </p>
 *
 * @author hungnt - Nov 2, 2015
 */
public class AirbillAdjustmentRequestDetailModel extends AirbillAdjustmentRequestModel {
    private static final long serialVersionUID = -7820307827797595380L;
    private String customerCode;
    private String requestDate;
    private String serviceId;
    private String franchiseCode;
    private String creditNoteStatus;
    private String responseDate;

    @Override
    public String toString() {
        return "AirbillAdjustmentDetailModel [customerCode=" + customerCode + ", requestDate=" + requestDate + ", serviceId=" + serviceId + ", franchiseCode=" + franchiseCode + ", creditNoteStatus=" + creditNoteStatus + ", responseDate=" + responseDate + "]";
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getStatusName() {
        // If status is 1 - Submitted then check it's sub status
        if ("1".equalsIgnoreCase(this.getStatus())) {
            String subState = this.getSubStatus() == null ? "0" : this.getSubStatus();
            switch (subState) {
                case "1":
                    return AppConstants.ADJUSTMENT_SUBSTATUS_REQUEST_FRANCHISE_COMMENT;
                case "2":
                    return AppConstants.ADJUSTMENT_SUBSTATUS_UPDATE_TO_FSC;
            }
        }

        return AppConstants.ADJUSTMENT_STATUS_MAP.get(this.getStatus());
    }

    public String getCreditNoteStatus() {
        return creditNoteStatus;
    }

    public void setCreditNoteStatus(String creditNoteStatus) {
        this.creditNoteStatus = creditNoteStatus;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }
}

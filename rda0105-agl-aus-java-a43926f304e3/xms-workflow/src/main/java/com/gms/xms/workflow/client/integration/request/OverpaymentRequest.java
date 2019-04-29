package com.gms.xms.workflow.client.integration.request;

/**
 * Posted from OverpaymentRequest
 * <p>
 * Author DatTV Date Apr 14, 2015 10:13:14 AM
 */
public class OverpaymentRequest extends BaseRequest {
    private long customerCode;

    public long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(long customerCode) {
        this.customerCode = customerCode;
    }
}

package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoFilter;

/**
 * Posted from InvoiceOverpaymentRequest
 * <p>
 * Author DatTV Date Apr 27, 2015 10:50:20 AM
 */
public class InvoiceOverpaymentRequest extends BaseRequest {
    private OverpaymentInfoFilter filter;

    public OverpaymentInfoFilter getFilter() {
        return filter;
    }

    public void setFilter(OverpaymentInfoFilter filter) {
        this.filter = filter;
    }
}

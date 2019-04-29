package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.BankFilter;

/**
 * Posted from BankRequest
 * <p>
 * Author DatTV Date Apr 9, 2015 1:57:11 PM
 */
public class BankRequest extends BaseRequest {
    private BankFilter filter;

    public BankFilter getFilter() {
        return filter;
    }

    public void setFilter(BankFilter filter) {
        this.filter = filter;
    }
}

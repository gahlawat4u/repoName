package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.CustomerAddressFilter;

/**
 * Posted from CustomerAddressRequest
 * <p>
 * Author DatTV Date Apr 9, 2015 1:57:22 PM
 */
public class CustomerAddressRequest extends BaseRequest {
    private CustomerAddressFilter filter;

    public CustomerAddressFilter getFilter() {
        return filter;
    }

    public void setFilter(CustomerAddressFilter filter) {
        this.filter = filter;
    }

}

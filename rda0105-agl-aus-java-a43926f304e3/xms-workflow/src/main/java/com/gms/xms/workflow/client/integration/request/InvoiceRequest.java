package com.gms.xms.workflow.client.integration.request;

import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceVo;

/**
 * Posted from InvoiceRequest
 * <p>
 * Author DatTV Date Apr 9, 2015 10:32:37 AM
 */
public class InvoiceRequest extends BaseRequest {
    private InvoiceFilter filter;
    private InvoiceVo invoice;

    public InvoiceFilter getFilter() {
        return filter;
    }

    public void setFilter(InvoiceFilter filter) {
        this.filter = filter;
    }

    public InvoiceVo getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceVo invoice) {
        this.invoice = invoice;
    }

}

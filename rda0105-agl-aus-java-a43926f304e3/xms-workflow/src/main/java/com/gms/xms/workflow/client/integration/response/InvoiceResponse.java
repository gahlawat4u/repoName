package com.gms.xms.workflow.client.integration.response;

import com.gms.xms.txndb.vo.InvoiceVo;

import java.util.List;

/**
 * Posted from InvoiceResponse
 * <p>
 * Author DatTV Date Apr 9, 2015 10:32:58 AM
 */
public class InvoiceResponse extends BaseResponse {
    private List<InvoiceVo> invoiceList;
    private InvoiceVo invoice;

    public List<InvoiceVo> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceVo> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public InvoiceVo getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceVo invoice) {
        this.invoice = invoice;
    }
}

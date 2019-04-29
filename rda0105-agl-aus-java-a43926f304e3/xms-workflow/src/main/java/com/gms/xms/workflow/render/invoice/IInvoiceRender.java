package com.gms.xms.workflow.render.invoice;

import com.gms.xms.txndb.vo.InvoiceFilter;

/**
 * Posted from IInvoiceRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface IInvoiceRender {
    public void renderWebshipInvoiceHtmlReport(InvoiceFilter filter, String outputFilePath) throws Exception;
}

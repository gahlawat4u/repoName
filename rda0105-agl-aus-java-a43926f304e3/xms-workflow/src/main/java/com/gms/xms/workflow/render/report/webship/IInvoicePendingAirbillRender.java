package com.gms.xms.workflow.render.report.webship;

import com.gms.xms.filter.reports.webship.InvoicePendingAirbillFilter;

/**
 * Posted from IInvoicePendingAirbillRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface IInvoicePendingAirbillRender {
    public void renderInvoicePendingAirbillHtmlFile(InvoicePendingAirbillFilter filter, String outputFilePath, String realPath) throws Exception;

    public void renderInvoicePendingAirbillXlsFile(InvoicePendingAirbillFilter filter, String outPutFilePath) throws Exception;
}

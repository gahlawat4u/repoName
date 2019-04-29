package com.gms.xms.workflow.render.report.customer.invoicedetail;

import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;

/**
 * Posted from ICustomerInvoiceDetailRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface ICustomerInvoiceDetailRender {
    public void renderCustomerInvoiceDetailHtmlFile(CustomerInvoiceDetailFilter filter, String outputFilePath, String realPath) throws Exception;

    public void renderCustomerInvoiceDetailXlsFile(CustomerInvoiceDetailFilter filter, String outPutFilePath) throws Exception;

    public void renderCustomerCreditNoteDetailHtmlFile(CustomerCreditNoteDetailFilter filter, String outputFilePath, String realPath) throws Exception;

    public void renderCustomerCreditNoteDetailXlsFile(CustomerCreditNoteDetailFilter filter, String outPutFilePath) throws Exception;
}

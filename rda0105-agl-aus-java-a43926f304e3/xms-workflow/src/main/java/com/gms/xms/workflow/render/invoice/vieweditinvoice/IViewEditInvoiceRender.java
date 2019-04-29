package com.gms.xms.workflow.render.invoice.vieweditinvoice;

import com.gms.xms.common.context.ContextBase2;

public interface IViewEditInvoiceRender {
    public void generateHtmlViewPdfFile(ContextBase2 context, Boolean showPayments) throws Exception;

    public void generateHtmlViewPrintableFile(ContextBase2 context, Boolean showPayments) throws Exception;
}

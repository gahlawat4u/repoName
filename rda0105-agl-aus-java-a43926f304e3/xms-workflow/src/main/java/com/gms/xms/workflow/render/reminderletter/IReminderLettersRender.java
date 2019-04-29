package com.gms.xms.workflow.render.reminderletter;

import com.gms.xms.filter.invoicing.StatementInvoiceFilter;

public interface IReminderLettersRender {
    public void generateHtmlPrintInvoice(String customerCode, StatementInvoiceFilter filter, String currentDateStr, String realPath, String printLetter, String outputFilePath) throws Exception;
}

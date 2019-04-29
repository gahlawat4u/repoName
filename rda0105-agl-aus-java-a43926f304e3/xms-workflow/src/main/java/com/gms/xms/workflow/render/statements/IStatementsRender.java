package com.gms.xms.workflow.render.statements;

import com.gms.xms.filter.invoicing.StatementInvoiceFilter;

public interface IStatementsRender {
    public void generateHtmlForPdfFile(String customerCode, StatementInvoiceFilter filter, String currentDateStr, String outputFilePath) throws Exception;

    public void generateHtmlFile(String customerCode, StatementInvoiceFilter filter, String currentDateStr, String realPath, String outputFilePath) throws Exception;
}

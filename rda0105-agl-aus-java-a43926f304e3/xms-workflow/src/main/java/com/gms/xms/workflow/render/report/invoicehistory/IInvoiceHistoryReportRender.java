package com.gms.xms.workflow.render.report.invoicehistory;

import com.gms.xms.txndb.model.reports.selfinsurance.InvoicedAirbillModel;
import com.gms.xms.txndb.model.reports.selfinsurance.SummaryInvoicedAirbillModel;
import com.gms.xms.txndb.vo.reports.selfinsurance.InvoiceHistoryColumnFlagsVo;

import java.util.List;

public interface IInvoiceHistoryReportRender {
    public void generateHTML(List<InvoicedAirbillModel> invoicedAirbillModels, SummaryInvoicedAirbillModel summary, InvoiceHistoryColumnFlagsVo columnFlags, String realPath, String htmlFilePath);

    public void generatePDF(List<InvoicedAirbillModel> invoicedAirbillModels, SummaryInvoicedAirbillModel summary, InvoiceHistoryColumnFlagsVo columnFlags, String htmlFilePath, String pdfFilePath) throws Exception;

    public void generateXLS(List<InvoicedAirbillModel> invoicedAirbillModels, SummaryInvoicedAirbillModel summary, InvoiceHistoryColumnFlagsVo columnFlags, String outputFilePath);
}

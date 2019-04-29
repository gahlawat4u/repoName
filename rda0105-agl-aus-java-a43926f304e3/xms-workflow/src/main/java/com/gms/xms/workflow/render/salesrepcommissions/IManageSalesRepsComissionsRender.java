package com.gms.xms.workflow.render.salesrepcommissions;

import com.gms.xms.filter.admin.payables.salesrep.SalesRepReportFilter;
import com.gms.xms.model.payables.salesrep.SalesRepAirbillStatModel;

import java.util.List;

public interface IManageSalesRepsComissionsRender {
    public void generateHtmlFile(SalesRepReportFilter overviewFilter, SalesRepReportFilter serviceFilter, SalesRepReportFilter invoiceFilter, String outputFilePath, String realPath) throws Exception;

    public void generateHtmlViewPdfFile(SalesRepReportFilter overviewFilter, SalesRepReportFilter serviceFilter, SalesRepReportFilter invoiceFilter, String outputFilePath) throws Exception;

    public void generateDetailsHtml(List<SalesRepAirbillStatModel> airbillStatModels, String outputFilePath, String realPath);

    public void generateDetailsXls(List<SalesRepAirbillStatModel> airbillStatModels, String outputFilePath);
}

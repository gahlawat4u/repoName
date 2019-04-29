package com.gms.xms.workflow.render.importtotals;

import com.gms.xms.filter.invoicing.ImportTotalReportFilter;

public interface IImportTotalsReportRender {
    public void generateHtmlFile(ImportTotalReportFilter filter, String outputFilePath) throws Exception;

    public void generateXlsFile(ImportTotalReportFilter filter, String outputFilePath) throws Exception;

}

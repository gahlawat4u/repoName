package com.gms.xms.workflow.render.report.webship;

import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;

import java.util.List;

/**
 * Posted from ICustomerHistoryRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface IWebshipCustomerHistoryRender {
    public void renderWebshipCustomerHistoryHtmlFile(WebshipCustomerHistoryFilter filter, List<String> columns, List<String> displayColumns, String outputFilePath, String realPath) throws Exception;

    public void renderWebshipCustomerHistoryXlsFile(WebshipCustomerHistoryFilter filter, List<String> columns, List<String> displayColumns, String outPutFilePath) throws Exception;
}

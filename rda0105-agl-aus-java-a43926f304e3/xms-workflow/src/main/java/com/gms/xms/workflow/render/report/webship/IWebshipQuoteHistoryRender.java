package com.gms.xms.workflow.render.report.webship;

import com.gms.xms.filter.reports.webship.WebshipQuoteHistoryFilter;

/**
 * Posted from IWebshipQuoteHistoryRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface IWebshipQuoteHistoryRender {
    public void renderWebshipQuoteHistoryHtmlFile(WebshipQuoteHistoryFilter filter, String outputFilePath, String realPath) throws Exception;

    public void renderWebshipQuoteHistoryXlsFile(WebshipQuoteHistoryFilter filter, String outPutFilePath) throws Exception;
}

package com.gms.xms.workflow.render.report.webship;

import com.gms.xms.filter.reports.webship.WebshipCustomerDetailFilter;

/**
 * Posted from IWebshipCustomerDetailRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface IWebshipCustomerDetailRender {
    public void renderWebshipCustomerDetailHtmlFile(WebshipCustomerDetailFilter filter, String outputFilePath, String realPath) throws Exception;

    public void renderWebshipCustomerDetailXlsFile(WebshipCustomerDetailFilter filter, String outPutFilePath) throws Exception;
}

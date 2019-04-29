package com.gms.xms.workflow.render.report.customer.inactivity;

import com.gms.xms.filter.reports.customer.inactivity.CustomerInactivityFilter;

/**
 * Posted from ICustomerInactivityRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface ICustomerInactivityRender {
    public void renderCustomerInactivityHtmlFile(CustomerInactivityFilter filter, String outputFilePath, String realPath) throws Exception;

    public void renderCustomerInactivityXlsFile(CustomerInactivityFilter filter, String outPutFilePath) throws Exception;
}

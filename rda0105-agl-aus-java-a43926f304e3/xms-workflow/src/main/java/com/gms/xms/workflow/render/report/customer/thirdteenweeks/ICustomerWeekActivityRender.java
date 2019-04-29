package com.gms.xms.workflow.render.report.customer.thirdteenweeks;

import com.gms.xms.filter.reports.customer.thirdteenweeks.CustomerWeekActivityFilter;

/**
 * Posted from ICustomerWeekActivityRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface ICustomerWeekActivityRender {
    public void renderCustomerWeekActivityHtmlFile(CustomerWeekActivityFilter filter, String outputFilePath, String realPath) throws Exception;

    public void renderCustomerWeekActivityXlsFile(CustomerWeekActivityFilter filter, String outPutFilePath) throws Exception;
}

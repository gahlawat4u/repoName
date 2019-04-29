package com.gms.xms.workflow.render.report.customer.activation;

import com.gms.xms.filter.reports.customer.activation.CustomerActivationFilter;

/**
 * Posted from ICustomerActivationRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface ICustomerActivationRender {
    public void renderCustomerActivationHtmlFile(CustomerActivationFilter filter, String outputFilePath, String realPath) throws Exception;

    public void renderCustomerActivationXlsFile(CustomerActivationFilter filter, String outPutFilePath) throws Exception;
}

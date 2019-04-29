package com.gms.xms.workflow.render.customersummary;

import com.gms.xms.txndb.vo.reports.customer.status.CustomerStatusColumn;

import java.util.List;
import java.util.Map;

/**
 * Posted from ICustomerSummaryRender
 * <p>
 * Author DatTV Dec 25, 2015
 */
public interface ICustomerSummaryRender {
    public void renderXLS(List<CustomerStatusColumn> columns, List<Map<String, Object>> summary, Map<String, Object> summaryTotal, String outPutFilePath);

    public void renderHTML(List<CustomerStatusColumn> columns, List<Map<String, String>> summary, Map<String, String> summaryTotal, String outPutFilePath);
}

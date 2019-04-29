package com.gms.xms.workflow.service.report.webship;

import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from IWebshipCustomerHistoryService.java
 * <p>
 * Author dattrinh 2:28:54 PM
 */
public interface IWebshipCustomerHistoryService {
    public List<Map<String, String>> getWebshipCustomerHistoryReport(WebshipCustomerHistoryFilter filter) throws Exception;

    public long getWebshipCustomerHistoryCount(WebshipCustomerHistoryFilter filter) throws Exception;
}

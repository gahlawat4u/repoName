package com.gms.xms.workflow.service.report.customer.status;

import com.gms.xms.filter.reports.customer.status.CustomerStatusFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from ICustomerStatusService
 * <p>
 * Author DatTV Nov 6, 2015
 */
public interface ICustomerStatusService {
    public List<Map<String, String>> getWeeklyReport(CustomerStatusFilter filter) throws Exception;

    public List<Map<String, String>> getMonthlyReport(CustomerStatusFilter filter) throws Exception;

    public Map<String, String> sumWeeklyReport(CustomerStatusFilter filter) throws Exception;

    public Map<String, String> sumMonthlyReport(CustomerStatusFilter filter) throws Exception;
}

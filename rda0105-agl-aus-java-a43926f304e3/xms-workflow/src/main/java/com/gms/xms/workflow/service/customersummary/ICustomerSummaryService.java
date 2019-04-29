package com.gms.xms.workflow.service.customersummary;

import com.gms.xms.txndb.vo.reports.customer.CustomerSummaryFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from ICustomerSummaryService
 * <p>
 * Author DatTV Sep 15, 2015
 */
public interface ICustomerSummaryService {
    public List<Map<String, String>> selectByFilter(CustomerSummaryFilter filter) throws Exception;

    public long countByFilter(CustomerSummaryFilter filter) throws Exception;

    public Map<String, String> sumByFilter(CustomerSummaryFilter filter) throws Exception;
}

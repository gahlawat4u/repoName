package com.gms.xms.workflow.task2.report.selfinsurance;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.reports.selfinsurance.InvoicedAirbillFilter;
import com.gms.xms.persistence.dao.report.selfinsurance.SelfInsuranceDao;
import com.gms.xms.workflow.core2.Task2;

import java.util.Map;

/**
 * Posted from May 23, 2016 2:32:17 PM
 * <p>
 * Author dattrinh
 */
public class PrepareData4InvoicedAirbillReportTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get caching status.
            String hasCache = context.get(Attributes.HAS_CACHE);
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            // Prepare data 4 invoiced airbill report if it has not caching.
            if (!AppConstants.YES_FLAG.equalsIgnoreCase(hasCache)) {
                // Get filter.
                InvoicedAirbillFilter filter = context.get(Attributes.INVOICED_AIRBILL_REPORT_FILTER);
                SelfInsuranceDao dao = new SelfInsuranceDao();
                dao.prepareInvoicedAirbillReport(addInfo, filter);
                // Get record count.
                Long count = dao.countInvoicedAirbillReport(filter);
                context.put(Attributes.INVOICED_AIRBILL_REPORT_RECORD_COUNT, count);
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

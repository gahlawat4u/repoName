package com.gms.xms.workflow.task2.report.selfinsurance;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.reports.selfinsurance.InvoicedAirbillFilter;
import com.gms.xms.persistence.dao.report.selfinsurance.SelfInsuranceDao;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;

import java.util.UUID;

/**
 * Posted from May 23, 2016 2:32:17 PM
 * <p>
 * Author dattrinh
 */
public class CheckInvoicedAirbillReportTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get filter.
            InvoicedAirbillFilter filter = context.get(Attributes.INVOICED_AIRBILL_REPORT_FILTER);
            if (StringUtils.isBlank(filter.getRptTxnId())) {
                context.put(Attributes.HAS_CACHE, AppConstants.NO_FLAG);
                // Update new rpt txn id.
                filter.setRptTxnId(UUID.randomUUID().toString());
                context.put(Attributes.INVOICED_AIRBILL_REPORT_FILTER, filter);
            } else {
                // Determine invoiced airbill report caching.
                SelfInsuranceDao dao = new SelfInsuranceDao();
                Long count = dao.countInvoicedAirbillReport(filter);
                // Put into the context.
                if (count > 0) {
                    context.put(Attributes.HAS_CACHE, AppConstants.YES_FLAG);
                } else {
                    context.put(Attributes.HAS_CACHE, AppConstants.NO_FLAG);
                }
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

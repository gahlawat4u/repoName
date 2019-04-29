package com.gms.xms.workflow.task.report.customer.summary;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.reports.customer.CustomerSummaryDao;
import com.gms.xms.txndb.vo.reports.customer.CustomerSummaryFilter;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from CountCustomerSummaryByFilterTask
 * <p>
 * Author DatTV Sep 15, 2015
 */
public class CountCustomerSummaryByFilterTask implements Task {
    private static final Log log = LogFactory.getLog(CountCustomerSummaryByFilterTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerSummaryDao summaryDao = new CustomerSummaryDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerSummaryFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_SUMMARY_FILTER), CustomerSummaryFilter.class);
        try {
            // Do DAO service to get record's count of customer summary report.
            long count = summaryDao.countByFilter(filter);
            // Puts result into the context
            context.put(Attributes.CUSTOMER_SUMMARY_RECORD_COUNT_RESULT, String.valueOf(count));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }
}
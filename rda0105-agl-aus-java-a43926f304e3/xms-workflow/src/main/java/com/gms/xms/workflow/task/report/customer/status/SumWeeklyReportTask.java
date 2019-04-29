package com.gms.xms.workflow.task.report.customer.status;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.status.CustomerStatusFilter;
import com.gms.xms.persistence.dao.report.CustomerStatusDao;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from SumWeeklyReportTask
 * <p>
 * Author DatTV Dec 17, 2015
 */
public class SumWeeklyReportTask implements Task {
    private static final Log log = LogFactory.getLog(SumWeeklyReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerStatusDao statusDao = new CustomerStatusDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerStatusFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_STATUS_FILTER), CustomerStatusFilter.class);
        try {
            // Do DAO service to get summary for customer status report.
            Map<String, String> result = statusDao.sumWeeklyReport(filter);
            // Puts result into the context
            context.put(Attributes.CUSTOMER_STATUS_WEEKLY_TOTAL_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
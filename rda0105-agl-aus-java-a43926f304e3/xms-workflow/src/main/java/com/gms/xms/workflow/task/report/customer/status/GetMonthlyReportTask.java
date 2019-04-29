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

import java.util.List;
import java.util.Map;

/**
 * Posted from GetMonthlyReportTask
 * <p>
 * Author DatTV Nov 6, 2015
 */
public class GetMonthlyReportTask implements Task {
    private static final Log log = LogFactory.getLog(GetMonthlyReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerStatusDao statusDao = new CustomerStatusDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerStatusFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_STATUS_FILTER), CustomerStatusFilter.class);
        try {
            // Do DAO service to get information for customer status report.
            List<Map<String, String>> result = statusDao.getMonthlyReport(filter);
            // Puts result into the context
            context.put(Attributes.CUSTOMER_STATUS_MONTHLY_REPORT_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
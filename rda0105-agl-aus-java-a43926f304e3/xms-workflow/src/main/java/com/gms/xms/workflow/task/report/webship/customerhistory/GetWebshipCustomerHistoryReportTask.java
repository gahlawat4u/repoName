package com.gms.xms.workflow.task.report.webship.customerhistory;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;
import com.gms.xms.persistence.dao.report.webship.WebshipCustomerHistoryDao;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from GetWebshipCustomerHistoryReportTask.java
 * <p>
 * Author dattrinh 2:14:44 PM
 */
public class GetWebshipCustomerHistoryReportTask implements Task {
    private static final Log log = LogFactory.getLog(GetWebshipCustomerHistoryReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        WebshipCustomerHistoryDao dao = new WebshipCustomerHistoryDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        WebshipCustomerHistoryFilter filter = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_CUSTOMER_HISTORY_FILTER), WebshipCustomerHistoryFilter.class);
        try {
            // Do DAO service to get information for the webship customer history report.
            List<Map<String, String>> result = dao.getWebshipCustomerHistoryReport(filter);
            // Puts result into the context
            context.put(Attributes.WEBSHIP_CUSTOMER_HISTORY_REPORT_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
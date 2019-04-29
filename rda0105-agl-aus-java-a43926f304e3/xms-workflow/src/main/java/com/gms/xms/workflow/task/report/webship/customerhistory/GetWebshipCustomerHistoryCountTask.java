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

/**
 * Posted from GetWebshipCustomerHistoryCountTask.java
 * <p>
 * Author dattrinh 2:19:00 PM
 */
public class GetWebshipCustomerHistoryCountTask implements Task {
    private static final Log log = LogFactory.getLog(GetWebshipCustomerHistoryCountTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        WebshipCustomerHistoryDao dao = new WebshipCustomerHistoryDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        WebshipCustomerHistoryFilter filter = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_CUSTOMER_HISTORY_FILTER), WebshipCustomerHistoryFilter.class);
        try {
            // Do DAO service to record count of the webship customer history report.
            Long recordCount = dao.countWebshipCustomerHistoryReport(filter);

            // Puts result into the context
            context.put(Attributes.WEBSHIP_CUSTOMER_HISTORY_RECORD_COUNT_RESULT, String.valueOf(recordCount));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
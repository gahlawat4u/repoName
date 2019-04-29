package com.gms.xms.workflow.task.report.webship.customerhistory;

import com.gms.xms.common.constants.AppConstants;
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
 * Posted from DetermineWebshipCustomerHistoryCacheTask.java
 * <p>
 * Author dattrinh 2:09:07 PM
 */
public class DetermineWebshipCustomerHistoryCacheTask implements Task {
    private static final Log log = LogFactory.getLog(DetermineWebshipCustomerHistoryCacheTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        WebshipCustomerHistoryDao dao = new WebshipCustomerHistoryDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        WebshipCustomerHistoryFilter filter = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_CUSTOMER_HISTORY_FILTER), WebshipCustomerHistoryFilter.class);
        try {
            // Check if report data was prepared or not?
            if (dao.checkWebshipCustomerHistoryReport(filter)) {
                context.put(Attributes.WEBSHIP_CUSTOMER_HISTORY_HAS_CACHE, AppConstants.YES_FLAG);
            } else {
                context.put(Attributes.WEBSHIP_CUSTOMER_HISTORY_HAS_CACHE, AppConstants.NO_FLAG);
            }
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
package com.gms.xms.workflow.task.report.webship.customerhistory;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.webship.WebshipCustomerHistoryFilter;
import com.gms.xms.persistence.dao.report.webship.WebshipCustomerHistoryDao;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from PrepareWebshipCustomerHistoryPeriodTask.java
 * <p>
 * Author dattrinh 1:54:33 PM
 */
public class PrepareWebshipCustomerHistoryPeriodTask implements Task {
    private static final Log log = LogFactory.getLog(PrepareWebshipCustomerHistoryPeriodTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        WebshipCustomerHistoryDao dao = new WebshipCustomerHistoryDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        WebshipCustomerHistoryFilter filter = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_CUSTOMER_HISTORY_FILTER), WebshipCustomerHistoryFilter.class);
        String hasCache = context.get(Attributes.WEBSHIP_CUSTOMER_HISTORY_HAS_CACHE);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            // Check if report data was prepared or not?
            if (AppConstants.NO_FLAG.equalsIgnoreCase(hasCache)) {
                dao.prepareWebshipCustomerHistoryPeriod(addInfo, filter);
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
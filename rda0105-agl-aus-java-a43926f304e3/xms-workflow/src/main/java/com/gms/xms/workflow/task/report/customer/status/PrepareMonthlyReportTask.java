package com.gms.xms.workflow.task.report.customer.status;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.status.CustomerStatusFilter;
import com.gms.xms.persistence.dao.report.CustomerStatusDao;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from PrepareMonthlyReportTask
 * <p>
 * Author DatTV Nov 6, 2015
 */
public class PrepareMonthlyReportTask implements Task {
    private static final Log log = LogFactory.getLog(PrepareMonthlyReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerStatusDao statusDao = new CustomerStatusDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerStatusFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_STATUS_FILTER), CustomerStatusFilter.class);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            // Check if report data was prepared or not?
            long count = statusDao.checkMonthlyReport(filter);
            // Prepare data for the report if it wasn't prepared
            if (count <= 0) {
                statusDao.prepareMonthlyReport(addInfo, filter);
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
package com.gms.xms.workflow.task.report.customer.activation;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.activation.CustomerActivationFilter;
import com.gms.xms.persistence.dao.report.CustomerActivationDao;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from PrepareActivationReportTask.java
 * <p>
 * Author dattrinh 11:30:35 AM
 */
public class PrepareActivationReportTask implements Task {
    private static final Log log = LogFactory.getLog(PrepareActivationReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerActivationDao activationDao = new CustomerActivationDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerActivationFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_ACTIVATION_FILTER), CustomerActivationFilter.class);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            // Check if report data was prepared or not?
            long count = activationDao.checkActivationReport(filter);
            // Prepare data for the report if it wasn't prepared
            if (count <= 0) {
                activationDao.prepareActivationReport(addInfo, filter);
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
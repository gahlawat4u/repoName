package com.gms.xms.workflow.task.report.customer.activation;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.activation.CustomerActivationFilter;
import com.gms.xms.persistence.dao.report.CustomerActivationDao;
import com.gms.xms.txndb.vo.reports.customer.activation.CustomerActivationVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetActivationReportTask.java
 * <p>
 * Author dattrinh 11:30:15 AM
 */
public class GetActivationReportTask implements Task {
    private static final Log log = LogFactory.getLog(GetActivationReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerActivationDao activationDao = new CustomerActivationDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerActivationFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_ACTIVATION_FILTER), CustomerActivationFilter.class);
        try {
            // Do DAO service to get information for customer activation report.
            List<CustomerActivationVo> result = activationDao.getActivationReport(filter);
            // Puts result into the context
            context.put(Attributes.CUSTOMER_ACTIVATION_REPORT_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
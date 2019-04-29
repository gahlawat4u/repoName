package com.gms.xms.workflow.task.customeraging;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.customeraging.CustomerAgingTaskDaoService;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from PrepareDataForCustomerAgingReportTask
 * <p>
 * Author DatTV Date Aug 13, 2015 4:12:42 PM
 */
public class PrepareDataForCustomerAgingReportTask implements Task {
    private static final Log log = LogFactory.getLog(PrepareDataForCustomerAgingReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerAgingTaskDaoService taskDaoService = new CustomerAgingTaskDaoService();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());

        // Get the filter from the context.
        CustomerAgingFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_AGING_FILTER), CustomerAgingFilter.class);

        try {
            if (!AppConstants.YES_FLAG.equalsIgnoreCase(context.getString(Attributes.HAS_CACHE_ID))) {
                // // Create new report transaction id
                // String newRptTxnId = UUID.randomUUID().toString();
                // filter.setRptTxnId(newRptTxnId);
                //
                // // Put filter back into the context
                // context.put(Attributes.CUSTOMER_AGING_FILTER,
                // GsonUtils.toGson(filter));

                // Do DAO service to prepare data for customer aging report
                taskDaoService.prepareDataForCustomerAgingReport(addInfo, filter);
            }
        } catch (Exception ex) {
            log.error(ex);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }
}

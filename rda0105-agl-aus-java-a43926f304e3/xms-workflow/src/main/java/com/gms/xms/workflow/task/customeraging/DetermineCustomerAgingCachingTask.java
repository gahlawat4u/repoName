package com.gms.xms.workflow.task.customeraging;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.customeraging.CustomerAgingDao;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from DetermineCustomerAgingCachingTask
 * <p>
 * Author DatTV Date Aug 13, 2015 3:50:43 PM
 */
public class DetermineCustomerAgingCachingTask implements Task {
    private static final Log log = LogFactory.getLog(DetermineCustomerAgingCachingTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        log.info("Going to determine the customer aging report is cached or not...");
        CustomerAgingDao customerAgingDao = new CustomerAgingDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            // Get the filter from the context.
            CustomerAgingFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_AGING_FILTER), CustomerAgingFilter.class);

            if (StringUtils.isBlank(filter.getRptTxnId())) {
                context.put(Attributes.HAS_CACHE_ID, AppConstants.NO_FLAG);
            } else {
                // Check if Report Transaction ID existed or not from the database?
                int count = customerAgingDao.countByRptTxnId(filter.getRptTxnId());

                if (count <= 0) {
                    context.put(Attributes.HAS_CACHE_ID, AppConstants.NO_FLAG);
                } else {
                    context.put(Attributes.HAS_CACHE_ID, AppConstants.YES_FLAG);
                }
            }
        } catch (Exception ex) {
            log.error(ex);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }
}

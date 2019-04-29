package com.gms.xms.workflow.task.customeraging;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.customeraging.CustomerAgingDao;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from CountCustomerAgingByFilterTask
 * <p>
 * Author DatTV Date Aug 13, 2015 5:11:37 PM
 */
public class CountCustomerAgingByFilterTask implements Task {
    private static final Log log = LogFactory.getLog(CountCustomerAgingByFilterTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerAgingDao customerAgingDao = new CustomerAgingDao();

        // Get the filter from the context.
        CustomerAgingFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_AGING_FILTER), CustomerAgingFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get information for customer aging report.
            long result = customerAgingDao.countByFilter(filter);

            // Puts result into the context
            context.put(Attributes.CUSTOMER_AGING_RECORD_COUNT_RESULT, String.valueOf(result));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }
}
package com.gms.xms.workflow.task.customeraging;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.customeraging.CustomerAgingDao;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingFilter;
import com.gms.xms.txndb.vo.receivables.customeraging.CustomerAgingVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetCustomerAgingByFilterTask
 * <p>
 * Author DatTV Date Aug 13, 2015 4:36:50 PM
 */
public class GetCustomerAgingByFilterTask implements Task {
    private static final Log log = LogFactory.getLog(GetCustomerAgingByFilterTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerAgingDao customerAgingDao = new CustomerAgingDao();

        // Get the filter from the context.
        CustomerAgingFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_AGING_FILTER), CustomerAgingFilter.class);

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get information for customer aging report.
            List<CustomerAgingVo> result = customerAgingDao.selectByFilter(filter);

            // Puts result into the context
            context.put(Attributes.CUSTOMER_AGING_LIST_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }
}
package com.gms.xms.workflow.task.report.customer.summary;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.reports.customer.CustomerSummaryDao;
import com.gms.xms.txndb.vo.reports.customer.CustomerSummaryFilter;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from PrepareDataForCustomerSummaryTask
 * <p>
 * Author DatTV Sep 15, 2015
 */
public class PrepareDataForCustomerSummaryTask implements Task {
    private static final Log log = LogFactory.getLog(PrepareDataForCustomerSummaryTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerSummaryDao summaryDao = new CustomerSummaryDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerSummaryFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_SUMMARY_FILTER), CustomerSummaryFilter.class);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            // Check if report data was prepared or not?
            long count = summaryDao.countByFilter(filter);
            // Prepare data for the report if it wasn't prepared
            if (count <= 0) {
                summaryDao.prepareDataForCustomerSummary(addInfo, filter);
            }
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }
        return true;
    }
}
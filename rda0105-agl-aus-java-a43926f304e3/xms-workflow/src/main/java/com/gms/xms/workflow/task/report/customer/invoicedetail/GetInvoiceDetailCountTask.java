package com.gms.xms.workflow.task.report.customer.invoicedetail;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;
import com.gms.xms.persistence.dao.report.CustomerInvoiceDetailDao;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetInvoiceDetailCountTask.java
 * <p>
 * Author dattrinh 3:33:24 PM
 */
public class GetInvoiceDetailCountTask implements Task {
    private static final Log log = LogFactory.getLog(GetInvoiceDetailCountTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerInvoiceDetailDao invoiceDetailDao = new CustomerInvoiceDetailDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerInvoiceDetailFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_INVOICE_DETAIL_FILTER), CustomerInvoiceDetailFilter.class);
        try {
            // Do DAO service to record count of customer invoice detail report.
            Long recordCount = invoiceDetailDao.checkInvoiceDetailReport(filter);

            // Puts result into the context
            context.put(Attributes.CUSTOMER_INVOICE_DETAIL_RECORD_COUNT_RESULT, String.valueOf(recordCount));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
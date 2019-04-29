package com.gms.xms.workflow.task.report.customer.invoicedetail;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerInvoiceDetailFilter;
import com.gms.xms.persistence.dao.report.CustomerInvoiceDetailDao;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerInvoiceDetailVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jun 17, 2016 11:40:31 AM
 * <p>
 * Author dattrinh
 */
public class SumInvoiceDetailReportTask implements Task {
    private static final Log log = LogFactory.getLog(SumInvoiceDetailReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerInvoiceDetailDao invoiceDetailDao = new CustomerInvoiceDetailDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerInvoiceDetailFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_INVOICE_DETAIL_FILTER), CustomerInvoiceDetailFilter.class);
        try {
            // Do DAO service to get summary information for customer invoice detail report.
            CustomerInvoiceDetailVo result = invoiceDetailDao.sumInvoiceDetailReport(filter);
            // Puts result into the context
            context.put(Attributes.CUSTOMER_INVOICE_DETAIL_SUMMARY_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
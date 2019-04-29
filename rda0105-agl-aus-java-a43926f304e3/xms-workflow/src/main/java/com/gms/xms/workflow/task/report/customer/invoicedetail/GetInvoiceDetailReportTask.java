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

import java.util.List;

/**
 * Posted from GetInvoiceDetailReportTask.java
 * <p>
 * Author dattrinh 3:43:58 PM
 */
public class GetInvoiceDetailReportTask implements Task {
    private static final Log log = LogFactory.getLog(GetInvoiceDetailReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerInvoiceDetailDao invoiceDetailDao = new CustomerInvoiceDetailDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerInvoiceDetailFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_INVOICE_DETAIL_FILTER), CustomerInvoiceDetailFilter.class);
        try {
            // Do DAO service to get information for customer activation report.
            List<CustomerInvoiceDetailVo> result = invoiceDetailDao.getInvoiceDetailReport(filter);
            // Puts result into the context
            context.put(Attributes.CUSTOMER_INVOICE_DETAIL_REPORT_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
package com.gms.xms.workflow.task.report.customer.invoicedetail;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.persistence.dao.report.CustomerCreditNoteDetailDao;
import com.gms.xms.txndb.vo.reports.customer.invoicedetail.CustomerCreditNoteDetailVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetCreditNoteDetailReportTask.java
 * <p>
 * Author dattrinh 3:46:46 PM
 */
public class SumCreditNoteDetailReportTask implements Task {
    private static final Log log = LogFactory.getLog(SumCreditNoteDetailReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerCreditNoteDetailDao creditNoteDetailDao = new CustomerCreditNoteDetailDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerCreditNoteDetailFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_FILTER), CustomerCreditNoteDetailFilter.class);
        try {
            // Do DAO service to get information for summary of credit note detail report.
            CustomerCreditNoteDetailVo result = creditNoteDetailDao.sumCreditNoteDetailReport(filter);
            // Puts result into the context
            context.put(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_SUMMARY_RESULT, GsonUtils.toGson(result));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
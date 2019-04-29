package com.gms.xms.workflow.task.report.customer.invoicedetail;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.persistence.dao.report.CustomerCreditNoteDetailDao;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetCreditNoteDetailCountTask.java
 * <p>
 * Author dattrinh 3:37:26 PM
 */
public class GetCreditNoteDetailCountTask implements Task {
    private static final Log log = LogFactory.getLog(GetCreditNoteDetailCountTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerCreditNoteDetailDao creditNoteDetailDao = new CustomerCreditNoteDetailDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerCreditNoteDetailFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_FILTER), CustomerCreditNoteDetailFilter.class);
        try {
            // Do DAO service to record count of customer credit note detail report.
            Long recordCount = creditNoteDetailDao.checkCreditNoteDetailReport(filter);

            // Puts result into the context
            context.put(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_RECORD_COUNT_RESULT, String.valueOf(recordCount));
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
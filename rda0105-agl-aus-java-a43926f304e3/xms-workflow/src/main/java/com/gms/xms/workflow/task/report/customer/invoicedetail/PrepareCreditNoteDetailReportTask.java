package com.gms.xms.workflow.task.report.customer.invoicedetail;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.reports.customer.invoicedetail.CustomerCreditNoteDetailFilter;
import com.gms.xms.persistence.dao.report.CustomerCreditNoteDetailDao;
import com.gms.xms.workflow.core.Task;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from PrepareCreditNoteDetailReportTask.java
 * <p>
 * Author dattrinh 3:41:45 PM
 */
public class PrepareCreditNoteDetailReportTask implements Task {
    private static final Log log = LogFactory.getLog(PrepareCreditNoteDetailReportTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        CustomerCreditNoteDetailDao creditNoteDetailDao = new CustomerCreditNoteDetailDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        // Get the filter from the context.
        CustomerCreditNoteDetailFilter filter = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_CREDIT_NOTE_DETAIL_FILTER), CustomerCreditNoteDetailFilter.class);
        Map<String, String> addInfo = GsonUtils.fromGson(context.get(Attributes.STR_ADD_INFO), new TypeToken<Map<String, String>>() {
        }.getType());
        try {
            // Check if report data was prepared or not?
            long count = creditNoteDetailDao.checkCreditNoteDetailReport(filter);
            // Prepare data for the report if it wasn't prepared
            if (count <= 0) {
                creditNoteDetailDao.prepareCreditNoteDetailReport(addInfo, filter);
            }
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
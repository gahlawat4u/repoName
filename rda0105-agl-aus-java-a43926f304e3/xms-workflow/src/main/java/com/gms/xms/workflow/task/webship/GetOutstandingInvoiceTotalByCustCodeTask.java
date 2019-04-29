package com.gms.xms.workflow.task.webship;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetOutstandingInvoicesCountByCustCodeTask
 * <p>
 * Author DatTV Date Mar 31, 2015
 */
public class GetOutstandingInvoiceTotalByCustCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetOutstandingInvoiceTotalByCustCodeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        InvoiceDao invoiceDao = new InvoiceDao();
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        InvoiceFilter filter = GsonUtils.fromGson(context.get(Attributes.INVOICE_FILTER), InvoiceFilter.class);
        try {
            InvoiceVo invoice = invoiceDao.getOutstandingInvoiceTotalByCustCode(filter);
            context.put(Attributes.OUTSTANDING_INVOICE_TOTAL_RESULT, String.valueOf(GsonUtils.toGson(invoice)));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            return false;
        }

        return true;
    }

}

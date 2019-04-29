package com.gms.xms.workflow.task.receivepayments;

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

import java.util.List;

/**
 * Posted from GetOutstandingInvoicesByCustOrInvoiceCodeTask
 * <p>
 * Author DatTV Date Apr 8, 2015 11:21:19 AM
 */
public class GetOutstandingInvoicesByCustOrInvoiceCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetOutstandingInvoicesByCustOrInvoiceCodeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        InvoiceDao invoiceDao = new InvoiceDao();
        try {
            InvoiceFilter filter = GsonUtils.fromGson(context.get(Attributes.INVOICE_FILTER), InvoiceFilter.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to gets list of outstanding invoices by
            // customer/invoice code
            List<InvoiceVo> invoiceList = invoiceDao.getOutstandingInvoicesByCustOrInvoiceCode(filter);

            // Puts result into the context
            context.put(Attributes.INVOICE_LIST_RESULT, GsonUtils.toGson(invoiceList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}

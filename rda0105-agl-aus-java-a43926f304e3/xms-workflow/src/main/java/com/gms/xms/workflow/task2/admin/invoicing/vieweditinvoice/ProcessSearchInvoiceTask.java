package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.filter.invoicing.manageinvoice.ViewEditInvoiceFilter;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from ProcessSearchInvoiceTask
 * <p>
 * Author TANDT
 */
public class ProcessSearchInvoiceTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessSearchInvoiceTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            ViewEditInvoiceFilter filter = context.get(Attributes.INVOICE_FILTER);
            IInvoiceService service = new InvoiceServiceImp();
            List<InvoiceVo> invoices = service.selectInvoiceByFilter(filter);
            context.put(Attributes.INVOICE_LIST_RESULT, invoices);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}

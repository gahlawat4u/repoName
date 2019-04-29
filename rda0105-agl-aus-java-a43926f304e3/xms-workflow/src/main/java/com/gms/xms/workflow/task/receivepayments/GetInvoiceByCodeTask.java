package com.gms.xms.workflow.task.receivepayments;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from CheckInvoiceCodeTask
 * <p>
 * Author DatTV Date Apr 18, 2015 11:11:58 AM
 */
public class GetInvoiceByCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetInvoiceByCodeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        InvoiceDao invoiceDao = new InvoiceDao();
        try {
            String invoiceCode = context.getString(Attributes.INVOICE_CODE);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to check if the customer with specified code
            // exists or not
            InvoiceVo invoiceVo = invoiceDao.selectByCode(invoiceCode);

            // Puts result into the context
            context.put(Attributes.INVOICE_RESULT, GsonUtils.toGson(invoiceVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}

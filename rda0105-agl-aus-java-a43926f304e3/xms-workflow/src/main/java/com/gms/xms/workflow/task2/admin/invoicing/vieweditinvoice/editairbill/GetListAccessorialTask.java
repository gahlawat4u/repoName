package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.editairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.admin.invoicing.ViewEditInvoiceDao;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.ViewEditInvoiceAccessorialVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from GetListAccessorialTask
 * <p>
 * Author TANDT
 */
public class GetListAccessorialTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetListAccessorialTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Integer serviceId = context.getInt(Attributes.SERVICE_ID);
            List<ViewEditInvoiceAccessorialVo> invoiceAccessorialDetailVo = new ArrayList<ViewEditInvoiceAccessorialVo>();
            ViewEditInvoiceDao editInvoiceDao = new ViewEditInvoiceDao();
            invoiceAccessorialDetailVo = editInvoiceDao.selectAccessorialsByShipmentId(serviceId);
            context.put(Attributes.ACCESSORIAL_LIST, invoiceAccessorialDetailVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}

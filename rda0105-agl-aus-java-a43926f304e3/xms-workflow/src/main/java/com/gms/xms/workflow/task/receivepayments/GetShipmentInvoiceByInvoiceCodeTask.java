package com.gms.xms.workflow.task.receivepayments;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentInvoiceDao;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetShipmentInvoiceByInvoiceCodeTask
 * <p>
 * Author DatTV Date Apr 10, 2015 4:23:54 PM
 */
public class GetShipmentInvoiceByInvoiceCodeTask implements Task {
    private static final Log log = LogFactory.getLog(GetShipmentInvoiceByInvoiceCodeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        ShipmentInvoiceDao shipmentInvoiceDao = new ShipmentInvoiceDao();
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            String invoiceCode = context.getString(Attributes.INVOICE_CODE);

            // Do DAO service to gets list of payments
            List<ShipmentInvoiceVo> shipmentInvoiceList = shipmentInvoiceDao.selectByInvoiceCode(invoiceCode);

            // Puts result into the context
            context.put(Attributes.SHIPMENT_INVOICE_LIST_RESULT, GsonUtils.toGson(shipmentInvoiceList));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}

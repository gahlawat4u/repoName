package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.editairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.admin.invoicing.ViewEditInvoiceDao;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AirbillInfoEditVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetListAccessorialTask
 * <p>
 * Author TANDT
 */
public class GetAirbillInforEditTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetAirbillInforEditTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            String airbillNumber = context.get(Attributes.AIRBILL_NUMBER);
            ViewEditInvoiceDao editInvoiceDao = new ViewEditInvoiceDao();
            AirbillInfoEditVo airbillInfoEditVo = new AirbillInfoEditVo();
            ShipmentVo shipmentVo = new ShipmentVo();
            shipmentVo.setAirbillNumber(airbillNumber);
            shipmentVo.setShipmentId(shipmentId);
            airbillInfoEditVo = editInvoiceDao.selectAirbillInfoEditByShipment(shipmentVo);
            Integer invoiceStatus = editInvoiceDao.selectInvoiceStatusByAribillNumber(shipmentVo);
            context.put(Attributes.INVOICE_STATUS, invoiceStatus);
            context.put(Attributes.SERVICE_ID, airbillInfoEditVo.getServiceId());
            context.put(Attributes.AIRBILL_INFO_VO, airbillInfoEditVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}

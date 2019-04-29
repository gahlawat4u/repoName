package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.editairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.admin.invoicing.ViewEditInvoiceDao;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from GetBillingInfoBaseChargeTask
 * <p>
 * Author TANDT
 */
public class GetListAccessorialInfoTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetListAccessorialInfoTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            String airbillNumber = context.get(Attributes.AIRBILL_NUMBER);
            ShipmentVo shipmentVo = new ShipmentVo();
            shipmentVo.setShipmentId(shipmentId);
            shipmentVo.setAirbillNumber(airbillNumber);
            List<AccessorialInfoVo> accessorialInfoVos = new ArrayList<AccessorialInfoVo>();
            ViewEditInvoiceDao editInvoiceDao = new ViewEditInvoiceDao();
            accessorialInfoVos = editInvoiceDao.selectAccessorialByShipmentIdAndAirbillNumber(shipmentVo);
            context.put(Attributes.ACCESSORIAL_INFO_VO, accessorialInfoVos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}

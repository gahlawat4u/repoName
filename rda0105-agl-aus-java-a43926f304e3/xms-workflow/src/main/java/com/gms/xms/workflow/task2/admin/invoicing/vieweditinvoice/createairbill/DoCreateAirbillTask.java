package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.admin.invoicing.IViewEditInvoiceService;
import com.gms.xms.persistence.service.admin.invoicing.ViewEditInvoiceServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.RecalculateChargeImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * File Name: DoCreateAirbillTask.java <br/>
 * Author: TANDT <br/>
 * Create Date: 18-03-2016 <br/>
 * Project Name: xms-workflow <br/>
 * package Name: com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill <br/>
 */
public class DoCreateAirbillTask implements Task2 {
    private static final Log log = LogFactory.getLog(DoCreateAirbillTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            EditAirbillResultVo createAirbillResult = context.get(Attributes.CREATE_AIRBILL_RESULT);
            ShipmentVo shipmentVo = context.get(Attributes.SHIPMENT_VO);
            ShipmentInvoiceVo shipmentInvoiceVo = context.get(Attributes.SHIPMENT_INVOICE_VO);
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            IViewEditInvoiceService service = new ViewEditInvoiceServiceImp();
            IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(addInfo);
            service.createAirbillDo(addInfo, createAirbillResult, shipmentVo, shipmentInvoiceVo, iRecalculateCharge);
            context.put(Attributes.SHIPMENT_ID, createAirbillResult.getShipmentBilling().getShipmentId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}

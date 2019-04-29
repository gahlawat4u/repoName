package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;

/**
 * File Name: PrepareShipmentInvoiceTask.java <br/>
 * Author: TANDT <br/>
 * Create Date: 18-03-2016 <br/>
 * Project Name: xms-workflow <br/>
 * package Name:
 * com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.createairbill
 * <br/>
 */
public class PrepareShipmentInvoiceTask implements Task2 {
    private static final Log log = LogFactory.getLog(PrepareShipmentInvoiceTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            EditAirbillResultVo createAirbillResult = context.get(Attributes.CREATE_AIRBILL_RESULT);
            Long invoiceId = context.getLong(Attributes.INVOICE_ID);
            ShipmentBillingVo shipmentBillingVo = createAirbillResult.getShipmentBilling();
            context.put(Attributes.SHIPMENT_INVOICE_VO, buildShipmentInvoiceVo(shipmentBillingVo, invoiceId));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

    private ShipmentInvoiceVo buildShipmentInvoiceVo(ShipmentBillingVo shipmentBillingVo, Long invoiceId) throws Exception {
        ShipmentInvoiceVo shipmentInvoiceVo = new ShipmentInvoiceVo();
        shipmentInvoiceVo.setAirbillNumber(shipmentBillingVo.getAirbillNumber());
        shipmentInvoiceVo.setInvoiceId(invoiceId);
        shipmentInvoiceVo.setPaidCarrierCost(BigDecimal.valueOf(0D));
        shipmentInvoiceVo.setPaidCustomerCost(BigDecimal.valueOf(0D));
        return shipmentInvoiceVo;
    }

}

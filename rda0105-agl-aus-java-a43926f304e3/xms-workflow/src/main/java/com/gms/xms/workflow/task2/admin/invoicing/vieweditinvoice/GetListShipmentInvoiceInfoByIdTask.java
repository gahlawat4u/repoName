package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.admin.invoicing.IViewEditInvoiceService;
import com.gms.xms.persistence.service.admin.invoicing.ViewEditInvoiceServiceImp;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.AirbillInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.ChargeVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from GetListShipmentInvoiceInfoByIdTask
 * <p>
 * Author TANDT
 */
public class GetListShipmentInvoiceInfoByIdTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetListShipmentInvoiceInfoByIdTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get invoice id from the context.
            Long invoiceId = context.getLong(Attributes.INVOICE_ID);
            // Get list of airbills of the invoice.
            List<AirbillInfoVo> shipmentInvoiceDetailVo = new ArrayList<AirbillInfoVo>();
            IViewEditInvoiceService invoiceInfoService = new ViewEditInvoiceServiceImp();
            shipmentInvoiceDetailVo = invoiceInfoService.selectAirbillList(invoiceId);
            // Calculate GST row for each the airbill.
            if (shipmentInvoiceDetailVo != null && shipmentInvoiceDetailVo.size() > 0) {
                for (AirbillInfoVo airbillInfoVo : shipmentInvoiceDetailVo) {
                    Double gst = 0.0;
                    if (airbillInfoVo.getCharges() != null && airbillInfoVo.getCharges().size() > 0) {
                        for (ChargeVo chargeVo : airbillInfoVo.getCharges()) {
                            gst += chargeVo.getAwbCustomerTax();
                        }
                    }
                    // Add new charge with name is GST.
                    ChargeVo gstChargeVo = new ChargeVo();
                    gstChargeVo.setAwbCustomerCost(gst);
                    gstChargeVo.setAwbDescription("GST");
                    airbillInfoVo.getCharges().add(gstChargeVo);
                }
            }
            context.put(Attributes.AIRBILL_INFO_VO, shipmentInvoiceDetailVo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}

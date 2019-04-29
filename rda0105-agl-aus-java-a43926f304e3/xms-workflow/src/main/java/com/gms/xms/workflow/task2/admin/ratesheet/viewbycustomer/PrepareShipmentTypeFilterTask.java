package com.gms.xms.workflow.task2.admin.ratesheet.viewbycustomer;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.admin.ratesheets.ViewRateSheetRequestVo;
import com.gms.xms.txndb.vo.airbill.ShipmentBillingInfoVo;
import com.gms.xms.workflow.core2.Task2;

/**
 * Posted from May 18, 2016 4:16:15 PM
 * <p>
 * Author dattrinh
 */
public class PrepareShipmentTypeFilterTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get view rate sheet request vo.
            ViewRateSheetRequestVo rateSheetRequestVo = context.get(Attributes.VIEW_RATE_SHEET_REQUEST_VO);
            // Create shipment billing vo object to keep filter data to get shipment type info.
            ShipmentBillingInfoVo billingInfoVo = new ShipmentBillingInfoVo();
            billingInfoVo.setShipmentTypeId(rateSheetRequestVo.getShipmentTypeId());
            billingInfoVo.setContent(rateSheetRequestVo.getContent());
            billingInfoVo.setBound(rateSheetRequestVo.getBound());
            // Put into the context.
            context.put(Attributes.SHIPMENT_BILLING_VO, billingInfoVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

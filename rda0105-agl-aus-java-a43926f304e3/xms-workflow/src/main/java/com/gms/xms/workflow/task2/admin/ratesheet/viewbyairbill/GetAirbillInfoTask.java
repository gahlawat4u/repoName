package com.gms.xms.workflow.task2.admin.ratesheet.viewbyairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.airbill.ShipmentBillingInfoVo;
import com.gms.xms.workflow.core2.Task2;

/**
 * Posted from May 16, 2016 2:26:59 PM
 * <p>
 * Author dattrinh
 */
public class GetAirbillInfoTask implements Task2 {

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            String airbillNumber = context.get(Attributes.AIRBILL_NUMBER);
            ShipmentBillingDao dao = new ShipmentBillingDao();
            ShipmentBillingVo billingVo = new ShipmentBillingVo();
            billingVo.setShipmentId(shipmentId);
            billingVo.setAirbillNumber(airbillNumber);
            ShipmentBillingInfoVo billingInfoVo = dao.getShipmentBillingInfoByCode(billingVo);
            if (billingInfoVo == null) {
                throw new Exception("No airbill found.");
            }
            context.put(Attributes.SHIPMENT_BILLING_VO, billingInfoVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

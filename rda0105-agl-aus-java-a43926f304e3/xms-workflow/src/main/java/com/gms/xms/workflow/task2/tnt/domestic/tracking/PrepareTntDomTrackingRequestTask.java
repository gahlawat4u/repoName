package com.gms.xms.workflow.task2.tnt.domestic.tracking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from PrepareTntDomTrackingRequestTask
 * <p>
 * Author TANDT
 */
public class PrepareTntDomTrackingRequestTask implements Task2 {

    private static final Log log = LogFactory.getLog(PrepareTntDomTrackingRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get shipment id from the context.
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            // Get shipment info by shipment id.
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(shipmentId);
            // Put air-bill number into the context.
            context.put(Attributes.AIRBILL_NUMBER, shipmentVo.getAirbillNumber());
            // Put country code into the context.
            context.put(Attributes.SHIPMENT_INFO_VO, shipmentVo);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

package com.gms.xms.workflow.task2.dhl.tracking;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.dhl.DhlTrackingRequestVo;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from PrepareDhlTrackingRequestTask
 * <p>
 * Author dattrinh Feb 1, 2016 3:44:56 PM
 */
public class PrepareDhlTrackingRequestTask implements Task2 {

    private static final Log log = LogFactory.getLog(PrepareDhlTrackingRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get shipment id from the context.
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            // Get LangCode.
            String langCode = context.get(Attributes.LANG_CODE);
            // Get shipment info by shipment id.
            IShipmentService shipmentService = new ShipmentServiceImp();
            ShipmentVo shipmentVo = shipmentService.selectById(shipmentId);
            if (shipmentVo == null) {
                throw new Exception("No shipment found ...\nPlease select another shipment.");
            }
            // Create DhlTracking Request.
            DhlTrackingRequestVo dhlTrackingRequestVo = new DhlTrackingRequestVo();
            dhlTrackingRequestVo.setLangCode(langCode);
            dhlTrackingRequestVo.setAirbillNumber(shipmentVo.getAirbillNumber());
            // Put Shipment Info into the context.
            context.put(Attributes.SHIPMENT_INFO_VO, shipmentVo);
            context.put(Attributes.TRACKING_REQUEST, dhlTrackingRequestVo);
            // Put DhlTrackingRequest into the context.
            context.put(Attributes.TRACKING_REQUEST, dhlTrackingRequestVo);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

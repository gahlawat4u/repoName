package com.gms.xms.workflow.task2.ups.tracking;

import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.delivery.ups.service.rest.tracking.UPSTrackingRequest;

public class PrepareUpsTrackingRequestTask implements Task2 {

	 private static final Log log = LogFactory.getLog(PrepareUpsTrackingRequestTask.class);
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
		    String authenticationKey = "gmsYupsZTimeXDel";
		    UPSTrackingRequest upsTrackingRequest = new UPSTrackingRequest();
		    upsTrackingRequest.setAWBNumber(shipmentVo.getAirbillNumber());
		    upsTrackingRequest.setAuthenticationKey(authenticationKey);
		    upsTrackingRequest.setJavaCode("java");
		    // Put Shipment Info into the context.
		    context.put(Attributes.SHIPMENT_INFO_VO, shipmentVo);
		    context.put(Attributes.TRACKING_REQUEST, upsTrackingRequest);
		   
		} catch (Exception e) {
		    log.error(e);
		    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
		    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
		    return false;
		}
		return true;
	    }


}

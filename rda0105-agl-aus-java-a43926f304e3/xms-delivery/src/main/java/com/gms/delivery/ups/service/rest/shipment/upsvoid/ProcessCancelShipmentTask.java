package com.gms.delivery.ups.service.rest.shipment.upsvoid;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.delivery.ups.service.GsonContextLoader;
import com.gms.delivery.ups.service.rest.shipment.cancel.Response;
import com.gms.delivery.ups.service.rest.shipment.cancel.UpsCancelPickupServiceApi;
import com.gms.delivery.ups.service.rest.shipment.cancel.UpsCancelTrackResponse;
import com.gms.delivery.ups.service.rest.shipment.cancel.UpsPickupCancelRequest;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.dhl.DhlCancelPickupRequestVo;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.workflow.core2.Task2;

public class ProcessCancelShipmentTask implements Task2{
	
	 private static final Log log = LogFactory.getLog(ProcessCancelShipmentTask.class);

	    @Override
	    public boolean execute(ContextBase2 context) throws Exception {
		try {
		    Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
		    context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
		    // Get dhlCancelPickupRequestVo.
		    DhlCancelPickupRequestVo upsCancelPickupRequestVo = context.get(Attributes.UPS_CANCEL_SHIPMENT_REQUEST);
		  
		    UpsCancelPickupServiceApi upsCancelPickupServiceApi = new UpsCancelPickupServiceApi();
		    
		    UpsPickupCancelRequest  upsPickupCancelRequest = new UpsPickupCancelRequest();
		    
		  /*  upsPickupCancelRequest.setHidlog("0");
		    upsPickupCancelRequest.setSmID("92768"+upsCancelPickupRequestVo.getShipment().getShipmentId());
		    upsPickupCancelRequest.setHidsmtname("183");
		    upsPickupCancelRequest.setHidsmid("92768"+upsCancelPickupRequestVo.getShipment().getShipmentId());
		    upsPickupCancelRequest.setSelreasonid("002"+upsCancelPickupRequestVo.getReason());
		    upsPickupCancelRequest.setLog("0");
		    upsPickupCancelRequest.setSubcancel("1");*/
		   /* upsPickupCancelRequest.setHidlog("0");
		    upsPickupCancelRequest.setSmID(""+upsCancelPickupRequestVo.getShipment().getShipmentId());
		    upsPickupCancelRequest.setHidsmtname(""+upsCancelPickupRequestVo.getShipment().getShipmentTypeId());
		    upsPickupCancelRequest.setHidsmid(""+upsCancelPickupRequestVo.getShipment().getShipmentId());
		    upsPickupCancelRequest.setSelreasonid(upsCancelPickupRequestVo.getReason());
		    upsPickupCancelRequest.setLog("0");
		    upsPickupCancelRequest.setSubcancel("1");
		*/    String response =    upsCancelPickupServiceApi.getUpsCancel(upsPickupCancelRequest);
		    
		    UpsCancelTrackResponse upsCancelTrackResponse = GsonContextLoader.getGsonContext().fromJson(response,UpsCancelTrackResponse.class);
		    
		    Response respo =  upsCancelTrackResponse.getResponse();
		    if(respo.getCondition_msg() != null){
		    throw new Exception(respo.getErr_header_msg());
		    }
		    /* DhlCancelPickupService cancelPickupService = new DhlCancelPickupService();
		    if (dhlCancelPickupRequestVo.getScheduleCollection() != null) {
			String xmlRequest = cancelPickupService.parseRequest(dhlCancelPickupRequestVo);
			
			String xmlResponse = cancelPickupService.execute(xmlRequest, null);
			CancelPUResult cancelPUResult = cancelPickupService.parseResponse(xmlResponse);*/
			// Check if connect success to the UPS cancel pickup
			// service.
			/*if (cancelPUResult == null) {
			    throw new Exception("Fail to send the DHL cancel pickup request.");
			}
			// Check if success then update the database.
			ScheduleCollectionDao scheduleDao = new ScheduleCollectionDao();
			if (cancelPUResult.getErrorResponse() == null) {
			    ScheduleCollectionVo schedule = dhlCancelPickupRequestVo.getScheduleCollection();
			    Date currentDate = new Date();
			    schedule.setCreateDate(currentDate);
			    schedule.setStatus((byte) 0);
			    scheduleDao.cancelScheduleCollectionById(addInfo, schedule);
			} else {
			    
			    throw new Exception(cancelPickupService.getErrorMessage(cancelPUResult.getErrorResponse()));
			}
		    } else {*/
			IShipmentService service = new ShipmentServiceImp();
			service.voidShipment(addInfo, upsCancelPickupRequestVo.getScheduleCollection(), upsCancelPickupRequestVo.getShipment());
		   // }
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
		    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
		    return false;
		}
		return true;
	    }

}

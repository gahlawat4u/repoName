package com.gms.xms.workflow.task2.webship.voids.ups;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.delivery.ups.service.GsonContextLoader;
import com.gms.delivery.ups.service.rest.shipment.cancel.Response;

import com.gms.delivery.ups.service.rest.shipment.cancel.UpsCancelTrackResponse;
import com.gms.xms.cache.SystemSettingCache;
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
			
		       String Username = SystemSettingCache.getInstance().getValueByKey("UPS UserId");
			   String Password = SystemSettingCache.getInstance().getValueByKey("UPS Password");
			   String AccessLicenseNumber = SystemSettingCache.getInstance().getValueByKey("UPS AccessLicenseNumber");
			   String ShipperNumber = SystemSettingCache.getInstance().getValueByKey("UPS_Web_Service_Account");
			   DhlCancelPickupRequestVo upsCancelPickupRequestVo = context.get(Attributes.UPS_CANCEL_SHIPMENT_REQUEST);
			   
			   VoidShipmentRequest voidShipmentRequest = new  VoidShipmentRequest();
			   AccessRequest accessRequest = new  AccessRequest();
			   
			   accessRequest.setPassword(Password);
			   accessRequest.setAccessLicenseNumber(AccessLicenseNumber);
			   accessRequest.setUserId(Username);
			   
			   Request request = new Request();
			   request.setRequestAction("1");
			  
			   TransactionReference transactionReference = new TransactionReference();
			   transactionReference.setCustomerContext("Customer Comment");
			   transactionReference.setXpciVersion("1.0");
			   request.setTransactionReference(transactionReference);
			   voidShipmentRequest.setRequest(request);

			   voidShipmentRequest.setShipmentIdentificationNumber(upsCancelPickupRequestVo.getShipment().getAirbillNumber());
			   
			   UpsPickupCancelRequest  upsPickupCancelRequest = new UpsPickupCancelRequest();
			   
			   upsPickupCancelRequest.setAccessRequest(accessRequest);
			   upsPickupCancelRequest.setVoidShipmentRequest(voidShipmentRequest);
			   
			   UpsCancelPickupServiceApi upsCancelPickupServiceApi = new UpsCancelPickupServiceApi();
			   
			   
		    String response =    upsCancelPickupServiceApi.getUpsCancel(upsPickupCancelRequest);
		
		  
		    
		    UpsCancelTrackResponse upsCancelTrackResponse = GsonContextLoader.getGsonContext().fromJson(response,UpsCancelTrackResponse.class);
		    Response respo =  upsCancelTrackResponse.getResponse();
		   
		    //code by rakesh sir
		   /* if(null !=respo && respo.getCondition_msg() != null){
		    throw new Exception(respo.getErr_header_msg());
		    }*/
		    
		    
		    
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
		//	service.voidShipment(addInfo, upsCancelPickupRequestVo.getScheduleCollection(), upsCancelPickupRequestVo.getShipment());
		   // }
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
		    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
		    return false;
		}
		return true;
	    }

	    
	    public String parseVoidUpsXMLRequest(VoidShipmentRequest voidShipmentRequest, AccessRequest accessRequest) throws Exception {

	    	String xmlAccess =""; 
	    	
	    	xmlAccess = AppUtils.Object2XmlString(accessRequest, AccessRequest.class);
	    	
	    	xmlAccess += AppUtils.Object2XmlString(voidShipmentRequest, VoidShipmentRequest.class);
	    	
	        return xmlAccess;
	    }
	    
}

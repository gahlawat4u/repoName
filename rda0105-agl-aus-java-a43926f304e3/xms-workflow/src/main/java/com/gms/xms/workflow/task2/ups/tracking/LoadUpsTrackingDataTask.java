package com.gms.xms.workflow.task2.ups.tracking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.delivery.ups.service.rest.tracking.Activity;
import com.gms.delivery.ups.service.rest.tracking.Loop_data;
import com.gms.delivery.ups.service.rest.tracking.UpsTrackingResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.dhl.DhlTrackingResponseVo;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.TrackingVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailInfoVo;
import com.gms.xms.workflow.core2.Task2;


public class LoadUpsTrackingDataTask implements Task2 {

	private static final Log log = LogFactory.getLog(LoadUpsTrackingDataTask.class);

	List<Loop_data> upsActivity = null;
    @Override
    public boolean execute(ContextBase2 context) throws Exception {
	try {
	    // Get Ups Tracking Response.
		UpsTrackingResponse upsTrackResponse = context.get(Attributes.TRACKING_RESPONSE);
		
		try{
			upsActivity = Arrays.asList(upsTrackResponse.getLoop_data());
		}catch (Exception e) {
			// TODO: handle exception
		}
			    	
			    	    DhlTrackingResponseVo trackingResponseVo = new DhlTrackingResponseVo();
					    HistoryDetailInfoVo detailInfoVo = new HistoryDetailInfoVo();
					    List<TrackingVo> trackingList = new ArrayList<TrackingVo>();
					    // Get shipment detail.
					    ShipmentVo shipmentVo = context.get(Attributes.SHIPMENT_INFO_VO);
					 
					       AddressServiceImp addressServiceImp = new AddressServiceImp();
						   AddressVo     sAddress =   addressServiceImp.getAddressById(shipmentVo.getSenderAddressId());
						   AddressVo     rAddress =   addressServiceImp.getAddressById(shipmentVo.getReceiverAddressId());

						   // Get detail info.
					    detailInfoVo.setTracking(shipmentVo.getAirbillNumber());
					    detailInfoVo.setsCompanyName(sAddress.getCompanyName());
					   // detailInfoVo.setContentDescription(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getDestinationServiceArea().getDescription());
					    IShipmentTypeService shipmentTypeService = new ShipmentTypeServiceImp();
					    ShipmentTypeVo shipmentTypeVo = shipmentTypeService.getShipmentTypeByShipmentTypeId(shipmentVo.getShipmentTypeId());
					    detailInfoVo.setServiceType(shipmentTypeVo.getShipmentTypeName());
					    detailInfoVo.setrCompanyName(rAddress.getCompanyName());
					    detailInfoVo.setShipmentDate(shipmentVo.getShipmentDate());
					    detailInfoVo.setStatus(shipmentVo.getStatus());
					    detailInfoVo.setWeightUnit(shipmentVo.getWeightUnit());
					    //detailInfoVo.setActualWeight(trackingResponse.getAWBInfo().get(0).getShipmentInfo().getWeight());
					  
					    if(upsActivity != null){
					    if(upsActivity.size() > 0){
					    	for (Loop_data activityLocation : upsActivity) {
					    		TrackingVo trackingVo = new TrackingVo(); 
					    		
					    		if( activityLocation.getDate() != null){
					    			trackingVo.setTrackDate(new SimpleDateFormat("yyyyMMdd").parse(  activityLocation.getDate()) );
					    		}
					    		if (activityLocation.getTime() != null) {
									trackingVo.setTrackTime( activityLocation.getTime());
								   }
					    		if( activityLocation.getLocation()  != null){
					    			trackingVo.setEventDescription( activityLocation.getLocation()  );
					    		}
					    		if(activityLocation.getCountryCode()  != null){
					    			trackingVo.setOriginServiceArea( activityLocation.getLocation()   );
					    		}
					    		trackingList.add(trackingVo);
					    	}
					    	trackingResponseVo.setTrackingList(trackingList);
					    }
					    
					    
			    }
					    trackingResponseVo.setDetailInfo(detailInfoVo);
						context.put(Attributes.TRACKING_RESPONSE, trackingResponseVo);
	} catch (Exception e) {
	    log.error(e);
	    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
	    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
	    return false;
	}
	return true;
    }


}

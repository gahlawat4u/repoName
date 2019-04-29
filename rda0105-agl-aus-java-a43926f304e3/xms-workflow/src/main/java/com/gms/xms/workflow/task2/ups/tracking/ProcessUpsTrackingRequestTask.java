package com.gms.xms.workflow.task2.ups.tracking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.delivery.ups.service.GsonContextLoader;
import com.gms.delivery.ups.service.rest.tracking.UPSTrackingApiService;
import com.gms.delivery.ups.service.rest.tracking.UPSTrackingRequest;
import com.gms.delivery.ups.service.rest.tracking.UpsTrackingResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.Task2;
public class ProcessUpsTrackingRequestTask implements Task2 {

	private static final Log log = LogFactory.getLog(ProcessUpsTrackingRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
	try {
		UPSTrackingRequest upsTrackingRequest =context.get(Attributes.TRACKING_REQUEST);
		UPSTrackingApiService upsTrackingApiService = new UPSTrackingApiService();
		//upsTrackingRequest.setAWBNumber("1Z06195E6693754179"); 
		String response =    upsTrackingApiService.getUPSTracking(upsTrackingRequest);
		UpsTrackingResponse UpsTrackingResponse = GsonContextLoader.getGsonContext().fromJson(response,UpsTrackingResponse.class);
	        context.put(Attributes.TRACKING_RESPONSE, UpsTrackingResponse);
	} catch (Exception e) {
	    log.error(e);
	    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
	    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
	    return false;
	}
	return true;
    }


}

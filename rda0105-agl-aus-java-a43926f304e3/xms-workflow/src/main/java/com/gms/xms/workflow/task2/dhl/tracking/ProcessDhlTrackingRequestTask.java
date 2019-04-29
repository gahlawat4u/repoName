package com.gms.xms.workflow.task2.dhl.tracking;

import com.gms.delivery.dhl.service.DhlTrackingService;
import com.gms.delivery.dhl.xmlpi.datatype.tracking.response.TrackingResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.dhl.DhlTrackingRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from ProcessDhlTrackingRequestTask
 * <p>
 * Author dattrinh Feb 1, 2016 4:20:06 PM
 */
public class ProcessDhlTrackingRequestTask implements Task2 {

    private static final Log log = LogFactory.getLog(ProcessDhlTrackingRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get Dhl Tracking Request.
            DhlTrackingRequestVo trackingRequestVo = context.get(Attributes.TRACKING_REQUEST);
            // Create xml request
            DhlTrackingService dhlTrackingService = new DhlTrackingService();
            String xmlRequest = dhlTrackingService.parseRequest(trackingRequestVo);
            // Send xml request and get xml response.
            String xmlResponse = "";
            xmlResponse = dhlTrackingService.execute(xmlRequest, xmlResponse);
            // Parse response.
            TrackingResponse trackResponse = dhlTrackingService.parseResponse(xmlResponse);
            // Put response to the context.
            context.put(Attributes.TRACKING_RESPONSE, trackResponse);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

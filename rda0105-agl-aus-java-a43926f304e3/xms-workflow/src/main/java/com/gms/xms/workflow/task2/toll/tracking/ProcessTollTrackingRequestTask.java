package com.gms.xms.workflow.task2.toll.tracking;

import com.gms.delivery.toll.service.TollTrackingService;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.TrackingModel;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from ProcessTollTrackingRequestTask
 * <p>
 * Author TANDT
 */
public class ProcessTollTrackingRequestTask implements Task2 {

    private static final Log log = LogFactory.getLog(ProcessTollTrackingRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get air-bill number from the context.
            String airbillNumber = context.get(Attributes.AIRBILL_NUMBER);
            // Get country code.
            TollTrackingService tollTrackingService = new TollTrackingService();
            // Send xml request and get xml response.
            String response = "";
            response = tollTrackingService.execute(airbillNumber);
            // Parse response.
            List<TrackingModel> trackingModels = new ArrayList<TrackingModel>();
            trackingModels = tollTrackingService.getTrackingInfo(response);
            // Put response to the context.
            context.put(Attributes.TRACKING_RESPONSE, trackingModels);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

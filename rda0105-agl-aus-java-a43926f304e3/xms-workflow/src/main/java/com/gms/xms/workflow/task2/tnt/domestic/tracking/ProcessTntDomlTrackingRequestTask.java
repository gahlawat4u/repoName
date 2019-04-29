package com.gms.xms.workflow.task2.tnt.domestic.tracking;

import com.gms.delivery.tnt.dom.service.TntDomTrackingService;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from ProcessTntDomlTrackingRequestTask
 * <p>
 * Author TANDT
 */
public class ProcessTntDomlTrackingRequestTask implements Task2 {

    private static final Log log = LogFactory.getLog(ProcessTntDomlTrackingRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) {
        try {
            // Get air-bill number from the context.
            String airbillNumber = context.get(Attributes.AIRBILL_NUMBER);
            // Create xml request
            TntDomTrackingService tntTrackingService = new TntDomTrackingService();
            String response = "";
            response = tntTrackingService.execute(airbillNumber);
            // Put response to the context.
            context.put(Attributes.TRACKING_RESPONSE, response);
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

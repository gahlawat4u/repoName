package com.gms.xms.workflow.task2.tnt.international.tracking;

import com.gms.delivery.tnt.service.TntTrackingService;
import com.gms.delivery.tnt.xmlpi.tracking.response.TrackResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from DoTntIntlTrackingRequestTask
 * <p>
 * Author dattrinh Feb 1, 2016 11:57:19 AM
 */
public class ProcessTntIntlTrackingRequestTask implements Task2 {

    private static final Log log = LogFactory.getLog(ProcessTntIntlTrackingRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Get air-bill number from the context.
            String airbillNumber = context.get(Attributes.AIRBILL_NUMBER);
            // Get country code.
            String countryCode = context.get(Attributes.COUNTRY_CODE);
            // Create xml request
            TntTrackingService tntTrackingService = new TntTrackingService();
            String xmlRequest = tntTrackingService.prepareXmlRequest(airbillNumber, countryCode);
            // Send xml request and get xml response.
            String xmlResponse = "";
            xmlResponse = tntTrackingService.execute(xmlRequest, xmlResponse);
            // Parse response.
            TrackResponse trackResponse = tntTrackingService.parseXmlResponse(xmlResponse);
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

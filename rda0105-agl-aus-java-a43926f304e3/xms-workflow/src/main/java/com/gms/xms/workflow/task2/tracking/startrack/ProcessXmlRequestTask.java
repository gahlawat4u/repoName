package com.gms.xms.workflow.task2.tracking.startrack;

import com.gms.delivery.startrack.service.StarTrackTrackingService;
import com.gms.delivery.startrack.xmlpi.tracking.response.GetConsignmentDetailResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jun 25, 2016 9:11:12 AM
 * <p>
 * Author dattrinh
 */
public class ProcessXmlRequestTask implements Task2 {

    private static final Log log = LogFactory.getLog(ProcessXmlRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // 1. Get xml request.
            String xmlRequest = context.getString(Attributes.STARTRACK_TRACKING_XML_REQUEST);
            // 2. Send xml request.
            StarTrackTrackingService trackingService = new StarTrackTrackingService();
            String xmlResponse = trackingService.execute(xmlRequest, null);
            if (StringUtils.isBlank(xmlResponse)) {
                throw new CustomException("Server interval error. Please contact the administrator for supporting.");
            }
            // 3. Get result.
            // Get error message first.
            String errorMessage = trackingService.getErrorMessage(xmlResponse);
            if (StringUtils.isBlank(errorMessage)) {
                // Get result if has no error.
                GetConsignmentDetailResponse detailResponse = trackingService.parseResponse(xmlResponse);
                // Put into the context.
                context.put(Attributes.STARTRACK_TRACKING_RESULT, detailResponse);
            } else {
                throw new CustomException(errorMessage);
            }
        } catch (Exception e) {
            log.error(e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

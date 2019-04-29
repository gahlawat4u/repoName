package com.gms.xms.workflow.task2.startrack.schedule;

import com.gms.delivery.startrack.service.StarTrackScheduleService;
import com.gms.delivery.startrack.xmlpi.tempuri.CreateBookingResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.startrack.StartrackShipmentRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jun 21, 2016 4:50:09 PM
 * <p>
 * Author huynd
 */
public class ProcessStartrackScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessStartrackScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            StartrackShipmentRequestVo startrackShipmentRequestVo = context.get(Attributes.STARTRACK_SHIPMENT_REQUEST_VO);
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
            StarTrackScheduleService starTrackScheduleService = new StarTrackScheduleService();

            String xmlRequest = starTrackScheduleService.prepareXmlRequest(startrackShipmentRequestVo);
            String xmlResponse = starTrackScheduleService.execute(xmlRequest, null);
            String errorMessage = starTrackScheduleService.getErrorMessage(xmlResponse);
            if (!StringUtils.isBlank(errorMessage)) {
                String errMsg = "Error message:" + errorMessage;
                throw new Exception(errMsg);
            } else {
                CreateBookingResponse bookingResponse = starTrackScheduleService.parseResponse(xmlResponse);
                String confirmationNumber = bookingResponse.getCreateBookingResult().getValue().getBookingNumber().getValue();
                scheduleCollectionVo.setConfirmationNo(confirmationNumber);
                context.put(Attributes.SCHEDULECOLLECTION_VO, scheduleCollectionVo);
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

}

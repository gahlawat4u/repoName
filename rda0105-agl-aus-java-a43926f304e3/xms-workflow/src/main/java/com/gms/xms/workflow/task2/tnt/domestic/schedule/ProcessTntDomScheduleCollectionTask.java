package com.gms.xms.workflow.task2.tnt.domestic.schedule;

import com.gms.delivery.tnt.dom.service.TntDomPickupService;
import com.gms.delivery.tntdom.jaxb.SubmitBookingResponse;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.shipment.TntDomShipmentRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from ProcessTntDomScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 3, 2016
 */
public class ProcessTntDomScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessTntDomScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TntDomShipmentRequestVo tntDomShipmentRequestVo = context.get(Attributes.TNT_DOM_SHIPMENT_REQUEST_VO);
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
            TntDomPickupService tntDomPickupService = new TntDomPickupService();

            String xmlRequest = tntDomPickupService.prepareXmlRequest(tntDomShipmentRequestVo);
            String xmlResponse = tntDomPickupService.execute(xmlRequest, null);
            SubmitBookingResponse bookingResponse = tntDomPickupService.parseResponse(xmlResponse);
            String errorMessage = tntDomPickupService.getErrorMessage(bookingResponse);
            String confirmationNumber = "";
            if (!StringUtils.isBlank(errorMessage)) {
                throw new Exception(errorMessage);
            } else {
                confirmationNumber = bookingResponse.getSubmitBookingResult().getValue().getOrderNumber().getValue();
            }
            scheduleCollectionVo.setConfirmationNo(confirmationNumber);
            context.put(Attributes.SCHEDULECOLLECTION_VO, scheduleCollectionVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

}

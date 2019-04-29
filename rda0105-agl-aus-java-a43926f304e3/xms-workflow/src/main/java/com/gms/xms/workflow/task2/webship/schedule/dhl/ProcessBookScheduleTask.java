package com.gms.xms.workflow.task2.webship.schedule.dhl;

import com.gms.delivery.dhl.service.DhlPickupServiceClient;
import com.gms.delivery.dhl.xmlpi.datatype.error.response.Condition;
import com.gms.delivery.dhl.xmlpi.datatype.pickup.response.BookPUResponse;
import com.gms.delivery.dhl.xmlpi.datatype.pickup.response.PickupResult;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.schedule.IScheduleCollectionService;
import com.gms.xms.persistence.service.schedule.ScheduleCollectionServiceImp;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.schedulecollection.BookPickupRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from ProcessModifyScheduleTask
 * <p>
 * Author dattrinh Jan 25, 2016 3:55:55 PM
 */
public class ProcessBookScheduleTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessBookScheduleTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            // Get ModifyPickupRequest.
            BookPickupRequestVo bookPickupRequestVo = context.get(Attributes.DHL_BOOK_SCHEDULE_REQUEST);

            ScheduleCollectionVo scheduleCollectionVo = bookPickupRequestVo.getScheduleCollection();
            // Connect to API Service.
            DhlPickupServiceClient dhlPickupServiceClient = new DhlPickupServiceClient();
            String xmlRequest = dhlPickupServiceClient.parsePickupXMLRequest(bookPickupRequestVo);
            String xmlResponse = dhlPickupServiceClient.executeGetPickup(xmlRequest, null);
            PickupResult pickupResult = dhlPickupServiceClient.parseResponse(xmlResponse);

            if (pickupResult.getErrorResponse() != null || pickupResult.getPickupErrorResponse() != null) {
                String errorMsg = "Fail to send pick up request.";
                if (pickupResult.getErrorResponse() != null) {
                    for (Condition condition : pickupResult.getErrorResponse().getResponse().getStatus().getCondition()) {
                        errorMsg += condition.getConditionCode() + " - " + condition.getConditionData() + "\n";
                    }
                } else if (pickupResult.getPickupErrorResponse() != null) {
                    for (Condition condition : pickupResult.getPickupErrorResponse().getResponse().getStatus().getCondition()) {
                        errorMsg += condition.getConditionCode() + " - " + condition.getConditionData() + "\n";
                    }
                }
                log.error(errorMsg);
                throw new Exception(errorMsg);
            } else {
                BookPUResponse bookPUResponse = pickupResult.getBookPUResponse();
                String comfirmationNumber = String.valueOf(bookPUResponse.getConfirmationNumber());
                scheduleCollectionVo.setConfirmationNo(comfirmationNumber);
                scheduleCollectionVo.setStatus((byte) 1);
                IScheduleCollectionService scheduleCollectionService = new ScheduleCollectionServiceImp();
                scheduleCollectionService.doSchedule(addInfo, bookPickupRequestVo.getPickupAddress(), scheduleCollectionVo);
                context.put(Attributes.SCHEDULECOLLECTION_VO, scheduleCollectionVo);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

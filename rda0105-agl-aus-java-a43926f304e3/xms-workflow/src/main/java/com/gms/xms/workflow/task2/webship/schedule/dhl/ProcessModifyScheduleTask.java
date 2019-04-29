package com.gms.xms.workflow.task2.webship.schedule.dhl;

import com.gms.delivery.dhl.service.DhlModifyPickupService;
import com.gms.delivery.dhl.xmlpi.datatype.pickup.modify.ModifyPUResult;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.schedule.IScheduleCollectionService;
import com.gms.xms.persistence.service.schedule.ScheduleCollectionServiceImp;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.schedulecollection.ModifyPickupRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from ProcessModifyScheduleTask
 * <p>
 * Author dattrinh Jan 25, 2016 3:55:55 PM
 */
public class ProcessModifyScheduleTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessModifyScheduleTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            // Get ModifyPickupRequest.
            ModifyPickupRequestVo modifyPickupRequestVo = context.get(Attributes.DHL_MODIFY_SCHEDULE_REQUEST);
            // Connect to API Service.
            DhlModifyPickupService modifyPickupService = new DhlModifyPickupService();
            String xmlRequest = modifyPickupService.parseRequest(modifyPickupRequestVo);
            String xmlResponse = modifyPickupService.execute(xmlRequest, null);
            ModifyPUResult modifyPUResult = modifyPickupService.parseResponse(xmlResponse);
            if (modifyPUResult.getPickupErrorResponse() != null) {
                String errorMessage = modifyPickupService.getErrorMessage(modifyPUResult.getPickupErrorResponse());
                throw new Exception(errorMessage);
            } else {
                // Get Schedule Collection.
                ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULE);
                // Get Pickup Address.
                AddressVo pickupAddressVo = context.get(Attributes.PICKUP_ADDRESS);
                // Set new confirmation number for the schedule collection.
                scheduleCollectionVo.setConfirmationNo(String.valueOf(modifyPUResult.getModifyPUResponse().getConfirmationNumber()));
                // Update the database.
                IScheduleCollectionService scheduleCollectionService = new ScheduleCollectionServiceImp();
                scheduleCollectionService.modifySchedule(addInfo, pickupAddressVo, scheduleCollectionVo);
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

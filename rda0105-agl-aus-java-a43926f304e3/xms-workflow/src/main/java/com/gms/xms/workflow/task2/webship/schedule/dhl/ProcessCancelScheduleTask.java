package com.gms.xms.workflow.task2.webship.schedule.dhl;

import com.gms.delivery.dhl.service.DhlCancelPickupService;
import com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response.CancelPUResult;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.dhl.DhlCancelPickupRequestVo;
import com.gms.xms.persistence.service.schedule.IScheduleCollectionService;
import com.gms.xms.persistence.service.schedule.ScheduleCollectionServiceImp;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from ProcessCancelScheduleTask
 * <p>
 * Author TANDT
 */
public class ProcessCancelScheduleTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessCancelScheduleTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get dhlCancelPickupRequestVo.
            DhlCancelPickupRequestVo dhlCancelPickupRequestVo = context.get(Attributes.DHL_VOID_SHIPMENT_REQUEST);
            DhlCancelPickupService cancelPickupService = new DhlCancelPickupService();
            String xmlRequest = cancelPickupService.parseRequest(dhlCancelPickupRequestVo);
            String xmlResponse = cancelPickupService.execute(xmlRequest, null);
            CancelPUResult cancelPUResult = cancelPickupService.parseResponse(xmlResponse);
            // Check if connect success to the DHL cancel pickup
            // service.
            if (cancelPUResult == null) {
                throw new Exception("Fail to send the DHL cancel collection request.");
            }
            // Check if success then update the database.
            if (cancelPUResult.getErrorResponse() == null) {
                IScheduleCollectionService service = new ScheduleCollectionServiceImp();
                service.cancelScheduleCollection(addInfo, dhlCancelPickupRequestVo.getScheduleCollection());
            } else {
                // String errorMsg = "Shipment can not cancel.";
                // errorMsg += "\n" + "Scheduled Pickup cannot be changed,
                // please contact Customer Representatives.";
                // throw new Exception(errorMsg);
                throw new Exception(cancelPickupService.getErrorMessage(cancelPUResult.getErrorResponse()));
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

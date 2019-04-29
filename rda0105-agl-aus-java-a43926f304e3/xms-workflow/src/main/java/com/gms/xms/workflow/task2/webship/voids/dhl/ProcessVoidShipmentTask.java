package com.gms.xms.workflow.task2.webship.voids.dhl;

import com.gms.delivery.dhl.service.DhlCancelPickupService;
import com.gms.delivery.dhl.xmlpi.datatype.cancelpickup.response.CancelPUResult;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.dhl.DhlCancelPickupRequestVo;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from ProcessModifyScheduleTask
 * <p>
 * Author dattrinh Jan 25, 2016 3:55:55 PM
 */
public class ProcessVoidShipmentTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessVoidShipmentTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get dhlCancelPickupRequestVo.
            DhlCancelPickupRequestVo dhlCancelPickupRequestVo = context.get(Attributes.DHL_VOID_SHIPMENT_REQUEST);

            DhlCancelPickupService cancelPickupService = new DhlCancelPickupService();
            if (dhlCancelPickupRequestVo.getScheduleCollection() != null) {
                String xmlRequest = cancelPickupService.parseRequest(dhlCancelPickupRequestVo);
                String xmlResponse = cancelPickupService.execute(xmlRequest, null);
                CancelPUResult cancelPUResult = cancelPickupService.parseResponse(xmlResponse);
                // Check if connect success to the DHL cancel pickup
                // service.
                if (cancelPUResult == null) {
                    throw new Exception("Fail to send the DHL cancel pickup request.");
                }
                // Check if success then update the database.
                if (cancelPUResult.getErrorResponse() == null) {
                    IShipmentService service = new ShipmentServiceImp();
                    service.voidShipment(addInfo, dhlCancelPickupRequestVo.getScheduleCollection(), dhlCancelPickupRequestVo.getShipment());
                } else {
                    // String errorMsg = "Shipment can not void.";
                    // errorMsg += "\n" + "Scheduled Pickup cannot be changed,
                    // please contact Customer Representatives.";
                    // throw new Exception(errorMsg);
                    throw new Exception(cancelPickupService.getErrorMessage(cancelPUResult.getErrorResponse()));
                }
            } else {
                IShipmentService service = new ShipmentServiceImp();
                service.voidShipment(addInfo, dhlCancelPickupRequestVo.getScheduleCollection(), dhlCancelPickupRequestVo.getShipment());
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

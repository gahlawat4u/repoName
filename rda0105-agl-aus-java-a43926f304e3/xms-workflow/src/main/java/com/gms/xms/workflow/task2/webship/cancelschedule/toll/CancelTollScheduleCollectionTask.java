package com.gms.xms.workflow.task2.webship.cancelschedule.toll;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.schedule.IScheduleCollectionService;
import com.gms.xms.persistence.service.schedule.ScheduleCollectionServiceImp;
import com.gms.xms.workflow.core2.Task2;

import java.util.Map;

/**
 * Created by thinhdd on 2/24/2017.
 */
public class CancelTollScheduleCollectionTask implements Task2 {
    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
        IScheduleCollectionService scheduleCollectionService = new ScheduleCollectionServiceImp();
        Long shipmentId = context.get(Attributes.SHIPMENT_ID);
        scheduleCollectionService.cancelScheduleByShipmentId(addInfo,shipmentId);
        return true;
    }
}

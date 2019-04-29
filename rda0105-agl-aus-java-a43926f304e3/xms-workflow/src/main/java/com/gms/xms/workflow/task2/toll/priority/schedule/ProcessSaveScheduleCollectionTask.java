package com.gms.xms.workflow.task2.toll.priority.schedule;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.schedule.IScheduleCollectionService;
import com.gms.xms.persistence.service.schedule.ScheduleCollectionServiceImp;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from ProcessSaveScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 17, 2016
 */
public class ProcessSaveScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessSaveScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            ScheduleCollectionVo scheduleCollection = context.get(Attributes.SCHEDULECOLLECTION_VO);
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            IScheduleCollectionService collectionService = new ScheduleCollectionServiceImp();
            collectionService.doSchedule(addInfo, scheduleCollection.getPickupAddress(), scheduleCollection);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}

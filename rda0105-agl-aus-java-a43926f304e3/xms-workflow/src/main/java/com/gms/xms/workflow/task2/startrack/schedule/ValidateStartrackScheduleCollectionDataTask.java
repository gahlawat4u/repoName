package com.gms.xms.workflow.task2.startrack.schedule;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from Jun 21, 2016 4:43:44 PM
 * <p>
 * Author huynd
 */
public class ValidateStartrackScheduleCollectionDataTask implements Task2 {
    private static final Log log = LogFactory.getLog(ValidateStartrackScheduleCollectionDataTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            Long shipmentId = context.getLong(Attributes.SHIPMENT_ID);
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
            if (shipmentId == null || shipmentId <= 0) {
                throw new Exception("Shipment is not valid.");
            }
            if (scheduleCollectionVo == null) {
                throw new Exception("Schedule collection datas are not valid.");
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

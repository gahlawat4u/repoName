package com.gms.xms.workflow.task.adjustment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.AirbillAdjustmentDetailDao;
import com.gms.xms.txndb.vo.adjustment.AirbillAdjustmentDetailFilter;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from CountAirbillAdjustmentDetailByFilterTask
 * <p>
 * Author DatTV Date May 12, 2015 4:52:28 PM
 */
public class CountAirbillAdjustmentDetailByFilterTask implements Task {
    private static final Log log = LogFactory.getLog(CountAirbillAdjustmentDetailByFilterTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        AirbillAdjustmentDetailDao adjustmentDetailDao = new AirbillAdjustmentDetailDao();
        try {
            AirbillAdjustmentDetailFilter filter = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_FILTER), AirbillAdjustmentDetailFilter.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to count of airbill adjustment by the filter
            Long count = adjustmentDetailDao.countDetailByFilter(filter);

            // Puts result into the context
            context.put(Attributes.AIRBILL_ADJUSTMENT_COUNT, String.valueOf(count));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

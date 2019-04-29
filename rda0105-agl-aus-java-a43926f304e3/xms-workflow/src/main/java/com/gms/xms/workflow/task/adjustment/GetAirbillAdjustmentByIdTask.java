package com.gms.xms.workflow.task.adjustment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.AirbillAdjustmentDao;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetAirbillAdjustmentByIdTask
 * <p>
 * Author DatTV Date May 14, 2015 5:03:01 PM
 */
public class GetAirbillAdjustmentByIdTask implements Task {
    private static final Log log = LogFactory.getLog(GetAirbillAdjustmentByIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
        try {
            Long adjustmentId = Long.valueOf(context.get(Attributes.AIRBILL_ADJUSTMENT_ID));
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get airbill adjustment by id
            AirbillAdjustmentVo adjustmentVo = adjustmentDao.selectById(adjustmentId);

            // Puts result into the context
            context.put(Attributes.AIRBILL_ADJUSTMENT_RESULT, GsonUtils.toGson(adjustmentVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

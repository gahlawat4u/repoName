package com.gms.xms.workflow.task.adjustmentrequest;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.adjustmentrequest.AirbillAdjustmentRequestDao;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetAirbillAdjustmentByIdTask
 * </p>
 *
 * @author hungnt - Nov 3, 2015
 */
public class GetAirbillAdjustmentByIdTask implements Task {
    private static final Log log = LogFactory.getLog(GetAirbillAdjustmentByIdTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        AirbillAdjustmentRequestDao adjustmentRequestDao = new AirbillAdjustmentRequestDao();
        try {
            Long adjustmentId = Long.valueOf(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_ID));
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get airbill adjustment by id
            AirbillAdjustmentRequestVo adjustmentRequestVo = adjustmentRequestDao.selectById(adjustmentId);

            // Puts result into the context
            context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_RESULT, GsonUtils.toGson(adjustmentRequestVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

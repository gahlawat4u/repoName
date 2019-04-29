package com.gms.xms.workflow.task.adjustmentrequest;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.adjustmentrequest.AirbillAdjustmentRequestDao;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestFilter;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetTotalPayableOfAdjustTypeTask
 * </p>
 *
 * @author hungnt - Nov 3, 2015
 */
public class GetTotalPayableOfAdjustTypeTask implements Task {
    private static final Log log = LogFactory.getLog(GetTotalPayableOfAdjustTypeTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        AirbillAdjustmentRequestDao adjustmentRequestDao = new AirbillAdjustmentRequestDao();
        try {
            AirbillAdjustmentRequestFilter filter = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_FILTER), AirbillAdjustmentRequestFilter.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get total payable of adjustment type of an airbill
            AirbillAdjustmentRequestVo adjustmentRequestVo = adjustmentRequestDao.selectTotalPayableOfAdjustType(filter);
            if (adjustmentRequestVo == null) {
                adjustmentRequestVo = AirbillAdjustmentRequestVo.createNotNullObject();
            } else {
                adjustmentRequestVo = AirbillAdjustmentRequestVo.removeNullValue(adjustmentRequestVo);
            }

            // Puts result into the context
            context.put(Attributes.AIRBILL_TOTAL_PAYABLE_BY_ADJUST_TYPE, GsonUtils.toGson(adjustmentRequestVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

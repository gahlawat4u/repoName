package com.gms.xms.workflow.task.adjustment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.AirbillAdjustmentDao;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.adjustment.AdjustmentRequestFilterVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetTotalAdjustAmountOfAWBTask
 * <p>
 * Author DatTV Date May 29, 2015 11:44:17 AM
 */
public class GetTotalAdjustAmountOfAWBTask implements Task {
    private static final Log log = LogFactory.getLog(GetTotalAdjustAmountOfAWBTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        AirbillAdjustmentDao adjustmentDao = new AirbillAdjustmentDao();
        try {
            AdjustmentRequestFilterVo filter = GsonUtils.fromGson(context.get(Attributes.ADJUSTMENT_REQUEST_FILTER), AdjustmentRequestFilterVo.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get total adjusted amount of an airbill
            AirbillAdjustmentVo adjustmentVo = adjustmentDao.selectTotalAdjustAmountOfAWB(filter);
            if (adjustmentVo == null) {
                adjustmentVo = AirbillAdjustmentVo.createNotNullObject();
            } else {
                adjustmentVo = AirbillAdjustmentVo.removeNullValue(adjustmentVo);
            }

            // Puts result into the context
            context.put(Attributes.AIRBILL_ADJUSTED_AMOUNT_TOTAL, GsonUtils.toGson(adjustmentVo));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

package com.gms.xms.workflow.task.adjustmentrequest;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.adjustmentrequest.AirbillAdjustmentRequestDetailDao;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestDetailFilter;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestDetailVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetAirbillAdjustmentDetailByFilterTask
 * </p>
 *
 * @author hungnt - Nov 3, 2015
 */
public class GetAirbillAdjustmentDetailByFilterTask implements Task {
    private static final Log log = LogFactory.getLog(GetAirbillAdjustmentDetailByFilterTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        AirbillAdjustmentRequestDetailDao adjustmentRequestDetailDao = new AirbillAdjustmentRequestDetailDao();
        try {
            AirbillAdjustmentRequestDetailFilter filter = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_DETAIL_FILTER), AirbillAdjustmentRequestDetailFilter.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get airbill adjustment by the filter
            List<AirbillAdjustmentRequestDetailVo> adjustmentRequestDetailVos = adjustmentRequestDetailDao.selectDetailByFilter(filter);

            // Puts result into the context
            context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_LIST_RESULT, GsonUtils.toGson(adjustmentRequestDetailVos));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}

package com.gms.xms.workflow.task.adjustmentrequest;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.adjustmentrequest.AirbillAdjustmentRequestTotalDao;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestDetailFilter;
import com.gms.xms.txndb.vo.adjustmentrequest.AirbillAdjustmentRequestTotalVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from TotalAirbillAdjustmentDetailByFilterTask
 * </p>
 *
 * @author hungnt - Nov 4, 2015
 */
public class TotalAirbillAdjustmentDetailByFilterTask implements Task {
    private static final Log log = LogFactory.getLog(TotalAirbillAdjustmentDetailByFilterTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        AirbillAdjustmentRequestTotalDao adjustmentRequestTotalDao = new AirbillAdjustmentRequestTotalDao();
        try {
            AirbillAdjustmentRequestDetailFilter filter = GsonUtils.fromGson(context.get(Attributes.AIRBILL_ADJUSTMENT_REQUEST_DETAIL_FILTER), AirbillAdjustmentRequestDetailFilter.class);
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);

            // Do DAO service to get total of airbill adjustment by the filter
            AirbillAdjustmentRequestTotalVo total = adjustmentRequestTotalDao.totalDetailByFilter(filter);

            // Puts result into the context
            context.put(Attributes.AIRBILL_ADJUSTMENT_REQUEST_TOTAL, GsonUtils.toGson(total));
        } catch (Exception e) {
            log.error(e.getMessage());
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
